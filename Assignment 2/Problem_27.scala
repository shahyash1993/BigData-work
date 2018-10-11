/*
*
*   7. Using the same array as in #6 take 1,000 samples of 100 random elements of the array.
Compute the mean of each sample. Find the value r where interval (M-r, M+r) contains 95%
of the means of the 1000 samples.
*
* */

object Problem_27{

  def square(x:Double):Double = x*x

  def sampleNoRepeats(range:Int, n:Int):Array[Int]={
    //if the range is less than n, it can't provide n unique random numbers
    if(range<n){
      System.exit(0)
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

  def findR():Double={
    val n:Int = 100000
    val range:Int = 50000
    val sampleElementSize:Int = 100
    val numberOfSamples:Int = 1000
    val z: Double = 1.96


    /*val n: Int = 10
    val range: Int = 50
    val sampleElementSize: Int = 3
    val numberOfSamples: Int = 10
    val z: Double = 1.96*/

    var rand = scala.util.Random
    var randomArr: Array[Int] = new Array[Int](n.toInt)
    var sampleArr: Array[Int] = new Array[Int](sampleElementSize)

    //array with random 100,000 integers between 0 and 50,000 created!
    for (i <- 0 until n.toInt) {
      randomArr(i) = rand.nextInt(range)
    }

    //Compute the mean value of the randomArr
    var randomSum: Float = 0
    for (i <- randomArr.indices) {
      randomSum += randomArr(i)
    }

    val M: Float = randomSum / n

    //println(">>Random Arr: "+randomArr.mkString(""," ",""))
    println("True Mean: " + M)

    var sampleMeanArr: Array[Double] = new Array[Double](numberOfSamples)

    for (j <- 0 until numberOfSamples) {

      //Take a random sample of 100 different elements (sampling without replacement) of the array.
      val sampleIndex:Array[Int] = sampleNoRepeats(n.toInt, sampleElementSize)

      for (i <- 0 until sampleElementSize) {
        sampleArr(i) = randomArr(sampleIndex(i))
      }
      //and compute the mean of the sample
      val sampleMean: Float = sampleArr.sum / sampleElementSize

      //println(">>sample Arr: "+sampleArr.mkString(""," ",""))
      //println("Sample Mean: " + sampleMean)
      sampleMeanArr(j) = sampleMean

      //Find the value r where interval (M-r, M+r) contains 95% of the means of the 1000 samples
      //r = z*s/sqrt(n)
    }

    //trial var sampleMeanArr: Array[Int] =  Array(0,10,20,30,40)
    val sampleMeanArrMean: Double = sampleMeanArr.sum / sampleMeanArr.length

    var xMinusXBarSquareArr: Array[Double] = new Array[Double](sampleMeanArr.length)

    for (i <- sampleMeanArr.indices) {
      xMinusXBarSquareArr(i) = square(Math.abs(sampleMeanArr(i) - sampleMeanArrMean))
    }

    var SD: Double = Math.sqrt(xMinusXBarSquareArr.sum / xMinusXBarSquareArr.length)

    var r: Double = z * SD / Math.sqrt(sampleMeanArr.length)
    println("sampleMeanArr.sum:  " + sampleMeanArr.sum + "| sampleMeanArr.length:  " + sampleMeanArr.length)
    println("xMinusXBarSquare.sum: "+xMinusXBarSquareArr.sum +" | SD: "+SD)
    println("sampleMeanArrMean: " + sampleMeanArrMean)

    //extra
    val minInterval = sampleMeanArrMean - r
    val maxInterval = sampleMeanArrMean + r

    val minValue = sampleMeanArr.min
    val maxValue = sampleMeanArr.max

    println("minVal: " + minValue + " | maxVal: " + maxValue)
    println("minInterval is: " + minInterval + " | maxInterval: " + maxInterval)

    println("<minInterval: " + sampleMeanArr.count(_ < minInterval) + " | >maxInterval: " + sampleMeanArr.count(_ > maxInterval))
    println("=total: outsiders: " + (sampleMeanArr.count(_ < minInterval) + sampleMeanArr.count(_ > maxInterval)) + " out of " + sampleMeanArr.length)

    println("Value of r = "+r)

    r
  }//end of findR

  def main(args: Array[String]): Unit = {
    println("Value of r is: "+findR())
  }//end of Main
}//end of Obj