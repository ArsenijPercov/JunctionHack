package com.example.junctionhack;

public class Events {
    private String eventId; // Baggage events ID
    private String baggageId;
    private String airport; // Airport of the event, three-letter IATA code
    private String timestamp;

    private boolean CHECKED_IN;
    private boolean LOADED;
    private boolean UNLOADED;
    private boolean DAMAGED;
    private boolean CLAIMED;
    private boolean MISSING;


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

    public boolean stateCHECKED_IN(){
        return CHECKED_IN;
    }

    public boolean stateLOADED(){
        return LOADED;
    }

    public boolean stateUNLOADED(){
        return UNLOADED;
    }

    public boolean stateDAMAGED(){
        return DAMAGED;
    }

    public boolean stateCLAIMED(){
        return CLAIMED;
    }

    public boolean stateMISSING(){
        return MISSING;
    }

}
