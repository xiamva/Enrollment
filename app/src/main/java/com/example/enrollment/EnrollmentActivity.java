package com.example.enrollment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EnrollmentActivity extends AppCompatActivity {

    private ArrayList<CheckBox> subjectCheckBoxes;
    private TextView textViewCredits;
    private Button buttonSubmit;
    private int totalCredits = 0;
    private final int MAX_CREDITS = 24;
    private final int CREDIT_PER_SUBJECT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        textViewCredits = findViewById(R.id.textViewCredits);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Initialize CheckBoxes for subjects
        subjectCheckBoxes = new ArrayList<>();
        subjectCheckBoxes.add(findViewById(R.id.checkBoxSubject1)); // 3D Computer Graphics and Animation
        subjectCheckBoxes.add(findViewById(R.id.checkBoxSubject2)); // Advanced Blockchain
        subjectCheckBoxes.add(findViewById(R.id.checkBoxSubject3)); // Artificial Intelligence
        subjectCheckBoxes.add(findViewById(R.id.checkBoxSubject4)); // Calculus
        subjectCheckBoxes.add(findViewById(R.id.checkBoxSubject5)); // IoT Programming
        subjectCheckBoxes.add(findViewById(R.id.checkBoxSubject6)); // Robotics
        subjectCheckBoxes.add(findViewById(R.id.checkBoxSubject7)); // Software Engineering
        subjectCheckBoxes.add(findViewById(R.id.checkBoxSubject8)); // Security Risk Management
        subjectCheckBoxes.add(findViewById(R.id.checkBoxSubject9)); // Wireless and Mobile Programming

        // Set up CheckBox listeners
        for (CheckBox checkBox : subjectCheckBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (totalCredits + CREDIT_PER_SUBJECT <= MAX_CREDITS) {
                        totalCredits += CREDIT_PER_SUBJECT;
                    } else {
                        checkBox.setChecked(false); // Uncheck if exceeding max credits
                        Toast.makeText(EnrollmentActivity.this, "You can only take up to " + MAX_CREDITS + " credits", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    totalCredits -= CREDIT_PER_SUBJECT;
                }
                updateCreditsDisplay();
            });
        }

        // Handle Submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalCredits > 0) {
                    ArrayList<String> selectedSubjects = new ArrayList<>();
                    for (CheckBox checkBox : subjectCheckBoxes) {
                        if (checkBox.isChecked()) {
                            selectedSubjects.add(checkBox.getText().toString());
                        }
                    }

                    // Navigate to Enrollment Summary
                    Intent intent = new Intent(EnrollmentActivity.this, EnrollmentSummaryActivity.class);
                    intent.putStringArrayListExtra("selectedSubjects", selectedSubjects);
                    startActivity(intent);
                    finish(); // Close EnrollmentActivity
                } else {
                    Toast.makeText(EnrollmentActivity.this, "Please select at least one subject", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Initialize total credits text
        updateCreditsDisplay();
    }

    private void updateCreditsDisplay() {
        textViewCredits.setText("Total Credits: " + totalCredits + "/" + MAX_CREDITS);
    }
}
