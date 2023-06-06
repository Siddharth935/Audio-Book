package com.royalfriends.bookies;

public class FileinModel {

    String filename, fileurl,fileImge,fileDescription;
    public FileinModel() {
    }

    public FileinModel(String filename, String fileurl, String fileImge, String fileDescription) {
        this.filename = filename;
        this.fileurl = fileurl;
        this.fileImge = fileImge;
        this.fileDescription = fileDescription;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getFileImge() {
        return fileImge;
    }

    public void setFileImge(String fileImge) {
        this.fileImge = fileImge;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }
}
