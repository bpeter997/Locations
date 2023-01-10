package com.learn.locations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class LocationsTestHelper {

  private static AutoCloseable closeable;

  protected static LocationDto generateLocationDto(long id, String name, double lat, double lon) {
    return LocationDto.builder().id(id).name(name).lat(lat).lon(lon).build();
  }

  protected static Location generateLocation(long id, String name, double lat, double lon) {
    return Location.builder().id(id).name(name).lat(lat).lon(lon).build();
  }

  @BeforeEach
  public void openMocks() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  public void releaseMocks() throws Exception {
    closeable.close();
  }
}
