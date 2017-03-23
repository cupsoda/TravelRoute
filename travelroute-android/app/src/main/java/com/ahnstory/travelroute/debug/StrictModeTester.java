package com.ahnstory.travelroute.debug;

import android.os.StrictMode;

import com.ahnstory.travelroute.config.AppConfiguration;

/**
 * Created by Sohyun Ahn on 2017. 3. 23..
 * email: coffeesoda13@gmail.com
 */

public class StrictModeTester {
    public static void setStrictMode() {
        if (AppConfiguration.isDebug) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
    }
}
