package com.learn.locations;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class LocationService {

    List<Location> locations = new LinkedList<>();

    public List<Location> getLocations() {
        Location location1 = Location.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build();
        Location location2 = Location.builder().id((long) 2.0).name("Delhi").lat(1.0).lon(1.0).build();
        locations.addAll(Stream.of(location1, location2).toList());
        return locations;
    }
}
