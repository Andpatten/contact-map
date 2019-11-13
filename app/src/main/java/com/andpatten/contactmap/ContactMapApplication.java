package com.andpatten.contactmap;

import android.app.Application;
import android.location.Address;
import android.location.Geocoder;
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

// private void getLatLongFromAddress(String address)
//  {
//    double lat= 0.0, lng= 0.0;
//
//    Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
//    try
//    {
//      List<Address> addresses = geoCoder.getFromLocationName(address , 1);
//      if (addresses.size() > 0)
//      {
//        GeoPoint p = new GeoPoint(
//            (int) (addresses.get(0).getLatitude() * 1E6),
//            (int) (addresses.get(0).getLongitude() * 1E6));
//
//        lat=p.getLatitudeE6()/1E6;
//        lng=p.getLongitudeE6()/1E6;
//
//        Log.d("Latitude", ""+lat);
//        Log.d("Longitude", ""+lng);
//      }
//    }
//    catch(Exception e)
//    {
//      e.printStackTrace();
//    }
//  }

}
