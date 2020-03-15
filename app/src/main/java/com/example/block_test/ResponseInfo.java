package com.example.block_test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseInfo {
    @SerializedName("code")
    public String code;

    @SerializedName("data")
    List<ResponseInfo.data> data;

    public class data {
        @SerializedName("lang")
        public String lang;
        @SerializedName("goodId")
        public String goodId;
        @SerializedName("goodTitle")
        public String goodTitle;
        @SerializedName("price")
        public int price;
        @SerializedName("category")
        public String category;
        @SerializedName("size")
        public String size;
        @SerializedName("imgPaths")
        public List<String> imgPaths;
        @SerializedName("mp4Paths")
        public List<String> mp4Paths;
    }



}
