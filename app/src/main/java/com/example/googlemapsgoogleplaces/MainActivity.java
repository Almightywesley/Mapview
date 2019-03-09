package com.example.googlemapsgoogleplaces;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.security.KeyStore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    if (isServecisOK()) {
        init();
    }
    };
    private void init(){
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean isServecisOK(){
        Log.d(TAG,"isServesecOK: chec" +
                "" +
                "king google version");

                int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available== ConnectionResult.SUCCESS){
            //ALL GOOOD
            Log.d(TAG,"isServecisOK: Google Play Services is working");
            return true;
        }

        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //error but fixable
            Log.d(TAG,"isServiceOK: an error occurd but fixable :)");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this,"You cant map request",Toast.LENGTH_SHORT).show();

        }
        return false;


    }
}
