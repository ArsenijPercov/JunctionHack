package com.example.junctionhack;


public class Events {
    private String eventId; // Baggage events ID
    private String baggageId;
    private String airport; // Airport of the event, three-letter IATA code
    private String timestamp;
    private String type;


    Events(String baggageIdNew, String eventIdNew, String airportNew, String timestampNew, String typeNew){

        baggageId = baggageIdNew;

        eventId = eventIdNew;
        airport = airportNew;
        timestamp = timestampNew;
        //baggageId = baggageNew;
        type = typeNew;
        }

    public String getEventId() {return eventId;}

    public String getBaggageId() { return baggageId;}

    public String getAirport(){
        return airport;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public String getType(){
        return type;
    }



}
