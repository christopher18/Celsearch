package com.example.chris.celsearch;

import com.loopj.android.http.*;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CelsearchReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // debugging
        Log.v("TAG", "chris debug: We are in onReceive.");

        final Bundle bundle = intent.getExtras();
        if (bundle != null) {
            final Object[] pdusObj = (Object[]) bundle.get("pdus");
            String format = bundle.getString("format");
            if (pdusObj == null) {
                return;
            }
            for (int i = 0; i < pdusObj.length; i++) {
                // get the message object
                final SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);

                // extract the body of the message from the SmsMessage object
                String message = currentMessage.getMessageBody();

                // if the message starts with CS we should process it
                if (message.startsWith("@wiki")) {

                    // remove the CS from the beginning of the message
                    String query = message.substring(6, message.length());

                    // get the phone number of the sender so that we can send the answer back later
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    // debugging
                    Log.v("TAG", "chris debug: " + query);

                    try {
                        // send query to REST server
                        getQueryAnswer(query, phoneNumber, "wikipedia");
                    } catch (JSONException e) {
                        Log.v("TAG", "chris debug: JSON exception occurred");
                        e.printStackTrace();
                    }
                } else if (message.startsWith("@mitsuku")) {
                    // remove the CS from the beginning of the message
                    String query = message.substring(9, message.length());

                    // get the phone number of the sender so that we can send the answer back later
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    try {
                        // send query to REST server
                        getQueryAnswer(query, phoneNumber, "mitsuku");
                    } catch (JSONException e) {
                        Log.v("TAG", "chris debug: JSON exception occurred");
                        e.printStackTrace();
                    }
                }


            }
            this.abortBroadcast();
        }
    }

    /**
     * Send to and receive answer from REST server
     * @param type Will be either 'mitsuku' or 'wiki'
     */
    public void getQueryAnswer(String query, final String number, String type) throws JSONException {
        // create parameters to send to REST server
        RequestParams params = new RequestParams();
        // add the query string in from the text
        params.put("query", query);
        // add the number of the phone that sent the text
        params.put("number", number);

        CelsearchRestClient.post(type + "/" + number, params, new AsyncHttpResponseHandler() {
            
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error)
            {
                // Response failed
                if (headers == null) {
                    Log.v("TAG", "IF");
                } else {
                    Log.v("TAG", "else");
                }
                Log.v("TAG", "SIZE IS : " + headers.length);
                for (Header header : headers) {
                    Log.v("TAG", "HERE");
                    Log.v("TAG", "chris debug: onFailure: " + header.getName());
                }
                String response = "";
                try {
                    Log.v("TAG", "chris debug: before responsebody conversion");
                    response = new String(responseBody, "UTF-8");
                    Log.v("TAG", "chris debug: after responsebody conversion");
                    Log.v("TAG", "chris debug: onFailure: " + response);
                } catch (UnsupportedEncodingException e) {
                    response = "error";
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Successfully got a response
                Log.v("TAG", "chris debug: onSuccess 2");
                String response = "";
                try {
                    response = new String(responseBody, "UTF-8");

                    // prepare to send result back to the phone
                    SmsManager smsManager = SmsManager.getDefault();

                    // Divide the response into pieces small enough to be send via SMS
                    ArrayList<String> pieces = smsManager.divideMessage(response);

                    // send the text message or messages with the result
                    for (String piece : pieces) {
                        smsManager.sendTextMessage(number, null, piece, null, null);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    response = "error";
                    e.printStackTrace();
                }
            }

            @Override
            public void onStart() {
                // Initiated the request
                Log.v("TAG", "debug: onStart ");
            }


            @Override
            public void onRetry(int retryNo) {
                // Request was retried
                Log.v("TAG", "onRetry ");
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                // Progress notification
                Log.v("TAG", "onProgress");
            }

            @Override
            public void onFinish() {
                // Completed the request (either success or failure)
                Log.v("TAG", "onFinish");
            }
        });
    }
}
