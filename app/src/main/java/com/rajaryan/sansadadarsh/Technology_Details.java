package com.rajaryan.sansadadarsh;

public class Technology_Details {
    String Name,Innovator,Field,Image,PDF,Contact;

    public Technology_Details() {
    }

    public Technology_Details(String name, String innovator, String field, String image, String PDF, String contact) {
        Name = name;
        Innovator = innovator;
        Field = field;
        Image = image;
        this.PDF = PDF;
        Contact = contact;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getInnovator() {
        return Innovator;
    }

    public void setInnovator(String innovator) {
        Innovator = innovator;
    }

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
