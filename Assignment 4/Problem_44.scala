import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StructField, StructType, StringType, DoubleType, IntegerType}
import org.apache.spark.ml.feature.RFormula
import org.apache.spark.ml.regression.DecisionTreeRegressor
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.Binarizer
import org.apache.spark.sql.functions._

object Problem_44 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    //Setting time out to 600 seconds
    conf.set("spark.network.timeout", "600s")

    //spark session and spark context
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().config(conf).getOrCreate();

    val schema = new StructType(Array(
      new StructField("survived", StringType, true),
      new StructField("sex", StringType, true),
      new StructField("age", DoubleType, true),
      new StructField("pclass", StringType, true),
      new StructField("name", StringType, true),
      new StructField("sibsp", IntegerType, true),
      new StructField("parch", IntegerType, true),
      new StructField("ticket", StringType, true),
      new StructField("fare", DoubleType, true),
      new StructField("cabin", StringType, true),
      new StructField("embarked", StringType, true),
      new StructField("boat", StringType, true),
      new StructField("body", IntegerType, true),
      new StructField("home", StringType, true)
    ))

    //Getting data from tsv file
    val data = spark.read.format("csv")
      .schema(schema)
      .option("header", true)
      .option("delimiter","\t")
      .load("D:\\temp\\titanic.tsv")

    //Dropping null values from three cols
    val droppedNullAgeDF = data.na.drop("any",Seq("age","sex","pclass"))
    println(">>After dropping: "+droppedNullAgeDF.count())
    println("Data:"+data.count())

    //Formula to predict survival based on age, sex and pclass
    val supervised = new RFormula().setFormula("survived ~ age + sex + pclass")
    val Array(train, test) = supervised.fit(data).transform(droppedNullAgeDF).randomSplit(Array(0.9, 0.1))
    train.show

    //2
    val dt = new DecisionTreeRegressor().setLabelCol("label").setFeaturesCol("features")
    val model = dt.fit(train)

    //predictions
    val predictions = model.transform(test)
    predictions.show

    //3
    val evaluator = new RegressionEvaluator().
      setLabelCol("label").
      setPredictionCol("prediction").
      setMetricName("rmse")

    val rmse = evaluator.evaluate(predictions)
    println("Root Mean Squared Error (RMSE) on test data = " + rmse)

    println("Learned regression tree model:\n" + model.toDebugString)

    //4
    val binarizer: Binarizer = new Binarizer()
      .setInputCol("prediction")
      .setOutputCol("binarized_prediction")
      .setThreshold(0.5)

    val predictionBinary = binarizer.transform(predictions)
    predictionBinary.show

    //5
    val wrongPredictions = predictionBinary.where(expr("label != binarized_prediction"))
    wrongPredictions.show

    //6
    val countErrors = wrongPredictions.groupBy("label").agg(count("prediction").alias("Errors"))
    countErrors.show

    //7
    val correctPredictions = predictionBinary.where(expr("label == binarized_prediction"))
    val countCorrectPredictions = correctPredictions.groupBy("label").agg(count("prediction").alias("Correct"))
    countCorrectPredictions.show

    //Finding TP, TN, FP, FN
    val truePositives = predictionBinary.where(expr("label == 1 AND prediction == 1")).count()
    println("TP: "+truePositives)

    val trueNegatives= predictionBinary.where(expr("label == 0 AND prediction == 0")).count()
    println("TN: "+trueNegatives)

    val falsePositive= predictionBinary.where(expr("label == 0 AND prediction == 1")).count()
    println("FP: "+falsePositive)

    val falseNegative= predictionBinary.where(expr("label == 1 AND prediction == 0")).count()
    println("FN: "+falseNegative)

    val totalCount= predictionBinary.count()
    println("total: "+totalCount)

    val percentFP:Double = falsePositive*100/totalCount
    val percentFN:Double = falseNegative*100/totalCount

    println("%FP=" + percentFP+"%")
    println("%FN=" + percentFN+"%")
  }//end
}
