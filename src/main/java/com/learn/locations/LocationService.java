package com.learn.locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class LocationService {

    @Autowired
    private final ModelMapper modelMapper;
    List<Location> locations = new LinkedList<>();
    Type targetListType = new TypeToken<List<LocationDto>>() {
    }.getType();

    public LocationService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        Location location1 = Location.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build();
        Location location2 = Location.builder().id((long) 2.0).name("NewDelhi").lat(1.0).lon(1.0).build();
        locations.addAll(Stream.of(location1, location2).toList());
    }

    public List<LocationDto> getLocations(String name) {
        List<Location> locationByName = locations.stream().filter(location -> name == null || name.isEmpty() || location.getName().toLowerCase().startsWith(name.toLowerCase())).toList();
        return modelMapper.map(locationByName, targetListType);
    }

    public List<LocationDto> getLocationById(Long id) {
        List<Location> locationByName = locations.stream().filter(location -> location.getId().equals(id)).toList();
        return modelMapper.map(locationByName, targetListType);
    }
}
