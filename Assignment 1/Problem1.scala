
/*If we list all the natural numbers below 20 that are multiples of 3 or 5 but not multiples both
  of 3 and 5, we get 3, 5, 6, 9, 10, 12, 18. The sum of these multiples is 63. Write a function,
  sum_multiples_3_5, that returns the sum of the multiples of 3 and 5 less than N.
  */

import scala.util.control.Breaks._

object Problem1 {
  def main(arg: Array[String]) {
    println("start!")
    val sum: Int = sum_multiples_3_5(20)
    println("Sum: " + sum)
  }

  def sum_multiples_3_5(n: Int): Int = {

    var sum: Int = 0
    if (n < 0) {
      println("Error! Number is negative!")
      System.exit(1)
    }

    for (i <- 1 until n) {
      if (i % 3 == 0) { //if the number is multiple of 3 only
        if (i % 5 != 0) { //if the number is multiple of both
          sum = sum + i
        }
      }
      else if (i % 5 == 0) {  //if the number is multiple of 5 only
        sum = sum + i
      }
    } //for
    sum

  } //main
}

//object