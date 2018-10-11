/*Write a function that receives a collection of strings and a map from strings to integers. Return
a collection of integers that are values of the map corresponding to one of the strings
  in the collection. For example, given Array("Tom", "Fred", "Harry") and Map("Tom" -> 3,
  "Dick" -> 4, "Harry" -> 5), return Array(3, 5).
*/

object Problem_24 {

  //test cases
  assert(findTrueValues(
    Array("A","B","C","D"),
    Map("A"->1, "B"->2, "D"->4))
    sameElements  Array(1,2,4) )

  assert(findTrueValues(
    Array(),
    Map())
    sameElements  Array(-1) )

    assert(findTrueValues(
    Array("X","Y","Z"),
    Map("A"->1))
    sameElements  Array[Int]() )

  def main(args: Array[String]): Unit = {

    var myMap:Map[String,Int] = Map("Tom" -> 3,"Dick" -> 4, "Harry" -> 5)
    var myArr:Array[String] = Array("Tom", "Fred", "Harry")

    var resultArr:Array[Int] = findTrueValues(myArr,myMap)
    println(resultArr.mkString("<"," | ",">"))
  }

  def findTrueValues(myArr:Array[String], myMap:Map[String,Int]): Array[Int] ={

    if(myArr.size ==0 || myMap.size == 0){    //checking edge conditions
      return Array(-1)
    }

    myArr.flatMap(myMap.get(_))
  }
}//end
