package com.example.samuel.hug;

import android.app.ActionBar;
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
import java.util.ArrayList;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;
import com.parse.ParseQuery;
import com.parse.LogInCallback;

public class HugActivity extends ActionBarActivity {
    private List<User> users;
    private int userIndex = 0;
    private String email;
    private String pw;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hug);

        // Initialize Parse and enable local datastore
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "VItHboPGcuvLhM1fIGNfSYeAmpaI8gZ8kapZzA5e", "pysSJFYVoD9Nqze879M8RPNTj2808uhatinCINNM");
        ParseObject.registerSubclass(User.class);
        Log.i("Application", "Initialized!");

        EditText emailEditText = (EditText)findViewById(R.id.email_edittext);
        EditText pwEditText = (EditText)findViewById(R.id.pw_edittext);
        email = emailEditText.getText().toString();
        pw = pwEditText.getText().toString();

        login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("email", email);
                query.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> parseUsers, ParseException e) {
                        if (e == null) {
                            // The query was successful, log in
                            ParseUser.logInInBackground(email, pw, new LogInCallback() {
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null) {
                                        // Hooray! The user is logged in.
                                        Intent intent = new Intent(HugActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // Signup failed. Look at the ParseException to see what happened.
                                        Toast.makeText(HugActivity.this, R.string.signup_failed, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else {
                            // The E-mail doesn't exist, sign up
                            ParseUser user = new ParseUser();
                            user.setPassword(pw);
                            user.setEmail(email);

                            user.signUpInBackground(new SignUpCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        // Hooray! Let them use the app now.
                                        Intent intent = new Intent(HugActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // Sign up didn't succeed. Look at the ParseException
                                        // to figure out what went wrong
                                    }
                                }
                            });
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
