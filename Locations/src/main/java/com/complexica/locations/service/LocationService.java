package com.complexica.locations.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.complexica.locations.model.Location;
import au.com.bytecode.opencsv.CSVReader;

@Service
public class LocationService
{
    private List<Location> locations = new ArrayList<Location>();

    public void saveLocations(List<Location> locations)
    {
        this.locations.clear();
        this.locations.addAll(locations);
    }

    public List<Location> getLocations()
    {
        return locations;
    }

    public List<Location> getLocationsFromCSV()
    {
        List<Location> locations = new ArrayList<Location>();
        try
        {
            CSVReader readerLocation = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:locations.csv")));
            String[] nextLocation;
            readerLocation.readNext(); //Exclude the headers
            while ((nextLocation = readerLocation.readNext()) != null)
            {
                Location location = new Location();
                location.setLatitude(nextLocation[0]);
                location.setLongitude(nextLocation[1]);
                location.setTitle(nextLocation[2]);
                location.setAddress(nextLocation[3]);
                locations.add(location);
            }
            readerLocation.close();
        }
        catch (IOException expection)
        {
            expection.printStackTrace();
        }
        Collections.sort(locations, (l1, l2) -> l1.getTitle().compareTo(l2.getTitle()));
        return locations;
    }
}
