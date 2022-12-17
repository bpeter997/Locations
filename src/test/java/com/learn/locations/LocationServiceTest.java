package com.learn.locations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

class LocationServiceTest extends LocationsTestHelper {

  @Test
  void testGetLocations() {
    LocationService locationService = new LocationService(new ModelMapper());
    List<LocationDto> locations =
        locationService.getLocations(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
    assertThat(locations.get(0)).isEqualTo(generateLocationDto(1, "Budapest", 120, 229));
  }

  @Test
  void testGetLocationByName() {
    LocationService locationService = new LocationService(new ModelMapper());
    List<LocationDto> locations =
        locationService.getLocations(
            "Budape".describeConstable(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
    assertThat(locations.get(0)).isEqualTo(generateLocationDto(1, "Budapest", 120, 229));
  }

  @Test
  void testGetLocationById() {
    LocationService locationService = new LocationService(new ModelMapper());
    List<LocationDto> locations = locationService.getLocationById((long) 1.0);
    assertThat(locations.get(0)).isEqualTo(generateLocationDto(1, "Budapest", 120, 229));
  }

  @Test
  void testGetLocationByLonInterval() {
    LocationService locationService = new LocationService(new ModelMapper());
    List<LocationDto> locations =
        locationService.getLocations(
            Optional.empty(),
            Optional.of(120.0),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
    assertEquals(2, locations.size());

    locations =
        locationService.getLocations(
            Optional.empty(),
            Optional.of(121.0),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
    assertEquals(1, locations.size());

    locations =
        locationService.getLocations(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(300.0));
    assertEquals(2, locations.size());

    locations =
        locationService.getLocations(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(299.0));
    assertEquals(1, locations.size());

    locations =
        locationService.getLocations(
            Optional.empty(),
            Optional.of(150.0),
            Optional.of(230.0),
            Optional.of(300.0),
            Optional.of(305.0));
    assertEquals(1, locations.size());
  }
}
