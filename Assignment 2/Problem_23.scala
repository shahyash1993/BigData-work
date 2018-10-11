/*
*
* 3. Implement factorial using “to” and “reduceLeft” without a loop or recursion.
*
* */

object Problem_23 {

  //test cases
  assert(factorial(2)==2)
  assert(factorial(0)==1)
  assert(factorial(-3)==(-6))

  def main(args: Array[String]): Unit = {

    val n:Int = -3
    println("Fact>"+factorial(n))
  }
  def factorial(n:Int):Int = {
    if(n==0){       //testing edge cases
      return 1
    }

    var arr:Array[Int] = (1 to Math.abs(n)).toArray

    if(n<0){        //if the number is negative return the negative result
      return -arr.reduceLeft(_*_)
    }
    arr.reduceLeft(_*_)
    //arr.product can also be used
  }//end of def
}
