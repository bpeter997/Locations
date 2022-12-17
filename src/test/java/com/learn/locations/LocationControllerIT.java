package com.learn.locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class LocationControllerIT {

    @Autowired
    LocationController locationController;

    @Test
    void testGetLocations() {
        List<LocationDto> locations = locationController.getLocations(null);
        assertThat(locations.get(0)).isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
    }

    @Test
    void testGetLocationsByName() {
        List<LocationDto> locations = locationController.getLocations("Budapest");
        assertThat(locations.get(0)).isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
    }

    @Test
    void testGetLocationsById() {
        List<LocationDto> locations = locationController.getLocationById((long) 1.0);
        assertThat(locations.get(0)).isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
    }
}
