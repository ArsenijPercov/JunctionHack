package com.example.junctionhack;

public class Baggage {

    private String baggageId;
    private String customerId; // Baggage owners ID
    private boolean rushbag; // Baggage needs to hurry to a connecting flight
    private float weight;

    private boolean A;
    private boolean L;
    private boolean H;
    private boolean C;
    private boolean T;
    private boolean W;

    /*
    If everything is set to 0: N	Normal	Standard luggage
    A	Animal	Live animal, requires heated cargo
    L	Long baggage	Baggage that is longer than usual, e.g. ski bag
    H	Heavy baggage	Baggage that is heavier than 23 kg
    C	Special condition	Baggage that requires specific temperature, e.g. medication
    T	Toxic chemicals	Larger amount of possibly hazardous chemicals
    W	Weapons or ammunition	Weapons or hunting gear, e.g. ammunition
 */

    public String getBaggageId(){
        return baggageId;
    }

    public String getCustomerId(){
        return customerId;
    }

    public boolean getRushbag(){
        return rushbag;
    }

    public float getWeight(){
        return weight;
    }

    public boolean stateNormal(){
        return (!A && !L && !H && !C && !T && !W);
    }

    public boolean stateA(){
        return A;
    }

    public boolean stateL(){
        return L;
    }

    public boolean stateH(){
        return H;
    }

    public boolean stateC(){
        return C;
    }

    public boolean stateT(){
        return T;
    }

    public boolean stateW(){
        return W;
    }


}
