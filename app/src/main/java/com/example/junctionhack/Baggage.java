package com.example.junctionhack;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Baggage {

    private String baggageId;
    private String customerId; // Baggage owners ID
    private boolean rushbag; // Baggage needs to hurry to a connecting flight
    private float weight;
    private String name;

    private boolean A;
    private boolean L;
    private boolean H;
    private boolean C;
    private boolean T;
    private boolean W;

    ArrayList<Events> listEvents = new ArrayList<Events>();
    Baggage(String baggageIdnew, String customerIdnew, boolean rushbagnew, float weightnew, String special, ArrayList<Events> listEventsNew){
        baggageId = baggageIdnew;
        customerId = customerIdnew;
        rushbag = rushbagnew;
        weight = weightnew;
        //name = "DEFAULT";
        listEvents = listEventsNew;
        for (int i = 0; i<special.length();i++){
            switch (special.charAt(i)){
                case 'A':
                    A = true;
                    break;
                case 'L':
                    L = true;
                    break;
                case 'H':
                    H = true;
                    break;
                case 'C':
                    C = true;
                    break;
                case 'T' :
                    T = true;
                    break;
                case 'W':
                    W = true;
                    break;
                case 'N':
                    A= L = H = C = T = W = false;
                    break;
                default:
                    break;

            }
        }

    }

    /*
    If everything is set to 0: N	Normal	Standard luggage
    A	Animal	Live animal, requires heated cargo
    L	Long baggage	Baggage that is longer than usual, e.g. ski bag
    H	Heavy baggage	Baggage that is heavier than 23 kg
    C	Special condition	Baggage that requires specific temperature, e.g. medication
    T	Toxic chemicals	Larger amount of possibly hazardous chemicals
    W	Weapons or ammunition	Weapons or hunting gear, e.g. ammunition
 */

    //public Str
    public void setName(String newName){
        Log.d("SANSA", newName);
        name = newName;
        Log.d("SANSA2", name);
    }

    public String getName(){
        return name;
    }
    public void addEvent(Events e){
        listEvents.add(e);
    }
    public String getBaggageId(){

        return baggageId;
    }
    public ArrayList<Events> getListEvents(){

        return listEvents;
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
