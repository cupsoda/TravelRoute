package com.ahnstory.travelroute.config;


import android.os.Build;

import com.ahnstory.travelroute.BuildConfig;

/**
 * Created by sohyunahn on 2017. 3. 14..
 */

public class AppConfiguration {
    public static boolean isDebug = BuildConfig.DEBUG;
    public static int ANDROID_VERSION = Build.VERSION.SDK_INT;
}
