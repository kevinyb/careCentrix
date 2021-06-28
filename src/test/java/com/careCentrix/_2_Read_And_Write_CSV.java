package com.careCentrix;

import Utilities.ConfigurationReader;
import com.opencsv.CSVWriter;

import static Utilities.Methods.*;



// In this Class I  have  called the methods from method class where I store
// this file under Utility package. Just like POM  I  called only related method
// to keep my class more organised.


public class _2_Read_And_Write_CSV {


   // CSV_FILE_PATH variable's value is coming from Configuration.properties file
   // this variable is holding the name of the  copied file's name
   // and its path
   private static final
    String CSV_FILE_PATH = ConfigurationReader.getProperty("secondFile");

    public static void main(String[] args) {

        //CreateCSV method is accepting 1 argument
        // that argument is specifying what will be the CSV file's name
        // and its path
        CSVWriter writer = CreateCSV(CSV_FILE_PATH);



        // readData_WriteInSecondCSVFile method is accepting 1 argument
        // this argument is holding the name and the path of the copy file.
        readData_WriteInSecondCSVFile(writer);


        // Message method is accepting 1 argument
        // this argument is holding the message text
        String messageBody =" file  copied !!!! ";
        message(messageBody);




    }

}