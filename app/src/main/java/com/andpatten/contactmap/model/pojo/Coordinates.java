/*This work is copyright 2019, Andrew Patten. All right reserved.
 */

package com.andpatten.contactmap.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinates {

  private Location location;

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public static class Location {

    @Expose
    @SerializedName("lat")
    private Float latitude;

    @Expose
    @SerializedName("lng")
    private Float longitude;

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

  }

}
