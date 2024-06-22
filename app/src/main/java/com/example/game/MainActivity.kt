package com.example.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var pcImg: ImageView
    lateinit var gameImg: ImageView
    lateinit var userImg: ImageView
    lateinit var btnSpock: Button
    lateinit var btnScissors: Button
    lateinit var btnPaper: Button
    lateinit var btnStone: Button
    lateinit var btnLizard: Button
    lateinit var btnStart: Button

    enum class Choice {
        SPOCK, SCISSORS, PAPER, STONE, LIZARD, NONE
    }

    var userChoice = Choice.NONE
    var pcChoice = Choice.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameImg = findViewById(R.id.gameImg)
        pcImg = findViewById(R.id.pcImg)
        userImg = findViewById(R.id.userImg)
        btnSpock = findViewById(R.id.BTNspoke)
        btnScissors = findViewById(R.id.BTNscissors)
        btnPaper = findViewById(R.id.BTNpaper)
        btnStone = findViewById(R.id.BTNstone)
        btnLizard = findViewById(R.id.BTNlizard)
        btnStart = findViewById(R.id.BTNstrt)

        btnSpock.setOnClickListener { onUserChoice(Choice.SPOCK) }
        btnScissors.setOnClickListener { onUserChoice(Choice.SCISSORS) }
        btnPaper.setOnClickListener { onUserChoice(Choice.PAPER) }
        btnStone.setOnClickListener { onUserChoice(Choice.STONE) }
        btnLizard.setOnClickListener { onUserChoice(Choice.LIZARD) }
        btnStart.setOnClickListener { onStartGame() }
    }

    private fun onUserChoice(choice: Choice) {
        userChoice = choice
        btnStone.setOnClickListener {
            userImg.setImageResource(R.drawable.stone)
        }
        btnPaper.setOnClickListener {
            userImg.setImageResource(R.drawable.paper)
        }
        btnLizard.setOnClickListener {
            userImg.setImageResource(R.drawable.lizard)
        }
        btnScissors.setOnClickListener {
            userImg.setImageResource(R.drawable.scissors)
        }
        btnSpock.setOnClickListener {
            userImg.setImageResource(R.drawable.spoke)
        }
    }

    private fun onStartGame() {
        if (userChoice == Choice.NONE) {
            Toast.makeText(this@MainActivity, "Поажлуйста, сделайте выбор", Toast.LENGTH_SHORT).show()
            return
        }

        pcChoice = Choice.values()[Random.nextInt(Choice.values().size - 1)]
        when (pcChoice) {
            Choice.STONE -> pcImg.setImageResource(R.drawable.stone)
            Choice.PAPER -> pcImg.setImageResource(R.drawable.paper)
            Choice.SCISSORS -> pcImg.setImageResource(R.drawable.scissors)
            Choice.LIZARD -> pcImg.setImageResource(R.drawable.lizard)
            Choice.SPOCK -> pcImg.setImageResource(R.drawable.spoke)
            else -> {}
        }

        val result = determineWinner(userChoice, pcChoice)
    }

    private fun determineWinner(userChoice: Choice, pcChoice: Choice): String {
        val result = when (userChoice) {
            Choice.SCISSORS -> when (pcChoice) {
                Choice.PAPER, Choice.LIZARD -> "Компьютер выиграл!"
                else -> "Вы выиграли"
            }
            Choice.PAPER -> when (pcChoice) {
                Choice.STONE, Choice.SPOCK -> "Компьютер выиграл!"
                else -> "Вы выиграли"
            }
            Choice.STONE -> when (pcChoice) {
                Choice.LIZARD, Choice.SCISSORS -> "Компьютер выиграл!"
                else -> "Вы выиграли"
            }
            Choice.LIZARD -> when (pcChoice) {
                Choice.SPOCK, Choice.PAPER -> "Компьютер выиграл!"
                else -> "Вы выиграли"
            }
            Choice.SPOCK -> when (pcChoice) {
                Choice.SCISSORS, Choice.STONE -> "Компьютер выиграл!"
                else -> "Вы выиграли"
            }
            else -> "Ничья"
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        return result
    }

}