package com.greed.ging.contentappmy;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import static android.Manifest.permission.*;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permission = ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS);
        if (permission != PackageManager.PERMISSION_GRANTED){
            //未取得權限，向使用者要求允許
            ActivityCompat.requestPermissions(this, new String[]{READ_CONTACTS, WRITE_CONTACTS},
                    REQUEST_CONTACTS);
        }else{
            //已有權限
            readContact();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CONTACTS:
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContact();
                }else {
                    //使用者拒絕，顯示對話框告知
                    new AlertDialog.Builder(this)
                            .setMessage("必須允許權限才能顯示")
                            .setPositiveButton("OK", null)
                            .show();
                }
                return;
        }
    }

    private void readContact() {
    }
}
