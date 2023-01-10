package com.learn.locations;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {

  @Query(
      value =
          "select * from locations l where upper(l.location_name) like upper(:name) and l.latitude >= :minLat and l.latitude <= :maxLat and l.longitude >= :minLon and l.longitude <= :maxLon",
      nativeQuery = true)
  List<Location> findAllByParams(
      @Param("name") Optional<String> name,
      @Param("minLat") Optional<Double> minLat,
      @Param("minLon") Optional<Double> minLon,
      @Param("maxLat") Optional<Double> maxLat,
      @Param("maxLon") Optional<Double> maxLon);
}
