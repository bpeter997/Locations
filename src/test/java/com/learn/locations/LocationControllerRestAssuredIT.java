package com.learn.locations;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerRestAssuredIT {

  @Autowired MockMvc mockMvc;

  @Autowired LocationService locationService;

  @BeforeEach
  void init() {
    RestAssuredMockMvc.mockMvc(mockMvc);
    RestAssuredMockMvc.requestSpecification =
        given().contentType(ContentType.JSON).accept(ContentType.JSON);
    locationService.deleteAllLocation();
  }

  @Test
  void testListLocations() {
    with()
        .body(CreateLocationCommand.builder().name("Szeged").lat(5.0).lon(60.0).build())
        .post("/api/locations")
        .then()
        .statusCode(201)
        .body("name", equalTo("Szeged"))
        .log();

    with()
        .get("/api/locations")
        .then()
        .statusCode(200)
        .body("[0].name", equalTo("Szeged"))
        .body("size()", equalTo(1));
  }

  @Test
  void validateJsonScheme() {
    with()
        .body(CreateLocationCommand.builder().name("Szed").lat(15.0).lon(66.0).build())
        .post("/api/locations")
        .then()
        .body(matchesJsonSchemaInClasspath("location-dto-schema.json"));
  }
}
