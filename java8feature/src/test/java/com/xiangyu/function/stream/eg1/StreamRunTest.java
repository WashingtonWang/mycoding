package com.xiangyu.function.stream.eg1;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xiangyu.wang on 2017/7/8.
 */
public class StreamRunTest {
    public static void main(String[] args) {
        //Stream<String> stream = Stream.of("a","b","c");
        //String [] strArray = new String[] {"a", "b", "c"};
        //Arrays.stream(strArray).forEach(o -> System.out.println(o.toString()));
        //Stream s = Stream.of(strArray);
        //List<String> collection = Arrays.asList(strArray);
        //Stream stream = collection.stream();
        //stream.forEach(e -> System.out.println(e.toString()));
        //IntStream.of(new int[]{1,2,3}).forEach(System.out::println);
        //IntStream.range(1,4).forEach(System.out::println);
        //IntStream.rangeClosed(1,3).forEach(System.out::println);
        //String[] strArray1 = stream.toArray(String[]::new);
        //Arrays.stream(strArray1).forEach(o -> System.out.println(o));
        //List<String> list1 = stream.collect(Collectors.toList());
        //list1.stream().forEach(o -> System.out.println(o));
        //List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        //list2.stream().forEach(o -> System.out.println(o));
        //List wordList = new ArrayList();
        //wordList.add("a");
        //wordList.add("b");
        //wordList.add("c");
        //List<String> output = wordList.stream().map(String::toUpperCase).collect(Collectors.toList());
        //output.stream().forEach(System.out::println);
        //List<Integer> nums = Arrays.asList(1,2,3,4,5);
        //List<Integer> squareNums = nums.stream().map(n -> n*n).collect(Collectors.toList());
        //squareNums.stream().forEach(System.out::println);
        //Stream<List<Integer>> inputStream = Stream.of(
        //        Arrays.asList(1),
        //        Arrays.asList(2,3),
        //        Arrays.asList(4,5,6)
        //);
        //Stream<Integer> outputStream = inputStream.flatMap(childList -> childList.stream());
        //outputStream.forEach(System.out::println);
        //Integer[] sixNums = {1,2,3,4,5,6};
        //Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        //Arrays.stream(evens).forEach(System.out::println);
        //Stream.of("one","two","three")
        //        .filter(e -> e.length() > 3)
        //        .peek(e -> System.out.println("Filtered valie : " +e))
        //        .map(String :: toUpperCase)
        //        .peek(e -> System.out.println("Mapped value : " +e))
        //        .collect(Collectors.toList()).forEach(System.out::println);
        //String strA = " abcd ", strB = null;
        //print(strA);
        //print("");
        //print(strB);
        //getLength(strA);
        //getLength("");
        //getLength(strB);
        /**********reduce*******/
        //String concat = Stream.of("A","B","C","D").reduce("",String::concat);
        //double minValue = Stream.of(-1.5,4.0,6.2,-2.1,-0.2).reduce(Double.MAX_VALUE,Double::min);
        //int sumValue = Stream.of(1,2,3,4).reduce(0,Integer::sum);
        //int sumValue1 = Stream.of(1,2,3,4,5).reduce(Integer::sum).get();
        //concat = Stream.of("a", "B", "c", "D", "e", "F")
        //        .filter(x -> x.compareTo("Z") > 0)
        //        .reduce("",String::concat);
        //System.out.println(concat);
        //List<Person> persons = new ArrayList();
        //for (int i = 1; i <= 5; i++) {
        //    Person person = new Person(i, "name" + i);
        //    persons.add(person);
        //}
        //List<String> personList3 = persons.stream()
        //        .map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
        //System.out.println(personList3);
        //personList3.forEach(System.out::println);
        //List<Person> personList = persons.stream()
        //        .sorted((p1,p2) -> p1.getName().compareTo(p2.getName())).limit(2).collect(Collectors.toList());
        //System.out.println(personList);
        //personList.forEach(System.out::println);
        //List<Person> personList2 = persons.stream()
        //        .limit(2)
        //        .sorted((p1,p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
        //System.out.println(personList2);
        /*BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\SUService.log"));
            //int longest = br.lines().
            //        mapToInt(String::length).
            //        max().
            //        getAsInt();
            //System.out.println(longest);
            List<String> words = br.lines()
                    .flatMap(line -> Stream.of(line.split(" ")))
                    .filter(word -> word.length() > 0)
                    .map(String::toLowerCase)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println(words);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/

        /*List<Person> persons = new ArrayList();
        persons.add(new Person(1, "name" + 1, 10));
        persons.add(new Person(2, "name" + 2, 21));
        persons.add(new Person(3, "name" + 3, 34));
        persons.add(new Person(4, "name" + 4, 6));
        persons.add(new Person(5, "name" + 5, 55));
        boolean isAllAdult = persons.stream()
                .allMatch(p -> p.getAge() > 18);
        System.out.println(isAllAdult);
        boolean isThereAnyChild = persons.stream()
                .anyMatch(p -> p.getAge() < 12);
        System.out.println(isThereAnyChild);*/

        //Stream.generate(new PersonSupplier())
        //        .limit(10)
        //        .forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));

        //Stream.iterate(0, n -> n+3).limit(10).forEach(x -> System.out.println(x + ""));

        /*Map<Integer,List<Person>> personGroups = Stream.generate(new PersonSupplier())
                .limit(100)
                .collect(Collectors.groupingBy(Person::getAge));
        Iterator it = personGroups.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer, List<Person>> persons = (Map.Entry<Integer, List<Person>>) it.next();
            System.out.println("age : "+ persons.getKey() + " = "+ persons.getValue().size());
        }*/

        Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier())
                .limit(100)
                .collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println("Children number: "+ children.get(true).size());
        System.out.println("Adult number: "+ children.get(false).size());
    }

    static class PersonSupplier implements Supplier<Person> {
        private int index = 0;
        private Random random = new Random();
        public Person get(){
            return new Person(index++, "StormTestUser"+index, random.nextInt(100), BigDecimal.ONE, "北京");
        }
    }

    public static void print(String text) {
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
    }
    public static int getLength(String text) {
        // Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
    };
}

