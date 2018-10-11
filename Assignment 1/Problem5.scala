/*
* Create an array that contains the value 0.00001f repeated 1000000 times. Sum up all the
elements of the array. What is the result? How big is the error?
*/

object Problem5 {
  def main(args: Array[String]): Unit = {
    var arr = new Array[Float](1000000)
    for (i <- 0 until 1000000) {
      arr(i) = 0.00001f
    }//value inserted in Array

    var sum: Float = 0f
    for (i <- 0 until 1000000) {
      sum = sum + arr(i)
    }//sum calculated

    println("Sum: " + sum)

    /*output:
     Program Sum: 9.917345
     Actual Sum: 10
     Error: 10 - 9.917345 = 0.082655
    */
  } //main
}

//obj
