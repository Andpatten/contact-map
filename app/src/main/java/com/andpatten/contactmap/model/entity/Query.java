/*This work is copyright 2019, Andrew Patten. All right reserved.
 */

package com.andpatten.contactmap.model.entity;

import android.provider.ContactsContract.RawContacts;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//import com.google.maps.GeoApiContext;
//import com.google.maps.android.SphericalUtil;
@Entity
public class Query {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "query_id")
  private long id;

  private long rawContactId;

  private String name;

  private double longitude;

  private double latitude;

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }


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
