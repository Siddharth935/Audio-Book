package com.royalfriends.bookies;

public class Song {
    private String songName, songUrl,songImg;

    public Song() {
    }

    public Song(String songName, String songUrl,String songImg) {
        this.songName = songName;
        this.songUrl = songUrl;
        this.songImg = songImg;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSongImg() {
        return songImg;
    }

    public void setSongImg(String songImg) {
        this.songImg = songImg;
    }
}
