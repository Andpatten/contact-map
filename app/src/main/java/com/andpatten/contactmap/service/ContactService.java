/*This work is copyright 2019, Andrew Patten. All right reserved.
 */
package com.andpatten.contactmap.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import com.andpatten.contactmap.BuildConfig;
import com.andpatten.contactmap.controller.MainActivity;
import com.andpatten.contactmap.model.pojo.Contact;

import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.maps.model.LatLng;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.maps.android.SphericalUtil;
import com.google.maps.GeoApiContext;

public class ContactService {

  private static Context applicationContext;
  private static Location myLocation;

  private ExecutorService executor = Executors.newSingleThreadExecutor();


  private ContactService() {
    //TODO fill in additional code as needed.
  }
  public static void setApplicationContext(Context applicationContext) {
    ContactService.applicationContext = applicationContext;
  }

  /**
   * Gets an instance of the ContactService class.
   * @return
   */
  public static ContactService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Gets a List of contacts and compares distance between each contact and the user.
   * @param q
   * @param maxDistance
   * @return
   */
  public List<Contact> getLocalContacts(Location location, String q, int maxDistance) {
    ContentResolver resolver = applicationContext.getContentResolver();

    List<Contact> contacts = new ArrayList<>();

    Cursor cursor = resolver
        .query(Contacts.CONTENT_URI,
            new String[]{Contacts._ID, Contacts.LOOKUP_KEY, Contacts.DISPLAY_NAME_PRIMARY},
            Contacts.DISPLAY_NAME_PRIMARY + " like ?", new String[]{"%" + q + "%"}, null);
    while (cursor.moveToNext()) {

      Contact contact = new Contact();
      String id = cursor.getString(cursor.getColumnIndex(Contacts._ID));
      String displayName = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME_PRIMARY));
      Uri uri = Uri.withAppendedPath(Contacts.CONTENT_URI, id);
      contact.setDisplayName(displayName);
      contact.setUri(uri);
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
      contacts.add(contact);
    }
    return contacts;
  }

  private static class InstanceHolder {
    private static final ContactService INSTANCE;

    static  {
      INSTANCE = new ContactService();
    }
  }

  /**
   * Sets the users location.
   * @param myLocation
   */
  public static void setMyLocation(Location myLocation) {
    ContactService.myLocation = myLocation;
  }
}
