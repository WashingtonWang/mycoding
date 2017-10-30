package com.xiangyu.lambdaeg.eg2;

import com.xiangyu.lambdaeg.eg1.Album;
import com.xiangyu.lambdaeg.eg1.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
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
     * @param artists
     * @return
     */
    public Optional<Artist> biggestGroup(Stream<Artist> artists){
        Function<Artist, Long> getCount = artist -> artist.getMembers().stream().count();
        return artists.max(Comparator.comparing(getCount));
    }

    /** stream 转换成值
     * 找出一组专辑上曲目的平均数
     * @param albums
     * @return
     */
    public double averageNumberOfTracks(List<Album> albums){
        return albums.stream()
                .collect(averagingInt(album -> album.getTracks().size()));
    }
    /***********************stream 转换成值 end***************************/

    /***********************stream 数据分块 start***************************/
    /**
     * 将艺术家组成的流分成乐队和独唱歌手的两部分
     * @param artists
     * @return
     */
    public Map<Boolean, List<Artist>> bandAndSolo(Stream<Artist> artists){
        return artists.collect(partitioningBy(Artist::isSolo));
    }
    /***********************stream 数据分块 end***************************/

    /***********************stream 数据分组 start***************************/
    /**
     * 使用主唱对专辑分组
     * @param albums
     * @return
     */
    public Map<Artist, List<Album>> albumByArtist(Stream<Album> albums){
        return albums.collect(groupingBy(Album::getMainMusician));
    }
    /***********************stream 数据分组 end***************************/

    /***********************stream 字符串 start***************************/

    /***********************stream 字符串 end*****************************/

    public static void main(String[] args){

    }
}
