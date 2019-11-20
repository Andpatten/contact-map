/*This work is copyright 2019, Andrew Patten. All right reserved.
 */
package com.andpatten.contactmap.viewmodel;

import android.app.Application;
import android.location.Location;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.andpatten.contactmap.BuildConfig;
import com.andpatten.contactmap.model.pojo.Contact;
import com.andpatten.contactmap.service.ContactService;
import com.andpatten.contactmap.service.GeoService;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel {

  private MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();
  private CompositeDisposable pending = new CompositeDisposable();
  private GeoService geoService = GeoService.getInstance();
  private Executor executor = Executors.newSingleThreadExecutor();
  private MutableLiveData<Location> location = new MutableLiveData<>();
  private final ContactService contactService;

  public MainViewModel(@NonNull Application application) {
    super(application);
    contactService = ContactService.getInstance();
  }

  public void search(String term, int maxDistance) {
    Location location = this.location.getValue();
    List<Contact> local = contactService.getLocalContacts(location, term, maxDistance);
    Flowable.fromIterable(local)
        .flatMap((contact) ->
            GeoService.getInstance().getCoordinates(contact.getStreet(), BuildConfig.API_KEY)
                .subscribeOn(Schedulers.from(executor))
                .map((response) -> {
                  contact.setLatitude(
                      response.getResults().get(0).getCoordinates().getLocation().getLatitude());
                  contact.setLongitude(
                      response.getResults().get(0).getCoordinates().getLocation().getLongitude());
                  LatLng temp = new LatLng(contact.getLatitude(), contact.getLongitude());
                  Log.d(getClass().getSimpleName(), "Getting distance to " + temp.toString());
                  LatLng tempUser = new LatLng(location.getLatitude(), location.getLongitude());
                  contact.setDistance((float) SphericalUtil.computeDistanceBetween(tempUser, temp));
                  return contact;
                })
                .toFlowable())
        .filter((contact) -> maxDistance <= 0 || contact.getDistance() / 1609 <= maxDistance)
        .collectInto(new ArrayList<Contact>(), (list, contact) -> list.add(contact))
        .subscribe((results) -> {
          Collections.sort(results, (c1, c2) -> Float.compare(c1.getDistance(), c2.getDistance()));
          this.contacts.postValue(results);
        });
  }

  public LiveData<List<Contact>> getContacts() {
    return contacts;
  }

  public void setLocation(Location location) {
    this.location.setValue(location);
  }

}
