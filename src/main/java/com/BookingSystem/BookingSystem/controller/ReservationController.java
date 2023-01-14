package com.BookingSystem.BookingSystem.controller;

import com.BookingSystem.BookingSystem.model.Reservation;
import com.BookingSystem.BookingSystem.model.Train;
import com.BookingSystem.BookingSystem.repository.ReservationRepository;
import com.BookingSystem.BookingSystem.repository.TrainRepository;
import com.BookingSystem.BookingSystem.repository.UserRepository;
import com.BookingSystem.BookingSystem.subClass.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired TrainRepository trainRepository;
    @Autowired UserRepository userRepository;

    @GetMapping("/reservations")
    public Iterable<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    @PostMapping("/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @PostMapping("reservations/book")
    public Boolean bookTicket(@RequestBody Ticket ticket) {
        Optional<Train> train = trainRepository.findById(ticket.getOutboundTrainId());
        if (!train.isPresent()) {
            //Train Id not found and user needs to provide right ID
            return false;
        }
        Train selectedTrain = train.get();

        if (ticket.getTravelClass().equals("First")) {
            if (ticket.getNumberOfTickets() > selectedTrain.getAvailableSeatsFirst()) {
                return false;
            }
        } else if (ticket.getTravelClass().equals("Business")) {
            if (ticket.getNumberOfTickets() > selectedTrain.getAvailableSeatsBusiness()) {
                return false;
            }
        } else if (ticket.getTravelClass().equals("Standard")) {
            if (ticket.getNumberOfTickets() > selectedTrain.getAvailableSeatStandard()) {
                return false;
            }
        }

        Reservation reservation = new Reservation();
        if(ticket.getReturnTrainId() != null) {
            Optional<Train> returnTrain = trainRepository.findById(ticket.getReturnTrainId());
            reservation.setReturnTrainId(returnTrain.get());
             selectedTrain = returnTrain.get();
            if (ticket.getTravelClass().equals("First")) {
                if (ticket.getNumberOfTickets() > selectedTrain.getAvailableSeatsFirst()) {
                    return false;
                }
            } else if (ticket.getTravelClass().equals("Business")) {
                if (ticket.getNumberOfTickets() > selectedTrain.getAvailableSeatsBusiness()) {
                    return false;
                }
            } else if (ticket.getTravelClass().equals("Standard")) {
                if (ticket.getNumberOfTickets() > selectedTrain.getAvailableSeatStandard()) {
                    return false;
                }
            }
        }

        Optional<Train> outboundTrainId = trainRepository.findById(ticket.getOutboundTrainId());
        reservation.setOutboundTrainId(outboundTrainId.get());
        reservation.setTravelClass(ticket.getTravelClass());
        reservation.setTicketType(ticket.getTicketType());
        reservation.setNumberOfTickets(ticket.getNumberOfTickets());
        //TODO change the user to the user logged in
        reservation.setUserId(userRepository.findById(1).get());
        reservation = reservationRepository.save(reservation);

        if (reservation == null) {
            return false;
        }
        return true;
    }



}
