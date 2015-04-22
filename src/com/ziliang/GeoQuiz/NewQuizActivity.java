package com.ziliang.GeoQuiz;

import android.app.Fragment;

/**
 * Created by Kurt on 4/21/2015.
 */
public class NewQuizActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NewQuizFragment();
    }
}
