package com.learn.locations;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocationServiceTest {

    @Test
    void testGetLocations() {
        LocationService locationService = new LocationService();
        List<Location> locations = locationService.getLocations();
        assertThat(locations.get(0)).isEqualTo(Location.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
    }
}
