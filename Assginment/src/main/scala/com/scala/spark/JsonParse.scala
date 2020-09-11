package com.scala.spark



import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object JsonParse extends App{
  //Spark Initialization
  val spark=SparkSession.builder().appName("Spark JSON Parse")
            .config("spark.master", "local")
            .getOrCreate()

//Importing the data frame
  val dataFrame=spark.read
                  .option("inferSchema","true")
                  .option("multiLine", "true")
                  .json("src/main/resources/data/sample.json")


//Generation of the exploded Data Frame
      val explodedDF=dataFrame.select(
        col("EVENTPAYLOADINJSON.EVENT_DETAILS.PROCESS_NAME").as("processName"),
        col("EVENTPAYLOADINJSON.EVENT_NAME").as("eventName"),
        explode(col("EVENTPAYLOADINJSON.EVENT_DETAILS.BATCH_DETAILS")).as("Batch_Details"))
        .select(col("processName"),col("eventName"),
          explode(col("Batch_Details.DATA_FILE_DETAILS.DATA_FILE_ATTRIBUTES")).as("File_Attributes"),
          col("Batch_Details.DATA_FILE_DETAILS.DATA_FILE_PATH")as("filePath"),
          col("Batch_Details.HOST_NAME").as("hostname"))

  //Printing the attributes of Exploded Data Frame
explodedDF.select(col("eventName"),col("processName"),col("hostname"),
            col("filePath"),
            col("File_Attributes.DATA_FILE_NAME").as("dataFileName"))
            .show()
}
