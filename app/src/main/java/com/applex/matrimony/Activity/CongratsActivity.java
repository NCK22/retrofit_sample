package com.applex.matrimony.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

public class CongratsActivity extends AppCompatActivity {


    TextView tvFillDetails,tvUName,tvId;
    SPCustProfile spCustProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        spCustProfile=new SPCustProfile(this);
        tvUName=(TextView)findViewById(R.id.tv_username);
        tvId=(TextView)findViewById(R.id.tv_id);

        tvUName.setText(spCustProfile.getEmail());
        tvId.setText(spCustProfile.getMatrimonyId());
        tvFillDetails=(TextView)findViewById(R.id.tv_personal_detl);
        tvFillDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //  startActivity(new Intent(CongratsActivity.this,TabParentProfileActivity.class));
                startActivity(new Intent(CongratsActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}
