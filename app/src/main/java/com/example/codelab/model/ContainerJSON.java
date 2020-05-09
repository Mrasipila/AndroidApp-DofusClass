package com.example.codelab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerJSON {


    @SerializedName("roles")
 //   @Expose(deserialize = true)
    private List<String> roles;

    @SerializedName("name")
 //   @Expose(deserialize = true)
    private String name;

    @SerializedName("femaleImg")
 //   @Expose(deserialize = true)
    private String femaleImg;

    private boolean expanded = true;

    public ContainerJSON(List<String> a, String b, String c) {
      this.roles = a;
      this.name = b;
      this.femaleImg = c;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public String getFemaleImg() {
        return femaleImg;
    }

    public boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(boolean to){
        this.expanded = to;
    }

}
