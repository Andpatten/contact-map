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


  @ColumnInfo(name = "query_id")
  private long queryId;

  private boolean asc;

  private boolean alphabetical;


}
