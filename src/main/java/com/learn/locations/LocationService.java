package com.learn.locations;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

  @Autowired private final ModelMapper modelMapper;
  List<Location> locations = new LinkedList<>();
  Type targetListType = new TypeToken<List<LocationDto>>() {}.getType();

  public LocationService(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    Location location1 =
        Location.builder().id((long) 1.0).name("Budapest").lat(120.0).lon(229.0).build();
    Location location2 =
        Location.builder().id((long) 2.0).name("NewDelhi").lat(200.0).lon(300.0).build();
    locations.addAll(Stream.of(location1, location2).toList());
  }

  private static Predicate<Location> filterByName(Optional<String> name) {
    return location ->
        name.isEmpty() || location.getName().toLowerCase().startsWith(name.get().toLowerCase());
  }

  private static Predicate<Location> filterByLat(Optional<Double> minLat, Optional<Double> maxLat) {
    return location ->
        (minLat.isEmpty() || location.getLat() >= minLat.get())
            && (maxLat.isEmpty() || location.getLat() <= maxLat.get());
  }

  private static Predicate<Location> filterByLon(Optional<Double> minLon, Optional<Double> maxLon) {
    return location ->
        (minLon.isEmpty() || location.getLon() >= minLon.get())
            && (maxLon.isEmpty() || location.getLon() <= maxLon.get());
  }

  public List<LocationDto> getLocations(
      Optional<String> name,
      Optional<Double> minLat,
      Optional<Double> minLon,
      Optional<Double> maxLat,
      Optional<Double> maxLon) {
    List<Location> locationByName =
        locations.stream()
            .filter(filterByName(name))
            .filter(filterByLat(minLat, maxLat))
            .filter(filterByLon(minLon, maxLon))
            .toList();
    return modelMapper.map(locationByName, targetListType);
  }

  public List<LocationDto> getLocationById(Long id) {
    List<Location> locationByName =
        locations.stream().filter(location -> location.getId().equals(id)).toList();
    return modelMapper.map(locationByName, targetListType);
  }
}
