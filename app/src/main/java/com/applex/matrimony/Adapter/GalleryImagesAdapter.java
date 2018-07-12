package com.applex.matrimony.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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

import com.applex.matrimony.Fragment.TabProfileDetail;
import com.applex.matrimony.Pojo.ChildPojoProfile;
import com.applex.matrimony.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GalleryImagesAdapter extends RecyclerView.Adapter<GalleryImagesAdapter.ItemRowHolder> implements Serializable {

    private ArrayList<String> dataList;
    private Context mContext;


    public GalleryImagesAdapter(Context context, ArrayList<String> dataList) {
        this.dataList = dataList;
        this.mContext = context;

    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_gallery_img, parent, false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemRowHolder holder, final int position) {




        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        final String singleItem = dataList.get(position);

        Log.e("path","http://applex360.in/Deshpande-family/Matrimony-web/"+singleItem);
        Picasso.with(mContext).load("http://applex360.in/Deshpande-family/Matrimony-web/"+singleItem).placeholder(R.mipmap.addicon).fit().into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*Bundle bundle=new Bundle();
                bundle.putString("matrimony_id",singleItem.getMatrimony_id());
                TabProfileDetail tabProfileDetail=new TabProfileDetail();
                tabProfileDetail.setArguments(bundle);

                Toast.makeText(mContext,"Clicked",Toast.LENGTH_SHORT).show();
                ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Container, tabProfileDetail)
                        .addToBackStack("detail")
                        .commit();*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public LinearLayout lyt_parent;
        public Button btnSendMail,btnShortList,btnPremium;
        ImageButton ibtnInterest;

        public ItemRowHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_row_logo);

        }
    }

        public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
