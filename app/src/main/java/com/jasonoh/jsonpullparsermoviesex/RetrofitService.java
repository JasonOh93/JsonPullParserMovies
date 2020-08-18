package com.jasonoh.jsonpullparsermoviesex;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitService {

//    @GET("/searchDailyBoxOfficeList.json?key={keyName}&targetDt={nowDate}")
//    Call<ArrayList<Member>> loadDataFromKobis(@Query("keyName") String keyName, @Query("nowDate") String nowDate);

//    @GET("/searchDailyBoxOfficeList.json?key=a6674e6a6b1460e7db396feb9fe986cd&targetDt=20200622")
//    Call<ArrayList<Member>> loadDataFromKobis();

//    @GET
//    Call<ArrayList<Member>> loadDataFromKobis(@Url String url);

//    @GET
//    Call<Map<String, Object>> loadDataFromKobis(@Url String url);

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    Call<Map<String, Object>> loadDataFromKobis(@Query("key") String key, @Query("targetDt") String targetDt);

}
