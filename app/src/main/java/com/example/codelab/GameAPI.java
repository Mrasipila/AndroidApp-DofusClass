package com.example.codelab;

import com.example.codelab.model.ContainerJSON;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GameAPI {
    String BASE_URL = "https://raw.githubusercontent.com/Mrasipila/Lel/master/";
    @GET("classes.json")
    Call<List<ContainerJSON>> getClassesInfo();
}
