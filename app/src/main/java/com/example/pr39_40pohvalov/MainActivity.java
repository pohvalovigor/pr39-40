package com.example.pr39_40pohvalov;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button startQuizButton, submitAnswerButton;
    private TextView questionTextView;
    private EditText answerEditText;

    private String[] questions = {
            "Какой компонент отвечает за обработку данных?",
            "Что такое ОЗУ?",
            "Какой компонент отвечает за вывод изображения на экран?",
            "Что такое SSD?"
    };

    private String[] answers = {
            "procesor",
            "оперативная память",
            "видеокарта",
            "твердотельный накопитель"
    };

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        startQuizButton = findViewById(R.id.startQuizButton);
        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);

        startQuizButton.setOnClickListener(v -> startQuiz());
        submitAnswerButton.setOnClickListener(v -> checkAnswer());
    }

    private void startQuiz() {
        currentQuestionIndex = 0;
        score = 0;

        startQuizButton.setVisibility(View.GONE);
        questionTextView.setVisibility(View.VISIBLE);
        answerEditText.setVisibility(View.VISIBLE);
        submitAnswerButton.setVisibility(View.VISIBLE);

        showQuestion();
    }
    private void showQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionTextView.setText(questions[currentQuestionIndex]);
            answerEditText.setText("");
        } else {
            endQuiz();
        }
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim().toLowerCase();

        if (userAnswer.equals(answers[currentQuestionIndex].toLowerCase())) {
            score++;
        }

        currentQuestionIndex++;
        showQuestion();
    }

    private void endQuiz() {
        questionTextView.setVisibility(View.GONE);
        answerEditText.setVisibility(View.GONE);
        submitAnswerButton.setVisibility(View.GONE);

        Toast.makeText(this, "Викторина окончена! Правильных ответов: " + score + "/" + questions.length, Toast.LENGTH_LONG).show();

        startQuizButton.setVisibility(View.VISIBLE);
    }
}

