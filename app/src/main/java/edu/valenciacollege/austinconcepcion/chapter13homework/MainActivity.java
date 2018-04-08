package edu.valenciacollege.austinconcepcion.chapter13homework;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    private final String URL = "https://waitbutwhy.com/feed";
    private ListView feedList;
    private EditText filterBox;
    private ArrayList<Item> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        feedList = (ListView) findViewById(R.id.feed_list);
        filterBox = (EditText) findViewById(R.id.sort_input_text);
        ParseTask task = new ParseTask(this);
        task.execute(URL);

//        try{
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser saxParser = factory.newSAXParser();
//            SAXHandler handler = new SAXHandler();
//            InputStream is = getResources().openRawResource(R.raw.test);
//            saxParser.parse( is, handler);
//
//            ArrayList<Item> items = handler.getItems();
//            for (Item item : items)
//                Log.w("MainActivity", item.toString());
//        }catch (Exception e){
//            Log.w("MainActivity", "e= "+e.toString());
//        }
    }

    public void displayList(ArrayList<Item> items){
        listItems = items;
        if (items != null) {
            ArrayList<String> titlesWithDates = new ArrayList<String>();
            for(Item item : items) {
                String listItem = item.getTitle() +"\nPosted on: " + item.getDate();
                titlesWithDates.add(listItem);
                Log.w("MainActivity", item.toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, titlesWithDates);
            feedList.setAdapter(adapter);
            ListItemHandler lih = new ListItemHandler();
            feedList.setOnItemClickListener(lih);
        } else Toast.makeText(this, "Sorry, we didn't find anything...", Toast.LENGTH_LONG).show();
    }

    public void refreshList(ArrayList<Item> items, long key){

        if (items != null) {
            ArrayList<String> titlesWithDates = new ArrayList<String>();
            for(Item item : items) {
                if(item.days <= key) {
                    String listItem = item.getTitle() + "\nPosted on: " + item.getDate();
                    titlesWithDates.add(listItem);
                    Log.w("MainActivity", item.toString());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, titlesWithDates);
            feedList.setAdapter(adapter);
            ListItemHandler lih = new ListItemHandler();
            feedList.setOnItemClickListener(lih);
        } else Toast.makeText(this, "Sorry, we didn't find anything...", Toast.LENGTH_LONG).show();
    }

    public void sortFilterList(View view){
        ArrayList<Item> thisList = listItems;
        long key = Long.parseLong(filterBox.getText().toString());
        Collections.sort(thisList, new CustomComparator());
        refreshList(thisList, key);
    }

    private class ListItemHandler implements AdapterView.OnItemClickListener{
        public void onItemClick(AdapterView<?> parent, View v, int position, long id){
            Item selectedItem = listItems.get(position);
            Uri uri = Uri.parse(selectedItem.getLink());
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);
        }
    }



    private class CustomComparator implements Comparator<Item>{
        @Override
        public int compare(Item item1, Item item2){
            if (item1.days > item2.days)
                return 1;
            else if(item1.days < item2.days)
                return -1;
            else
                return 0;
        }
    }

}

