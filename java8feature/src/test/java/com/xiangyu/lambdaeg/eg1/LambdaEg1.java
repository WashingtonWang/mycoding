package com.xiangyu.lambdaeg.eg1;

import java.util.ArrayList;
import java.util.List;

public class LambdaEg1 {
    public static void main(String[] args){
        List<Artist> allArtists = new ArrayList<>();
        List members = new ArrayList();
        members.add("wang");
        members.add("xiang");
        members.add("yu");
        Artist artist1 = new Artist("1",members,"London");
        Artist artist2 = new Artist("2",members,"London");
        Artist artist3 = new Artist("3",members,"London");

        allArtists.add(artist1);
        allArtists.add(artist2);
        allArtists.add(artist3);

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
    }
}
