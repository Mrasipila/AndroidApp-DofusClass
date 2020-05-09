package com.example.codelab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences SPcache;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        SPcache = getSharedPreferences("mon_stack", Context.MODE_PRIVATE);

        List<ContainerJSON> B = DataListfromCache();
       // makeApiCall();
        if(B != null){
            showList(B);
        } else {
            makeApiCall();
        }
    }

    private List<ContainerJSON> DataListfromCache() {
        String collection = SPcache.getString(Constants.KEY_SHARED_PREF_CLASSES,null);
        if(collection == null){
            return null;
        } else {
            Type ListContainer = new TypeToken<List<ContainerJSON>>() {
            }.getType();
            return gson.fromJson(collection, ListContainer);
        }
    }

    private void showList(List<ContainerJSON> from) {
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(from);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    private void saveList(List<ContainerJSON> from){
        String jsonString = gson.toJson(from);
        SPcache.edit().putString("JSON",jsonString).apply();
        Toast.makeText(getApplicationContext(),"List Saved", Toast.LENGTH_SHORT).show();
    }

    private void makeApiCall(){ ;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GameAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GameAPI gerritAPI = retrofit.create(GameAPI.class);

        Call<List<ContainerJSON>> call = gerritAPI.getClassesInfo();
        call.enqueue(new Callback<List<ContainerJSON>>() {
            @Override
            public void onResponse(Call<List<ContainerJSON>> call, Response<List<ContainerJSON>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<ContainerJSON> A = response.body();
                    saveList(A);
                    showList(A);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<ContainerJSON>> call, Throwable t) {
                showFailure();
            }
        });

    }

    private void showFailure() {
        Toast.makeText(getApplicationContext(),"API Error HEYHEY", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        // this <=> getApplicationContext()
        Toast.makeText(getApplicationContext(),"API Error No object loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
