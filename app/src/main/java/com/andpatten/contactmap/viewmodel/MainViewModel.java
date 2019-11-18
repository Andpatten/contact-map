package com.andpatten.contactmap.viewmodel;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.Contacts;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.andpatten.contactmap.BuildConfig;
import com.andpatten.contactmap.model.pojo.Contact;
import com.andpatten.contactmap.service.ContactService;
import com.andpatten.contactmap.service.GeoService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel {

  private MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();
  private CompositeDisposable pending = new CompositeDisposable();
  private GeoService geoService = GeoService.getInstance();
  private Executor executor = Executors.newSingleThreadExecutor();
  private static Context applicationContext;

  public static void setApplicationContext(Context applicationContext) {
    MainViewModel.applicationContext = applicationContext;
  }

  public MainViewModel(@NonNull Application application) {
    super(application);
    ContactService contactService = ContactService.getInstance();
    contacts.setValue(contactService.getContacts("Bob", 3.0f, false));

  }

  public LiveData<List<Contact>> getContacts() {
    return contacts;
  }




}
