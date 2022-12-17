package com.learn.locations;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDto> getLocations(@RequestParam(required = false, defaultValue = "") String name) {
        return locationService.getLocations(name);
    }

    @GetMapping("/{id}")
    public List<LocationDto> getLocationById(@PathVariable("id") long id) {
        return locationService.getLocationById(id);
    }
}
