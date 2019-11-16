package com.example.junctionhack;

public class Baggage {

    private String baggageId;
    private String customerId; // Baggage owners ID
    private boolean rushbag; // Baggage needs to hurry to a connecting flight
    private double weight;
    private enum special {
        N, A, L, H, C, T, W;
        /*
        N	Normal	Standard luggage
        A	Animal	Live animal, requires heated cargo
        L	Long baggage	Baggage that is longer than usual, e.g. ski bag
        H	Heavy baggage	Baggage that is heavier than 23 kg
        C	Special condition	Baggage that requires specific temperature, e.g. medication
        T	Toxic chemicals	Larger amount of possibly hazardous chemicals
        W	Weapons or ammunition	Weapons or hunting gear, e.g. ammunition
         */
    }

    public String getBaggageId(){
        return baggageId;
    }

    public String getCustomerId(){
        return customerId;
    }

    public boolean getRushbag(){
        return rushbag;
    }

    public double getWeight(){
        return weight;
    }

}
