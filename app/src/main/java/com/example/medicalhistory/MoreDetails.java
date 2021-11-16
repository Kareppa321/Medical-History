package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MoreDetails extends AppCompatActivity {
    private TextView dateTextView;
    private TextView diseaseTextView;
    private TextView doctorNameTextView;
    private TextView hospitalNameTextView;
    private TextView addressFieldTextView;
    private TextView medicineFieldTextView;
    private Button addReminderButton;
    private FloatingActionButton photoFAB;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        dateTextView = findViewById(R.id.dateTextView);
        diseaseTextView = findViewById(R.id.diseaseTextView);
        doctorNameTextView = findViewById(R.id.doctorTextView);
        hospitalNameTextView = findViewById(R.id.hospitalTextView);
        addressFieldTextView = findViewById(R.id.addressFieldTextView);
        medicineFieldTextView = findViewById(R.id.medicineFieldTextView);
        addReminderButton = findViewById(R.id.addReminderButton);
        photoFAB = findViewById(R.id.photoFAB);

        String date = getIntent().getStringExtra("date");
        String disease = getIntent().getStringExtra("disease");
        String doctor = getIntent().getStringExtra("doctor");
        String hospital = getIntent().getStringExtra("hospital");
        String address = getIntent().getStringExtra("address");
        String medicine = getIntent().getStringExtra("medicine");

        dateTextView.setText("Date: " + date);
        diseaseTextView.setText("Disease: " + disease);
        doctorNameTextView.setText("Doctors Name: " + doctor);
        hospitalNameTextView.setText("Hospital: " + hospital);
        addressFieldTextView.setText(address);
        medicineFieldTextView.setText(medicine);

        addReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSetReminderActivity();
            }
        });

        photoFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
    private void startSetReminderActivity() {
        Intent intent = new Intent(this, ReminderActivity.class);
        startActivity(intent);
    }
}