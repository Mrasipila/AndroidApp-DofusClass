package com.example.codelab;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameAPI {
    @GET("classes")
    Call<GetClassResponse> getClassesInfo( @Query("_id") Integer _id,
                                           @Query("name") String name,
                                           @Query("femaleImg") String femaleImg,
                                           @Query("roles") List<String>roles);
}
