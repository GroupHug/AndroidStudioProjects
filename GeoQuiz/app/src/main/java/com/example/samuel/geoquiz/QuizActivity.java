package com.example.samuel.geoquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*; // Import all widgets (buttons, toasts, etc)


public class QuizActivity extends ActionBarActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button nextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_owls, true),
            new TrueFalse(R.string.question_owlcyone, true),
            new TrueFalse(R.string.question_ground, false),
            new TrueFalse(R.string.question_sleep, false),
            new TrueFalse(R.string.question_cute, true),
            new TrueFalse(R.string.question_wise, true),
            new TrueFalse(R.string.question_infinity, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); // Resource ID

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        nextButton = (Button)findViewById(R.id.next_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            // Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(QuizActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
