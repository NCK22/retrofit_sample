package com.applex.matrimony.Fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.applex.matrimony.Activity.DetailsActivity;
import com.applex.matrimony.Activity.RegisterActivity;
import com.applex.matrimony.Adapter.HomeProfilesAdapter;
import com.applex.matrimony.Interface.APIInterface;
import com.applex.matrimony.Interface.getCasteInterface;
import com.applex.matrimony.Interface.getCityInterface;
import com.applex.matrimony.Interface.getEducationInterface;
import com.applex.matrimony.Interface.getGotraInterface;
import com.applex.matrimony.Interface.getHeightInterface;
import com.applex.matrimony.Interface.getHighlightedInterface;
import com.applex.matrimony.Interface.getMTongueInterface;
import com.applex.matrimony.Interface.getOccupationInterface;
import com.applex.matrimony.Interface.getRaasiInterface;
import com.applex.matrimony.Interface.getReligionInterface;
import com.applex.matrimony.Interface.getStarInterface;
import com.applex.matrimony.Interface.getStateInterface;
import com.applex.matrimony.Interface.getWeightInterface;
import com.applex.matrimony.Pojo.ChildModelCountry;
import com.applex.matrimony.Pojo.ChildPojoCaste;
import com.applex.matrimony.Pojo.ChildPojoCity;
import com.applex.matrimony.Pojo.ChildPojoEducation;
import com.applex.matrimony.Pojo.ChildPojoGotra;
import com.applex.matrimony.Pojo.ChildPojoHeight;
import com.applex.matrimony.Pojo.ChildPojoMTongue;
import com.applex.matrimony.Pojo.ChildPojoOccupation;
import com.applex.matrimony.Pojo.ChildPojoProfile;
import com.applex.matrimony.Pojo.ChildPojoRaasi;
import com.applex.matrimony.Pojo.ChildPojoReligion;
import com.applex.matrimony.Pojo.ChildPojoStar;
import com.applex.matrimony.Pojo.ChildPojoState;
import com.applex.matrimony.Pojo.ChildPojoWeight;
import com.applex.matrimony.Pojo.ParentModelCountry;
import com.applex.matrimony.Pojo.ParentPojoCaste;
import com.applex.matrimony.Pojo.ParentPojoCity;
import com.applex.matrimony.Pojo.ParentPojoEducation;
import com.applex.matrimony.Pojo.ParentPojoGotra;
import com.applex.matrimony.Pojo.ParentPojoHeight;
import com.applex.matrimony.Pojo.ParentPojoMTongue;
import com.applex.matrimony.Pojo.ParentPojoOccupation;
import com.applex.matrimony.Pojo.ParentPojoProfile;
import com.applex.matrimony.Pojo.ParentPojoRaasi;
import com.applex.matrimony.Pojo.ParentPojoReligion;
import com.applex.matrimony.Pojo.ParentPojoStar;
import com.applex.matrimony.Pojo.ParentPojoState;
import com.applex.matrimony.Pojo.ParentPojoWeight;
import com.applex.matrimony.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

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


