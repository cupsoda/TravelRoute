package com.ahnstory.travelroute.observer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ahnstory.travelroute.config.AppConfiguration;
import com.ahnstory.travelroute.debug.Logger;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by sohyunahn on 2017. 3. 14..
 */

public class PhotoTakenObserver extends ContentObserver {

    private PublishSubject<Uri> changedEvent = PublishSubject.create();
    @NonNull private final Context context;
    public PhotoTakenObserver(final Context context, Handler handler){
        super(handler);
        this.context = context;

        changedEvent.flatMap(new Func1<Uri, Observable<Uri>>() {
            @Override
            public Observable<Uri> call(Uri uri) {
                // TODO Uri type 에 따라 쿼리를 달리 보내기
                // TODO 퍼미션 요청 처리하기
                if (uri.toString().contains(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString())) {
                    String[] splitedText = uri.toString().split("/");
                    Cursor cursor = MediaStore.Images.Media.query(context.getContentResolver(), uri, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        while (cursor.moveToNext()) {
                            String[] columnNames = cursor.getColumnNames();
                            for (String columnName : columnNames) {
                                int columnIndex = cursor.getColumnIndex(columnName);
                                String value = cursor.getString(columnIndex);
                                Log.d("SSO", columnName + ": " + value);
                            }
                        }
                    }
//                    context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, splitedText[splitedText.length - 1], )
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
                // TODO @SSO 위치 정보를 찾아내어 지도에 표시
                Logger.v(getClass().getSimpleName(), "onChanged: " + uri.toString());
                changedEvent.onNext(uri);
                Log.d("SSO", uri.toString());
//                context.getContentResolver().query(uri, )
            }
        }
    }

    private void processUri(Uri uri) {

    }
}
