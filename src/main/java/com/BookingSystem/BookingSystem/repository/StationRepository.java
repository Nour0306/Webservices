package com.BookingSystem.BookingSystem.repository;

import com.BookingSystem.BookingSystem.model.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends CrudRepository<Station, Integer> {
    Station findByCity(String City);
}