public class TabMyProfile extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    MaterialSpinner spMaritalStat,spWillIntercast,spCountry,spState,spCity,spBirthCountry,spBirthState,spBirthCity,spHeight,spWeight,spBodyType,spComplexion,
            spPhysStat,spEdu,spOccu,spEmployIn,spCurrency,spFood,spDrink,spSmoke,spFamStat,spFamType,spFamValue
    , spProfFor,spCaste,spReligion,spMTongue,spGender,spStar,spRassi,spDosh,spGotra,spResident;

    ArrayAdapter aaProfFor,aaGender,aaCaste,aaMTongue,aaMaritalStat,aaCountry,aaState,aaCity,aaHeight,aaWeight,aaBodyType,aaComplexion,
            aaPhysStat,aaEdu,aaOccu,aaEmployIn,aaCurrency,aaFood,aaDrink,aaSmoke,aaFamStat,aaFamType,aapFamValue,aaStar,aaRassi,aaDosh,aaGotra,aaResident;


    EditText etAbout,etAboutFam;
    ArrayList<String> list_prof_for=new ArrayList<String>();
    ArrayList<String> list_caste=new ArrayList<String>();
    ArrayList<ChildPojoReligion>list_pojo_religion=new ArrayList<ChildPojoReligion>();
    ArrayList<ChildPojoCaste>list_pojo_caste=new ArrayList<ChildPojoCaste>();
    ArrayList<ChildPojoMTongue>list_pojo_mtongue=new ArrayList<ChildPojoMTongue>();
    ArrayList<ChildPojoStar>list_pojo_star=new ArrayList<ChildPojoStar>();
    ArrayList<ChildPojoRaasi>list_pojo_raasi=new ArrayList<ChildPojoRaasi>();
    ArrayList<ChildPojoGotra>list_pojo_gotra=new ArrayList<ChildPojoGotra>();
    ArrayList<String> list_religion=new ArrayList<String>();
    ArrayList<String> list_mtongue=new ArrayList<String>();
    ArrayList<String> list_star=new ArrayList<String>();
    ArrayList<String> list_gotra=new ArrayList<String>();
    ArrayList<String> list_raasi=new ArrayList<String>();
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
    ArrayList<String> list_dosh=new ArrayList<String>();
    ArrayList<String> list_resident=new ArrayList<String>();
    ArrayList<String> list_food=new ArrayList<String>();
    ArrayList<String>list_drink=new ArrayList<String>();
    ArrayList<String> list_smoke=new ArrayList<String>();
    ArrayList<String> list_fam_stat=new ArrayList<String>();
    ArrayList<String>list_fam_type=new ArrayList<String>();
    ArrayList<String> list_fam_value=new ArrayList<String>();
    ArrayList<String>list_currency=new ArrayList<String>();
    JSONArray eduArray;
/*    ArrayList<String> list_religion=new ArrayList<String>();
    ArrayList<ChildPojoReligion>list_pojo_religion=new ArrayList<ChildPojoReligion>();
    ArrayList<ChildPojoCaste>list_pojo_caste=new ArrayList<ChildPojoCaste>();
    ArrayList<String> list_caste=new ArrayList<String>();*/
    ArrayList<ChildPojoProfile> list_matches=new ArrayList<ChildPojoProfile>();
    ArrayList<ChildPojoProfile> list_highlights=new ArrayList<ChildPojoProfile>();
    public RecyclerView rv_profile_matches,rv_profile_highlight;
    HomeProfilesAdapter adapter;
    private ProgressBar progressBar;
    private LinearLayout lyt_not_found;
    ProgressDialog progressDialog;
    Button  btnFind;
    EditText etAgeFrom,etAgeTo;
    Button btnBasic,btnReligion,btnProfessional,btnGroomBrideLoc,btnFam,btnAbtFam,btnAbt;
    LinearLayout llBasic,llBasicEdit,llBasicView,llReligion,llReligionEdit,llReligionView,llGroomBrideLoc,llGroomBrideLocEdit,llGroomBrideLocView,
            llProfessional,llProfessionalEdit,llProfessionalView,llFamily,llFamilyEdit,llFamilyView,llAbtFam,llAbt;

    ImageView imgEditBasic,imgClearBasic,imgEditProfessional,imgClearProfessional,imgEditReligion,imgClearReligion
    ,imgEditGroomBrideLoc,imgClearGroomBrideLoc,imgEditFam,imgClearFam,imgEditAbtFam,imgClearAbtFam,imgEditAbout,imgClearAbout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.tab_my_profile,container,false);

        Log.e("TabHome","onCreateView");
