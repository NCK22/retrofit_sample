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
import com.applex.matrimony.Interface.getHighlightedInterface;
import com.applex.matrimony.Interface.getReligionInterface;
import com.applex.matrimony.Pojo.ChildPojoCaste;
import com.applex.matrimony.Pojo.ChildPojoProfile;
import com.applex.matrimony.Pojo.ChildPojoReligion;
import com.applex.matrimony.Pojo.ParentPojoCaste;
import com.applex.matrimony.Pojo.ParentPojoProfile;
import com.applex.matrimony.Pojo.ParentPojoReligion;
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


    MaterialSpinner spReligion,spCaste;
    JSONArray eduArray;
    ArrayList<String> list_religion=new ArrayList<String>();
    ArrayList<ChildPojoReligion>list_pojo_religion=new ArrayList<ChildPojoReligion>();
    ArrayList<ChildPojoCaste>list_pojo_caste=new ArrayList<ChildPojoCaste>();
    ArrayList<String> list_caste=new ArrayList<String>();
    ArrayList<ChildPojoProfile> list_matches=new ArrayList<ChildPojoProfile>();
    ArrayList<ChildPojoProfile> list_highlights=new ArrayList<ChildPojoProfile>();
    public RecyclerView rv_profile_matches,rv_profile_highlight;
    HomeProfilesAdapter adapter;
    private ProgressBar progressBar;
    private LinearLayout lyt_not_found;
    ProgressDialog progressDialog;
    ArrayAdapter aaCaste;
    Button  btnFind;
    EditText etAgeFrom,etAgeTo;
    ExpandableRelativeLayout erlBasic;
    Button btnBasic;
    LinearLayout llBasic,llBasicEdit,llBasicView;
    ImageView imgEditBasic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.tab_my_profile,container,false);

        Log.e("TabHome","onCreateView");


        llBasic=(LinearLayout)rootView.findViewById(R.id.ll_basic);
        llBasicEdit=(LinearLayout)rootView.findViewById(R.id.ll_basic_edit);
        llBasicView=(LinearLayout)rootView.findViewById(R.id.ll_basic_view);
        btnBasic=(Button)rootView.findViewById(R.id.expandableButton4) ;
        btnBasic.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(llBasic.getVisibility()==View.GONE)
                llBasic.setVisibility(View.VISIBLE);
            else
                llBasic.setVisibility(View.GONE);

        }
    });

        imgEditBasic=(ImageView)rootView.findViewById(R.id.img_editSave_basic);
        imgEditBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llBasicView.setVisibility(View.INVISIBLE);
                llBasicEdit.setVisibility(View.VISIBLE);
                llBasicEdit.setBackgroundResource(R.mipmap.saveicon);
            }
        });

        return rootView;/*

        rv_profile_matches = (RecyclerView) rootView.findViewById(R.id.rv_prof_matches);
        rv_profile_highlight = (RecyclerView) rootView.findViewById(R.id.rv_prof_highlight);
        rv_profile_matches.setHasFixedSize(true);
        rv_profile_matches.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_profile_highlight.setHasFixedSize(true);
        rv_profile_highlight.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));



        etAgeFrom=(EditText)rootView.findViewById(R.id.et_age_from);
        etAgeTo=(EditText)rootView.findViewById(R.id.et_age_to);
        btnFind=(Button)rootView.findViewById(R.id.btn_home_find);
        spCaste=(MaterialSpinner)rootView.findViewById(R.id.sp_home_caste);
        spReligion=(MaterialSpinner)rootView.findViewById(R.id.sp_home_religion);

        btnFind.setOnClickListener(this);
        spReligion.setOnItemSelectedListener(this);
        spCaste.setOnItemSelectedListener(this);

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait home");

        getReligionList();
       // displayData();
        getHighlightedProf();

*/


    }
    private void displayData() {
        Log.e("displayData","called");
        Log.e("List_highlight size",""+list_highlights.size());
        adapter = new HomeProfilesAdapter(getActivity(), list_highlights);
        rv_profile_highlight.setAdapter(adapter);
        rv_profile_matches.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
  //          lyt_not_found.setVisibility(View.VISIBLE);
        } else {
//            lyt_not_found.setVisibility(View.GONE);
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


    public void getHighlightedProf()
    {

        progressDialog.show();
        if(list_highlights!=null)
            list_highlights.clear();

        getHighlightedInterface getResponse = APIClient.getClient().create(getHighlightedInterface.class);
        Call<ParentPojoProfile> call = getResponse.doGetListResources();
        call.enqueue(new Callback<ParentPojoProfile>() {
            @Override
            public void onResponse(Call<ParentPojoProfile> call, Response<ParentPojoProfile> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoProfile parentPojoProfile =response.body();
                if(parentPojoProfile !=null){
                    if(parentPojoProfile.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+ parentPojoProfile.getObjProfile().size());
                        list_highlights= parentPojoProfile.getObjProfile();
                        if(list_highlights.size()!=0)

                            displayData();

                    }
                }
                else
                    Log.e("parentpojotabwhome","null");
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ParentPojoProfile> call, Throwable t) {

                Log.e("throwable",""+t);
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

    }
}
