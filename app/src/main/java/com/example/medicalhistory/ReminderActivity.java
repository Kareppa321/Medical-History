package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class ReminderActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private NumberPicker numberPicker;
    private Button setReminder;
    private int repeat;
    private int hour;
    private int minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        timePicker = findViewById(R.id.timePicker);
        numberPicker = findViewById(R.id.repeatNumberPicker);
        setReminder = findViewById(R.id.setReminderButton);
        repeat = 1;

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(31);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                repeat = numberPicker.getValue();
            }
        });

        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                Toast.makeText(ReminderActivity.this, hour + " " + minute + " " + repeat, Toast.LENGTH_SHORT).show();
            }
        });

    }
}