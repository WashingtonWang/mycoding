package com.xiangyu.lambdaeg.eg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaEg1 {
    public static void main(String[] args){
        List<Artist> allArtists = new ArrayList<>();
        List members = new ArrayList();
        members.add("wang");
        members.add("xiang");
        members.add("yu");
        Artist artist1 = new Artist("1",members,"London");
        Artist artist2 = new Artist("2",members,"London");
        Artist artist3 = new Artist("3",members,"Old JinShan");
        Artist artist4 = new Artist("4",members,"Washington DC");
        Artist artist5 = new Artist("5",members,"LuoShanJi");

        allArtists.add(artist1);
        allArtists.add(artist2);
        allArtists.add(artist3);
        allArtists.add(artist4);
        allArtists.add(artist5);

        long count = allArtists.stream().filter(artist -> artist.isFrom("London")).count();
        System.out.println(count);

        allArtists.stream()
                .filter(artist -> {
                    System.out.println(artist.getName());
                    return artist.isFrom("London");
                });

        allArtists.stream()
                .filter(artist -> {
                    System.out.println(artist.getName());
                    return artist.isFrom("London");
                }).count();

        List<Integer> together = Stream.of(Arrays.asList(1,2), Arrays.asList(3,4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        together.forEach(q -> System.out.println(q));

        List<Track> tracks = Arrays.asList(
                new Track("Bakai", 524),
                new Track("Violets",378),
                new Track("Time Was", 451));
        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getName()))
                .get();
        System.out.println(shortestTrack.getName());



    }

}
