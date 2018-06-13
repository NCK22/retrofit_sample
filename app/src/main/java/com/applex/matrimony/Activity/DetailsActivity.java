package com.applex.matrimony.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Interface.APIInterface;
import com.applex.matrimony.Interface.checkOtpInterface;
import com.applex.matrimony.Interface.getCityInterface;
import com.applex.matrimony.Interface.getCurrencyInterface;
import com.applex.matrimony.Interface.getEducationInterface;
import com.applex.matrimony.Interface.getHeightInterface;
import com.applex.matrimony.Interface.getOccupationInterface;
import com.applex.matrimony.Interface.getStateInterface;
import com.applex.matrimony.Interface.getWeightInterface;
import com.applex.matrimony.Interface.regDetlInterface;
import com.applex.matrimony.Interface.sendOtpInterface;
import com.applex.matrimony.Pojo.ChildModelCountry;
import com.applex.matrimony.Pojo.ChildPojoCity;
import com.applex.matrimony.Pojo.ChildPojoCurrency;
import com.applex.matrimony.Pojo.ChildPojoEducation;
import com.applex.matrimony.Pojo.ChildPojoHeight;
import com.applex.matrimony.Pojo.ChildPojoOccupation;
import com.applex.matrimony.Pojo.ChildPojoState;
import com.applex.matrimony.Pojo.ChildPojoWeight;
import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentModelCountry;
import com.applex.matrimony.Pojo.ParentPojoCity;
import com.applex.matrimony.Pojo.ParentPojoCurrency;
import com.applex.matrimony.Pojo.ParentPojoEducation;
import com.applex.matrimony.Pojo.ParentPojoHeight;
import com.applex.matrimony.Pojo.ParentPojoOccupation;
import com.applex.matrimony.Pojo.ParentPojoState;
import com.applex.matrimony.Pojo.ParentPojoWeight;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    MaterialSpinner spMaritalStat,spWillIntercast,spCountry,spState,spCity,spHeight,spWeight,spBodyType,spComplexion,
    spPhysStat,spEdu,spOccu,spEmployIn,spCurrency,spFood,spDrink,spSmoke,spFamStat,spFamType,spFamValue;

    ArrayAdapter aaMaritalStat,aaWillIntercast,aaCountry,aaState,aaCity,aaHeight,aaWeight,aaBodyType,aaComplexion,
            aaPhysStat,aaEdu,aaOccu,aaEmployIn,aaCurrency,aaFood,aaDrink,aaSmoke,aaFamStat,aaFamType,aapFamValue;


    ArrayList<String> list_marital_Stat=new ArrayList<String>();
    ArrayList<String> list_will_intercast=new ArrayList<String>();
    ArrayList<String> list_country=new ArrayList<String>();
    ArrayList<ChildModelCountry>list_pojo_country=new ArrayList<ChildModelCountry>();
    ArrayList<ChildPojoState> list_pojo_state=new ArrayList<ChildPojoState>();
    ArrayList<ChildPojoCity>list_pojo_city=new ArrayList<ChildPojoCity>();
    ArrayList<String> list_state=new ArrayList<String>();
    ArrayList<ChildPojoHeight> list_pojo_height=new ArrayList<ChildPojoHeight>();
    ArrayList<String>list_city=new ArrayList<String>();
    ArrayList<String> list_height=new ArrayList<String>();
    ArrayList<ChildPojoWeight> list_pojo_weight=new ArrayList<ChildPojoWeight>();
    ArrayList<String> list_weight=new ArrayList<String>();
    ArrayList<String>list_body_type=new ArrayList<String>();
    ArrayList<String> list_complexion=new ArrayList<String>();
    ArrayList<String> list_physical_stat=new ArrayList<String>();
    ArrayList<String> list_edu=new ArrayList<String>();
    ArrayList<ChildPojoEducation> list_pojo_edu=new ArrayList<ChildPojoEducation>();
    ArrayList<String> list_occu=new ArrayList<String>();
    ArrayList<ChildPojoOccupation> list_pojo_occu=new ArrayList<ChildPojoOccupation>();
    ArrayList<String> list_employed_in=new ArrayList<String>();
    ArrayList<String>list_lakh=new ArrayList<String>();
    ArrayList<String> list_thosand=new ArrayList<String>();
    ArrayList<String> list_food=new ArrayList<String>();
    ArrayList<String>list_drink=new ArrayList<String>();
    ArrayList<String> list_smoke=new ArrayList<String>();
    ArrayList<String> list_fam_stat=new ArrayList<String>();
    ArrayList<String>list_fam_type=new ArrayList<String>();
    ArrayList<String> list_fam_value=new ArrayList<String>();
