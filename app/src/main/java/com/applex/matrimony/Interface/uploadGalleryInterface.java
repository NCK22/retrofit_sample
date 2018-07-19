package com.applex.matrimony.Interface;

import com.applex.matrimony.Pojo.CommonParentPojo;
import com.applex.matrimony.Pojo.ParentPojoGallery;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface uploadGalleryInterface {


    @Multipart
    @POST("app/app_upload_gallery.php")
    Call<ParentPojoGallery> uploadFile(@Part MultipartBody.Part file, @Part("file") RequestBody name, @Part("matrimony_id") RequestBody matrimony_id);
   /* Call<CommonParentPojo> doGetListResources(
            @Field("email_id") String email_id,
            @Field("matrimony_id") String matrimony_id,
            @Field("phone") String phone
    );
*/

}
