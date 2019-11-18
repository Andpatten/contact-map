package com.andpatten.contactmap.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import com.andpatten.contactmap.BuildConfig;
import com.andpatten.contactmap.model.pojo.Contact;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactService {

  private static Context applicationContext;
  private ExecutorService executor = Executors.newSingleThreadExecutor();
  private ContactService() {
    //TODO fill in additional code as needed.
  }
  public static void setApplicationContext(Context applicationContext) {
    ContactService.applicationContext = applicationContext;
  }

  public static ContactService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public List<Contact> getContacts(String q, Float maxDistance, Boolean alphabetical) {
    ContentResolver resolver = applicationContext.getContentResolver();


    List<Contact> contacts = new ArrayList<>();


    Cursor cursor = resolver
        .query(Contacts.CONTENT_URI,
            new String[]{Contacts._ID, Contacts.LOOKUP_KEY, Contacts.DISPLAY_NAME_PRIMARY},
            Contacts.DISPLAY_NAME_PRIMARY + " like ?", new String[] {"%" + q + "%"}, null);
    while (cursor.moveToNext()) {

      Contact contact = new Contact();
      String id = cursor.getString(cursor.getColumnIndex(Contacts._ID));
      String displayName = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME_PRIMARY));

      contact.setDisplayName(displayName);
      Cursor address = resolver.query(StructuredPostal.CONTENT_URI, null,
          StructuredPostal.CONTACT_ID + " = " + id, null, null);
      while (address.moveToNext()) {
        String street = address.getString(address.getColumnIndex(StructuredPostal.STREET));
        String city = address.getString(address.getColumnIndex(StructuredPostal.CITY));
        String state = address.getString(address.getColumnIndex(StructuredPostal.REGION));
        contact.setStreet(street);
        contact.setCity(city);
        contact.setState(state);
      }
      Cursor phones = resolver.query(Phone.CONTENT_URI, null,
          Phone.CONTACT_ID + " = " + id, null, null);
      while (phones.moveToNext()) {
        int type = phones.getInt(phones.getColumnIndex(Phone.TYPE));
        String number = phones.getString(phones.getColumnIndex(Phone.NUMBER));
        contact.setPhoneNumber(number);
      }

      String query = contact.getStreet();//String.format("%s, %s, %s", contact.getStreet(), contact.getCity(), contact.getState());
      GeoService.getInstance().getCoordinates(query, BuildConfig.API_KEY)
          .subscribeOn(Schedulers.from(executor))
          .subscribe(
              (response) -> {
                contact.setLatitude(response.getResults().get(0).getCoordinates().getLocation().getLatitude());
                contact.setLongitude(response.getResults().get(0).getCoordinates().getLocation().getLongitude());
                // TODO Get distance from current location to latitude & longitude; store in contact;
                //TODO Apply additional filter if neccesary.
                contacts.add(contact);
              },
              (ex) -> {
                Log.e(ex.getClass().getSimpleName(), ex.getMessage(), ex);
              });
    }
    if (alphabetical) {
      Collections.sort(contacts, (a, b) -> a.getDisplayName().compareToIgnoreCase(b.getDisplayName()));
    } else {
      Collections.sort(contacts, (a, b) -> Float.compare(a.getDistance(), b.getDistance()));
    }
    //Does retreive empty contact list from android contacts.
    Log.d(getClass().getSimpleName(), contacts.toString());
    return contacts;
  }
  //TODO lookup -> can we define custom attributes for latitude and longitude for each contact?

//  Cursor cursor = new Cursor();
//
//  String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//  Cursor address = ContentResolver
//      .query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null,
//          ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID, new String[]{id},
//          null);
//
//    String name = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.DISPLAY_NAME));
//
//  String fullUrl =
//      baseUrl + "json?address=" + street + ",+" + city + ",+" + state + "&key=" + API_KEY;
//// ^ must match https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=[YOUR_API_KEY]

  private static class InstanceHolder {
    private static final ContactService INSTANCE;

    static  {
      INSTANCE = new ContactService();
    }
  }

}
