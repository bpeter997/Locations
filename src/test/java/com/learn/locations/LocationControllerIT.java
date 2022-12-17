package com.learn.locations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocationControllerIT {

  @Autowired LocationController locationController;

  @Test
  void testGetLocations() {
    List<LocationDto> locations =
        locationController.getLocations(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
    assertThat(locations.get(0))
        .isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
  }

  @Test
  void testGetLocationsByName() {
    List<LocationDto> locations =
        locationController.getLocations(
            "Budapest".describeConstable(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
    assertThat(locations.get(0))
        .isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
  }

  @Test
  void testGetLocationsById() {
    List<LocationDto> locations = locationController.getLocationById((long) 1.0);
    assertThat(locations.get(0))
        .isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
  }
}
