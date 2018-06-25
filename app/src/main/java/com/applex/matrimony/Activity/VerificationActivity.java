package com.applex.matrimony.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Interface.checkOtpInterface;
import com.applex.matrimony.Interface.resendOtpInterface;
import com.applex.matrimony.Interface.sendOtpInterface;
import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity implements View.OnClickListener {

    Button submit;
    EditText etOtp;
    TextView tvResend,tvEmail;
    ProgressDialog progressDialog;
    ImageButton ibtnEditEmail;

    SPCustProfile spCustProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        etOtp=(EditText)findViewById(R.id.edt_otp);
        submit=(Button)findViewById(R.id.btn_submit_verif);
        ibtnEditEmail=(ImageButton)findViewById(R.id.ibtn_edit_email);
        tvResend=(TextView)findViewById(R.id.tv_resend_otp);
        tvEmail=(TextView)findViewById(R.id.tv_email);


        tvResend.setOnClickListener(this);
        ibtnEditEmail.setOnClickListener(this);
        submit.setOnClickListener(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        spCustProfile=new SPCustProfile(this);

        tvEmail.setText(spCustProfile.getEmail());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_submit_verif:

                checkOtp();
              //  startActivity(new Intent(VerificationActivity.this,CongratsActivity.class));
                break;

            case R.id.ibtn_edit_email:
                tvEmail.setEnabled(true);
                spCustProfile.setEmail(tvEmail.getText().toString());
                break;

            case R.id.tv_resend_otp:

                resendOtp();
                break;
        }
    }


    public void checkOtp(){

        progressDialog.show();

        checkOtpInterface getResponse = APIClient.getClient().create(checkOtpInterface.class);
        Call<CommonParentPojo> call = getResponse.doGetListResources(spCustProfile.getMatrimonyId(),etOtp.getText().toString());
        call.enqueue(new Callback<CommonParentPojo>() {
            @Override
            public void onResponse(Call<CommonParentPojo> call, Response<CommonParentPojo> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                CommonParentPojo commonParentPojo=response.body();
                if(commonParentPojo!=null){
                    if(commonParentPojo.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response",commonParentPojo.getMsg());
                        startActivity(new Intent(VerificationActivity.this,CongratsActivity.class));
                    }
                    showToast(commonParentPojo.getMsg());
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    public void resendOtp(){

        progressDialog.show();

        resendOtpInterface getResponse = APIClient.getClient().create(resendOtpInterface.class);
        Call<CommonParentPojo> call = getResponse.doGetListResources("nehackulkarni22@gmail.com",spCustProfile.getMatrimonyId(),spCustProfile.getMobile());
        call.enqueue(new Callback<CommonParentPojo>() {
            @Override
            public void onResponse(Call<CommonParentPojo> call, Response<CommonParentPojo> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                CommonParentPojo commonParentPojo=response.body();
                if(commonParentPojo!=null){
                    if(commonParentPojo.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response",commonParentPojo.getMsg());

                    }
                    progressDialog.dismiss();
                    showToast(commonParentPojo.getMsg());
                }

            }

            @Override
            public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    public void showToast(String msg)
    {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

}
