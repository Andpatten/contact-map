package com.andpatten.contactmap.service;

import static com.andpatten.contactmap.BuildConfig.API_KEY;
import static com.andpatten.contactmap.BuildConfig.BASE_URL;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.andpatten.contactmap.BuildConfig;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GeoService {

  String baseUrl = BASE_URL;


  static GeoService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @GET("maps/api/geocode/")
  Single<com.andpatten.contactmap.model.entity.Query> get(@Query("address") String address,
      @Query("api_key") String api_key);

  @POST("maps/api/geocode/")
  Single<com.andpatten.contactmap.model.entity.Query> post(@Query("address") String address);


  class InstanceHolder {

    private static final GeoService INSTANCE;
    private static final String API_KEY;

    static {
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(BASE_URL)
          .client(client)
          .build();
      INSTANCE = retrofit.create(GeoService.class);
      API_KEY = BuildConfig.API_KEY;
    }

  }
}
