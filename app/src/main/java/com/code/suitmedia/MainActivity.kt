package com.code.suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.code.suitmedia.databinding.ActivityMainBinding
import com.code.suitmedia.second.SecondActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.checkButton.setOnClickListener {checkButtonCliked()}
        binding.nextButton.setOnClickListener {nextButtonCliked()}

    }

    private fun checkButtonCliked(){
        val inputText = binding.palindromeEditText.text.toString()
        if(inputText.isNotEmpty()){
            val isPalindrome = isPalindrome(inputText)
            val message = if (isPalindrome) {
                "isPalindrome"
            } else {
                "not palindrome"
            }

            showDialog(message)
        }else Toast.makeText(this, "Palindrome is empty!", Toast.LENGTH_SHORT).show()
    }

    private fun nextButtonCliked(){
        val nameInput = binding.nameEditText.text.toString()
        if(nameInput.isNotEmpty()){
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(SecondActivity.USER_NAME, nameInput)
            startActivity(intent)

        }else Toast.makeText(this, "Name is empty!", Toast.LENGTH_SHORT).show()


    }

    private fun isPalindrome(input: String): Boolean {
        val normalizedInput = input.replace(" ", "").toLowerCase(Locale.ROOT)
        val reversedInput = normalizedInput.reversed()
        return normalizedInput == reversedInput
    }

    private fun showDialog(message: String) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Palindrome Check")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()

        alertDialog.show()
    }
}