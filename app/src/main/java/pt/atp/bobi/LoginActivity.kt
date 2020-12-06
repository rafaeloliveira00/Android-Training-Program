package pt.atp.bobi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Setup()
    }

    private fun setup() {
        findViewById<TextInputEditText>(R.id.tet_username).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<TextInputEditText>(R.id.tet_password).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<Button>(R.id.btn_auth).setOnClickListener {
            validateCredentialsAndRedirect()
        }
    }

    private fun Setup() {
        findViewById<TextInputEditText>(R.id.tet_username).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<TextInputEditText>(R.id.tet_password).setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                validateCredentialsAndRedirect()
            }
            true
        }

        findViewById<Button>(R.id.btn_auth).setOnClickListener {
            validateCredentialsAndRedirect()
        }
    }

    private fun validateCredentialsAndRedirect() {
        if (areCredentialsValid()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun areCredentialsValid(): Boolean {
        val username = findViewById<TextInputEditText>(R.id.tet_username).text.toString()
        val password = findViewById<TextInputEditText>(R.id.tet_password).text.toString()

        if (username.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text =
                getString(R.string.error_credentials_empty_username)
            return false
        }

        if (password.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text =
                getString(R.string.error_credentials_empty_password)
            return false
        }

        val valid = username == password

        if (!valid) {
            findViewById<TextView>(R.id.tv_error).text =
                getString(R.string.error_invalid_credentials)
        }

        return valid
    }
}