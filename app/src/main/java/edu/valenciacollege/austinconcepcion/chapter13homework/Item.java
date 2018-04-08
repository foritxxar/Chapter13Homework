package edu.valenciacollege.austinconcepcion.chapter13homework;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Austin Concepcion on 4/7/2018.
 */

public class Item {
    private String title, link, date;
    long days;

    public Item (String newTitle, String newLink, String newDate){
        setTitle(newTitle);
        setLink(newLink);
        setDate(newDate);
    }

    public void setTitle (String newTitle) {title = newTitle;}
    public void setLink (String newLink) {link = newLink;}
    public void setDate (String newDate) {date = newDate;}

    public String getTitle() {return title;}
    public String getLink() {return link;}
    public String getDate() {
        RSSDateParser(date);
        return date;
    }

    public String toString() {return title+"; "+link;}



    public void RSSDateParser(String dateString){
        DateFormat inputFormatter, outputFormatter;

        inputFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzzz");
        outputFormatter = new SimpleDateFormat("MMM dd yyyy");
        try {
            Date inputDate = inputFormatter.parse(date);
            Date todayDate = new Date();
            date = outputFormatter.format(inputDate);
            long diff = inputDate.getTime() - todayDate.getTime();
            days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) * -1;
        }catch(ParseException pe){}
    }
}
