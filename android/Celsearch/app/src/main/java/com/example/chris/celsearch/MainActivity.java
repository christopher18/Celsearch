package com.example.chris.celsearch;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;

    // edittext that will contain query
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            getPermissionToReadSMS();
        }
        // register the edittext
        edit = (EditText) findViewById(R.id.edittext);

        // check if we are here
        Log.v("TAG", "chris debug: We are alive.");
    }

    /**
     * Sends the query to the server using CelsearchReceiver's getQuery method
     * @param view the button that triggered this method
     */
    public void sendQuery(View view) {
        Log.v("TAG", "chris debug: send query 1");
        CelsearchReceiver receiver = new CelsearchReceiver();
        try {
            Log.v("TAG", "chris debug: send query 2");
            receiver.getQueryAnswer(edit.getText().toString(), "0");
            Log.v("TAG", "chris debug: send query 3");
            edit.setText("");
        } catch (JSONException e) {
            Log.v("TAG", "chris debug: JSON exception");
            e.printStackTrace();
        }
    }

    public void getPermissionToReadSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_SMS)) {
                Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_SMS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
