package pt.atp.bobi.data

import android.util.Log
import pt.atp.bobi.data.callback.DataRetriever
import pt.atp.bobi.data.callback.DataSearched
import pt.atp.bobi.data.model.Breed
import pt.atp.bobi.data.model.Search
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG = "DogsAPIClient"

object DogsAPIClient {

    private val apiDog by lazy {
        setup()
    }

    private fun setup(): DogsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    fun getListOfBreeds(listener: DataRetriever) {
        apiDog.getBreedsList().enqueue(object : Callback<List<Breed>> {
            override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    listener.onDataFetchedSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                Log.d(TAG, "onRFailure: ${t.message}")
                listener.onDataFetchedFailed()
            }
        })
    }

    fun getBreedImage(breedId: String, listener: DataSearched) {
        apiDog.getBreedImage("1", breedId).enqueue(object : Callback<List<Search>> {
            override fun onResponse(call: Call<List<Search>>, response: Response<List<Search>>) {
                if (response.isSuccessful)
                    listener.onDataSearchedSuccess(response.body()!!)
                else
                    Log.e(TAG, "code: " + response.code())
            }

            override fun onFailure(call: Call<List<Search>>, t: Throwable) {
                Log.e(TAG, "Unable to get breed image. Error: ${t.message}")
                listener.onDataSearchedFailed()
            }

        })
    }
}