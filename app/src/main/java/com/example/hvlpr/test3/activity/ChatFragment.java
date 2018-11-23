package com.example.hvlpr.test3.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.hvlpr.test3.R;
import com.example.hvlpr.test3.view.MessageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ChatFragment extends Fragment {

    private ImageButton sendImageButton;
    private LinearLayout chatContentLayout;
    private EditText messageContentEditText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.activity_chat, container, false);
        init(view);
        return view;
    }


    private void init(View view) {
        sendImageButton = view.findViewById(R.id.sendMessageButton);
        chatContentLayout = view.findViewById(R.id.chatContentLayout);
        messageContentEditText = view.findViewById(R.id.messageContentEditText);
        sendImageButton.setOnClickListener(this::onSendMessageButtonClicked);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onSendMessageButtonClicked(View view) {
        Log.d("MyLog", "Hay lam!!!");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date currentTime = Calendar.getInstance().getTime();
        String timeStr = df.format(currentTime);
        String content = messageContentEditText.getText().toString();
        Random random = new Random();

        MessageView messageView = new MessageView(getContext(), timeStr, content,random.nextBoolean());
        chatContentLayout.addView(messageView);
    }

}
