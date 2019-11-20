package com.example.mylibrary.util;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.mylibrary.R;


public class VerifyCodeTimer extends CountDownTimer {

    private final TextView mTextView;

    // private SoftReference<TextView> mTextView;

    private WordingCallback wordingCallback;

    public VerifyCodeTimer(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        mTextView = textView;
        // mTextView = new SoftReference<TextView>(textView);
    }

    // public void setmTextView(SoftReference<TextView> mTextView) {
    // this.mTextView = mTextView;
    // }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false);
        String showText = getWordingCallback().getCountdownWording(millisUntilFinished);
        mTextView.setText(showText);
    }

    @Override
    public void onFinish() {
        mTextView.setText(getWordingCallback().getAfterCountdownWoring());
        mTextView.setClickable(true);
    }

    public void setWordingCallback(WordingCallback wordingCallback) {
        this.wordingCallback = wordingCallback;
    }

    public WordingCallback getWordingCallback() {
        if (wordingCallback == null) {
            wordingCallback = new WordingCallback() {
                @Override
                public String getCountdownWording(long millisUntilFinished) {
                    return mTextView.getContext().getString(R.string.verifynumTime, Long.toString(millisUntilFinished / 1000));
                }

                @Override
                public String getAfterCountdownWoring() {
                    return mTextView.getResources().getString(R.string.obtain_verif_code);
                }
            };
        }
        return wordingCallback;
    }

    public  interface WordingCallback {
        String getCountdownWording(long millisUntilFinished);

        String getAfterCountdownWoring();
    }
}