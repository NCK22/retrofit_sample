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

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Adapter.WhoViewedAdapter;
import com.applex.matrimony.Interface.getViewedMeInterface;
import com.applex.matrimony.Pojo.ChildPojoProfile;
import com.applex.matrimony.Pojo.ParentPojoProfile;
import com.applex.matrimony.Pojo.PojoProfileOld;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Dell on 15-01-2018.
 */


public class TabSearchResult extends Fragment {



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

        View rootView=inflater.inflate(R.layout.tab_search_result,container,false);

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait");

        spCustProfile=new SPCustProfile(getActivity());

        Log.e("TabWhoViewed","onCreateView");
        mListItem = new ArrayList<ChildPojoProfile>();


        Log.e("List size",""+mListItem.size());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_who_viewed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       /* ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);*/


      //  displayData();
//        getwhoViewedMe();


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
        Call<ParentPojoProfile> call = getResponse.doGetListResources("7180214");
        call.enqueue(new Callback<ParentPojoProfile>() {
            @Override
            public void onResponse(Call<ParentPojoProfile> call, Response<ParentPojoProfile> response) {

                Log.e("Inside","onResponseGetWhoViewedMe");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoProfile parentPojoProfile =response.body();
                if(parentPojoProfile !=null){
                    if(parentPojoProfile.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response","Success");
                        Log.e("objsize", ""+ parentPojoProfile.getObjProfile().size());
                        if(parentPojoProfile.getObjProfile().size()!=0) {
                            mListItem= parentPojoProfile.getObjProfile();
                            displayData();
                        }

                    }
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ParentPojoProfile> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }
}
