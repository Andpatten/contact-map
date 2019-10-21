package com.andpatten.contactmap.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.andpatten.contactmap.model.entity.Filter;
import java.util.List;

@Dao
public interface FilterDao {

  @Insert
  long insert(Filter filter);

  @Query("SELECT * FROM Filter WHERE filter_id = :filterId ORDER BY filter_id ASC")
  List<Filter> getFilterByFilterId(long filterId);

  @Query("SELECT * FROM Filter WHERE filter_id = :filterId ORDER BY query_id ASC")
  List<Filter> getFilterByQueryId(long filterId);

  @Update
  int update(Filter... filter);

  @Delete
  int delete(Filter... filter);

}
