package com.andpatten.contactmap.model.entity;

import android.provider.ContactsContract.RawContacts;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Query {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "query_id")
  private long id;

  private long rawContactId; //90% sure "RawContacts" is what I need here.

  private String name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getRawContactId() {
    return rawContactId;
  }

  public void setRawContactId(long rawContactId) {
    this.rawContactId = rawContactId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
