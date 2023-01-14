package com.BookingSystem.BookingSystem.repository;

import com.BookingSystem.BookingSystem.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
