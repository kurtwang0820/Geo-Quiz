package com.ziliang.GeoQuiz;

/**
 * Created by Kurt on 2/8/2015.
 */
public class TrueFalse {
    private int mQuestion;
    private boolean mTrueQuestion;
    public TrueFalse(int question,boolean trueQuestion){
        mQuestion=question;
        mTrueQuestion=trueQuestion;
    }

    public void setmTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }

    public void setmQuestion(int mQuestion) {

        this.mQuestion = mQuestion;
    }

    public boolean ismTrueQuestion() {

        return mTrueQuestion;
    }

    public int getmQuestion() {

        return mQuestion;
    }
}
