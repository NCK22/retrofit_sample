package com.applex.matrimony.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Interface.custRegInterface;
import com.applex.matrimony.Interface.loginInterface;
import com.applex.matrimony.Pojo.ChildPojoCustReg;
import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoCustReg;
import com.applex.matrimony.Pojo.ParentPojoLogin;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUname, etPwd;
    TextView tvForgot, tvRegister;
    Button btnLogin;
    Boolean flagAllValid = false;
    ProgressDialog progressDialog;
    SPCustProfile spCustProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spCustProfile = new SPCustProfile(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        etUname = (EditText) findViewById(R.id.edt_email);
        etPwd = (EditText) findViewById(R.id.edt_pwd);
        tvForgot = (TextView) findViewById(R.id.tv_forgotPwd);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        btnLogin = (Button) findViewById(R.id.btn_submit);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgot.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_submit:
                //login();
                startActivity(new Intent(LoginActivity.this, TabViewParentActivity.class));
                break;

            case R.id.tv_forgotPwd:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;

            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }


    public void checkValidity() {
        progressDialog.show();
        if (etUname.getText().toString().equals("") || etPwd.getText().toString().equals("")) {
            showToast("Please fill all fields");
        } else if (etPwd.getText().toString().length() < 4)
            showToast("Enter valid password");
        else
            flagAllValid = true;
        progressDialog.dismiss();
    }

    public void login() {


        checkValidity();

        if (flagAllValid == true) {
            progressDialog.show();
            loginInterface getResponse = APIClient.getClient().create(loginInterface.class);
            Call<ParentPojoLogin> call = getResponse.doGetListResources(etUname.getText().toString(), etPwd.getText().toString());
            call.enqueue(new Callback<ParentPojoLogin>() {
                @Override
                public void onResponse(Call<ParentPojoLogin> call, Response<ParentPojoLogin> response) {

                    Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                    ParentPojoLogin parentPojoLogin = response.body();
                    if (parentPojoLogin != null) {
                        if (parentPojoLogin.getStatus().equalsIgnoreCase("1")) {
                            Log.e("Response", parentPojoLogin.getMsg());

                            spCustProfile.setIsLogin("true");
                            spCustProfile.setMatrimonyId(parentPojoLogin.getObjProfile().get("matrimony_id"));
                            spCustProfile.setUser_id(parentPojoLogin.getObjProfile().get("user_id"));
                            spCustProfile.setProfile_id(parentPojoLogin.getObjProfile().get("profile_id"));
                            spCustProfile.setEmail(etUname.getText().toString());

                            startActivity(new Intent(LoginActivity.this, TabViewParentActivity.class));
                        }

                        showToast(parentPojoLogin.getMsg());
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ParentPojoLogin> call, Throwable t) {

                    Log.e("Throwabe ", "" + t);
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void showToast(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
