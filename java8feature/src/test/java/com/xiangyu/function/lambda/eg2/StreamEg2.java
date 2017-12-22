package com.xiangyu.function.lambda.eg2;

import com.xiangyu.function.lambda.eg3.Album;
import com.xiangyu.function.lambda.eg3.Artist;
import com.xiangyu.function.stream.eg1.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * user: xiangyu.wang
 * date: 2017/11/3 9:39
 */
public class StreamEg2 {

    Runnable r1 = () -> {
        System.out.println(this);
    };
    Runnable r2 = () -> {
        System.out.println(toString());
    };

    public String toString(){
        return "Hello World!";
    }


    public void test(List<Artist> artists){
        Map<String,Artist> map = artists.stream()
                .collect(Collectors.toMap(Artist::getName, artist -> artist));

        //Collection  //collection set map 等集合的上层接口 实现Iterable 接口
        //Collectors  //stream集合类  包括常用的一些如toList toSet方法
        //Collector   //stream的接口
        Supplier<Runnable> c = () -> () -> {
            System.out.println("x");
        };
    }

    public Callable<String> test1(String name){
        String hello = "Hello";
        return () -> (hello + ", " + name);
    }

    public static void main(String[] args) {
        new StreamEg2().r1.run();
        new StreamEg2().r2.run();

        List<Integer> listInt = new ArrayList<>();
        listInt.stream().forEach(e -> {listInt.add(e); });

        Consumer<Integer> b1 = System::exit;

        IntFunction<int[]> arrayMark = int[]::new;
        int[] array = arrayMark.apply(10);

        List<Person> person = new ArrayList<>();
        person.sort(Comparator.comparing(Person::getName));

    }


    /**************************用stream 重用代码start*********************************/
    List<Album> albums = new ArrayList<>();
    public long countFeature(ToLongFunction<Album> function){
        return albums.stream()
                .mapToLong(function)
                .sum();
    }

    public long countTracks(){
        return countFeature(album -> album.getTracks().stream().count());
    }

    public long countRunningTime(){
        return countFeature(album -> album.getTracks().stream()
                .mapToLong(track -> track.getLength()).sum());
    }

    public long countMusicians(){
        return countFeature(album -> album.getMusicians().stream().count());
    }
    /**************************用stream 重用代码end*********************************/

    //public List<String> findHeadings(Reader input){
    //    return withLinesOf(input,
    //            lines -> lines.filter(line -> line.endsWith(":"))
    //                .map(line -> line.substring(0, line.length() - 1))
    //                .collect(toList()),
    //
    //    );
    //}

    private <T> T withLinesOf(Reader input, Function<Stream<String>, T> handler,
                              Function<IOException, RuntimeException> error){
        try(BufferedReader reader = new BufferedReader(input)) {
            return handler.apply(reader.lines());
        }catch (IOException e){
            throw error.apply(e);
        }
    }
}
