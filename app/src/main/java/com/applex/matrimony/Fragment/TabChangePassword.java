package com.applex.matrimony.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Interface.changePasswordInterface;
import com.applex.matrimony.Interface.editEmailInterface;
import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Dell on 15-01-2018.
 */


public class TabChangePassword extends Fragment implements View.OnClickListener {




    EditText etCurrPwd,etNewPwd,etConfirmNewPwd;
    Button  btnChange;
    SPCustProfile spCustProfile;
    ProgressDialog progressDialog;
    boolean flagAllValid=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.tab_change_password,container,false);

        Log.e("TabHome","onCreateView");
        progressDialog=new ProgressDialog(getActivity());
        spCustProfile=new SPCustProfile(getActivity());

        etCurrPwd=(EditText)rootView.findViewById(R.id.edt_current_pwd);
        etNewPwd=(EditText)rootView.findViewById(R.id.edt_new_pwd);
        etConfirmNewPwd=(EditText)rootView.findViewById(R.id.edt_confirm_new_pwd);
        btnChange=(Button)rootView.findViewById(R.id.btn_change);
        btnChange.setOnClickListener(this);


        return rootView;

    }






    @Override
    public void onClick(View v) {

        checkValidity();
       if(flagAllValid==true)
        changePassword();
    }

public void changePassword(){

    progressDialog.show();
    changePasswordInterface getResponse = APIClient.getClient().create(changePasswordInterface.class);
    Call<CommonParentPojo> call = getResponse.doGetListResources(etCurrPwd.getText().toString(),etNewPwd.getText().toString(),etConfirmNewPwd.getText().toString());
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

        //display toast
    public void showToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    public void checkValidity(){

        if(etNewPwd.getText().toString().length()==0 || etCurrPwd.getText().toString().length()==0 || etConfirmNewPwd.getText().toString().length()==0)
            showToast("Please fill all fields");
        else if(etCurrPwd.getText().toString().length()<6)
            showToast("Please enter valid Current Password");
        else if(etNewPwd.getText().toString().length()<6)
            showToast("Please enter valid New Password");
        else if(etConfirmNewPwd.getText().toString().length()<6)
            showToast("Please enter valid Confirm Password");
        else
            flagAllValid=true;
    }
}
