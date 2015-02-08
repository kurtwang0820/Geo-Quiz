package com.ziliang.GeoQuiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kurt on 2/8/2015.
 */
public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE="com.ziliang.GeoQuiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN="com.ziliang.GeoQuiz.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mAnswerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setAnswerShownResult(false);
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView=(TextView)findViewById(R.id.answerTextView);
        mAnswerButton=(Button)findViewById(R.id.showAnswerButton);
        mAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
}
