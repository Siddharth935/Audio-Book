package com.royalfriends.bookies;

public class ProfileModel {
    String Name,MobileNumber,emailId,Image;

    public ProfileModel() {
    }

    public ProfileModel(String name, String mobileNumber, String emailId, String image) {
        Name = name;
        MobileNumber = mobileNumber;
        this.emailId = emailId;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
