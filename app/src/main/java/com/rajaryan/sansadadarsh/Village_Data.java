package com.rajaryan.sansadadarsh;

public class Village_Data {
    String Mp,Village,Image,District,DP,Contact,Name,PDF;

    public Village_Data() {
    }

    public Village_Data(String mp, String village, String image, String district, String DP, String contact, String name, String PDF) {
        Mp = mp;
        Village = village;
        Image = image;
        District = district;
        this.DP = DP;
        Contact = contact;
        Name = name;
        this.PDF = PDF;
    }

    public String getMp() {
        return Mp;
    }

    public void setMp(String mp) {
        Mp = mp;
    }

    public String getVillage() {
        return Village;
    }

    public void setVillage(String village) {
        Village = village;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getDP() {
        return DP;
    }

    public void setDP(String DP) {
        this.DP = DP;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }
}
