/*This work is copyright 2019, Andrew Patten. All right reserved.
 */
package com.andpatten.contactmap.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import com.andpatten.contactmap.model.entity.Query;
import java.util.List;

@Dao
public interface QueryDao {

  @Insert
  long insert(Query query);

  @Insert
  List<Long> insert(Query... query);

//  @androidx.room.Query("SELECT * FROM `Query` WHERE query_id = :locationId ORDER BY location_id ASC")
//  LiveData<List<Location>> getQueryByLocationId(long locationId); //TODO implememt location via Geocoding(street address -> coordinates)

  @androidx.room.Query("SELECT * FROM `Query` WHERE query_id = :sortId ORDER BY query_id ASC")
  List<Query> getQueryBySortId(long sortId);

  @androidx.room.Query("SELECT * FROM `Query` WHERE query_id = :filterId ORDER BY query_id ASC")
  List<Query> getQueryByFilterId(long filterId);

  @Update
  int update(Query... query);

  @Update
  int update(Query query);

  @Delete
  int delete(Query... query);

  @Delete
  int delete(Query query);

}
