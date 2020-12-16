package pt.atp.bobi.presentation.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.atp.bobi.data.DogsAPIClient
import pt.atp.bobi.data.callback.DataRetriever
import pt.atp.bobi.data.model.Breed

class BreedsViewModel(
) : ViewModel(), DataRetriever {

    private val _dogsViewModel = MutableLiveData<List<Breed>>()
    val dogsLiveData = _dogsViewModel

    fun loadDogs() {
        DogsAPIClient.getListOfBreeds(this)
    }

//    fun loadDogsDatabase(): LiveData<List<DogModel>> {
//        return repository.allDogs
//    }

    override fun onDataFetchedSuccess(breeds: List<Breed>) {
        _dogsViewModel.postValue(breeds)
    }

    override fun onDataFetchedFailed() {
        _dogsViewModel.postValue(emptyList())
    }
}

//class BreedsViewModelFactory(
//    private val repository: DogRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BreedsViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return BreedsViewModel(repository) as T
//        }
//
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}