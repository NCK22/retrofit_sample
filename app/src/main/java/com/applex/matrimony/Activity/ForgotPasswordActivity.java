package com.applex.matrimony.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Interface.forgotPasswordInterface;
import com.applex.matrimony.Interface.loginInterface;
import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUname;
    Button btnLogin;
    Boolean flagAllValid=false;
    ProgressDialog progressDialog;
    SPCustProfile spCustProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        spCustProfile=new SPCustProfile(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        etUname=(EditText)findViewById(R.id.edt_email);

        btnLogin=(Button)findViewById(R.id.btn_submit);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btn_submit:
              sendPwdToMail();
                break;


        }
    }



    public void checkValidity()
    {
        progressDialog.show();
        if(etUname.getText().toString().equals("")){
            showToast("Please fill all fields");
        }
        else
            flagAllValid=true;
        progressDialog.dismiss();
    }

    public void sendPwdToMail(){


        checkValidity();

        if(flagAllValid==true) {
            progressDialog.show();
            forgotPasswordInterface getResponse = APIClient.getClient().create(forgotPasswordInterface.class);
            Call<CommonParentPojo> call = getResponse.doGetListResources(etUname.getText().toString());
            call.enqueue(new Callback<CommonParentPojo>() {
                @Override
                public void onResponse(Call<CommonParentPojo> call, Response<CommonParentPojo> response) {

                    Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                    CommonParentPojo commonParentPojo = response.body();
                    if (commonParentPojo != null) {
                        if (commonParentPojo.getStatus().equalsIgnoreCase("1")) {
                            Log.e("Response", commonParentPojo.getMsg());

                           // spCustProfile.setIsLogin("true");
                            startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                        }

                        showToast(commonParentPojo.getMsg());
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                    Log.e("Throwabe ", "" + t);
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void showToast(String msg)
    {
        Toast.makeText(ForgotPasswordActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
