package com.learn.locations;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@ControllerAdvice
public class LocationExceptionHandler {
  @ExceptionHandler
  ResponseEntity<Problem> handleException(
      LocationNotFoundException exception) {
    Problem problem =
        Problem.builder()
            .withType(URI.create("locations/location-not-found"))
            .withTitle("Not found")
            .withStatus(Status.NOT_FOUND)
            .withDetail(exception.getMessage())
            .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(problem);
  }
}
