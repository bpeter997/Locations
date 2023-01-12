package com.learn.locations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from locations")
class LocationControllerWebClientIT {

  @Autowired WebTestClient webTestClient;

  @Autowired LocationService locationService;

  @BeforeEach
  void setUp() {
    locationService.deleteAllLocations();
    webTestClient
        .post()
        .uri("/api/locations")
        .bodyValue(new CreateLocationCommand("Budapest", 47.497912, 19.040235))
        .exchange();

    locationService.createLocation(new CreateLocationCommand("Róma", 41.90383, 12.50557));

    webTestClient
        .post()
        .uri("/api/locations")
        .bodyValue(new CreateLocationCommand("Athén", 37.97954, 23.72638))
        .exchange();
  }

  @Test
  void testCreateLocation() {
    webTestClient
        .post()
        .uri("/api/locations")
        .bodyValue(CreateLocationCommand.builder().name("Szeged").lon(35.0).lat(74.0).build())
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody()
        .jsonPath("name")
        .isEqualTo("Szeged");
  }

  @Test()
  void testCreateLocationValidationError() {
    webTestClient
        .post()
        .uri("/api/locations")
        .bodyValue(CreateLocationCommand.builder().name("").lon(35.0).lat(74.0).build())
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void testListLocations() {
    webTestClient
        .get()
        .uri("/api/locations")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(LocationDto.class)
        .hasSize(3);
  }

  @Test
  void testListLocationsWithQueryParams() {
    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/api/locations").queryParam("name", "buda").build())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(LocationDto.class)
        .hasSize(1)
        .value(locations -> assertEquals("Budapest", locations.get(0).getName()));
  }
}
