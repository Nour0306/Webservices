package com.BookingSystem.BookingSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "outbound_train_id")
    private Train outboundTrainId;

    @ManyToOne
    @JoinColumn(name = "return_train_id")
    private Train returnTrainId;

    private String travelClass;
    private String ticketType;
    private Integer numberOfTickets;

    public Reservation(){}

    public Reservation(Integer reservationId, User userId, Train outboundTrainId, Train returnTrainId, String travelClass, String ticketType, Integer numberOfTickets) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.outboundTrainId = outboundTrainId;
        this.returnTrainId = returnTrainId;
        this.travelClass = travelClass;
        this.ticketType = ticketType;
        this.numberOfTickets = numberOfTickets;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Train getOutboundTrainId() {
        return outboundTrainId;
    }

    public void setOutboundTrainId(Train outboundTrainId) {
        this.outboundTrainId = outboundTrainId;
    }

    public Train getReturnTrainId() {
        return returnTrainId;
    }

    public void setReturnTrainId(Train returnTrainId) {
        this.returnTrainId = returnTrainId;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
