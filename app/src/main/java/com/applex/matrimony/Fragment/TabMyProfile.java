package com.applex.matrimony.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.applex.matrimony.Activity.FullImageActivity;
import com.applex.matrimony.Activity.RegisterActivity;
import com.applex.matrimony.Adapter.GalleryImagesAdapter;
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
import com.applex.matrimony.Interface.getProfileInterface;
import com.applex.matrimony.Interface.getRaasiInterface;
import com.applex.matrimony.Interface.getReligionInterface;
import com.applex.matrimony.Interface.getStarInterface;
import com.applex.matrimony.Interface.getStateInterface;
import com.applex.matrimony.Interface.getWeightInterface;
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
import com.applex.matrimony.Pojo.ParentPojoProfile;
import com.applex.matrimony.Pojo.ParentPojoRaasi;
import com.applex.matrimony.Pojo.ParentPojoReligion;
import com.applex.matrimony.Pojo.ParentPojoStar;
import com.applex.matrimony.Pojo.ParentPojoState;
import com.applex.matrimony.Pojo.ParentPojoWeight;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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


public class TabMyProfile extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {


    MaterialSpinner spMaritalStat, spCountry, spState, spCity, spBirthCountry, spBirthState, spBirthCity, spHeight, spWeight, spBodyType, spComplexion,
            spPhysStat, spEdu, spOccu, spCurrency, spFood, spDrink, spSmoke, spFamStat, spFamType, spFamValue, spProfFor, spCaste, spReligion, spMTongue, spGender, spStar, spRassi, spDosh, spGotra, spResident;

    ArrayAdapter aaProfFor, aaGender, aaCaste, aaMTongue, aaMaritalStat, aaCountry, aaState, aaCity, aaHeight, aaWeight, aaBodyType, aaComplexion,
            aaPhysStat, aaEdu, aaOccu, aaEmployIn, aaCurrency, aaFood, aaDrink, aaSmoke, aaFamStat, aaFamType, aapFamValue, aaStar, aaRassi, aaDosh, aaGotra, aaResident;


    EditText etAbout, etAboutFam, etName, etSubCaste, etCollege, etEduDetail, etOccuDetail, etEmployedIn, etIncome,
            etFamOrigin, etFamLoc, etFatherStat, etMotherStat, etNoOfBrothers, etNoOfSisters;
    DatePickerEditText etBDate;
    TimePickerEditText etBTime;
    ArrayList<String> list_prof_for = new ArrayList<String>();
    ArrayList<String> list_caste = new ArrayList<String>();
    ArrayList<ChildPojoReligion> list_pojo_religion = new ArrayList<ChildPojoReligion>();
    ArrayList<ChildPojoCaste> list_pojo_caste = new ArrayList<ChildPojoCaste>();
    ArrayList<ChildPojoMTongue> list_pojo_mtongue = new ArrayList<ChildPojoMTongue>();
    ArrayList<ChildPojoStar> list_pojo_star = new ArrayList<ChildPojoStar>();
    ArrayList<ChildPojoRaasi> list_pojo_raasi = new ArrayList<ChildPojoRaasi>();
    ArrayList<ChildPojoGotra> list_pojo_gotra = new ArrayList<ChildPojoGotra>();
    ArrayList<String> list_religion = new ArrayList<String>();
    ArrayList<String> list_mtongue = new ArrayList<String>();
    ArrayList<String> list_star = new ArrayList<String>();
    ArrayList<String> list_gotra = new ArrayList<String>();
    ArrayList<String> list_raasi = new ArrayList<String>();
    ArrayList<String> list_gender = new ArrayList<String>();
    ArrayList<String> list_marital_Stat = new ArrayList<String>();
    ArrayList<String> list_will_intercast = new ArrayList<String>();
    ArrayList<String> list_birth_country = new ArrayList<String>();
    ArrayList<ChildModelCountry> list_pojo_birth_country = new ArrayList<ChildModelCountry>();
    ArrayList<ChildPojoState> list_pojo_birth_state = new ArrayList<ChildPojoState>();
    ArrayList<String> list_birth_state = new ArrayList<String>();
    ArrayList<ChildPojoCity> list_pojo_birth_city = new ArrayList<ChildPojoCity>();
    ArrayList<String> list_birth_city = new ArrayList<String>();
    ArrayList<String> list_country = new ArrayList<String>();
    ArrayList<ChildModelCountry> list_pojo_country = new ArrayList<ChildModelCountry>();
    ArrayList<ChildPojoState> list_pojo_state = new ArrayList<ChildPojoState>();
    ArrayList<ChildPojoCity> list_pojo_city = new ArrayList<ChildPojoCity>();
    ArrayList<String> list_state = new ArrayList<String>();
    ArrayList<ChildPojoHeight> list_pojo_height = new ArrayList<ChildPojoHeight>();
    ArrayList<String> list_city = new ArrayList<String>();
    ArrayList<String> list_height = new ArrayList<String>();
    ArrayList<ChildPojoWeight> list_pojo_weight = new ArrayList<ChildPojoWeight>();
    ArrayList<String> list_weight = new ArrayList<String>();
    ArrayList<String> list_body_type = new ArrayList<String>();
    ArrayList<String> list_complexion = new ArrayList<String>();
    ArrayList<String> list_physical_stat = new ArrayList<String>();
    ArrayList<String> list_edu = new ArrayList<String>();
    ArrayList<ChildPojoEducation> list_pojo_edu = new ArrayList<ChildPojoEducation>();
    ArrayList<String> list_occu = new ArrayList<String>();
    ArrayList<ChildPojoOccupation> list_pojo_occu = new ArrayList<ChildPojoOccupation>();
    ArrayList<String> list_employed_in = new ArrayList<String>();
    ArrayList<String> list_lakh = new ArrayList<String>();
    ArrayList<String> list_thosand = new ArrayList<String>();
    ArrayList<String> list_dosh = new ArrayList<String>();
    ArrayList<String> list_resident = new ArrayList<String>();
    ArrayList<String> list_food = new ArrayList<String>();
    ArrayList<String> list_drink = new ArrayList<String>();
    ArrayList<String> list_smoke = new ArrayList<String>();
    ArrayList<String> list_fam_stat = new ArrayList<String>();
    ArrayList<String> list_fam_type = new ArrayList<String>();
    ArrayList<String> list_fam_value = new ArrayList<String>();
    ArrayList<String> list_currency = new ArrayList<String>();
    ArrayList<ChildPojoProfile> mListItem = new ArrayList<ChildPojoProfile>();
    JSONArray eduArray;
    /*    ArrayList<String> list_religion=new ArrayList<String>();
        ArrayList<ChildPojoReligion>list_pojo_religion=new ArrayList<ChildPojoReligion>();
        ArrayList<ChildPojoCaste>list_pojo_caste=new ArrayList<ChildPojoCaste>();
        ArrayList<String> list_caste=new ArrayList<String>();*/
    ArrayList<ChildPojoProfile> list_matches = new ArrayList<ChildPojoProfile>();
    ArrayList<ChildPojoProfile> list_highlights = new ArrayList<ChildPojoProfile>();
    public RecyclerView rv_profile_matches, rv_profile_highlight;
    HomeProfilesAdapter adapter;
    private ProgressBar progressBar;
    private LinearLayout lyt_not_found;
    ProgressDialog progressDialog;
    Button btnFind;
    EditText etAgeFrom, etAgeTo;
    Button btnBasic, btnReligion, btnProfessional, btnGroomBrideLoc, btnFam, btnAbtFam, btnAbt;
    LinearLayout llBasic, llBasicEdit, llBasicView, llReligion, llReligionEdit, llReligionView, llGroomBrideLoc, llGroomBrideLocEdit, llGroomBrideLocView,
            llProfessional, llProfessionalEdit, llProfessionalView, llFamily, llFamilyEdit, llFamilyView, llAbtFam, llAbt;

    ImageView imgProfPic,imgEditBasic, imgClearBasic, imgEditProfessional, imgClearProfessional, imgEditReligion, imgClearReligion, imgEditGroomBrideLoc, imgClearGroomBrideLoc, imgEditFam, imgClearFam, imgEditAbtFam, imgClearAbtFam, imgEditAbout, imgClearAbout;

    TextView tvProfFor, tvName, tvAge, tvMaritalStat, tvBodyType, tvPhysicalStat, tvHeight, tvWeight, tvComplexion, tvMTongue, tvFood, tvDrink, tvSmoke,
            tvReligion, tvCaste, tvSubCaste, tvRaasi, tvStar, tvGotra, tvDosh, tvBirthTime, tvBirthCountry, tvBirthState, tvBirthCity, tvChartStyle,
            tvCountry, tvState, tvCity, tvResidentStatus, tvParish, tvVillage,
            tvEdu, tvInstitute, tvEduDetail, tvOccu, tvOccuDetail, tvEmployedIn, tvAnnualIncome,
            tvFamValues, tvFamType, tvFamStatus, tvFamOrigin, tvFamLoc, tvFatherStat, tvMotherStat, tvNoOfBrothers, tvNoOfSisters;

