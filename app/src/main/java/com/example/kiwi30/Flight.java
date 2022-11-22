package com.example.kiwi30;

import android.util.Log;

import java.util.ArrayList;

public class Flight {
    private String fId;
    private String fDepatureTown;
    private String fDestinationTown;
    private String fTimeDuration;
    private String fAirline;
    private String fAirlineLogo;
    private String fDepatureDate;
    private String fDepatureTime;
    private String fArrivalDate;
    private String fArrivalTime;
    private String fPrice;


    public Flight(String fId, String fDepatureTown, String fDestinationTown, String fTimeDuration, String fAirline, String fAirlineLogo, String fDepatureDate, String fDepatureTime, String fArrivalDate, String fArrivalTime, String fPrice) {
        this.fId = fId;
        Log.d("fId", fId);
        this.fDepatureTown= fDepatureTown;
        this.fDestinationTown = fDestinationTown;
        this.fTimeDuration = fTimeDuration;
        this.fAirline = fAirline;
        this.fAirlineLogo = fAirlineLogo;
        this.fDepatureDate = fDepatureDate;
        this.fDepatureTime = fDepatureTime;
        this.fArrivalDate = fArrivalDate;
        this.fArrivalTime= fArrivalTime;
        this.fPrice = fPrice;
    }

    public String getfId() {
        return fId;
    }

    public String getfDepatureTown() {
        return fDepatureTown;
    }

    public String getfDestinationTown() {
        return fDestinationTown;
    }

    public String getfTimeDuration() {
        return fTimeDuration;
    }

    public String getfAirline() {
        return fAirline;
    }

    public String getfAirlineLogo() {
        return fAirlineLogo;
    }

    public String getfDepatureTime() {
        return fDepatureTime;
    }

    public String getfArrivalTime() {
        return fArrivalTime;
    }

    public String getfPrice() {
        return fPrice;
    }


    public String getfDepatureDate() {
        return fDepatureDate;
    }

    public String getfArrivalDate() {
        return fArrivalDate;
    }



}
