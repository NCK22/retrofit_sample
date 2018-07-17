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
import android.widget.Toast;

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
import com.applex.matrimony.Interface.getInterestedMyInterface;
import com.applex.matrimony.Interface.getMTongueInterface;
import com.applex.matrimony.Interface.getOccupationInterface;
import com.applex.matrimony.Interface.getPreferenceInterface;
import com.applex.matrimony.Interface.getProfileInterface;
import com.applex.matrimony.Interface.getRaasiInterface;
import com.applex.matrimony.Interface.getReligionInterface;
import com.applex.matrimony.Interface.getStarInterface;
import com.applex.matrimony.Interface.getStateInterface;
import com.applex.matrimony.Interface.getWeightInterface;
import com.applex.matrimony.Interface.savePreferencesInterface;
import com.applex.matrimony.Interface.updateAboutInterface;
import com.applex.matrimony.Interface.updateProfileInterface;
import com.applex.matrimony.Pojo.ChildModelCountry;
import com.applex.matrimony.Pojo.ChildPojoCaste;
import com.applex.matrimony.Pojo.ChildPojoCity;
import com.applex.matrimony.Pojo.ChildPojoEducation;
import com.applex.matrimony.Pojo.ChildPojoGotra;
import com.applex.matrimony.Pojo.ChildPojoHeight;
import com.applex.matrimony.Pojo.ChildPojoMTongue;
import com.applex.matrimony.Pojo.ChildPojoOccupation;
import com.applex.matrimony.Pojo.ChildPojoPartnerPref;
import com.applex.matrimony.Pojo.ChildPojoProfile;
import com.applex.matrimony.Pojo.ChildPojoRaasi;
import com.applex.matrimony.Pojo.ChildPojoReligion;
import com.applex.matrimony.Pojo.ChildPojoStar;
import com.applex.matrimony.Pojo.ChildPojoState;
import com.applex.matrimony.Pojo.ChildPojoWeight;
import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentModelCountry;
import com.applex.matrimony.Pojo.ParentPojoCaste;
import com.applex.matrimony.Pojo.ParentPojoCity;
import com.applex.matrimony.Pojo.ParentPojoEducation;
import com.applex.matrimony.Pojo.ParentPojoGotra;
import com.applex.matrimony.Pojo.ParentPojoHeight;
import com.applex.matrimony.Pojo.ParentPojoMTongue;
import com.applex.matrimony.Pojo.ParentPojoOccupation;
import com.applex.matrimony.Pojo.ParentPojoPartnerPref;
import com.applex.matrimony.Pojo.ParentPojoProfile;
import com.applex.matrimony.Pojo.ParentPojoRaasi;
import com.applex.matrimony.Pojo.ParentPojoReligion;
import com.applex.matrimony.Pojo.ParentPojoStar;
import com.applex.matrimony.Pojo.ParentPojoState;
import com.applex.matrimony.Pojo.ParentPojoWeight;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;


import org.json.JSONArray;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;

import fr.ganfra.materialspinner.MaterialSpinner;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerEditText;
import io.blackbox_vision.datetimepickeredittext.view.TimePickerEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Dell on 15-01-2018.
 */


public class TabPartnerPreferences extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    MaterialSpinner spMaritalStat,spCountry,spState,spCity,spBirthCountry,spBirthState,spBirthCity,spHeightFrom,spHeightTo,spWeight,spBodyType,spComplexion,
            spPhysStat,spEdu,spOccu,spCurrency,spFood,spDrink,spSmoke,spFamStat,spFamType,spFamValue
            , spProfFor,spCaste,spReligion,spMTongue,spStar,spRassi,spDosh,spGotra,spResident,spAgeFrom,spAgeTo;

    ArrayAdapter aaProfFor,aaAge,aaGender,aaCaste,aaMTongue,aaMaritalStat,aaCountry,aaState,aaCity,aaHeight,aaWeight,aaBodyType,aaComplexion,
            aaPhysStat,aaEdu,aaOccu,aaEmployIn,aaCurrency,aaFood,aaDrink,aaSmoke,aaFamStat,aaFamType,aapFamValue,aaStar,aaRassi,aaDosh,aaGotra,aaResident;


    EditText etAbout,etAboutFam,etName,etSubCaste,etCollege,etEduDetail,etOccuDetail,etEmployedIn,etIncome,
            etFamOrigin,etFamLoc,etFatherStat,etMotherStat,etNoOfBrothers,etNoOfSisters;
    DatePickerEditText etBDate;
    TimePickerEditText etBTime;
    ArrayList<String> list_prof_for=new ArrayList<String>();
    ArrayList<String> list_age=new ArrayList<String>();
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
    ArrayList<String> list_birth_country=new ArrayList<String>();
    ArrayList<ChildModelCountry>list_pojo_birth_country=new ArrayList<ChildModelCountry>();
    ArrayList<ChildPojoState> list_pojo_birth_state=new ArrayList<ChildPojoState>();
    ArrayList<String> list_birth_state=new ArrayList<String>();
    ArrayList<ChildPojoCity>list_pojo_birth_city=new ArrayList<ChildPojoCity>();
    ArrayList<String>list_birth_city=new ArrayList<String>();
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
    ArrayList<ChildPojoPartnerPref> mListItem=new ArrayList<ChildPojoPartnerPref>();
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

    TextView tvProfFor,tvName,tvAge,tvMaritalStat,tvBodyType,tvPhysicalStat,tvHeight,tvWeight,tvComplexion,tvMTongue,tvFood,tvDrink,tvSmoke,
            tvReligion,tvCaste,tvSubCaste,tvRaasi,tvStar,tvGotra,tvDosh,tvBirthTime,tvBirthCountry,tvBirthState,tvBirthCity,tvChartStyle,
            tvCountry,tvState,tvCity,tvResidentStatus,tvParish,tvVillage,
            tvEdu,tvInstitute,tvEduDetail,tvOccu,tvOccuDetail,tvEmployedIn,tvAnnualIncome,
            tvFamValues,tvFamType,tvFamStatus,tvFamOrigin,tvFamLoc,tvFatherStat,tvMotherStat,tvNoOfBrothers,tvNoOfSisters;

    String intentReligion="",intentCaste="",intentCountry="",intentState="",intentMaritalStat="",intentBirthCountry="",intentBirthState="",intentBirthCity="",
            intentOccu="",intentEdu="",intentMTongue="",intentPhysicalStat="",intentHeightFrom="",intentHeightTo="",intentEating="",intentDrinking="",
    intentSmoking="";
    SPCustProfile spCustProfile;
    ArrayList<LinearLayout> list_ll=new ArrayList<LinearLayout>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.tab_partner_preferences,container,false);

        Log.e("TabHome","onCreateView");
        progressDialog=new ProgressDialog(getActivity());
        spCustProfile=new SPCustProfile(getActivity());

        etName=(EditText)rootView.findViewById(R.id.edt_name);
        etAbout=(EditText)rootView.findViewById(R.id.edt_about_you);
        etCollege=(EditText)rootView.findViewById(R.id.edt_college);
        etEduDetail=(EditText)rootView.findViewById(R.id.edt_eduInDetail);
        etOccuDetail=(EditText)rootView.findViewById(R.id.edt_occuInDetail);
        etEmployedIn=(EditText)rootView.findViewById(R.id.edt_employedIn);
        etBDate = (DatePickerEditText) rootView.findViewById(R.id.edt_bdate);
        etSubCaste=(EditText)rootView.findViewById(R.id.edt_subCaste);
        etIncome=(EditText)rootView.findViewById(R.id.edt_income);
        etFamOrigin=(EditText)rootView.findViewById(R.id.edt_fam_origin);
        etFamLoc=(EditText)rootView.findViewById(R.id.edt_fam_location);
        etFatherStat=(EditText)rootView.findViewById(R.id.edt_father_stat);
        etMotherStat=(EditText)rootView.findViewById(R.id.edt_mother_stat);
        etNoOfBrothers=(EditText)rootView.findViewById(R.id.edt_noOfBrother);
        etNoOfSisters=(EditText)rootView.findViewById(R.id.edt_noOfSister);
