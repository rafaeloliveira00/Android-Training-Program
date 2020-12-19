package pt.atp.bobi.presentation.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import pt.atp.bobi.R
import pt.atp.bobi.domain.Settings

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TopAppBar(title = { Text(stringResource(id = R.string.settings)) })

                    AboutAppItem(setting = Settings.settings[0])
                }
            }
        }
    }
}