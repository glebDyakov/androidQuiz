package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public int currentNumberQuestion = 1;
    public int allQuestions = 5;
    public ArrayList<Boolean> answers = new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextQuestionBtn = findViewById(R.id.nextQuestionBtn);
        nextQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("currentNumberQuestion", currentNumberQuestion + 1);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            currentNumberQuestion = Integer.valueOf(extras.getString("currentNumberQuestion"));
        } else if(extras == null){
            currentNumberQuestion = 1;
        }

        TextView questionDetector = findViewById(R.id.questionDetector);
        questionDetector.setText("Вопрос " + currentNumberQuestion + " из " + allQuestions);

        for(int answerIdx = 0; answerIdx < allQuestions; answerIdx++){
            answers.add(false);
        }

    }
}