//        etBDate.setManager(getActivity().getSupportFragmentManager());

       /* etAboutFam=(EditText)rootView.findViewById(R.id.edt_about_fam);
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
*/
        tvProfFor=(TextView)rootView.findViewById(R.id.tv_profile_for);
        tvName=(TextView)rootView.findViewById(R.id.tv_name);
        tvAge=(TextView)rootView.findViewById(R.id.tv_age);
        tvBodyType=(TextView)rootView.findViewById(R.id.tv_body_type);
        tvPhysicalStat=(TextView)rootView.findViewById(R.id.tv_physical_stat);
        tvComplexion=(TextView)rootView.findViewById(R.id.tv_complexion);
        tvMaritalStat=(TextView)rootView.findViewById(R.id.tv_marital_stat);
        tvMTongue=(TextView)rootView.findViewById(R.id.tv_mtongue);
        tvFood=(TextView)rootView.findViewById(R.id.tv_food);
        tvDrink=(TextView)rootView.findViewById(R.id.tv_drink);
        tvSmoke=(TextView)rootView.findViewById(R.id.tv_smoke);
        tvHeight=(TextView)rootView.findViewById(R.id.tv_height);
        tvWeight=(TextView)rootView.findViewById(R.id.tv_weight);

        tvReligion=(TextView)rootView.findViewById(R.id.tv_religion);
        tvCaste=(TextView)rootView.findViewById(R.id.tv_caste);
        tvSubCaste=(TextView)rootView.findViewById(R.id.tv_subCaste);
        tvRaasi=(TextView)rootView.findViewById(R.id.tv_raasi);
        tvStar=(TextView)rootView.findViewById(R.id.tv_star);
        tvGotra=(TextView)rootView.findViewById(R.id.tv_gothra);
        tvDosh=(TextView)rootView.findViewById(R.id.tv_dosham);
        tvBirthTime=(TextView)rootView.findViewById(R.id.tv_timeOfBirth);
        tvBirthCountry=(TextView)rootView.findViewById(R.id.tv_countryOfBirth);
        tvBirthState=(TextView)rootView.findViewById(R.id.tv_stateOfBirth);
        tvBirthCity=(TextView)rootView.findViewById(R.id.tv_cityOfBirth);
        tvChartStyle=(TextView)rootView.findViewById(R.id.tv_chartStyle);

        tvCountry=(TextView)rootView.findViewById(R.id.tv_country);
        tvState=(TextView)rootView.findViewById(R.id.tv_state);
        tvCity=(TextView)rootView.findViewById(R.id.tv_city);
        tvResidentStatus=(TextView)rootView.findViewById(R.id.tv_resident_stat);
        tvParish=(TextView)rootView.findViewById(R.id.tv_parish);
        tvVillage=(TextView)rootView.findViewById(R.id.tv_village);

        tvEdu=(TextView)rootView.findViewById(R.id.tv_edu);
        tvInstitute=(TextView)rootView.findViewById(R.id.tv_college);
        tvEduDetail=(TextView)rootView.findViewById(R.id.tv_eduInDetail);
        tvOccu=(TextView)rootView.findViewById(R.id.tv_occu);
        tvOccuDetail=(TextView)rootView.findViewById(R.id.tv_occuInDetail);
        tvEmployedIn=(TextView)rootView.findViewById(R.id.tv_employdIn);
        tvAnnualIncome=(TextView)rootView.findViewById(R.id.tv_income);

        tvFamValues=(TextView)rootView.findViewById(R.id.tv_fam_values);
        tvFamType=(TextView)rootView.findViewById(R.id.tv_fam_type);
        tvFamStatus=(TextView)rootView.findViewById(R.id.tv_fam_stat);
        tvFamOrigin=(TextView)rootView.findViewById(R.id.tv_fam_origin);
        tvFamLoc=(TextView)rootView.findViewById(R.id.tv_fam_loc);
        tvFatherStat=(TextView)rootView.findViewById(R.id.tv_father_status);
        tvMotherStat=(TextView)rootView.findViewById(R.id.tv_mother_status);
        tvNoOfBrothers=(TextView)rootView.findViewById(R.id.tv_noOfBrother);
        tvNoOfSisters=(TextView)rootView.findViewById(R.id.tv_noOfSister);

        spProfFor=(MaterialSpinner)rootView.findViewById(R.id.sp_profile_for);
        spCaste=(MaterialSpinner)rootView.findViewById(R.id.sp_caste);
        spReligion=(MaterialSpinner)rootView.findViewById(R.id.sp_religion);
        spMTongue=(MaterialSpinner)rootView.findViewById(R.id.sp_mothertongue);
        spAgeFrom=(MaterialSpinner)rootView.findViewById(R.id.sp_age_from);
        spAgeTo=(MaterialSpinner)rootView.findViewById(R.id.sp_age_to);

        spMaritalStat=(MaterialSpinner)rootView.findViewById(R.id.sp_marital_stat);
        spCountry=(MaterialSpinner)rootView.findViewById(R.id.sp_country);
        spState=(MaterialSpinner)rootView.findViewById(R.id.sp_state);spCity=(MaterialSpinner)rootView.findViewById(R.id.sp_city);
        spBirthCountry=(MaterialSpinner)rootView.findViewById(R.id.sp_CountryOfBirth);
        spBirthState=(MaterialSpinner)rootView.findViewById(R.id.sp_stateOfBirth);spBirthCity=(MaterialSpinner)rootView.findViewById(R.id.sp_cityOfBirth);
        spHeightFrom=(MaterialSpinner)rootView.findViewById(R.id.sp_height_from);spHeightTo=(MaterialSpinner)rootView.findViewById(R.id.sp_height_to);
        spWeight=(MaterialSpinner)rootView.findViewById(R.id.sp_weight);
        spBodyType=(MaterialSpinner)rootView.findViewById(R.id.sp_body_type);spComplexion=(MaterialSpinner)rootView.findViewById(R.id.sp_complexion);
        spPhysStat=(MaterialSpinner)rootView.findViewById(R.id.sp_physical_stat);spEdu=(MaterialSpinner)rootView.findViewById(R.id.sp_edu);
        spOccu=(MaterialSpinner)rootView.findViewById(R.id.sp_occu);
        //spCurrency=(MaterialSpinner)rootView.findViewById(R.id.sp_currency);
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
        spAgeFrom.setOnItemSelectedListener(this);
        spAgeTo.setOnItemSelectedListener(this);
