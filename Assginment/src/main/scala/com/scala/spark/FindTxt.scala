package com.scala.spark

import java.io.File
import java.util.Date


object FindTxt extends App{
  def fileFilter(file: File):Boolean=
  {
try
  {
    val extension=file.toString.split("\\.").last
    val differenceTime=new Date().getTime- file.lastModified()

    if(file.isFile()&&extension.equals("txt")&&differenceTime> 7*24 * 60 * 60 * 1000)
      {
        return true
      }
    else
      return false}
    catch {
      case e: Exception => {
        println(e)
        return false
      }
    }
  }
def findText(pathString: String):List[File]={
  val directory=new File(pathString)
  try {
    if (directory.exists() && directory.isDirectory()) {
      return directory.listFiles.filter(x => fileFilter(x)).toList
    }
    return null
  }
  catch {
    case e:Exception=> {
      println(e)
      return null
    }
  }
}

  val ListOfFiles=findText("C:\\hadoop\\hadoop-3.2.1")

  println("The file with txt extensions with modified date older than 7 days are")
  ListOfFiles.foreach(x=>println(x.getName))

}
