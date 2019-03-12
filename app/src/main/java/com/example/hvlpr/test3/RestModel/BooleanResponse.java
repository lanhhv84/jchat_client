package com.example.hvlpr.test3.RestModel;

import com.google.gson.annotations.SerializedName;

public class BooleanResponse {
    @SerializedName("value")
    private Boolean value;

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
