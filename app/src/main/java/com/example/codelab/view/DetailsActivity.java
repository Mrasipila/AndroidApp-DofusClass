package com.example.codelab.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.codelab.Injection;
import com.example.codelab.R;
import com.example.codelab.model.ContainerJSON;

public class DetailsActivity extends AppCompatActivity {

    private TextView txtDetails;
    private ImageView imgDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_zero);
        //    Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
         //   setSupportActionBar(myToolbar);

        txtDetails = findViewById(R.id.details_txt);
        imgDetails = findViewById(R.id.DImage_txt);
        Intent intent = getIntent();
        String container_json = intent.getStringExtra("item");
        ContainerJSON container = Injection.getGs().fromJson(container_json, ContainerJSON.class);
        showDetails(container);
    }

    private void showDetails(ContainerJSON container) {
        txtDetails.setVisibility(View.VISIBLE);
        imgDetails.setVisibility(View.VISIBLE);
        txtDetails.setText(container.getDescription());
        final ImageView myImageView;
        Glide.with(getApplicationContext())
                .load(container.getFemaleImg())
                .centerCrop()
                .apply(new RequestOptions().override(500, 500))
                .into(imgDetails);
    }

}
