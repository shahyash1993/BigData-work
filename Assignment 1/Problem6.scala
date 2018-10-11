/*
* Using Scala compute the sums 0.0001f + 8000.0f and 0.000000001 + 90000000. What is
the result? How big is the error?
*/

object Problem6 {
  def main(args: Array[String]): Unit = {
    var sum = 0.0001f + 8000.0f + 0.000000001 + 90000000
    println("Sum: " + sum)
    /*
    Program sum: 9.0008E7
    Sum should be: 90008000.000100001
    Error: 90008000.000100001 - 90008000 = 0.000100001
     */
  } //def
}

//obj
