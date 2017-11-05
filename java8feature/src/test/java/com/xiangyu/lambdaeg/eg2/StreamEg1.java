package com.xiangyu.lambdaeg.eg2;

import com.xiangyu.lambdaeg.eg1.Album;
import com.xiangyu.lambdaeg.eg1.Artist;
import com.xiangyu.lambdaeg.eg1.Track;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * user: xiangyu.wang
 * date: 2017/10/30 19:40
 */
public class StreamEg1 {
    /***********************stream 转换成值 start***************************/
    /**
     * 找出乐队成员最多的 乐队
     *
     * @param artists
     * @return
     */
    public Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().stream().count();
        return artists.max(Comparator.comparing(getCount));
    }

    /**
     * stream 转换成值
     * 找出一组专辑上曲目的平均数
     *
     * @param albums
     * @return
     */
    public double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                .collect(averagingInt(album -> album.getTracks().size()));
    }
    /***********************stream 转换成值 end***************************/

    /***********************stream 数据分块 start***************************/
    /**
     * 将艺术家组成的流分成乐队和独唱歌手的两部分
     *
     * @param artists
     * @return
     */
    public Map<Boolean, List<Artist>> bandAndSolo(Stream<Artist> artists) {
        return artists.collect(partitioningBy(Artist::isSolo));
    }
    /***********************stream 数据分块 end***************************/

    /***********************stream 数据分组 start***************************/
    /**
     * 使用主唱对专辑分组
     *
     * @param albums
     * @return
     */
    public Map<Artist, List<Album>> albumByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician));
    }
    /***********************stream 数据分组 end***************************/

    /***********************stream 字符串 start***************************/
    /**
     * 用stream输出 格式为："[Geoge Harrison, John Lennon, Paul McCartney, Rigo Starr]"
     *
     * @param artists
     */
    public static void streamJoining(List<Artist> artists) {
        String result = artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(result);
    }
    /***********************stream 字符串 end*****************************/


    /***********************stream 组合收集器 start*****************************/
    //使用收集器计算每个艺术家的专辑数
    public Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician(), counting()));
    }

    //使用收集器求每个艺术家的专辑名
    public Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician, mapping(Album::getName, toList())));
    }
    /***********************stream 组合收集器 end*****************************/

    /***********************stream 重构和定制收集器 start*****************************/


    /***********************stream 重构和定制收集器 end*****************************/


    public static void main(String[] args) {
        List<Artist> allArtists = new ArrayList<>();
        List members = new ArrayList();
        members.add("wang");
        members.add("xiang");
        members.add("yu");
        Artist artist1 = new Artist("张三", members, "London", false);
        Artist artist2 = new Artist("李四", members, "London", true);
        Artist artist3 = new Artist("王五", members, "Old JinShan", true);
        Artist artist4 = new Artist("赵六", members, "Washington DC", true);
        Artist artist5 = new Artist("小八", members, "LuoShanJi", false);

        allArtists.add(artist1);
        allArtists.add(artist2);
        allArtists.add(artist3);
        allArtists.add(artist4);
        allArtists.add(artist5);
        streamJoining(allArtists);

        //List<Artist> allArtists = new ArrayList<>();
        //List members = new ArrayList();
        //members.add("wang");
        //members.add("xiang");
        //members.add("yu");
        //Artist artist1 = new Artist("1",members,"London",false);
        //Artist artist2 = new Artist("2",members,"London",true);
        //Artist artist3 = new Artist("3",members,"Old JinShan",true);
        //Artist artist4 = new Artist("4",members,"Washington DC",true);
        //Artist artist5 = new Artist("5",members,"LuoShanJi",false);
        //
        //allArtists.add(artist1);
        //allArtists.add(artist2);
        //allArtists.add(artist3);
        //allArtists.add(artist4);
        //allArtists.add(artist5);
        //streamJoining(allArtists);

        //double[] d = paralleInitiallize(10);
        //printArray(d);
        double[] d1 = {0, 1, 2, 3, 4, 3.5};
        simpleMovingAverage(d1, 3);

    }

    /***********************stream 并行化流 start*****************************/
    public int addIntegers(List<Integer> values) {
        return values.parallelStream()
                .mapToInt(i -> i)
                .sum();
    }

    public static double[] paralleInitiallize(int size) {
        double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);
        return values;
    }

    /***********************stream 并行化流 end*****************************/

    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        printArray(sums);
        int start = n - 1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    double prefix = i == start ? 0 : sums[i - n];
                    return (sums[i] - prefix) / n;
                }).toArray();
    }

    public static void printArray(double[] d) {
        if (d == null || d.length == 0) {
            return;
        }

        for (int i = 0; i < d.length; i++) {
            System.out.println(d[i]);
        }
    }
}