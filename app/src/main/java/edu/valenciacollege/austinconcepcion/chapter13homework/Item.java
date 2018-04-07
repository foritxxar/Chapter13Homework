package edu.valenciacollege.austinconcepcion.chapter13homework;

/**
 * Created by Austin Concepcion on 4/7/2018.
 */

public class Item {
    private String title, link;

    public Item (String newTitle, String newLink){
        setTitle(newTitle);
        setLink(newLink);
    }

    public void setTitle (String newTitle) {title = newTitle;}
    public void setLink (String newLink) {link = newLink;}

    public String getTitle() {return title;}
    public String getLink() {return link;}
    public String toString() {return title+"; "+link;}

}
