package com.example.samuel.hug;

import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Switch;

import com.parse.Parse;
import com.parse.ParseObject;


public class HomeActivity extends ActionBarActivity {
    private TextView huggerName;
    private ImageView huggerPicture;
    private TextView hugs;
    private Button findButton;
    private Switch hugSwitch;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        huggerName = (TextView)findViewById(R.id.username_textview);
        huggerName.setOnClickListener(new View.OnClickListener() {
            // Set user name
            public void onClick(View v) {

            }
        });

        huggerPicture = (ImageView)findViewById(R.id.user_imageview);
        huggerPicture.setOnClickListener(new View.OnClickListener() {
            // Upload user photo
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        hugs = (TextView)findViewById(R.id.hugs_textview);
        hugs.setOnClickListener(new View.OnClickListener() {
            // See people you've hugged
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HugsActivity.class);
                startActivity(intent);
            }
        });

        findButton = (Button)findViewById(R.id.find_button);
        findButton.setOnClickListener(new View.OnClickListener() {
            // Search for huggers around
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FindActivity.class);
                startActivity(intent);
            }
        });

        hugSwitch = (Switch)findViewById(R.id.hug_switch);
        hugSwitch.setOnClickListener(new View.OnClickListener() {
            // Show "Hug me!" on search page
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
