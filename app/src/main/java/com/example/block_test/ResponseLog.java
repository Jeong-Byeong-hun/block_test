package com.example.block_test;

import com.google.gson.annotations.SerializedName;

// api/reg api/log랑 같다 같이 써도 무방

public class ResponseLog {
    @SerializedName("code")
    public String code;

    @SerializedName("data")
    public String data;
}
