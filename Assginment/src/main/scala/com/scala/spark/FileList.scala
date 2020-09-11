package com.scala.spark


import java.io.File
import java.text.SimpleDateFormat


//class that returns the files in the current directory and they last modified time
class fileFinder(path:String){

  def list():Map[String,String]={
  val directory=new File(path)
    if(directory.isDirectory)
      {
        //Generate only a list of files
       val listOfFiles= directory.listFiles.filter(_.isFile).toList
        //Map to store the date and file names
        var MapOfFiles:Map[String,String] = Map()
        for(file<-listOfFiles) {
          //Generator of Human Readable Timestamp
          val dateFormat:SimpleDateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm")
          val modifiedDate:String = dateFormat.format(file.lastModified)
          MapOfFiles += (file.getName -> modifiedDate)
        }
        return  MapOfFiles
      }
    return null
  }
}
object fileLister extends App {

  val path = "C:\\hadoop\\hadoop-3.2.1"
  val finder = new fileFinder(path)
  val MapOfFiles: Map[String, String] = finder.list()
//Checking if the list is empty
  if (MapOfFiles != null) {
      println("The files in the directory path " + path + " are:")
      println("FileName\t||\tModified Date")
      for ((fileName, modifiedName) <- MapOfFiles) {
        println(fileName + "\t||\t" + modifiedName)
      }
  }
  else{
    println("The files in the directory path " + path + " has no files present")
  }
}
