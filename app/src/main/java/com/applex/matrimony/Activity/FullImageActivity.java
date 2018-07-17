package com.applex.matrimony.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


import com.applex.matrimony.R;
import com.squareup.picasso.Picasso;

public class FullImageActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        //get intent data
        Intent i = getIntent();
        Picasso.with(this)
                .load("http://applex360.in/Deshpande-family/Matrimony-web/"+i.getStringExtra("path"))
                .into(imageView);

        //selected image id
      /*  int pos = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);
        imageView.setImageResource(imageAdapter.mThumbIds[pos]);*/
    }
}
