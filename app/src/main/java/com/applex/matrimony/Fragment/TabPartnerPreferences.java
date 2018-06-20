package com.applex.matrimony.Fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Adapter.HomeProfilesAdapter;
import com.applex.matrimony.Interface.getCasteInterface;
import com.applex.matrimony.Interface.getReligionInterface;
import com.applex.matrimony.Pojo.ChildModelCountry;
import com.applex.matrimony.Pojo.ChildPojoCaste;
import com.applex.matrimony.Pojo.ChildPojoCity;
import com.applex.matrimony.Pojo.ChildPojoEducation;
import com.applex.matrimony.Pojo.ChildPojoHeight;
import com.applex.matrimony.Pojo.ChildPojoMTongue;
import com.applex.matrimony.Pojo.ChildPojoOccupation;
import com.applex.matrimony.Pojo.ChildPojoProfile;
import com.applex.matrimony.Pojo.ChildPojoReligion;
import com.applex.matrimony.Pojo.ChildPojoState;
import com.applex.matrimony.Pojo.ChildPojoWeight;
import com.applex.matrimony.Pojo.ParentPojoCaste;
import com.applex.matrimony.Pojo.ParentPojoReligion;
import com.applex.matrimony.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Dell on 15-01-2018.
 */


public class TabPartnerPreferences extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    MaterialSpinner spMaritalStat,spWillIntercast,spCountry,spState,spCity,spHeight,spWeight,spBodyType,spComplexion,
            spPhysStat,spEdu,spOccu,spEmployIn,spCurrency,spFood,spDrink,spSmoke,spFamStat,spFamType,spFamValue
    , spProfFor,spCaste,spReligion,spMTongue,spGender;

    ArrayAdapter aaProfFor,aaGender,aaCaste,aaMTongue,aaMaritalStat,aaWillIntercast,aaCountry,aaState,aaCity,aaHeight,aaWeight,aaBodyType,aaComplexion,
            aaPhysStat,aaEdu,aaOccu,aaEmployIn,aaCurrency,aaFood,aaDrink,aaSmoke,aaFamStat,aaFamType,aapFamValue;


    ArrayList<String> list_prof_for=new ArrayList<String>();
    ArrayList<String> list_caste=new ArrayList<String>();
    ArrayList<ChildPojoReligion>list_pojo_religion=new ArrayList<ChildPojoReligion>();
    ArrayList<ChildPojoCaste>list_pojo_caste=new ArrayList<ChildPojoCaste>();
    ArrayList<ChildPojoMTongue>list_pojo_mtongue=new ArrayList<ChildPojoMTongue>();
    ArrayList<String> list_religion=new ArrayList<String>();
    ArrayList<String> list_mtongue=new ArrayList<String>();
    ArrayList<String>list_gender=new ArrayList<String>();
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

    ArrayList<ChildPojoProfile> list_matches=new ArrayList<ChildPojoProfile>();
    ArrayList<ChildPojoProfile> list_highlights=new ArrayList<ChildPojoProfile>();
    public RecyclerView rv_profile_matches,rv_profile_highlight;
    HomeProfilesAdapter adapter;
    private ProgressBar progressBar;
    private LinearLayout lyt_not_found;
    ProgressDialog progressDialog;
    Button  btnFind;
    EditText etAgeFrom,etAgeTo;
    Button btnBasic,btnReligion,btnProfessional,btnGroomBrideLoc,btnFam,btnAbtFam;
    LinearLayout llBasic,llBasicEdit,llBasicView,llReligion,llReligionEdit,llReligionView,llGroomBrideLoc,llGroomBrideLocEdit,llGroomBrideLocView,
            llProfessional,llProfessionalEdit,llProfessionalView,llFamily,llFamilyEdit,llFamilyView,llAbtFam;

    ImageView imgEditBasic,imgClearBasic,imgEditProfessional,imgClearProfessional,imgEditReligion,imgClearReligion
    ,imgEditGroomBrideLoc,imgClearGroomBrideLoc,imgEditFam,imgClearFam,imgEditAbtFam,imgClearAbtFam;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.tab_partner_preferences,container,false);

        Log.e("TabHome","onCreateView");
