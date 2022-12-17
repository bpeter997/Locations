package com.learn.locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocationServiceTest {

    @Test
    void testGetLocations() {
        LocationService locationService = new LocationService(new ModelMapper());
        List<LocationDto> locations = locationService.getLocations(null);
        assertThat(locations.get(0)).isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
    }

    @Test
    void testGetLocationByName() {
        LocationService locationService = new LocationService(new ModelMapper());
        List<LocationDto> locations = locationService.getLocations("Budape");
        assertThat(locations.get(0)).isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
    }

    @Test
    void testGetLocationById() {
        LocationService locationService = new LocationService(new ModelMapper());
        List<LocationDto> locations = locationService.getLocationById((long) 1.0);
        assertThat(locations.get(0)).isEqualTo(LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());
    }
}
