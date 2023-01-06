package com.learn.locations;

import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
  LocationDto locationToLocationDto(Location location);

  List<LocationDto> locationToLocationDto(List<Location> location);

  Location createLocationCommandToLocation(CreateLocationCommand locationCommand);
}
