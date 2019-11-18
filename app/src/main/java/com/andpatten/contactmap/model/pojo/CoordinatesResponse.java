package com.andpatten.contactmap.model.pojo;

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

    private Coordinates coordinates;

    public Coordinates getCoordinates() {
      return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
      this.coordinates = coordinates;
    }

  }

}