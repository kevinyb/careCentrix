package com.careCentrix;

import Utilities.ConfigurationReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _3_Reading_Log_File {


    public static void main(String[] args) {

        // creating List for storing all data from CSV file
        List<String> content = new ArrayList<>();
        content = readLogFile();
        List<String> group = new ArrayList<>();

        String allErrorsNumber = "200 201 202 204 300 400 401 403 404 405 500 501 502 503";
        String errorType ="";
        Boolean errorEntry = true;
        Scanner sc = new Scanner(System.in);

        while (errorEntry) {

            // asking the errorCode for checking
            System.out.println("Please write the error code  you want to check  <{Q} for exit>");
            System.out.println("Example : 200, 500, 400");
            errorType = sc.next();
            if (errorType.equalsIgnoreCase("Q")) {
                System.exit(0);
            }
            if (errorType.length()==3 && allErrorsNumber.contains(errorType)) {

                errorEntry= false;

            } else
            System.out.println("invalid entry please try again ");

        }

        System.out.println("Error  number you want to check is " + errorType);


        // I stored all the error codes in Configuraion.property file.
        // we can  store all the error codes into an MAP also.
        // using error codes as a key we can call the definition of the error codes from MAP
        String errorDefinition = ConfigurationReader.getProperty(errorType);

        System.out.println("Error Definition is ==> " + errorDefinition);
        System.out.println();
        System.out.println("----------");


        int occur = 0;

            // asking number of occurency the error codes
            System.out.println("What should be the minimum occurance < [0] for exit >");
            occur = sc.nextInt();
            if (occur == 0) {
                System.exit(0);
            }

        System.out.println("----------");




        int innerCount = 0; // this variable controls the changing
                            // of the error codes from the log files
        int errorCount = 0; // it counts the occurence  of the error codes
        int totalCount = 0; // it counts the total occurence

        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).contains(errorType)) {
                group.add(content.get(i));
                errorCount++;
                innerCount++;
                totalCount++;


            } else {innerCount = 0;}

            if (innerCount == 0 && errorCount >= occur ) {
                readErrorFromArrayList(group);

                System.out.println("--------------");
                System.out.println("errorCount = " + errorCount);
                System.out.println("--------------");
                group.clear();
                errorCount = 0;

            } else
                    if (innerCount == 0 && errorCount < occur ){

                         group.clear();
                         errorCount = 0;
                   }


        }

        // This block is controlling the accurence after the loop.
        // without adding this if block program is giving wrong result.

        if (group.size()>= occur){
            readErrorFromArrayList(group);

            System.out.println("--------------");
            System.out.println("errorCount = " + errorCount);
            System.out.println("--------------");
        }

            System.out.println("total number of occurrences of <" + errorType + " " +
                    errorDefinition + "> in the log file is ==> " + totalCount
                    + " times");

        }


        // This method is reading the log file and storing into a List
        public static List<String> readLogFile () {

            // first file's name which will be read,
            // and its path is coming from configuration.property file
            String firstFile = ConfigurationReader.getProperty("logFile");

            List<String> content = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(firstFile))) {
                String line = "";
                while ((line = br.readLine()) != null) {
                    content.add(line);
//                System.out.println(line);
                }

            } catch (Exception e) {
                System.out.println
                        ("File could not read and written please check the path");
            }
            return content;
        }


        // This Method is reading the records from List
        // and printing on the screen if the
        // the conditions match with the requirements
        public static void readErrorFromArrayList(List<String> group) {
            for (String error:group) {
                System.out.println(error);

            }

        }


    }





