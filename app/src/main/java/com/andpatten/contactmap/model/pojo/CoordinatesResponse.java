/*This work is copyright 2019, Andrew Patten. All right reserved.
 */

package com.andpatten.contactmap.model.pojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CoordinatesResponse {

  private List<Item> results;

  public List<Item> getResults() {
    return results;
  }

  public void setResults(
      List<Item> results) {
    this.results = results;
  }

  public static class Item {

    @SerializedName("geometry")
    private Coordinates coordinates;

    public Coordinates getCoordinates() {
      return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
      this.coordinates = coordinates;
    }

  }

}