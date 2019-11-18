package com.andpatten.contactmap.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.andpatten.contactmap.model.pojo.Contact;
import com.andpatten.contactmap.service.ContactService;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

  private MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();

  public MainViewModel(@NonNull Application application) {
    super(application);
    ContactService contactService = ContactService.getInstance();
    contacts.setValue(contactService.getContacts(null, 3.0f, false));

  }

  public LiveData<List<Contact>> getContacts() {
    return contacts;
  }
}
