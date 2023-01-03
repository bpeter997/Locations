package com.learn.locations;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = LocationController.class)
class LocationControllerWebMvcIT {

  @MockBean LocationService locationService;

  @Autowired MockMvc mockMvc;

  @Test
  void testGetLocations() throws Exception {
    when(locationService.getLocations(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()))
        .thenReturn(
            Stream.of(
                    LocationDto.builder().id((long) 1.0).name("Budapest").lat(1.0).lon(1.0).build())
                .collect(Collectors.toList()));
    mockMvc
        .perform(get("/api/locations"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name", equalTo("Budapest")));
  }
}
