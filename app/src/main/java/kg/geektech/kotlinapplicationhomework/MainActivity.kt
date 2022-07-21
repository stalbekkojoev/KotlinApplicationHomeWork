package kg.geektech.kotlinapplicationhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kg.geektech.kotlinapplicationhomework.databinding.ActivityMainBinding

/*Дз: На первой активити добавить EditText + Button, при вводе если значения в editText пустое и вы нажали
на button, то отобразить Toast, что EditText не может быть пустым, иначе перейти на вторую активити и
отобразить значение в EditText, также добавить Button, если EditText не пуст,
то вернуться на 1 активити и отобразить текст из 2 активити.(использовать registerForActivity)*/

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //private lateinit var launcher: ActivityResultLauncher<Intent> Правильный вариант
    //private lateinit var user: String Тоже вроде предположительный варинат
    private var launcher: ActivityResultLauncher<Intent>? = null
    private var user: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher()
        buttonClicker()
    }

    private fun buttonClicker() {//Тут отправляем информацию из первого экрана на второй
        binding.btnClicker.setOnClickListener {
            if (binding.etUser.text?.isEmpty() == true) {
                Toast.makeText(this, getString(R.string.redactor_toast), Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                user = binding.etUser.text.toString()
                intent.putExtra(USER_KEY, user)
                launcher?.launch(intent)
            }
        }
    }

    private fun resultLauncher() {// Тут получаем информацию из второго экрана
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val userText = it.data?.getStringExtra(USER_KEY)
                binding.etUser.setText(userText)
            }
        }
    }

    companion object {
        const val USER_KEY = "User"
    }
}