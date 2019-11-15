package com.andpatten.contactmap;

import android.app.Application;
import android.content.ContentValues;
import android.location.Address;
import android.location.Geocoder;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import com.andpatten.contactmap.service.ContactMapDatabase;
import com.facebook.stetho.Stetho;
import java.util.List;
import java.util.Locale;

public class ContactMapApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
//    Picasso.setSingletonInstance(
//        new Picasso.Builder(this)
//            .loggingEnabled(true)
//            .build());
    ContactMapDatabase.setApplicationContext(this);
    final ContactMapDatabase database = ContactMapDatabase.getInstance();
    new Thread(new Runnable() {
      @Override
      public void run() {
        database.getQueryDao().delete();
      }
    }).start();

  }


}
