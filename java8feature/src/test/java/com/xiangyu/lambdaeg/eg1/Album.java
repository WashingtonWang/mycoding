package com.xiangyu.lambdaeg.eg1;

import java.util.List;

public class Album {
    private String name;
    private List<Track> tracks;
    private List<Artist> musicians;
    private Artist mainMusician;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Artist> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Artist> musicians) {
        this.musicians = musicians;
    }

    public Artist getMainMusician() {
        return mainMusician;
    }

    public void setMainMusician(Artist mainMusician) {
        this.mainMusician = mainMusician;
    }
}
