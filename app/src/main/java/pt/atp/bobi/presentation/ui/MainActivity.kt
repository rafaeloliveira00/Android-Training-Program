package pt.atp.bobi.presentation.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import pt.atp.bobi.EXTRA_USERNAME
import pt.atp.bobi.R
import pt.atp.bobi.presentation.MainViewModel
import java.io.File

private const val REQUEST_IMAGE_CAPTURE = 100
private const val REQUEST_READ_STORAGE = 200

private const val FIFI =
    "https://raw.githubusercontent.com/android-training-program/aula-5/master/imagens/fifi.jpg?raw=true"

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.extras?.getString(EXTRA_USERNAME, getString(R.string.welcome_default))
        findViewById<TextView>(R.id.tv_hello).text = getString(R.string.welcome, username)

        findViewById<Button>(R.id.open_camera).setOnClickListener {
            openNativeCamera()
        }

        findViewById<Button>(R.id.open_list).setOnClickListener {
            openListActivity()
        }

        findViewById<Button>(R.id.show_dialog).setOnClickListener {
            showDialog()
        }

        findViewById<Button>(R.id.show_snack_bar).setOnClickListener {
            showAppSnackbar()
        }

        findViewById<Button>(R.id.startTimer).setOnClickListener {
            startTimer()
        }

        Glide.with(this)
            .load(FIFI)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(findViewById(R.id.imageView))

        val tv_counter = findViewById<TextView>(R.id.tv_counter)
        viewModel.timerLiveDate.observe(this) { millis ->
            tv_counter.setText(millis.toString())

            if (millis == 0L)
                loadImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            findViewById<ImageView>(R.id.imageView).setImageBitmap(imageBitmap)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Calling this method will open the default camera application.
     */
    private fun openNativeCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    /**
     * Calling this method will open a new activity.
     */
    private fun openListActivity() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    /**
     * Show a dialog
     */
    private fun showDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle(R.string.dialog_title)
        builder.setMessage(R.string.dialog_message)

        builder.apply {
            setPositiveButton(R.string.ok) { _, _ ->
                Toast.makeText(this@MainActivity, R.string.dialog_on_ok, Toast.LENGTH_SHORT).show()
            }
            setNegativeButton(R.string.cancel) { _, _ ->
                Toast.makeText(this@MainActivity, R.string.dialog_on_cancel, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        builder.create().show()
    }

    /**
     * Show a SnackBar
     */
    private fun showAppSnackbar() {
        Snackbar.make(
            findViewById<ConstraintLayout>(R.id.container),
            R.string.snackbar_message,
            Snackbar.LENGTH_SHORT
        ).setAction(R.string.snackbar_thanks) {
            Toast.makeText(this@MainActivity, R.string.snackbar_thanks_clicked, Toast.LENGTH_SHORT)
                .show()
        }.show()
    }

    private fun startTimer() {

        if (!checkPermissionAndRequest())
            return

        viewModel.startTime(5000)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_READ_STORAGE) {
            if (permissions[0] == Manifest.permission.READ_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startTimer()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


    private fun checkPermissionAndRequest(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_READ_STORAGE
            )

            return false
        }

        return true
    }

    private fun loadImage() {
        val file = File("/storage/emulated/0/Download/dog.jpg")

        val uri = Uri.fromFile(file)

        findViewById<ImageView>(R.id.imageView).setImageURI(uri)
    }
}