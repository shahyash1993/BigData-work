/*
*
* If we list all the natural numbers below 20 that are multiples of 3 or 5 but not multiples both
of 3 and 5, we get 3, 5, 6, 9, 10, 12, 18. The sum of these multiples is 63. Write a function,
sum_multiples_3_5, that returns the sum of the multiples of 3 and 5 but not both less than
N.
*
* */
object Problem_21{

  //test cases
    assert(sum_multiples_3_5(20)==63)
    assert(sum_multiples_3_5(0)==0)
    assert(sum_multiples_3_5(-10)==(-1))


  def main(arg: Array[String]) {
    println("start!")
    val n:Int = 20
    val sum: Int = sum_multiples_3_5(n)
    println("Sum: " + sum)
  }

  def isValid(i:Int):Boolean ={
    if(i%3==0){
      if(i%5!=0) {
        true
      }
      else{
        false
      }
    }
    else if(i%5==0){
      true
    }
    else{
      false
    }
  }

  def sum_multiples_3_5(n: Int): Int = {
    if(n<0){
      print("negative n given!")
      return -1
    }

    val numArr = (1 until n).toArray
    val modifiedNum = numArr.filter(isValid)    //checking whether the number satisfies the condition or not
    println(modifiedNum.mkString("<",", ",">"))
    val sum:Int = modifiedNum.sum               //summation of the whole array
    sum
  } //main End
}//object End
