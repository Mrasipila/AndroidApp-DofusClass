package com.example.codelab;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GameAPI {
    //@Headers("secret-key:$2b$10$eThJXHlFBoDfhudoP0VTMe6XIWYKYpL.bb1zRFMeAYEltv1u5EB8O")
    String BASE_URL = "https://raw.githubusercontent.com/Mrasipila/Lel/master/";
    @GET("classes.json")
    Call<List<ContainerJSON>> getClassesInfo();
                                           //@Query("$[?(@._id)].roles") List<String[]>roles);
}
