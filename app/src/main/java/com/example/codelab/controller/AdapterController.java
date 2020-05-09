package com.example.codelab.controller;

import android.content.SharedPreferences;

import com.example.codelab.view.MainActivity;
import com.google.gson.Gson;

public class AdapterController {


    public int Start(int holder_position,int expandedPosition){
        // Check for an expanded view, collapse if you find one
        if (expandedPosition >= 0) {
            return expandedPosition;
        } else {
            // Set the current position to "expanded"
            return holder_position;
        }
    }

}
