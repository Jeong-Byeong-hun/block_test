package com.example.block_test;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {
    @SerializedName("code")
    public String code;
    @SerializedName("data")
    public List<goods> data = new ArrayList();

    public class goods {
        @SerializedName("goodsTitle")
        public String goodsTitle;
        @SerializedName("goodsId")
        public String goodsId;

    }
}
