package kotlinsyntax

/**
 * Created by wangxiangyu on 2017/7/16.
 */
//class Person constructor(name: String) {
//    init {
//        print("Perosn initialized with value ${name}")
//    }
//}

//class Customer public @inject constructor(name: String)

open class Person{
    open fun a(){
        print("A")
    }
    fun b(){
        print("a")
    }
}

interface B {
    fun a(){
        print("B")
    }
    fun b(){
        print("b")
    }
}


//class Sub() : Person(){
//    override fun a(){}
//}