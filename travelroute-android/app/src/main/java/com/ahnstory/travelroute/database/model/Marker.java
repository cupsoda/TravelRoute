package com.ahnstory.travelroute.database.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by sohyunahn on 2017. 3. 15..
 */

public class Marker extends RealmObject {
    @Required private Date date;
    private long latitude;
    private long longitude;
    private String mediaUri;
}
