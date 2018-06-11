package com.applex.matrimony.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.applex.matrimony.APIClient;
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

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class UploadPhotoActivity extends AppCompatActivity implements View.OnClickListener {


    public static File file;
    private Uri mCameraFileUri;
    Bitmap bitmap;
    ShapedImageView imgProfilePic;
    File imageFile;
    Button btnSubmit;
    SPCustProfile spCustProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
        imgProfilePic=(ShapedImageView) findViewById(R.id.img_profile_pic);
        btnSubmit=(Button)findViewById(R.id.btn_submit_photo);
        btnSubmit.setOnClickListener(this);
        imgProfilePic.setOnClickListener(this);
        spCustProfile=new SPCustProfile(this);
    }


    private void chooseImage()
    {
        // Intent intent = new Intent();
        // intent.setType("image/*");
        //  intent.setAction(Intent.ACTION_GET_CONTENT);
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");

        file = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),200);
    }

    private File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(getCacheDir(), "cache_images");
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
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
            Cursor cursor = getContentResolver().query(contentURI, filePathColumn, null, null, null);
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
                //uploadFile();
                startActivity(new Intent(UploadPhotoActivity.this,TabViewParentActivity.class));
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

        uploadPhotoInterface getResponse = APIClient.getClient().create(uploadPhotoInterface.class);
        Call<CommonParentPojo> call = getResponse.uploadFile(fileToUpload, filename,spCustProfile.getProfile_id());
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
                        Toast.makeText(getApplicationContext(), "success" + commonParentPojo.getMsg(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UploadPhotoActivity.this,TabViewParentActivity.class));
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