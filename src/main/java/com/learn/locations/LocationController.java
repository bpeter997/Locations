package com.learn.locations;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
  LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping
  public List<LocationDto> getLocations(
      @RequestParam Optional<String> name,
      @RequestParam Optional<Double> minLat,
      @RequestParam Optional<Double> minLon,
      @RequestParam Optional<Double> maxLat,
      @RequestParam Optional<Double> maxLon) {
    return locationService.getLocations(name, minLat, minLon, maxLat, maxLon);
  }

  @GetMapping("/{id}")
  public List<LocationDto> getLocationById(@PathVariable("id") long id) {
    return locationService.getLocationById(id);
  }
}
