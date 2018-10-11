  /*
  *
  *   * 6. Create an array with random 100,000 integers between 0 and 50,000. Compute the mean
  value of the array, call it M. Take a random sample of 100 different elements (sampling
  without replacement) of the array and compute the mean of the sample. What is the error?
  *
  * */

  object Problem_26 {

    def sampleNoRepeats(range:Int, n:Int):Array[Int]={
      //if the range is less than n, it can't provide n unique random numbers

      if(range <= 0 || n <=0){
        println("Error! range or n <= 0")
        return Array(-1)
      }

      else if(range<n){
        println("Error! range < n")
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

    def solution(): Unit ={
      val n:Int = 100000
      val range:Int = 50000
      val sampleSize:Int = 100

      /*  val n:Int = 10
          val range:Int = 50
          val sampleSize:Int = 3*/

      var r = scala.util.Random
      var randomArr:Array[Int] =  new Array[Int](n.toInt)
      var sampleArr:Array[Int] =  new Array[Int](sampleSize)

      //array with random 100,000 integers between 0 and 50,000 created!
      for(i<-0 until n.toInt){
        randomArr(i) = r.nextInt(range)
      }

      //Compute the mean value of the randomArr
      var randomSum:Float= 0
      for(i <- randomArr.indices){
        randomSum += randomArr(i)
      }

      val M:Float= randomSum / n.toInt
      println("Original Mean: "+M)

      //Take a random sample of 100 different elements (sampling without replacement) of the array.
      val sampleIndex = sampleNoRepeats(n.toInt,sampleSize)

      for(i<-0 until sampleSize){
        sampleArr(i) = randomArr(sampleIndex(i))
      }

      //and compute the mean of the sample
      val sampleMean:Int = sampleArr.sum/sampleSize

      //println(">>sample Arr: "+sampleArr.mkString(""," ",""))
      println("Sample Mean: "+sampleMean)

      //error is:
      val error:Float = 100*Math.abs((M - sampleMean)/sampleMean)
      println("Error: "+error+"%")
    }

    def main(args: Array[String]): Unit = {
      solution()
    }//end of Main
  }//end of Obj
