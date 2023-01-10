package com.learn.locations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class LocationControllerTest extends LocationsTestHelper {

  @InjectMocks LocationController locationController;

  @Mock LocationService locationService;

  @Test
  void testGetLocations() {
    when(locationService.getLocations(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()))
        .thenReturn(
            Stream.of(
                    LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build())
                .toList());
    assertThat(
            locationController
                .getLocations(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty())
                .get(0))
        .isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
  }
}
