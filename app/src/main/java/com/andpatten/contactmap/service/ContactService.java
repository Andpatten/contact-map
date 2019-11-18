package com.andpatten.contactmap.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import com.andpatten.contactmap.model.pojo.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactService {

  private static Context applicationContext;

  private ContactService() {
    //TODO fill in additional code as needed.
  }
  public static void setApplicationContext(Context applicationContext) {
    ContactService.applicationContext = applicationContext;
  }

  public static ContactService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public List<Contact> getContacts(String keyword, Float maxDistance, Boolean alphabetical) {
    ContentResolver resolver = applicationContext.getContentResolver();


    List<Contact> contacts = new ArrayList<>();
    //TODO Investigate specifying partial filter through args.


    Cursor cursor = resolver
        .query(Contacts.CONTENT_URI, new String[]{Contacts._ID, Contacts.LOOKUP_KEY, Contacts.DISPLAY_NAME_PRIMARY}, null, null, null); //FIXME 2nd is filter values, 3rd is specify sort order
    while (cursor.moveToNext()) {

      Contact contact = new Contact();
      String id = cursor.getString(cursor.getColumnIndex(Contacts._ID));
      String displayName = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME_PRIMARY));

      //TODO finish getting addresses from Contacts for geocoding api requests.
//      String street = cursor.getString(cursor.getColumnIndex(StructuredPostal.STREET));
//      String city = cursor.getString(cursor.getColumnIndex(StructuredPostal.CITY));
//      String state = cursor.getString(cursor.getColumnIndex(StructuredPostal.COUNTRY));

      contact.setDisplayName(displayName);
      Cursor phones = resolver.query(Phone.CONTENT_URI, null,
          Phone.CONTACT_ID + " = " + id, null, null);
      while (phones.moveToNext()) {
        int type = phones.getInt(phones.getColumnIndex(Phone.TYPE));
        String number = phones.getString(phones.getColumnIndex(Phone.NUMBER));
        contact.setPhoneNumber(number);
      }
      //TODO Apply additional filter if neccesary.
      contacts.add(contact);
    }
    //TODO Sort contacts
    //Does retreive empty contact list from android contacts.
    Log.d(getClass().getSimpleName(), contacts.toString());
    return contacts;
  }

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
