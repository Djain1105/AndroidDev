package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    // initializing 2 players (true and false) and turn count
    var PLAYER = true
    var TURN_COUNT = 0

    //Declaring the actual board and board status arrays
    var boardstatus = Array(3) { IntArray(3) }
    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // binding buttons to board using array initialization
        board = arrayOf(
            arrayOf(binding.btn1,binding.btn2,binding.btn3),
            arrayOf(binding.btn4,binding.btn5,binding.btn6),
            arrayOf(binding.btn7,binding.btn8,binding.btn9)
        )

        // creating a view for button listener
        for (i in board) {
            for(button in i) {
                button.setOnClickListener(this)
            }
        }

        // initializing the board using a function
        intializeBoardStatus()

        // defining the reset button work
        binding.resetBtn.setOnClickListener{
            PLAYER = true
            TURN_COUNT = 0
            intializeBoardStatus()
            updateDisplay("Player X Turn")
        }
    }

    // creating a function to initialize the board and board Status
    private fun intializeBoardStatus() {
        for(i in 0..2) {
            for(j in 0..2) {
                boardstatus[i][j] = -1
            }
        }

        for (i in board) {
            for(button in i) {
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    // defining the button click function
    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn1 -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.btn2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            }
            R.id.btn3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }
            R.id.btn4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
            }
            R.id.btn5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
            }
            R.id.btn6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }
            R.id.btn7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)
            }
            R.id.btn8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }
            R.id.btn9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER

        if(PLAYER){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player O Turn")
        }

        if(TURN_COUNT == 9) {
            updateDisplay("Game Draw")
        }

        // using check winner function to check after each click
        checkWinner()
    }

    // defining the check winner function
    private fun checkWinner() {
        // Horizontal Rows
        for(i in 0..2) {
            if (boardstatus[i][0] == boardstatus[i][1] && boardstatus[i][0] == boardstatus[i][2]) {
                if (boardstatus[i][0] == 1) {
                    updateDisplay("Player X Won")
                    break
                } else if (boardstatus[i][0] == 0) {
                    updateDisplay("Player O Won")
                    break
                }
            }
        }

        // Vertical Columns
        for(i in 0..2) {
            if (boardstatus[0][i] == boardstatus[1][i] && boardstatus[0][i] == boardstatus[2][i]) {
                if (boardstatus[0][i] == 1) {
                    updateDisplay("Player X Won")
                    break
                } else if (boardstatus[0][i] == 0) {
                    updateDisplay("Player O Won")
                    break
                }
            }
        }

        // First Diagonal
        if (boardstatus[0][0] == boardstatus[1][1] && boardstatus[0][0] == boardstatus[2][2]) {
            if (boardstatus[0][0] == 1) {
                updateDisplay("Player X Won")
            } else if (boardstatus[0][0] == 0) {
                updateDisplay("Player O Won")
            }
        }

        //Second Diagonal
        if(boardstatus[0][2] == boardstatus[1][1] && boardstatus[0][2] == boardstatus[2][0]) {
            if (boardstatus[0][2] == 1) {
                updateDisplay("Player X Won")
            } else if (boardstatus[0][2] == 0) {
                updateDisplay("Player O Won")
            }
        }
    }

    // defining the update display function
    private fun updateDisplay(text: String) {
        binding.displayTv.text = text
        if (text.contains("Won")){
            disableButton()
        }
    }

    // defining the disable button function
    private fun disableButton() {
        for (i in board) {
            for(button in i) {
                button.isEnabled = false
            }
        }
    }

    // defining the update value function
    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "O"
        val value = if(player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardstatus[row][col] = value
    }
}