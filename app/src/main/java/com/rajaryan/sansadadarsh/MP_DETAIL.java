package com.rajaryan.sansadadarsh;

public class MP_DETAIL {
    String Name,DP,Description,Village,Contact;

    public MP_DETAIL() {
    }

    public MP_DETAIL(String name, String DP, String description, String village, String contact) {
        Name = name;
        this.DP = DP;
        Description = description;
        Village = village;
        Contact = contact;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDP() {
        return DP;
    }

    public void setDP(String DP) {
        this.DP = DP;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getVillage() {
        return Village;
    }

    public void setVillage(String village) {
        Village = village;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
