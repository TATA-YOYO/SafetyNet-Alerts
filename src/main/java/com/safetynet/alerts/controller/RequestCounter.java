package com.safetynet.alerts.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RequestCounter {
    private static final Logger logger = LogManager.getLogger("RequestCounter");
    private int NumberOfRequests = 0;

    public int getNumberOfRequests() {
        return NumberOfRequests;
    }

    public int addRequest() {
        NumberOfRequests++;
        logger.debug("the number of request are increased");
        return NumberOfRequests;
    }
}
