package com.example.chris.celsearch;

import com.loopj.android.http.*;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                if (message.startsWith("CS")) {

                    // remove the CS from the beginning of the message
                    String query = message.substring(3, message.length());

                    // get the phone number of the sender so that we can send the answer back later
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    // debugging
                    Log.v("TAG", "chris debug: " + query);

                    try {
                        // send query to REST server
                        getQueryAnswer(query, phoneNumber);
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
     * Receive answer from REST API
     */
    public void getQueryAnswer(String query, String number) throws JSONException {
        // create parameters to send to REST server
        RequestParams params = new RequestParams();
        // add the query string in from the text
        params.put("query", query);
        // add the number of the phone that sent the text
        params.put("number", number);
        //TODO: fill in the address we are sending it to!
        CelsearchRestClient.get("", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // debugging
                Log.v("TAG", "chris debug: we have a response in JSONObject onSuccess method");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // debugging
                Log.v("TAG", "chris debug: we have a response in JSONArray onSuccess method");
            }
        });
    }
}