package com.xiangyu.function.lambda.eg3;

import java.util.List;

public class Artist {
    private String name;
    private List members;
    private String origin;
    private boolean solo;

    public Artist(String name, List members, String origin, boolean solo) {
        this.name = name;
        this.members = members;
        this.origin = origin;
        this.solo = solo;
    }

    public boolean isFrom(String from){
        if (from == null || from.equals(""))
            return false;
        return from.equals(origin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getMembers() {
        return members;
    }

    public void setMembers(List members) {
        this.members = members;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isSolo() {
        return solo;
    }

    public void setSolo(boolean solo) {
        this.solo = solo;
    }
}
