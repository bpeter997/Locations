package com.learn.locations;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@ControllerAdvice
public class LocationExceptionHandler {
  @ExceptionHandler
  ResponseEntity<Problem> handleException(LocationNotFoundException exception) {
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

  @ExceptionHandler
  ResponseEntity<Problem> handleValidationException(MethodArgumentNotValidException exception) {
    List<Violation> violations =
        exception.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new Violation(fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList());

    Problem problem =
        Problem.builder()
            .withType(URI.create("locations/validation-error"))
            .withTitle("Validation error")
            .withStatus(Status.BAD_REQUEST)
            .withDetail(exception.getMessage())
            .with("violations", violations)
            .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(problem);
  }
}
