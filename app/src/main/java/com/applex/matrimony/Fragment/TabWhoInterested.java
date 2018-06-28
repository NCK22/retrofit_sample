package com.applex.matrimony.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Adapter.WhoViewedAdapter;
import com.applex.matrimony.Interface.getInterestedMeInterface;
import com.applex.matrimony.Pojo.ParentPojoProfile;
import com.applex.matrimony.Pojo.ChildPojoProfile;
import com.applex.matrimony.Pojo.PojoProfileOld;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Dell on 15-01-2018.
 */


public class TabWhoInterested extends Fragment {


    Spinner quali,course,special,college,crs_type,year;
    JSONArray eduArray;
    ArrayList<String> list=new ArrayList<String>();
    ArrayList<ChildPojoProfile> mListItem;
    public RecyclerView recyclerView;
    WhoViewedAdapter adapter;
    private ProgressBar progressBar;
    private LinearLayout lyt_not_found;
    ProgressDialog progressDialog;

    SPCustProfile spCustProfile;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.tab_who_interested,container,false);

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait");

        spCustProfile=new SPCustProfile(getActivity());

        Log.e("TabWhoInterested","onCreateView");
        mListItem = new ArrayList<ChildPojoProfile>();
        PojoProfileOld pojoWhoViewed=new PojoProfileOld();
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
     //   mListItem.add(pojoWhoViewed);

        PojoProfileOld pojoWhoViewed1=new PojoProfileOld();
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
      //  mListItem.add(pojoWhoViewed1);
        //mListItem.add(pojoWhoViewed);
        Log.e("List size",""+mListItem.size());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_who_viewed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       /* ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);*/

    getWhoInterestedMe();

        return rootView;

    }
    private void displayData() {
        Log.e("displayData","called");
        Log.e("List size",""+mListItem.size());
        adapter = new WhoViewedAdapter(getActivity(), mListItem);
        recyclerView.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
        } else {
        }
    }

    public void getWhoInterestedMe()
    {

        progressDialog.show();
        if(mListItem!=null)
            mListItem.clear();

        getInterestedMeInterface getResponse = APIClient.getClient().create(getInterestedMeInterface.class);
        Call<ParentPojoProfile> call = getResponse.doGetListResources(spCustProfile.getMatrimonyId());
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
                        mListItem= parentPojoProfile.getObjProfile();
                        if(mListItem.size()!=0)

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
}
