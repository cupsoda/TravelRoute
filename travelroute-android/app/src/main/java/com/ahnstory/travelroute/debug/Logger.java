package com.ahnstory.travelroute.debug;

import android.util.Log;

import com.ahnstory.travelroute.config.AppConfiguration;

/**
 * Created by sohyunahn on 2017. 3. 14..
 */

public class Logger {
    public static void v(String className, String message) {
        Log.d("SSO", "isDebug?" + AppConfiguration.isDebug);
        if (AppConfiguration.isDebug) {
            Log.v(className, message);
        }
    }
}
