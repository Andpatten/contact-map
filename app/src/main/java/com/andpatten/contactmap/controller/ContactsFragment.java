package com.andpatten.contactmap.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.andpatten.contactmap.R;
import com.andpatten.contactmap.service.ContactService;
import com.andpatten.contactmap.viewmodel.MainViewModel;

public class ContactsFragment extends Fragment {

  public ContactsFragment() {
    //Required empty public constructor.
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.contact_list, null, false);

    return view;
  }
//
//  @Override
//  public int getLayout() {
//  return (R.layout.contact_list);
//  }

  //Recyclerview (adapter) (layout manager)
}
