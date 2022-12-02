package com.learn.locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class LocationsApplicationTests {

    @Autowired
    private LocationController locationController;

    @Test
    void contextLoads() {
        assertThat(locationController).isNotNull();
    }

}
