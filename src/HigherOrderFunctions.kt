//fun main() {
//    displayMessage(::morning)
//    displayMessage(::evening)
//}
//
//fun displayMessage(mes: () -> Unit){
//    mes()
//}
//
//fun morning(){
//    println("Good morning")
//}
//
//fun evening(){
//    println("Good Evening")
//
//    action(5, 3,  ::sum)
//    action( 5, 3,  ::multiply)
//    action( 5,  3,  ::subtract)
//}
//
//fun action(n1: Int, n2: Int, op: (Int, Int) -> Int) {
//    val result = op(n1, n2)
//    println(result)
//}
//
//fun sum(a: Int, b: Int): Int {
//    return a + b
//}
//
//fun subtract(a: Int, b: Int): Int {
//    return a - b
//}
//
//fun multiply(a: Int, b: Int): Int {
//    return a * b
//}

fun selectAction(key: Int): (Int, Int) -> Int {
    return when(key) {
        1 -> ::sum
        2 -> ::subtract
        3 -> ::multiply
        else -> ::empty
    }
}

fun empty(a: Int, b: Int): Int {
    return 0
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun subtract(a: Int, b: Int): Int {
    return a - b
}

fun multiply(a: Int, b: Int): Int {
    return a * b
}

fun main() {
    val action1 = selectAction(key = 1)
    println(action1(8,5))

    val action2 = selectAction(key = 2)
    println(action2(8,5))
}