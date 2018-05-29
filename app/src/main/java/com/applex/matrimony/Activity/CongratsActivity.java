package com.applex.matrimony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.applex.matrimony.R;

public class CongratsActivity extends AppCompatActivity {


    TextView tvFillDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        tvFillDetails=(TextView)findViewById(R.id.tv_personal_detl);
        tvFillDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(CongratsActivity.this,DoneActivity.class));
            }
        });
    }
}
