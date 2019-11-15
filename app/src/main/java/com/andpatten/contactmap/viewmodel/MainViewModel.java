package com.andpatten.contactmap.viewmodel;

import com.andpatten.contactmap.model.pojo.Contact;
import com.andpatten.contactmap.service.ContactService;

public class MainViewModel {

  public MainViewModel() {
    ContactService contsvc = ContactService.getInstance();
    contsvc.getContacts(null, 3.0f, false);
  }

}
