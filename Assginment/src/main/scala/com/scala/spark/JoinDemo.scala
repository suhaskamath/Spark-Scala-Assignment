package com.scala.spark


import org.apache.spark.sql.SparkSession

object JoinDemo extends App {
  //Spark Initialization
  val spark=SparkSession.builder().appName("Join Demo")
    .config("spark.master", "local")
    .getOrCreate()
  val ordersDataFrame=spark.read.option("inferSchema","true")
    .option("header", "true")
    .option("sep", ",")
    .csv("src/main/resources/data/olist_order_data.csv")

  val sellerDataFrame=spark.read.option("inferSchema","true")
    .option("header", "true")
    .option("sep", ",")
    .csv("src/main/resources/data/olist_sellers_data.csv")

  //Join based on seller ID
  val joinCondition=ordersDataFrame.col("seller_id")===sellerDataFrame.col("seller_id")
  val joinedDataFrame=ordersDataFrame.join(sellerDataFrame,
    joinCondition,
    "inner").drop(ordersDataFrame.col("seller_id"))
  joinedDataFrame.show(20)
}