ArrayList<String>list_currency=new ArrayList<String>();
    Button submit;
    EditText etSubCaste,etAboutYou,etChildren;
    ProgressDialog progressDialog;
    LinearLayout llChildren;

    String strChildrenNo="0";
    SPCustProfile spCustProfile;

    Boolean flagAllValid=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        spMaritalStat=(MaterialSpinner)findViewById(R.id.sp_marital_stat);
        spWillIntercast=(MaterialSpinner)findViewById(R.id.sp_will_intercast);
        spCountry=(MaterialSpinner)findViewById(R.id.sp_country);
        spState=(MaterialSpinner)findViewById(R.id.sp_state);spCity=(MaterialSpinner)findViewById(R.id.sp_city);
        spHeight=(MaterialSpinner)findViewById(R.id.sp_height); spWeight=(MaterialSpinner)findViewById(R.id.sp_weight);
        spBodyType=(MaterialSpinner)findViewById(R.id.sp_body_type);spComplexion=(MaterialSpinner)findViewById(R.id.sp_complexion);
        spPhysStat=(MaterialSpinner)findViewById(R.id.sp_physical_stat);spEdu=(MaterialSpinner)findViewById(R.id.sp_edu);
        spOccu=(MaterialSpinner)findViewById(R.id.sp_occup);spEmployIn=(MaterialSpinner)findViewById(R.id.sp_employed_in);
        spCurrency=(MaterialSpinner)findViewById(R.id.sp_currency);
        spFood=(MaterialSpinner)findViewById(R.id.sp_food);spDrink=(MaterialSpinner)findViewById(R.id.sp_drink);
        spSmoke=(MaterialSpinner)findViewById(R.id.sp_smoke);spFamStat=(MaterialSpinner)findViewById(R.id.sp_fam_stat);
        spFamType=(MaterialSpinner)findViewById(R.id.sp_fam_type);spFamValue=(MaterialSpinner)findViewById(R.id.sp_fam_value);

        submit=(Button)findViewById(R.id.btn_submit_dtl);
        etSubCaste=(EditText)findViewById(R.id.edt_subCaste);
        etAboutYou=(EditText)findViewById(R.id.edt_about_you);
        etChildren=(EditText)findViewById(R.id.edt_children);

        llChildren=(LinearLayout)findViewById(R.id.ll_children);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");

        spCustProfile=new SPCustProfile(this);

        initializeSpinnerLists();

        getCountryList();
        getHeightList();
        getWeightList();
        getEducationList();
        getOccupationList();
        initializeSpinnerAdapters();


        spMaritalStat.setAdapter(aaMaritalStat);
        spWillIntercast.setAdapter(aaWillIntercast);
        spBodyType.setAdapter(aaBodyType);spComplexion.setAdapter(aaComplexion);
        spPhysStat.setAdapter(aaPhysStat);
        spEmployIn.setAdapter(aaEmployIn);
        spFood.setAdapter(aaFood);spDrink.setAdapter(aaDrink);spSmoke.setAdapter(aaSmoke);
        spFamStat.setAdapter(aaFamStat);spFamType.setAdapter(aaFamType);spFamValue.setAdapter(aapFamValue);


        spMaritalStat.setOnItemSelectedListener(this);
        spWillIntercast.setOnItemSelectedListener(this);
        spCountry.setOnItemSelectedListener(this);
        spState.setOnItemSelectedListener(this); spCity.setOnItemSelectedListener(this);  spHeight.setOnItemSelectedListener(this);
        spWeight.setOnItemSelectedListener(this);spBodyType.setOnItemSelectedListener(this);spComplexion.setOnItemSelectedListener(this);
        spPhysStat.setOnItemSelectedListener(this);spEdu.setOnItemSelectedListener(this);spOccu.setOnItemSelectedListener(this);
        spEmployIn.setOnItemSelectedListener(this);spCurrency.setOnItemSelectedListener(this);
        spFood.setOnItemSelectedListener(this);spDrink.setOnItemSelectedListener(this);spSmoke.setOnItemSelectedListener(this);
        spFamStat.setOnItemSelectedListener(this);spFamType.setOnItemSelectedListener(this);spFamValue.setOnItemSelectedListener(this);


        spMaritalStat.setSelected(true);spWillIntercast.setSelected(true);spCountry.setSelected(true);
        spState.setSelected(true);spCity.setSelected(true);spHeight.setSelected(true);spWeight.setSelected(true);spBodyType.setSelected(true);
        spComplexion.setSelected(true);spPhysStat.setSelected(true);spEdu.setSelected(true);
        spOccu.setSelected(true);spEmployIn.setSelected(true);spCurrency.setSelected(true);
        spFood.setSelected(true);spDrink.setSelected(true);spSmoke.setSelected(true);spFamStat.setSelected(true);
        spFamType.setSelected(true);spFamValue.setSelected(true);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //registerDetails();
                startActivity(new Intent(DetailsActivity.this,VerificationActivity.class));
            }
        });

        etAboutYou.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.getParent().getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                return false;
            }
        });
    }

    private void initializeSpinnerLists() {

        progressDialog.show();
        list_marital_Stat.add("Marital Status");
        list_marital_Stat.add("Never Married");
        list_marital_Stat.add("Divorced");
        list_marital_Stat.add("Widowed");
        list_marital_Stat.add("Awaiting for Divorce");
        list_will_intercast.add("Willing to marry from the other communities?");
        list_will_intercast.add("Yes");
        list_will_intercast.add("No");
        list_height.add("Height");
        list_weight.add("weight");
        list_body_type.add("Body Type");
        list_body_type.add("Slim");
        list_body_type.add("Average");
        list_body_type.add("Athletic");
        list_body_type.add("Heavy");

        list_complexion.add("Complexion");
        list_complexion.add("Very Fair");
        list_complexion.add("Fair");
        list_complexion.add("Wheatish");
        list_complexion.add("Wheatish Brown");
        list_complexion.add("Dark");

        list_physical_stat.add("Physical Status");
        list_physical_stat.add("Normal");
        list_physical_stat.add("Physically Challenged");

        list_employed_in.add("Employed in");
        list_employed_in.add("Government");
        list_employed_in.add("Private");
        list_employed_in.add("Business");
        list_employed_in.add("Self Employed");

        list_food.add("Food");
        list_food.add("Vegetarian");
        list_food.add("Non Vegetarian");
        list_food.add("Eggetarian");

        list_drink.add("Drinking");
        list_drink.add("No");
        list_drink.add("Drink Socially");
        list_drink.add("Yes");

        list_smoke.add("Smoking");
        list_smoke.add("No");
        list_smoke.add("Ocassionally");
        list_smoke.add("Yes");

        list_fam_stat.add("Family Status");
        list_fam_stat.add("Middle Class");
        list_fam_stat.add("Upper Middle Class");
        list_fam_stat.add("Rich");
        list_fam_stat.add("Affluent");

        list_fam_type.add("Family Type");
        list_fam_type.add("Joint");
        list_fam_type.add("Nuclear");

        list_fam_value.add("Family Value");
        list_fam_value.add("Orthodox");
        list_fam_value.add("Traditional");
        list_fam_value.add("Moderate");
        list_fam_value.add("Liberal");

        progressDialog.dismiss();
    }

    public void initializeSpinnerAdapters(){

        progressDialog.show();
        aaMaritalStat = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_marital_Stat);
        aaMaritalStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaWillIntercast = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_will_intercast);
        aaWillIntercast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaBodyType = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_body_type);
        aaBodyType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaComplexion = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_complexion);
        aaComplexion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaPhysStat = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_physical_stat);
        aaPhysStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaEmployIn = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_employed_in);
        aaEmployIn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaFood = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_food);
        aaFood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaDrink = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_drink);
        aaDrink.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaSmoke = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_smoke);
        aaSmoke.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaFamStat = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_fam_stat);
        aaFamStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaFamType = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_fam_type);
        aaFamType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aapFamValue = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_fam_value);
        aapFamValue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        progressDialog.dismiss();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        ((TextView)view).setTextColor(Color.WHITE);
        ((TextView)view).setTextSize(16);

        switch(adapterView.getId())
        {

            case R.id.sp_marital_stat:
                if(spMaritalStat.getSelectedItemPosition()>1)
                    llChildren.setVisibility(View.VISIBLE);
                break;

            case R.id.sp_country:
                Log.e("onIemSelectedListener ","country called");
                if(spCountry.getSelectedItemPosition()!=-1)
                {
                   /* Log.e("selected index",""+list_country.indexOf(spCountry.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_name());
                    Log.e("pojo element id",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_id());
                    Log.e("pojo element sortname",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_currency());
                   // Log.e("pojo element phone",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_extension());*/
                    getStateList(list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())+1).getCountry_id());
                    getCurrencyList(list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())+1).getCountry_id());

                }
                break;

            case R.id.sp_state:
                Log.e("onIemSelectedListener","state called");
                if(spState.getSelectedItemPosition() != -1)
                {
                   /* Log.e("selected index",""+list_state.indexOf(spState.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_name());
                    Log.e("pojo element id",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_id());
                    Log.e("pojo element countryid",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getCountry_id());*/
                    getCityList(list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())+1).getState_id());
                }


            case R.id.sp_occup:
                Log.e("onIemSelectedListener","occu called");
                Log.e("position", String.valueOf(spOccu.getSelectedItemPosition()));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void getCountryList()
    {
        progressDialog.show();
        if(list_country!=null)
            list_country.clear();
        if(list_pojo_country!=null)
            list_pojo_country.clear();
        APIInterface getResponse = APIClient.getClient().create(APIInterface.class);
        Call<ParentModelCountry> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentModelCountry>() {
            @Override
            public void onResponse(Call<ParentModelCountry> call, Response<ParentModelCountry> response) {

                Log.e("Inside","onResponse");
                Log.e("response body",response.body().status);
                ParentModelCountry parentModelCountry=response.body();
                if(parentModelCountry!=null){
                    if(parentModelCountry.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentModelCountry.getObjCountry().size());

                        LinkedHashMap<String, ChildModelCountry> resultMap = parentModelCountry.getObjCountry();
                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();
                            list_pojo_country.add(resultMap.get(key));
                            list_country.add(resultMap.get(key).getCountry_name());
                        }

                        //Collections.sort(list_country);
                        Log.e("List Size",""+list_country.size());
                        aaCountry = new ArrayAdapter(DetailsActivity.this,android.R.layout.simple_spinner_item,list_country);
                        aaCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCountry.setAdapter(aaCountry);
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParentModelCountry> call, Throwable t) {

                Log.e("Inside","onFailure");
                Log.e("Throwable",""+t);
                progressDialog.dismiss();
            }
        });
    }

    public void getStateList(String country_id){

        progressDialog.show();
        if(list_state!=null)
            list_state.clear();
        if(list_pojo_state!=null)
            list_pojo_state.clear();
        Log.e("Country",country_id);
        getStateInterface getResponse = APIClient.getClient().create(getStateInterface.class);
        Call<ParentPojoState> call = getResponse.doGetListResources(country_id);
        call.enqueue(new Callback<ParentPojoState>() {
            @Override
            public void onResponse(Call<ParentPojoState> call, Response<ParentPojoState> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoState parentPojoState=response.body();
                if(parentPojoState!=null){
                    if(parentPojoState.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoState.getObjState().size());

                        LinkedHashMap<String, ChildPojoState> resultMap =parentPojoState.getObjState();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();
                            list_pojo_state.add(resultMap.get(key));
                            list_state.add(resultMap.get(key).getState_name());
                        }
                        Log.e("List Size",""+list_state.size());
                        aaState = new ArrayAdapter(DetailsActivity.this,android.R.layout.simple_spinner_item,list_state);
                        aaState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spState.setAdapter(aaState);
                    }
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ParentPojoState> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    public void getCityList(String state_id){

        progressDialog.show();
        if(list_city!=null)
            list_city.clear();
        if(list_pojo_city!=null)
                list_pojo_city.clear();
        Log.e("State",state_id);
        getCityInterface getResponse = APIClient.getClient().create(getCityInterface.class);
        Call<ParentPojoCity> call = getResponse.doGetListResources(state_id);
        call.enqueue(new Callback<ParentPojoCity>() {
            @Override
            public void onResponse(Call<ParentPojoCity> call, Response<ParentPojoCity> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoCity parentPojoCity=response.body();
                if(parentPojoCity!=null){
                    if(parentPojoCity.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoCity.getObjCity().size());

                        LinkedHashMap<String, ChildPojoCity> resultMap =parentPojoCity.getObjCity();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();
                            list_pojo_city.add(resultMap.get(key));
                            list_city.add(resultMap.get(key).getCity_name());
                        }
                        Log.e("List Size",""+list_city.size());
                        aaCity = new ArrayAdapter(DetailsActivity.this,android.R.layout.simple_spinner_item,list_city);
                        aaCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCity.setAdapter(aaCity);

                    }
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ParentPojoCity> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }
    public void getHeightList(){

        progressDialog.show();
        if(list_height!=null)
            list_height.clear();
        if(list_pojo_height!=null)
            list_pojo_height.clear();

        getHeightInterface getResponse = APIClient.getClient().create(getHeightInterface.class);
        Call<ParentPojoHeight> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoHeight>() {
            @Override
            public void onResponse(Call<ParentPojoHeight> call, Response<ParentPojoHeight> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoHeight parentPojoHeight=response.body();
                if(parentPojoHeight!=null){
                    if(parentPojoHeight.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoHeight.getObjHeight().size());

                        LinkedHashMap<String, ChildPojoHeight> resultMap =parentPojoHeight.getObjHeight();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();
                            list_pojo_height.add(resultMap.get(key));
                            list_height.add(resultMap.get(key).getHeight());
                        }
                        Log.e("List Size",""+list_height.size());
                        aaHeight = new ArrayAdapter(DetailsActivity.this,android.R.layout.simple_spinner_item,list_height);
                        aaHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spHeight.setAdapter(aaHeight);
                    }
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ParentPojoHeight> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    public void getWeightList(){

        progressDialog.show();
        if(list_weight!=null)
            list_weight.clear();
        if(list_pojo_weight!=null)
            list_pojo_weight.clear();

        getWeightInterface getResponse = APIClient.getClient().create(getWeightInterface.class);
        Call<ParentPojoWeight> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoWeight>() {
            @Override
            public void onResponse(Call<ParentPojoWeight> call, Response<ParentPojoWeight> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoWeight parentPojoWeight=response.body();
                if(parentPojoWeight!=null){
                    if(parentPojoWeight.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoWeight.getObjWeight().size());

                        LinkedHashMap<String, ChildPojoWeight> resultMap =parentPojoWeight.getObjWeight();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();
                            list_pojo_weight.add(resultMap.get(key));
                            list_weight.add(resultMap.get(key).getWeight());
                        }
                        Log.e("List Size",""+list_weight.size());
                        aaWeight = new ArrayAdapter(DetailsActivity.this,android.R.layout.simple_spinner_item,list_weight);
                        aaWeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spWeight.setAdapter(aaWeight);
                    }
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ParentPojoWeight> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }
    public void getEducationList(){

        progressDialog.show();
        if(list_edu!=null)
            list_edu.clear();
        if(list_pojo_edu!=null)
            list_pojo_edu.clear();

        getEducationInterface getResponse = APIClient.getClient().create(getEducationInterface.class);
        Call<ParentPojoEducation> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoEducation>() {
            @Override
            public void onResponse(Call<ParentPojoEducation> call, Response<ParentPojoEducation> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoEducation parentPojoEducation=response.body();
                if(parentPojoEducation!=null){
                    if(parentPojoEducation.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoEducation.getObjEducation().size());

                        LinkedHashMap<String, ChildPojoEducation> resultMap =parentPojoEducation.getObjEducation();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();
                            list_pojo_edu.add(resultMap.get(key));
                            list_edu.add(resultMap.get(key).getEducation());
                        }
                        Log.e("List Size",""+list_edu.size());
                        aaEdu = new ArrayAdapter(DetailsActivity.this,android.R.layout.simple_spinner_item,list_edu);
                        aaEdu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spEdu.setAdapter(aaEdu);
                    }
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ParentPojoEducation> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }
    public void getOccupationList(){

        progressDialog.show();
        if(list_occu!=null)
            list_occu.clear();
        if(list_pojo_occu!=null)
            list_pojo_occu.clear();

        getOccupationInterface getResponse = APIClient.getClient().create(getOccupationInterface.class);
        Call<ParentPojoOccupation> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoOccupation>() {
            @Override
            public void onResponse(Call<ParentPojoOccupation> call, Response<ParentPojoOccupation> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoOccupation parentPojoOccupation=response.body();
                if(parentPojoOccupation!=null){
                    if(parentPojoOccupation.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoOccupation.getObjOccupation().size());

                        LinkedHashMap<String, ChildPojoOccupation> resultMap =parentPojoOccupation.getObjOccupation();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();
                            list_pojo_occu.add(resultMap.get(key));
                            list_occu.add(resultMap.get(key).getOccupation());
                        }
                        Log.e("List Size",""+list_occu.size());
                        aaOccu = new ArrayAdapter(DetailsActivity.this,android.R.layout.simple_spinner_item,list_occu);
                        aaOccu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spOccu.setAdapter(aaOccu);
                    }
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ParentPojoOccupation> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    public void getCurrencyList(String country_id){

        progressDialog.show();
        if(list_currency!=null)
            list_currency.clear();

        Log.e("Country",country_id);
        getCurrencyInterface getResponse = APIClient.getClient().create(getCurrencyInterface.class);
        Call<ParentPojoCurrency> call = getResponse.doGetListResources(country_id);
        call.enqueue(new Callback<ParentPojoCurrency>() {
            @Override
            public void onResponse(Call<ParentPojoCurrency> call, Response<ParentPojoCurrency> response) {

                Log.e("Inside","onResponseCurrency");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoCurrency parentPojoCurrency=response.body();
                if(parentPojoCurrency!=null){
                    if(parentPojoCurrency.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("currobjsize", ""+parentPojoCurrency.getObjCurrency().size());

                        LinkedHashMap<String, ChildPojoCurrency> resultMap =parentPojoCurrency.getObjCurrency();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();

                            list_currency.add(resultMap.get(key).getCurrency());
                        }
                        Log.e("currency List Size",""+list_currency.size());
                        aaCurrency = new ArrayAdapter(DetailsActivity.this,android.R.layout.simple_spinner_item,list_currency);
                        aaCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCurrency.setAdapter(aaCurrency);
                    }
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ParentPojoCurrency> call, Throwable t) {

                Log.e("throwable currency",""+t);
                progressDialog.dismiss();
            }
        });

    }

    public void registerDetails(){

        checkValidity();
        progressDialog.show();

        regDetlInterface getResponse = APIClient.getClient().create(regDetlInterface.class);


        Log.e("Ms", String.valueOf(spMaritalStat.getSelectedItemPosition()));
        Log.e("ic", String.valueOf(spWillIntercast.getSelectedItemPosition()));
        Log.e("sc",etSubCaste.getText().toString());
        Log.e("c",list_pojo_country.get(list_country.indexOf(spCountry.getItemAtPosition(spCountry.getSelectedItemPosition()).toString())).getCountry_id());
        Log.e("s",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem().toString())).getState_id());
        Log.e("c",list_pojo_city.get(list_city.indexOf(spCity.getSelectedItem().toString())).getCity_id());
        Log.e("c", list_pojo_city.get(list_city.indexOf(spCity.getSelectedItem().toString())).getCity_name());
        Log.e("h", list_pojo_height.get(list_height.indexOf(spHeight.getSelectedItem().toString())).getHeight_id());
        Log.e("w",list_pojo_weight.get(list_weight.indexOf(spWeight.getSelectedItem().toString())).getWeight_id());
        Log.e("bt",String.valueOf(spBodyType.getSelectedItemPosition()));
        Log.e("c", String.valueOf(spComplexion.getSelectedItemPosition()));
        Log.e("ps", String.valueOf(spPhysStat.getSelectedItemPosition()));
        Log.e("e", list_pojo_edu.get(list_edu.indexOf(spEdu.getSelectedItem().toString())).getEducation_id());
        Log.e("o",list_pojo_occu.get(list_occu.indexOf(spOccu.getSelectedItem().toString())).getOccupation_id());
        Log.e("f", String.valueOf(spFood.getSelectedItemPosition()));
        Log.e("d", String.valueOf(spDrink.getSelectedItemPosition()));
        Log.e("s", String.valueOf(spSmoke.getSelectedItemPosition()));
        Log.e("fs", String.valueOf(spFamStat.getSelectedItemPosition()));Log.e("ft", String.valueOf(spFamType.getSelectedItemPosition()));
        Log.e("fv", String.valueOf(spFamValue.getSelectedItemPosition()));
        Log.e("a",etAboutYou.getText().toString());
        Log.e("cp",spCustProfile.getProfile_id());

        if(llChildren.getVisibility()==View.VISIBLE)
            strChildrenNo=etChildren.getText().toString();
        Call<CommonParentPojo> call = getResponse.doGetListResources( String.valueOf(spMaritalStat.getSelectedItemPosition()),
                strChildrenNo,String.valueOf(spWillIntercast.getSelectedItemPosition()),
                etSubCaste.getText().toString(),
                list_pojo_country.get(list_country.indexOf(spCountry.getItemAtPosition(spCountry.getSelectedItemPosition()).toString())).getCountry_id(),
                list_pojo_state.get(list_state.indexOf(spState.getSelectedItem().toString())).getState_id(),
                list_pojo_city.get(list_city.indexOf(spCity.getSelectedItem().toString())).getCity_id(),
                list_pojo_city.get(list_city.indexOf(spCity.getSelectedItem().toString())).getCity_name(),
                "",",","","","",
                list_pojo_height.get(list_height.indexOf(spHeight.getSelectedItem().toString())).getHeight_id(),
                list_pojo_weight.get(list_weight.indexOf(spWeight.getSelectedItem().toString())).getWeight_id(),
                String.valueOf(spBodyType.getSelectedItemPosition()),String.valueOf(spComplexion.getSelectedItemPosition()),
                String.valueOf(spPhysStat.getSelectedItemPosition()),
                list_pojo_edu.get(list_edu.indexOf(spEdu.getSelectedItem().toString())).getEducation_id(),
                list_pojo_occu.get(list_occu.indexOf(spOccu.getSelectedItem().toString())).getOccupation_id(),
                "",String.valueOf(spFood.getSelectedItemPosition()),String.valueOf(spDrink.getSelectedItemPosition()),String.valueOf(spSmoke.getSelectedItemPosition()),
                "","",String.valueOf(spFamStat.getSelectedItemPosition()),String.valueOf(spFamType.getSelectedItemPosition()),
                String.valueOf(spFamValue.getSelectedItemPosition()),
                etAboutYou.getText().toString(),spCustProfile.getProfile_id()

        );
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
                        /*showToast(commonParentPojo.getMsg());
                        sendOtp();*/
                        startActivity(new Intent(DetailsActivity.this,VerificationActivity.class));

                    }
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                Log.e("Throwabe ",""+t);
                progressDialog.dismiss();
            }
        });

    }

    public void sendOtp(){

        progressDialog.show();

        sendOtpInterface getResponse = APIClient.getClient().create(sendOtpInterface.class);
        Call<CommonParentPojo> call = getResponse.doGetListResources(spCustProfile.getEmail(),"",spCustProfile.getMobile());
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


    public void checkValidity(){
        progressDialog.show();
        if(spMaritalStat.getSelectedItemPosition()==0 || spWillIntercast.getSelectedItemPosition()==0||etSubCaste.getText().toString().equals(""))
            showToast("Please fill all personal details");
             else if(spCountry.getSelectedItemPosition()==0 || spState.getSelectedItemPosition()==0 ||spCity.getSelectedItemPosition()==0 )
                    showToast("Please fil all location details");
             else if(spHeight.getSelectedItemPosition()==0 || spWeight.getSelectedItemPosition()==0 ||spBodyType.getSelectedItemPosition()==0
                ||spComplexion.getSelectedItemPosition()==0 || spPhysStat.getSelectedItemPosition()==0 )
                 showToast("Please fill all physical attributes");
             else if(spEdu.getSelectedItemPosition()==0 || spOccu.getSelectedItemPosition()==0 ||spEmployIn.getSelectedItemPosition()==0 )
                 showToast("Please fill all details of Education & occupation");
                 else if(spFood.getSelectedItemPosition()==0 || spDrink.getSelectedItemPosition()==0 ||spSmoke.getSelectedItemPosition()==0 )
                     showToast("Please fill all details of habit");
                 else if(spFamStat.getSelectedItemPosition()==0 || spFamType.getSelectedItemPosition()==0 ||spFamValue.getSelectedItemPosition()==0 )
                     showToast("Please fill all family profile details");
                 else if(etAboutYou.getText().toString().equalsIgnoreCase(""))
                     showToast("Please write something about you");
        else
            flagAllValid=true;
        progressDialog.dismiss();
    }
}
