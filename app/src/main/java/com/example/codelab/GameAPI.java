package com.example.codelab;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GameAPI {
    //@Headers("secret-key:$2b$10$eThJXHlFBoDfhudoP0VTMe6XIWYKYpL.bb1zRFMeAYEltv1u5EB8O")
    @GET("classes.json")
    Call<List<Classes>> getClassesInfo();
                                           //@Query("$[?(@._id)].roles") List<String[]>roles);
}
