package com.pedoran.pwpbsesi11.StopWatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pedoran.pwpbsesi11.MainActivity;
import com.pedoran.pwpbsesi11.R;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SutoppuWatch extends AppCompatActivity {
    TextView tv_display;
    Button start,pause,reset,lap;
    long miliSecondTime,StartTime,TimeBuff,UpdateTime = 0L;
    Handler handler;
    int seconds,minutes,miliseconds,hours;
    ListView listView;
    String[] listElement = new String[]{};
    List<String> listElementsArrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sutoppu_watch);

        tv_display = (TextView) findViewById(R.id.textView);
        start = (Button) findViewById(R.id.button);
        pause = (Button) findViewById(R.id.button2);
        reset = (Button) findViewById(R.id.button3);
        lap = (Button) findViewById(R.id.button4);
        listView = (ListView) findViewById(R.id.listview1);

        handler = new Handler();
        listElementsArrayList = new ArrayList<String>(Arrays.asList(listElement));

        adapter = new ArrayAdapter<String>(SutoppuWatch.this,android.R.layout.simple_list_item_1,listElementsArrayList);

        listView.setAdapter(adapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                reset.setEnabled(false);
                start.setEnabled(false);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuff += miliSecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
                start.setEnabled(true);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miliSecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                seconds = 0;
                minutes = 0;
                miliseconds = 0;

                tv_display.setText("00:00:00");

                listElementsArrayList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listElementsArrayList.add(tv_display.getText().toString());

                adapter.notifyDataSetChanged();
            }
        });

    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            miliSecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + miliSecondTime;

            seconds = (int) (UpdateTime/1000);
            minutes = seconds / 60;
            hours = seconds % 60;
            miliseconds = (int) (UpdateTime%1000);

            tv_display.setText(""+minutes+":"+ String.format("%02d",seconds)+":"+String.format("%03d",miliseconds));
            handler.postDelayed(this,0);
        }
    };
}
