package com.complexica.locations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.complexica.locations.model.Location;
import com.complexica.locations.service.LocationService;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping("/get")
    public List<Location> getLocations()
    {
        return locationService.getLocations();
    }

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public void postLocations(@RequestBody List<Location> locations)
    {
        locationService.saveLocations(locations);
    }

    @RequestMapping("/getLocationsFromCSV")
    public List<Location> getLocationsFromCSV()
    {
        return locationService.getLocationsFromCSV();
    }
}
