package Utilities;

import com.opencsv.CSVWriter;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Methods {


    // This method is generating random date
    // it is accepting 2 parameters start and end years
    // and it returns LocalDate
    public static LocalDate createRandomDate(int startYear, int endYear) {

        int year = createRandomIntBetween(startYear, endYear);
        int month = createRandomIntBetween(1, 12);
        int day = createRandomIntBetween(1, 30);
        if (month == 2 && day >= 29) {
            day = 28;
        }

        return LocalDate.of(year, month, day);
    }


    // This method is generating random numbers between given 2 arguments
    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }


    //// This method is creating CSV file and returning CSVWriter object
    // it is accepting 1 argument which is holding
    //  the name of the second file and its path

    public static CSVWriter CreateCSV(String CSV_FILE_PATH) {

        File file = new File(CSV_FILE_PATH);

        FileWriter outputfile = null;
        try {
            outputfile = new FileWriter(file);
        } catch (Exception e) {
            e.printStackTrace();
        }


        CSVWriter writer = new CSVWriter(outputfile, ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);

        return writer;
    }


    // this method is accepting 1 argument which is called writer
    //this argument is holding 2nd filename and its path.
    // method is opening "firstFile" and getting from
    // Configuration.properties file and writing into the 2nd file.
    public static void readData_WriteInSecondCSVFile(CSVWriter writer) {

        // first file's name which will be read,
        // and its path is coming from configuration.property file
        String firstFile = ConfigurationReader.getProperty("firstFile");

        List<String[]> content = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(firstFile))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
                System.out.println(line);
            }

            writer.writeAll(content);
            writer.close();

        } catch (Exception e) {
            System.out.println
                    ("File could not read and written please check the path");
        }


    }


    // This method is giving alert and accepting 1 argument
    // argument is holding the message text.
    public static void message(String messageBody) {
        JOptionPane.showMessageDialog(null, messageBody);

    }




}

