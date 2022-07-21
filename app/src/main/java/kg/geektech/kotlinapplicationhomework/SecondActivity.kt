package kg.geektech.kotlinapplicationhomework

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kg.geektech.kotlinapplicationhomework.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var user: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getEditText()
        buttonClicker()
    }

    private fun buttonClicker() { //Отправляем данные из второго экрана на первый
        binding.btnClicker.setOnClickListener {
            if (binding.etUser.text?.isEmpty() == true) {
                Toast.makeText(this, getString(R.string.redactor_toast), Toast.LENGTH_SHORT)
                    .show()
            } else {
                user = binding.etUser.text.toString()
                setResult(RESULT_OK, intent.putExtra(MainActivity.USER_KEY, user))//
                finish()//финиш уничтожает активити
            }
        }
    }

    private fun getEditText() { //Получаем информацию из первого экрана
        user = intent.getStringExtra(MainActivity.USER_KEY)
        binding.etUser.setText(user)
    }
}