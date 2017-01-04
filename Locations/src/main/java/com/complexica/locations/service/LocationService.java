package com.complexica.locations.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
        persistList(locations);
    }

    public List<Location> getLocations()
    {
        return readList();
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
        //Collections.sort(locations, (l1, l2) -> l1.getTitle().compareTo(l2.getTitle()));
        return sortLocations(locations);
    }

    private List<Location> sortLocations(List<Location> locations)
    {
        Location[] locationArray = locations.toArray(new Location[locations.size()]);
        for (int i=0; i < locationArray.length; i++)
        {
            for (int j=i+1; j < locationArray.length; j++)
            {
                if (locationArray[i].getTitle().compareTo(locationArray[j].getTitle()) > 0)
                {
                    Location tempLocation = locationArray[i];
                    locationArray[i] = locationArray[j];
                    locationArray[j] = tempLocation;
                }
            }
        }
        return new ArrayList<Location>(Arrays.asList(locationArray));
    }
    
    private void persistList(List<Location> locations)
    {
        try
        {
            FileOutputStream fileOut= new FileOutputStream (ResourceUtils.getFile("classpath:location.tmp"));
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOut);
            objectOutStream.writeObject(locations);
            fileOut.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
    
    private List<Location> readList()
    {
        List<Location> locations = new ArrayList<Location>();
        try
        {
            FileInputStream fileIn= new FileInputStream (ResourceUtils.getFile("classpath:location.tmp"));
            if (fileIn.available() != 0)
            {
                ObjectInputStream objectInStream = new ObjectInputStream(fileIn);
                locations= (ArrayList<Location>)objectInStream.readObject();
                objectInStream.close();
            }
            fileIn.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return locations;
    }
}
