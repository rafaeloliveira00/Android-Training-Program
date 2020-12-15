package pt.atp.bobi.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.atp.bobi.EXTRA_DOG_BREED
import pt.atp.bobi.EXTRA_DOG_NAME
import pt.atp.bobi.R
import pt.atp.bobi.data.model.Breed
import pt.atp.bobi.presentation.BreedsViewModel

private const val TAG = "ListActivity"

class ListActivity : AppCompatActivity() {

    private val viewModel: BreedsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setup()
        viewModel.loadDogs()
    }

    private fun setup() {
        findViewById<RecyclerView>(R.id.rv_breeds).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = BreedsAdapter {
                openDetailsScreen(it)
            }
        }

        viewModel.dogsLiveData.observe(this) {
            val adapter =
                findViewById<RecyclerView>(R.id.rv_breeds).adapter as BreedsAdapter
            adapter.submitList(it)
        }

//        viewModel.loadDogsDatabase().observe(this) {
//            Log.d(TAG, "$it")
//        }
    }

    private fun openDetailsScreen(breed: Breed) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_DOG_NAME, breed.name)
        intent.putExtra(EXTRA_DOG_BREED, breed.id)
        startActivity(intent)
    }
}