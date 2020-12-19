package pt.atp.bobi.presentation.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.atp.bobi.data.DogsAPIClient
import pt.atp.bobi.data.callback.DataRetriever
import pt.atp.bobi.data.model.Breed
import pt.atp.bobi.data.persistence.DogModel
import pt.atp.bobi.data.persistence.DogRepository

class BreedsViewModel(
    private val repository: DogRepository
) : ViewModel(), DataRetriever {

    private val _dogsViewModel = MutableLiveData<List<Breed>>()
    val dogsLiveData = _dogsViewModel

    init {
        repository.insert(DogModel(1, "breedfor", "group", 50, "name1", "unknown", "medium"))
        repository.insert(DogModel(2, "breedfor", "group", 50, "name2", "unknown", "medium"))
        repository.insert(DogModel(3, "breedfor", "group", 50, "name3", "unknown", "medium"))
    }

    fun loadDogs() {
        DogsAPIClient.getListOfBreeds(this)
    }

    fun loadDogsDatabase(): LiveData<List<DogModel>> {
        return repository.allDogs
    }

    override fun onDataFetchedSuccess(breeds: List<Breed>) {
        _dogsViewModel.postValue(breeds)
    }

    override fun onDataFetchedFailed() {
        _dogsViewModel.postValue(emptyList())
    }
}

class BreedsViewModelFactory(
    private val repository: DogRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BreedsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BreedsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}