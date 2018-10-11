/*
* Write a function minmax(values: Array[Int]) that returns a pair containing the smallest and
the largest values in the array.
* */

object Problem9 {
  def main(args: Array[String]): Unit = {
    println("Start")
    var arr: Array[Int] = Array(3, 5, 2, 1, 4)
    var result: Tuple2[Int, Int] = minmax(arr)
    println(result)
  }

  def minmax(values: Array[Int]): Tuple2[Int, Int] = {
    var min: Int = values(0)
    var max: Int = values(0)

    for (i <- 1 until values.size - 1) {
      if (values(i) > max) {//if greater than max, changed
        max = values(i)
      }
      if (values(i) < min) {//if smaller than min, changed
        min = values(i)
      }
    } //for
    var tuple2 = (min, max)
    tuple2
  } //def
}

//obj
