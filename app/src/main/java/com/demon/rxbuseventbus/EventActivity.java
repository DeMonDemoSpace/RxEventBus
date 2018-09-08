package com.demon.rxbuseventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventActivity extends AppCompatActivity {

    @BindView(R.id.rx)
    Button rx;
    @BindView(R.id.java)
    Button java;
    @BindView(R.id.android)
    Button android;
    @BindView(R.id.event_layout)
    FrameLayout eventLayout;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.event_layout, new EventFragment());
        transaction.commit();
    }

    @OnClick({R.id.rx, R.id.java, R.id.android})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rx:
                startActivity(new Intent(this, RxActivity.class));
                break;
            case R.id.java:
                EventBus.getDefault().post(new MsgEvent("Java"));
                break;
            case R.id.android:
                EventBus.getDefault().post(new MsgEvent("Android"));
                break;
        }
    }

}
