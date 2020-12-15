package pt.atp.bobi.data.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dog")
class DogModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "bred_for")
    val bredFor: String,
    @ColumnInfo(name = "bred_group")
    val breedGroup: String,
    @ColumnInfo(name = "life_span")
    val lifeSpan: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "origin")
    val origin: String?,
    @ColumnInfo(name = "temperament")
    val temperament: String
)