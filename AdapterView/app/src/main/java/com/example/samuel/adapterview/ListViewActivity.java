package com.example.samuel.adapterview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;
import java.util.ArrayList;
import android.widget.ListView;


public class ListViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // Contacts list
        final List<Contact> contacts = new ArrayList<Contact>() {{
            add(new Contact("John", "http://icdn.pro/images/en/g/r/green-white-male-user-icone-7708-48.png" ));
            add(new Contact("Tom", "http://www.minimania.com/images/Icon_User-48-shdw.png"));
            add(new Contact("Alice", "https://wiki.jenkins-ci.org/images/icons/profilepics/user1.gif"));
            add(new Contact("Alan", "http://icdn.pro/images/en/g/r/green-white-male-user-icone-7708-48.png" ));
            add(new Contact("Ben", "http://www.minimania.com/images/Icon_User-48-shdw.png"));
            add(new Contact("Adam", "https://wiki.jenkins-ci.org/images/icons/profilepics/user1.gif"));
            add(new Contact("Joe", "http://icdn.pro/images/en/g/r/green-white-male-user-icone-7708-48.png" ));
            add(new Contact("Paul", "http://www.minimania.com/images/Icon_User-48-shdw.png"));
            add(new Contact("Peter", "https://wiki.jenkins-ci.org/images/icons/profilepics/user1.gif"));
        }};

        ListView listView = (ListView) findViewById(R.id.listView);
        ContactArrayAdapter listAdapter = new ContactArrayAdapter(this, contacts);
        listView.setAdapter(listAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
