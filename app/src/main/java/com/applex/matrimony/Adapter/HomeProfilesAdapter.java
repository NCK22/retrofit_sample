package com.applex.matrimony.Adapter;

import android.content.Context;
import android.graphics.Color;
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

import com.applex.matrimony.Pojo.PojoProfile;
import com.applex.matrimony.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HomeProfilesAdapter extends RecyclerView.Adapter<HomeProfilesAdapter.ItemRowHolder> implements Serializable {

    private ArrayList<PojoProfile> dataList;
    private Context mContext;


    public HomeProfilesAdapter(Context context, ArrayList<PojoProfile> dataList) {
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

        holder.member_typ.setText(singleItem.getMember_typ());



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
            image = (ImageView) itemView.findViewById(R.id.img_logo);
            member_typ = (TextView) itemView.findViewById(R.id.tv_row_member_type);

        }
    }

        public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
