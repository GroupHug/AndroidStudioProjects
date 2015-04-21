package com.example.samuel.layout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TinderActivity extends ActionBarActivity {
    private List<User> users;
    private int userIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);

        // Initialize users
        users = new ArrayList<User>();
        users.add(new User("Alex", 23, R.drawable.s_1, 10, 4, 7));
        users.add(new User("Amy", 20, R.drawable.s_2, 12, 6 ,17));
        users.add(new User("Sarah", 24, R.drawable.s_3, 20, 6 ,27));
        users.add(new User("Zach", 26, R.drawable.s_4, 30, 16 ,7));

        ImageButton button = (ImageButton)findViewById(R.id.likeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViews();
            }
        });

    }

    private void updateViews() {
        userIndex++;
        if (userIndex < users.size()) {
            User user = users.get(userIndex);

            ImageView imageView = (ImageView)findViewById(R.id.photoView);
            TextView nameAgeTextView = (TextView) findViewById(R.id.nameAgeText);
            TextView photosTextView = (TextView) findViewById(R.id.photosText);
            TextView messagesTextView = (TextView) findViewById(R.id.messagesText);
            TextView friendsTextView = (TextView) findViewById(R.id.friendsText);

            nameAgeTextView.setText(user.getName() + "," + user.getAge());
            photosTextView.setText(user.getPhotos() + "");
            messagesTextView.setText(user.getMessages() + "");
            friendsTextView.setText(user.getFriends() + "");

            imageView.setImageResource(user.getPhotoRes());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tinder, menu);
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
