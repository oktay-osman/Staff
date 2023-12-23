package com.example.demo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static LocalDate parseDate(String inputDate) {
        LocalDate parsedDate = null;
        String[] formats = {
                "yyyy-MM-dd",
                "dd-MM-yyyy",
                "MM-dd-yyyy",
                "dd-MMM-yyyy",
                "MMM-dd-yyyy",
                "MM/dd/yyyy",
                "dd/MM/yyyy",
                "yyyy/MM/dd"
        };

        for (String format : formats) {
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                parsedDate = LocalDate.parse(inputDate, formatter);
                break;
            } catch (Exception e) {
                //TODO add format not supported exception
                e.getMessage();
            }

        }
        return parsedDate;
    }
}
