package com.scala.spark

import java.io.File

import sys.process._

object GPGEncryption extends App {
  val filePath=
  "C:\\Users\\Suhas\\Desktop\\final\\Spark-Assignment\\Assginment\\src\\main\\resources\\data"
  def encryption (fileName:String){
  val file=new File(filePath+"\\"+fileName)
  val isFile=file.isFile()
  if(isFile) {
//Encryption of file
    val status = ("cmd :/C" + "gpg -c " + filePath + "\\" + fileName).!
  if(status==0)
    println("Successful encrypted the "+fileName+ " file using GPG Encryption")
   else {
    println("Failure to perform GPG Encryption")
  }
  }
  else {
    println("Input is not a file")
  }
  }
    //Deletion of file existing file
    val status = ("cmd :/C" + " del " +filePath + "\\sample.txt.gpg"  ).!
  if(status==0)
    {
      println("Execution of deletion operation of Pre-existing file completed")
    }
  //Encryption of the file
  encryption("sample.txt");

}
