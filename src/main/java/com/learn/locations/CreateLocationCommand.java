package com.learn.locations;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {
  @Schema(description = "name of the location", example = "Budapest")
  @NotBlank(message = "Name can not be blank!")
  private String name;

  @Schema(description = "coordinate lat", example = "5.0")
  @Coordinate(type = CoordinateType.LAT)
  private double lat;

  @Schema(description = "coordinate lon", example = "5.0")
  @Coordinate(type = CoordinateType.LON)
  private double lon;
}
