package com.scala.spark

import org.apache.spark.sql.SparkSession

object SqlWrite extends App{
  //Spark Initialization
  val spark=SparkSession.builder().appName("SQl Write")
    .config("spark.master", "local")
    .getOrCreate()

  //Import the csv file from local filesystem
  val lifeInsuranceDF=spark.read.option("inferSchema","true")
    .option("header", "true")
    .option("sep", ",")
    .csv("src/main/resources/data/FL_insurance_sample.csv")

  lifeInsuranceDF.show(10)

  //Saving the imported data to MySQL Database
  lifeInsuranceDF.write
    .format("jdbc")
    .options(Map(
      "url"->"jdbc:mysql://localhost:3306",
      "user"->"root",
      "password"->"root123",
      "dbtable"->"ABC_Insurance.policies")
    ).save()

}