/*
        spBirthCountry.setOnItemSelectedListener(this);
        spBirthState.setOnItemSelectedListener(this);
        spBirthCity.setOnItemSelectedListener(this);
*/
        spReligion.setOnItemSelectedListener(this);
      //  spDosh.setOnItemSelectedListener(this);


        btnAbt=(Button)rootView.findViewById(R.id.btnAboutGroomBride) ;
        btnBasic=(Button)rootView.findViewById(R.id.btnBasic) ;
        btnReligion=(Button)rootView.findViewById(R.id.btnReligion) ;
        btnProfessional=(Button)rootView.findViewById(R.id.btnProfessional) ;
        btnGroomBrideLoc=(Button)rootView.findViewById(R.id.btnGroomBrideLoc) ;
        btnFam=(Button)rootView.findViewById(R.id.btnFamily) ;
        btnAbtFam=(Button)rootView.findViewById(R.id.btnAboutFamily) ;


        btnBasic.setOnClickListener(this);btnReligion.setOnClickListener(this);btnProfessional.setOnClickListener(this);
        btnGroomBrideLoc.setOnClickListener(this);btnAbt.setOnClickListener(this);// btnFam.setOnClickListener(this);// btnAbtFam.setOnClickListener(this);


        initializeSpinnerLists();
        initializeSpinnerAdapters();

        getCountryList();
        getHeightList();
        getEducationList();
        getOccupationList();
        getReligionList();
        getMTongueList();




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


        list_ll.add(llAbt);
        list_ll.add(llBasic);
        list_ll.add(llReligion);
        list_ll.add(llGroomBrideLoc);
        list_ll.add(llProfessional);


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
//        imgEditFam.setOnClickListener(this);
  //      imgClearFam.setOnClickListener(this);
        imgEditAbout.setOnClickListener(this);
        imgClearAbout.setOnClickListener(this);


        //getPreference();

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

        for(int i=18;i<=70;i++)
            list_age.add(String.valueOf(i));

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
        aaAge= new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_age);
        aaAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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


        spMaritalStat.setAdapter(aaMaritalStat);spAgeTo.setAdapter(aaAge);spAgeFrom.setAdapter(aaAge);
        spPhysStat.setAdapter(aaPhysStat);
        spFood.setAdapter(aaFood);spDrink.setAdapter(aaDrink);spSmoke.setAdapter(aaSmoke);

        Log.e("intentMaritialStat",intentMaritalStat);
        if(!intentMaritalStat.equalsIgnoreCase("")){

            tvMaritalStat.setText(list_marital_Stat.get(Integer.parseInt(intentMaritalStat)-1));
            spMaritalStat.setSelection(Integer.parseInt(intentMaritalStat));
        }

        Log.e("intentPhysicalStat",intentPhysicalStat);
        if(!intentPhysicalStat.equalsIgnoreCase("")){

            tvPhysicalStat.setText(list_physical_stat.get(Integer.parseInt(intentPhysicalStat)-1));
            spPhysStat.setSelection(Integer.parseInt(intentPhysicalStat));
        }

        Log.e("intentEating",intentEating);
        if(!intentEating.equalsIgnoreCase("")){

            tvFood.setText(list_food.get(Integer.parseInt(intentEating)-1));
            spFood.setSelection(Integer.parseInt(intentEating));
        }

        Log.e("intentDrinking",intentDrinking);
        if(!intentDrinking.equalsIgnoreCase("")){

            tvDrink.setText(list_drink.get(Integer.parseInt(intentDrinking)-1));
            spDrink.setSelection(Integer.parseInt(intentDrinking));
        }

        Log.e("intentSmoking",intentSmoking);
        if(!intentSmoking.equalsIgnoreCase("")){

            tvSmoke.setText(list_smoke.get(Integer.parseInt(intentSmoking)-1));
            spSmoke.setSelection(Integer.parseInt(intentSmoking));
        }

        getPreference();



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
                if(spCountry.getSelectedItemPosition()>0 )
                {
                   /* Log.e("selected index",""+list_country.indexOf(spCountry.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_name());
                    Log.e("pojo element id",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_id());
                    Log.e("pojo element sortname",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_currency());
                   // Log.e("pojo element phone",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_extension());*/
                    getStateList(list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_id());
                    //  getCurrencyList(list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())+1).getCountry_id());

                }
                break;

            case R.id.sp_CountryOfBirth:
                if(spBirthCountry.getSelectedItemPosition()>0)
                {
                    getBirthStateList(list_pojo_birth_country.get(list_birth_country.indexOf(spBirthCountry.getSelectedItem())).getCountry_id());
                }
                break;

            case R.id.sp_stateOfBirth:
                Log.e("onIemSelectedListener","state called");
                if( spBirthState.getSelectedItemPosition()>0)
                {
                    /*Log.e("selected index",""+list_state.indexOf(spState.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_name());
                    Log.e("pojo element id",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_id());
                    Log.e("pojo element countryid",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getCountry_id());*/
                    getBirthCityList(list_pojo_birth_state.get(list_birth_state.indexOf(spBirthState.getSelectedItem())).getState_id());
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
//                    getCityList(list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_id());
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
              keepButtonOpen("basic");
                /*  if(llBasic.getVisibility()==View.GONE)
                    llBasic.setVisibility(View.VISIBLE);
                else
                    llBasic.setVisibility(View.GONE);*/
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
                    updateProfile("basic");
                }

            case R.id.btnReligion:
              keepButtonOpen("religion");
                /*  if(llReligion.getVisibility()==View.GONE)
                    llReligion.setVisibility(View.VISIBLE);
                else
                    llReligion.setVisibility(View.GONE);*/
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
                    updateProfile("religion");
                }
                break;


            case R.id.btnGroomBrideLoc:
                keepButtonOpen("location");
                /*if(llGroomBrideLoc.getVisibility()==View.GONE)
                    llGroomBrideLoc.setVisibility(View.VISIBLE);
                else
                    llGroomBrideLoc.setVisibility(View.GONE);*/
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
                    updateProfile("groomBrideLoc");
                }
                break;

            case R.id.btnProfessional:
               keepButtonOpen("professional");
                /* if(llProfessional.getVisibility()==View.GONE)
                    llProfessional.setVisibility(View.VISIBLE);
                else
                    llProfessional.setVisibility(View.GONE);*/
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
                    updateProfile("professional");
                }

          /*  case R.id.btnFamily:
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
                    updateProfile("family_details");
                }*/
          /*  case R.id.btnAboutFamily:
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
                    *//*llFamilyView.setVisibility(View.INVISIBLE);
                    llFamilyEdit.setVisibility(View.VISIBLE);*//*
                    imgEditAbtFam.setImageResource(R.mipmap.saveicon);
                    imgClearAbtFam.setVisibility(View.VISIBLE);
                }
                else{
                    updateProfile("family_about");
                }
*/

            case R.id.btnAboutGroomBride:
               keepButtonOpen("about");
                /* if(llAbt.getVisibility()==View.GONE)
                    llAbt.setVisibility(View.VISIBLE);
                else
                    llAbt.setVisibility(View.GONE);*/
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
                    updateProfile("about_partner");
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

            /*            if(!intentReligion.equalsIgnoreCase("")) {

                            String religion_name="";
                            for(int i=0;i<list_pojo_religion.size();i++){
                                if(list_pojo_religion.get(i).getReligion_id().equalsIgnoreCase(intentReligion)){
                                    religion_name=list_pojo_religion.get(i).getReligion_name();
                                    break;
                                }

                            }
                            //spReligion.setSelection(list_religion.indexOf(intentReligion) + 1);
                         *//*   spReligion.setSelection(Integer.parseInt(intentReligion));
                            tvReligion.setText(list_religion.get(Integer.parseInt(intentReligion)));*//*
                            spReligion.setSelection(list_religion.indexOf(religion_name)+1);
                            tvReligion.setText(religion_name);
*/
                            Log.e("intentReligion",intentReligion);
                            if(!intentReligion.equalsIgnoreCase("")){

                                for(int i=0;i<list_pojo_religion.size();i++){
                                    if(list_pojo_religion.get(i).getReligion_id().equalsIgnoreCase(intentReligion)){
                                        tvReligion.setText(list_pojo_religion.get(i).getReligion_name());
                                        spReligion.setSelection(list_religion.indexOf(list_pojo_religion.get(i).getReligion_name())+1);
                                        break;
                                    }
                                }

                            }

                        }
                        progressDialog.dismiss();
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
        if(list_pojo_caste!=null)
            list_pojo_caste.clear();

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
                      /*  if(intentCaste!=null) {
                            Log.e("intentCaste",intentCaste);
                            String caste_name="";
                           // spCaste.setSelection(list_caste.indexOf(intentCaste) + 1);
                            for(int i=0;i<list_pojo_caste.size();i++){
                                if(list_pojo_caste.get(i).getCaste_id().equalsIgnoreCase(intentCaste)){
                                    caste_name=list_pojo_caste.get(i).getCaste_name();
                                    break;
                                }

                            }
                            Log.e("caste_name",caste_name);
                            spCaste.setSelection(list_caste.indexOf(caste_name)+1);
                            tvCaste.setText(caste_name);
                        }*/

                        Log.e("intentCaste",intentCaste);
                        if(!intentCaste.equalsIgnoreCase("")){


                            for(int i=0;i<list_pojo_caste.size();i++){
                                if(list_pojo_caste.get(i).getCaste_id().equalsIgnoreCase(intentCaste)) {
                                    tvCaste.setText(list_pojo_caste.get(i).getCaste_name());
                                    spCaste.setSelection(list_caste.indexOf(list_pojo_caste.get(i).getCaste_name())+1);
                                    break;

                                }
                            }
                        }

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
                        aaMTongue = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_mtongue);
                        aaMTongue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMTongue.setAdapter(aaMTongue);

                      /*  if(intentMTongue!=null) {
                         //   spMTongue.setSelection(list_mtongue.indexOf(intentMTongue) + 1);
                            spMTongue.setSelection(Integer.parseInt(intentMTongue));
                            tvMTongue.setText(list_mtongue.get(Integer.parseInt(intentMTongue)));
                        }*/

                        Log.e("intentMTongue",intentMTongue);
                        if(!intentMTongue.equalsIgnoreCase("")){

                            for(int i=0;i<list_pojo_mtongue.size();i++){
                                if(list_pojo_mtongue.get(i).getMother_tongue_id().equalsIgnoreCase(intentMTongue)) {
                                    tvMTongue.setText(list_pojo_mtongue.get(i).getMother_tongue_name());
                                    spMTongue.setSelection(list_mtongue.indexOf(list_pojo_mtongue.get(i).getMother_tongue_name())+1);
                                    break;

                                }
                            }


                        }

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

                        /*if(!intentCountry.equalsIgnoreCase("")) {
                            tvCountry.setText(list_pojo_country.get(Integer.parseInt(intentCountry)-1).getCountry_name());
                            spCountry.setSelection(list_country.indexOf(list_pojo_country.get(Integer.parseInt(intentCountry)-1).getCountry_name()));
                        }*/

                        if(!intentCountry.equalsIgnoreCase(""))
                        {
                            for(int i=0;i<list_pojo_country.size();i++){
                                if(list_pojo_country.get(i).getCountry_id().equalsIgnoreCase(intentCountry)){
                                    tvCountry.setText(list_pojo_country.get(i).getCountry_name());
                                    spCountry.setSelection(list_country.indexOf(list_pojo_country.get(i).getCountry_name())+1);
                                    break;
                                }
                            }
                        }
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
/*
                        if(!intentState.equalsIgnoreCase("")) {
                         //   spState.setSelection(list_state.indexOf(intentState) + 1);
                            tvState.setText(list_pojo_state.get(Integer.parseInt(intentState)-1).getState_name());
                            spState.setSelection(list_state.indexOf(list_pojo_state.get(Integer.parseInt(intentState)-1).getState_name()));
                        }*/

                        if(!intentState.equalsIgnoreCase("")){
                            for(int i=0;i<list_pojo_state.size();i++){
                                if(list_pojo_state.get(i).getState_id().equalsIgnoreCase(intentState)){
                                    tvState.setText(list_pojo_state.get(i).getState_name());
                                    spState.setSelection(list_state.indexOf(list_pojo_state.get(i).getState_name())+1);
                                    break;
                                }
                            }
                        }

                    }
                    else
                        spState.setSelection(0);
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
                      //  spCity.setAdapter(aaCity);


                        //if(intentCity!=null) spCity.setSelection(list_city.indexOf(intentCity)+1);

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

    public void getBirthStateList(String country_id){

        progressDialog.show();
        if(list_birth_state!=null)
            list_birth_state.clear();
        if(list_pojo_birth_state!=null)
            list_pojo_birth_state.clear();
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
                            list_pojo_birth_state.add(resultMap.get(key));
                            list_birth_state.add(resultMap.get(key).getState_name());
                        }
                        Log.e("List Size",""+list_birth_state.size());
                        aaState = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_birth_state);
                        aaState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     /*   spBirthState.setAdapter(aaState);

                        if(intentBirthState!=null)
                            spBirthState.setSelection(list_birth_state.indexOf(intentBirthState)+1);
*/
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

    public void getBirthCityList(String state_id){

        progressDialog.show();
        if(list_birth_city!=null)
            list_birth_city.clear();
        if(list_pojo_birth_city!=null)
            list_pojo_birth_city.clear();
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
                            list_pojo_birth_city.add(resultMap.get(key));
                            list_birth_city.add(resultMap.get(key).getCity_name());
                        }
                        Log.e("List Size",""+list_city.size());
                        aaCity = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list_birth_city);
                        aaCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     /*   spBirthCity.setAdapter(aaCity);


                        if(intentBirthCity!=null)
                            spBirthCity.setSelection(list_birth_city.indexOf(intentBirthCity)+1);
*/
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
                        spHeightFrom.setAdapter(aaHeight);
                        spHeightTo.setAdapter(aaHeight);


                        Log.e("intentHeightFrom",intentHeightFrom);
                        if(intentHeightFrom.equalsIgnoreCase("")||intentHeightFrom.equalsIgnoreCase("0")){

                            tvHeight.setText("");
                        }
                        else{

                            Log.e("heightFrom",list_pojo_height.get(Integer.parseInt(intentHeightFrom)).getHeight());
                            tvHeight.setText(list_pojo_height.get(Integer.parseInt(intentHeightFrom)-1).getHeight());
                            spHeightFrom.setSelection(list_height.indexOf(list_pojo_height.get(Integer.parseInt(intentHeightFrom)).getHeight()));
                        }

                        Log.e("intentHeightTo",intentHeightTo);
                        if(intentHeightTo.equalsIgnoreCase("")||intentHeightTo.equalsIgnoreCase("0")){
                            tvHeight.append("");
                        }
                        else{

                            tvHeight.append(" to "+list_pojo_height.get(Integer.parseInt(intentHeightTo)-1).getHeight());
                            spHeightTo.setSelection(list_height.indexOf(list_pojo_height.get(Integer.parseInt(intentHeightTo)).getHeight()));
                        }
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
                    if(parentPojoEducation.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoEducation.getObjEducation().size());

                        LinkedHashMap<String, ChildPojoEducation> resultMap = parentPojoEducation.getObjEducation();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_edu.add(resultMap.get(key));
                            list_edu.add(resultMap.get(key).getEducation());
                        }
                        Log.e("List Size", "" + list_edu.size());
                        aaEdu = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_edu);
                        aaEdu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spEdu.setAdapter(aaEdu);

                       /* if(!intentEdu.equalsIgnoreCase("")) {
                            Log.e("intentedufunction",intentEdu);
                            Log.e("indexIntentEdu",""+list_edu.indexOf(intentEdu));
                            spEdu.setSelection(Integer.parseInt(intentEdu));
                            tvEdu.setText(list_edu.get(Integer.parseInt(intentEdu)-1));
                        }*/

                        if (!intentEdu.equalsIgnoreCase("")) {
                            for (int i = 0; i < list_pojo_edu.size(); i++) {
                                if (list_pojo_edu.get(i).getEducation_id().equalsIgnoreCase(intentEdu)) {
                                    tvEdu.setText(list_pojo_edu.get(i).getEducation());
                                    spEdu.setSelection(list_edu.indexOf(list_pojo_edu.get(i).getEducation()) + 1);
                                    break;
                                }
                            }
                        }
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

                       /* if(!intentOccu.equalsIgnoreCase("")) {
                            Log.e("intentoccufunction",intentOccu);
                            spOccu.setSelection(Integer.parseInt(intentOccu));
                            Log.e("indexIntentOccu",""+list_occu.indexOf(intentOccu));
                            tvOccu.setText(list_occu.get(Integer.parseInt(intentOccu)-1));
                        }*/

                        if(!intentOccu.equalsIgnoreCase("")) {

                            for (int i = 0; i < list_pojo_occu.size(); i++) {
                                if (list_pojo_occu.get(i).getOccupation_id().equalsIgnoreCase(intentOccu)) {
                                    tvOccu.setText(list_pojo_occu.get(i).getOccupation());
                                    spOccu.setSelection(list_occu.indexOf(list_pojo_occu.get(i).getOccupation()) + 1);
                                    break;
                                }
                            }
                        }
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
    public void updateProfile(final String section){

        progressDialog.show();


        final savePreferencesInterface getResponse = APIClient.getClient().create(savePreferencesInterface.class);
        Call<CommonParentPojo> call = null;
        if(section.equalsIgnoreCase("Basic")) {
            String ps= spPhysStat.getSelectedItemPosition()==0 ? "" : String.valueOf(spPhysStat.getSelectedItemPosition());
            String ms= spMaritalStat.getSelectedItemPosition()==0 ? "" : String.valueOf(spMaritalStat.getSelectedItemPosition());

            call = getResponse.updateBasic(spCustProfile.getMatrimonyId(), "24","25",
                    spMaritalStat.getSelectedItemPosition()==0 ? "0" : String.valueOf(spMaritalStat.getSelectedItemPosition()),
                    spPhysStat.getSelectedItemPosition()==0 ? "0" : String.valueOf(spPhysStat.getSelectedItemPosition()),
                    spHeightFrom.getSelectedItemPosition()==0 ? "0" : list_pojo_height.get(list_height.indexOf(spHeightFrom.getSelectedItem().toString())).getHeight_id(),
                   spHeightTo.getSelectedItemPosition()==0 ? "0" : list_pojo_height.get(list_height.indexOf(spHeightTo.getSelectedItem().toString())).getHeight_id(),
                    spFood.getSelectedItemPosition()==0 ? "0" : String.valueOf(spFood.getSelectedItemPosition()),String.valueOf(spDrink.getSelectedItemPosition()),
                    spSmoke.getSelectedItemPosition()==0 ? "0" : String.valueOf(spSmoke.getSelectedItemPosition()));
        }
        else if(section.equalsIgnoreCase("religion")) {
        String c="";
            if(list_caste.size()==0)
                c="0";
            else if(spCaste.getSelectedItemPosition()==0)
                c="0";
            else
                c=list_pojo_caste.get(list_caste.indexOf(spCaste.getSelectedItem().toString())).getCaste_id();


            call = getResponse.updateReligion(spCustProfile.getMatrimonyId(),
                    spReligion.getSelectedItemPosition()==0?"0":list_pojo_religion.get(list_religion.indexOf(spReligion.getSelectedItem().toString())).getReligion_id(),
                    spMTongue.getSelectedItemPosition()==0?"0":list_pojo_mtongue.get(list_mtongue.indexOf(spMTongue.getSelectedItem().toString())).getMother_tongue_id(),
                    c);
        }
        else if(section.equalsIgnoreCase("groomBrideLoc")) {

            String s="";
            if(list_state.size()==0)
                s="0";
            else if(spState.getSelectedItemPosition()<=0)
                s="0";
            else
                s=list_pojo_state.get(list_state.indexOf(spState.getSelectedItem().toString())).getState_id();

            call = getResponse.updateGroomBrideLoc(spCustProfile.getMatrimonyId(),
                    spCountry.getSelectedItemPosition()<=0?"0":list_pojo_country.get(list_country.indexOf(spCountry.getItemAtPosition(spCountry.getSelectedItemPosition()-1).toString())).getCountry_id(),
                   s);
        }
        else if(section.equalsIgnoreCase("professional")) {

            call = getResponse.updateProfessional(spCustProfile.getMatrimonyId(),
                    spEdu.getSelectedItemPosition()<=0?"0":list_pojo_edu.get(list_edu.indexOf(spEdu.getItemAtPosition(spEdu.getSelectedItemPosition()-1).toString())).getEducation_id(),
                    spOccu.getSelectedItemPosition()<=0?"0":list_pojo_occu.get(list_occu.indexOf(spOccu.getItemAtPosition(spOccu.getSelectedItemPosition()-1).toString())).getOccupation_id(),etIncome.getText().toString());

        }
        else if(section.equalsIgnoreCase("about_partner")) {

            call = getResponse.updateAboutYou(spCustProfile.getMatrimonyId(),etAbout.getText().toString());
        }
        call.enqueue(new Callback<CommonParentPojo>() {
            @Override
            public void onResponse(Call<CommonParentPojo> call, Response<CommonParentPojo> response) {

                Log.e("Inside","onResponse");

                CommonParentPojo commonParentPojo =response.body();
                Log.e("response religion", String.valueOf(response.body()));
                if(commonParentPojo !=null){
                    if(commonParentPojo.getStatus().equalsIgnoreCase("1")){

                        showToast("Updated Successfully!");
                        getPreference();

                        if(section.equalsIgnoreCase("Basic"))
                            imgClearBasic.performClick();
                        else if(section.equalsIgnoreCase("religion"))
                            imgClearReligion.performClick();
                        else if(section.equalsIgnoreCase("groomBrideLoc"))
                            imgClearGroomBrideLoc.performClick();
                        else if(section.equalsIgnoreCase("professional"))
                            imgClearProfessional.performClick();
                        else if(section.equalsIgnoreCase("about_partner"))
                            imgClearAbout.performClick();

                    }
                }

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                Log.e("throwable",""+t);
                progressDialog.dismiss();
            }
        });

    }
    public void getPreference(){

        progressDialog.show();
        if(mListItem!=null)
            mListItem.clear();

        getPreferenceInterface getResponse = APIClient.getClient().create(getPreferenceInterface.class);
        Call<ParentPojoPartnerPref> call = getResponse.doGetListResources(spCustProfile.getMatrimonyId());
        call.enqueue(new Callback<ParentPojoPartnerPref>() {
            @Override
            public void onResponse(Call<ParentPojoPartnerPref> call, Response<ParentPojoPartnerPref> response) {

                Log.e("Inside","onResponse");
               Log.e(" status response",response.body().getStatus());
                //Log.e("response body",response.body().getMsg());*/
                ParentPojoPartnerPref parentPojoPartnerPref =response.body();
                Log.e(" status pojo",parentPojoPartnerPref.getStatus());
                if(parentPojoPartnerPref !=null){
                    if(parentPojoPartnerPref.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+ parentPojoPartnerPref.getObjProfile().size());
                        mListItem=parentPojoPartnerPref.getObjProfile();
                        //mListItem.add(parentPojoPartnerPref.getObjProfile().get(0));
                        Log.e("age",""+mListItem.get(0).getCaste());
                        setBasic();
                        setReligion();
                       setGroomBrideLoc();
                        setProfessional();
                        setPartnerDescription();
                    }
                }
                else
                    Log.e("parentpojotabwhome","null");
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ParentPojoPartnerPref> call, Throwable t) {

                Log.e("throwable",""+t);
                progressDialog.dismiss();
            }
        });

    }

    public void setBasic(){

        tvAge.setText(mListItem.get(0).getAge_from()+" to "+mListItem.get(0).getAge_to());
        if(mListItem.get(0).getPhysical_status()!=null) {
            intentPhysicalStat = mListItem.get(0).getPhysical_status();
            if (intentPhysicalStat.equalsIgnoreCase("0") || intentPhysicalStat.equalsIgnoreCase("")) {
                tvPhysicalStat.setText("");
            } else {
                //intentPhysicalStat = mListItem.get(0).getPhysical_status();
                Log.e("listPhysicalStatSize", "" + list_physical_stat.size());
                Log.e("textPStat", "" + list_marital_Stat.get(Integer.parseInt(intentPhysicalStat) - 1));
                if (list_physical_stat.size() > Integer.parseInt(intentPhysicalStat) - 1) {
                    tvPhysicalStat.setText(list_physical_stat.get(Integer.parseInt(intentPhysicalStat) - 1));
                    spPhysStat.setSelection(Integer.parseInt(intentPhysicalStat));
                }
            }
        }
        if(mListItem.get(0).getMaritial_status()!=null) {
            intentMaritalStat = mListItem.get(0).getMaritial_status();
            if (intentMaritalStat.equalsIgnoreCase("0")||intentMaritalStat.equalsIgnoreCase("")) {
                tvMaritalStat.setText("");
            } else {
                //intentMaritalStat = mListItem.get(0).getMaritial_status();
                Log.e("listMaritialStatSize", "" + list_marital_Stat.size());
                Log.e("textMStat", "" + list_marital_Stat.get(Integer.parseInt(intentMaritalStat) - 1));
                if (list_marital_Stat.size() > Integer.parseInt(intentMaritalStat) - 1) {

                    tvMaritalStat.setText(list_marital_Stat.get(Integer.parseInt(intentMaritalStat) - 1));
                    spMaritalStat.setSelection(Integer.parseInt(intentMaritalStat));
                    Log.e("textMaritalStat", tvMaritalStat.getText().toString());
                }
            }
        }

        if(mListItem.get(0).getHeight_from_id()!=null) {
            intentHeightFrom = mListItem.get(0).getHeight_from_id();
            if (intentHeightFrom.equalsIgnoreCase("0")||intentHeightFrom.equalsIgnoreCase("")) {
                tvHeight.setText("");
            } else {
               // intentHeightFrom = mListItem.get(0).getHeight_from_id();
                if (list_height.size() > Integer.parseInt(intentHeightFrom)) {
                    Log.e("heightFrom", list_pojo_height.get(Integer.parseInt(intentHeightFrom)).getHeight());
                    tvHeight.setText(list_pojo_height.get(Integer.parseInt(intentHeightFrom) - 1).getHeight());
                    spHeightFrom.setSelection(list_height.indexOf(list_pojo_height.get(Integer.parseInt(intentHeightFrom)).getHeight()));
                }
            }
        }

        if(mListItem.get(0).getHeight_to_id()!=null) {
            intentHeightTo = mListItem.get(0).getHeight_to_id();
            if (intentHeightTo.equalsIgnoreCase("0")||intentHeightTo.equalsIgnoreCase("")) {
                tvHeight.append("");
            } else {
                //intentHeightTo = mListItem.get(0).getHeight_to_id();
                if (list_height.size() > Integer.parseInt(intentHeightTo)) {
                    tvHeight.append(" to " + list_pojo_height.get(Integer.parseInt(intentHeightTo) - 1).getHeight());
                    spHeightTo.setSelection(list_height.indexOf(list_pojo_height.get(Integer.parseInt(intentHeightTo)).getHeight()));
                }
            }
        }

        if(mListItem.get(0).getEating_habit()!=null) {
            intentEating = mListItem.get(0).getEating_habit();
            if (intentEating.equalsIgnoreCase("0")||intentEating.equalsIgnoreCase("")) {
                tvFood.setText("");
            } else {
                //intentEating = mListItem.get(0).getEating_habit();
                Log.e("intentEating", intentEating);
                Log.e("listEatingSize", "" + list_food.size());
                Log.e("textEating", "" + list_food.get(Integer.parseInt(intentEating) - 1));
                if (list_food.size() >= (Integer.parseInt(intentEating))) {
                    tvFood.setText(list_food.get(Integer.parseInt(intentEating) - 1));
                    spFood.setSelection(Integer.parseInt(intentEating));
                }
            }
        }

        if(mListItem.get(0).getDrinking_habit()!=null) {
            intentDrinking = mListItem.get(0).getDrinking_habit();
            if (intentDrinking.equalsIgnoreCase("0")||intentDrinking.equalsIgnoreCase("")) {
                tvDrink.setText("");
            } else {
                //intentDrinking = mListItem.get(0).getDrinking_habit();
                Log.e("intentDrinking", intentDrinking);
                Log.e("listDrinkingSize", "" + list_drink.size());
                Log.e("textDrinking", "" + list_drink.get(Integer.parseInt(intentDrinking) - 1));
                if (list_drink.size() >= Integer.parseInt(intentDrinking)) {
                    tvDrink.setText(list_drink.get(Integer.parseInt(intentDrinking) - 1));
                    spDrink.setSelection(Integer.parseInt(intentDrinking));
                }
            }
        }

        if(mListItem.get(0).getSmoking_habit()!=null) {
            intentSmoking = mListItem.get(0).getSmoking_habit();
            if (intentSmoking.equalsIgnoreCase("0")||intentSmoking.equalsIgnoreCase("")) {
                tvSmoke.setText("");
            } else {
               // intentSmoking = mListItem.get(0).getSmoking_habit();
                Log.e("intentSmoking", intentSmoking);
                Log.e("listSmokingSize", "" + list_smoke.size());
                Log.e("textSmoke", "" + list_smoke.get(Integer.parseInt(intentSmoking) - 1));
                if (list_smoke.size() >= Integer.parseInt(intentSmoking)) {
                    tvSmoke.setText(list_smoke.get(Integer.parseInt(intentSmoking) - 1));
                    spSmoke.setSelection(Integer.parseInt(intentSmoking));
                }
            }
        }
    }

    public void setReligion(){

        if(mListItem.get(0).getReligion()!=null) {
            intentReligion = mListItem.get(0).getReligion();
            if (intentReligion.equalsIgnoreCase("0")||intentReligion.equalsIgnoreCase("")) {
                tvReligion.setText("");
            } else {
//                intentReligion = mListItem.get(0).getReligion();
                for (int i = 0; i < list_pojo_religion.size(); i++) {
                    if (list_pojo_religion.get(i).getReligion_id().equalsIgnoreCase(intentReligion)) {
                        tvReligion.setText(list_pojo_religion.get(i).getReligion_name());
                        spReligion.setSelection(list_religion.indexOf(list_pojo_religion.get(i).getReligion_name()) + 1);
                        break;
                    }
                }
            }
        }

        if(mListItem.get(0).getCaste()!=null) {
            intentCaste = mListItem.get(0).getCaste();
            if (intentCaste.equalsIgnoreCase("0")||intentCaste.equalsIgnoreCase("")) {
                tvCaste.setText("");
            } else {

//                intentCaste = mListItem.get(0).getCaste();
                for (int i = 0; i < list_pojo_caste.size(); i++) {
                    if (list_pojo_caste.get(i).getCaste_id().equalsIgnoreCase(intentCaste)) {
                        tvCaste.setText(list_pojo_caste.get(i).getCaste_name());
                        spCaste.setSelection(list_caste.indexOf(list_pojo_caste.get(i).getCaste_name()) + 1);
                        break;

                    }
                }
            }
        }

        if(mListItem.get(0).getMother_language()!=null) {
            intentMTongue = mListItem.get(0).getMother_language();
            if (intentMTongue.equalsIgnoreCase("0")||intentMTongue.equalsIgnoreCase("")) {
                tvMTongue.setText("");
            } else {
                intentMTongue = mListItem.get(0).getMother_language();
                for (int i = 0; i < list_pojo_mtongue.size(); i++) {
                    if (list_pojo_mtongue.get(i).getMother_tongue_id().equalsIgnoreCase(intentMTongue)) {
                        tvMTongue.setText(list_pojo_mtongue.get(i).getMother_tongue_name());
                        spMTongue.setSelection(list_mtongue.indexOf(list_pojo_mtongue.get(i).getMother_tongue_name()) + 1);
                        break;

                    }
                }
            }
        }
    }

    public void setGroomBrideLoc(){

        if(mListItem.get(0).getCountry()!=null) {
            intentCountry = mListItem.get(0).getCountry();
            if (intentCountry.equalsIgnoreCase("0")||intentCountry.equalsIgnoreCase("")) {
                tvCountry.setText("");
            } else {

                //intentCountry = mListItem.get(0).getCountry();
                for (int i = 0; i < list_pojo_country.size(); i++) {
                    if (list_pojo_country.get(i).getCountry_id().equalsIgnoreCase(intentCountry)) {
                        tvCountry.setText(list_pojo_country.get(i).getCountry_name());
                        spCountry.setSelection(list_country.indexOf(list_pojo_country.get(i).getCountry_name()) + 1);
                        break;
                    }
                }
            }
        }

        if(mListItem.get(0).getState()!=null) {
            intentState = mListItem.get(0).getState();
            if (intentState.equalsIgnoreCase("0")||intentState.equalsIgnoreCase("")) {
                tvState.setText("");
            } else {
                Log.e("state", mListItem.get(0).getState());
                //  tvState.setText(mListItem.get(0).getState());
              //  intentState = mListItem.get(0).getState();

                for (int i = 0; i < list_pojo_state.size(); i++) {
                    if (list_pojo_state.get(i).getState_id().equalsIgnoreCase(intentState)) {
                        tvState.setText(list_pojo_state.get(i).getState_name());
                        spState.setSelection(list_state.indexOf(list_pojo_state.get(i).getState_name()) + 1);
                        break;
                    }
                }
            }
        }

    }

    public void setProfessional(){
        if(mListItem.get(0).getEducation()!=null) {
            intentEdu = mListItem.get(0).getEducation();
            if (intentEdu.equalsIgnoreCase("0")||intentEdu.equalsIgnoreCase("")) {
                tvEdu.setText("");
            } else {
              //  intentEdu = mListItem.get(0).getEducation();
                for (int i = 0; i < list_pojo_edu.size(); i++) {
                    if (list_pojo_edu.get(i).getEducation_id().equalsIgnoreCase(intentEdu)) {
                        tvEdu.setText(list_pojo_edu.get(i).getEducation());
                        spEdu.setSelection(list_edu.indexOf(list_pojo_edu.get(i).getEducation()) + 1);
                        break;
                    }
                }
            }
        }
        if(mListItem.get(0).getOccupation()!=null) {
            intentOccu = mListItem.get(0).getOccupation();
            if (intentOccu.equalsIgnoreCase("")||intentOccu.equalsIgnoreCase("")) {
                tvOccu.setText("");
            } else {
               // intentOccu = mListItem.get(0).getOccupation();
                for (int i = 0; i < list_pojo_occu.size(); i++) {
                    if (list_pojo_occu.get(i).getOccupation_id().equalsIgnoreCase(intentOccu)) {
                        tvOccu.setText(list_pojo_occu.get(i).getOccupation());
                        spOccu.setSelection(list_occu.indexOf(list_pojo_occu.get(i).getOccupation()) + 1);
                        break;
                    }
                }
            }
        }
       // getEducationList();
        //getOccupationList();
//        tvEdu.setText(list_edu.get(Integer.parseInt(mListItem.get(0).getEducation())-1));
       /* if (spEdu.getSelectedItemPosition()==0) {
            if (list_edu.contains(intentEdu)) {
                spEdu.setSelection(list_edu.indexOf(intentEdu) + 1);
            }
        }

        Log.e("intentEdu", mListItem.get(0).getEducation());

        //tvOccu.setText(list_occu.get(Integer.parseInt(mListItem.get(0).getOccupation())-1));
        intentOccu=mListItem.get(0).getOccupation();

        if (spOccu.getSelectedItemPosition()==0) {
            if (list_occu.contains(intentOccu)) {
                spOccu.setSelection(list_occu.indexOf(intentOccu) + 1);
            }
        }
        Log.e("intentoccu", intentOccu);*/
        tvAnnualIncome.setText(mListItem.get(0).getMin_income());
        etIncome.setText(mListItem.get(0).getMin_income());
    }

    public void setPartnerDescription(){


        etAbout.setText(mListItem.get(0).getAbout_partner());

    }
    //display toast
    public void showToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    public void keepButtonOpen(String section)
    {


        switch(section)
        {
            case "basic": if (llBasic.getVisibility() == View.GONE) {
                for(int i=0;i<list_ll.size();i++)
                {
                    list_ll.get(i).setVisibility(View.GONE);
                }
                llBasic.setVisibility(View.VISIBLE);
            }
            else
                llBasic.setVisibility(View.GONE);

                break;

            case "about":
                if (llAbt.getVisibility() == View.GONE) {

                    for(int i=0;i<list_ll.size();i++)
                    {
                        list_ll.get(i).setVisibility(View.GONE);
                    }
                    llAbt.setVisibility(View.VISIBLE);
                }
                else
                    llAbt.setVisibility(View.GONE);

                break;

            case "religion":
                if (llReligion.getVisibility() == View.GONE) {
                    for(int i=0;i<list_ll.size();i++)
                    {
                        list_ll.get(i).setVisibility(View.GONE);
                    }

                    llReligion.setVisibility(View.VISIBLE);
                }
                else
                    llReligion.setVisibility(View.GONE);

                break;

            case "location": if (llGroomBrideLoc.getVisibility() == View.GONE) {
                for(int i=0;i<list_ll.size();i++)
                {
                    list_ll.get(i).setVisibility(View.GONE);
                }
                llGroomBrideLoc.setVisibility(View.VISIBLE);
            }
            else
                llGroomBrideLoc.setVisibility(View.GONE);

                break;

            case "professional":
                if (llProfessional.getVisibility() == View.GONE) {

                    for(int i=0;i<list_ll.size();i++)
                    {
                        list_ll.get(i).setVisibility(View.GONE);
                    }
                    llProfessional.setVisibility(View.VISIBLE);
                }
                else
                    llProfessional.setVisibility(View.GONE);

                break;

        }

    }


}
