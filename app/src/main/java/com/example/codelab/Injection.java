package com.example.codelab;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injection {

    private static Gson GsInstance;
    private static GameAPI my_api_Instance;
    private static SharedPreferences SP_Instance;

    public static Gson getGs(){
        if(GsInstance == null){
            GsInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return GsInstance;
    }

    public static GameAPI My_Api(){
        if(my_api_Instance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GameAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGs()))
                    .build();

            my_api_Instance = retrofit.create(GameAPI.class);
        }
        return my_api_Instance;
    }

    public static SharedPreferences getSP(Context context){
        if(SP_Instance == null){
            SP_Instance = context.getSharedPreferences("mon_stack", Context.MODE_PRIVATE);
        }
        return SP_Instance;
    }
}
