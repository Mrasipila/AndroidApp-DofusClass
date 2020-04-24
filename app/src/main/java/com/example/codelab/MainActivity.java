package com.example.codelab;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
        makeApiCall();
    }

    private void showList() {
        //View rootView = inflater.inflate(R.layout.fragment_zero, container, false);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter
        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
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



    private void makeApiCall(){
            Integer _id = null;
            String name = null;
            String femaleImg = null;
            List<String> roles = null;
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fr.dofus.dofapi.fr/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            GameAPI gerritAPI = retrofit.create(GameAPI.class);

            Call<GetClassResponse> call = gerritAPI.getClassesInfo(_id,name,femaleImg,roles);
            call.enqueue(new Callback<GetClassResponse>() {
                @Override
                public void onResponse(Call<GetClassResponse> call, Response<GetClassResponse> response) {
                    if(response.isSuccessful() && response.body() != null){
                        List<Class> GameCLasses = response.body().getClasses();
                        Toast.makeText(getApplicationContext(),"API Success object loaded",Toast.LENGTH_SHORT).show();
                    } else {
                        showError();
                    }
                }

                @Override
                public void onFailure(Call<GetClassResponse> call, Throwable t) {

                }
            });
    }

    private void showError() {
        // this <=> getApplicationContext()
        Toast.makeText(getApplicationContext(),"API Error No object loaded", Toast.LENGTH_SHORT).show();
    }
}
