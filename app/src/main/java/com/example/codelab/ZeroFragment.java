package com.example.codelab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZeroFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
    //    View rootView = inflater.inflate(R.layout.fragment_zero, container, false);
     //   recyclerView = rootView.findViewById(R.id.my_recycler_view);
       // List<Classes> gameCLasses = null;
        makeApiCall();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zero, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ZeroFragment.this).navigate(R.id.action_ZeroFragment_to_FirstFragment);
            }
        });
    }
    
    private void makeApiCall(){ ;
 /*       OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        OkHttpClient.Builder builder;
        builder = httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("secret-key", "$2b$10$eThJXHlFBoDfhudoP0VTMe6XIWYKYpL.bb1zRFMeAYEltv1u5EB8O")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        }

        OkHttpClient client = httpClient.build();*/

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Mrasipila/CodeLab/master/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GameAPI gerritAPI = retrofit.create(GameAPI.class);

        Call<List<Classes>> call = gerritAPI.getClassesInfo();
        call.enqueue(new Callback<List<Classes>>() {
            @Override                                           
            public void onResponse(Call<List<Classes>> call, Response<List<Classes>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Classes> gameCLasses = response.body();
                    for(Classes i : gameCLasses){
                        System.out.println(i.getFemaleImg());
                    }

                    Toast.makeText(getActivity(),"API Success object loaded",Toast.LENGTH_SHORT).show();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Classes>> call, Throwable t) {
                showFailure();
            }
        });

        }

    private void showFailure() {
        Toast.makeText(getActivity(),"API Error HEYHEY", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        // this <=> getApplicationContext()
        Toast.makeText(getActivity(),"API Error No object loaded", Toast.LENGTH_SHORT).show();
    }
 /*   public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter
        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }*/
}
