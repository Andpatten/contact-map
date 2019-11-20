/*This work is copyright 2019, Andrew Patten. All right reserved.
 */

package com.andpatten.contactmap.model.pojo;

import android.net.Uri;
import androidx.annotation.NonNull;

public class Contact {

  private long id;
  private String displayName;
  private String phoneNumber;
  private Uri uri;
  private String street;
  private String city;
  private String state;
  private Float latitude;
  private Float longitude;
  private Float distance;

  //delete after test worked
  public Contact(String bob) {
    this.displayName = bob;

  }

  public Contact() {

  }

  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }

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
    return String.format("%1$s [%2$s] Distance: %3$.1f miles", displayName, (phoneNumber != null) ? phoneNumber : "none", distance/1609);
  }
}
