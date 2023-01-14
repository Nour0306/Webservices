package com.BookingSystem.BookingSystem.controller;

import com.BookingSystem.BookingSystem.model.Station;
import com.BookingSystem.BookingSystem.model.Train;
import com.BookingSystem.BookingSystem.repository.StationRepository;
import com.BookingSystem.BookingSystem.repository.TrainRepository;
import com.BookingSystem.BookingSystem.subClass.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController


public class TrainController {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private StationRepository stationRepository;


    @GetMapping("/trains")
    public Iterable<Train> getAllTrains(){
        return trainRepository.findAll();
    }

    // _______________   Scenario ___________________________________

    // Get One way trains
    @GetMapping(value = {"/trains/from/{from}/to/{to}/outboundDate/{outboundDate}/tickets/{tickets}/travelClass/{travelClass}"})
    public List<Train> getTrainsOneWay (
            @PathVariable("from") String departureStation,
            @PathVariable("to") String arrivalStation,
            @PathVariable("outboundDate") String outboundDate,
            @PathVariable("tickets") Integer tickets,
            @PathVariable("travelClass") String travelClass)
    {
        Station departureStat = stationRepository.findByCity(departureStation);
        Station arrivalStat = stationRepository.findByCity(arrivalStation);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(outboundDate, formatter);

        List<Train> trains = trainRepository.findByCriteria(departureStat, arrivalStat, dateTime);
        List<Train> filteredTrains = new ArrayList<>();

        for (Train train : trains) {
            if (travelClass.equals("First")) {
                if (tickets <= train.getAvailableSeatsFirst()) {
                    filteredTrains.add(train);
                }
            } else if (travelClass.equals("Business")) {
                if (tickets <= train.getAvailableSeatsBusiness()) {
                    filteredTrains.add(train);
                }
            } else if (travelClass.equals("Standard")) {
                if (tickets <= train.getAvailableSeatStandard()) {
                    filteredTrains.add(train);
                }
            }
            else {throw new EntityNotFoundException("No trains are available"); }
        }
        return filteredTrains;
    }

    //Get round trip trains
    @GetMapping(value = {"/trains/from/{from}/to/{to}/outboundDate/{outboundDate}/returnDate/{returnDate}/tickets/{tickets}/travelClass/{travelClass}"})
    public List<Train> getTrainsTwoWay (
            @PathVariable("from") String departureStation,
            @PathVariable("to") String arrivalStation,
            @PathVariable("outboundDate") String outboundDate,
            @PathVariable("returnDate") String returnDate,
            @PathVariable("tickets") Integer tickets,
            @PathVariable("travelClass") String travelClass)
    {
        // One-way method for outbound trip
        List<Train> outboundTrains = getTrainsOneWay(departureStation, arrivalStation, outboundDate, tickets, travelClass);
        List<Train> inboundTrains = getTrainsOneWay(arrivalStation, departureStation, returnDate, tickets, travelClass);

        outboundTrains.addAll(inboundTrains);
        return outboundTrains;
    }

    @PutMapping("trains/update")
    public void updateTrains(@RequestBody Ticket ticket) {
        Optional<Train> train = trainRepository.findById(ticket.getOutboundTrainId());
        if (!train.isPresent()) {
            //Train Id not found and user needs to provide right ID
            throw new InvalidParameterException("Wrong ID");
        }

        Train selectedTrain = train.get();

        if (ticket.getTravelClass().equals("First")) {
            selectedTrain.setAvailableSeatsFirst(selectedTrain.getAvailableSeatsFirst() - ticket.getNumberOfTickets());
        } else if (ticket.getTravelClass().equals("Business")) {
            selectedTrain.setAvailableSeatsBusiness(selectedTrain.getAvailableSeatsBusiness() - ticket.getNumberOfTickets());
        } else if (ticket.getTravelClass().equals("Standard")) {
            selectedTrain.setAvailableSeatStandard(selectedTrain.getAvailableSeatStandard() - ticket.getNumberOfTickets());
        }
        trainRepository.save(selectedTrain);

        if(ticket.getReturnTrainId() != null) {
            Optional<Train> returnTrain = trainRepository.findById(ticket.getReturnTrainId());
            selectedTrain = returnTrain.get();
            if (ticket.getTravelClass().equals("First")) {
                selectedTrain.setAvailableSeatsFirst(selectedTrain.getAvailableSeatsFirst() - ticket.getNumberOfTickets());
            } else if (ticket.getTravelClass().equals("Business")) {
                selectedTrain.setAvailableSeatsBusiness(selectedTrain.getAvailableSeatsBusiness() - ticket.getNumberOfTickets());
            } else if (ticket.getTravelClass().equals("Standard")) {
                selectedTrain.setAvailableSeatStandard(selectedTrain.getAvailableSeatStandard() - ticket.getNumberOfTickets());
            }
            trainRepository.save(selectedTrain);
        }
    }
    // ______________________ Additional methods ____________________

    @GetMapping(value = {"/trains/from/{from}/to/{to}"})
    public List<Train> getTrainsBasedOnStations(
            @PathVariable("from") String departureStation,
            @PathVariable("to") String arrivalStation)
    {
        Station departureStat = stationRepository.findByCity(departureStation);
        Station arrivalStat = stationRepository.findByCity(arrivalStation);

        List<Train> trains = trainRepository.findByStations(departureStat, arrivalStat);

        if(trains.isEmpty())
            throw new EntityNotFoundException("No trains are available for the given stations!");
        return trains;
    }

    public Train createTrain(@RequestBody Train train) {
        return trainRepository.save(train);
    }
}
