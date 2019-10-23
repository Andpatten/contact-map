package com.andpatten.contactmap.controller;

import android.app.SearchManager;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.andpatten.contactmap.R;

public class SearchableActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    // Get the intent, verify the action and get the query
    Intent intent = getIntent();
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      doMySearch(query);
    }
  }

  @Override
  public boolean onSearchRequested() {
    Bundle appData = new Bundle();
    appData.putBoolean(SearchableActivity.JARGON, true);
    startSearch(null, false, appData, false);
    return true;
  }

//  Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);
//    if(appData !=null)
//
//  {
//    boolean jargon = appData.getBoolean(SearchableActivity.JARGON);
//  }
}
