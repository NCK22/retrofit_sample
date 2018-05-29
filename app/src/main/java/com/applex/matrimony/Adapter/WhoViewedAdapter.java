package com.applex.matrimony.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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

import com.applex.matrimony.Pojo.PojoProfile;
import com.applex.matrimony.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WhoViewedAdapter extends RecyclerView.Adapter<WhoViewedAdapter.ItemRowHolder> implements Serializable {

    private ArrayList<PojoProfile> dataList;
    private Context mContext;


    public WhoViewedAdapter(Context context, ArrayList<PojoProfile> dataList) {
        this.dataList = dataList;
        this.mContext = context;

    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_who_viewed, parent, false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemRowHolder holder, final int position) {


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

        final PojoProfile singleItem = dataList.get(position);
        holder.age_loc.setText(singleItem.getAge()+" ,"+singleItem.getLocation());
        holder.height.setText(singleItem.getHeight());
        holder.religion_caste.setText(singleItem.getReligion()+" ,"+singleItem.getCaste());
        holder.sign_star.setText(singleItem.getSign()+" ,"+singleItem.getStar());
        holder.edu.setText(singleItem.getEdu());
        holder.occu.setText(singleItem.getOccu());
        holder.member_typ.setText(singleItem.getMember_typ());
        holder.matrimony_id.setText(singleItem.getMatrimony_id());

        holder.btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.btnShortList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.ibtnInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ibtnInterest.setImageResource(R.mipmap.iconintrestedfilled);
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
}
