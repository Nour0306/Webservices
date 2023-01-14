package com.BookingSystem.BookingSystem.subClass;

import org.springframework.web.bind.annotation.RequestParam;

public class Ticket {

    //One-way ticket or Round Trip
    //private String type;
    //Business, First or Standard
    private String travelClass;
    //Flexible or not
    private String ticketType;
    private Integer numberOfTickets;
    private Integer outboundTrainId;
    private Integer returnTrainId;

    public Ticket(){}

    public Ticket( String travelClass, String ticketType, Integer numberOfTickets, Integer outboundTrainId, Integer returnTrainId) {
        this.travelClass = travelClass;
        this.ticketType = ticketType;
        this.numberOfTickets = numberOfTickets;
        this.outboundTrainId = outboundTrainId;
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

    public Integer getOutboundTrainId() {
        return outboundTrainId;
    }

    public void setOutboundTrainId(Integer outboundTrainId) {
        this.outboundTrainId = outboundTrainId;
    }

    public Integer getReturnTrainId() {
        return returnTrainId;
    }

    public void setReturnTrainId(Integer returnTrainId) {
        this.returnTrainId = returnTrainId;
    }
}

