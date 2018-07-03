package com.applex.matrimony.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applex.matrimony.Fragment.TabMatches;
import com.applex.matrimony.Fragment.TabProfileDetail;
import com.applex.matrimony.Pojo.ChildPojoProfile;
import com.applex.matrimony.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HomeProfilesAdapter extends RecyclerView.Adapter<HomeProfilesAdapter.ItemRowHolder> implements Serializable {

    private ArrayList<ChildPojoProfile> dataList;
    private Context mContext;


    public HomeProfilesAdapter(Context context, ArrayList<ChildPojoProfile> dataList) {
        this.dataList = dataList;
        this.mContext = context;

    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_home_profiles, parent, false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemRowHolder holder, final int position) {




        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        final ChildPojoProfile singleItem = dataList.get(position);

        if ((singleItem.getGender().equalsIgnoreCase("male"))) {
            holder.member_typ.setText("Groom");
        } else {
            holder.member_typ.setText("Bride");
        }
        if(singleItem.getProfile_photo()!=null)

        Picasso.with(mContext).load("http://applex360.in/Deshpande-family/Matrimony-web/"+singleItem.getProfile_photo()).placeholder(R.mipmap.iconprofile).fit().into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                singleItem.getMatrimony_id();

                Toast.makeText(mContext,"Clicked",Toast.LENGTH_SHORT).show();
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Container, new TabProfileDetail())
                        .addToBackStack("detail")
                        .commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView member_typ;
        public LinearLayout lyt_parent;
        public Button btnSendMail,btnShortList,btnPremium;
        ImageButton ibtnInterest;

        public ItemRowHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_row_logo);
            member_typ = (TextView) itemView.findViewById(R.id.tv_row_member_type);

        }
    }

        public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
