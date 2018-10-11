/*
* 5. Implement a function sampleNoRepeats with two arguments. The first argument is a range,
the second an integer, n, greater that zero. The function returns n random numbers in the
given range with no repeats.
*
* */

object Problem_25 {
  //test cases
  assert(sampleNoRepeats(0,0) sameElements  Array(-1) )

  def sampleNoRepeats(range:Int, n:Int):Array[Int]={
    //if the range is less than n, it can't provide n unique random numbers
    if(range<n){
      println("Error! Range is less than n! Can't get unique random numbers ")
    }

    else if(range <=0 || n <=0){
     println("Error! range or n <= 0")
      return Array(-1)
    }

    val r = scala.util.Random
    var temp:Int = 0
    var s:Set[Int] = Set()

    var i:Int = 0
    while(i<n){
        temp = r.nextInt(range)   //random number will be checked whether it is already in the set or not
        if(!s.contains(temp)){    //if the random number is not in the set
          s=s+temp;               //random number is added in the set
          i+=1
        }
      }
    s.toArray                      //set to array
  }

  def main(args: Array[String]): Unit = {
    var range:Int = 10
    var n:Int = 5
    var randomArr:Array[Int] = sampleNoRepeats(range,n) //range and n
    println(">>"+randomArr.mkString("",", ",""))
  }//end of def
}//end of main