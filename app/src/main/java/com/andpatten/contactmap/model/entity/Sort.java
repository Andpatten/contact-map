/*This work is copyright 2019, Andrew Patten. All right reserved.
 */

package com.andpatten.contactmap.model.entity;

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
public class Sort {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "sort_id")
  private long id;

  @ColumnInfo(name = "query_id", index = true)
  private long queryId;

  private boolean asc;

  private boolean alphabetical;

  public void setAsc(boolean asc) {
    this.asc = asc;
  }

  public void setAlphabetical(boolean alphabetical) {
    this.alphabetical = alphabetical;
  }

  public long getId() {
    return id;
  }

  public long getQueryId() {
    return queryId;
  }

  public boolean isAsc() {
    return asc;
  }

  public boolean isAlphabetical() {
    return alphabetical;
  }

  public void setQueryId(long queryId) {
    this.queryId = queryId;
  }

  public void setId(long id) {
    this.id = id;
  }
}
