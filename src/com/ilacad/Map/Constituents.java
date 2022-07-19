package com.ilacad.Map;

public class Constituents {

    private String name;
    private String address;
    private String contactNumber;
    private int vaccineCount;

    public Constituents(String name, String address, String contactNumber, int vaccineCount) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.vaccineCount = vaccineCount;
    }

    public void updateName (String name) {
        this.name = name;
    }

    public void updateAddress (String address) {
        this.address = address;
    }

    public void updateContact (String contact) {
        this.contactNumber = contact;
    }

    public void updateVaccineCount(int vaccineCount) {
        this.vaccineCount = vaccineCount;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getVaccineCount() {
        return vaccineCount;
    }

    public boolean status() {
        if (this.vaccineCount >= 2) {
            return true;
        }
        return false;
    }
}
