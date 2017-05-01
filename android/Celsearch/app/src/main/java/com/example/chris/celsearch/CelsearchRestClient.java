package com.example.chris.celsearch;

import com.loopj.android.http.*;

public class CelsearchRestClient {

    // This string must be set to the server's IP address
    private static final String IP_ADDRESS = "http://10.0.2.2:3000";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    /**
     * Post a query to the server
     * @param url This will be either mitsuku or wiki, followed by '/', followed by the sender's number
     */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(IP_ADDRESS + "/celsearch/" + url, params, responseHandler);
    }
}
