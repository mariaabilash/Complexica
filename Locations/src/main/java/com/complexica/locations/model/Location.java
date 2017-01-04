package com.complexica.locations.model;

public class Location
{
    private String latitude;
    private String longitude;
    private String title;
    private String address;

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
