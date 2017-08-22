package com.xiangyu.lambdaeg.eg1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEg1 {

    public static void main(String[] args){
        List<String> collected = Stream.of("a","b","c")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(collected);

        List<Integer> together = Stream.of(Arrays.asList(1,2), Arrays.asList(3,4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        System.out.println(together);

        List<Track> tracks = Arrays.asList(new Track("Bakai"),
                new Track("Violets for your Furs"),
                new Track("Time Was"));
        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(Track::getName))
                .get();
        System.out.println(shortestTrack.getName());
    }
}
