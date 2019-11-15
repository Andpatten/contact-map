package com.andpatten.contactmap.service;

import static com.andpatten.contactmap.BuildConfig.API_KEY;
import static com.andpatten.contactmap.service.GeoService.baseUrl;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
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
    List<Contact> contacts = new ArrayList<>();
    Cursor cursor = applicationContext.getContentResolver()
        .query(Data.CONTENT_URI, new String[]{Data._ID, Phone.NUMBER, StructuredName.DISPLAY_NAME}, null, null, null); //FIXME 2nd is filter values, 3rd is specify sort order
    while (cursor.moveToNext()) {
      Contact contact = new Contact();
//      String street = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
//      String state = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
//      String city = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
      contacts.add(contact);
    }
    //TODO Sort contacts
    //Does retreive empty contact list from android contacts. 
    for (Contact contact :
        contacts) {
      System.out.println(contact.toString());
    }
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
