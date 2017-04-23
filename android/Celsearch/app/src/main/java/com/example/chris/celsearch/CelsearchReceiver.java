package com.example.chris.celsearch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class CelsearchReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("TAG", "chris debug: We are in onReceive.");
        /*SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        // check each message
        for (SmsMessage message : messages) {

            // get the address from which it came
            String sender = message.getOriginatingAddress();

            // get the text from the message
            String body = message.getMessageBody();

            if (body.startsWith("CS")) {
                Log.v("TAG", "chris debug: " + body.substring(3, body.length()));
            }

            // this is used to keep the message from propogating to other broadcast receivers
            this.abortBroadcast();*/
        final Bundle bundle = intent.getExtras();
        if (bundle != null) {
            final Object[] pdusObj = (Object[]) bundle.get("pdus");
            String format = bundle.getString("format");
            if (pdusObj == null) {
                return;
            }
            for (int i = 0; i < pdusObj.length; i++) {
                final SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                String message = currentMessage.getMessageBody();
                if (message.startsWith("CS")) {
                    Log.v("TAG", "chris debug: " + message.substring(3, message.length()));
                }
            }
            this.abortBroadcast();
        }
    }
}