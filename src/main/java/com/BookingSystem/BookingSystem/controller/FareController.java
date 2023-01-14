package com.BookingSystem.BookingSystem.controller;

import com.BookingSystem.BookingSystem.model.Fare;
import com.BookingSystem.BookingSystem.repository.FareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FareController {
    @Autowired
    private FareRepository fareRepository;

    @GetMapping("/fares")
    public Iterable<Fare> getAllFares(){
        return fareRepository.findAll();
    }

    @PostMapping("/fares")
    public Fare createFares(@RequestBody Fare fare) {
        return fareRepository.save(fare);
    }
}
