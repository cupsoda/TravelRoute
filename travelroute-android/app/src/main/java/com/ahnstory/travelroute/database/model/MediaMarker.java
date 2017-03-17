package com.ahnstory.travelroute.database.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by sohyunahn on 2017. 3. 15..
 */

public class MediaMarker extends RealmObject {

    @Required private long id;
    @Required private Date takenDate;
    @Required private String mediaPath;
    @Required private String mimeType;
    private double latitude;
    private double longitude;
    private int orientation;

    public MediaMarker(long id, Date takenDate, String mediaPath, String mimeType, double latitude, double longitude, int orientation) {
        this.id = id;
        this.takenDate = takenDate;
        this.mediaPath = mediaPath;
        this.mimeType = mimeType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.orientation = orientation;
    }
}
