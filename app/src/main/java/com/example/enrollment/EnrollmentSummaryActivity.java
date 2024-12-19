package com.example.enrollment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class EnrollmentSummaryActivity extends AppCompatActivity {

    private LinearLayout layoutSubjects;
    private TextView textViewTotalCredits;
    private Button buttonFinish;
    private ArrayList<String> selectedSubjects;
    private int totalCredits = 0;

    // Schedules for subjects
    private final HashMap<String, String> schedules = new HashMap<String, String>() {{
        put("3D Computer Graphics and Animation", "FRI, 13:40 - 15:55");
        put("Advanced Blockchain", "MON, 07:30 - 09:45");
        put("Artificial Intelligence", "TUE, 12:30 - 14:45");
        put("Calculus", "FRI, 07:00 - 09:15");
        put("IoT Programming", "TUE, 10:00 - 12:15");
        put("Robotics", "WED, 10:00 - 12:15");
        put("Software Engineering", "THU, 10:00 - 12:15 / LABA211");
        put("Security Risk Management", "WED, 07:00 - 12:15");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_summary);

        layoutSubjects = findViewById(R.id.layoutSubjects);
        textViewTotalCredits = findViewById(R.id.textViewTotalCredits);
        buttonFinish = findViewById(R.id.buttonFinish);

        // Get selected subjects from intent
        selectedSubjects = getIntent().getStringArrayListExtra("selectedSubjects");

        // Display selected subjects and schedules
        if (selectedSubjects != null) {
            for (String subject : selectedSubjects) {
                addSubjectToLayout(subject);
                totalCredits += 3; // Each subject has 3 credits
            }
        }

        // Set total credits
        textViewTotalCredits.setText("Total Credits: " + totalCredits);

        // Finish button action
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EnrollmentSummaryActivity.this, "Enrollment saved successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EnrollmentSummaryActivity.this, HomeActivity.class));
                finish(); // Close activity
            }
        });
    }

    private void addSubjectToLayout(String subject) {
        TextView textView = new TextView(this);
        textView.setText(subject + " (" + schedules.get(subject) + ")");
        textView.setTextSize(16);
        layoutSubjects.addView(textView);
    }
}
