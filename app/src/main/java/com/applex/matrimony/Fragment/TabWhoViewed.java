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
import com.applex.matrimony.Interface.getViewedMeInterface;
import com.applex.matrimony.Pojo.ParentPojoTabWhoMe;
import com.applex.matrimony.Pojo.PojoProfile;
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


public class TabWhoViewed extends Fragment {


    Spinner quali,course,special,college,crs_type,year;
    JSONArray eduArray;
    ArrayList<String> list=new ArrayList<String>();
    ArrayList<PojoProfile> mListItem;
    public RecyclerView recyclerView;
    WhoViewedAdapter adapter;
    private ProgressBar progressBar;
    private LinearLayout lyt_not_found;
    ProgressDialog progressDialog;

    SPCustProfile spCustProfile;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.tab_who_viewed,container,false);

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait");

        spCustProfile=new SPCustProfile(getActivity());

        Log.e("TabWhoViewed","onCreateView");
        mListItem = new ArrayList<PojoProfile>();
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
      //  mListItem.add(pojoWhoViewed);

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
        //mListItem.add(pojoWhoViewed1);
        //mListItem.add(pojoWhoViewed);
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

        } else {

        }
    }

    public void getwhoViewedMe()
    {

        progressDialog.show();
        if(mListItem!=null)
            mListItem.clear();

        getViewedMeInterface getResponse = APIClient.getClient().create(getViewedMeInterface.class);
        Call<ParentPojoTabWhoMe> call = getResponse.doGetListResources("6673532");
        call.enqueue(new Callback<ParentPojoTabWhoMe>() {
            @Override
            public void onResponse(Call<ParentPojoTabWhoMe> call, Response<ParentPojoTabWhoMe> response) {

                Log.e("Inside","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoTabWhoMe parentPojoTabWhoMe=response.body();
                if(parentPojoTabWhoMe!=null){
                    if(parentPojoTabWhoMe.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+parentPojoTabWhoMe.getObjProfile().size());
                        if(parentPojoTabWhoMe.getObjProfile().size()!=0)
                            displayData();

                    }
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ParentPojoTabWhoMe> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }
}
