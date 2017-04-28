package com.example.anderson.webcoleta.model;

/**
 * Created by Anderson on 23/04/2017.
 */

public class GarbagePlaceGeosonList {

    private GarbagePlace[] features;

    private String type;

    public GarbagePlace[] getFeatures ()
    {
        return features;
    }

    public void setFeatures (GarbagePlace[] features)
    {
        this.features = features;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [features = "+features+", type = "+type+"]";
    }
}
