package com.learn.locations;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class LocationService {

  private final LocationMapper locationMapper;

  private final LocationRepository locationRepository;

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

  private static Supplier<LocationNotFoundException> getLocationNotFoundExceptionSupplier(Long id) {
    return () -> new LocationNotFoundException(String.format("Location with id %d not found", id));
  }

  public List<LocationDto> getLocations(
      Optional<String> name,
      Optional<Double> minLat,
      Optional<Double> minLon,
      Optional<Double> maxLat,
      Optional<Double> maxLon) {
    List<Location> locations = locationRepository.findAll();
    if (locations.isEmpty()) {
      throw new LocationNotFoundException("Locations not found");
    }
    List<Location> filteredLocations =
        locations.stream()
            .filter(filterByName(name))
            .filter(filterByLat(minLat, maxLat))
            .filter(filterByLon(minLon, maxLon))
            .toList();
    return locationMapper.locationToLocationDto(filteredLocations);
  }

  public LocationDto getLocationById(Long id) {
    Location location =
        locationRepository.findById(id).orElseThrow(getLocationNotFoundExceptionSupplier(id));
    return locationMapper.locationToLocationDto(location);
  }

  public LocationDto createLocation(CreateLocationCommand locationCommand) {
    Location location = locationMapper.createLocationCommandToLocation(locationCommand);
    locationRepository.save(location);
    log.info("Request with the following id {} is created.", location.getId());
    return locationMapper.locationToLocationDto(location);
  }

  @Transactional
  public LocationDto updateLocation(long id, UpdateLocationCommand locationCommand) {
    Location location =
        locationRepository.findById(id).orElseThrow(getLocationNotFoundExceptionSupplier(id));

    location.setName(locationCommand.getName());
    location.setLat(locationCommand.getLat());
    location.setLon(locationCommand.getLon());
    locationRepository.save(location);
    log.info("Request with the following id {} is updated.", id);
    return locationMapper.locationToLocationDto(location);
  }

  public void deleteLocation(long id) {
    locationRepository.deleteById(id);
    log.info("Request with the following id {} is deleted.", id);
  }

  public void deleteAllLocations() {
    locationRepository.deleteAll();
  }
}
