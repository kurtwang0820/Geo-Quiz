package com.ziliang.GeoQuiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private TrueFalse[] mQuestionBank = new TrueFalse[]{new TrueFalse(R.string.question_africa, false), new TrueFalse(R.string.question_america, true), new TrueFalse(R.string.question_asia, true), new TrueFalse(R.string.question_mideast, false), new TrueFalse(R.string.question_ocean, true)};
    private int mCurrentIndex = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton=(ImageButton)findViewById(R.id.prev_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex--;
                if(mCurrentIndex<0){
                    mCurrentIndex=mQuestionBank.length-1;
                }
                updateQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
