package com.applex.matrimony.Activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Interface.custRegInterface;
import com.applex.matrimony.Interface.getCasteInterface;
import com.applex.matrimony.Interface.getCityInterface;
import com.applex.matrimony.Interface.getMTongueInterface;
import com.applex.matrimony.Interface.getReligionInterface;
import com.applex.matrimony.Pojo.ChildModelCountry;
import com.applex.matrimony.Pojo.ChildPojoCaste;
import com.applex.matrimony.Pojo.ChildPojoCity;
import com.applex.matrimony.Pojo.ChildPojoCustReg;
import com.applex.matrimony.Pojo.ChildPojoMTongue;
import com.applex.matrimony.Pojo.ChildPojoReligion;
import com.applex.matrimony.Pojo.ChildPojoState;
import com.applex.matrimony.Pojo.ParentPojoCaste;
import com.applex.matrimony.Pojo.ParentPojoCity;
import com.applex.matrimony.Pojo.ParentPojoCustReg;
import com.applex.matrimony.Pojo.ParentPojoMTongue;
import com.applex.matrimony.Pojo.ParentPojoReligion;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import fr.ganfra.materialspinner.MaterialSpinner;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    LinearLayout ll_parent;
    DatePickerEditText dpBDate;
    Spinner spProfFor,spCaste,spReligion,spMTongue,spGender;
    EditText etName,etCountryCode,etMobile,etEmail,etPwd;
    Button btnSubmit;
    ArrayList<String> list_prof_for=new ArrayList<String>();
    ArrayList<String> list_caste=new ArrayList<String>();
    ArrayList<ChildPojoReligion>list_pojo_religion=new ArrayList<ChildPojoReligion>();
    ArrayList<ChildPojoCaste>list_pojo_caste=new ArrayList<ChildPojoCaste>();
    ArrayList<ChildPojoMTongue>list_pojo_mtongue=new ArrayList<ChildPojoMTongue>();
    ArrayList<String> list_religion=new ArrayList<String>();
    ArrayList<String> list_mtongue=new ArrayList<String>();
    ArrayList<String>list_gender=new ArrayList<String>();
    ArrayList<String> list_country=new ArrayList<String>();

    ArrayAdapter aaCaste,aaMTongue;
    ProgressDialog progressDialog;

    SPCustProfile spCustProfile;

    Boolean flagAllValid=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        dpBDate = (DatePickerEditText) findViewById(R.id.edt_bdate);
        dpBDate.setManager(getSupportFragmentManager());

        etName=(EditText)findViewById(R.id.edt_name);
        etCountryCode=(EditText)findViewById(R.id.edt_code);
        etMobile=(EditText)findViewById(R.id.edt_mob);
        etEmail=(EditText)findViewById(R.id.edt_email);
        etPwd=(EditText)findViewById(R.id.edt_pwd);

        spProfFor=(MaterialSpinner)findViewById(R.id.sp_profile_for);
        spCaste=(MaterialSpinner)findViewById(R.id.sp_caste);
        spReligion=(MaterialSpinner)findViewById(R.id.sp_religion);
        spMTongue=(MaterialSpinner)findViewById(R.id.sp_mothertongue);
        spGender=(MaterialSpinner)findViewById(R.id.sp_gender);

        btnSubmit=(Button)findViewById(R.id.btn_submit);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait");

        spCustProfile=new SPCustProfile(this);

        list_prof_for.add("Profile For");
        list_prof_for.add("Son");
        list_prof_for.add("Daughter");
        list_prof_for.add("Myself");
        list_prof_for.add("Brother");
        list_prof_for.add("Sister");
        list_prof_for.add("Relative");

        list_gender.add("Gender");
        list_gender.add("Male");
        list_gender.add("Female");

        ArrayAdapter aaProfFor = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_prof_for);
        aaProfFor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter aaGender = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_gender);
        aaGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spProfFor.setAdapter(aaProfFor);
        spGender.setAdapter(aaGender);

        spProfFor.setOnItemSelectedListener(this);
        spCaste.setOnItemSelectedListener(this);
        spReligion.setOnItemSelectedListener(this);
        spMTongue.setOnItemSelectedListener(this);
        spGender.setOnItemSelectedListener(this);

        getReligionList();
        getMTongueList();

      btnSubmit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             // registerCustomer();
            startActivity(new Intent(RegisterActivity.this,DetailsActivity.class).putExtra("tabFlag","home"));

          }
      });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      /*  Typeface externalFont=Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        ((TextView) view).setTypeface(externalFont);*/
        ((TextView)view).setTextColor(Color.WHITE);
        ((TextView)view).setTextSize(16);

        switch (adapterView.getId()){
            case R.id.sp_religion:

                Log.e("onIemSelectedListener ","religion called");
                Log.e("position clicked",""+spReligion.getSelectedItemPosition());
                if(spReligion.getSelectedItemPosition() != -1)
                {
                   /* Log.e("selected index",""+list_country.indexOf(spCountry.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_name());
                    Log.e("pojo element id",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_id());
                    Log.e("pojo element sortname",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_currency());
                   // Log.e("pojo element phone",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_extension());*/
                    getCasteList(list_pojo_religion.get(list_religion.indexOf(spReligion.getSelectedItem())+1).getReligion_id());

                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void getReligionList(){

        progressDialog.show();
        if(list_religion!=null)
            list_religion.clear();
        if(list_pojo_religion!=null)
            list_pojo_religion.clear();

        getReligionInterface getResponse = APIClient.getClient().create(getReligionInterface.class);
        Call<ParentPojoReligion> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoReligion>() {
            @Override
            public void onResponse(Call<ParentPojoReligion> call, Response<ParentPojoReligion> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoReligion parentPojoReligion=response.body();
                if(parentPojoReligion!=null){
                    if(parentPojoReligion.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoReligion.getObjReligion().size());

                        LinkedHashMap<String, ChildPojoReligion> resultMap =parentPojoReligion.getObjReligion();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();
                            list_pojo_religion.add(resultMap.get(key));
                            list_religion.add(resultMap.get(key).getReligion_name());
                        }
                        Log.e("List Size",""+list_religion.size());
                        ArrayAdapter aaReligion = new ArrayAdapter(RegisterActivity.this,android.R.layout.simple_spinner_item,list_religion);
                        aaReligion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spReligion.setAdapter(aaReligion);
                        progressDialog.dismiss();
                    }
                }

            }

            @Override
            public void onFailure(Call<ParentPojoReligion> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    public void getCasteList(String religion_id){

        progressDialog.show();
        if(list_caste!=null)
            list_caste.clear();

        Log.e("Religion",religion_id);
        getCasteInterface getResponse = APIClient.getClient().create(getCasteInterface.class);
        Call<ParentPojoCaste> call = getResponse.doGetListResources(religion_id);
        call.enqueue(new Callback<ParentPojoCaste>() {
            @Override
            public void onResponse(Call<ParentPojoCaste> call, Response<ParentPojoCaste> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoCaste parentPojoCaste=response.body();
                if(parentPojoCaste!=null){
                    if(parentPojoCaste.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoCaste.getObjCaste().size());

                        LinkedHashMap<String, ChildPojoCaste> resultMap =parentPojoCaste.getObjCaste();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();

                            list_pojo_caste.add(resultMap.get(key));
                            list_caste.add(resultMap.get(key).getCaste_name());
                        }
                        Log.e("List Size",""+list_caste.size());
                        aaCaste = new ArrayAdapter(RegisterActivity.this,android.R.layout.simple_spinner_item,list_caste);
                        aaCaste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCaste.setAdapter(aaCaste);
                        progressDialog.dismiss();
                    }
                }

            }

            @Override
            public void onFailure(Call<ParentPojoCaste> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    public void getMTongueList()
    {
        progressDialog.show();
        if(list_mtongue!=null)
            list_mtongue.clear();


        getMTongueInterface getResponse = APIClient.getClient().create(getMTongueInterface.class);
        Call<ParentPojoMTongue> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoMTongue>() {
            @Override
            public void onResponse(Call<ParentPojoMTongue> call, Response<ParentPojoMTongue> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoMTongue parentPojoMTongue=response.body();
                if(parentPojoMTongue!=null){
                    if(parentPojoMTongue.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoMTongue.getObjMTongue().size());

                        LinkedHashMap<String, ChildPojoMTongue> resultMap =parentPojoMTongue.getObjMTongue();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();

                            list_pojo_mtongue.add(resultMap.get(key));
                            list_mtongue.add(resultMap.get(key).getMother_tongue_name());
                        }
                        Log.e("List Size",""+list_mtongue.size());
                        aaMTongue = new ArrayAdapter(RegisterActivity.this,android.R.layout.simple_spinner_item,list_mtongue);
                        aaMTongue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMTongue.setAdapter(aaMTongue);
                        progressDialog.dismiss();
                    }
                }

            }

            @Override
            public void onFailure(Call<ParentPojoMTongue> call, Throwable t) {
                progressDialog.dismiss();
            }

        });

    }


    public void registerCustomer(){


        checkValidity();

        if(flagAllValid==true) {
            progressDialog.show();
            custRegInterface getResponse = APIClient.getClient().create(custRegInterface.class);
            Call<ParentPojoCustReg> call = getResponse.doGetListResources(spProfFor.getSelectedItem().toString(),
                    etName.getText().toString(), spGender.getSelectedItem().toString(), dpBDate.getText().toString(),
                    list_pojo_religion.get(list_religion.indexOf(spReligion.getSelectedItem().toString())).getReligion_id(),
                    list_pojo_caste.get(list_caste.indexOf(spCaste.getSelectedItem().toString())).getCaste_id(),
                    list_pojo_mtongue.get(list_mtongue.indexOf(spMTongue.getSelectedItem().toString())).getMother_tongue_id(),
                    etCountryCode.getText().toString(), etMobile.getText().toString(), etEmail.getText().toString(), etPwd.getText().toString()
            );
            call.enqueue(new Callback<ParentPojoCustReg>() {
                @Override
                public void onResponse(Call<ParentPojoCustReg> call, Response<ParentPojoCustReg> response) {

                    Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                    ParentPojoCustReg parentPojoCustReg = response.body();
                    if (parentPojoCustReg != null) {
                        if (parentPojoCustReg.getStatus().equalsIgnoreCase("1")) {
                            Log.e("Response", "Success");
                            //Log.e("objsize", ""+parentPojoCustReg.getObjProfile());

                            ChildPojoCustReg resultObj = parentPojoCustReg.getObjProfile();


                            spCustProfile.setProfile_id(resultObj.getProfile_id());
                            spCustProfile.setGender(resultObj.getGender());
                            spCustProfile.setDob(resultObj.getDob());
                            spCustProfile.setMobile(resultObj.getMobile());
                            spCustProfile.setReligion(resultObj.getReligion());
                            spCustProfile.setEmail(resultObj.getEmail());


                            Log.e("profile_id", spCustProfile.getProfile_id());
                            Log.e("gender", spCustProfile.getGender());
                            Log.e("dob", spCustProfile.getDob());
                            Log.e("phone", spCustProfile.getMobile());
                            Log.e("religion", spCustProfile.getReligion());
                            Log.e("email", spCustProfile.getEmail());

                            startActivity(new Intent(RegisterActivity.this, DetailsActivity.class));
                        }

                        showToast(parentPojoCustReg.getMsg());
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ParentPojoCustReg> call, Throwable t) {

                    Log.e("Throwabe ", "" + t);
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void showToast(String msg)
    {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public void checkValidity(){
        progressDialog.show();
        if(spProfFor.getSelectedItemPosition()==0 || spGender.getSelectedItemPosition()==0||
                spReligion.getSelectedItemPosition()==0 || spCaste.getSelectedItemPosition()==0 ||
                spMTongue.getSelectedItemPosition()==0 || etName.getText().toString().equals("")||
                dpBDate.getText().toString().equals("")|| etMobile.getText().toString().equals("")||
                etEmail.getText().toString().equals("")||etPwd.getText().toString().equals(""))
            showToast("Please fill all fields");

        else if(!etEmail.getText().toString().contains("@") || !etEmail.getText().toString().contains("."))
            showToast("Plese enter valid email id");

        else if(etPwd.getText().toString().length()<6)
            showToast("Please enter atleast 6 characters for password");
        else
            flagAllValid=true;

        progressDialog.dismiss();
    }

}
