package edu.valenciacollege.austinconcepcion.chapter13homework;

import android.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Austin Concepcion on 4/7/2018.
 */

public class SAXHandler extends DefaultHandler {
    private boolean validText;
    private String element="";
    private Item currentItem;
    private ArrayList<Item> items;

    public SAXHandler(){
        validText = false;
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> getItems(){return items;}

    public void startElement (String uri, String localName, String startElement,
                              Attributes attributes) throws SAXException{
        validText = true;
        element = startElement;
        if(startElement.equals("item"))
            currentItem = new Item("", "");
        //Log.w("MainActivity", "Inside startElement, startElement =" +startElement);
    }

    public void endElement(String uri, String localName, String endElement) throws SAXException{
        validText = false;
        if(endElement.equals("item"))
            items.add(currentItem);
        //Log.w("MainActivity", "Inside endElement, endElement ="+endElement);
    }

    public void characters(char [] ch, int start, int length) throws SAXException{

        if(currentItem != null && element.equals("title") && validText)
            currentItem.setTitle(new String(ch, start, length));
        else if(currentItem!=null && element.equals("link") && validText)
            currentItem.setLink(new String(ch, start, length));
        //String text = new String(ch, start, length);
        //Log.w("Main Activity", "Inside characters, text = "+text);
    }
}
