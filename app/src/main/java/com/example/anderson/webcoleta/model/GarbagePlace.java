package com.example.anderson.webcoleta.model;

import java.io.Serializable;

/**
 * Created by Anderson on 12/04/2017.
 */

public class GarbagePlace implements Serializable {

    private String id;

    private GarbagePlaceInfo properties;

    private String type;

   // private Geometry geometry;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public GarbagePlaceInfo getProperties ()
    {
        return properties;
    }

    public void setProperties (GarbagePlaceInfo properties)
    {
        this.properties = properties;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    /* public Geometry getGeometry ()
    {
        return geometry;
    }

    public void setGeometry (Geometry geometry)
    {
        this.geometry = geometry;
    }
*/

    @Override
    public String toString()
    {
       // return "ClassPojo [id = "+id+", properties = "+properties+", type = "+type+", geometry = "+geometry+"]";
        return "ClassPojo [id = "+id+", properties = "+properties+", type = "+type+"]";
    }


}
