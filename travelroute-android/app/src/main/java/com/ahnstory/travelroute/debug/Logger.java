package com.ahnstory.travelroute.debug;

import android.util.Log;

import com.ahnstory.travelroute.config.AppConfiguration;

/**
 * Created by sohyunahn on 2017. 3. 14..
 */

public class Logger {
    public static void v(String className, String message) {
        if (AppConfiguration.isDebug) {
            Log.v(className, message);
        }
    }

    public static void w(String className, String message) {
        Log.w(className, message);
    }

    public static void w(String className, String message, Exception e) {
        // TODO @SSO warn 이상의 log 는 firebase or crashlytics 에 전송하기
        Log.w(className, message, e);
    }
}
