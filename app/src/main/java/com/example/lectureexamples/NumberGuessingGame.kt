package com.example.lectureexamples

fun runGame(){
    val randomNumber = getRandomNumber()
    print(randomNumber)

    var inputNumber: Int

    do{
        println("Please enter your guess (4 digits):")
        inputNumber = readlnOrNull()?.toInt()!!
        val n = getN(inputNumber, randomNumber)
        val m = getM(inputNumber, randomNumber)
        if (m != 4) println("Correct Guess : Right Position $n:$m")
    } while (inputNumber != randomNumber)
    println("Congrats, you guessed the correct number!")
}
fun getN(inputNumber: Int, randomNumber: Int): Int {
    val inputNumberSet = inputNumber.toString().toSet() // a set saves which digits are contained in a number (our case a set of Integers)
    val randomNumberSet = randomNumber.toString().toSet()
    return inputNumberSet.intersect(randomNumberSet).size // intersect saves mutual numbers in a set - deswegen .size
}

fun getM(inputNumber: Int, randomNumber: Int): Int {
    val inputNumberList = inputNumber.toString().toList()
    val randomNumberList = randomNumber.toString().toList()
    return inputNumberList.zip(randomNumberList).count { (a, b) -> a == b }
}
fun getRandomNumber(): Int {
    val ones = (Math.random() * 10).toInt()

    var tens = (Math.random() * 10).toInt()
    while (ones == tens)
        tens = (Math.random() * 10).toInt()

    var hundreds = (Math.random() * 10).toInt()
    while (hundreds == ones || hundreds == tens)
        hundreds = (Math.random() * 10).toInt()

    var thousands = (Math.random() * 10).toInt()
    while (thousands == ones || thousands == tens || thousands == hundreds || thousands == 0)
    thousands = (Math.random() * 10).toInt()

    tens *= 10
    hundreds *= 100
    thousands *= 1000

    return ones + tens + hundreds + thousands
}
fun main(){
    runGame()
}