progressDialog=new ProgressDialog(getActivity());


        spProfFor=(MaterialSpinner)rootView.findViewById(R.id.sp_profile_for);
        spCaste=(MaterialSpinner)rootView.findViewById(R.id.sp_caste);
        spReligion=(MaterialSpinner)rootView.findViewById(R.id.sp_religion);
        spMTongue=(MaterialSpinner)rootView.findViewById(R.id.sp_mothertongue);

        spMaritalStat=(MaterialSpinner)rootView.findViewById(R.id.sp_marital_stat);
        spCountry=(MaterialSpinner)rootView.findViewById(R.id.sp_country);
        spState=(MaterialSpinner)rootView.findViewById(R.id.sp_state);spCity=(MaterialSpinner)rootView.findViewById(R.id.sp_city);
        spHeight=(MaterialSpinner)rootView.findViewById(R.id.sp_height); spWeight=(MaterialSpinner)rootView.findViewById(R.id.sp_weight);
        spBodyType=(MaterialSpinner)rootView.findViewById(R.id.sp_body_type);spComplexion=(MaterialSpinner)rootView.findViewById(R.id.sp_complexion);
        spPhysStat=(MaterialSpinner)rootView.findViewById(R.id.sp_physical_stat);spEdu=(MaterialSpinner)rootView.findViewById(R.id.sp_edu);
        spOccu=(MaterialSpinner)rootView.findViewById(R.id.sp_occup);
        spCurrency=(MaterialSpinner)rootView.findViewById(R.id.sp_currency);
        spFood=(MaterialSpinner)rootView.findViewById(R.id.sp_food);spDrink=(MaterialSpinner)rootView.findViewById(R.id.sp_drink);
        spSmoke=(MaterialSpinner)rootView.findViewById(R.id.sp_smoke);spFamStat=(MaterialSpinner)rootView.findViewById(R.id.sp_fam_stat);
        spFamType=(MaterialSpinner)rootView.findViewById(R.id.sp_fam_type);spFamValue=(MaterialSpinner)rootView.findViewById(R.id.sp_fam_value);

        btnBasic=(Button)rootView.findViewById(R.id.btnBasic) ;
        btnReligion=(Button)rootView.findViewById(R.id.btnReligion) ;
        btnProfessional=(Button)rootView.findViewById(R.id.btnProfessional) ;
        btnGroomBrideLoc=(Button)rootView.findViewById(R.id.btnLocDetails) ;
        btnFam=(Button)rootView.findViewById(R.id.btnFamily) ;
        btnAbtFam=(Button)rootView.findViewById(R.id.btnAboutFamily) ;

        btnBasic.setOnClickListener(this);btnReligion.setOnClickListener(this);btnProfessional.setOnClickListener(this);

        initializeSpinnerLists();
        initializeSpinnerAdapters();

        spMaritalStat.setAdapter(aaMaritalStat);
        spPhysStat.setAdapter(aaPhysStat);
        spFood.setAdapter(aaFood);spDrink.setAdapter(aaDrink);spSmoke.setAdapter(aaSmoke);



        llBasic=(LinearLayout)rootView.findViewById(R.id.ll_basic);
        llBasicEdit=(LinearLayout)rootView.findViewById(R.id.ll_basic_edit);
        llBasicView=(LinearLayout)rootView.findViewById(R.id.ll_basic_view);

        llReligion=(LinearLayout)rootView.findViewById(R.id.ll_religion);
        llReligionEdit=(LinearLayout)rootView.findViewById(R.id.ll_religion_edit);
        llReligionView=(LinearLayout)rootView.findViewById(R.id.ll_religion_view);

        llGroomBrideLoc=(LinearLayout)rootView.findViewById(R.id.ll_locDetails);
        llGroomBrideLocEdit=(LinearLayout)rootView.findViewById(R.id.ll_locDetails_edit);
        llGroomBrideLocView=(LinearLayout)rootView.findViewById(R.id.ll_locDetails_view);

        llProfessional=(LinearLayout)rootView.findViewById(R.id.ll_professional);
        llProfessionalEdit=(LinearLayout)rootView.findViewById(R.id.ll_professional_edit);
        llProfessionalView=(LinearLayout)rootView.findViewById(R.id.ll_professional_view);

        llFamily=(LinearLayout)rootView.findViewById(R.id.ll_family);
        llFamilyEdit=(LinearLayout)rootView.findViewById(R.id.ll_family_edit);
        llFamilyView=(LinearLayout)rootView.findViewById(R.id.ll_family_view);


        llAbtFam=(LinearLayout)rootView.findViewById(R.id.ll_about_family);



        imgEditBasic=(ImageView)rootView.findViewById(R.id.img_editSave_basic);
        imgClearBasic=(ImageView)rootView.findViewById(R.id.img_clear_basic);

        imgEditReligion=(ImageView)rootView.findViewById(R.id.img_editSave_religion);
        imgClearReligion=(ImageView)rootView.findViewById(R.id.img_clear_religion);

        imgEditProfessional=(ImageView)rootView.findViewById(R.id.img_editSave_professional);
        imgClearProfessional=(ImageView)rootView.findViewById(R.id.img_clear_professional);

        imgEditGroomBrideLoc=(ImageView)rootView.findViewById(R.id.img_editSave_groomBrideLoc);
        imgClearGroomBrideLoc=(ImageView)rootView.findViewById(R.id.img_clear_groomBrideLoc);

        imgEditFam=(ImageView)rootView.findViewById(R.id.img_editSave_family);
        imgClearFam=(ImageView)rootView.findViewById(R.id.img_clear_family);

        imgEditAbtFam=(ImageView)rootView.findViewById(R.id.img_editSave_aboutFam);
        imgClearAbtFam=(ImageView)rootView.findViewById(R.id.img_clear_aboutFam);


        imgEditBasic.setOnClickListener(this);
        imgClearBasic.setOnClickListener(this);
        imgEditReligion.setOnClickListener(this);
        imgClearReligion.setOnClickListener(this);
        imgEditProfessional.setOnClickListener(this);
        imgClearProfessional.setOnClickListener(this);



        return rootView;

    }


    private void initializeSpinnerLists() {

        progressDialog.show();



        list_marital_Stat.add("Marital Status");
        list_marital_Stat.add("Never Married");
        list_marital_Stat.add("Divorced");
        list_marital_Stat.add("Widowed");
        list_marital_Stat.add("Awaiting for Divorce");

        list_height.add("Height");
        list_weight.add("weight");

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



        progressDialog.dismiss();
    }

    public void initializeSpinnerAdapters(){

        progressDialog.show();

        aaPhysStat = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_physical_stat);
        aaPhysStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaMaritalStat = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_marital_Stat);
        aaMaritalStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        aaPhysStat = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_physical_stat);
        aaPhysStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaEmployIn = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_employed_in);
        aaEmployIn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaFood = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_food);
        aaFood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaDrink = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_drink);
        aaDrink.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaSmoke = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_smoke);
        aaSmoke.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        progressDialog.dismiss();

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
                        ArrayAdapter aaReligion = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_religion);
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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        ((TextView)view).setTextColor(Color.BLACK);
        ((TextView)view).setTextSize(16);
        switch(adapterView.getId())
        {
            case R.id.sp_home_religion:
                Log.e("onIemSelectedListener ","religion called");
                Log.e("position clicked",""+spReligion.getSelectedItemPosition());
                if(spReligion.getSelectedItemPosition() >0)
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
                progressDialog.dismiss();
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
                        aaCaste = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_caste);
                        aaCaste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCaste.setAdapter(aaCaste);

                    }
                }

            }

            @Override
            public void onFailure(Call<ParentPojoCaste> call, Throwable t) {

                Log.e("throwable home",""+t);
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnBasic:
                if(llBasic.getVisibility()==View.GONE)
                    llBasic.setVisibility(View.VISIBLE);
                else
                    llBasic.setVisibility(View.GONE);
                break;

            case R.id.img_clear_basic:
                llBasicEdit.setVisibility(View.GONE);
                llBasicView.setVisibility(View.VISIBLE);
                break;

            case R.id.img_editSave_basic:
                if(llBasicView.getVisibility()==View.VISIBLE) {
                llBasicView.setVisibility(View.INVISIBLE);
                llBasicEdit.setVisibility(View.VISIBLE);
                imgEditBasic.setImageResource(R.mipmap.saveicon);
                imgClearBasic.setVisibility(View.VISIBLE);
                }
                else{
                  saveBasic();
                }

            case R.id.btnReligion:
                if(llReligion.getVisibility()==View.GONE)
                    llReligion.setVisibility(View.VISIBLE);
                else
                    llReligion.setVisibility(View.GONE);
                break;

            case R.id.img_clear_religion:
                llReligionEdit.setVisibility(View.GONE);
                llReligionView.setVisibility(View.VISIBLE);
                break;

            case R.id.img_editSave_religion:
                if(llReligionView.getVisibility()==View.VISIBLE) {
                    llReligionView.setVisibility(View.INVISIBLE);
                    llReligionEdit.setVisibility(View.VISIBLE);
                    imgEditReligion.setImageResource(R.mipmap.saveicon);
                    imgClearReligion.setVisibility(View.VISIBLE);
                }
                else{
                    saveBasic();
                }
    break;


            case R.id.btnGroomBrideLoc:
                if(llGroomBrideLoc.getVisibility()==View.GONE)
                    llGroomBrideLoc.setVisibility(View.VISIBLE);
                else
                    llGroomBrideLoc.setVisibility(View.GONE);
                break;

            case R.id.img_clear_groomBrideLoc:
                llGroomBrideLocEdit.setVisibility(View.GONE);
                llGroomBrideLocView.setVisibility(View.VISIBLE);
                break;

            case R.id.img_editSave_groomBrideLoc:
                if(llGroomBrideLocView.getVisibility()==View.VISIBLE) {
                    llGroomBrideLocView.setVisibility(View.INVISIBLE);
                    llGroomBrideLocEdit.setVisibility(View.VISIBLE);
                    imgEditGroomBrideLoc.setImageResource(R.mipmap.saveicon);
                    imgClearGroomBrideLoc.setVisibility(View.VISIBLE);
                }
                else{
                    saveBasic();
                }
                break;

            case R.id.btnProfessional:
                if(llProfessional.getVisibility()==View.GONE)
                    llProfessional.setVisibility(View.VISIBLE);
                else
                    llProfessional.setVisibility(View.GONE);
                break;

            case R.id.img_clear_professional:
                llProfessionalEdit.setVisibility(View.GONE);
                llProfessionalView.setVisibility(View.VISIBLE);
                break;

            case R.id.img_editSave_professional:
                if(llProfessionalView.getVisibility()==View.VISIBLE) {
                    llProfessionalView.setVisibility(View.INVISIBLE);
                    llProfessionalEdit.setVisibility(View.VISIBLE);
                    imgEditProfessional.setImageResource(R.mipmap.saveicon);
                    imgClearProfessional.setVisibility(View.VISIBLE);
                }
                else{
                    saveBasic();
                }

            case R.id.btnFamily:
                if(llFamily.getVisibility()==View.GONE)
                    llFamily.setVisibility(View.VISIBLE);
                else
                    llProfessional.setVisibility(View.GONE);
                break;

            case R.id.img_clear_family:
                llFamilyEdit.setVisibility(View.GONE);
                llFamilyView.setVisibility(View.VISIBLE);
                break;

            case R.id.img_editSave_family:
                if(llFamilyView.getVisibility()==View.VISIBLE) {
                    llFamilyView.setVisibility(View.INVISIBLE);
                    llFamilyEdit.setVisibility(View.VISIBLE);
                    imgEditFam.setImageResource(R.mipmap.saveicon);
                    imgClearFam.setVisibility(View.VISIBLE);
                }
                else{
                    saveBasic();
                }
            case R.id.btnAboutFamily:
                if(llAbtFam.getVisibility()==View.GONE)
                    llAbtFam.setVisibility(View.VISIBLE);
                else
                    llAbtFam.setVisibility(View.GONE);
                break;

            case R.id.img_clear_aboutFam:
                llFamilyEdit.setVisibility(View.GONE);
                llFamilyView.setVisibility(View.VISIBLE);
                break;

            case R.id.img_editSave_aboutFam:
                if(llFamilyView.getVisibility()==View.VISIBLE) {
                    llFamilyView.setVisibility(View.INVISIBLE);
                    llFamilyEdit.setVisibility(View.VISIBLE);
                    imgEditFam.setImageResource(R.mipmap.saveicon);
                    imgClearFam.setVisibility(View.VISIBLE);
                }
                else{
                    saveBasic();
                }
        }

    }

    public void saveBasic(){}
}
