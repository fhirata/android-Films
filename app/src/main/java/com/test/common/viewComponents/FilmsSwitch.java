package com.test.common.viewComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.test.filmlocations.R;

/**
 * Custom Switch with two buttons for toggle
 */

public class FilmsSwitch extends LinearLayout {
    private Context mContext;
    private String mTextOne;
    private String mTextTwo;
    private Button mOptionOne;
    private Button mOptionTwo;
    private SwitchListener mListener;

    public FilmsSwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        setWeightSum(1f);
        mContext = context;

        initializeAttributes(attrs);
        initializeButtons(attrs);
    }

    public interface SwitchListener {
        void onSwitchToggled(View view, boolean optionOneSelected);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.equals(mOptionOne)) {
                mListener.onSwitchToggled(v, true);
                mOptionOne.setSelected(true);
                mOptionTwo.setSelected(false);
            } else {
                mListener.onSwitchToggled(v, false);
                mOptionOne.setSelected(false);
                mOptionTwo.setSelected(true);
            }
        }
    };

    private void initializeAttributes(AttributeSet attrs) {
        TypedArray a = mContext.getTheme().obtainStyledAttributes(attrs,
                R.styleable.FilmsSwitch, 0, 0);

        try {
            mTextOne = a.getString(R.styleable.FilmsSwitch_optionOneText);
            mTextTwo = a.getString(R.styleable.FilmsSwitch_optionTwoText);
        } finally {
            a.recycle();
        }
    }

    private void initializeButtons(AttributeSet attrs) {
        mOptionOne = new Button(mContext, attrs);
        mOptionTwo = new Button(mContext, attrs);

        configureButton(mOptionOne, mTextOne);
        configureButton(mOptionTwo, mTextTwo);
        mOptionOne.setBackground(ResourcesCompat.getDrawable(getResources(),
                R.drawable.switch_button_selector_right, null));
        mOptionTwo.setBackground(ResourcesCompat.getDrawable(getResources(),
                R.drawable.switch_button_selector_left, null));

        setSwitchActive(false);

        this.addView(mOptionOne);
        this.addView(mOptionTwo);
    }

    private void configureButton(Button button, String buttonText) {
        int height = (int) getResources().getDimension(R.dimen.switch_button_height);
        float fontSize = getResources().getDimension(R.dimen.font_size_extra_small);
        // Use a width of 0 and a weight of 0.5 for each of the (two) buttons to ensure they equally
        // share the layout width, which has a weight sum of 1
        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(0,height,0.5f);

        button.setText(buttonText);
        button.setOnClickListener(mOnClickListener);
        button.setLayoutParams(buttonLayoutParams);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
        button.setTextColor(ResourcesCompat.getColorStateList(getResources(),
                R.color.switch_button_text_selector, null));
    }

    public void setSwitchActive(boolean isActive) {
        if (isActive) {
            mOptionOne.performClick();

            mOptionOne.setEnabled(true);
            mOptionTwo.setEnabled(true);
        } else {
            mOptionOne.setSelected(false);
            mOptionTwo.setSelected(false);
        }
    }

    public void setOptionOneSelected() {
        mOptionOne.performClick();
    }

    public void setOptionTwoSelected() {
        mOptionTwo.performClick();
    }

    public boolean isOptionOneSelected() {
        return mOptionOne.isSelected();
    }
}
