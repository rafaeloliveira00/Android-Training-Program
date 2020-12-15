package pt.atp.bobi.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DogDao {

    @Query("select * from dog order by id asc")
    fun getAlphabetizedDogs(): LiveData<List<DogModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dog: DogModel)

    @Query("delete from dog")
    fun deleteAll()
}