package com.ahnstory.travelroute.database.model;

import java.util.Date;

/**
 * Created by sohyunahn on 2017. 3. 15..
 */

public class MediaMarker {

    private long mediaId;
    private Date takenDate;
    private String mediaPath;
    private String mimeType;
    private double latitude;
    private double longitude;
    private int orientation;

    public MediaMarker(long mediaId, Date takenDate, String mediaPath, String mimeType, double latitude, double longitude) {
        this(mediaId, takenDate, mediaPath, mimeType, latitude, longitude, 0);
    }

    public MediaMarker(long mediaId, Date takenDate, String mediaPath, String mimeType, double latitude, double longitude, int orientation) {
        this.mediaId = mediaId;
        this.takenDate = takenDate;
        this.mediaPath = mediaPath;
        this.mimeType = mimeType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "mediaId=" + mediaId + ", takenDate=" + takenDate + ", mediaPath=" + mediaPath + "mimeType=" + mimeType + ", latitude=" + latitude + ", longitude=" + longitude + ", orientation=" + orientation;
    }
}
