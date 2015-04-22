package com.ziliang.GeoQuiz;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kurt on 4/21/2015.
 */
public class NewQuizFragment extends Fragment {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private TextView apiLevel;
    private TrueFalse[] mQuestionBank = new TrueFalse[]{new TrueFalse(R.string.question_africa, false), new TrueFalse(R.string.question_america, true), new TrueFalse(R.string.question_asia, true), new TrueFalse(R.string.question_mideast, false), new TrueFalse(R.string.question_ocean, true)};
    private boolean[] cheatQuestion=new boolean[mQuestionBank.length];
    private int mCurrentIndex = 0;
    //    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String CHEAT_STATUS="cheated";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main,container,false);
        mFalseButton = (Button) v.findViewById(R.id.false_button);
        mTrueButton = (Button) v.findViewById(R.id.true_button);
        mQuestionTextView = (TextView) v.findViewById(R.id.question_text_view);
        apiLevel=(TextView)v.findViewById(R.id.api_level);
        int apiLevelInt= Build.VERSION.SDK_INT;
        apiLevel.setText("API Level: "+apiLevelInt);
        mNextButton = (ImageButton) v.findViewById(R.id.next_button);
        mPrevButton = (ImageButton) v.findViewById(R.id.prev_button);
        mCheatButton = (Button) v.findViewById(R.id.cheat_button);
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
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex--;
                if (mCurrentIndex < 0) {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                updateQuestion();
            }
        });
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewCheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
                i.putExtra(NewCheatFragment.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(i, 0);
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            cheatQuestion=savedInstanceState.getBooleanArray(CHEAT_STATUS);
        }
        updateQuestion();
        return v;
    }

    //save current index and if the user has cheated when a device rotation happens
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
//        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBooleanArray(CHEAT_STATUS, cheatQuestion);
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
        int messageResId = 0;
        if (cheatQuestion[mCurrentIndex]) {
            messageResId = R.string.judgement_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(getActivity(), messageResId, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        cheatQuestion[mCurrentIndex] |= data.getBooleanExtra(NewCheatFragment.EXTRA_ANSWER_SHOWN, false);
    }
}
