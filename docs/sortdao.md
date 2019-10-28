package com.andpatten.contactmap.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.andpatten.contactmap.model.entity.Sort;
import java.util.List;

@Dao
public interface SortDao {

  @Insert
  long insert(Sort sort);

  //TODO LocationId from RawContacts
  @Query("SELECT * FROM Sort WHERE sort_id = :sortId ORDER BY sort_id ASC")
  Sort getSortBySortId(long sortId);

  @Query("SELECT * FROM Sort WHERE sort_id = :queryId ORDER BY query_id ASC")
  LiveData<List<Sort>> getSortbyQueryId(long queryId);

  @Update
  int update(Sort sort);

  @Delete
  int delete(Sort sort);



}