package pt.atp.bobi.data.callback

import pt.atp.bobi.data.model.Breed

interface DataRetriever {
    fun onDataFetchedSuccess(breeds: List<Breed>)

    fun onDataFetchedFailed()
}