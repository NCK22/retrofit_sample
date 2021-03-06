package com.applex.matrimony.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Toast;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Adapter.GalleryBitmapsAdapter;
import com.applex.matrimony.Adapter.GalleryImagesAdapter;
import com.applex.matrimony.Interface.getProfileInterface;
import com.applex.matrimony.Interface.uploadGalleryInterface;
import com.applex.matrimony.Interface.uploadPhotoInterface;
import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoGallery;
import com.applex.matrimony.Pojo.ParentPojoProfile;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class TabGalleryPhoto extends Fragment implements View.OnClickListener {


    public static File file;
    private Uri mCameraFileUri;
    Bitmap bitmap;
    public static ShapedImageView imgProfilePic;
    File imageFile;
    Button btnSubmit;
    SPCustProfile spCustProfile;
    ProgressDialog progressDialog;
    RecyclerView rv_gallery;
    GalleryImagesAdapter adapterGallery;
    ArrayList<String> list_gallery=new ArrayList<String>();
    ArrayList<LinearLayout> list_ll=new ArrayList<LinearLayout>();
    String[] temp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_gallery_photo, container, false);
        imgProfilePic = (ShapedImageView) rootView.findViewById(R.id.img_profile_pic);
        btnSubmit = (Button) rootView.findViewById(R.id.btn_submit_photo);
        btnSubmit.setOnClickListener(this);
        imgProfilePic.setOnClickListener(this);
        spCustProfile = new SPCustProfile(getActivity());
        progressDialog=new ProgressDialog(getActivity());

        rv_gallery = (RecyclerView) rootView.findViewById(R.id.rv_prof_gallery);
        rv_gallery.setHasFixedSize(true);
        rv_gallery.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        displayData();

        return rootView;
    }


    private void chooseImage() {
        // Intent intent = new Intent();
        // intent.setType("image/*");
        //  intent.setAction(Intent.ACTION_GET_CONTENT);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");

        file = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 200);
    }

    private File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(getActivity().getCacheDir(), "cache_images");
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdir();
        }

        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + getFileName() + ".png");
        } else {
            return null;
        }
        return mediaFile;
    }

    private String getFileName() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {

                Uri selectedImage = data.getData();
                imageFile = new File(getRealPathFromURI(selectedImage));


                if (imageFile == null)
                    Log.e("imagefile", "null");
                else
                    Log.e("imagefile", String.valueOf(imageFile));
                Log.e("image before", String.valueOf(imageFile.length()));


                bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    imgProfilePic.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {

        String thePath = "no-path-found";
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentURI, filePathColumn, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                thePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        return thePath;
    }

    private void displayData() {


        Log.e("displayData","called");
        //Log.e("List_highlight size",""+list_gallery.size());
        if(list_gallery.size()<=0 && spCustProfile.getGalleryPhotoPath()!=null) {
            temp = spCustProfile.getGalleryPhotoPath().toArray(new String[0]);
            for (int i = 0; i < temp.length; i++)
                list_gallery.add(temp[i]);
        }
        adapterGallery = new GalleryImagesAdapter(getActivity(),list_gallery);
        rv_gallery.setAdapter(adapterGallery);


        if (adapterGallery.getItemCount() == 0) {
            //          lyt_not_found.setVisibility(View.VISIBLE);
        } else {
//            lyt_not_found.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_profile_pic:

                chooseImage();
                break;

            case R.id.btn_submit_photo:
                uploadFile();
                // startActivity(new Intent(UploadPhoto.this,TabViewParentActivity.class));
                break;
        }
    }

    private void uploadFile() {

        // Map is used to multipart the file using okhttp3.RequestBody
        // File file = new File(docPath);

        // Parsing any Media type file
       // spCustProfile.setProfile_id("83");
        if(list_gallery!=null)
            list_gallery.clear();
        Log.e("matrimony_id", spCustProfile.getMatrimonyId());
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), imageFile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("JPEG/PNG"), imageFile.getName());
        RequestBody matrimony_id = RequestBody.create(MediaType.parse("text/plain"), spCustProfile.getMatrimonyId());
        uploadGalleryInterface getResponse = APIClient.getClient().create(uploadGalleryInterface.class);
        Call<ParentPojoGallery> call = getResponse.uploadFile(fileToUpload, filename, matrimony_id);
        call.enqueue(new Callback<ParentPojoGallery>() {
            @Override
            public void onResponse(Call<ParentPojoGallery> call, Response<ParentPojoGallery> response) {

                ParentPojoGallery parentPojoGallery = response.body();
                Log.e("ServerResponse", parentPojoGallery.getMsg());
                if (parentPojoGallery != null) {
                    Log.e("response", parentPojoGallery.getMsg());
                    if (parentPojoGallery.getStatus().equalsIgnoreCase("1")) {
                        //  strResumePath=serverResponse.getMessage();
                        Log.e("Success Response", parentPojoGallery.getMsg());
                        Toast.makeText(getActivity(), "success" + parentPojoGallery.getMsg(), Toast.LENGTH_SHORT).show();
                        list_gallery=parentPojoGallery.getObjGallery().get("Gallery");
                        spCustProfile.setGalleryPhotoPath(list_gallery);
                        displayData();

                        //getProfile();

                    }
                   /* } else {
                        strResumePath=serverResponse.getMessage();
                        Log.v("Response", serverResponse.getMessage());
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }*/
                }
            }


            @Override
            public void onFailure(Call<ParentPojoGallery> call, Throwable t) {

                Log.e("throwbale", "" + t);
            }
        });
    }


    public void getProfile() {

        progressDialog.show();

        getProfileInterface getResponse = APIClient.getClient().create(getProfileInterface.class);
        Call<ParentPojoProfile> call = getResponse.doGetListResources(spCustProfile.getMatrimonyId());
        call.enqueue(new Callback<ParentPojoProfile>() {
            @Override
            public void onResponse(Call<ParentPojoProfile> call, Response<ParentPojoProfile> response) {

                Log.e("Inside", "onResponse");
               /* Log.e("response body",response.body().getStatus());
                Log.e("response body",response.body().getMsg());*/
                ParentPojoProfile parentPojoProfile = response.body();
                if (parentPojoProfile != null) {
                    if (parentPojoProfile.getStatus().equalsIgnoreCase("1")) {
                        Log.e("Response", "Success");
                        Log.e("objsize", "" + parentPojoProfile.getObjProfile().size());
                        if (parentPojoProfile.getObjProfile().get(0).getProfile_photo() != null) {
                            Log.e("profile_photo res", parentPojoProfile.getObjProfile().get(0).getProfile_photo());
                            spCustProfile.setProfilePhotoPath(parentPojoProfile.getObjProfile().get(0).getProfile_photo());
                            spCustProfile.setName(parentPojoProfile.getObjProfile().get(0).getProfile_name());
                        }
                       // setHeader();
                    }
                } else
                    Log.e("parentpojotabwhome", "null");
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ParentPojoProfile> call, Throwable t) {

                Log.e("throwable", "" + t);
                progressDialog.dismiss();
            }
        });

    }
}