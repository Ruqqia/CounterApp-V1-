package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView counter;
    Button increment, reset, close;

    String count = "LAST_COUNT";
    String key = "TV_VALUE";

    int textCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            textCounter = 0;
        }

        reset = findViewById(R.id.reset);
        increment = findViewById(R.id.increment);
        close = findViewById(R.id.close);
        counter = findViewById(R.id.counter_add);
        counter.setText("0");

        reset.setOnClickListener(this);
        increment.setOnClickListener(this);
        close.setOnClickListener(this);

        readData();
    }

    public void readData() {
        SharedPreferences prefs = getSharedPreferences(key, MODE_PRIVATE);
        textCounter = prefs.getInt(count, Integer.parseInt(counter.getText().toString()));
        counter.setText("" + textCounter);
    }

    public void saveData() {
        SharedPreferences prefs = getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(count, textCounter);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        saveData();
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.increment:

                textCounter++;
                counter.setText(String.valueOf(textCounter));
                break;

            case R.id.reset:

                counter.setText("0");
                textCounter = 0;
                break;

            case R.id.close:

                onBackPressed();
                break;


        }
    }
}
