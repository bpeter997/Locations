package com.learn.locations;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class LocationService {

    List<Location> locations = new LinkedList<>();

    public List<Location> getLocations() {
        Location location = Location.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build();
        locations.add(location);
        return locations;
    }
}
