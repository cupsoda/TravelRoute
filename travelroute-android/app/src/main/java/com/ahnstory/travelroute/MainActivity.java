package com.ahnstory.travelroute;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahnstory.travelroute.debug.StrictModeTester;
import com.ahnstory.travelroute.permission.PermissionHandler;
import com.ahnstory.travelroute.service.CollectingDataService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictModeTester.setStrictMode();
        setContentView(R.layout.activity_main);

        startService(new Intent(this, CollectingDataService.class));

        requestPermissions();
    }

    /**
     * 앱에 필요한 권한을 승인 요청한다
     */
    private void requestPermissions() {
        if (!PermissionHandler.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            PermissionHandler.requestPermission(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
        }
    }
}
