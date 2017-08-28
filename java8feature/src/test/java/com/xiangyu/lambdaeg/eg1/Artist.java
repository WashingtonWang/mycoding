package com.xiangyu.lambdaeg.eg1;

import java.util.List;

public class Artist {
    private String name;
    private List members;
    private String origin;

    public Artist(String name, List members, String origin) {
        this.name = name;
        this.members = members;
        this.origin = origin;
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
}
