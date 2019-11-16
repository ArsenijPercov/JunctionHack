package com.example.junctionhack;

public class Events {
    private String eventId; // Baggage events ID
    private String baggageId;
    private String airport; // Airport of the event, three-letter IATA code
    private String timestamp;
    private enum type {
        CHECKED_IN,
        LOADED,
        UNLOADED,
        DAMAGED,
        CLAIMED,
        MISSING;
    }

    public String getEventId(){
        return eventId;
    }

    public String getBaggageId(){
        return baggageId;
    }

    public String getAirport(){
        return airport;
    }

    public String getTimestamp(){
        return timestamp;
    }

}
