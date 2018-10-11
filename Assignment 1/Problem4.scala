/*
* Write a function noDuplicates(ints : Array[Int]) that returns an array all values from the array
ints with duplicates removed.
*/

object Problem4 {
  def main(args: Array[String]): Unit = {
    var arr: Array[Int] = Array(1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5)
    println("Start!")
    arr = noDuplicates(arr)
    for (i <- arr) {
      print(i + " ")
    }
  }

  def noDuplicates(ints: Array[Int]): Array[Int] = {
    ints.toSet.toArray
  } //def
}

//obj
