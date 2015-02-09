package com.ziliang.GeoQuiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kurt on 2/8/2015.
 */
public class CheatActivity extends Activity {
    private static final String CHEAT_STATUS = "cheated";
    public static final String EXTRA_ANSWER_IS_TRUE = "com.ziliang.GeoQuiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.ziliang.GeoQuiz.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private TextView apiLevel;
    private Button mAnswerButton;
    private boolean answerShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setAnswerShownResult(false);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        apiLevel=(TextView)findViewById(R.id.api_level);
        int apiLevelInt=Build.VERSION.SDK_INT;
        apiLevel.setText("API Level: "+apiLevelInt);
        mAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        mAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
                answerShown = true;
            }
        });
        if (savedInstanceState != null) {
            answerShown = savedInstanceState.getBoolean(CHEAT_STATUS);
            setAnswerShownResult(answerShown);
        }
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(CHEAT_STATUS, answerShown);
    }
}
