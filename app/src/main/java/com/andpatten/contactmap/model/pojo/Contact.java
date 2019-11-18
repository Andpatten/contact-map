package com.andpatten.contactmap.model.pojo;

import android.net.Uri;
import androidx.annotation.NonNull;

public class Contact {

  private long id;
  private String displayName;
  private String phoneNumber;
  private Uri uri;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Uri getUri() {
    return uri;
  }

  public void setUri(Uri uri) {
    this.uri = uri;
  }

  @NonNull
  @Override
  public String toString() {
    return String.format("%1$s (%2$s)", displayName, (phoneNumber != null) ? phoneNumber : "none");
  }
}
