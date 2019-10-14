package com.andpatten.contactmap.model.entity;

import android.provider.ContactsContract.RawContacts;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Query {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "query_id")
  private long id;

  private RawContacts contact; //TODO double check "RawContacts" in documentation.


}
