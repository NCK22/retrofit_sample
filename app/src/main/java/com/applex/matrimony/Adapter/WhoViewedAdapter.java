package com.applex.matrimony.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Interface.getShortlistedMyInterface;
import com.applex.matrimony.Interface.sendInterestInterface;
import com.applex.matrimony.Interface.shortListInterface;
import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoProfile;
import com.applex.matrimony.Pojo.ChildPojoProfile;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;
import com.squareup.picasso.Picasso;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhoViewedAdapter extends RecyclerView.Adapter<WhoViewedAdapter.ItemRowHolder> implements Serializable {

    private ArrayList<ChildPojoProfile> dataList;
    private Context mContext;
    ProgressDialog progressDialog;
    ArrayList<String> list_profile=new ArrayList<String>();
    ArrayList<ChildPojoProfile> mListItem;
    SPCustProfile spCustProfile;

    public WhoViewedAdapter(Context context, ArrayList<ChildPojoProfile> dataList) {
        this.dataList = dataList;
        this.mContext = context;
        progressDialog=new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait adapter");
        mListItem = new ArrayList<ChildPojoProfile>();
        spCustProfile=new SPCustProfile(mContext);


    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_who_viewed, parent, false);

        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemRowHolder holder, final int position) {
        getWhoShortlistedMy();
        Log.e("BindviewHolder","called");
        spCustProfile=new SPCustProfile(mContext);

        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#00000000"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        final ChildPojoProfile singleItem = dataList.get(position);
        holder.age_loc.setText(singleItem.getAge()+" ,"+singleItem.getFamily_location());
        holder.height.setText(singleItem.getHeight());
        holder.religion_caste.setText(singleItem.getReligion()+" ,"+singleItem.getCaste());
        holder.sign_star.setText(singleItem.getRaasi()+" ,"+singleItem.getStar());
        holder.edu.setText(singleItem.getEducation());
        if(singleItem.getOccupation()!=null)
        holder.occu.setText(singleItem.getOccupation());
        else
            holder.occu.setText(singleItem.getOccupation_detail());
        if (singleItem.getGender() != null) {


            if ((singleItem.getGender().equalsIgnoreCase("male"))) {
                holder.member_typ.setText("Groom");
            } else {
                holder.member_typ.setText("Bride");
            }
        }
        holder.matrimony_id.setText(singleItem.getMatrimony_id());
        if(list_profile.contains(singleItem.getMatrimony_id()))
        {
            Log.e(singleItem.getMatrimony_id(),"shortListed");
            holder.btnShortList.setText("shortlisted");
        }
        else
        {
            Log.e(singleItem.getMatrimony_id(),"not shortListed");
            holder.btnShortList.setText("Short List");
        }

      /*  if(spCustProfile.getShortListedProf().contains(singleItem.getMatrimony_id()))
        {
            Log.e(singleItem.getMatrimony_id(),"shortListed");
            holder.btnShortList.setText("shortlisted");
        }
        else
        {
            Log.e(singleItem.getMatrimony_id(),"not shortListed");
            holder.btnShortList.setText("Short List");
        }*/
        Picasso.with(mContext).load("http://applex360.in/Deshpande-family/Matrimony-web/"+singleItem.getProfile_photo()).placeholder(R.mipmap.iconprofile).into(holder.image);

        holder.btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.btnShortList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shortList(singleItem.getMatrimony_id(),view);
            }
        });

        holder.ibtnInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendInterest(singleItem.getMatrimony_id(),view);
            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView age_loc,height,religion_caste,sign_star,edu,occu,matrimony_id,member_typ;
        public LinearLayout lyt_parent;
        public Button btnSendMail,btnShortList,btnPremium;
        ImageButton ibtnInterest;

        public ItemRowHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_logo);
            age_loc = (TextView) itemView.findViewById(R.id.tv_row_age_loc);
            height = (TextView) itemView.findViewById(R.id.tv_row_height);
            religion_caste = (TextView) itemView.findViewById(R.id.tv_row_religion_caste);
            sign_star = (TextView) itemView.findViewById(R.id.tv_row_sign_star);
            edu = (TextView) itemView.findViewById(R.id.tv_row_edu);
            occu = (TextView) itemView.findViewById(R.id.tv_row_occu);
            matrimony_id = (TextView) itemView.findViewById(R.id.tv_row_matrimony_id);
            member_typ = (TextView) itemView.findViewById(R.id.tv_row_member_type);
            ibtnInterest=(ImageButton)itemView.findViewById(R.id.ibtn_row_interest);


            btnSendMail = (Button) itemView.findViewById(R.id.btn_row_sendMail);
            btnShortList = (Button) itemView.findViewById(R.id.btn_row_shortList);
            btnPremium=(Button)itemView.findViewById(R.id.btn_row_premium);
        }
    }

        public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void shortList(String matrimony_id_to, final View view)
    {

        progressDialog.show();

    Log.e("matrimony_id",matrimony_id_to);
        shortListInterface getResponse = APIClient.getClient().create(shortListInterface.class);
        Call<CommonParentPojo> call = getResponse.doGetListResources("7180214",matrimony_id_to);
        call.enqueue(new Callback<CommonParentPojo>() {
            @Override
            public void onResponse(Call<CommonParentPojo> call, Response<CommonParentPojo> response) {

                Log.e("Inside","onResponse");

                CommonParentPojo commonParentPojo=response.body();
                showToast(commonParentPojo.getMsg());
                if(commonParentPojo!=null){
                    if(commonParentPojo.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response",commonParentPojo.getMsg());
                        new ItemRowHolder(view).btnShortList.setText("Shortlisted");

                    }


                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    public void sendInterest(String matrimony_id_to, final View view)
    {

        progressDialog.show();

        Log.e("matrimony_id",matrimony_id_to);
        sendInterestInterface getResponse = APIClient.getClient().create(sendInterestInterface.class);
        Call<CommonParentPojo> call = getResponse.doGetListResources("7180214",matrimony_id_to);
        call.enqueue(new Callback<CommonParentPojo>() {
            @Override
            public void onResponse(Call<CommonParentPojo> call, Response<CommonParentPojo> response) {

                Log.e("Inside","onResponse");

                CommonParentPojo commonParentPojo=response.body();
                showToast(commonParentPojo.getMsg());
                if(commonParentPojo!=null){
                    if(commonParentPojo.getStatus().equalsIgnoreCase("1")){
                        Log.e("Response interest",commonParentPojo.getMsg());
                        new ItemRowHolder(view).ibtnInterest.setImageResource(R.mipmap.iconintrestedfilled);
                    }


                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    public void getWhoShortlistedMy()
    {

        progressDialog.show();
        if(list_profile!=null)
            list_profile.clear();
        if(mListItem!=null)
            mListItem.clear();

        getShortlistedMyInterface getResponse = APIClient.getClient().create(getShortlistedMyInterface.class);
        Call<ParentPojoProfile> call = getResponse.doGetListResources("7180214");
        call.enqueue(new Callback<ParentPojoProfile>() {
            @Override
            public void onResponse(Call<ParentPojoProfile> call, Response<ParentPojoProfile> response) {

                Log.e("Inside adapter","onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoProfile parentPojoProfile =response.body();
                if(parentPojoProfile !=null){
                    Log.e("Responseadaptr", parentPojoProfile.getMsg());
                    if(parentPojoProfile.getStatus().equalsIgnoreCase("1")){
                        Log.e("Responseadaptr", parentPojoProfile.getMsg());

                        Log.e("objsize adapter", ""+ parentPojoProfile.getObjProfile().size());
                        if(parentPojoProfile.getObjProfile().size()!=0) {
                            mListItem= parentPojoProfile.getObjProfile();
                            for(int i=0;i<mListItem.size();i++) {
                                list_profile.add(mListItem.get(i).getMatrimony_id());
                                spCustProfile.setShortListedProf(spCustProfile.getShortListedProf()+","+mListItem.get(i).getMatrimony_id());
                            }
                            Log.e("size of list_profile",""+list_profile.size());
                            Log.e("sp",spCustProfile.getShortListedProf());

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


