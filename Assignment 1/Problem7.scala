/*
Write a function randomInts(n:Int, range:Int) that returns an array of n integers all between
0 and range
 */

object Problem7 {
  def main(args: Array[String]): Unit = {
    println("Start!")
    var arr: Array[Int] = randomInts(10, 100)
    for (i <- arr) {
      print(i + " ")
    }
  }

  def randomInts(n: Int, range: Int): Array[Int] = {
    val r = scala.util.Random
    var randoms = new Array[Int](n)
    for (i <- 0 until n) {
      randoms(i) = r.nextInt(range) //generates random number in specific range specified by 'range'
    }
    randoms
  } //def
}

//obj
