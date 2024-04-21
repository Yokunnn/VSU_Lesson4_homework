package ru.zakablukov.vsu_lesson4_homework

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.zakablukov.vsu_lesson4_homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initBtn()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        showToast(intent)
    }

    private fun initBtn() {
        binding.startBtn.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(requiredIntent: Intent?) {
        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requiredIntent?.extras?.getSerializable(AppConsts.USER_KEY, User::class.java)
        } else {
            requiredIntent?.extras?.getSerializable(AppConsts.USER_KEY) as? User
        }
        user?.let {
            Toast.makeText(
                this,
                "Добро пожаловать, ${user.name} ${user.surname}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}