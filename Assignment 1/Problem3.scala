/*
* Write a function product(s : String) that returns the product of the Unicode codes of all letters
in string s without using a loop.
* */

object Problem3 {
  def main(args: Array[String]): Unit = {
    println("Start!")
    var result: Long = product("abc")
    println("Product is  " + result)
  }

  def product(s: String): Long = {
    s.foldLeft(1L)(_ * _.toInt) //It multiplies the hex unicodes first, and converts them back to decimals afterwards
  } //def
}

//obj
