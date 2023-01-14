package com.BookingSystem.BookingSystem.controller;

import com.BookingSystem.BookingSystem.model.Station;
import com.BookingSystem.BookingSystem.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/stations")
    public Iterable<Station> getAllStations(){
        return stationRepository.findAll();
    }

    @PostMapping("/stations")
    public Station createStation(@RequestBody Station station) {
        return stationRepository.save(station);
    }
}
