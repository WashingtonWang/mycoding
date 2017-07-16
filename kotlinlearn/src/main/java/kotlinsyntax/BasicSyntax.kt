package kotlinsyntax

import org.apache.coyote.http11.Constants.a
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * Created by wangxiangyu on 2017/7/15.
 */
//1 normal
//fun sum(a: Int, b: Int): Int{
//    return  a + b
//}
// function
//fun sum(a: Int, b: Int) = a + b
//function 1
//fun printSum(a: Int, b: Int):Unit{
//    println("sum of $a and $b is ${a + b}")
//}
//function 2
//fun printSum(a: Int, b: Int){
//    println("sum of $a and $b is ${a + b}")
//}
//fun main(args: Array<String>) {
////    print("sum is : ")
////    println(sum(3,5))
//    printSum(3,5)
//}

//fun main(args: Array<String>){
//    val a: Int = 1
//    val b = 2
//    val c: Int
//    c = 3
//    println("a = $a, b = $b, c = $c ")
//}

/*fun main(args: Array<String>){
    var x = 5
    x += 1
    println("x = $x")
}*/

//fun main(args: Array<String>){
//    var a = 1
//    val s1 = "a is $a"
//    a = 2
//    val s2 = "${s1.replace("is","was")}, but now is $a"
//    println(s2)
//
//}

//fun maxOf(a: Int, b: Int): Int{
//    if (a > b){
//        return a
//    }else{
//        return b
//    }
//}
//fun maxOf(a: Int, b: Int) = if(a > b) a else b
//fun main(args: Array<String>){
//    println("max of 0 and 42 is ${maxOf(2,42)}")
//}

/*fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}*/
/*fun printProduct(arg1: String, arg2: String){
    val x = parseInt(arg1)
    val y = parseInt(arg2)
    *//*if (x != null && y != null){
        println(x * y)
    }else{
        println("either '$arg1' or '$arg2' is not a number")
    }*//*

    if (x == null){
        println("wrong number format in arg1: '$arg1' ")
        return
    }
    if (y == null){
        println("wrong number format in arg2: '$arg2' ")
        return
    }
    println(x * y)
}*/
/*
fun main(args: Array<String>){
    printProduct("6","7")
    printProduct("a","b")
    printProduct("a","6")

}*/

//fun getStringLength(obj: Any): Int?{
//    if (obj is String){
//        return obj.length
//    }
//    return null
//}
//fun getStringLength(obj: Any): Int?{
//    if (obj !is String) return null
//    return obj.length
//}
//fun getStringLength(obj: Any): Int?{
//    if (obj is String && obj.length > 0){
//        return obj.length
//    }
//    return null
//}
//fun main(args: Array<String>){
//    fun printLength(obj: Any){
//        println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
//    }
//    printLength("Incomprehensibiilteis")
//    printLength(1000)
//    printLength(listOf(Any()))
//}

//fun main(args: Array<String>){
//    val items = listOf("apple", "banana", "kiwi")
//    for (item in items){
//        println(item)
//    }
//
//    for (index in items.indices){
//        println("item at $index is ${items[index]}")
//    }
//
//}

//fun main(args: Array<String>){
//    val items = listOf("apple", "banana", "kiwi")
//    var index = 0
//    while (index < items.size){
//        println("item at $index is ${items[index]}")
//        index++
//    }
//}

//fun describe(obj: Any): String =
//    when (obj){
//        1 -> "one"
//        "Hello" -> "Greeting"
//        is Long -> "Long"
//        !is String -> "Not a string"
//        else -> "Uknown"
//    }
//fun main(args: Array<String>){
//    println(describe(1))
//    println(describe("Hello"))
//    println(describe(1000L))
//    println(describe(2))
//    println(describe("other"))
//}

//fun main(args: Array<String>){
//    val x = 10
//    val y = 9
//    if (x in 1..y+1){
//        println("fits in range")
//    }
//}

//fun main(args: Array<String>){
//    val list = listOf("a", "b", "c")
//    if (-1 !in 0..list.lastIndex){
//        println("-1 is out of range")
//    }
//    if (list.size !in list.indices){
//        println("list size is out of valid list indices range too")
//    }
//}

//fun main(args: Array<String>){
//    for (x in 1..5){
//        print(x)
//    }
//}

//fun main(args: Array<String>){
//    for (x in 1..10 step 2){
//        print(x)
//    }
//    println()
//    for (z in 9 downTo 0 step 3){
//        print(z)
//    }
//    println()
//}

//fun main(args: Array<String>){
//    val items = listOf("apple", "banana", "kiwi")
//    for (item in items){
//        println(item)
//    }
//}

//fun main(args: Array<String>){
//    val items = setOf("apple", "banana", "kiwi")
//    when{
//        "orange" in items -> println("juicy")
//        "apple" in items -> println("apple is fine too")
//    }
//}

//fun main(args: Array<String>){
//    val fruits = listOf("banana", "avocado", "apple", "kiwi")
//    fruits.filter { it.startsWith("a") }
//            .sortedBy { it }
//            .map { it.toUpperCase() }
//            .forEach { println(it) }
//}

//fun main(args: Array<String>){
//    val data = null
//    data?.let { println("no is null") }
//    test()
//    foo(1)
//    var a = transform("Blue")
//    println(a)
//}
//fun javaStream(){
//    val stream = Files.newInputStream(Paths.get("/asdf/asdf.txt"))
//    stream.buffered().reader().use { reader ->
//        println(reader.readText())
//    }
//}
//
//fun transform(color: String): Int = when(color){
//    "Red" -> 0
//    "Green" -> 1
//    "Blue" -> 2
//    else -> throw IllegalStateException("Invalid color param value")
//}
//
//fun foo(param: Int){
//    val result = if (param == 1){
//        "one"
//    }else if (param == 2){
//        "two"
//    }else {
//        "three"
//    }
//    println(result)
//}
//
//fun test(){
//    val result = try {
//        println("xxxx")
//    }catch (e: ArithmeticException){
//        throw IllegalStateException(e)
//    }
//}

//fun main(args: Array<String>){
//    val a: Int = 1000
//    val b: Int = 1000
//    println(a === a)
//    println(a === b)
//
//    val boxedA: Int? = a
//    val anotherBoxedA: Int? = a
//    println(boxedA === anotherBoxedA)
//    println(boxedA == anotherBoxedA)
//}

//fun main(args: Array<String>){
//    val a: Int? = 1
//    val b: Long? = a?.toLong()
//    val x = 1L + 3
//    println(x)
//}

//fun main(args: Array<String>){
//    val x = (1 shl 2) and 0x000FF000
//    println(x)
//}

//fun decimalDigitValue(c: Char): Int{
//    if (c !in '0'..'9'){
//        throw IllegalStateException("Out of range")
//    }
//    return c.toInt() - '0'.toInt()
//}
//fun main(args: Array<String>){
//    decimalDigitValue('!')
//}

//fun main(args: Array<String>){
//    val asc = Array(5,{i -> (i * i).toString()})
//    println(asc)
//    for (i in asc)
//        println(i)
//}

//fun main(args: Array<String>){
//    val text = """
//    for (c in "foo")
//        print(c)
//    """.trimMargin()
//    println(text)
//}

//fun main(args: Array<String>){
//    loop@ for (i in 1..100){
//        println(i)
//        for (j in 1..100){
//            print(j)
//            if (i == 2){
//                break@loop
//            }
//        }
//    }
//}
