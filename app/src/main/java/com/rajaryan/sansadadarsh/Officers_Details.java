package com.rajaryan.sansadadarsh;

public class Officers_Details {
    String Name,DP,Description,Work,Contact;

    public Officers_Details() {
    }

    public Officers_Details(String name, String DP, String description, String work, String contact) {
        Name = name;
        this.DP = DP;
        Description = description;
        Work = work;
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

    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        Work = work;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
