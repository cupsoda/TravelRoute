package com.ahnstory.travelroute.observer;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

import com.ahnstory.travelroute.debug.Logger;

/**
 * Created by sohyunahn on 2017. 3. 14..
 */

public class PhotoTakenObserver extends ContentObserver {

    public PhotoTakenObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        if (!selfChange) {
            // TODO 위치 정보를 찾아내어 지도에 표시
            Logger.v(getClass().getSimpleName(), "onChanged: " + uri.toString());
        }
    }
}
