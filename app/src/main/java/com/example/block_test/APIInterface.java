package com.example.block_test;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface APIInterface {


    @FormUrlEncoded
    @POST("api/registe")
    Call<ResponseLog> register(@Field("id") String id,
                               @Field("password") String password,
                               @Field("mac") String mac,
                               @Field("name") String name);

    @FormUrlEncoded
    @POST("api/list")
    Call<ResponseData> checkAuth(@Field("id") String id,
                                 @Field("password") String password,
                                 @Field("mac") String mac);

    @FormUrlEncoded
    @POST("api/good")
    Call<ResponseInfo> downInfo(@Field("id") String id,
                                @Field("password") String password,
                                @Field("mac") String mac,
                                @Field("goodsId") String goodsId);

    @FormUrlEncoded
    @POST("api/log")
    Call<ResponseLog> postLog(@Field("id") String id,
                              @Field("password") String password,
                              @Field("mac") String mac,
                              @Field("goodsId") String goodsId,
                              @Field("storeId") String storeId,
                              @Field("deviceName") String deviceName,
                              @Field("shelfName") String shelfName,
                              @Field("sellPrice") String sellPrice,
                              @Field("goodsPrice") String goodsPrice,
                              @Field("goodsLang" ) String goodsLang,
                              @Field("goodsSize") String goodsSize,
                              @Field("goodsColor") String goodsColor,
                              @Field("memo") String memo);

}
