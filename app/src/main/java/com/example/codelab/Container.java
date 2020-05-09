// UNUSED

package com.example.codelab;

import com.example.codelab.ContainerJSON;

import java.util.List;

class Container {

    private static List<ContainerJSON> A;

    Container(){}

    List<ContainerJSON> getListA(){
        if(A != null){
            return A;
        } else {
            System.out.println("From Class Container (1) : Already filled");
            return null;
        }
    }

    void setListA(List<ContainerJSON> from){
        if(A != null){
            System.out.println("From Class Container (2) : Already filled");
        } else {
            A = from;
        }
    }

    void showList(){
        if(A == null) System.out.println("From Container: List not filled");

        for(ContainerJSON i : A) System.out.println(i);
    }

}
