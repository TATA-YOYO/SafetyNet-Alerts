package com.safetynet.alerts.models;

public class FireStation {
    private String address;
    private int station;

    @Override
    public String toString() {
        return "FireStation{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }
}
