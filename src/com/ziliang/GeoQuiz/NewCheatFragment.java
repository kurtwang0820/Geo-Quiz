package com.ziliang.GeoQuiz;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kurt on 4/21/2015.
 */
public class NewCheatFragment extends Fragment {
    private static final String CHEAT_STATUS = "cheated";
    public static final String EXTRA_ANSWER_IS_TRUE = "com.ziliang.GeoQuiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.ziliang.GeoQuiz.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private TextView apiLevel;
    private Button mAnswerButton;
    private boolean answerShown = false;

    public static NewCheatFragment newInstance(boolean answerIsTrue){
        Bundle args=new Bundle();
        args.putBoolean(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        NewCheatFragment cheatFragment=new NewCheatFragment();
        cheatFragment.setArguments(args);
        return cheatFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_cheat,container,false);
        setAnswerShownResult(false);
        mAnswerIsTrue = getArguments().getBoolean(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView) v.findViewById(R.id.answerTextView);
        apiLevel=(TextView)v.findViewById(R.id.api_level);
        int apiLevelInt= Build.VERSION.SDK_INT;
        apiLevel.setText("API Level: "+apiLevelInt);
        mAnswerButton = (Button) v.findViewById(R.id.showAnswerButton);
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
        return v;
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        getActivity().setResult(Activity.RESULT_OK, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(CHEAT_STATUS, answerShown);
    }
}
