package com.jasonoh.jsonpullparsermoviesex;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public RetrofitHelper() {
    }

    public static Retrofit getInstance(){
        String baseUrl = "http://www.kobis.or.kr";
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl( baseUrl );
        builder.addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

}
