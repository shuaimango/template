package com.example.mylibrary.util;

import android.text.SpannableString;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.blankj.utilcode.util.SpanUtils;
import com.example.mylibrary.R;

import java.util.List;

/**
 */
public class Util_layout {
    public static TextView setItemChoose(View container, int drawLeft, SpannableString key, SpannableString value, View.OnClickListener clickListener) {
        TextView tv_name = container.findViewById(R.id.tv_name);
        TextView tv_value = container.findViewById(R.id.tv_value);
        if (drawLeft > 0)
            tv_name.setCompoundDrawablesWithIntrinsicBounds(drawLeft, 0, 0, 0);
        tv_name.setText(key);
        if (value != null)
            tv_value.setText(value);
        if (clickListener != null)
            container.setOnClickListener(clickListener);
        return tv_value;
    }


    public static TextView setItemChoose(View container, SpannableString key, SpannableString value, View.OnClickListener clickListener) {
        return setItemChoose(container, 0, key, value, clickListener);
    }
    public static TextView setItemChoose(View container, SpannableString value, View.OnClickListener clickListener) {
        TextView tv_value = container.findViewById(R.id.tv_value);
        setItemChoose(container,0, Util_Spannable.setTextForeground(
                "职业", container.getResources().getColor(R.color.black1)),value,clickListener);
        return tv_value;
    }
}
