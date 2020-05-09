// UNUSED

package com.example.codelab;

import java.util.List;

public class StoreJSON {

    static List<ContainerJSON> Lcov;

    StoreJSON(){}

    private void showList(){
        if(Lcov == null) System.out.println("From StoreJSON: List not filled");

        for(ContainerJSON i : Lcov) System.out.println(i);
    }
}
