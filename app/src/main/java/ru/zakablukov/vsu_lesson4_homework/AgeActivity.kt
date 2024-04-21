package ru.zakablukov.vsu_lesson4_homework

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.zakablukov.vsu_lesson4_homework.databinding.ActivityAgeBinding

class AgeActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityAgeBinding::bind, R.id.main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_age)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initNextBtn()
        initBackBtn()
        initCloseBtn()
    }

    private fun initNextBtn() {
        binding.nextBtn.setOnClickListener {
            val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.extras?.getSerializable(AppConsts.USER_KEY, User::class.java)
            } else {
                intent.extras?.getSerializable(AppConsts.USER_KEY) as? User
            }
            user?.age = binding.ageEditText.text.toString().toInt()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(AppConsts.USER_KEY, user)
            startActivity(intent)
        }
    }

    private fun initBackBtn() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun initCloseBtn() {
        binding.closeBtn.setOnClickListener {
            finishAffinity()
        }
    }
}