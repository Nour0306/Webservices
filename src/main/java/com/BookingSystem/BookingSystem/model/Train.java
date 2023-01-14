package com.BookingSystem.BookingSystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "train_id")
    private Integer trainId;

    @Column(name = " train_user_id")
    private String trainUserId;

    @ManyToOne
    @JoinColumn(name = "departure_station_id")
    private Station departureStation;

    @ManyToOne
    @JoinColumn(name = "arrival_station_id")
    private Station arrivalStation;

    @Column(name = "date_time")
    private LocalDateTime  dateTimeTrain;

    @Column(name = "available_seats_first")
    private Integer availableSeatsFirst;

    @Column(name = "available_seats_business")
    private Integer availableSeatsBusiness;

    @Column(name = "available_seats_standard")
    private Integer availableSeatStandard;

    @Column(name = "flexible_price")
    private Integer flexiblePrice;

    @Column(name = "Non_flexible_price")
    private Integer nonFlexiblePrice;


    public Train() {}

    public Train(Integer trainId, Station departureStation, Station arrivalStation, LocalDateTime dateTimeTrain, Integer availableSeatsFirst, Integer availableSeatsBusiness, Integer availableSeatStandard, Integer flexiblePrice, Integer nonFlexiblePrice) {
        this.trainId = trainId;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.dateTimeTrain = dateTimeTrain;
        this.availableSeatsFirst = availableSeatsFirst;
        this.availableSeatsBusiness = availableSeatsBusiness;
        this.availableSeatStandard = availableSeatStandard;
        this.flexiblePrice = flexiblePrice;
        this.nonFlexiblePrice = nonFlexiblePrice;
    }

    public Train(Integer trainId, String trainUserId, Station departureStation, Station arrivalStation, LocalDateTime dateTimeTrain, Integer availableSeatsFirst, Integer availableSeatsBusiness, Integer availableSeatStandard, Integer flexiblePrice, Integer nonFlexiblePrice) {
        this.trainId = trainId;
        this.trainUserId = trainUserId;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.dateTimeTrain = dateTimeTrain;
        this.availableSeatsFirst = availableSeatsFirst;
        this.availableSeatsBusiness = availableSeatsBusiness;
        this.availableSeatStandard = availableSeatStandard;
        this.flexiblePrice = flexiblePrice;
        this.nonFlexiblePrice = nonFlexiblePrice;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainUserId() {
        return trainUserId;
    }

    public void setTrainUserId(String trainUserId) {
        this.trainUserId = trainUserId;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public LocalDateTime getDateTimeTrain() {
        return dateTimeTrain;
    }

    public void setDateTimeTrain(LocalDateTime dateTimeTrain) {
        this.dateTimeTrain = dateTimeTrain;
    }

    public Integer getAvailableSeatsFirst() {
        return availableSeatsFirst;
    }

    public void setAvailableSeatsFirst(Integer availableSeatsFirst) {
        this.availableSeatsFirst = availableSeatsFirst;
    }

    public Integer getAvailableSeatsBusiness() {
        return availableSeatsBusiness;
    }

    public void setAvailableSeatsBusiness(Integer availableSeatsBusiness) {
        this.availableSeatsBusiness = availableSeatsBusiness;
    }

    public Integer getAvailableSeatStandard() {
        return availableSeatStandard;
    }

    public void setAvailableSeatStandard(Integer availableSeatStandard) {
        this.availableSeatStandard = availableSeatStandard;
    }

    public Integer getFlexiblePrice() {
        return flexiblePrice;
    }

    public void setFlexiblePrice(Integer flexiblePrice) {
        this.flexiblePrice = flexiblePrice;
    }

    public Integer getNonFlexiblePrice() {
        return nonFlexiblePrice;
    }

    public void setNonFlexiblePrice(Integer nonFlexiblePrice) {
        this.nonFlexiblePrice = nonFlexiblePrice;
    }

}
