package com.learn.locations;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
  LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<LocationDto> getLocations(
      @RequestParam Optional<String> name,
      @RequestParam Optional<Double> minLat,
      @RequestParam Optional<Double> minLon,
      @RequestParam Optional<Double> maxLat,
      @RequestParam Optional<Double> maxLon) {
    return locationService.getLocations(name, minLat, minLon, maxLat, maxLon);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public LocationDto getLocationById(@PathVariable("id") long id) {
    return locationService.getLocationById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LocationDto createLocation(@RequestBody CreateLocationCommand locationCommand) {
    return locationService.createLocation(locationCommand);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public LocationDto updateLocation(
      @PathVariable("id") long id, @RequestBody UpdateLocationCommand locationCommand) {
    return locationService.updateLocation(id, locationCommand);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteLocation(@PathVariable("id") long id) {
    locationService.deleteLocation(id);
  }
}
