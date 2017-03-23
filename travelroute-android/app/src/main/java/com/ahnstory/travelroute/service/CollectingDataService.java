package com.ahnstory.travelroute.service;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ahnstory.travelroute.debug.Logger;
import com.ahnstory.travelroute.observer.PhotoTakenObserver;

/**
 * Created by sohyunahn on 2017. 3. 14..
 */

public class CollectingDataService extends Service {

    @NonNull private final Handler handler = new Handler();

    @Nullable private ContentObserver contentObserver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.v(getClass().getSimpleName(), "onStartCommand");
        contentObserver = new PhotoTakenObserver(this, handler);
        // TODO @SSO 동영상이나 DCIM 에 저장되어 있는 사진 정보도 가져오기
        getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, contentObserver);
        getContentResolver().registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, contentObserver);
//        getContentResolver().registerContentObserver(Uri.fromFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)), true, contentObserver);
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Logger.v(getClass().getSimpleName(), "onDestroy");
        getContentResolver().unregisterContentObserver(contentObserver);
        super.onDestroy();
    }
}
