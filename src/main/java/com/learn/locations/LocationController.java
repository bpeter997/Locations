package com.learn.locations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
@Tag(name = "Web operations on locations")
@PreAuthorize("isAuthenticated()")
public class LocationController {
  LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get Locations with optional query parameters")
  @ApiResponse(responseCode = "200")
  @PreAuthorize("hasAuthority('locations_app_user')")
  public List<LocationDto> getLocations(
      @Schema(description = "name of the location", example = "Budapest") @RequestParam
          Optional<String> name,
      @Schema(description = "coordinate lat, minimum", example = "5") @RequestParam
          Optional<Double> minLat,
      @Schema(description = "coordinate lon, minimum", example = "5") @RequestParam
          Optional<Double> minLon,
      @Schema(description = "coordinate lat, maximum", example = "305") @RequestParam
          Optional<Double> maxLat,
      @Schema(description = "coordinate lon, maximum", example = "305") @RequestParam
          Optional<Double> maxLon) {
    return locationService.getLocations(name, minLat, minLon, maxLat, maxLon);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get location by Id")
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404", description = "Location has not been found")
  public LocationDto getLocationById(@PathVariable("id") long id) {
    return locationService.getLocationById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a location")
  @ApiResponse(responseCode = "201", description = "Location was created.")
  public LocationDto createLocation(@Valid @RequestBody CreateLocationCommand locationCommand) {
    return locationService.createLocation(locationCommand);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Update a location")
  @ApiResponse(responseCode = "200", description = "Location was updated.")
  @ApiResponse(responseCode = "404", description = "Location has not been found")
  public LocationDto updateLocation(
      @PathVariable("id") long id, @Valid @RequestBody UpdateLocationCommand locationCommand) {
    return locationService.updateLocation(id, locationCommand);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Delete a location")
  @ApiResponse(responseCode = "204", description = "Location was deleted.")
  @ApiResponse(responseCode = "404", description = "Location has not been found")
  public void deleteLocation(@PathVariable("id") long id) {
    locationService.deleteLocation(id);
  }
}
