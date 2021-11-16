package com.example.medicalhistory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.DiskReadViolation;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DashBoardActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    private ArrayList<DiseaseEntry> diseaseArrayList;
    private DiseaseAdapter diseaseAdapter;

    FirebaseUser currentUser;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(currentUser.getUid());
        DatabaseReference diseaseReference = reference.child("diseases");
        diseaseArrayList = new ArrayList<DiseaseEntry>();



        RecyclerView diseaseList = findViewById(R.id.diseaseList);
        diseaseList.setLayoutManager(new LinearLayoutManager(this));
        diseaseAdapter = new DiseaseAdapter(diseaseArrayList);
        diseaseList.setAdapter(diseaseAdapter);
        FloatingActionButton addDiseaseItem = findViewById(R.id.addDiseaseItem);

        diseaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                diseaseArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DiseaseEntry entry = dataSnapshot.getValue(DiseaseEntry.class);
                    diseaseArrayList.add(entry);
                }
                diseaseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addDiseaseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddDiseaseActivity();
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            diseaseArrayList.add((DiseaseEntry) getIntent().getSerializableExtra("entry"));
        }

    }

    public void startAddDiseaseActivity() {
        Intent intent = new Intent(this, AddDiseaseActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                String diseaseName = data.getStringExtra("diseaseName");
                String doctorName = data.getStringExtra("doctorName");
                String hospitalName = data.getStringExtra("hospitalName");
                String medicineName = data.getStringExtra("medicineName");
                String hospitalAddress = data.getStringExtra("hospitalName");
                String date = data.getStringExtra("date");

                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference(currentUser.getUid());
                DatabaseReference diseaseReference = reference.child("diseases");
                DatabaseReference currentDiseaseRef = diseaseReference.child(Integer.toString(diseaseArrayList.size()));

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("doctorName", doctorName);
                hashMap.put("diseaseName", diseaseName);
                hashMap.put("hospitalName", hospitalName);
                hashMap.put("hospitalAddress", hospitalAddress);
                hashMap.put("medicineName", medicineName);
                hashMap.put("date", date);
                currentDiseaseRef.setValue(hashMap);


//                DiseaseEntry toAdd = new DiseaseEntry();
//                toAdd.doctorName = doctorName;
//                toAdd.diseaseName = diseaseName;
//                toAdd.hospitalName = hospitalName;
//                toAdd.medicineName = medicineName;
//                toAdd.hospitalAddress = hospitalAddress;
//
//                diseaseArrayList.add(toAdd);
//                diseaseAdapter.notifyItemInserted(diseaseArrayList.size() - 1);
            }
        }
    }

}