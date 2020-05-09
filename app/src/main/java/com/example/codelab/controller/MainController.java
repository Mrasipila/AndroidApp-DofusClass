package com.example.codelab.controller;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.codelab.Constants;
import com.example.codelab.GameAPI;
import com.example.codelab.model.ContainerJSON;
import com.example.codelab.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {


    private SharedPreferences SP_cache;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainactivity, Gson gson, SharedPreferences sharedpreferences){
        this.view = mainactivity;
        this.gson = gson;
        this.SP_cache = sharedpreferences;
    }

    public void onStart(){
        List<ContainerJSON> B = DataListfromCache();
        if(B != null) {
            view.showList(B);
        } else {
            makeApiCall();
        }
    }

    private List<ContainerJSON> DataListfromCache() {
        String collection = SP_cache.getString(Constants.KEY_SHARED_PREF_CLASSES,null);
        if(collection == null){
            return null;
        } else {
            Type ListContainer = new TypeToken<List<ContainerJSON>>() {
            }.getType();
            return gson.fromJson(collection, ListContainer);
        }
    }

    private void makeApiCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GameAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GameAPI gerritAPI = retrofit.create(GameAPI.class);

        Call<List<ContainerJSON>> call = gerritAPI.getClassesInfo();
        call.enqueue(new Callback<List<ContainerJSON>>() {
            @Override
            public void onResponse(Call<List<ContainerJSON>> call, Response<List<ContainerJSON>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<ContainerJSON> A = response.body();
                    saveList(A);
                    view.showList(A);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<List<ContainerJSON>> call, Throwable t) {
                view.showFailure();
            }
        });

    }

    private void saveList(List<ContainerJSON> from){
        String jsonString = gson.toJson(from);
        SP_cache.edit().putString("JSON",jsonString).apply();
        Toast.makeText(view.getApplicationContext(),"List Saved", Toast.LENGTH_SHORT).show();
    }

    public void onItemClick(){

    }

    public void onButtonClick(){

    }

}
