package com.safetynet.alerts.controller;

import org.springframework.stereotype.Component;

@Component
public class RequestCounter {
    private int NumberOfRequests = 0;

    public int getNumberOfRequests() {
        return NumberOfRequests;
    }

    public int addRequest(){
        NumberOfRequests++;
        return NumberOfRequests;
    }
}
