package com.andpatten.contactmap.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.andpatten.contactmap.model.dao.FilterDao;
import com.andpatten.contactmap.model.dao.QueryDao;
import com.andpatten.contactmap.model.dao.SortDao;
import com.andpatten.contactmap.model.entity.Filter;
import com.andpatten.contactmap.model.entity.Query;
import com.andpatten.contactmap.model.entity.Sort;

@Database(
    entities = {Filter.class, Query.class, Sort.class},
    version = 1, exportSchema = true
)

  public abstract class ContactMapDatabase extends RoomDatabase {

    protected ContactMapDatabase() {}

    private static Application applicationContext;

    public static void setApplicationContext(Application applicationContext) {
      ContactMapDatabase.applicationContext = applicationContext;
    }

    public static ContactMapDatabase getInstance() {
      return InstanceHolder.INSTANCE;
    }

    public abstract QueryDao getQueryDao();

    public abstract FilterDao getFilterDao();

    public abstract SortDao getSortDao();

    private static class InstanceHolder {

      private static final ContactMapDatabase INSTANCE;

      static {
        INSTANCE =
            Room.databaseBuilder(applicationContext, ContactMapDatabase.class, "contact_map_db").build();
      }

    }

  }
