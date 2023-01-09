package com.learn.locations;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "locations")
public class Location {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @Schema(description = "name of the location", example = "Budapest")
  @Column(name = "location_name")
  private String name;

  @Schema(description = "coordinate lat", example = "5.0")
  @Column(name = "latitude")
  private double lat;

  @Schema(description = "coordinate lon", example = "5.0")
  @Column(name = "longitude")
  private double lon;
}
