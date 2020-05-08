package com.example.codelab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContainerJSON {


    @SerializedName("_id")
    @Expose(deserialize = true)
    private Integer id;

    @SerializedName("name")
    @Expose(deserialize = true)
    private String name;

    @SerializedName("femaleImg")
    @Expose(deserialize = true)
    private String femaleImg;
   // private List<String> roles;

    public ContainerJSON(Integer a, String b, String c) {
      this.id = a;
      this.name = b;
      this.femaleImg = c;
    }

    public Integer get_id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFemaleImg() {
        return femaleImg;
    }

}
