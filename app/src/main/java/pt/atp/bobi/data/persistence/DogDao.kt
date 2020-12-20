package pt.atp.bobi.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDao {

    @Query("select * from dog order by name asc")
    fun getDogs(): List<Dog>

    @Query("select * from dog where id = :id")
    fun getDogById(id:String): LiveData<Dog>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dog: Dog)

    @Query("delete from dog")
    fun deleteAll()

    @Query("delete from dog where id = :id")
    fun deleteById(id:String)

}