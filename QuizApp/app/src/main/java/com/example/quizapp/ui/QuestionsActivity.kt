package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView

    private lateinit var textViewOp1: TextView
    private lateinit var textViewOp2: TextView
    private lateinit var textViewOp3: TextView
    private lateinit var textViewOp4: TextView
    private lateinit var checkButton: Button

    private lateinit var questionsList: MutableList<Question>
    private var questionCounter = 0
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false

    private lateinit var name: String
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        progressBar = findViewById(R.id.progressBar)
        textViewProgress = findViewById(R.id.text_view_progress)
        textViewQuestion = findViewById(R.id.question_text_view)
        flagImage = findViewById(R.id.image_flag)
        checkButton = findViewById(R.id.button_check)
        textViewOp1 = findViewById(R.id.text_view_option_one)
        textViewOp2 = findViewById(R.id.text_view_option_two)
        textViewOp3 = findViewById(R.id.text_view_option_three)
        textViewOp4 = findViewById(R.id.text_view_option_four)

        textViewOp1.setOnClickListener(this)
        textViewOp2.setOnClickListener(this)
        textViewOp3.setOnClickListener(this)
        textViewOp4.setOnClickListener(this)
        checkButton.setOnClickListener(this)

        questionsList = Constants.getQuestions()
        Log.d("QuestionSize","${questionsList.size}")

        showNextQuestion()
        if(intent.hasExtra(Constants.USER_NAME)) {
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }
    }

    private fun showNextQuestion(){
        if(questionCounter < questionsList.size){
            resetOptions()
            val question = questionsList[questionCounter]
            flagImage.setImageResource(question.image)
            progressBar.progress = questionCounter
            textViewProgress.text = "${questionCounter + 1}/${progressBar.max}"
            textViewQuestion.text = question.question
            textViewOp1.text = question.op1
            textViewOp2.text = question.op2
            textViewOp3.text = question.op3
            textViewOp4.text = question.op4

            checkButton.text = "CHECK"
            currentQuestion = question
        } else {
            checkButton.text = "FINISH"
            Intent(this, ResultActivity::class.java).also {
                it.putExtra(Constants.USER_NAME, name)
                it.putExtra(Constants.SCORE, score)
                it.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                startActivity(it)
            }
        }
        questionCounter++
        answered = false
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(textViewOp1)
        options.add(textViewOp2)
        options.add(textViewOp3)
        options.add(textViewOp4)

        for(option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOption(textView: TextView, selectOptionNumber: Int){
        resetOptions()
        selectedAnswer = selectOptionNumber
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.text_view_option_one -> {
                selectedOption(textViewOp1, 1)
            }
            R.id.text_view_option_two -> {
                selectedOption(textViewOp2, 2)
            }
            R.id.text_view_option_three -> {
                selectedOption(textViewOp3, 3)
            }
            R.id.text_view_option_four -> {
                selectedOption(textViewOp4, 4)
            }
            R.id.button_check -> {
                if(!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer = 0
            }
        }
    }

    private fun checkAnswer() {
        answered = true
        if(selectedAnswer == currentQuestion.correctAnswer) {
            highlightAnswer(selectedAnswer)
            score++
        } else {
            when(selectedAnswer){
                1 -> {
                    textViewOp1.background = ContextCompat.getDrawable(this,R.drawable.wrong_option_border_bg)
                }
                2 -> {
                    textViewOp2.background = ContextCompat.getDrawable(this,R.drawable.wrong_option_border_bg)
                }
                3 -> {
                    textViewOp3.background = ContextCompat.getDrawable(this,R.drawable.wrong_option_border_bg)
                }
                4 -> {
                    textViewOp4.background = ContextCompat.getDrawable(this,R.drawable.wrong_option_border_bg)
                }
            }
        }
        checkButton.text = "NEXT"
        showSolution()
    }

    private fun showSolution(){
        selectedAnswer = currentQuestion.correctAnswer
        highlightAnswer(selectedAnswer)
    }

    private fun highlightAnswer(answer: Int) {
        when(answer){
            1 -> {
                textViewOp1.background = ContextCompat.getDrawable(this,R.drawable.correct_option_border_bg)
            }
            2 -> {
                textViewOp2.background = ContextCompat.getDrawable(this,R.drawable.correct_option_border_bg)
            }
            3 -> {
                textViewOp3.background = ContextCompat.getDrawable(this,R.drawable.correct_option_border_bg)
            }
            4 -> {
                textViewOp4.background = ContextCompat.getDrawable(this,R.drawable.correct_option_border_bg)
            }
        }
    }
}