package com.learn.locations;

public class LocationsTestHelper {

  protected static LocationDto generateLocationDto(long id, String name, double lat, double lon) {
    return LocationDto.builder().id(id).name(name).lat(lat).lon(lon).build();
  }
}
