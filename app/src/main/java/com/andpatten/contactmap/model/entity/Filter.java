/*This work is copyright 2019, Andrew Patten. All right reserved.
 */

package com.andpatten.contactmap.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Query.class,
            childColumns = "query_id",
            parentColumns = "query_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)

public class Filter {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "filter_id")
  private long id;

  @ColumnInfo(name = "query_id", index = true)
  private long queryId;

  private long state;

  private long distance;

  private long limit;


  public void setState(long state) {
    this.state = state;
  }

  public void setDistance(long distance) {
    this.distance = distance;
  }

  public void setLimit(long limit) {
    this.limit = limit;
  }

  public long getId() {
    return id;
  }

  public long getQueryId() {
    return queryId;
  }

  public void setQueryId(long queryId) {
    this.queryId = queryId;
  }

  public long getState() {
    return state;
  }

  public long getDistance() {
    return distance;
  }

  public long getLimit() {
    return limit;
  }

  public void setId(long id) {
    this.id = id;
  }
}
