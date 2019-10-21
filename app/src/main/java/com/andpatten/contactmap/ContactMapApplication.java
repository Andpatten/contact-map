package com.andpatten.contactmap;

import android.app.Application;
import com.andpatten.contactmap.service.ContactMapDatabase;
import com.facebook.stetho.Stetho;

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
