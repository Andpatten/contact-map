package com.andpatten.contactmap.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.andpatten.contactmap.R;
import com.andpatten.contactmap.model.pojo.Contact;
import com.andpatten.contactmap.service.ContactService;
import com.andpatten.contactmap.view.ContactRecyclerAdapter;
import com.andpatten.contactmap.viewmodel.MainViewModel;
import java.util.LinkedList;

public abstract class ContactsFragment extends Fragment {

  private MainViewModel viewModel;
  private ContactRecyclerAdapter adapter;
  private TextView name;
  private TextView number;
  private RecyclerView contacts;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(getLayout(), container, false);
    contacts = view.findViewById(R.id.contacts);
    name = view.findViewById(R.id.name);
    number = view.findViewById(R.id.number);
    return view;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.contact_list, null, false);

    return view;
  }

  private MainViewModel getViewModel() {
    return viewModel;
  }

  public abstract LinkedList<Contact> contactLinkedList(MainViewModel viewModel);

  public abstract int getLayout();

}
