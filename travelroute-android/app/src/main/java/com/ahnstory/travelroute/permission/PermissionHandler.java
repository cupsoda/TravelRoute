package com.ahnstory.travelroute.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by sohyunahn on 2017. 3. 15..
 */

public class PermissionHandler {
    public static int ACTIVITY_PERMISSIONS_REQUEST_CODE = 1;

    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity activity, String[] permissions) {
        if (activity != null) {
            ActivityCompat.requestPermissions(activity, permissions, ACTIVITY_PERMISSIONS_REQUEST_CODE);
        }
    }
}
