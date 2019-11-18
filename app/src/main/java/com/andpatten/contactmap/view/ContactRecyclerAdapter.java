package com.andpatten.contactmap.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.andpatten.contactmap.R;
import com.andpatten.contactmap.model.pojo.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ContactHolder> {

  private ArrayList<Contact> contactList;

public ContactRecyclerAdapter(ArrayList<Contact> contactList) {
  this.contactList = contactList;
}

  public void updateContactList(List<Contact> newContactList) {
    contactList.clear();
    contactList.addAll(newContactList);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.contact_list, parent, false);
    return new ContactHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
    TextView name = holder.itemView.findViewById(R.id.name);
    TextView number = holder.itemView.findViewById(R.id.number);

    number.setText(contactList.get(position).getPhoneNumber());
    name.setText(contactList.get(position).getDisplayName());
  }

  @Override
  public int getItemCount() {
    return contactList.size();
  }

  public class ContactHolder extends RecyclerView.ViewHolder {

    public View itemView;

    public ContactHolder(@NonNull View itemView) {
      super(itemView);
      this.itemView = itemView;
    }
  }



}
