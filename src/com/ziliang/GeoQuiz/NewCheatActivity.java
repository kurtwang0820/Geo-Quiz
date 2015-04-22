package com.ziliang.GeoQuiz;

import android.app.Fragment;

/**
 * Created by Kurt on 4/21/2015.
 */
public class NewCheatActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        boolean answerIsTrue=getIntent().getBooleanExtra(NewCheatFragment.EXTRA_ANSWER_IS_TRUE,false);
        return NewCheatFragment.newInstance(answerIsTrue);
    }
}
