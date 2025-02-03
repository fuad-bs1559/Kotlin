package com.example.kotlin_demo
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.net.CookieStore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
    }

    private fun setListeners(){
        val clickableView: List<View> = listOf(findViewById(R.id.box_one_text),
            findViewById(R.id.box_two_text),
            findViewById(R.id.box_three_text),
            findViewById(R.id.box_four_text),
            findViewById(R.id.box_five_text),
            findViewById(R.id.red_button),
            findViewById(R.id.yollow_button),
            findViewById(R.id.green_button))

        for (item in clickableView){
            item.setOnClickListener{ makeColored(it) }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun makeColored(view: View){
        when(view.id){
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)

            R.id.box_three_text -> view.setBackgroundColor(Color.BLUE)
            R.id.box_four_text -> view.setBackgroundColor(Color.BLUE)
            R.id.box_five_text -> view.setBackgroundColor(Color.BLUE)

            R.id.red_button -> findViewById<View?>(R.id.box_three_text).setBackgroundColor(Color.CYAN)
            R.id.yollow_button -> findViewById<View?>(R.id.box_four_text).setBackgroundColor(Color.CYAN)
            R.id.green_button -> findViewById<View?>(R.id.box_five_text).setBackgroundColor(Color.CYAN)

            else -> view.setBackgroundColor(Color.RED)
        }
    }
}