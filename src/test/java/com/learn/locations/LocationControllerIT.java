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
        List<Location> locations = locationController.getLocations();
        assertThat(locations.get(0)).isEqualTo(Location.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
    }
}
