package com.mycoding.javabase.threadeg.eg4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于监视器模式的 车辆追踪 类 是线程安全的  P.52 《java并发编程实战》
 */
public class MonitorVehicleTracker {
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations){
        this.locations = deepCopy(locations);
    }
    public synchronized Map<String, MutablePoint> getLocations(){
        return deepCopy(locations);
    }
    public synchronized MutablePoint getLocation(String id){
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }
    public synchronized void setLocation(String id, int x, int y){
        MutablePoint loc = locations.get(id);
        if (loc == null)
            throw new IllegalArgumentException("No such ID: " + id);
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m){
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : m.keySet()){
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}

/**
 * 这是类对象 不是线程安全的。。。
 */
class MutablePoint {
    public int x ,y;

    public MutablePoint(){
        x = 0;
        y = 0;
    }
    public MutablePoint(MutablePoint p){
        this.x = p.x;
        this.y = p.y;
    }
}
