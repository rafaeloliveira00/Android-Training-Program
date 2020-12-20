package pt.atp.bobi.data.persistence

class DogRepository(private val dogDao: DogDao) {

    fun getDogs(onLoaded: (List<Dog>) -> Unit) {
        KennelDatabase
            .databaseWriteExecutor
            .execute {
                onLoaded(dogDao.getDogs())
            }
    }

    fun insert(dog: Dog) {
        KennelDatabase
            .databaseWriteExecutor
            .execute {
                dogDao.insert(dog)
            }
    }

    fun deleteById(id: String, onLoaded: (Unit) -> Unit) {
        KennelDatabase
            .databaseWriteExecutor
            .execute {
                dogDao.deleteById(id)
                onLoaded(Unit)
            }
    }
}