package com.example.samuel.hug;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.LogInCallback;

public class HugActivity extends ActionBarActivity {
    private List<User> users;
    private int userIndex = 0;
    private String email;
    private String pw;
    private Button login;
    private Button signup;
    private EditText emailEditText;
    private EditText pwEditText;
    private boolean initialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hug);

        if (!initialized) {
            // Initialize Parse and enable local datastore
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "VItHboPGcuvLhM1fIGNfSYeAmpaI8gZ8kapZzA5e", "pysSJFYVoD9Nqze879M8RPNTj2808uhatinCINNM");
            ParseObject.registerSubclass(User.class);
            Log.i("Application", "Initialized!");
            initialized = true;
        }

        emailEditText = (EditText)findViewById(R.id.email_edittext);
        pwEditText = (EditText)findViewById(R.id.pw_edittext);
        email = emailEditText.getText().toString();
        pw = pwEditText.getText().toString();

        signup = (Button)findViewById(R.id.signup_button);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HugActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(email, pw, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Intent intent = new Intent(HugActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            Toast.makeText(HugActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hug, menu);
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
