package com.applex.matrimony;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.applex.matrimony.Activity.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUname,etPwd;
    TextView tvForgot,tvRegister;
    Button btnLogin;
    Boolean flagAllValid=false;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        etUname=(EditText)findViewById(R.id.edt_email);
        etPwd=(EditText)findViewById(R.id.edt_pwd);
        tvForgot=(TextView)findViewById(R.id.tv_forgotPwd);
        tvRegister=(TextView)findViewById(R.id.tv_register);
        btnLogin=(Button)findViewById(R.id.btn_submit);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgot.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btn_submit:
              login();
                break;

            case R.id.tv_forgotPwd:
                break;

            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }

    public void login(){

        checkValidity();
        if(flagAllValid==true){

        }
    }

    public void checkValidity()
    {
        progressDialog.show();
        if(etUname.getText().toString().equals("")|| etPwd.getText().toString().equals("")){
            showToast("Please fill all fields");
        }
        else if(etPwd.getText().toString().length()<4)
            showToast("Enter valid password");
        else
            flagAllValid=true;
        progressDialog.dismiss();
    }

    public void showToast(String msg)
    {
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
