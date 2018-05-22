package com.bobbythorne.ledcontroller;

import java.util.UUID;

/**
 * Created by Thorne on 9/13/2016.
 */
public class Preset {
    private UUID mId;
    private String mTitle;
    private String mType;


    public Preset() {
        //Denerate unique identifier
        this(UUID.randomUUID());
        mType = "Solid";
        mTitle = "New Preset";
    }

    public Preset(UUID id) {
        mId = id;
        mType = "Solid";
        mTitle = "New Preset";
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
