package com.learn.locations;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

  @Autowired private final ModelMapper modelMapper;
  private final AtomicLong idGenerator = new AtomicLong();
  List<Location> locations = new LinkedList<>();
  Type targetListType = new TypeToken<List<LocationDto>>() {}.getType();

  public LocationService(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    Location location1 =
        Location.builder()
            .id(idGenerator.incrementAndGet())
            .name("Budapest")
            .lat(120.0)
            .lon(229.0)
            .build();
    Location location2 =
        Location.builder()
            .id(idGenerator.incrementAndGet())
            .name("NewDelhi")
            .lat(200.0)
            .lon(300.0)
            .build();
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

  private Location filterLocationById(long id) {
    return locations.stream()
        .filter(l -> l.getId() == id)
        .findFirst()
        .orElseThrow(
            () ->
                new LocationNotFoundException(String.format("Location with id %d not found", id)));
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

  public LocationDto getLocationById(Long id) {
    Location location = filterLocationById(id);
    return modelMapper.map(location, LocationDto.class);
  }

  public LocationDto createLocation(CreateLocationCommand locationCommand) {
    Location location = modelMapper.map(locationCommand, Location.class);
    location.setId(idGenerator.incrementAndGet());
    locations.add(location);
    return modelMapper.map(location, LocationDto.class);
  }

  public LocationDto updateLocation(long id, UpdateLocationCommand locationCommand) {
    Location location = filterLocationById(id);
    location.setLat(locationCommand.getLat());
    location.setLon(locationCommand.getLon());
    location.setName(locationCommand.getName());
    return modelMapper.map(location, LocationDto.class);
  }

  public void deleteLocation(long id) {
    Location location = filterLocationById(id);
    locations.remove(location);
  }
}
