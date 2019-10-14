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

  @ColumnInfo(name = "query_id")
  private long queryId;

  

  private long state;

  private long distance;

  private long limit;

}
