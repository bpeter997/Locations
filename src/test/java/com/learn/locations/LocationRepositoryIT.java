package com.learn.locations;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class LocationRepositoryIT {

  @Autowired LocationRepository locationRepository;

  @Test
  void testPersist() {
    locationRepository.deleteAll();
    String locationName = "Szeged";
    Location location = Location.builder().name(locationName).id(1L).lat(34.6).lon(45.543).build();
    locationRepository.save(location);
    List<Location> locations = locationRepository.findAll();
    assertThat(locations).extracting(Location::getName).containsExactly(locationName);
  }
}
