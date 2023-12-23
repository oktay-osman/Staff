package com.example.demo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateParser {
    public static LocalDate parseDate(String inputDate) {
        LocalDate parsedDate = null;
        List<String> formats = Arrays.asList(
                "yyyy-MM-dd",
                "dd-MM-yyyy",
                "MM-dd-yyyy",
                "dd-MMM-yyyy",
                "MMM-dd-yyyy",
                "MM/dd/yyyy",
                "dd/MM/yyyy",
                "yyyy/MM/dd"
        );

        for (String format : formats) {
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                parsedDate = LocalDate.parse(inputDate, formatter);
                break;
            } catch (DateTimeParseException ignored) {
                // Format didn't match continue to the next format
            }
        }

        if(parsedDate == null) {
            throw new IllegalArgumentException("Input date doesn't match any supported format");
        }

        return parsedDate;
    }
}
