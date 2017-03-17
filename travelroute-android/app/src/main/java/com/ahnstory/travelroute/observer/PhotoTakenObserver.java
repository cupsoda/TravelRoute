package com.ahnstory.travelroute.observer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.ahnstory.travelroute.config.AppConfiguration;
import com.ahnstory.travelroute.database.model.MediaMarker;
import com.ahnstory.travelroute.debug.Logger;
import com.ahnstory.travelroute.permission.PermissionHandler;

import java.util.Date;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by Sohyun Ahn on 2017. 3. 14..
 * email: coffeesoda13@gmail.com
 */

public class PhotoTakenObserver extends ContentObserver {

    private PublishSubject<Uri> changedEvent = PublishSubject.create();
    @NonNull private final Context context;
    public PhotoTakenObserver(final Context context, Handler handler){
        super(handler);
        this.context = context;

        changedEvent.flatMap(new Func1<Uri, Observable<MediaMarker>>() {
            @Override
            public Observable<MediaMarker> call(Uri uri) {
                // TODO @SSO Uri type 에 따라 쿼리를 달리 보내기
                // android.permission.READ_EXTERNAL_STORAGE 퍼미션을 받았는지 체크한다
                if (PermissionHandler.checkPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (uri.toString().contains(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString())) {
                        return Observable.just(processDataInMediaStoreImageUri(uri));
                    }
                } else {
                    // TODO @SSO 권한 승인을 받지 못한 경우에 대한 메시지 보여주기

                }
                return Observable.never();
            }
        }).subscribe();
    }

    @Override
    public void onChange(boolean selfChange) {
        if (AppConfiguration.ANDROID_VERSION < Build.VERSION_CODES.JELLY_BEAN) {
            super.onChange(selfChange);
            // TODO @SSO API 15 이하는 uri 정보가 없다. 여기에서 하위 버전 단말 처리하기
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onChange(boolean selfChange, Uri uri) {
        if (AppConfiguration.ANDROID_VERSION >= Build.VERSION_CODES.JELLY_BEAN) {
            super.onChange(selfChange, uri);
            if (!selfChange) {
                Logger.v(getClass().getSimpleName(), "onChanged: " + uri.toString());
                changedEvent.onNext(uri);
            }
        }
    }

    private MediaMarker processDataInMediaStoreImageUri(Uri uri) {
        MediaMarker mediaMarker = null;
        String[] projections = new String[]{
                android.provider.BaseColumns._ID,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.MediaColumns.DATA,
                MediaStore.MediaColumns.MIME_TYPE,
                MediaStore.Images.ImageColumns.LATITUDE,
                MediaStore.Images.ImageColumns.LONGITUDE,
                MediaStore.Images.ImageColumns.ORIENTATION,
        };
        Cursor cursor = context.getContentResolver().query(uri, projections, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndex(projections[0]));
                Date takenDate = new Date(cursor.getLong(cursor.getColumnIndex(projections[1])));
                String mediaPath = cursor.getString(cursor.getColumnIndex(projections[2]));
                String mimeType = null;
                try {
                    mimeType = cursor.getString(cursor.getColumnIndex(projections[3]));
                } catch (Exception e) {
                    Logger.w(getClass().getSimpleName(), "MimeType is null.", e);
                }

                // TODO @SSO 위치 정보가 없으면 정보를 찾아내어 지도에 표시
                double latitude = cursor.getDouble(cursor.getColumnIndex(projections[4]));
                double longitude = cursor.getDouble(cursor.getColumnIndex(projections[5]));
                int orientation = cursor.getInt(cursor.getColumnIndex(projections[6]));
                mediaMarker = new MediaMarker(id, takenDate, mediaPath, mimeType, latitude, longitude, orientation);
            }
            cursor.close();
        }
        return mediaMarker;
    }
}
