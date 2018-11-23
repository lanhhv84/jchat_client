package com.example.hvlpr.test3.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hvlpr.test3.R;

public class MessageView extends LinearLayout {


    @StyleableRes
    int index0 = 0;
    @StyleableRes
    int index1 = 1;
    @StyleableRes
    int index2 = 2;

    ImageView profilePictureView;
    TextView timeTextView;
    TextView contentTextView;

    public MessageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, null);
    }
    public MessageView(Context context, String time, String content) {
        super(context);
        init(context, null);
        this.timeTextView.setText(time);
        this.contentTextView.setText(content);
        setDirection(true);
    }
    public MessageView(Context context, AttributeSet attrs, String time, String content) {
        super(context, attrs);
        init(context, attrs);
        this.timeTextView.setText(time);
        this.contentTextView.setText(content);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.message_view, this);

        int[] sets = {R.attr.profilePictureView, R.attr.timeTextView, R.attr.contentTextView};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        typedArray.recycle();
        initComponents();
    }

    private void initComponents() {
        profilePictureView = findViewById(R.id.profilePictureView);

        timeTextView = findViewById(R.id.timeTextView);

        contentTextView = findViewById(R.id.contentTextView);
    }

    public MessageView(Context context, String time, String content, boolean user) {
        this(context, time, content);
        setDirection(user);
    }

    private void setDirection(boolean user) {
        if (user) {
            this.setLayoutDirection(LAYOUT_DIRECTION_RTL);
            this.setPadding(50, 0, 0, 0);
        }
        else {
            this.setLayoutDirection(LAYOUT_DIRECTION_LTR);
            this.setPadding(0, 0, 50, 0);
        }
    }




}