    String intentReligion = "", intentCaste = "", intentCountry = "", intentState = "", intentCity = "", intentBirthCountry = "", intentBirthState = "", intentBirthCity = "",
            intentOccu = "", intentEdu = "", intentMTongue, intentPhysicalStat = "", intentMaritalStat = "", intentHeight = "", intentEating = "", intentDrinking = "", intentSmoking = "",
            intentWeight = "", intentBodyType = "", intentComplexion = "", intentProfileFor = "", intentRaasi = "", intentStar = "", intentGothra = "", intentDosham = "", intentChart = "",
            intentResident="",intentFamilyValue="",intentFamilyStat="",intentFamilyType="";
    SPCustProfile spCustProfile;
    RecyclerView rv_gallery;
    GalleryImagesAdapter adapterGallery;
    ArrayList<String> list_gallery=new ArrayList<String>();
    ArrayList<LinearLayout> list_ll=new ArrayList<LinearLayout>();
    String[] temp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_my_profile, container, false);

        Log.e("TabHome", "onCreateView");
        progressDialog = new ProgressDialog(getActivity());

        spCustProfile = new SPCustProfile(getActivity());


        rv_gallery = (RecyclerView) rootView.findViewById(R.id.rv_prof_gallery);
        rv_gallery.setHasFixedSize(true);
        rv_gallery.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



        etName = (EditText) rootView.findViewById(R.id.edt_name);
        etAbout = (EditText) rootView.findViewById(R.id.edt_about_you);
        etCollege = (EditText) rootView.findViewById(R.id.edt_college);
        etEduDetail = (EditText) rootView.findViewById(R.id.edt_eduInDetail);
        etOccuDetail = (EditText) rootView.findViewById(R.id.edt_occuInDetail);
        etEmployedIn = (EditText) rootView.findViewById(R.id.edt_employedIn);
        etBDate = (DatePickerEditText) rootView.findViewById(R.id.edt_bdate);
        etSubCaste = (EditText) rootView.findViewById(R.id.edt_subCaste);
        etIncome = (EditText) rootView.findViewById(R.id.edt_income);
        etFamOrigin = (EditText) rootView.findViewById(R.id.edt_fam_origin);
        etFamLoc = (EditText) rootView.findViewById(R.id.edt_fam_location);
        etFatherStat = (EditText) rootView.findViewById(R.id.edt_father_stat);
        etMotherStat = (EditText) rootView.findViewById(R.id.edt_mother_stat);
        etNoOfBrothers = (EditText) rootView.findViewById(R.id.edt_noOfBrother);
        etNoOfSisters = (EditText) rootView.findViewById(R.id.edt_noOfSister);
        etBDate.setManager(getActivity().getSupportFragmentManager());
        etBTime = (TimePickerEditText) rootView.findViewById(R.id.edt_timeOfBirth);
        etBTime.setManager(getActivity().getSupportFragmentManager());
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
        etAboutFam = (EditText) rootView.findViewById(R.id.edt_about_fam);
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


        tvProfFor = (TextView) rootView.findViewById(R.id.tv_profile_for);
        tvName = (TextView) rootView.findViewById(R.id.tv_name);
        tvAge = (TextView) rootView.findViewById(R.id.tv_age);
        tvBodyType = (TextView) rootView.findViewById(R.id.tv_body_type);
        tvPhysicalStat = (TextView) rootView.findViewById(R.id.tv_physical_stat);
        tvComplexion = (TextView) rootView.findViewById(R.id.tv_complexion);
        tvMaritalStat = (TextView) rootView.findViewById(R.id.tv_marital_stat);
        tvMTongue = (TextView) rootView.findViewById(R.id.tv_mtongue);
        tvFood = (TextView) rootView.findViewById(R.id.tv_food);
        tvDrink = (TextView) rootView.findViewById(R.id.tv_drink);
        tvSmoke = (TextView) rootView.findViewById(R.id.tv_smoke);
        tvHeight = (TextView) rootView.findViewById(R.id.tv_height);
        tvWeight = (TextView) rootView.findViewById(R.id.tv_weight);

        tvReligion = (TextView) rootView.findViewById(R.id.tv_religion);
        tvCaste = (TextView) rootView.findViewById(R.id.tv_caste);
        tvSubCaste = (TextView) rootView.findViewById(R.id.tv_subCaste);
        tvRaasi = (TextView) rootView.findViewById(R.id.tv_raasi);
        tvStar = (TextView) rootView.findViewById(R.id.tv_star);
        tvGotra = (TextView) rootView.findViewById(R.id.tv_gothra);
        tvDosh = (TextView) rootView.findViewById(R.id.tv_dosham);
        tvBirthTime = (TextView) rootView.findViewById(R.id.tv_timeOfBirth);
        tvBirthCountry = (TextView) rootView.findViewById(R.id.tv_countryOfBirth);
        tvBirthState = (TextView) rootView.findViewById(R.id.tv_stateOfBirth);
        tvBirthCity = (TextView) rootView.findViewById(R.id.tv_cityOfBirth);
        tvChartStyle = (TextView) rootView.findViewById(R.id.tv_chartStyle);

        tvCountry = (TextView) rootView.findViewById(R.id.tv_country);
        tvState = (TextView) rootView.findViewById(R.id.tv_state);
        tvCity = (TextView) rootView.findViewById(R.id.tv_city);
        tvResidentStatus = (TextView) rootView.findViewById(R.id.tv_resident_stat);
        tvParish = (TextView) rootView.findViewById(R.id.tv_parish);
        tvVillage = (TextView) rootView.findViewById(R.id.tv_village);

        tvEdu = (TextView) rootView.findViewById(R.id.tv_edu);
        tvInstitute = (TextView) rootView.findViewById(R.id.tv_college);
        tvEduDetail = (TextView) rootView.findViewById(R.id.tv_eduInDetail);
        tvOccu = (TextView) rootView.findViewById(R.id.tv_occu);
        tvOccuDetail = (TextView) rootView.findViewById(R.id.tv_occuInDetail);
        tvEmployedIn = (TextView) rootView.findViewById(R.id.tv_employdIn);
        tvAnnualIncome = (TextView) rootView.findViewById(R.id.tv_income);

        tvFamValues = (TextView) rootView.findViewById(R.id.tv_fam_values);
        tvFamType = (TextView) rootView.findViewById(R.id.tv_fam_type);
        tvFamStatus = (TextView) rootView.findViewById(R.id.tv_fam_stat);
        tvFamOrigin = (TextView) rootView.findViewById(R.id.tv_fam_origin);
        tvFamLoc = (TextView) rootView.findViewById(R.id.tv_fam_loc);
        tvFatherStat = (TextView) rootView.findViewById(R.id.tv_father_status);
        tvMotherStat = (TextView) rootView.findViewById(R.id.tv_mother_status);
        tvNoOfBrothers = (TextView) rootView.findViewById(R.id.tv_noOfBrother);
        tvNoOfSisters = (TextView) rootView.findViewById(R.id.tv_noOfSister);

        spProfFor = (MaterialSpinner) rootView.findViewById(R.id.sp_profile_for);
        spCaste = (MaterialSpinner) rootView.findViewById(R.id.sp_caste);
        spReligion = (MaterialSpinner) rootView.findViewById(R.id.sp_religion);
        spMTongue = (MaterialSpinner) rootView.findViewById(R.id.sp_mothertongue);

        spMaritalStat = (MaterialSpinner) rootView.findViewById(R.id.sp_marital_stat);
        spCountry = (MaterialSpinner) rootView.findViewById(R.id.sp_country);
        spState = (MaterialSpinner) rootView.findViewById(R.id.sp_state);
        spCity = (MaterialSpinner) rootView.findViewById(R.id.sp_city);
        spBirthCountry = (MaterialSpinner) rootView.findViewById(R.id.sp_CountryOfBirth);
        spBirthState = (MaterialSpinner) rootView.findViewById(R.id.sp_stateOfBirth);
        spBirthCity = (MaterialSpinner) rootView.findViewById(R.id.sp_cityOfBirth);
        spHeight = (MaterialSpinner) rootView.findViewById(R.id.sp_height);
        spWeight = (MaterialSpinner) rootView.findViewById(R.id.sp_weight);
        spBodyType = (MaterialSpinner) rootView.findViewById(R.id.sp_body_type);
        spComplexion = (MaterialSpinner) rootView.findViewById(R.id.sp_complexion);
        spPhysStat = (MaterialSpinner) rootView.findViewById(R.id.sp_physical_stat);
        spEdu = (MaterialSpinner) rootView.findViewById(R.id.sp_edu);
        spOccu = (MaterialSpinner) rootView.findViewById(R.id.sp_occu);
        spCurrency = (MaterialSpinner) rootView.findViewById(R.id.sp_currency);
        spFood = (MaterialSpinner) rootView.findViewById(R.id.sp_food);
        spDrink = (MaterialSpinner) rootView.findViewById(R.id.sp_drink);
        spSmoke = (MaterialSpinner) rootView.findViewById(R.id.sp_smoke);
        spFamStat = (MaterialSpinner) rootView.findViewById(R.id.sp_fam_stat);
        spFamType = (MaterialSpinner) rootView.findViewById(R.id.sp_fam_type);
        spFamValue = (MaterialSpinner) rootView.findViewById(R.id.sp_fam_value);

        spStar = (MaterialSpinner) rootView.findViewById(R.id.sp_star);
        spRassi = (MaterialSpinner) rootView.findViewById(R.id.sp_rassi);
        spDosh = (MaterialSpinner) rootView.findViewById(R.id.sp_dosham);
        spGotra = (MaterialSpinner) rootView.findViewById(R.id.sp_gothra);
        spResident = (MaterialSpinner) rootView.findViewById(R.id.sp_resident_stat);

        spCountry.setOnItemSelectedListener(this);
        spState.setOnItemSelectedListener(this);
        spCity.setOnItemSelectedListener(this);
        spBirthCountry.setOnItemSelectedListener(this);
        spBirthState.setOnItemSelectedListener(this);
        spBirthCity.setOnItemSelectedListener(this);
        spReligion.setOnItemSelectedListener(this);
        spDosh.setOnItemSelectedListener(this);


        btnAbt = (Button) rootView.findViewById(R.id.btnAboutGroomBride);
        btnBasic = (Button) rootView.findViewById(R.id.btnBasic);
        btnReligion = (Button) rootView.findViewById(R.id.btnReligion);
        btnProfessional = (Button) rootView.findViewById(R.id.btnProfessional);
        btnGroomBrideLoc = (Button) rootView.findViewById(R.id.btnGroomBrideLoc);
        btnFam = (Button) rootView.findViewById(R.id.btnFamily);
        btnAbtFam = (Button) rootView.findViewById(R.id.btnAboutFamily);

        btnAbt.setOnClickListener(this);
        btnBasic.setOnClickListener(this);
        btnReligion.setOnClickListener(this);
        btnProfessional.setOnClickListener(this);
        btnGroomBrideLoc.setOnClickListener(this);
        btnFam.setOnClickListener(this);
        btnAbtFam.setOnClickListener(this);


        initializeSpinnerLists();
        initializeSpinnerAdapters();

        getCountryList();
        getBirthCountryList();
        getHeightList();
        getWeightList();
        getEducationList();
        getOccupationList();
        getReligionList();
        getMTongueList();
        getStarList();
        getRaasiList();
        getGotraList();


        llBasic = (LinearLayout) rootView.findViewById(R.id.ll_basic);
        llBasicEdit = (LinearLayout) rootView.findViewById(R.id.ll_basic_edit);
        llBasicView = (LinearLayout) rootView.findViewById(R.id.ll_basic_view);

        llReligion = (LinearLayout) rootView.findViewById(R.id.ll_religion);
        llReligionEdit = (LinearLayout) rootView.findViewById(R.id.ll_religion_edit);
        llReligionView = (LinearLayout) rootView.findViewById(R.id.ll_religion_view);

        llGroomBrideLoc = (LinearLayout) rootView.findViewById(R.id.ll_groomBrideLoc);
        llGroomBrideLocEdit = (LinearLayout) rootView.findViewById(R.id.ll_groomBrideLoc_edit);
        llGroomBrideLocView = (LinearLayout) rootView.findViewById(R.id.ll_groomBrideLoc_view);

        llProfessional = (LinearLayout) rootView.findViewById(R.id.ll_professional);
        llProfessionalEdit = (LinearLayout) rootView.findViewById(R.id.ll_professional_edit);
        llProfessionalView = (LinearLayout) rootView.findViewById(R.id.ll_professional_view);

        llFamily = (LinearLayout) rootView.findViewById(R.id.ll_family);
        llFamilyEdit = (LinearLayout) rootView.findViewById(R.id.ll_family_edit);
        llFamilyView = (LinearLayout) rootView.findViewById(R.id.ll_family_view);


        llAbtFam = (LinearLayout) rootView.findViewById(R.id.ll_about_family);

        llAbt = (LinearLayout) rootView.findViewById(R.id.ll_about);

        list_ll.add(llAbt);
        list_ll.add(llBasic);
        list_ll.add(llReligion);
        list_ll.add(llGroomBrideLoc);
        list_ll.add(llProfessional);
        list_ll.add(llFamily);
        list_ll.add(llAbtFam);

        imgProfPic=(ImageView)rootView.findViewById(R.id.img_profile_pic);

        imgEditBasic = (ImageView) rootView.findViewById(R.id.img_editSave_basic);
        imgClearBasic = (ImageView) rootView.findViewById(R.id.img_clear_basic);

        imgEditReligion = (ImageView) rootView.findViewById(R.id.img_editSave_religion);
        imgClearReligion = (ImageView) rootView.findViewById(R.id.img_clear_religion);

        imgEditProfessional = (ImageView) rootView.findViewById(R.id.img_editSave_professional);
        imgClearProfessional = (ImageView) rootView.findViewById(R.id.img_clear_professional);

        imgEditGroomBrideLoc = (ImageView) rootView.findViewById(R.id.img_editSave_groomBrideLoc);
        imgClearGroomBrideLoc = (ImageView) rootView.findViewById(R.id.img_clear_groomBrideLoc);

        imgEditFam = (ImageView) rootView.findViewById(R.id.img_editSave_family);
        imgClearFam = (ImageView) rootView.findViewById(R.id.img_clear_family);

        imgEditAbtFam = (ImageView) rootView.findViewById(R.id.img_editSave_aboutFam);
        imgClearAbtFam = (ImageView) rootView.findViewById(R.id.img_clear_aboutFam);

        imgEditAbout = (ImageView) rootView.findViewById(R.id.img_editSave_about);
        imgClearAbout = (ImageView) rootView.findViewById(R.id.img_clear_about);


        imgProfPic.setOnClickListener(this);

        // Load the animation like this
        Animation animSlide = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide);
        animSlide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

// Start the animation like this
       // imgProfPic.startAnimation(animSlide);

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

        spCountry.setMultiline(true);

