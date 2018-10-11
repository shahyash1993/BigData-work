/*
* Create three files with random integers in the range 0 to Int.MaxValue/2. The first file contains
1,000 integers, the second 100,000 integers, the third 10,000,000 integers. Write
scala code to find the average and median value for each file. What are the average and
median value for each file? How long does it take compute those values for each file?
* */

import java.io._
import scala.io.Source

object Problem8 {
  def main(args: Array[String]): Unit = {

    var r = scala.util.Random

    var range: Int = Int.MaxValue / 2
    val file1_size: Int = 1000
    val file2_size: Int = 100000
    val file3_size: Int = 10000000

    /*    var range: Int = 10
        val file1_size: Int = 5
        val file2_size: Int = 5
        val file3_size: Int = 5*/

    val file1_arr = new Array[Int](file1_size)
    val file2_arr = new Array[Int](file2_size)
    val file3_arr = new Array[Int](file3_size)

    val file1_name: String = "File1.txt"
    val file2_name: String = "File2.txt"
    val file3_name: String = "File3.txt"

    //create files
    val file1_obj = new File(file1_name)
    val file2_obj = new File(file2_name)
    val file3_obj = new File(file3_name)

    val printWriter_file1 = new PrintWriter(file1_obj)
    val printWriter_file2 = new PrintWriter(file2_obj)
    val printWriter_file3 = new PrintWriter(file3_obj)

    //inserting in files...
    //file1
    for (i <- 0 until file1_size) {
      printWriter_file1.write(r.nextInt(range).toString + "\n")
    }

    //file2
    for (i <- 0 until file2_size) {
      printWriter_file2.write(r.nextInt(range).toString + "\n")
    }

    //file3
    for (i <- 0 until file3_size) {
      printWriter_file3.write(r.nextInt(range).toString + "\n")
    }

    //closing..
    printWriter_file1.close()
    printWriter_file2.close()
    printWriter_file3.close()

    //reading files...
    val source_file1 = Source.fromFile(file1_name)
    val source_file2 = Source.fromFile(file2_name)
    val source_file3 = Source.fromFile(file3_name)

    //file1
    var count: Int = 0
    for (line <- source_file1.getLines()) {
      file1_arr(count) = line.toInt
      count = count + 1
    }

    //file2
    count = 0
    for (line <- source_file2.getLines()) {
      file2_arr(count) = line.toInt
      count = count + 1
    }

    //file3
    count = 0
    for (line <- source_file3.getLines()) {
      file3_arr(count) = line.toInt
      count = count + 1
    }

    //writin on screen...
    /*//temp
    println("file1:>")
    for(i<-file1_arr){
      println(">"+i)
    }
    println("file2:>")
    for(i<-file2_arr){
      println(">"+i)
    }
    println("file3:>")
    for(i<-file3_arr){
      println(">"+i)
    }*/

    //closing..
    source_file1.close()
    source_file2.close()
    source_file3.close()

    //calculate avg & median
    var avg_file1: Double = 0
    var avg_file2: Double = 0
    var avg_file3: Double = 0

    var sum_file1: Double = 0
    var sum_file2: Double = 0
    var sum_file3: Double = 0

    for (i <- file3_arr) {
      sum_file3 += i
    }

    //file1
    var startTime = System.nanoTime() //gets the current time in nano

    for (i <- file1_arr) {
      sum_file1 += i
    }

    avg_file1 = sum_file1 / file1_size
    val median_file1 = file1_arr.sortWith(_ < _).drop(file1_arr.length / 2).head
    var duration = (System.nanoTime() - startTime) / 1e9d //calculates the difference

    println("file1: runtime: " + duration)

    //file2
    startTime = System.nanoTime()

    for (i <- file2_arr) {
      sum_file2 += i
    }

    avg_file2 = sum_file2 / file2_size
    val median_file2 = file2_arr.sortWith(_ < _).drop(file2_arr.length / 2).head
    duration = (System.nanoTime() - startTime) / 1e9d

    println("file2: runtime: " + duration)

    //file3
    startTime = System.nanoTime()

    for (i <- file1_arr) {
      sum_file1 += i
    }

    avg_file3 = sum_file3 / file3_size
    val median_file3 = file3_arr.sortWith(_ < _).drop(file3_arr.length / 2).head
    duration = (System.nanoTime() - startTime) / 1e9d

    println("file3: runtime: " + duration)


    println("avg1: " + avg_file1)
    println("avg2: " + avg_file2)
    println("avg3: " + avg_file3)

    println("med1: " + median_file1)
    println("med2: " + median_file2)
    println("med3: " + median_file3)
  } //main
}

//obj
