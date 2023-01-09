package com.learn.locations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LocationControllerRestTemplateIT {

  @Autowired TestRestTemplate template;

  @Test
  void testGetLocations() {

    LocationDto location =
        template.postForObject(
            "/api/locations",
            CreateLocationCommand.builder().name("Budapest").lat(120.0).lon(229.0).build(),
            LocationDto.class);

    assertThat("Budapest").isEqualTo(location.getName());

    List<LocationDto> locations =
        template
            .exchange(
                "/api/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {})
            .getBody();

    assert locations != null;
    assertThat(locations.get(0).getName()).isEqualTo("Budapest");
  }
}
