package pt.atp.bobi.presentation.ui.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import pt.atp.bobi.EXTRA_USERNAME
import pt.atp.bobi.R
import java.io.File

private const val REQUEST_IMAGE_CAPTURE = 100
private const val REQUEST_READ_STORAGE = 200

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username =
            activity?.intent?.extras?.getString(EXTRA_USERNAME, getString(R.string.welcome_default))

        view.findViewById<TextView>(R.id.tv_hello).setText(username)

        view.findViewById<Button>(R.id.open_camera).setOnClickListener {
            openNativeCamera()
        }

        view.findViewById<Button>(R.id.show_dialog).setOnClickListener {
            showDialog()
        }

        view.findViewById<Button>(R.id.show_snackbar).setOnClickListener {
            showAppSnackbar()
        }

        view.findViewById<Button>(R.id.startTimer).setOnClickListener {
            startTimer()
        }

        Glide.with(this)
            .load("https://github.com/android-training-program/aula-5/blob/master/imagens/fifi.jpg?raw=true")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(view.findViewById(R.id.imageView))

        val tvStartTimer = view.findViewById<TextView>(R.id.tv_counter)

        viewModel.timerLiveDate.observe(viewLifecycleOwner) { count ->
            tvStartTimer.text = count.toString()

            if (count == 0L)
                loadImage()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_READ_STORAGE) {
            if (permissions[0] == Manifest.permission.READ_EXTERNAL_STORAGE &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                startTimer()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            requireView().findViewById<ImageView>(R.id.imageView).setImageBitmap(imageBitmap)
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
     * Show a dialog
     */
    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle(R.string.dialog_title)
        builder.setMessage(R.string.dialog_message)

        builder.apply {
            setPositiveButton(R.string.ok) { _, _ ->
                Toast.makeText(requireContext(), R.string.dialog_on_ok, Toast.LENGTH_SHORT).show()
            }
            setNegativeButton(R.string.cancel) { _, _ ->
                Toast.makeText(requireContext(), R.string.dialog_on_cancel, Toast.LENGTH_SHORT)
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
            requireView(),
            R.string.snackbar_message,
            Snackbar.LENGTH_SHORT
        ).setAction(R.string.snackbar_thanks) {
            Toast.makeText(requireContext(), R.string.snackbar_thanks_clicked, Toast.LENGTH_SHORT)
                .show()
        }.show()
    }

    private fun startTimer() {

        if (!checkPermissionAndRequest())
            return

        viewModel.startTime(5000)
    }

    private fun checkPermissionAndRequest(): Boolean {

        if (ContextCompat
                .checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {

            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_READ_STORAGE
            )

            return false
        }

        return true
    }

    private fun loadImage() {

        val file = File("/storage/emulated/0/Download/dog.jpg")

        val uri = Uri.fromFile(file)

        val imageView = requireView().findViewById<ImageView>(R.id.imageView)
        imageView.setImageURI(uri)
    }
}