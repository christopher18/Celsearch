package com.example.chris.celsearch;

import com.loopj.android.http.*;

public class CelsearchRestClient {
    // TODO: The base url to which to send the query
    private static final String BASE_URL = "";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }
}
