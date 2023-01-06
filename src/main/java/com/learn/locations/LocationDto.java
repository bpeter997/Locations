package com.learn.locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
  private Long id;

  @Schema(description = "name of the location", example = "Budapest")
  private String name;

  @Schema(description = "coordinate lat", example = "5.0")
  private double lat;

  @Schema(description = "coordinate lon", example = "5.0")
  private double lon;
}
