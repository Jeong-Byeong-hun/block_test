package com.example.block_test;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface APIInterface {


    @FormUrlEncoded
    @POST("api/list")
    Call<ResponseData> checkAuth(@Field("auth") String auth);

    @FormUrlEncoded
    @POST("api/good")
    Call<ResponseInfo> downInfo(@Field("auth") String auth, @Field("goodsId") String goodsId);

}
