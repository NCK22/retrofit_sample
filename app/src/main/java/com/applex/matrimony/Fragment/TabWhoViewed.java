package com.applex.matrimony.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;


import com.applex.matrimony.Adapter.WhoViewedAdapter;
import com.applex.matrimony.Pojo.PojoProfile;
import com.applex.matrimony.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Dell on 15-01-2018.
 */


public class TabWhoViewed extends Fragment {


    Spinner quali,course,special,college,crs_type,year;
    JSONArray eduArray;
    ArrayList<String> list=new ArrayList<String>();
    ArrayList<PojoProfile> mListItem;
    public RecyclerView recyclerView;
    WhoViewedAdapter adapter;
    private ProgressBar progressBar;
    private LinearLayout lyt_not_found;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.tab_who_viewed,container,false);

        Log.e("TabWhoViewed","onCreateView");
        mListItem = new ArrayList<PojoProfile>();
        PojoProfile pojoWhoViewed=new PojoProfile();
        pojoWhoViewed.setAge("25 yrs");
        pojoWhoViewed.setLocation("Hyderabad");
        pojoWhoViewed.setHeight("150cm - 4ft 11.05");
        pojoWhoViewed.setReligion("Hindu");
        pojoWhoViewed.setCaste("Brahmin");
        pojoWhoViewed.setSign("Dhanu");
        pojoWhoViewed.setStar("Purvashadha");
        pojoWhoViewed.setEdu("MBA - Finance");
        pojoWhoViewed.setOccu("Administration security manager");
        pojoWhoViewed.setMember_typ("Bride");
        pojoWhoViewed.setMatrimony_id("KL4566623");
        mListItem.add(pojoWhoViewed);

        PojoProfile pojoWhoViewed1=new PojoProfile();
        pojoWhoViewed1.setAge("27 yrs");
        pojoWhoViewed1.setLocation("HariyanaHariyanaHariyana");
        pojoWhoViewed1.setHeight("150cm - 4ft 11.05");
        pojoWhoViewed1.setReligion("Hindu");
        pojoWhoViewed1.setCaste("Maratha");
        pojoWhoViewed1.setSign("Kanya");
        pojoWhoViewed1.setStar("bhrugu");
        pojoWhoViewed1.setEdu("MBA - operations");
        pojoWhoViewed1.setOccu("Administration security manager");
        pojoWhoViewed1.setMember_typ("Groom");
        pojoWhoViewed1.setMatrimony_id("K987566623");
        mListItem.add(pojoWhoViewed1);
        mListItem.add(pojoWhoViewed);
        Log.e("List size",""+mListItem.size());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_who_viewed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       /* ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);*/





        displayData();


        return rootView;

    }
    private void displayData() {
        Log.e("displayData","called");
        Log.e("List size",""+mListItem.size());
        adapter = new WhoViewedAdapter(getActivity(), mListItem);
        recyclerView.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
  //          lyt_not_found.setVisibility(View.VISIBLE);
        } else {
//            lyt_not_found.setVisibility(View.GONE);
        }
    }
}
