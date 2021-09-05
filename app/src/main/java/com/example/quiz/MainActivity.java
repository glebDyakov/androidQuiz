package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    public int currentNumberQuestion = 1;
    public int allQuestions = 5;
    public ArrayList<Boolean> answers = new ArrayList<Boolean>();
    public ArrayList<String> rightAnswers = new ArrayList<String>();
    public ArrayList<String> questions = new ArrayList<String>();
    public ArrayList<String> variants = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextQuestionBtn = findViewById(R.id.nextQuestionBtn);
        nextQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentNumberQuestion <= allQuestions - 1) {
                    RadioGroup variantGroup = findViewById(R.id.variantGroup);
                    Log.d("mytag", String.valueOf(variantGroup.getCheckedRadioButtonId()));
                    Log.d("mytag", String.valueOf(currentNumberQuestion + 1));
                    RadioButton checkedRadioButton = findViewById(variantGroup.getCheckedRadioButtonId());
                    if (checkedRadioButton.getText().toString().contains(rightAnswers.get(currentNumberQuestion - 1))) {
                        Log.d("mytag", "Правильный ответ");
                        answers.set(currentNumberQuestion - 1, true);
                    } else if (!checkedRadioButton.getText().toString().contains(rightAnswers.get(currentNumberQuestion - 1))) {
                        Log.d("mytag", "Не правильный ответ");
                    }
                    //                Intent intent = MainActivity.this.getIntent();
                    //                intent.putExtra("currentNumberQuestion", currentNumberQuestion + 1);
                    //                MainActivity.this.startActivity(intent);
//                    MainActivity.this.startActivity(MainActivity.this.getIntent());

                    currentNumberQuestion += 1;

                    ProgressBar progressBar = findViewById(R.id.progressBar);
                    int delta = 100 / allQuestions * currentNumberQuestion;
//                    progressBar.setProgress(progressBar.getProgress() + delta);
                    progressBar.setProgress(delta);

                    refreshActivity();
                } else if(currentNumberQuestion > allQuestions - 1){

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("allQuestions", allQuestions);
//                    List<Boolean> answersList = answers.stream().filter((a) -> {
//                        return !a;
//                    }).collect(Collectors.<Boolean>toList());
                    ArrayList<Boolean> answersCount = new ArrayList<Boolean>();
                    for(Boolean answer : answers){
                        if(answer){
                            answersCount.add(answer);
                        }
                    }
                    intent.putExtra("answers", answersCount.size());
                    MainActivity.this.startActivity(intent);

                }
            }
        });

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        refreshActivity();

    }

    public void refreshActivity() {
//        Bundle extras = getIntent().getExtras();
//        if(extras != null){
//            currentNumberQuestion = Integer.valueOf(extras.getString("currentNumberQuestion"));
//        } else if(extras == null){
//            currentNumberQuestion = 1;
//        }
        Log.d("mytag", String.valueOf(currentNumberQuestion));
        TextView questionDetector = findViewById(R.id.questionDetector);
        questionDetector.setText("Вопрос " + currentNumberQuestion + " из " + allQuestions);

        for(int answerIdx = 0; answerIdx < allQuestions; answerIdx++){
            answers.add(false);
        }

        rightAnswers.add("Мифический");
        rightAnswers.add("Картечь");
        rightAnswers.add("След дыма");
        rightAnswers.add("Мне не нужна одежда я робот");
        rightAnswers.add("Чёрный");

        questions.add("Какой тип бойца?");
        questions.add("Выбери любимую атаку?");
        questions.add("Выбери себе звёздную силу?");
        questions.add("Как ты обычно одеваешься?");
        questions.add("Какой цвет тебе нравится?");

        variants.add("Мифический");
        variants.add("Сверхредкий");
        variants.add("Эпический");
        variants.add("Легендарный");
        variants.add("Крутящиеся лезвия");
        variants.add("Картечь");
        variants.add("Лучевой бластер");
        variants.add("Мушкетон");
        variants.add("Плохая карма");
        variants.add("Смертельный яд");
        variants.add("След дыма");
        variants.add("Жуткая жатва");
        variants.add("Худи и шорты");
        variants.add("Джинсы и топ");
        variants.add("Юбка и топ");
        variants.add("Мне не нужна одежда я робот");
        variants.add("Серый");
        variants.add("Розовый");
        variants.add("Чёрный");
        variants.add("Фиолетовый");

        TextView currentQuestion = findViewById(R.id.currentQuestion);
        currentQuestion.setText(questions.get(currentNumberQuestion));

        TextView variantOne = findViewById(R.id.variantOne);
        variantOne.setText(variants.get(currentNumberQuestion * 4 - 4));
        TextView variantTwo = findViewById(R.id.variantTwo);
        variantTwo.setText(variants.get(currentNumberQuestion * 4 - 3));
        TextView variantThree = findViewById(R.id.variantThree);
        variantThree.setText(variants.get(currentNumberQuestion * 4 - 2));
        TextView variantFour = findViewById(R.id.variantFour);
        variantFour.setText(variants.get(currentNumberQuestion * 4 - 1));

        ImageView questionImg = findViewById(R.id.questionImg);
        if(currentNumberQuestion == 1){
            questionImg.setImageResource(R.drawable.barcode);
        } else if(currentNumberQuestion == 2){
            questionImg.setImageResource(R.drawable.five);
        } else if(currentNumberQuestion == 3){
            questionImg.setImageResource(R.drawable.magnet);
        } else if(currentNumberQuestion == 4){
            questionImg.setImageResource(R.drawable.camera);
        } else if(currentNumberQuestion == 5){
            questionImg.setImageResource(R.drawable.cross);
        }



    }

}