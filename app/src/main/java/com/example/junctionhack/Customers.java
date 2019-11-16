package com.example.junctionhack;

public class Customers {
    private String customerId; // Baggage owners ID, unique identifer
    private String name;
    private String email;
    private String phone;
    private String target; // Destination airport, three-letter IATA code

    public String getCustomerId(){
        return customerId;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public String getTarget(){
        return target;
    }



}
