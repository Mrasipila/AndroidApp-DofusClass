package com.example.codelab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.codelab.Constants;
import com.example.codelab.GameAPI;
import com.example.codelab.Injection;
import com.example.codelab.R;
import com.example.codelab.controller.MainController;
import com.example.codelab.model.ContainerJSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        controller = new MainController(
                this,
                Injection.getGs(),
                Injection.getSP(getApplicationContext())
        );
        controller.onStart();
    }

    public void showList(List<ContainerJSON> from) {
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter mAdapter = new MyAdapter(from, item -> controller.onItemClick(item));
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void showFailure() {
        Toast.makeText(getApplicationContext(),"API Error HEYHEY", Toast.LENGTH_SHORT).show();
    }

    public void showError() {
        // this <=> getApplicationContext()
        Toast.makeText(getApplicationContext(),"API Error No object loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);

        return true;
    }


    public void navigateToDetails(ContainerJSON item) {
        Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
        myIntent.putExtra("item",Injection.getGs().toJson(item));
        MainActivity.this.startActivity(myIntent);
    }
}
