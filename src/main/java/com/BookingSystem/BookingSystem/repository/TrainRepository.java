package com.BookingSystem.BookingSystem.repository;

import com.BookingSystem.BookingSystem.model.Station;
import com.BookingSystem.BookingSystem.model.Train;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TrainRepository extends CrudRepository<Train, Integer> {

    @Query("SELECT t FROM Train t " +
            "WHERE t.departureStation = :departureStation " +
            "AND t.arrivalStation = :arrivalStation " +
            "AND t.dateTimeTrain = :dateTimeTrain " )

    List<Train> findByCriteria(
            @Param("departureStation") Station departureStation,
            @Param("arrivalStation") Station arrivalStation,
            @Param("dateTimeTrain") LocalDateTime dateTimeTrain);

    @Query("SELECT t FROM Train t " +
            "WHERE t.departureStation = :departureStation " +
            "AND t.arrivalStation = :arrivalStation ")
    List<Train> findByStations(
            @Param("departureStation") Station departureStation,
            @Param("arrivalStation") Station arrivalStation);

}

