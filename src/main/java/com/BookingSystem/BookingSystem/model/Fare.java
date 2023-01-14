package com.BookingSystem.BookingSystem.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "fares")
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fareId;
    private String ticketType;
    private String travelClass;
    private BigDecimal price;

    public Fare() {}

    public Fare(Integer fareId, String ticketType, String travelClass, BigDecimal price) {
        this.fareId = fareId;
        this.ticketType = ticketType;
        this.travelClass = travelClass;
        this.price = price;
    }

    public Integer getFareId() {
        return fareId;
    }

    public void setFareId(Integer fareId) {
        this.fareId = fareId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
