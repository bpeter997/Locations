package com.learn.locations;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocationCommand {
  @Schema(description = "name of the location", example = "Budapest")
  @NotBlank(message = "Name can not be blank!")
  private String name;

  @Schema(description = "coordinate lat", example = "5.0")
  @Min(value = -90L,message = "Latitude must be greater or equal than -90!")
  @Max(value = 90L,message = "Latitude must be lesser or equal than 90!")
  private double lat;

  @Schema(description = "coordinate lon", example = "5.0")
  @Min(value = -180L,message = "Latitude must be greater or equal than -180!")
  @Max(value = 180L,message = "Latitude must be lesser or equal than 180!")
  private double lon;
}
