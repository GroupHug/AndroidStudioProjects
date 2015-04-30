package com.example.samuel.hug;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignupActivity extends ActionBarActivity {
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText pwEditText;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = (EditText)findViewById(R.id.sign_username);
        emailEditText = (EditText)findViewById(R.id.sign_email);
        pwEditText = (EditText)findViewById(R.id.sign_pw);

        signup = (Button)findViewById(R.id.signup_button);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(usernameEditText.getText().toString());
                user.setPassword(pwEditText.getText().toString());
                user.setEmail(emailEditText.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Intent intent = new Intent(SignupActivity.this, HugActivity.class);
                            startActivity(intent);
                        } else {
                            // Sign up didn't succeed. Look at the ParseException to figure out what went wrong
                            Toast.makeText(SignupActivity.this, R.string.signup_failed, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signin, menu);
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
