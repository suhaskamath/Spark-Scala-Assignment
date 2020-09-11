package com.scala.spark

import org.apache.spark.sql.{SaveMode, SparkSession}

object ReadWriteHadoop extends App{
  //Spark Initialization
val spark=SparkSession.builder().appName("Read Hadoop")
              .config("spark.master","local").getOrCreate()
  //Import the file from Hadoop
val lifeInsuranceDataFrame=spark.read.option("header","true")
  .csv("hdfs://localhost:9000/FL_insurance_sample.csv")
  //Printing the output of the imported data
  lifeInsuranceDataFrame.show(20)

  //Writing to local filesystem
  lifeInsuranceDataFrame.write
    .format("csv")
    .mode(SaveMode.Overwrite)
    .option("header", "true")
    .save("src/main/resources/data/out.csv")
}