progressDialog=new ProgressDialog(getActivity());

        etAbout=(EditText)rootView.findViewById(R.id.edt_about_you);
        etAbout.setOnTouchListener(new View.OnTouchListener() {
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
        etAboutFam=(EditText)rootView.findViewById(R.id.edt_about_fam);
        etAboutFam.setOnTouchListener(new View.OnTouchListener() {
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

        spProfFor=(MaterialSpinner)rootView.findViewById(R.id.sp_profile_for);
        spCaste=(MaterialSpinner)rootView.findViewById(R.id.sp_caste);
        spReligion=(MaterialSpinner)rootView.findViewById(R.id.sp_religion);
        spMTongue=(MaterialSpinner)rootView.findViewById(R.id.sp_mothertongue);

        spMaritalStat=(MaterialSpinner)rootView.findViewById(R.id.sp_marital_stat);
        spCountry=(MaterialSpinner)rootView.findViewById(R.id.sp_country);
        spState=(MaterialSpinner)rootView.findViewById(R.id.sp_state);spCity=(MaterialSpinner)rootView.findViewById(R.id.sp_city);
        spBirthCountry=(MaterialSpinner)rootView.findViewById(R.id.sp_CountryOfBirth);
        spBirthState=(MaterialSpinner)rootView.findViewById(R.id.sp_stateOfBirth);spBirthCity=(MaterialSpinner)rootView.findViewById(R.id.sp_cityOfBirth);
        spHeight=(MaterialSpinner)rootView.findViewById(R.id.sp_height); spWeight=(MaterialSpinner)rootView.findViewById(R.id.sp_weight);
        spBodyType=(MaterialSpinner)rootView.findViewById(R.id.sp_body_type);spComplexion=(MaterialSpinner)rootView.findViewById(R.id.sp_complexion);
        spPhysStat=(MaterialSpinner)rootView.findViewById(R.id.sp_physical_stat);spEdu=(MaterialSpinner)rootView.findViewById(R.id.sp_edu);
        spOccu=(MaterialSpinner)rootView.findViewById(R.id.sp_occu);
        spCurrency=(MaterialSpinner)rootView.findViewById(R.id.sp_currency);
        spFood=(MaterialSpinner)rootView.findViewById(R.id.sp_food);spDrink=(MaterialSpinner)rootView.findViewById(R.id.sp_drink);
        spSmoke=(MaterialSpinner)rootView.findViewById(R.id.sp_smoke);spFamStat=(MaterialSpinner)rootView.findViewById(R.id.sp_fam_stat);
        spFamType=(MaterialSpinner)rootView.findViewById(R.id.sp_fam_type);spFamValue=(MaterialSpinner)rootView.findViewById(R.id.sp_fam_value);

        spStar=(MaterialSpinner)rootView.findViewById(R.id.sp_star);
        spRassi=(MaterialSpinner)rootView.findViewById(R.id.sp_rassi);
        spDosh=(MaterialSpinner)rootView.findViewById(R.id.sp_dosham);
        spGotra=(MaterialSpinner)rootView.findViewById(R.id.sp_gothra);
        spResident=(MaterialSpinner)rootView.findViewById(R.id.sp_resident_stat);

        spCountry.setOnItemSelectedListener(this);
        spState.setOnItemSelectedListener(this);
        spCity.setOnItemSelectedListener(this);
        spBirthCountry.setOnItemSelectedListener(this);
        spBirthState.setOnItemSelectedListener(this);
        spBirthCity.setOnItemSelectedListener(this);
        spReligion.setOnItemSelectedListener(this);
        spDosh.setOnItemSelectedListener(this);


        btnAbt=(Button)rootView.findViewById(R.id.btnAboutGroomBride) ;
        btnBasic=(Button)rootView.findViewById(R.id.btnBasic) ;
        btnReligion=(Button)rootView.findViewById(R.id.btnReligion) ;
        btnProfessional=(Button)rootView.findViewById(R.id.btnProfessional) ;
        btnGroomBrideLoc=(Button)rootView.findViewById(R.id.btnGroomBrideLoc) ;
        btnFam=(Button)rootView.findViewById(R.id.btnFamily) ;
        btnAbtFam=(Button)rootView.findViewById(R.id.btnAboutFamily) ;

        btnAbt.setOnClickListener(this);
        btnBasic.setOnClickListener(this);btnReligion.setOnClickListener(this);btnProfessional.setOnClickListener(this);
        btnGroomBrideLoc.setOnClickListener(this); btnFam.setOnClickListener(this); btnAbtFam.setOnClickListener(this);


        initializeSpinnerLists();
        initializeSpinnerAdapters();

        getCountryList();
        getHeightList();
        getWeightList();
        getEducationList();
        getOccupationList();
        getReligionList();
        getMTongueList();
        getStarList();
        getRaasiList();
        getGotraList();

        spProfFor.setAdapter(aaProfFor);
        spMaritalStat.setAdapter(aaMaritalStat);
        spBodyType.setAdapter(aaBodyType);spComplexion.setAdapter(aaComplexion);
        spPhysStat.setAdapter(aaPhysStat);spDosh.setAdapter(aaDosh);spResident.setAdapter(aaResident);
        spFood.setAdapter(aaFood);spDrink.setAdapter(aaDrink);spSmoke.setAdapter(aaSmoke);
        spFamStat.setAdapter(aaFamStat);spFamType.setAdapter(aaFamType);spFamValue.setAdapter(aapFamValue);


        llBasic=(LinearLayout)rootView.findViewById(R.id.ll_basic);
        llBasicEdit=(LinearLayout)rootView.findViewById(R.id.ll_basic_edit);
        llBasicView=(LinearLayout)rootView.findViewById(R.id.ll_basic_view);

        llReligion=(LinearLayout)rootView.findViewById(R.id.ll_religion);
        llReligionEdit=(LinearLayout)rootView.findViewById(R.id.ll_religion_edit);
        llReligionView=(LinearLayout)rootView.findViewById(R.id.ll_religion_view);

        llGroomBrideLoc=(LinearLayout)rootView.findViewById(R.id.ll_groomBrideLoc);
        llGroomBrideLocEdit=(LinearLayout)rootView.findViewById(R.id.ll_groomBrideLoc_edit);
        llGroomBrideLocView=(LinearLayout)rootView.findViewById(R.id.ll_groomBrideLoc_view);

        llProfessional=(LinearLayout)rootView.findViewById(R.id.ll_professional);
        llProfessionalEdit=(LinearLayout)rootView.findViewById(R.id.ll_professional_edit);
        llProfessionalView=(LinearLayout)rootView.findViewById(R.id.ll_professional_view);

        llFamily=(LinearLayout)rootView.findViewById(R.id.ll_family);
        llFamilyEdit=(LinearLayout)rootView.findViewById(R.id.ll_family_edit);
        llFamilyView=(LinearLayout)rootView.findViewById(R.id.ll_family_view);


        llAbtFam=(LinearLayout)rootView.findViewById(R.id.ll_about_family);

        llAbt=(LinearLayout)rootView.findViewById(R.id.ll_about);



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

        imgEditAbout=(ImageView)rootView.findViewById(R.id.img_editSave_about);
        imgClearAbout=(ImageView)rootView.findViewById(R.id.img_clear_about);


        imgEditBasic.setOnClickListener(this);
        imgClearBasic.setOnClickListener(this);
        imgEditReligion.setOnClickListener(this);
        imgClearReligion.setOnClickListener(this);
        imgEditProfessional.setOnClickListener(this);
        imgClearProfessional.setOnClickListener(this);
        imgEditGroomBrideLoc.setOnClickListener(this);
        imgClearGroomBrideLoc.setOnClickListener(this);
        imgEditFam.setOnClickListener(this);
        imgClearFam.setOnClickListener(this);
        imgEditAbtFam.setOnClickListener(this);
        imgClearAbtFam.setOnClickListener(this);
        imgEditAbout.setOnClickListener(this);
        imgClearAbout.setOnClickListener(this);



        return rootView;

    }


    private void initializeSpinnerLists() {

        progressDialog.show();

       // list_prof_for.add("Profile For");
        list_prof_for.add("Son");
        list_prof_for.add("Daughter");
        list_prof_for.add("Myself");
        list_prof_for.add("Brother");
        list_prof_for.add("Sister");
        list_prof_for.add("Relative");


       // list_marital_Stat.add("Marital Status");
        list_marital_Stat.add("Never Married");
        list_marital_Stat.add("Divorced");
        list_marital_Stat.add("Widowed");
        list_marital_Stat.add("Awaiting for Divorce");

      //  list_height.add("Height");
       // list_weight.add("weight");

     //   list_body_type.add("Body Type");
        list_body_type.add("Slim");
        list_body_type.add("Average");
        list_body_type.add("Athletic");
        list_body_type.add("Heavy");

     //   list_complexion.add("Complexion");
        list_complexion.add("Very Fair");
        list_complexion.add("Fair");
        list_complexion.add("Wheatish");
        list_complexion.add("Wheatish Brown");
        list_complexion.add("Dark");

     //   list_physical_stat.add("Physical Status");
        list_physical_stat.add("Normal");
        list_physical_stat.add("Physically Challenged");

      //  list_employed_in.add("Employed in");
        list_employed_in.add("Government");
        list_employed_in.add("Private");
        list_employed_in.add("Business");
        list_employed_in.add("Self Employed");

       // list_food.add("Food");
        list_food.add("Vegetarian");
        list_food.add("Non Vegetarian");
        list_food.add("Eggetarian");

     //   list_drink.add("Drinking");
        list_drink.add("No");
        list_drink.add("Drink Socially");
        list_drink.add("Yes");

       // list_smoke.add("Smoking");
        list_smoke.add("No");
        list_smoke.add("Ocassionally");
        list_smoke.add("Yes");

      //  list_fam_stat.add("Family Status");
        list_fam_stat.add("Middle Class");
        list_fam_stat.add("Upper Middle Class");
        list_fam_stat.add("Rich");
        list_fam_stat.add("Affluent");

        //list_fam_type.add("Family Type");
        list_fam_type.add("Joint");
        list_fam_type.add("Nuclear");

       // list_fam_value.add("Family Value");
        list_fam_value.add("Orthodox");
        list_fam_value.add("Traditional");
        list_fam_value.add("Moderate");
        list_fam_value.add("Liberal");

        list_dosh.add("Yes");
        list_dosh.add("No");
        list_dosh.add("Don't Know");

        list_resident.add("Permanent Resident");
        list_resident.add("Work Permit");
        list_resident.add("Student Visa");
        list_resident.add("Temporary Visa");

        progressDialog.dismiss();
    }

    public void initializeSpinnerAdapters(){

        progressDialog.show();
        aaProfFor = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_prof_for);
        aaProfFor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaPhysStat = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_physical_stat);
        aaPhysStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaMaritalStat = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_marital_Stat);
        aaMaritalStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaBodyType = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_body_type);
        aaBodyType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaComplexion = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_complexion);
        aaComplexion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
        aaFamStat = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_fam_stat);
        aaFamStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaFamType = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_fam_type);
        aaFamType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aapFamValue = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_fam_value);
        aapFamValue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaDosh = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_dosh);
        aaDosh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaResident = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_resident);
        aaResident.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        progressDialog.dismiss();

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        ((TextView)view).setTextColor(Color.BLACK);
        ((TextView)view).setTextSize(16);
        switch(adapterView.getId())
        {
            case R.id.sp_religion:
                Log.e("onIemSelectedListener ","religion called");
                Log.e("position clicked",""+spReligion.getSelectedItemPosition());
                if(spReligion.getSelectedItemPosition() >0)
                {
                   /* Log.e("selected index",""+list_country.indexOf(spCountry.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_name());
                    Log.e("pojo element id",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_id());
                    Log.e("pojo element sortname",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_currency());
                   // Log.e("pojo element phone",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_extension());*/
                    getCasteList(list_pojo_religion.get(list_religion.indexOf(spReligion.getSelectedItem())).getReligion_id());

                }
                break;

            case R.id.sp_country:
                Log.e("onIemSelectedListener ","country called");
                if(spCountry.getSelectedItemPosition()!=-1 || spBirthCountry.getSelectedItemPosition()>0)
                {
                   /* Log.e("selected index",""+list_country.indexOf(spCountry.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_name());
                    Log.e("pojo element id",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_id());
                    Log.e("pojo element sortname",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_currency());
                   // Log.e("pojo element phone",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_extension());*/
                    getStateList(list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())+1).getCountry_id());
                  //  getCurrencyList(list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())+1).getCountry_id());

                }
                break;

            case R.id.sp_state:
                Log.e("onIemSelectedListener","state called");
                if(spState.getSelectedItemPosition() != -1 || spBirthState.getSelectedItemPosition()>0)
                {
                   /* Log.e("selected index",""+list_state.indexOf(spState.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_name());
                    Log.e("pojo element id",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_id());
                    Log.e("pojo element countryid",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getCountry_id());*/
                    getCityList(list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())+1).getState_id());
                }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                imgEditBasic.setImageResource(R.mipmap.editicon2);
                imgClearBasic.setVisibility(View.INVISIBLE);
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
                imgEditReligion.setImageResource(R.mipmap.editicon2);
                imgClearReligion.setVisibility(View.INVISIBLE);
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
                imgEditGroomBrideLoc.setImageResource(R.mipmap.editicon2);
                imgClearGroomBrideLoc.setVisibility(View.INVISIBLE);
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
                imgEditProfessional.setImageResource(R.mipmap.editicon2);
                imgClearProfessional.setVisibility(View.INVISIBLE);
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
                    llFamily.setVisibility(View.GONE);
                break;

            case R.id.img_clear_family:
                llFamilyEdit.setVisibility(View.GONE);
                llFamilyView.setVisibility(View.VISIBLE);
                imgEditFam.setImageResource(R.mipmap.editicon2);
                imgClearFam.setVisibility(View.INVISIBLE);
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
               // llFamilyEdit.setVisibility(View.GONE);
                //llFamilyView.setVisibility(View.VISIBLE);
                etAboutFam.setEnabled(false);
                imgEditAbtFam.setImageResource(R.mipmap.editicon2);
                imgClearAbtFam.setVisibility(View.INVISIBLE);
                break;

            case R.id.img_editSave_aboutFam:
                if(!etAboutFam.isEnabled()) {
                    etAboutFam.setEnabled(true);
                    /*llFamilyView.setVisibility(View.INVISIBLE);
                    llFamilyEdit.setVisibility(View.VISIBLE);*/
                    imgEditAbtFam.setImageResource(R.mipmap.saveicon);
                    imgClearAbtFam.setVisibility(View.VISIBLE);
                }
                else{
                    saveBasic();
                }


            case R.id.btnAboutGroomBride:
                if(llAbt.getVisibility()==View.GONE)
                    llAbt.setVisibility(View.VISIBLE);
                else
                    llAbt.setVisibility(View.GONE);
                break;

            case R.id.img_clear_about:
                //llA.setVisibility(View.GONE);
                //llFamilyView.setVisibility(View.VISIBLE);
                etAbout.setEnabled(false);
                imgEditAbout.setImageResource(R.mipmap.editicon2);
                imgClearAbout.setVisibility(View.INVISIBLE);
                break;

            case R.id.img_editSave_about:
                if(!etAbout.isEnabled()) {
                    etAbout.setEnabled(true);
                  //  llFamilyEdit.setVisibility(View.VISIBLE);
                    imgEditAbout.setImageResource(R.mipmap.saveicon);
                    imgClearAbout.setVisibility(View.VISIBLE);
                }
                else{
                    saveBasic();
                }




        }

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
                        Log.e("cast List Size",""+list_caste.size());
                        aaCaste = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_caste);
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

    public void getStarList()
    {
        progressDialog.show();
        if(list_star!=null)
            list_star.clear();


        getStarInterface getResponse = APIClient.getClient().create(getStarInterface.class);
        Call<ParentPojoStar> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoStar>() {
            @Override
            public void onResponse(Call<ParentPojoStar> call, Response<ParentPojoStar> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoStar parentPojoStar=response.body();
                if(parentPojoStar!=null){
                    if(parentPojoStar.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("starobjsize", ""+parentPojoStar.getObjStar().size());

                        LinkedHashMap<String, ChildPojoStar> resultMap =parentPojoStar.getObjStar();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();

                            list_pojo_star.add(resultMap.get(key));
                            list_star.add(resultMap.get(key).getStar_name());
                        }
                        Log.e("star List Size",""+list_star.size());
                        aaStar = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_star);
                        aaStar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spStar.setAdapter(aaStar);
                        progressDialog.dismiss();
                    }
                }

            }

            @Override
            public void onFailure(Call<ParentPojoStar> call, Throwable t) {
                Log.e("star thrwable",""+t);
                progressDialog.dismiss();
            }

        });

    }

    public void getGotraList()
    {
        progressDialog.show();
        if(list_gotra!=null)
            list_gotra.clear();


        getGotraInterface getResponse = APIClient.getClient().create(getGotraInterface.class);
        Call<ParentPojoGotra> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoGotra>() {
            @Override
            public void onResponse(Call<ParentPojoGotra> call, Response<ParentPojoGotra> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoGotra parentPojoGotra=response.body();
                if(parentPojoGotra!=null){
                    if(parentPojoGotra.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("starobjsize", ""+parentPojoGotra.getObjGotra().size());

                        LinkedHashMap<String, ChildPojoGotra> resultMap =parentPojoGotra.getObjGotra();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();

                            list_pojo_gotra.add(resultMap.get(key));
                            list_gotra.add(resultMap.get(key).getGothra_name());
                        }
                        Log.e("star List Size",""+list_gotra.size());
                        aaGotra = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_gotra);
                        aaGotra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spGotra.setAdapter(aaGotra);
                        progressDialog.dismiss();
                    }
                }

            }

            @Override
            public void onFailure(Call<ParentPojoGotra> call, Throwable t) {
                Log.e("star thrwable",""+t);
                progressDialog.dismiss();
            }

        });

    }

    public void getRaasiList()
    {
        progressDialog.show();
        if(list_raasi!=null)
            list_raasi.clear();


        getRaasiInterface getResponse = APIClient.getClient().create(getRaasiInterface.class);
        Call<ParentPojoRaasi> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoRaasi>() {
            @Override
            public void onResponse(Call<ParentPojoRaasi> call, Response<ParentPojoRaasi> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoRaasi parentPojoRaasi=response.body();
                if(parentPojoRaasi!=null){
                    if(parentPojoRaasi.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoRaasi.getObjRaasi().size());

                        LinkedHashMap<String, ChildPojoRaasi> resultMap =parentPojoRaasi.getObjRaasi();

                        Iterator<String> keys=resultMap.keySet().iterator();
                        while (keys.hasNext()){
                            String key=keys.next();

                            list_pojo_raasi.add(resultMap.get(key));
                            list_raasi.add(resultMap.get(key).getRaasi_name());
                        }
                        Log.e("star List Size",""+list_star.size());
                        aaRassi = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_raasi);
                        aaRassi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spRassi.setAdapter(aaRassi);
                        progressDialog.dismiss();
                    }
                }

            }

            @Override
            public void onFailure(Call<ParentPojoRaasi> call, Throwable t) {
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
                        aaMTongue = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_mtongue);
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
                        aaCountry = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_country);
                        aaCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCountry.setAdapter(aaCountry);
                        spBirthCountry.setAdapter(aaCountry);
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
                        aaState = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_state);
                        aaState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spState.setAdapter(aaState);
                        spBirthState.setAdapter(aaState);
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
                        aaCity = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_city);
                        aaCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCity.setAdapter(aaCity);
                        spBirthCity.setAdapter(aaCity);

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
                        aaHeight = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_height);
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
                        aaWeight = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_weight);
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
                        aaEdu = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_edu);
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
                        aaOccu = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_occu);
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
    public void saveBasic(){}
}
