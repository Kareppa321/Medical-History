package com.example.medicalhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddDiseaseActivity extends AppCompatActivity {
    private EditText diseaseEditText;
    private EditText doctorEditText;
    private EditText hospitalEditText;
    private EditText medicineEditText;
    private EditText addressEditText;
    private EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_disease);

        diseaseEditText = findViewById(R.id.diseaseEditText);
        doctorEditText = findViewById(R.id.doctorNameEditText);
        hospitalEditText = findViewById(R.id.hospitalEditText);
        medicineEditText = findViewById(R.id.medicineEditText);
        addressEditText = findViewById(R.id.addressEditText);
        dateEditText = findViewById(R.id.dateEditText);

        FloatingActionButton addDiseaseItem = findViewById(R.id.addDiseaseItem);
        addDiseaseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToList();
            }
        });
    }
    private void addToList() {
        String diseaseName = diseaseEditText.getText().toString();
        String doctorName = doctorEditText.getText().toString();
        String hospitalName = hospitalEditText.getText().toString();
        String medicineName = medicineEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String date = dateEditText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("diseaseName", diseaseName);
        intent.putExtra("doctorName", doctorName);
        intent.putExtra("hospitalName", hospitalName);
        intent.putExtra("medicineName", medicineName);
        intent.putExtra("address", address);
        intent.putExtra("date", date);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}