//        getProfile();
displayData();
        return rootView;

    }

    private void displayData() {


/*        list_gallery.add("assets/uploads/profile_pics/1521118041_Jellyfish.jpg");
        list_gallery.add("assets/uploads/profile_pics/1521118041_Jellyfish.jpg");
        list_gallery.add("assets/uploads/profile_pics/1521118041_Jellyfish.jpg");
        list_gallery.add("assets/uploads/profile_pics/1521118041_Jellyfish.jpg");
        list_gallery.add("assets/uploads/profile_pics/1521118041_Jellyfish.jpg");
        list_gallery.add("assets/uploads/profile_pics/1521118041_Jellyfish.jpg");*/

        Log.e("displayData","called");
        Log.e("List_highlight size",""+list_gallery.size());
        if(list_gallery.size()<=0 && spCustProfile.getGalleryPhotoPath()!=null) {
            temp = spCustProfile.getGalleryPhotoPath().toArray(new String[0]);
            for (int i = 0; i < temp.length; i++)
                list_gallery.add(temp[i]);
        }
        adapterGallery = new GalleryImagesAdapter(getActivity(), list_gallery);
        rv_gallery.setAdapter(adapterGallery);


        if (adapterGallery.getItemCount() == 0) {
            //          lyt_not_found.setVisibility(View.VISIBLE);
        } else {
//            lyt_not_found.setVisibility(View.GONE);
        }
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

    public void initializeSpinnerAdapters() {

        progressDialog.show();
        aaProfFor = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_prof_for);
        aaProfFor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaPhysStat = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_physical_stat);
        aaPhysStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaMaritalStat = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_marital_Stat);
        aaMaritalStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaBodyType = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_body_type);
        aaBodyType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaComplexion = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_complexion);
        aaComplexion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaPhysStat = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_physical_stat);
        aaPhysStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaEmployIn = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_employed_in);
        aaEmployIn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaFood = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_food);
        aaFood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaDrink = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_drink);
        aaDrink.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaSmoke = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_smoke);
        aaSmoke.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaFamStat = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_fam_stat);
        aaFamStat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaFamType = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_fam_type);
        aaFamType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aapFamValue = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_fam_value);
        aapFamValue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaDosh = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_dosh);
        aaDosh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aaResident = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_resident);
        aaResident.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spProfFor.setAdapter(aaProfFor);
        spMaritalStat.setAdapter(aaMaritalStat);
        spBodyType.setAdapter(aaBodyType);
        spComplexion.setAdapter(aaComplexion);
        spPhysStat.setAdapter(aaPhysStat);
        spDosh.setAdapter(aaDosh);
        spResident.setAdapter(aaResident);
        spFood.setAdapter(aaFood);
        spDrink.setAdapter(aaDrink);
        spSmoke.setAdapter(aaSmoke);
        spFamStat.setAdapter(aaFamStat);
        spFamType.setAdapter(aaFamType);
        spFamValue.setAdapter(aapFamValue);

        Log.e("intentProfileFor", intentProfileFor);
        if (!intentProfileFor.equalsIgnoreCase("")) {
/*
            tvProfFor.setText(list_prof_for.get(Integer.parseInt(intentProfileFor) - 1));
            spProfFor.setSelection(Integer.parseInt(intentProfileFor));*/

            tvProfFor.setText(intentProfileFor);
            spProfFor.setSelection(list_prof_for.indexOf(intentProfileFor)+1);
        }

        Log.e("intentBodyType", intentBodyType);
        if (!intentBodyType.equalsIgnoreCase("")) {

            tvBodyType.setText(list_body_type.get(Integer.parseInt(intentBodyType) - 1));
            spBodyType.setSelection(Integer.parseInt(intentBodyType));
        }
        Log.e("intentComplexion", intentComplexion);
        if (!intentComplexion.equalsIgnoreCase("")) {

            tvComplexion.setText(list_complexion.get(Integer.parseInt(intentComplexion) - 1));
            spComplexion.setSelection(Integer.parseInt(intentComplexion));
        }
        Log.e("intentMaritialStat", intentMaritalStat);
        if (!intentMaritalStat.equalsIgnoreCase("")) {

            tvMaritalStat.setText(list_marital_Stat.get(Integer.parseInt(intentMaritalStat) - 1));
            spMaritalStat.setSelection(Integer.parseInt(intentMaritalStat));
        }

        Log.e("intentPhysicalStat", intentPhysicalStat);
        if (!intentPhysicalStat.equalsIgnoreCase("")) {

            tvPhysicalStat.setText(list_physical_stat.get(Integer.parseInt(intentPhysicalStat) - 1));
            spPhysStat.setSelection(Integer.parseInt(intentPhysicalStat));
        }

        Log.e("intentEating", intentEating);
        if (!intentEating.equalsIgnoreCase("")) {

            tvFood.setText(list_food.get(Integer.parseInt(intentEating) - 1));
            spFood.setSelection(Integer.parseInt(intentEating));
        }

        Log.e("intentDrinking", intentDrinking);
        if (!intentDrinking.equalsIgnoreCase("")) {

            tvDrink.setText(list_drink.get(Integer.parseInt(intentDrinking) - 1));
            spDrink.setSelection(Integer.parseInt(intentDrinking));
        }

        Log.e("intentSmoking", intentSmoking);
        if (!intentSmoking.equalsIgnoreCase("")) {

            tvSmoke.setText(list_smoke.get(Integer.parseInt(intentSmoking) - 1));
            spSmoke.setSelection(Integer.parseInt(intentSmoking));
        }

        Log.e("intentDosham", intentDosham);
        if (!intentDosham.equalsIgnoreCase("")) {

            tvDosh.setText(list_dosh.get(Integer.parseInt(intentDosham) - 1));
            spDosh.setSelection(Integer.parseInt(intentDosham));
        }

        Log.e("intentResident", intentResident);
        if (!intentResident.equalsIgnoreCase("")) {

         /*   tvResidentStatus.setText(list_resident.get(Integer.parseInt(intentResident) - 1));
            spResident.setSelection(Integer.parseInt(intentResident));*/
            tvResidentStatus.setText(intentResident);
            spResident.setSelection(list_resident.indexOf(intentResident)+1);
        }

        Log.e("intentFamilyValue", intentFamilyValue);
        if (!intentFamilyValue.equalsIgnoreCase("")) {

            tvFamValues.setText(list_fam_value.get(Integer.parseInt(intentFamilyValue) - 1));
            spFamValue.setSelection(Integer.parseInt(intentFamilyValue));
        }

        Log.e("intentFamilyType", intentFamilyType);
        if (!intentFamilyType.equalsIgnoreCase("")) {

            tvFamType.setText(list_fam_type.get(Integer.parseInt(intentFamilyType) - 1));
            spFamType.setSelection(Integer.parseInt(intentFamilyType));
        }

        Log.e("intentFamilyStat", intentFamilyStat);
        if (!intentFamilyStat.equalsIgnoreCase("")) {

            tvFamStatus.setText(list_fam_stat.get(Integer.parseInt(intentFamilyStat) - 1));
            spFamStat.setSelection(Integer.parseInt(intentFamilyStat));
        }

        getProfile();

        progressDialog.dismiss();

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
if(view!=null) {
    ((TextView) view).setTextColor(Color.BLACK);
    ((TextView) view).setTextSize(16);
}
        switch (adapterView.getId()) {
            case R.id.sp_religion:
                Log.e("onIemSelectedListener ", "religion called");
                Log.e("position clicked", "" + spReligion.getSelectedItemPosition());
                if (spReligion.getSelectedItemPosition() > 0) {
                   /* Log.e("selected index",""+list_country.indexOf(spCountry.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_name());
                    Log.e("pojo element id",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_id());
                    Log.e("pojo element sortname",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_currency());
                   // Log.e("pojo element phone",list_pojo_country.get(list_country.indexOf(spCountry.getSelectedItem())).getCountry_extension());*/
                    getCasteList(list_pojo_religion.get(list_religion.indexOf(spReligion.getSelectedItem())).getReligion_id());
                }
                break;

            case R.id.sp_country:
                Log.e("onIemSelectedListener ", "country called");
                if (spCountry.getSelectedItemPosition() > 0) {
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
                if (spBirthCountry.getSelectedItemPosition() > 0) {
                    getBirthStateList(list_pojo_birth_country.get(list_birth_country.indexOf(spBirthCountry.getSelectedItem())).getCountry_id());
                }
                break;

            case R.id.sp_stateOfBirth:
                Log.e("onIemSelectedListener", "state called");
                if (spBirthState.getSelectedItemPosition() > 0) {
                    /*Log.e("selected index",""+list_state.indexOf(spState.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_name());
                    Log.e("pojo element id",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_id());
                    Log.e("pojo element countryid",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getCountry_id());*/
                    getBirthCityList(list_pojo_birth_state.get(list_birth_state.indexOf(spBirthState.getSelectedItem())).getState_id());
                }
                break;

            case R.id.sp_state:
                Log.e("onIemSelectedListener", "state called");
                if (spState.getSelectedItemPosition() != -1) {
                   /* Log.e("selected index",""+list_state.indexOf(spState.getSelectedItem()));
                    Log.e("pojo element name",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_name());
                    Log.e("pojo element id",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_id());
                    Log.e("pojo element countryid",list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getCountry_id());*/
                    getCityList(list_pojo_state.get(list_state.indexOf(spState.getSelectedItem())).getState_id());
                }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_profile_pic:
                if(!spCustProfile.getProfilePhotoPath().equalsIgnoreCase("")){
                Intent intent=new Intent(getActivity(), FullImageActivity.class);
                intent.putExtra("path",spCustProfile.getProfilePhotoPath());
                startActivity(intent);
                }
                break;

            case R.id.btnBasic:
                keepButtonOpen("basic");
               /* if (llBasic.getVisibility() == View.GONE)
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
                Log.e("editSaveBasic","onClick");
                if (llBasicView.getVisibility() == View.VISIBLE) {
                    llBasicEdit.setVisibility(View.VISIBLE);
                    llBasicView.setVisibility(View.INVISIBLE);
                    imgEditBasic.setImageResource(R.mipmap.saveicon);
                    imgClearBasic.setVisibility(View.VISIBLE);
                } else {
                    updateProfile("basic");
                }
                break;

            case R.id.btnReligion:
               /* if (llReligion.getVisibility() == View.GONE)
                    llReligion.setVisibility(View.VISIBLE);
                else
                    llReligion.setVisibility(View.GONE);*/
               keepButtonOpen("religion");
                break;

            case R.id.img_clear_religion:
                llReligionEdit.setVisibility(View.GONE);
                llReligionView.setVisibility(View.VISIBLE);
                imgEditReligion.setImageResource(R.mipmap.editicon2);
                imgClearReligion.setVisibility(View.INVISIBLE);
                break;

            case R.id.img_editSave_religion:
                Log.e("editSaveReligion","onClick");
                if (llReligionView.getVisibility() == View.VISIBLE) {
                    llReligionView.setVisibility(View.INVISIBLE);
                    llReligionEdit.setVisibility(View.VISIBLE);
                    imgEditReligion.setImageResource(R.mipmap.saveicon);
                    imgClearReligion.setVisibility(View.VISIBLE);
                } else {
                    updateProfile("religion");
                }
                break;


            case R.id.btnGroomBrideLoc:
                /*if (llGroomBrideLoc.getVisibility() == View.GONE)
                    llGroomBrideLoc.setVisibility(View.VISIBLE);
                else
                    llGroomBrideLoc.setVisibility(View.GONE);*/
                keepButtonOpen("location");
                break;

            case R.id.img_clear_groomBrideLoc:
                llGroomBrideLocEdit.setVisibility(View.GONE);
                llGroomBrideLocView.setVisibility(View.VISIBLE);
                imgEditGroomBrideLoc.setImageResource(R.mipmap.editicon2);
                imgClearGroomBrideLoc.setVisibility(View.INVISIBLE);
                break;

            case R.id.img_editSave_groomBrideLoc:
                if (llGroomBrideLocView.getVisibility() == View.VISIBLE) {
                    llGroomBrideLocView.setVisibility(View.INVISIBLE);
                    llGroomBrideLocEdit.setVisibility(View.VISIBLE);
                    imgEditGroomBrideLoc.setImageResource(R.mipmap.saveicon);
                    imgClearGroomBrideLoc.setVisibility(View.VISIBLE);
                } else {
                    updateProfile("groomBrideLoc");
                }
                break;

            case R.id.btnProfessional:
               /* if (llProfessional.getVisibility() == View.GONE)
                    llProfessional.setVisibility(View.VISIBLE);
                else
                    llProfessional.setVisibility(View.GONE);*/
               keepButtonOpen("professional");
                break;

            case R.id.img_clear_professional:
                llProfessionalEdit.setVisibility(View.GONE);
                llProfessionalView.setVisibility(View.VISIBLE);
                imgEditProfessional.setImageResource(R.mipmap.editicon2);
                imgClearProfessional.setVisibility(View.INVISIBLE);
                break;

            case R.id.img_editSave_professional:
                if (llProfessionalView.getVisibility() == View.VISIBLE) {
                    llProfessionalView.setVisibility(View.INVISIBLE);
                    llProfessionalEdit.setVisibility(View.VISIBLE);
                    imgEditProfessional.setImageResource(R.mipmap.saveicon);
                    imgClearProfessional.setVisibility(View.VISIBLE);
                } else {
                    updateProfile("professional");
                }
                break;

            case R.id.btnFamily:
           /*     if (llFamily.getVisibility() == View.GONE)
                    llFamily.setVisibility(View.VISIBLE);
                else
                    llFamily.setVisibility(View.GONE);*/
           keepButtonOpen("Family");
                break;

            case R.id.img_clear_family:
                llFamilyEdit.setVisibility(View.GONE);
                llFamilyView.setVisibility(View.VISIBLE);
                imgEditFam.setImageResource(R.mipmap.editicon2);
                imgClearFam.setVisibility(View.INVISIBLE);
                break;

            case R.id.img_editSave_family:
                if (llFamilyView.getVisibility() == View.VISIBLE) {
                    llFamilyView.setVisibility(View.INVISIBLE);
                    llFamilyEdit.setVisibility(View.VISIBLE);
                    imgEditFam.setImageResource(R.mipmap.saveicon);
                    imgClearFam.setVisibility(View.VISIBLE);
                } else {
                    updateProfile("family_details");
                }

                break;

            case R.id.btnAboutFamily:
           /*     if (llAbtFam.getVisibility() == View.GONE)
                    llAbtFam.setVisibility(View.VISIBLE);
                else
                    llAbtFam.setVisibility(View.GONE);*/
           keepButtonOpen("aboutFam");
                break;

            case R.id.img_clear_aboutFam:
                // llFamilyEdit.setVisibility(View.GONE);
                //llFamilyView.setVisibility(View.VISIBLE);
                etAboutFam.setEnabled(false);
                imgEditAbtFam.setImageResource(R.mipmap.editicon2);
                imgClearAbtFam.setVisibility(View.INVISIBLE);
                break;

            case R.id.img_editSave_aboutFam:
                if (!etAboutFam.isEnabled()) {
                    etAboutFam.setEnabled(true);
                    /*llFamilyView.setVisibility(View.INVISIBLE);
                    llFamilyEdit.setVisibility(View.VISIBLE);*/
                    imgEditAbtFam.setImageResource(R.mipmap.saveicon);
                    imgClearAbtFam.setVisibility(View.VISIBLE);
                } else {
                    updateProfile("family_about");
                }
                break;

            case R.id.btnAboutGroomBride:
               /* if (llAbt.getVisibility() == View.GONE)
                    llAbt.setVisibility(View.VISIBLE);
                else
                    llAbt.setVisibility(View.GONE);*/
               keepButtonOpen("about");
                break;

            case R.id.img_clear_about:
                //llA.setVisibility(View.GONE);
                //llFamilyView.setVisibility(View.VISIBLE);
                etAbout.setEnabled(false);
                imgEditAbout.setImageResource(R.mipmap.editicon2);
                imgClearAbout.setVisibility(View.INVISIBLE);
                break;

            case R.id.img_editSave_about:
                if (!etAbout.isEnabled()) {
                    etAbout.setEnabled(true);
                    //  llFamilyEdit.setVisibility(View.VISIBLE);
                    imgEditAbout.setImageResource(R.mipmap.saveicon);
                    imgClearAbout.setVisibility(View.VISIBLE);
                } else {
                    updateAbout();
                }
                break;
        }

    }


    public void getReligionList() {

        progressDialog.show();
        if (list_religion != null)
            list_religion.clear();
        if (list_pojo_religion != null)
            list_pojo_religion.clear();

        getReligionInterface getResponse = APIClient.getClient().create(getReligionInterface.class);
        Call<ParentPojoReligion> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoReligion>() {
            @Override
            public void onResponse(Call<ParentPojoReligion> call, Response<ParentPojoReligion> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoReligion parentPojoReligion = response.body();
                if (parentPojoReligion != null) {
                    if (parentPojoReligion.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoReligion.getObjReligion().size());

                        LinkedHashMap<String, ChildPojoReligion> resultMap = parentPojoReligion.getObjReligion();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_religion.add(resultMap.get(key));
                            list_religion.add(resultMap.get(key).getReligion_name());
                        }
                        Log.e("List Size", "" + list_religion.size());
                        ArrayAdapter aaReligion = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_religion);
                        aaReligion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spReligion.setAdapter(aaReligion);

                        if (!intentReligion.equalsIgnoreCase("")) {
                            tvReligion.setText(intentReligion);
                            spReligion.setSelection(list_religion.indexOf(intentReligion) + 1);
                        }
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

    public void getCasteList(String religion_id) {

        progressDialog.show();
        if (list_caste != null)
            list_caste.clear();
        if(list_pojo_caste!=null)
            list_pojo_caste.clear();

        Log.e("Religion", religion_id);
        getCasteInterface getResponse = APIClient.getClient().create(getCasteInterface.class);
        Call<ParentPojoCaste> call = getResponse.doGetListResources(religion_id);
        call.enqueue(new Callback<ParentPojoCaste>() {
            @Override
            public void onResponse(Call<ParentPojoCaste> call, Response<ParentPojoCaste> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoCaste parentPojoCaste = response.body();
                if (parentPojoCaste != null) {
                    if (parentPojoCaste.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoCaste.getObjCaste().size());

                        LinkedHashMap<String, ChildPojoCaste> resultMap = parentPojoCaste.getObjCaste();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();

                            list_pojo_caste.add(resultMap.get(key));
                            list_caste.add(resultMap.get(key).getCaste_name());
                        }
                        Log.e("cast List Size", "" + list_caste.size());
                        aaCaste = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_caste);
                        aaCaste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCaste.setAdapter(aaCaste);
                        if (!intentCaste.equalsIgnoreCase("")) {
                            tvCaste.setText(intentCaste);
                            spCaste.setSelection(list_caste.indexOf(intentCaste) + 1);
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

    public void getStarList() {
        progressDialog.show();
        if (list_star != null)
            list_star.clear();


        getStarInterface getResponse = APIClient.getClient().create(getStarInterface.class);
        Call<ParentPojoStar> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoStar>() {
            @Override
            public void onResponse(Call<ParentPojoStar> call, Response<ParentPojoStar> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoStar parentPojoStar = response.body();
                if (parentPojoStar != null) {
                    if (parentPojoStar.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("starobjsize", "" + parentPojoStar.getObjStar().size());

                        LinkedHashMap<String, ChildPojoStar> resultMap = parentPojoStar.getObjStar();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();

                            list_pojo_star.add(resultMap.get(key));
                            list_star.add(resultMap.get(key).getStar_name());
                        }
                        Log.e("star List Size", "" + list_star.size());
                        aaStar = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_star);
                        aaStar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spStar.setAdapter(aaStar);

                        if (!intentStar.equalsIgnoreCase("")) {
                            tvStar.setText(intentStar);
                            spStar.setSelection(list_star.indexOf(intentStar) + 1);
                        }

                        progressDialog.dismiss();
                    }
                }

            }

            @Override
            public void onFailure(Call<ParentPojoStar> call, Throwable t) {
                Log.e("star thrwable", "" + t);
                progressDialog.dismiss();
            }

        });

    }

    public void getGotraList() {
        progressDialog.show();
        if (list_gotra != null)
            list_gotra.clear();


        getGotraInterface getResponse = APIClient.getClient().create(getGotraInterface.class);
        Call<ParentPojoGotra> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoGotra>() {
            @Override
            public void onResponse(Call<ParentPojoGotra> call, Response<ParentPojoGotra> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoGotra parentPojoGotra = response.body();
                if (parentPojoGotra != null) {
                    if (parentPojoGotra.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("starobjsize", "" + parentPojoGotra.getObjGotra().size());

                        LinkedHashMap<String, ChildPojoGotra> resultMap = parentPojoGotra.getObjGotra();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();

                            list_pojo_gotra.add(resultMap.get(key));
                            list_gotra.add(resultMap.get(key).getGothra_name());
                        }
                        Log.e("star List Size", "" + list_gotra.size());
                        aaGotra = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_gotra);
                        aaGotra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spGotra.setAdapter(aaGotra);


                        if (!intentGothra.equalsIgnoreCase("")) {
                            tvGotra.setText(intentGothra);
                            spGotra.setSelection(list_star.indexOf(intentGothra) + 1);
                        }
                        progressDialog.dismiss();
                    }
                }

            }

            @Override
            public void onFailure(Call<ParentPojoGotra> call, Throwable t) {
                Log.e("star thrwable", "" + t);
                progressDialog.dismiss();
            }

        });

    }

    public void getRaasiList() {
        progressDialog.show();
        if (list_raasi != null)
            list_raasi.clear();


        getRaasiInterface getResponse = APIClient.getClient().create(getRaasiInterface.class);
        Call<ParentPojoRaasi> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoRaasi>() {
            @Override
            public void onResponse(Call<ParentPojoRaasi> call, Response<ParentPojoRaasi> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoRaasi parentPojoRaasi = response.body();
                if (parentPojoRaasi != null) {
                    if (parentPojoRaasi.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoRaasi.getObjRaasi().size());

                        LinkedHashMap<String, ChildPojoRaasi> resultMap = parentPojoRaasi.getObjRaasi();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();

                            list_pojo_raasi.add(resultMap.get(key));
                            list_raasi.add(resultMap.get(key).getRaasi_name());
                        }
                        Log.e("star List Size", "" + list_star.size());
                        aaRassi = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_raasi);
                        aaRassi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spRassi.setAdapter(aaRassi);

                        if (!intentRaasi.equalsIgnoreCase("")) {
                            tvRaasi.setText(intentRaasi);
                            spRassi.setSelection(list_raasi.indexOf(intentRaasi) + 1);
                        }
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

    public void getMTongueList() {
        progressDialog.show();
        if (list_mtongue != null)
            list_mtongue.clear();


        getMTongueInterface getResponse = APIClient.getClient().create(getMTongueInterface.class);
        Call<ParentPojoMTongue> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoMTongue>() {
            @Override
            public void onResponse(Call<ParentPojoMTongue> call, Response<ParentPojoMTongue> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoMTongue parentPojoMTongue = response.body();
                if (parentPojoMTongue != null) {
                    if (parentPojoMTongue.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoMTongue.getObjMTongue().size());

                        LinkedHashMap<String, ChildPojoMTongue> resultMap = parentPojoMTongue.getObjMTongue();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();

                            list_pojo_mtongue.add(resultMap.get(key));
                            list_mtongue.add(resultMap.get(key).getMother_tongue_name());
                        }
                        Log.e("List Size", "" + list_mtongue.size());
                        aaMTongue = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_mtongue);
                        aaMTongue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spMTongue.setAdapter(aaMTongue);

                        if (intentMTongue != null) {
                            tvMTongue.setText(intentMTongue);
                            spMTongue.setSelection(list_mtongue.indexOf(intentMTongue) + 1);
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

    public void getCountryList() {
        progressDialog.show();
        if (list_country != null)
            list_country.clear();
        if (list_pojo_country != null)
            list_pojo_country.clear();
        APIInterface getResponse = APIClient.getClient().create(APIInterface.class);
        Call<ParentModelCountry> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentModelCountry>() {
            @Override
            public void onResponse(Call<ParentModelCountry> call, Response<ParentModelCountry> response) {

                Log.e("Inside", "onResponse");
                Log.e("response body", response.body().status);
                ParentModelCountry parentModelCountry = response.body();
                if (parentModelCountry != null) {
                    if (parentModelCountry.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentModelCountry.getObjCountry().size());

                        LinkedHashMap<String, ChildModelCountry> resultMap = parentModelCountry.getObjCountry();
                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_country.add(resultMap.get(key));
                            list_country.add(resultMap.get(key).getCountry_name());
                        }

                        //Collections.sort(list_country);
                        Log.e("List Size", "" + list_country.size());
                        aaCountry = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_country);
                        aaCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCountry.setAdapter(aaCountry);


                        if (!intentCountry.equalsIgnoreCase("")) {
                            tvCountry.setText(intentCountry);
                            spCountry.setSelection(list_country.indexOf(intentCountry) + 1);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ParentModelCountry> call, Throwable t) {

                Log.e("Inside", "onFailure");
                Log.e("Throwable", "" + t);
                progressDialog.dismiss();
            }
        });
    }

    public void getStateList(String country_id) {

        progressDialog.show();
        if (list_state != null)
            list_state.clear();
        if (list_pojo_state != null)
            list_pojo_state.clear();
        Log.e("Country", country_id);
        getStateInterface getResponse = APIClient.getClient().create(getStateInterface.class);
        Call<ParentPojoState> call = getResponse.doGetListResources(country_id);
        call.enqueue(new Callback<ParentPojoState>() {
            @Override
            public void onResponse(Call<ParentPojoState> call, Response<ParentPojoState> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoState parentPojoState = response.body();
                if (parentPojoState != null) {
                    if (parentPojoState.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoState.getObjState().size());

                        LinkedHashMap<String, ChildPojoState> resultMap = parentPojoState.getObjState();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_state.add(resultMap.get(key));
                            list_state.add(resultMap.get(key).getState_name());
                        }
                        Log.e("List Size", "" + list_state.size());
                        aaState = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_state);
                        aaState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spState.setAdapter(aaState);

                        if (!intentState.equalsIgnoreCase("")) {
                            tvState.setText(intentState);
                            spState.setSelection(list_state.indexOf(intentState) + 1);
                        }

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

    public void getCityList(String state_id) {

        progressDialog.show();
        if (list_city != null)
            list_city.clear();
        if (list_pojo_city != null)
            list_pojo_city.clear();
        Log.e("State", state_id);
        getCityInterface getResponse = APIClient.getClient().create(getCityInterface.class);
        Call<ParentPojoCity> call = getResponse.doGetListResources(state_id);
        call.enqueue(new Callback<ParentPojoCity>() {
            @Override
            public void onResponse(Call<ParentPojoCity> call, Response<ParentPojoCity> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoCity parentPojoCity = response.body();
                if (parentPojoCity != null) {
                    if (parentPojoCity.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoCity.getObjCity().size());

                        LinkedHashMap<String, ChildPojoCity> resultMap = parentPojoCity.getObjCity();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_city.add(resultMap.get(key));
                            list_city.add(resultMap.get(key).getCity_name());
                        }
                        Log.e("List Size", "" + list_city.size());
                        aaCity = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_city);
                        aaCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCity.setAdapter(aaCity);


                        if (!intentCity.equalsIgnoreCase("")) {
                            tvCity.setText(intentCity);
                            spCity.setSelection(list_city.indexOf(intentCity) + 1);
                        }

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

    public void getBirthCountryList() {
        progressDialog.show();
        if (list_birth_country != null)
            list_birth_country.clear();
        if (list_pojo_birth_country != null)
            list_pojo_birth_country.clear();
        APIInterface getResponse = APIClient.getClient().create(APIInterface.class);
        Call<ParentModelCountry> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentModelCountry>() {
            @Override
            public void onResponse(Call<ParentModelCountry> call, Response<ParentModelCountry> response) {

                Log.e("Inside", "onResponse");
                Log.e("response body", response.body().status);
                ParentModelCountry parentModelCountry = response.body();
                if (parentModelCountry != null) {
                    if (parentModelCountry.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentModelCountry.getObjCountry().size());

                        LinkedHashMap<String, ChildModelCountry> resultMap = parentModelCountry.getObjCountry();
                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_birth_country.add(resultMap.get(key));
                            list_birth_country.add(resultMap.get(key).getCountry_name());
                        }

                        //Collections.sort(list_country);
                        Log.e("birthcountryList Size", "" + list_birth_country.size());
                        aaCountry = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_birth_country);
                        aaCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spBirthCountry.setAdapter(aaCountry);


                        if (!intentBirthCountry.equalsIgnoreCase("")) {
                            Log.e("intentBCountry", intentBirthCountry);
                            Log.e("indexintentBirthCountry", "" + list_birth_country.indexOf(intentBirthCountry));
                            spBirthCountry.setSelection(list_birth_country.indexOf(intentBirthCountry) + 1);
                            tvBirthCountry.setText(intentBirthCountry);
                        }
                        else
                            tvBirthCountry.setText("");

                        /*if (!intentBirthCountry.equalsIgnoreCase("")) {
                            for (int i = 0; i < list_pojo_birth_country.size(); i++) {
                                if (list_pojo_country.get(i).getCountry_id().equalsIgnoreCase(intentBirthCountry)) {
                                    tvBirthCountry.setText(list_pojo_birth_country.get(i).getCountry_name());
                                    spBirthCountry.setSelection(list_birth_country.indexOf(list_pojo_birth_country.get(i).getCountry_name()) + 1);
                                    break;
                                }
                            }
                        } else
                            spBirthCountry.setSelection(0);
*/
                    }
                }
            }


            @Override
            public void onFailure(Call<ParentModelCountry> call, Throwable t) {

                Log.e("Inside", "onFailure");
                Log.e("Throwable", "" + t);
                progressDialog.dismiss();
            }
        });
    }

    public void getBirthStateList(String country_id) {

        progressDialog.show();
        if (list_birth_state != null)
            list_birth_state.clear();
        if (list_pojo_birth_state != null)
            list_pojo_birth_state.clear();
        Log.e("Country", country_id);
        getStateInterface getResponse = APIClient.getClient().create(getStateInterface.class);
        Call<ParentPojoState> call = getResponse.doGetListResources(country_id);
        call.enqueue(new Callback<ParentPojoState>() {
            @Override
            public void onResponse(Call<ParentPojoState> call, Response<ParentPojoState> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoState parentPojoState = response.body();
                if (parentPojoState != null) {
                    if (parentPojoState.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoState.getObjState().size());

                        LinkedHashMap<String, ChildPojoState> resultMap = parentPojoState.getObjState();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_birth_state.add(resultMap.get(key));
                            list_birth_state.add(resultMap.get(key).getState_name());
                        }
                        Log.e("List Size", "" + list_birth_state.size());
                        aaState = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_birth_state);
                        aaState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spBirthState.setAdapter(aaState);

                        if (!intentBirthState.equalsIgnoreCase("")){

                            spBirthState.setSelection(list_birth_state.indexOf(intentBirthState) + 1);
                            tvBirthState.setText(intentBirthState);
                        }
                        else
                            tvBirthState.setText("");


                       /* if (!intentBirthState.equalsIgnoreCase("")) {
                            for (int i = 0; i < list_pojo_birth_state.size(); i++) {
                                if (list_pojo_birth_state.get(i).getState_id().equalsIgnoreCase(intentBirthState)) {
                                    tvBirthState.setText(list_pojo_birth_state.get(i).getState_name());
                                    spBirthState.setSelection(list_birth_state.indexOf(list_pojo_birth_state.get(i).getState_name()) + 1);
                                    break;
                                }
                            }
                        } else
                            spBirthState.setSelection(0);*/

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

    public void getBirthCityList(String state_id) {

        progressDialog.show();
        if (list_birth_city != null)
            list_birth_city.clear();
        if (list_pojo_birth_city != null)
            list_pojo_birth_city.clear();
        Log.e("State", state_id);
        getCityInterface getResponse = APIClient.getClient().create(getCityInterface.class);
        Call<ParentPojoCity> call = getResponse.doGetListResources(state_id);
        call.enqueue(new Callback<ParentPojoCity>() {
            @Override
            public void onResponse(Call<ParentPojoCity> call, Response<ParentPojoCity> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoCity parentPojoCity = response.body();
                if (parentPojoCity != null) {
                    if (parentPojoCity.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoCity.getObjCity().size());

                        LinkedHashMap<String, ChildPojoCity> resultMap = parentPojoCity.getObjCity();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_birth_city.add(resultMap.get(key));
                            list_birth_city.add(resultMap.get(key).getCity_name());
                        }
                        Log.e("List Size", "" + list_city.size());
                        aaCity = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_birth_city);
                        aaCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spBirthCity.setAdapter(aaCity);


                        if (!intentBirthCity.equalsIgnoreCase("")){

                            tvBirthCity.setText(intentBirthCity);
                            spBirthCity.setSelection(list_birth_city.indexOf(intentBirthCity) + 1);
                        }

/*
                      if (!intentBirthCity.equalsIgnoreCase("")) {
                            for (int i = 0; i < list_pojo_birth_city.size(); i++) {
                                if (list_pojo_birth_city.get(i).getCity_id().equalsIgnoreCase(intentBirthCity)) {
                                    tvBirthCity.setText(list_pojo_birth_city.get(i).getCity_name());
                                    spBirthCity.setSelection(list_birth_city.indexOf(list_pojo_birth_city.get(i).getCity_name()) + 1);
                                    break;
                                }
                            }
                        } else
                          spBirthCity.setSelection(0);*/



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

    public void getHeightList() {

        progressDialog.show();
        if (list_height != null)
            list_height.clear();
        if (list_pojo_height != null)
            list_pojo_height.clear();

        getHeightInterface getResponse = APIClient.getClient().create(getHeightInterface.class);
        Call<ParentPojoHeight> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoHeight>() {
            @Override
            public void onResponse(Call<ParentPojoHeight> call, Response<ParentPojoHeight> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoHeight parentPojoHeight = response.body();
                if (parentPojoHeight != null) {
                    if (parentPojoHeight.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoHeight.getObjHeight().size());

                        LinkedHashMap<String, ChildPojoHeight> resultMap = parentPojoHeight.getObjHeight();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_height.add(resultMap.get(key));
                            list_height.add(resultMap.get(key).getHeight());
                        }
                        Log.e("List Size", "" + list_height.size());
                        aaHeight = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_height);
                        aaHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spHeight.setAdapter(aaHeight);


                        Log.e("intentHeightFrom",intentHeight);
                        if(intentHeight.equalsIgnoreCase("")||intentHeight.equalsIgnoreCase("0")){

                            tvHeight.setText("");
                        }
                        else{

                           /* Log.e("heightFrom",list_pojo_height.get(Integer.parseInt(intentHeight)).getHeight());
                            tvHeight.setText(list_pojo_height.get(Integer.parseInt(intentHeight)-1).getHeight());
                            spHeight.setSelection(list_height.indexOf(list_pojo_height.get(Integer.parseInt(intentHeight)).getHeight()));*/

                           // Log.e("heightFrom",list_pojo_height.get(Integer.parseInt(intentHeight)).getHeight());
                            tvHeight.setText(intentHeight);
                            spHeight.setSelection(list_height.indexOf(intentHeight)+1);
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

    public void getWeightList() {

        progressDialog.show();
        if (list_weight != null)
            list_weight.clear();
        if (list_pojo_weight != null)
            list_pojo_weight.clear();

        getWeightInterface getResponse = APIClient.getClient().create(getWeightInterface.class);
        Call<ParentPojoWeight> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoWeight>() {
            @Override
            public void onResponse(Call<ParentPojoWeight> call, Response<ParentPojoWeight> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoWeight parentPojoWeight = response.body();
                if (parentPojoWeight != null) {
                    if (parentPojoWeight.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoWeight.getObjWeight().size());

                        LinkedHashMap<String, ChildPojoWeight> resultMap = parentPojoWeight.getObjWeight();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_weight.add(resultMap.get(key));
                            list_weight.add(resultMap.get(key).getWeight());
                        }
                        Log.e("List Size", "" + list_weight.size());
                        aaWeight = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_weight);
                        aaWeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spWeight.setAdapter(aaWeight);

                        Log.e("intentWeight",intentWeight);
                        if(intentWeight.equalsIgnoreCase("")||intentWeight.equalsIgnoreCase("0")){

                            tvWeight.setText("");
                        }
                        else{

                           /* Log.e("heightFrom",list_pojo_weight.get(Integer.parseInt(intentWeight)).getWeight());
                            tvWeight.setText(list_pojo_weight.get(Integer.parseInt(intentWeight)-1).getWeight());
                            spWeight.setSelection(list_weight.indexOf(list_pojo_weight.get(Integer.parseInt(intentWeight)).getWeight()));*/

                           // Log.e("heightFrom",list_pojo_weight.get(Integer.parseInt(intentWeight)).getWeight());
                            tvWeight.setText(intentWeight);
                            spWeight.setSelection(list_weight.indexOf(intentWeight)+1);
                        }

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

    public void getEducationList() {

        progressDialog.show();
        if (list_edu != null)
            list_edu.clear();
        if (list_pojo_edu != null)
            list_pojo_edu.clear();

        getEducationInterface getResponse = APIClient.getClient().create(getEducationInterface.class);
        Call<ParentPojoEducation> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoEducation>() {
            @Override
            public void onResponse(Call<ParentPojoEducation> call, Response<ParentPojoEducation> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoEducation parentPojoEducation = response.body();
                if (parentPojoEducation != null) {
                    if (parentPojoEducation.getStatus().equalsIgnoreCase("1")) {
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

                        if(!intentEdu.equalsIgnoreCase("")) {
                          //  Log.e("intentedufunction",intentEdu);
                         //Log.e("indexIntentEdu",""+list_edu.indexOf(intentEdu));
                            tvEdu.setText(intentEdu);
                            spEdu.setSelection(list_edu.indexOf(intentEdu)+1);
                        }

                    /*    if (!intentEdu.equalsIgnoreCase("")) {
                            for (int i = 0; i < list_pojo_edu.size(); i++) {
                                if (list_pojo_edu.get(i).getEducation_id().equalsIgnoreCase(intentEdu)) {
                                    tvEdu.setText(list_pojo_edu.get(i).getEducation());
                                    spEdu.setSelection(list_edu.indexOf(list_pojo_edu.get(i).getEducation()) + 1);
                                    break;
                                }
                            }
                        }*/
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

    public void getOccupationList() {

        progressDialog.show();
        if (list_occu != null)
            list_occu.clear();
        if (list_pojo_occu != null)
            list_pojo_occu.clear();

        getOccupationInterface getResponse = APIClient.getClient().create(getOccupationInterface.class);
        Call<ParentPojoOccupation> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoOccupation>() {
            @Override
            public void onResponse(Call<ParentPojoOccupation> call, Response<ParentPojoOccupation> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoOccupation parentPojoOccupation = response.body();
                if (parentPojoOccupation != null) {
                    if (parentPojoOccupation.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoOccupation.getObjOccupation().size());

                        LinkedHashMap<String, ChildPojoOccupation> resultMap = parentPojoOccupation.getObjOccupation();

                        Iterator<String> keys = resultMap.keySet().iterator();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            list_pojo_occu.add(resultMap.get(key));
                            list_occu.add(resultMap.get(key).getOccupation());
                        }
                        Log.e("List Size", "" + list_occu.size());
                        aaOccu = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_occu);
                        aaOccu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spOccu.setAdapter(aaOccu);

                        if(intentOccu!=null) {
                            //Log.e("intentoccufunction",intentOccu);
                            spOccu.setSelection(list_occu.indexOf(intentOccu)+1);
                            tvOccu.setText(intentOccu);
                           // Log.e("indexIntentOccu",""+list_occu.indexOf(intentOccu));
                        }


                        /*if (!intentOccu.equalsIgnoreCase("")) {

                            for (int i = 0; i < list_pojo_occu.size(); i++) {
                                if (list_pojo_occu.get(i).getOccupation_id().equalsIgnoreCase(intentOccu)) {
                                    tvOccu.setText(list_pojo_occu.get(i).getOccupation());
                                    spOccu.setSelection(list_occu.indexOf(list_pojo_occu.get(i).getOccupation()) + 1);
                                    break;
                                }
                            }
                        }*/
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

    /*    public void getChartList(){

            progressDialog.show();
            if(list_chart!=null)
                list_height.clear();
            if(list_pojo_height!=null)
                list_pojo_height.clear();

            getHeightInterface getResponse = APIClient.getClient().create(getHeightInterface.class);
            Call<ParentPojoHeight> call = getResponse.doGetListResources();
            call.enqueue(new Callback<ParentPojoHeight>() {
                @Override
                public void onResponse(Call<ParentPojoHeight> call, Response<ParentPojoHeight> response) {

                    Log.e("Inside","onResponse");
                   *//* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*//*
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

    }*/
    public void updateProfile(final String section) {

        progressDialog.show();


        final updateProfileInterface getResponse = APIClient.getClient().create(updateProfileInterface.class);
        Call<CommonParentPojo> call = null;
        if (section.equalsIgnoreCase("Basic")) {

            Calendar d = etBDate.getDate();
            String s = d.get(d.YEAR) + "-" + (d.get(d.MONTH) + 1) + "-" + d.get(d.DAY_OF_MONTH);
            String age = String.valueOf(2018 - d.get(d.YEAR));

            call = getResponse.updateBasic(spCustProfile.getUser_id(),
                    spProfFor.getSelectedItemPosition()==0 ? "0" :  spProfFor.getSelectedItem().toString(), etName.getText().toString(), age,
                    spBodyType.getSelectedItemPosition()==0 ? "0" : String.valueOf(spBodyType.getSelectedItemPosition()),
                    spPhysStat.getSelectedItemPosition()==0 ? "0" : String.valueOf(spPhysStat.getSelectedItemPosition()),
                    spComplexion.getSelectedItemPosition()==0 ? "0" : String.valueOf(spComplexion.getSelectedItemPosition()), s,
                    spMaritalStat.getSelectedItemPosition()==0 ? "0" :String.valueOf(spMaritalStat.getSelectedItemPosition()),
                    spHeight.getSelectedItemPosition()==0 ? "0" :list_pojo_height.get(list_height.indexOf(spHeight.getSelectedItem().toString())).getHeight_id(),
                    spWeight.getSelectedItemPosition()==0 ? "0" :list_pojo_weight.get(list_weight.indexOf(spWeight.getSelectedItem().toString())).getWeight_id(),
                    spMTongue.getSelectedItemPosition()==0 ? "0" :list_pojo_mtongue.get(list_mtongue.indexOf(spMTongue.getSelectedItem().toString())).getMother_tongue_id()
                    , spFood.getSelectedItemPosition()==0 ? "0" :String.valueOf(spFood.getSelectedItemPosition()),
                    spDrink.getSelectedItemPosition()==0 ? "0" :String.valueOf(spDrink.getSelectedItemPosition()),
                    spSmoke.getSelectedItemPosition()==0 ? "0" :String.valueOf(spSmoke.getSelectedItemPosition()));

        } else if (section.equalsIgnoreCase("religion")) {

            Calendar t = etBTime.getTime();
            String tm;
            if(t!=null)
            tm = t.get(t.HOUR) + ":" + t.get(t.MINUTE);
            else
                tm=etBTime.getText().toString();

            call = getResponse.updateReligion(spCustProfile.getUser_id(),
                    spReligion.getSelectedItemPosition()==0?"0":list_pojo_religion.get(list_religion.indexOf(spReligion.getSelectedItem().toString())).getReligion_id(),
                    spCaste.getSelectedItemPosition()==0?"0":list_pojo_caste.get(list_caste.indexOf(spCaste.getSelectedItem().toString())).getCaste_id(), etSubCaste.getText().toString(),
                    spRassi.getSelectedItemPosition()==0?"0":list_pojo_raasi.get(list_raasi.indexOf(spRassi.getSelectedItem().toString())).getRaasi_id(),
                    spStar.getSelectedItemPosition()==0?"0":list_pojo_star.get(list_star.indexOf(spStar.getSelectedItem().toString())).getStar_id(),
                    spGotra.getSelectedItemPosition()==0?"0":list_pojo_gotra.get(list_gotra.indexOf(spGotra.getSelectedItem().toString())).getGothra_id(),
                    spDosh.getSelectedItemPosition()==0?"0":String.valueOf(spDosh.getSelectedItemPosition()), tm,
                    spBirthCountry.getSelectedItemPosition()==0?"0":list_pojo_birth_country.get(list_birth_country.indexOf(spBirthCountry.getItemAtPosition(spBirthCountry.getSelectedItemPosition() - 1).toString())).getCountry_id(),
                    spBirthState.getSelectedItemPosition()==0?"0":list_pojo_birth_state.get(list_birth_state.indexOf(spBirthState.getSelectedItem().toString())).getState_id(),
                    spBirthCity.getSelectedItemPosition()==0?"0":list_pojo_birth_city.get(list_birth_city.indexOf(spBirthCity.getSelectedItem().toString())).getCity_id(), "");
        } else if (section.equalsIgnoreCase("groomBrideLoc")) {

            call = getResponse.updateGroomBrideLoc(spCustProfile.getUser_id(), list_pojo_country.get(list_country.indexOf(spCountry.getItemAtPosition(spCountry.getSelectedItemPosition() - 1).toString())).getCountry_id(),
                    list_pojo_state.get(list_state.indexOf(spState.getSelectedItem().toString())).getState_id(),
                    list_pojo_city.get(list_city.indexOf(spCity.getSelectedItem().toString())).getCity_id(),
                    spResident.getSelectedItemPosition()==0?"0":spResident.getSelectedItem().toString(),
                    "", "");
        } else if (section.equalsIgnoreCase("professional")) {

            call = getResponse.updateProfessional(spCustProfile.getUser_id(), list_pojo_edu.get(list_edu.indexOf(spEdu.getItemAtPosition(spEdu.getSelectedItemPosition() - 1).toString())).getEducation_id(), etCollege.getText().toString(),
                    etEduDetail.getText().toString(), list_pojo_occu.get(list_occu.indexOf(spOccu.getItemAtPosition(spOccu.getSelectedItemPosition() - 1).toString())).getOccupation_id(),
                    etOccuDetail.getText().toString(), etEmployedIn.getText().toString(), etIncome.getText().toString());

        } else if (section.equalsIgnoreCase("family_details")) {

            call = getResponse.updateFamilyDetails(spCustProfile.getUser_id(), String.valueOf(spFamValue.getSelectedItemPosition()), String.valueOf(spFamType.getSelectedItemPosition()),
                    String.valueOf(spFamStat.getSelectedItemPosition()), etFamOrigin.getText().toString(), etFamLoc.getText().toString(),
                    etFatherStat.getText().toString(), etMotherStat.getText().toString(), etNoOfBrothers.getText().toString(), etNoOfSisters.getText().toString());
        } else if (section.equalsIgnoreCase("family_about")) {

            call = getResponse.updateAboutFam(spCustProfile.getUser_id(), etAboutFam.getText().toString());
        } else if (section.equalsIgnoreCase("about_you")) {

            call = getResponse.updateAboutFam(spCustProfile.getUser_id(), etAbout.getText().toString());
        }
        call.enqueue(new Callback<CommonParentPojo>() {
            @Override
            public void onResponse(Call<CommonParentPojo> call, Response<CommonParentPojo> response) {

                Log.e("Inside", "onResponse");

                CommonParentPojo commonParentPojo = response.body();
                Log.e("response religion", String.valueOf(response.body()));
                if (commonParentPojo != null) {
                    if (commonParentPojo.getStatus().equalsIgnoreCase("1")) {

                        showToast("Updated Successfully!");
                        getProfile();

                        if (section.equalsIgnoreCase("Basic"))
                            imgClearBasic.performClick();
                        else if (section.equalsIgnoreCase("religion"))
                            imgClearReligion.performClick();
                        else if (section.equalsIgnoreCase("groomBrideLoc"))
                            imgClearGroomBrideLoc.performClick();
                        else if (section.equalsIgnoreCase("professional"))
                            imgClearProfessional.performClick();
                        else if (section.equalsIgnoreCase("family_details"))
                            imgClearFam.performClick();
                        else if (section.equalsIgnoreCase("family_about"))
                            imgClearAbtFam.performClick();
                        else if (section.equalsIgnoreCase("about_you"))
                            imgClearAbout.performClick();

                    }
                }

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                Log.e("throwable", "" + t);
                progressDialog.dismiss();
            }
        });

    }

    public void updateAbout() {

        progressDialog.show();


        final updateAboutInterface getResponse = APIClient.getClient().create(updateAboutInterface.class);
        Call<CommonParentPojo> call = getResponse.updateAboutYou(spCustProfile.getUser_id(), etAbout.getText().toString());

        call.enqueue(new Callback<CommonParentPojo>() {
            @Override
            public void onResponse(Call<CommonParentPojo> call, Response<CommonParentPojo> response) {

                Log.e("Inside", "onResponse");

                CommonParentPojo commonParentPojo = response.body();
                Log.e("response religion", String.valueOf(response.body()));
                if (commonParentPojo != null) {
                    if (commonParentPojo.getStatus().equalsIgnoreCase("1")) {

                        showToast("Updated Successfully!");
                        getProfile();
                        imgClearAbout.performClick();
                    }
                }

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                Log.e("throwable", "" + t);
                progressDialog.dismiss();
            }
        });

    }

    public void getProfile() {

        progressDialog.show();
        if (mListItem != null)
            mListItem.clear();

        getProfileInterface getResponse = APIClient.getClient().create(getProfileInterface.class);
        Call<ParentPojoProfile> call = getResponse.doGetListResources(spCustProfile.getMatrimonyId());
        call.enqueue(new Callback<ParentPojoProfile>() {
            @Override
            public void onResponse(Call<ParentPojoProfile> call, Response<ParentPojoProfile> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoProfile parentPojoProfile = response.body();
                if (parentPojoProfile != null) {
                    if (parentPojoProfile.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoProfile.getObjProfile().size());
                        mListItem = parentPojoProfile.getObjProfile();
                        spCustProfile.setProfilePhotoPath(mListItem.get(0).getProfile_photo());
                        spCustProfile.setGalleryPhotoPath(mListItem.get(0).getGallery());
                        spCustProfile.setGender(mListItem.get(0).getGender());
                        setBasic();
                        setReligion();
                        setGroomBrideLoc();
                        setProfessional();
                       setFamilyDetails();
                    }
                } else
                    Log.e("parentpojotabwhome", "null");
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ParentPojoProfile> call, Throwable t) {

                Log.e("throwable", "" + t);
                progressDialog.dismiss();
            }
        });

    }

    public void setBasic() {

        btnAbt.setText("A few words about "+spCustProfile.getName());
        etAbout.setText(mListItem.get(0).getAbout_you());
        /*tvProfFor.setText(mListItem.get(0).getProfile_for());
        Log.e("profforsize",""+list_prof_for.size());
        Log.e("indexproffor",""+list_prof_for.indexOf(mListItem.get(0).getProfile_for()));
        spProfFor.setSelection(list_prof_for.indexOf(mListItem.get(0).getProfile_for()+1));*/
       /* tvProfFor.setText(list_prof_for.get(Integer.parseInt(mListItem.get(0).getProfile_for())-1));
        spProfFor.setSelection(Integer.parseInt(mListItem.get(0).getProfile_for()));*/
        Log.e("profile_photo_myProfile","http://applex360.in/Deshpande-family/Matrimony-web/" + spCustProfile.getProfilePhotoPath());

        if(!spCustProfile.getProfilePhotoPath().equalsIgnoreCase("")) {
            Picasso.Builder builder = new Picasso.Builder(getActivity());
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    exception.printStackTrace();
                    Log.e("Exception", "" + exception);
                    imgProfPic.setImageResource(R.mipmap.userprofile);
                }
            });
            builder.build()
                    .load("http://applex360.in/Deshpande-family/Matrimony-web/" + spCustProfile.getProfilePhotoPath())
                    .placeholder(R.mipmap.userprofile)
                    .into(imgProfPic);

        }
   /*     Picasso.with(getActivity().getApplicationContext())
                .load("http://applex360.in/Deshpande-family/Matrimony-web/"+spCustProfile.getProfilePhotoPath())
                .placeholder(R.mipmap.userprofile)
                .error(R.mipmap.addicon)
                .fit()
                .into(imgProfPic, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });*/

        tvName.setText(mListItem.get(0).getProfile_name());
        etName.setText(mListItem.get(0).getProfile_name());
        tvAge.setText(mListItem.get(0).getAge());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = sdf.parse(mListItem.get(0).getDob());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            etBDate.setDate(cal);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (mListItem.get(0).getProfile_for() != null) {
            intentProfileFor = mListItem.get(0).getProfile_for();
            if (intentProfileFor.equalsIgnoreCase("0") || intentProfileFor.equalsIgnoreCase("")) {
                tvProfFor.setText("");
            } else {

                tvProfFor.setText(intentProfileFor);
                spProfFor.setSelection(list_prof_for.indexOf(intentProfileFor)+1);
               /* Log.e("listPhysicalStatSize", "" + list_prof_for.size());
                Log.e("textPStat", "" + list_prof_for.get(Integer.parseInt(intentProfileFor) - 1));
                if (list_prof_for.size() > Integer.parseInt(intentProfileFor) - 1) {
                    tvProfFor.setText(list_prof_for.get(Integer.parseInt(intentProfileFor) - 1));
                    spProfFor.setSelection(Integer.parseInt(intentProfileFor));
                }*/
            }
        }

        else if(mListItem.get(0).getProfile_for()==null) tvProfFor.setText("");

        if (mListItem.get(0).getComplexion() != null) {
            intentComplexion = mListItem.get(0).getComplexion();
            if (intentComplexion.equalsIgnoreCase("0") || intentComplexion.equalsIgnoreCase("")) {
                tvComplexion.setText("");
            } else {
                //intentPhysicalStat = mListItem.get(0).getPhysical_status();
                Log.e("listComplexionSize", "" + list_complexion.size());
                Log.e("textPStat", "" + list_complexion.get(Integer.parseInt(intentComplexion) - 1));
                if (list_complexion.size() > Integer.parseInt(intentComplexion) - 1) {
                    tvComplexion.setText(list_complexion.get(Integer.parseInt(intentComplexion) - 1));
                    spComplexion.setSelection(Integer.parseInt(intentComplexion));
                }
            }
        }
        else if (mListItem.get(0).getComplexion() == null) tvComplexion.setText("");

        if (mListItem.get(0).getBody_type() != null) {
            intentBodyType = mListItem.get(0).getComplexion();
            if (intentBodyType.equalsIgnoreCase("0") || intentBodyType.equalsIgnoreCase("")) {
                tvBodyType.setText("");
            } else {
                //intentPhysicalStat = mListItem.get(0).getPhysical_status();
                Log.e("list_body_typenSize", "" + list_body_type.size());
                Log.e("textPStat", "" + list_body_type.get(Integer.parseInt(intentBodyType) - 1));
                if (list_body_type.size() > Integer.parseInt(intentBodyType) - 1) {
                    tvBodyType.setText(list_body_type.get(Integer.parseInt(intentBodyType) - 1));
                    spBodyType.setSelection(Integer.parseInt(intentBodyType));
                }
            }
        }
        else if (mListItem.get(0).getBody_type() == null) tvBodyType.setText("");

        if (mListItem.get(0).getPhysical_status() != null) {
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
       else if (mListItem.get(0).getPhysical_status() == null) tvPhysicalStat.setText("");

        if (mListItem.get(0).getMaritial_status() != null) {
            intentMaritalStat = mListItem.get(0).getMaritial_status();
            if (intentMaritalStat.equalsIgnoreCase("0") || intentMaritalStat.equalsIgnoreCase("")) {
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
        else if (mListItem.get(0).getPhysical_status() == null) tvPhysicalStat.setText("");
/*
        if(mListItem.get(0).getHeight()!=null) {
            intentHeight = mListItem.get(0).getHeight();
            if (intentHeight.equalsIgnoreCase("0")||intentHeight.equalsIgnoreCase("")) {
                tvHeight.setText("");
            } else {
                // intentHeightFrom = mListItem.get(0).getHeight_from_id();
                if (list_height.size() > Integer.parseInt(intentHeight)) {
                    Log.e("heightFrom", list_pojo_height.get(Integer.parseInt(intentHeight)).getHeight());
                    tvHeight.setText(list_pojo_height.get(Integer.parseInt(intentHeight) - 1).getHeight());
                    spHeight.setSelection(list_height.indexOf(list_pojo_height.get(Integer.parseInt(intentHeight)).getHeight()));
                }
            }
        }

        if(mListItem.get(0).getWeight()!=null) {
            intentWeight = mListItem.get(0).getWeight();
            if (intentWeight.equalsIgnoreCase("0")||intentWeight.equalsIgnoreCase("")) {
                tvWeight.setText("");
            } else {
                // intentHeightFrom = mListItem.get(0).getHeight_from_id();
                if (list_weight.size() > Integer.parseInt(intentWeight)) {
                    Log.e("heightFrom", list_pojo_weight.get(Integer.parseInt(intentWeight)).getWeight());
                    tvWeight.setText(list_pojo_weight.get(Integer.parseInt(intentWeight) - 1).getWeight());
                    spWeight.setSelection(list_weight.indexOf(list_pojo_weight.get(Integer.parseInt(intentWeight)).getWeight()));
                }
            }
        }*/


        if(mListItem.get(0).getHeight()!=null) {
            intentHeight = mListItem.get(0).getHeight();
            if (intentHeight.equalsIgnoreCase("0")||intentHeight.equalsIgnoreCase("")) {
                tvHeight.setText("");
            } else {
                // intentHeightFrom = mListItem.get(0).getHeight_from_id();
                if (list_height.contains(intentHeight)) {
                   // Log.e("heightFrom", list_pojo_height.get(Integer.parseInt(intentHeight)).getHeight());
                    tvHeight.setText(intentHeight);
                    spHeight.setSelection(list_height.indexOf(intentHeight)+1);
                }
            }
        }
       else if(mListItem.get(0).getHeight()==null) tvHeight.setText("");

        if(mListItem.get(0).getWeight()!=null) {
            intentWeight = mListItem.get(0).getWeight();
            if (intentWeight.equalsIgnoreCase("0")||intentWeight.equalsIgnoreCase("")) {
                tvWeight.setText("");
            } else {
                // intentHeightFrom = mListItem.get(0).getHeight_from_id();
                if (list_weight.contains(intentWeight)) {
                  //  Log.e("heightFrom", list_pojo_weight.get(Integer.parseInt(intentWeight)).getWeight());
                    tvWeight.setText(intentWeight);
                    spWeight.setSelection(list_weight.indexOf(intentWeight)+1);
                }
            }
        }
        if(mListItem.get(0).getWeight()==null) tvWeight.setText("");

        if (mListItem.get(0).getEating() != null) {
            intentEating = mListItem.get(0).getEating();
            if (intentEating.equalsIgnoreCase("0") || intentEating.equalsIgnoreCase("")) {
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
        else if (mListItem.get(0).getEating() == null) tvFood.setText("");

        if (mListItem.get(0).getDrinking() != null) {
            intentDrinking = mListItem.get(0).getDrinking();
            if (intentDrinking.equalsIgnoreCase("0") || intentDrinking.equalsIgnoreCase("")) {
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
else if (mListItem.get(0).getDrinking() == null) tvDrink.setText("");

        if (mListItem.get(0).getSmoking() != null) {
            intentSmoking = mListItem.get(0).getSmoking();
            if (intentSmoking.equalsIgnoreCase("0") || intentSmoking.equalsIgnoreCase("")) {
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
        else if (mListItem.get(0).getSmoking() == null) tvSmoke.setText("");

        if (mListItem.get(0).getMother_language() != null) {
            intentMTongue = mListItem.get(0).getMother_language();
            if (intentMTongue.equalsIgnoreCase("0") || intentMTongue.equalsIgnoreCase("")) {
                tvMTongue.setText("");
            } else {
              /*  intentMTongue = mListItem.get(0).getMother_language();
                for (int i = 0; i < list_pojo_mtongue.size(); i++) {
                    if (list_pojo_mtongue.get(i).getMother_tongue_id().equalsIgnoreCase(intentMTongue)) {
                        tvMTongue.setText(list_pojo_mtongue.get(i).getMother_tongue_name());
                        spMTongue.setSelection(list_mtongue.indexOf(list_pojo_mtongue.get(i).getMother_tongue_name()) + 1);
                        break;

                    }
                }*/
                if (list_mtongue.contains(intentMTongue)) {
                    // Log.e("heightFrom", list_pojo_weight.get(Integer.parseInt(intentWeight)).getWeight());
                    tvMTongue.setText(intentMTongue);
                    spMTongue.setSelection(list_mtongue.indexOf(intentWeight));
                }
            }
        }
        else if (mListItem.get(0).getMother_language() == null) tvMTongue.setText("");


       /* tvPhysicalStat.setText(list_physical_stat.get(Integer.parseInt(mListItem.get(0).getPhysical_status())-1));
        spPhysStat.setSelection(Integer.parseInt(mListItem.get(0).getPhysical_status()));
        tvMaritalStat.setText(list_marital_Stat.get(Integer.parseInt(mListItem.get(0).getMaritial_status())-1));
        spMaritalStat.setSelection(Integer.parseInt(mListItem.get(0).getMaritial_status()));
        tvHeight.setText(mListItem.get(0).getHeight());
        tvWeight.setText(mListItem.get(0).getWeight());
        spWeight.setSelection(list_weight.indexOf(mListItem.get(0).getWeight()));
        spHeight.setSelection(list_height.indexOf(mListItem.get(0).getHeight()));
         tvFood.setText(list_food.get(Integer.parseInt(mListItem.get(0).getEating())-1));
        spFood.setSelection(Integer.parseInt(mListItem.get(0).getEating()));
        tvDrink.setText(list_drink.get(Integer.parseInt(mListItem.get(0).getDrinking())-1));
        spDrink.setSelection(Integer.parseInt(mListItem.get(0).getDrinking()));
        tvSmoke.setText(list_smoke.get(Integer.parseInt(mListItem.get(0).getSmoking())-1));
        spSmoke.setSelection(Integer.parseInt(mListItem.get(0).getSmoking()));
         tvComplexion.setText(list_complexion.get(Integer.parseInt(mListItem.get(0).getComplexion())-1));
        spComplexion.setSelection(Integer.parseInt(mListItem.get(0).getComplexion()));
        tvMTongue.setText(mListItem.get(0).getMother_language());
        intentMTongue=mListItem.get(0).getMother_language();
        spMTongue.setSelection(list_mtongue.indexOf(mListItem.get(0).getMother_language())+1);

        tvBodyType.setText(list_body_type.get(Integer.parseInt(mListItem.get(0).getBody_type())-1));
        spBodyType.setSelection(Integer.parseInt(mListItem.get(0).getBody_type()));*/


    }

    public void setReligion() {

        if (mListItem.get(0).getReligion() != null) {
            intentReligion = mListItem.get(0).getReligion();
            if (intentReligion.equalsIgnoreCase("0") || intentReligion.equalsIgnoreCase("")) {
                tvReligion.setText("");
            } else {
//                intentReligion = mListItem.get(0).getReligion();
                    if (list_religion.contains(intentReligion)) {
                        tvReligion.setText(intentReligion);
                        spReligion.setSelection(list_religion.indexOf(intentReligion)+1);
                    }
            }
        }
        else if(mListItem.get(0).getReligion() == null) tvReligion.setText("");

            if (mListItem.get(0).getCaste() != null) {
            intentCaste = mListItem.get(0).getCaste();
            if (intentCaste.equalsIgnoreCase("0") || intentCaste.equalsIgnoreCase("")) {
                tvCaste.setText("");
            } else {

//                intentCaste = mListItem.get(0).getCaste();

                    if (list_caste.contains(intentCaste)) {
                        tvCaste.setText(intentCaste);
                        spCaste.setSelection(list_caste.indexOf(intentCaste) + 1);
                    }

            }
        }
       else if (mListItem.get(0).getCaste() == null) tvCaste.setText("");

            tvSubCaste.setText(mListItem.get(0).getSub_caste());
        etSubCaste.setText(mListItem.get(0).getSub_caste());


        if (mListItem.get(0).getRaasi() != null) {
            intentRaasi = mListItem.get(0).getRaasi();
            if (intentRaasi.equalsIgnoreCase("0") || intentRaasi.equalsIgnoreCase("")) {
                tvRaasi.setText("");
            } else {

//                intentCaste = mListItem.get(0).getCaste();

                    if (list_raasi.contains(intentRaasi)) {
                        tvRaasi.setText(intentRaasi);
                        spRassi.setSelection(list_raasi.indexOf(intentRaasi)+1);

                }
            }
        }
       else if (mListItem.get(0).getRaasi() == null) tvRaasi.setText("");

        if (mListItem.get(0).getStar() != null) {
            intentStar = mListItem.get(0).getStar();
            if (intentStar.equalsIgnoreCase("0") || intentStar.equalsIgnoreCase("")) {
                tvStar.setText("");
            } else {

//                intentCaste = mListItem.get(0).getCaste();
                    if (list_star.contains(intentStar)) {
                        tvStar.setText(intentStar);
                        spStar.setSelection(list_star.indexOf(intentStar)+1);
                }
            }
        }
        else if (mListItem.get(0).getStar() == null) tvStar.setText("");

        if (mListItem.get(0).getGothra() != null) {
            intentGothra = mListItem.get(0).getGothra();
            if (intentGothra.equalsIgnoreCase("0") || intentGothra.equalsIgnoreCase("")) {
                tvGotra.setText("");
            } else {

//                intentCaste = mListItem.get(0).getCaste();

                    if (list_gotra.contains(intentGothra)) {
                        tvGotra.setText(intentGothra);
                        spGotra.setSelection(list_gotra.indexOf(intentGothra) + 1);

                }
            }
        }
        else  if (mListItem.get(0).getGothra() == null) tvGotra.setText("");

        if (mListItem.get(0).getHave_dosham() != null) {
            intentDosham = mListItem.get(0).getHave_dosham();
            if (intentDosham.equalsIgnoreCase("0") || intentDosham.equalsIgnoreCase("")) {
                tvDosh.setText("");
            } else {
                //intentPhysicalStat = mListItem.get(0).getPhysical_status();
                Log.e("listPhysicalStatSize", "" + list_dosh.size());
                Log.e("textPStat", "" + list_dosh.get(Integer.parseInt(intentDosham) - 1));
                if (list_dosh.size() > Integer.parseInt(intentDosham) - 1) {
                    tvDosh.setText(list_dosh.get(Integer.parseInt(intentDosham) - 1));
                    spDosh.setSelection(Integer.parseInt(intentDosham));
                }
            }
        }
        else if (mListItem.get(0).getHave_dosham() == null) tvDosh.setText("");

       /* if(mListItem.get(0).getBirth_country()!=null) {
            intentBirthCountry = mListItem.get(0).getBirth_country();
            if (intentBirthCountry.equalsIgnoreCase("0")||intentBirthCountry.equalsIgnoreCase("")) {
                tvBirthCountry.setText("");
            } else {

                //intentCountry = mListItem.get(0).getCountry();
                for (int i = 0; i < list_pojo_birth_country.size(); i++) {
                    if (list_pojo_birth_country.get(i).getCountry_id().equalsIgnoreCase(intentBirthCountry)) {
                        tvBirthCountry.setText(list_pojo_birth_country.get(i).getCountry_name());
                        spBirthCountry.setSelection(list_birth_country.indexOf(list_pojo_birth_country.get(i).getCountry_name()) + 1);
                        break;
                    }
                }
            }
        }
        else if(mListItem.get(0).getBirth_country()==null) tvBirthCountry.setText("");*/

        if(mListItem.get(0).getBirth_country()!=null) {
            intentBirthCountry = mListItem.get(0).getBirth_country();
            if (intentBirthCountry.equalsIgnoreCase("0")||intentBirthCountry.equalsIgnoreCase("")) {
                tvBirthCountry.setText("");
            } else {

                //intentCountry = mListItem.get(0).getCountry();
                    if (list_birth_country.contains(intentBirthCountry)) {
                        tvBirthCountry.setText(intentBirthCountry);
                        spBirthCountry.setSelection(list_birth_country.indexOf(intentBirthCountry)+1);
                        //break;
                    }

            }
        }
        else if(mListItem.get(0).getBirth_country()==null) tvBirthCountry.setText("");

        /*if(mListItem.get(0).getBirth_state()!=null) {
            intentBirthState = mListItem.get(0).getBirth_state();
            if (intentBirthState.equalsIgnoreCase("0")||intentBirthState.equalsIgnoreCase("")) {
                tvBirthState.setText("");
            } else {
                Log.e("state", mListItem.get(0).getState());
                //  tvState.setText(mListItem.get(0).getState());
                //  intentState = mListItem.get(0).getState();

                for (int i = 0; i < list_pojo_birth_state.size(); i++) {
                    if (list_pojo_birth_state.get(i).getState_id().equalsIgnoreCase(intentBirthState)) {
                        tvBirthState.setText(list_pojo_birth_state.get(i).getState_name());
                        spBirthState.setSelection(list_birth_state.indexOf(list_pojo_birth_state.get(i).getState_name()) + 1);
                        break;
                    }
                }
            }
        }
        else if(mListItem.get(0).getBirth_state()==null) tvBirthState.setText("");
*/

        if(mListItem.get(0).getBirth_state()!=null) {
            intentBirthState = mListItem.get(0).getBirth_state();
            if (intentBirthState.equalsIgnoreCase("0")||intentBirthState.equalsIgnoreCase("")) {
                tvBirthState.setText("");
            } else {

                //intentCountry = mListItem.get(0).getCountry();
                if (list_birth_state.contains(intentBirthState)) {
                    tvBirthState.setText(intentBirthState);
                    spBirthState.setSelection(list_birth_state.indexOf(intentBirthState)+1);
                    //break;
                }

            }
        }
        else if(mListItem.get(0).getBirth_state()==null) tvBirthState.setText("");

   /*
        if(mListItem.get(0).getBirth_city()!=null) {
            intentBirthCity = mListItem.get(0).getBirth_city();
            if (intentBirthCity.equalsIgnoreCase("0")||intentBirthCity.equalsIgnoreCase("")) {
                tvBirthCity.setText("");
            } else {
                Log.e("state", mListItem.get(0).getState());
                //  tvState.setText(mListItem.get(0).getState());
                //  intentState = mListItem.get(0).getState();

                for (int i = 0; i < list_pojo_birth_city.size(); i++) {
                    if (list_pojo_birth_city.get(i).getCity_id().equalsIgnoreCase(intentBirthCity)) {
                        tvBirthCity.setText(list_pojo_birth_city.get(i).getCity_name());
                        spBirthCity.setSelection(list_birth_city.indexOf(list_pojo_birth_city.get(i).getCity_name()) + 1);
                        break;
                    }
                }
            }
        }
        else if(mListItem.get(0).getBirth_city()==null) tvBirthCity.setText("");*/

        if(mListItem.get(0).getBirth_city()!=null) {
            intentBirthCity = mListItem.get(0).getBirth_city();
            if (intentBirthCity.equalsIgnoreCase("0")||intentBirthCity.equalsIgnoreCase("")) {
                tvBirthCity.setText("");
            } else {

                //intentCountry = mListItem.get(0).getCountry();
                if (list_birth_city.contains(intentBirthCity)) {
                    tvBirthCity.setText(intentBirthCity);
                    spBirthCity.setSelection(list_birth_city.indexOf(intentBirthCity)+1);
                    //break;
                }

            }
        }
        else if(mListItem.get(0).getBirth_city()==null) tvBirthCity.setText("");
        etBTime.setText(mListItem.get(0).getBirth_time());
        tvBirthTime.setText(mListItem.get(0).getBirth_time());
        /*  tvReligion.setText(mListItem.get(0).getReligion());
        Log.e("indexr", String.valueOf(list_religion.indexOf(mListItem.get(0).getReligion())));
        spReligion.setSelection(list_religion.indexOf(mListItem.get(0).getReligion())+1);
        intentReligion=mListItem.get(0).getReligion();
        spReligion.setSelection(list_religion.indexOf(mListItem.get(0).getReligion())+1);
        Log.e("indexc", String.valueOf(list_caste.indexOf(mListItem.get(0).getCaste())));
        tvCaste.setText(mListItem.get(0).getCaste());
        spCaste.setSelection(list_caste.indexOf(mListItem.get(0).getCaste())+1);
        intentCaste=mListItem.get(0).getCaste();
          if(mListItem.get(0).getRaasi()!=null) {
            tvRaasi.setText(mListItem.get(0).getRaasi());
            spRassi.setSelection(list_raasi.indexOf(mListItem.get(0).getRaasi())+1);
        }


        if(mListItem.get(0).getStar()!=null) {
            tvStar.setText(mListItem.get(0).getStar());
            spStar.setSelection(list_star.indexOf(mListItem.get(0).getStar())+1);
        }
        if(mListItem.get(0).getGothra()!=null) {
            tvGotra.setText(mListItem.get(0).getGothra());
            spGotra.setSelection(list_gotra.indexOf(mListItem.get(0).getGothra())+1);
        }
        if(mListItem.get(0).getHave_dosham()!=null) {
            tvDosh.setText(list_dosh.get(Integer.parseInt(mListItem.get(0).getHave_dosham()) - 1));
            spDosh.setSelection(Integer.parseInt(mListItem.get(0).getHave_dosham()));
            tvBirthTime.setText(mListItem.get(0).getBirth_time());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = null;
            try {
                date = sdf.parse(mListItem.get(0).getBirth_time());
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                etBTime.setTime(cal);

            } catch (ParseException e) {
                e.printStackTrace();
            }


        if (mListItem.get(0).getBirth_country() != null) {
            Log.e("Countrybirth", mListItem.get(0).getBirth_country());
            //Log.e("indexcountry",""+list_country.indexOf(mListItem.get(0).getBirth_country()));
            tvBirthCountry.setText(mListItem.get(0).getBirth_country());
            intentBirthCountry = mListItem.get(0).getBirth_country();
            spBirthCountry.setSelection(list_birth_country.indexOf(intentBirthCountry) + 1);
        }
        if (mListItem.get(0).getBirth_state() != null) {
            Log.e("statebirth", mListItem.get(0).getBirth_state());
            tvBirthState.setText(mListItem.get(0).getBirth_state());
            intentBirthState = mListItem.get(0).getBirth_state();
            spBirthState.setSelection(list_birth_state.indexOf(intentBirthState) + 1);
        }
        if (mListItem.get(0).getBirth_city() != null) {
            Log.e("citybirth", mListItem.get(0).getBirth_city());
            tvBirthCity.setText(mListItem.get(0).getBirth_city());
            intentBirthCity = mListItem.get(0).getBirth_city();
            spBirthCity.setSelection(list_birth_city.indexOf(intentBirthCity) + 1);
        }
        tvChartStyle.setText((mListItem.get(0).getChart()));
        //spChart.setSelection(Integer.parseInt(mListItem.get(0).getSmoking()));*/



}

    public void setGroomBrideLoc(){

        if(spCustProfile.getGender().equalsIgnoreCase("Male"))
            btnGroomBrideLoc.setText("Groom's Location");
        else if(spCustProfile.getGender().equalsIgnoreCase("Female"))
            btnGroomBrideLoc.setText("Bride's Location");

        if(mListItem.get(0).getCountry()!=null) {
            intentCountry = mListItem.get(0).getCountry();
            if (intentCountry.equalsIgnoreCase("0")||intentCountry.equalsIgnoreCase("")) {
                tvCountry.setText("");
            } else {

                //intentCountry = mListItem.get(0).getCountry();

                    if (list_country.contains(intentCountry)) {
                        tvCountry.setText(intentCountry);
                        spCountry.setSelection(list_country.indexOf(intentCountry)+1);

                }
            }
        }
         else if(mListItem.get(0).getCountry()==null) tvCountry.setText("");

        if(mListItem.get(0).getState()!=null) {
            intentState = mListItem.get(0).getState();
            if (intentState.equalsIgnoreCase("0")||intentState.equalsIgnoreCase("")) {
                tvState.setText("");
            } else {
                Log.e("state", mListItem.get(0).getState());
                //  tvState.setText(mListItem.get(0).getState());
                //  intentState = mListItem.get(0).getState();


                    if (list_state.contains(intentState)) {
                        tvState.setText(intentState);
                        spState.setSelection(list_state.indexOf(intentState) + 1);

                }
            }
        }
        else if(mListItem.get(0).getState()==null) tvState.setText("");

        if(mListItem.get(0).getCity()!=null) {
            intentCity = mListItem.get(0).getCity();
            if (intentCity.equalsIgnoreCase("0")||intentCity.equalsIgnoreCase("")) {
                tvCity.setText("");
            } else {
                Log.e("state", mListItem.get(0).getState());
                //  tvState.setText(mListItem.get(0).getState());
                //  intentState = mListItem.get(0).getState();


                    if (list_city.contains(intentCity)) {
                        tvCity.setText(intentCity);
                        spCity.setSelection(list_city.indexOf(intentCity) + 1);

                }
            }
        }
        else if(mListItem.get(0).getCity()==null) tvCity.setText("");

        if(mListItem.get(0).getResident_status()!=null) {
            intentResident = mListItem.get(0).getResident_status();
            if (intentResident.equalsIgnoreCase("0")||intentResident.equalsIgnoreCase("")) {
                tvResidentStatus.setText("");
            } else {
               tvResidentStatus.setText(intentResident);
               spResident.setSelection(list_resident.indexOf(intentResident)+1);
               /* Log.e("listMaritialStatSize", "" + list_resident.size());
                Log.e("textMStat", "" + list_resident.get(Integer.parseInt(intentResident) - 1));
                if (list_resident.size() > Integer.parseInt(intentResident) - 1) {

                    tvResidentStatus.setText(list_resident.get(Integer.parseInt(intentResident) - 1));
                    spResident.setSelection(Integer.parseInt(intentResident));
                    Log.e("textMaritalStat", tvResidentStatus.getText().toString());
                }*/
            }
        }
        else if(mListItem.get(0).getResident_status()==null) tvResidentStatus.setText("");




        /*        if(mListItem.get(0).getCountry()!=null) {
            //Log.e("indexcountry",""+list_country.indexOf(mListItem.get(0).getBirth_country()));
            Log.e("Country",mListItem.get(0).getCountry());
            tvCountry.setText(mListItem.get(0).getCountry());
            intentCountry=mListItem.get(0).getCountry();
            spCountry.setSelection(list_country.indexOf(intentCountry)+1);
        }
        if(mListItem.get(0).getState()!=null) {
            Log.e("state",mListItem.get(0).getState());
            tvState.setText(mListItem.get(0).getState());
            intentState=mListItem.get(0).getState();
            spState.setSelection(list_state.indexOf(intentState)+1);
        }
        if(mListItem.get(0).getCity()!=null) {
            tvCity.setText(mListItem.get(0).getCity());
            intentCity=mListItem.get(0).getCity();
            spCity.setSelection(list_city.indexOf(intentCity)+1);
        }
        if(mListItem.get(0).getResident_status()!=null) {
          //  Log.e("resident",(mListItem.get(0).getResident_status()));
           // Log.e("residentindex", "" + list_resident.indexOf(mListItem.get(0).getResident_status()));
            tvResidentStatus.setText(list_resident.get(Integer.parseInt(mListItem.get(0).getResident_status())));
            spResident.setSelection(list_resident.indexOf(mListItem.get(0).getResident_status()));
        }*/
        //tvParish.setText(mListItem.get(0).getParish());
        //tvVillage.setText(mListItem.get(0).getParish_village());
    }

    public void setProfessional(){

       /* if(mListItem.get(0).getEducation()!=null) {
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
      else if(mListItem.get(0).getEducation()==null) tvEdu.setText("");

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
        else if(mListItem.get(0).getOccupation()==null) tvOccu.setText("");
*/



       if(mListItem.get(0).getEducation()!=null) {
            intentEdu = mListItem.get(0).getEducation();
            if (intentEdu.equalsIgnoreCase("0")||intentEdu.equalsIgnoreCase("")) {
                tvEdu.setText("");
            } else {
                //  intentEdu = mListItem.get(0).getEducation();

                    if (list_edu.contains(intentEdu)) {
                        tvEdu.setText(intentEdu);
                        spEdu.setSelection(list_edu.indexOf(intentEdu) + 1);

                }
            }
        }
      else if(mListItem.get(0).getEducation()==null) tvEdu.setText("");

        if(mListItem.get(0).getOccupation()!=null) {
            intentOccu = mListItem.get(0).getOccupation();
            if (intentOccu.equalsIgnoreCase("")||intentOccu.equalsIgnoreCase("")) {
                tvOccu.setText("");
            } else {
                // intentOccu = mListItem.get(0).getOccupation();

                    if (list_occu.contains(intentOccu)) {
                        tvOccu.setText(intentOccu);
                        spOccu.setSelection(list_occu.indexOf(intentOccu)+1);

                }
            }
        }
        else if(mListItem.get(0).getOccupation()==null) tvOccu.setText("");

       /*
        tvEdu.setText(mListItem.get(0).getEducation());
        Log.e("indexr", String.valueOf(list_religion.indexOf(mListItem.get(0).getReligion())));
        intentEdu=mListItem.get(0).getEducation();

            if (spEdu.getSelectedItemPosition()==0) {
                if (list_edu.contains(intentEdu)) {
                    spEdu.setSelection(list_edu.indexOf(intentEdu) + 1);
                }
            }

        Log.e("intentEdu", mListItem.get(0).getEducation());
        tvInstitute.setText(mListItem.get(0).getCollege());
        etCollege.setText(mListItem.get(0).getCollege());
        tvEduDetail.setText(mListItem.get(0).getEducation_detail());
        etEduDetail.setText(mListItem.get(0).getEducation_detail());
        tvOccu.setText(mListItem.get(0).getOccupation());
        intentOccu=mListItem.get(0).getOccupation();

            if (spOccu.getSelectedItemPosition()==0) {
                if (list_occu.contains(intentOccu)) {
                    spOccu.setSelection(list_occu.indexOf(intentOccu) + 1);
                }
            }

        Log.e("intentoccu", intentOccu);*/
       tvInstitute.setText(mListItem.get(0).getCollege());
        etCollege.setText(mListItem.get(0).getCollege());
        tvEduDetail.setText(mListItem.get(0).getEducation_detail());
        etEduDetail.setText(mListItem.get(0).getEducation_detail());
        tvOccuDetail.setText(mListItem.get(0).getOccupation_detail());
        etOccuDetail.setText(mListItem.get(0).getOccupation_detail());
       tvEmployedIn.setText(mListItem.get(0).getEmployed_in());
       etEmployedIn.setText(mListItem.get(0).getEmployed_in());
       tvAnnualIncome.setText(mListItem.get(0).getIncome_actual());
       etIncome.setText(mListItem.get(0).getIncome_actual());
    }

    public void setFamilyDetails(){

        if (mListItem.get(0).getFamily_value() != null) {
            intentFamilyValue= mListItem.get(0).getFamily_value();
            if (intentFamilyValue.equalsIgnoreCase("0") || intentFamilyValue.equalsIgnoreCase("")) {
                tvFamValues.setText("");
            } else {
                //intentPhysicalStat = mListItem.get(0).getPhysical_status();
                Log.e("listPhysicalStatSize", "" + list_fam_value.size());
                Log.e("textPStat", "" + list_fam_value.get(Integer.parseInt(intentFamilyValue) - 1));
                if (list_fam_value.size() > Integer.parseInt(intentFamilyValue) - 1) {
                    tvFamValues.setText(list_fam_value.get(Integer.parseInt(intentFamilyValue) - 1));
                    spFamValue.setSelection(Integer.parseInt(intentFamilyValue));
                }
            }
        }

        if (mListItem.get(0).getFamily_status() != null) {
            intentFamilyStat= mListItem.get(0).getFamily_status();
            if (intentFamilyStat.equalsIgnoreCase("0") || intentFamilyStat.equalsIgnoreCase("")) {
                tvFamStatus.setText("");
            } else {
                //intentPhysicalStat = mListItem.get(0).getPhysical_status();
                Log.e("listPhysicalStatSize", "" + list_fam_stat.size());
                Log.e("textPStat", "" + list_fam_stat.get(Integer.parseInt(intentFamilyStat) - 1));
                if (list_fam_stat.size() > Integer.parseInt(intentFamilyStat) - 1) {
                    tvFamStatus.setText(list_fam_stat.get(Integer.parseInt(intentFamilyStat) - 1));
                    spFamStat.setSelection(Integer.parseInt(intentFamilyStat));
                }
            }
        }

        if (mListItem.get(0).getFamily_type() != null) {
            intentFamilyType= mListItem.get(0).getFamily_type();
            if (intentFamilyType.equalsIgnoreCase("0") || intentFamilyType.equalsIgnoreCase("")) {
                tvFamType.setText("");
            } else {
                //intentPhysicalStat = mListItem.get(0).getPhysical_status();
                Log.e("listPhysicalStatSize", "" + list_fam_type.size());
//                Log.e("textPStat", "" + list_fam_type.get(Integer.parseInt(intentFamilyType) - 1));
                if (list_fam_type.size() > Integer.parseInt(intentFamilyType) - 1) {
                    tvFamType.setText(list_fam_type.get(Integer.parseInt(intentFamilyType) - 1));
                    spFamType.setSelection(Integer.parseInt(intentFamilyType));
                }
            }
        }

        /*tvFamValues.setText(list_fam_value.get(Integer.parseInt(mListItem.get(0).getFamily_value())-1));
        spFamValue.setSelection(Integer.parseInt(mListItem.get(0).getFamily_value()));
        tvFamStatus.setText(list_fam_stat.get(Integer.parseInt(mListItem.get(0).getFamily_status())-1));
        spFamStat.setSelection(Integer.parseInt(mListItem.get(0).getFamily_status()));
        tvFamType.setText(list_fam_type.get(Integer.parseInt(mListItem.get(0).getFamily_type())-1));
        spFamType.setSelection(Integer.parseInt(mListItem.get(0).getFamily_type()));*/

        tvFamOrigin.setText(mListItem.get(0).getFamily_origin());
        etFamOrigin.setText(mListItem.get(0).getFamily_origin());
        tvFamLoc.setText(mListItem.get(0).getFamily_location());
        etFamLoc.setText(mListItem.get(0).getFamily_location());
        tvFatherStat.setText(mListItem.get(0).getFather_status());
        etFatherStat.setText(mListItem.get(0).getFather_status());
        tvMotherStat.setText(mListItem.get(0).getMother_status());
        etMotherStat.setText(mListItem.get(0).getMother_status());
        tvNoOfBrothers.setText(mListItem.get(0).getBrothers());
        etNoOfBrothers.setText(mListItem.get(0).getBrothers());
        tvNoOfSisters.setText(mListItem.get(0).getSisters());
        etNoOfSisters.setText(mListItem.get(0).getSisters());

        etAboutFam.setText(mListItem.get(0).getFamily_about());

    }
    //display toast
    public void showToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    public void resetAll()
    {
        spProfFor.setSelection(0);
        spBodyType.setSelection(0);
        spComplexion.setSelection(0);
        spPhysStat.setSelection(0);
        spHeight.setSelection(0);
        spWeight.setSelection(0);
        spMTongue.setSelection(0);
        spMaritalStat.setSelection(0);
        spFood.setSelection(0);
        spDrink.setSelection(0);
        spSmoke.setSelection(0);
    }

    public void keepButtonOpen(String section)
    {


       switch(section)
       {
           case "basic":
               Log.e("basic","keepButtonOpen");
               if (llBasic.getVisibility() == View.GONE) {
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
               Log.e("religion","keepButtonOpen");
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

           case "Family":
               if (llFamily.getVisibility() == View.GONE) {
                   for(int i=0;i<list_ll.size();i++)
                   {
                       list_ll.get(i).setVisibility(View.GONE);
                   }

                   llFamily.setVisibility(View.VISIBLE);
               }
               else
                   llFamily.setVisibility(View.GONE);

               break;

           case "aboutFam":
               if (llAbtFam.getVisibility() == View.GONE) {
                   for(int i=0;i<list_ll.size();i++)
                   {
                       list_ll.get(i).setVisibility(View.GONE);
                   }

                   llAbtFam.setVisibility(View.VISIBLE);
               }
               else
                   llAbtFam.setVisibility(View.GONE);

               break;
       }

    }
}
