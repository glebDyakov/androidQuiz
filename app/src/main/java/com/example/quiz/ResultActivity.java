package com.example.quiz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView result = findViewById(R.id.result);
        Bundle extras = getIntent().getExtras();
        int allQuestions = extras.getInt("allQuestions");
        int answers = extras.getInt("answers");
        result.setText("Правильные ответы " + String.valueOf(answers) + " из " + String.valueOf(allQuestions));
    }
}
