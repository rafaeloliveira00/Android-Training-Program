package pt.atp.bobi.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.atp.bobi.EXTRA_DOG_BREED
import pt.atp.bobi.EXTRA_DOG_NAME
import pt.atp.bobi.R
import pt.atp.bobi.data.DogsAPIClient
import pt.atp.bobi.data.callback.DataRetriever
import pt.atp.bobi.data.model.Breed

private const val TAG = "ListActivity"

class ListActivity : AppCompatActivity(), DataRetriever {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        findViewById<RecyclerView>(R.id.rv_breeds).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = BreedsAdapter {
                openDetailsScreen(it)
            }
        }
        DogsAPIClient.getListOfBreeds(this)
    }

    private fun openDetailsScreen(breed: Breed) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_DOG_NAME, breed.name)
        intent.putExtra(EXTRA_DOG_BREED, breed.id)
        startActivity(intent)
    }

    override fun onDataFetchedSuccess(breeds: List<Breed>) {
        val adapter = findViewById<RecyclerView>(R.id.rv_breeds).adapter as BreedsAdapter
        adapter.submitList(breeds)
    }

    override fun onDataFetchedFailed() {
        Log.d(TAG, "onDataFetchedFailed")
    }
}