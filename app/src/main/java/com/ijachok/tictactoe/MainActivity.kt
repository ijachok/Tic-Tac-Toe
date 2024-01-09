package com.ijachok.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ijachok.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var player = "p1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            box1.setOnClickListener { onButtonClick(box1) }
            box2.setOnClickListener { onButtonClick(box2) }
            box3.setOnClickListener { onButtonClick(box3) }
            box4.setOnClickListener { onButtonClick(box4) }
            box5.setOnClickListener { onButtonClick(box5) }
            box6.setOnClickListener { onButtonClick(box6) }
            box7.setOnClickListener { onButtonClick(box7) }
            box8.setOnClickListener { onButtonClick(box8) }
            box9.setOnClickListener { onButtonClick(box9) }

            resetButton.setOnClickListener { resetGame() }
            websiteButton.setOnClickListener {
                val intent = Intent(this@MainActivity, WebsiteActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun onButtonClick(button: Button) {

        val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
        val interpolator = MyBounceInterpolator(0.05, 40.0)
        anim.interpolator = interpolator


        if (button.text == "") {
            if (player == "p1") {
                button.background = resources.getDrawable(R.drawable.x_icon)
                button.text = "x"
                button.isClickable = false
                button.startAnimation(anim)
                player = "p2"
            } else {
                button.background = resources.getDrawable(R.drawable.o_icon)
                button.text = "o"
                button.isClickable = false
                button.startAnimation(anim)
                player = "p1"
            }
        }

        win()
    }

    private fun win() {
        binding.apply {
            if ((box1.text == "x" && box2.text == "x" && box3.text == "x") ||
                (box4.text == "x" && box5.text == "x" && box6.text == "x") ||
                (box7.text == "x" && box8.text == "x" && box9.text == "x") ||
                (box1.text == "x" && box4.text == "x" && box7.text == "x") ||
                (box2.text == "x" && box5.text == "x" && box8.text == "x") ||
                (box3.text == "x" && box6.text == "x" && box9.text == "x") ||
                (box1.text == "x" && box5.text == "x" && box9.text == "x") ||
                (box3.text == "x" && box5.text == "x" && box7.text == "x")
            ) {
                tvResult.text = "X won the game"
                resetButton.visibility = View.VISIBLE
                Toast.makeText(this@MainActivity, "X won the game", Toast.LENGTH_SHORT).show()
                disableButtons()
            } else if ((box1.text == "o" && box2.text == "o" && box3.text == "o") ||
                (box4.text == "o" && box5.text == "o" && box6.text == "o") ||
                (box7.text == "o" && box8.text == "o" && box9.text == "o") ||
                (box1.text == "o" && box4.text == "o" && box7.text == "o") ||
                (box2.text == "o" && box5.text == "o" && box8.text == "o") ||
                (box3.text == "o" && box6.text == "o" && box9.text == "o") ||
                (box1.text == "o" && box5.text == "o" && box9.text == "o") ||
                (box3.text == "o" && box5.text == "o" && box7.text == "o")
            ) {
                tvResult.text = "O won the game"
                Toast.makeText(this@MainActivity, "O won the game", Toast.LENGTH_SHORT).show()
                disableButtons()
            } else if (box1.text != "" && box2.text != "" && box3.text != "" &&
                box4.text != "" && box5.text != "" && box6.text != "" &&
                box7.text != "" && box8.text != "" && box9.text != ""
            ) {
                tvResult.text = "Match Draw"
                Toast.makeText(this@MainActivity, "It's a Tie... Play again...", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun disableButtons() {
        binding.apply {
            box1.isClickable = false
            box2.isClickable = false
            box3.isClickable = false
            box4.isClickable = false
            box5.isClickable = false
            box6.isClickable = false
            box7.isClickable = false
            box8.isClickable = false
            box9.isClickable = false
        }
    }

    private fun resetGame() {
        player = "p1"

        binding.apply {

            box1.background = resources.getDrawable(R.drawable.box_icon)
            box2.background = resources.getDrawable(R.drawable.box_icon)
            box3.background = resources.getDrawable(R.drawable.box_icon)
            box4.background = resources.getDrawable(R.drawable.box_icon)
            box5.background = resources.getDrawable(R.drawable.box_icon)
            box6.background = resources.getDrawable(R.drawable.box_icon)
            box7.background = resources.getDrawable(R.drawable.box_icon)
            box8.background = resources.getDrawable(R.drawable.box_icon)
            box9.background = resources.getDrawable(R.drawable.box_icon)

            box1.text = ""
            box2.text = ""
            box3.text = ""
            box4.text = ""
            box5.text = ""
            box6.text = ""
            box7.text = ""
            box8.text = ""
            box9.text = ""

            box1.isClickable = true
            box2.isClickable = true
            box3.isClickable = true
            box4.isClickable = true
            box5.isClickable = true
            box6.isClickable = true
            box7.isClickable = true
            box8.isClickable = true
            box9.isClickable = true

            tvResult.text = ""
            resetButton.visibility = View.GONE
        }
    }
}