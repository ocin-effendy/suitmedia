package com.code.suitmedia.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.code.suitmedia.R
import com.code.suitmedia.databinding.ActivitySecondBinding
import com.code.suitmedia.third.ThirdActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
            title = "Second Screen"
        }

        setView()

        binding.chooseUserButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            val selectedUserName = intent.getStringExtra(SELECTED_USER_NAME)
            if (selectedUserName != null) {
                binding.selectedUserLabel.text = selectedUserName
            }
        }
    }

    private fun setView(){
        val userName = intent.getStringExtra(USER_NAME)
        binding.usernameTextView.text = userName
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val USER_NAME = "USER NAME"
        const val SELECTED_USER_NAME = "SELECTED_USER_NAME"
    }
}