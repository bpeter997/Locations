package com.learn.locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @InjectMocks
    LocationController locationController;

    @Mock
    LocationService locationService;

    @Test
    void testGetLocations() {
        when(locationService.getLocations()).thenReturn(Stream.of(Location.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build()).toList());
        assertThat(locationController.getLocations().get(0)).isEqualTo(Location.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build());

    }
}
