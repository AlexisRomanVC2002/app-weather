package com.alexisvc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateService {

    private static int hourInit;
    private Calendar date = Calendar.getInstance();
    private SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd k:mm");

    public DateService() {
        hourInit = date.get(Calendar.HOUR_OF_DAY);
    }

    public String getDateActually() {
        date = Calendar.getInstance();
        return formatterDate.format(date.getTime());
    }

    public boolean checkDates(String dateOne, String dateTwo) {

        try {
            Date dateTest = formatterDate.parse(dateOne);
            Date dateTest2 = formatterDate.parse(dateTwo);

            return dateTest.before(dateTest2);

        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return false;
    }

    public boolean changeHour() {
        int hourActually = date.get(Calendar.HOUR_OF_DAY);
        if (hourInit != hourActually) {
            hourInit = hourActually;
            return true;
        }
        return false;
    }

}
