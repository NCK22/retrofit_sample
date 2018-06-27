package com.applex.matrimony.Fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.applex.matrimony.APIClient;
import com.applex.matrimony.Activity.TabViewParentActivity;
import com.applex.matrimony.Interface.uploadPhotoInterface;
import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.R;
import com.applex.matrimony.Storage.SPCustProfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class UploadPhoto extends Fragment implements View.OnClickListener {


    public static File file;
    private Uri mCameraFileUri;
    Bitmap bitmap;
    ShapedImageView imgProfilePic;
    File imageFile;
    Button btnSubmit;
    SPCustProfile spCustProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.activity_upload_photo,container,false);
        imgProfilePic=(ShapedImageView) rootView.findViewById(R.id.img_profile_pic);
        btnSubmit=(Button)rootView.findViewById(R.id.btn_submit_photo);
        btnSubmit.setOnClickListener(this);
        imgProfilePic.setOnClickListener(this);
        spCustProfile=new SPCustProfile(getActivity());
        return rootView;
    }



    private void chooseImage()
    {
        // Intent intent = new Intent();
        // intent.setType("image/*");
        //  intent.setAction(Intent.ACTION_GET_CONTENT);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");

        file = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),200);
    }

    private File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(getActivity().getCacheDir(), "cache_images");
        if(!mediaStorageDir.exists()){
            mediaStorageDir.mkdir();
        }

        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + getFileName() + ".png");
        }  else {
            return null;
        }
        return mediaFile;
    }

    private String getFileName(){
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
            if(cursor!=null) {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    thePath = cursor.getString(columnIndex);
                }
                cursor.close();
            }
            return  thePath;
        }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
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
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), imageFile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("JPEG/PNG"), imageFile.getName());
        RequestBody profile_id = RequestBody.create(null, spCustProfile.getProfile_id());
        uploadPhotoInterface getResponse = APIClient.getClient().create(uploadPhotoInterface.class);
        Call<CommonParentPojo> call = getResponse.uploadFile(fileToUpload, filename,profile_id);
        call.enqueue(new Callback<CommonParentPojo>() {
            @Override
            public void onResponse(Call<CommonParentPojo> call, retrofit2.Response<CommonParentPojo> response) {

                CommonParentPojo commonParentPojo = response.body();
                    Log.e("ServerResponse", commonParentPojo.getMsg());
                if (commonParentPojo != null) {
                    Log.e("response",commonParentPojo.getMsg());
                    if (commonParentPojo.getStatus().equalsIgnoreCase("1")) {
                        //  strResumePath=serverResponse.getMessage();
                        Log.e("Success Response", commonParentPojo.getMsg());
                        Toast.makeText(getActivity(), "success" + commonParentPojo.getMsg(), Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(UploadPhoto.this,TabViewParentActivity.class));
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
            public void onFailure(Call<CommonParentPojo> call, Throwable t) {

                Log.e("throwbale",""+t);
            }

            });



}
    }