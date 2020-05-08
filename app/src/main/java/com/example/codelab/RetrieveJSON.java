package com.example.codelab;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrieveJSON {

    private void makeApiCall(){ ;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

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
                    new Container().A = response.body();
                    Toast.makeText(new ZeroFragment().getZeroFragActivity(),"API Success object loaded",Toast.LENGTH_SHORT).show();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<ContainerJSON>> call, Throwable t) {
                showFailure();
            }
        });
    }

    private void showFailure() {
        Toast.makeText(new ZeroFragment().getZeroFragActivity(),"API Error HEYHEY", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        // this <=> getApplicationContext()
        Toast.makeText(new ZeroFragment().getZeroFragActivity(),"API Error No object loaded", Toast.LENGTH_SHORT).show();
    }
}
