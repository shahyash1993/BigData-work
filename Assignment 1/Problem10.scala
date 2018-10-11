/*
* Make a class Car with read-only properties for manufacturer, model name, and model year,
and a read-write property for the license plate. Supply four constructors. All require the
manufacturer and model name. Optionally, model year and license plate can also be specified
in the constructor. If not, the model year is set to -1 and the license plate to the empty
string. Which constructor are you choosing as the primary constructor? Why?
* */

object Problem_10 {
  def main(args: Array[String]): Unit = {
    println("Start!")

    var car_obj1 = new Car("aa", "bb", 1, "cc")
    car_obj1.print()

    var car_obj2 = new Car("dd", "ee", "ff")
    car_obj2.print()

    var car_obj3 = new Car("gg", "hh", 9)
    car_obj3.print()
  }
}

class Car(val manufacturer: String, val modelName: String, val modelYear: Int, var licensePlate: String) {
  //primary constructor
  /*
     This constructor is defined as a primary constructor because it contains all the four data members and also
     other constructors can pass on default values for members that were not given values for
  */

  def this(manufacturer: String, modelName: String, modelYear: Int) = {
    //constructor2
    this(manufacturer, modelName, modelYear, "")
  }

  def this(manufacturer: String, modelName: String, licensePlate: String) = {
    //constructor3
    this(manufacturer, modelName, -1, licensePlate)
  }

  def this(manufacturer: String, modelName: String) = {
    //constructor4
    this(manufacturer, modelName, -1, "")
  }

  def print() {
    println("manufacturer: " + manufacturer + " | modelName: " + modelName + " | modelYear: " + modelYear + " | licensePlate: " + licensePlate)
  }
}