package com.example.mohamedrashed.gym.ModelClasses;

public class GymModel {

    private int sauna, jacuzzi, spa;
    private String gymName;
    private String gymAddress;
    private String gymPrice;
    private String gymTime;
    private String gymDescription;
    private String gymGender;
    private String gymImageLink;
    private String key;
    private String latLng;

    public GymModel(int sauna, int jacuzzi, int spa, String gymName, String gymAddress, String gymPrice, String gymTime, String gymDescription, String gymGender, String gymImageLink, String key, String latLng) {
        this.sauna = sauna;
        this.jacuzzi = jacuzzi;
        this.spa = spa;
        this.gymName = gymName;
        this.gymAddress = gymAddress;
        this.gymPrice = gymPrice;
        this.gymTime = gymTime;
        this.gymDescription = gymDescription;
        this.gymGender = gymGender;
        this.gymImageLink = gymImageLink;
        this.key = key;
        this.latLng = latLng;
    }

    public GymModel() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLatLng() {
        return latLng;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }

    public int getSauna() {
        return sauna;
    }

    public void setSauna(int sauna) {
        this.sauna = sauna;
    }

    public int getJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(int jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public int getSpa() {
        return spa;
    }

    public void setSpa(int spa) {
        this.spa = spa;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getGymAddress() {
        return gymAddress;
    }

    public void setGymAddress(String gymAddress) {
        this.gymAddress = gymAddress;
    }

    public String getGymPrice() {
        return gymPrice;
    }

    public void setGymPrice(String gymPrice) {
        this.gymPrice = gymPrice;
    }

    public String getGymTime() {
        return gymTime;
    }

    public void setGymTime(String gymTime) {
        this.gymTime = gymTime;
    }

    public String getGymDescription() {
        return gymDescription;
    }

    public void setGymDescription(String gymDescription) {
        this.gymDescription = gymDescription;
    }

    public String getGymGender() {
        return gymGender;
    }

    public void setGymGender(String gymGender) {
        this.gymGender = gymGender;
    }

    public String getGymImageLink() {
        return gymImageLink;
    }

    public void setGymImageLink(String gymImageLink) {
        this.gymImageLink = gymImageLink;
    }

}
