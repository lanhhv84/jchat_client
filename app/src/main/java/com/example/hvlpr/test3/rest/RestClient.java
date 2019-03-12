package com.example.hvlpr.test3.rest;

import com.loopj.android.http.*;

public class RestClient {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static final String BASE_URL = "45.32.101.238:8080";

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


}
