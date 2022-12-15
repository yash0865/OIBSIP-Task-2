package com.example.quiz_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Quiz extends AppCompatActivity {
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submit;
    Button clickedButton;

    int score = 0;
    int i = 1;
    int totalQuestions = QuestionsAndAnswers.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswers = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        totalQuestionsTextView = findViewById(R.id.textView);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        submit = findViewById(R.id.submitButton);

        totalQuestionsTextView.setText("Question No: " +i++ +"/10");

        loadNewQuestion();

    }

    private void loadNewQuestion() {
        if(currentQuestionIndex == totalQuestions){
            finishQuiz();
            return;
        }

        questionTextView.setText(currentQuestionIndex+1 +"."+ QuestionsAndAnswers.question[currentQuestionIndex]);
        ansA.setText(QuestionsAndAnswers.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionsAndAnswers.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionsAndAnswers.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionsAndAnswers.choices[currentQuestionIndex][3]);
    }

    private void finishQuiz() {
        totalQuestionsTextView.setText("Question No: " +"10/10");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.reward);
        builder.setTitle("Result");
        builder.setMessage("Score : "+score+"/"+totalQuestions);
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartQuiz();
            }
        });
        builder.create();
        builder.show();

    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        totalQuestionsTextView.setText("Question No: " + i++ +"/10");
        loadNewQuestion();
    }

    public void answerButtonClicked(View view){
        ansA.setTextColor(Color.rgb(231, 131, 46));
        ansB.setTextColor(Color.rgb(231, 131, 46));
        ansC.setTextColor(Color.rgb(231, 131, 46));
        ansD.setTextColor(Color.rgb(231, 131, 46));

        clickedButton =(Button) view;

        selectedAnswers = clickedButton.getText().toString();
        clickedButton.setTextColor(Color.rgb(255, 255, 255));

    }
    public void submitButtonClicked(View view){
        clickedButton.setTextColor(Color.rgb(231, 131, 46));
        if(selectedAnswers.equals(QuestionsAndAnswers.correctAnswers[currentQuestionIndex])){
            score++;
        }
        totalQuestionsTextView.setText("Question No: " + i++ +"/10");
        currentQuestionIndex++;
        loadNewQuestion();
    }
}