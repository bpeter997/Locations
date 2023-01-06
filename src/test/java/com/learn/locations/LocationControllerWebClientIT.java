package com.learn.locations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LocationControllerWebClientIT {

  @Autowired WebTestClient webTestClient;

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

  @Test
  void testFindEmployeeById() {
    webTestClient
        .get()
        .uri("/api/locations/{id}", 1)
        .exchange()
        .expectBody(LocationDto.class)
        .value(dto -> assertEquals("Budapest", dto.getName()));
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
        .hasSize(2);
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
