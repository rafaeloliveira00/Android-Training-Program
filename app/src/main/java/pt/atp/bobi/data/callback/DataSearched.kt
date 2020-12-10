package pt.atp.bobi.data.callback

import pt.atp.bobi.data.model.Search

interface DataSearched {

    fun onDataSearchedSuccess(search: List<Search>)

    fun onDataSearchedFailed()
}