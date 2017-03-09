package com.example.lijing.gpionew;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends Activity implements
         View.OnClickListener {
    public static final String TAG = "Wei-DIOTest";

    private static int mConfiguredPins[] = {0, 0, 0, 0};
    private static final int TEST_DIO_PINS = mConfiguredPins.length;


    private Gpio mGPIOMgr;

    private static final int MAX_DIO_PINS = 4;

    private ScrollView mScrollView;
    private TextView mMessage;



    TimerCountdownView timercountdown;


    Button mbtnLO0;
    Button mbtnLO1;
    Button mbtnLO2;
    Button mbtnLO3;
    Button mbtnHI0;
    Button mbtnHI1;
    Button mbtnHI2;
    Button mbtnHI3;
    TextView timer;

    TextView timerHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mbtnLO0 = (Button) (findViewById(R.id.btnLO0));
        mbtnLO0.setOnClickListener(this);
        mbtnLO1 = (Button) (findViewById(R.id.btnLO1));
        mbtnLO1.setOnClickListener(this);
        mbtnLO2 = (Button) (findViewById(R.id.btnLO2));
        mbtnLO2.setOnClickListener(this);
        mbtnLO3 = (Button) (findViewById(R.id.btnLO3));
        mbtnLO3.setOnClickListener(this);
        mbtnHI0 = (Button) (findViewById(R.id.btnHI0));
        mbtnHI0.setOnClickListener(this);
        mbtnHI1 = (Button) (findViewById(R.id.btnHI1));
        mbtnHI1.setOnClickListener(this);
        mbtnHI2 = (Button) (findViewById(R.id.btnHI2));
        mbtnHI2.setOnClickListener(this);
        mbtnHI3 = (Button) (findViewById(R.id.btnHI3));
        mbtnHI3.setOnClickListener(this);
        timercountdown= (TimerCountdownView) findViewById(R.id.timercountdown);
        timer= (TextView) findViewById(R.id.timer);
        timerHint= (TextView) findViewById(R.id.timer_hint);
        timercountdown.setMaxTime(4);//设置时间
        timercountdown.updateView();
        timercountdown.addCountdownTimerListener(litener);
        try {
            mGPIOMgr = new Gpio(getApplicationContext());
        } catch (Exception e) {
            Log.d(TAG, "Exception : " + e.toString());
        }
        initValue();
        mGPIOMgr.configureDIO(0, false);
        mGPIOMgr.configureDIO(1, false);
        setDigitalOutputStatus(0, 1);
        setDigitalOutputStatus(1, 1);
    }

    private void initValue() {
        for (int i = 0; i < mConfiguredPins.length; i++) {
            mGPIOMgr.configureDIO(i, mConfiguredPins[i] == 0);
        }
    }
    TimerCountdownView.CountdownTimerListener litener = new TimerCountdownView.CountdownTimerListener() {

        @Override
        public void onCountDown(String time) {
            timer.setText(time);
        }

        @Override
        public void onTimeArrive(boolean isArrive) {
            if (isArrive) {
                timercountdown.destroy();
                setDigitalOutputStatus(0, 0);
                setDigitalOutputStatus(1, 0);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");



    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }


    private static final int MINUTES = 60000;


    public void setDigitalOutputStatus(int no, int value) {
        mGPIOMgr.setDO(no, value);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btnLO0:
                setDigitalOutputStatus(0, 0);


                break;
            case R.id.btnHI0:
                setDigitalOutputStatus(0, 1);


                break;
            case R.id.btnLO1:
                setDigitalOutputStatus(1, 0);


                break;
            case R.id.btnHI1:
                setDigitalOutputStatus(1, 1);


                break;
            case R.id.btnLO2:
                setDigitalOutputStatus(2, 0);


                break;
            case R.id.btnHI2:
                setDigitalOutputStatus(2, 1);


                break;
            case R.id.btnLO3:
                setDigitalOutputStatus(3, 0);


                break;
            case R.id.btnHI3:
                setDigitalOutputStatus(3, 1);


                break;

        }
    }


}
