package com.example.gpa_debaa1_calculator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText[] gradeInputs = new EditText[5];
    private TextView gpaResultText;
    private Button computeGpaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditTexts for grade input
        gradeInputs[0] = findViewById(R.id.gradeInput1);
        gradeInputs[1] = findViewById(R.id.gradeInput2);
        gradeInputs[2] = findViewById(R.id.gradeInput3);
        gradeInputs[3] = findViewById(R.id.gradeInput4);
        gradeInputs[4] = findViewById(R.id.gradeInput5);

        // Initialize TextView for GPA result display
        gpaResultText = findViewById(R.id.gpaResultText);

        // Initialize Button for computing GPA
        computeGpaBtn = findViewById(R.id.computeGpaBtn);
        computeGpaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeGPA();
            }
        });
    }

    private void computeGPA() {
        double sum = 0;
        boolean allFieldsValid = true;
        int count = 0;

        for (EditText gradeInput : gradeInputs) {
            String gradeStr = gradeInput.getText().toString();
            if (!gradeStr.isEmpty()) {
                try {
                    double grade = Double.parseDouble(gradeStr);
                    if (grade >= 0 && grade <= 100) {
                        sum += grade;
                        count++;
                    } else {
                        gradeInput.setError("Enter a value between 0 and 100");
                        allFieldsValid = false;
                    }
                } catch (NumberFormatException e) {
                    gradeInput.setError("Invalid input");
                    allFieldsValid = false;
                }
            }
        }

        if (allFieldsValid && count > 0) {
            double gpa = sum / count;
            gpaResultText.setText(String.format("GPA: %.2f", gpa));
            updateGpaResultBackground(gpa);
        } else if (count == 0) {
            gpaResultText.setText("Please enter at least one grade");
            gpaResultText.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void updateGpaResultBackground(double gpa) {
        if (gpa < 60) {
            gpaResultText.setBackgroundColor(Color.RED);
        } else if (gpa <= 79) {
            gpaResultText.setBackgroundColor(Color.YELLOW);
        } else {
            gpaResultText.setBackgroundColor(Color.GREEN);
        }
    }
}

