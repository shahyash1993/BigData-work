import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._

object Problem_41 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")

    //spark session and spark context
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().config(conf).getOrCreate();

    //Getting data from tsv file
    val titanicDF = spark.read
      .format("org.apache.spark.csv")
      .option("header", true)
      .option("inferSchema", true)
      .option("delimiter", "\t")
      .option("mode", "DROPMALFORMED")
      .csv("D:\\temp\\titanic.tsv")

    val notNullVal = titanicDF.where(col("age") > 0).count()
    val totalVal = titanicDF.count()

    println(">>Count: " + notNullVal)
    println(">>Total Count: " + totalVal)
    println(">>Diff: " + (totalVal - notNullVal))

    //Dropping Null values
    val droppedNullAgeDF = titanicDF.na.drop("any", Seq("age"))
    println(">>After dropping: " + droppedNullAgeDF.count())

    //Logic to create age groups
    val modifiedDroppedNullAgeDF = droppedNullAgeDF
      .orderBy(col("age"))
      .withColumn("ageGroup", floor(col("age") / 5) * 5)

    val countY = count(when(modifiedDroppedNullAgeDF("survived".toString()) === "y", true))

    //finding survival percentage
    val finalDF = modifiedDroppedNullAgeDF
      .groupBy(col("ageGroup"))
      .agg(countY / count("ageGroup") * 100 as "Survival (%)")

    finalDF.orderBy("ageGroup").show()

    println(">>End")
  } //end
}
