/*This work is copyright 2019, Andrew Patten. All right reserved.
 */
package com.andpatten.contactmap.controller;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import com.andpatten.contactmap.R;
import com.andpatten.contactmap.model.pojo.Contact;
import com.andpatten.contactmap.viewmodel.MainViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Main user interface for accessing users location, checking permissions, and displaying contacts.
 */
public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  private static final int CONTACTS_PERMISSIONS_REQUEST_CODE = 100;
  private static final int LOCATION_PERMISSIONS_REQUEST_CODE = 200;

  private MainViewModel viewModel;
  private FusedLocationProviderClient fusedLocationProviderClient;
  private Location myLocation;
  private SearchView contactSearch;
  private SeekBar maxDistance;
  private ListView contactList;


  /**
   * Initializes UI and sets up observers for backing ViewModel data.
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    contactSearch = findViewById(R.id.contact_search);
    contactSearch.setOnQueryTextListener(new OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        viewModel.search(contactSearch.getQuery().toString(), maxDistance.getProgress());
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });
    maxDistance = findViewById(R.id.max_distance);
    contactList = findViewById(R.id.contact_list);
    contactList.setOnItemClickListener((parent, view, position, id) -> {
      Contact contact = (Contact) parent.getItemAtPosition(position);
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(contact.getUri());
      startActivity(intent);
    });
    checkPermissions();


    //testing mainviewmodel and contact retrieval
    viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getContacts().observe(this, (contacts) -> {
      ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
      contactList.setAdapter(adapter);
    });
    setupLocationListener();

  }

  private void setupLocationListener() {
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    fusedLocationProviderClient.getLastLocation()
        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
          @Override
          public void onSuccess(Location location) {
            if (location == null) {
              Log.e(TAG, "onSuccess: null location");
            } else {
              myLocation = location;
//              ContactService.setMyLocation(myLocation);
              viewModel.setLocation(location);
            }
          }
        })
        .addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
            Log.e(e.getClass().getSimpleName(), e.getMessage(), e);
          }
        });
  }

  /**
   * Inflates the menu resource. This adds items to the action bar if it is present.
   *
   * @param menu
   * @return
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  /**
   * Handles selections from the options menu.
   *
   * @param item
   * @return
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * Requests permission to use contact and location data.
   */
  private void checkPermissions() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
          Manifest.permission.READ_CONTACTS)) {
      }
      ActivityCompat.requestPermissions(this,
          new String[]{Manifest.permission.READ_CONTACTS},
          CONTACTS_PERMISSIONS_REQUEST_CODE);
    }

    if (ContextCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
          permission.ACCESS_FINE_LOCATION)) {
      }
      ActivityCompat.requestPermissions(this,
          new String[]{permission.ACCESS_FINE_LOCATION},
          CONTACTS_PERMISSIONS_REQUEST_CODE);
    } else {
      setupLocationListener();
    }

  }

  /**
   * Checks if permission was granted and shuts down the app if permission was denied.
   *
   * @param requestCode
   * @param permissions
   * @param grantResults
   */
  @Override
  public void onRequestPermissionsResult(int requestCode,
      String[] permissions, int[] grantResults) {
    switch (requestCode) {
      case CONTACTS_PERMISSIONS_REQUEST_CODE:
        if (grantResults.length == 0
            || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
          Log.d("Permissions", "Permission denied!");
          this.finishAffinity();
          Toast.makeText(this,
              "Contact Map requires access to your contacts to run. Please re-run to allow permission",
              Toast.LENGTH_LONG).show();
        }

      case LOCATION_PERMISSIONS_REQUEST_CODE:
        if (grantResults.length == 0
            || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
          Log.d("Permissions", "Permission denied!");
          this.finishAffinity();
          Toast.makeText(this,
              "Contact Map requires access to your location to run. Please re-run to allow permission",
              Toast.LENGTH_LONG).show();
        } else {
          setupLocationListener();
        }


    }

  }


}


