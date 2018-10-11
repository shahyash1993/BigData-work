import org.apache.spark.sql.functions._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object Problem_42 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")

    //spark session and spark context
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().config(conf).getOrCreate();

    //Getting data from  tsv filess
    val titanicDF = spark.read
      .format("org.apache.spark.csv")
      .option("header", true)
      .option("inferSchema", true)
      .option("delimiter","\t")
      .option("mode","DROPMALFORMED")
      .csv("D:\\temp\\titanic.tsv")

    //Dropping null values
    val droppedNullAgeDF = titanicDF.na.drop("any",Seq("age"))
    println(">>After dropping: "+droppedNullAgeDF.count())

    //Renaming home.dest because of the unusual error
    val newTitanicDF = droppedNullAgeDF.withColumnRenamed("home.dest","home")

    //<<Machine Learning Stuff>>>
    import org.apache.spark.ml.feature.RFormula

    //Generating formula to predict survival based on the age
    val examFormula = new RFormula()
      .setFormula("survived ~ age")
      .setFeaturesCol("features")
      .setLabelCol("label")

    //Splitting the data into Trainset and Testsets
    val fittedRF = examFormula.fit(newTitanicDF)
    val preparedDF = fittedRF.transform(newTitanicDF)
    val Array(trainDF, testDF) = preparedDF.randomSplit(Array(0.9, 0.1))
    trainDF.show

    println("total: "+newTitanicDF.count())
    println("train: "+trainDF.count())
    println("test: "+testDF.count())

    //fitting the  model
    import org.apache.spark.ml.classification.LogisticRegression
    val lr = new LogisticRegression()
              .setFeaturesCol("features")
              .setLabelCol("label")
    val lrModel = lr.fit(trainDF)

    val trainedModelDF = lrModel.evaluate(testDF).predictions
    trainedModelDF.show()

    //Finding out the wrong predictions
    val wrongPredictions = trainedModelDF.where(expr("label != prediction"))
    val incorrectPredicition = wrongPredictions.count()
    println("Incorrect Predictons: "+incorrectPredicition)

    //finding errors
    val countErrors = wrongPredictions
      .groupBy("label")
      .agg(count("prediction").alias("Errors"))
    countErrors.show

    //Finding out TP, TN, FP, FN
    val truePositives = trainedModelDF.where(expr("label == 1 AND prediction == 1")).count()
    println("TP: "+truePositives)

    val trueNegatives= trainedModelDF.where(expr("label == 0 AND prediction == 0")).count()
    println("TN: "+trueNegatives)

    val falsePositive= trainedModelDF.where(expr("label == 0 AND prediction == 1")).count()
    println("FP: "+falsePositive)

    val falseNegative= trainedModelDF.where(expr("label == 1 AND prediction == 0")).count()
    println("FN: "+falseNegative)

    val totalCount= trainedModelDF.count()
    println("total: "+totalCount)

    val percentFP:Double = falsePositive*100/totalCount
    val percentFN:Double = falseNegative*100/totalCount

    println("%FP=" + percentFP+"%")
    println("%FN=" + percentFN+"%")
  }//end
}
