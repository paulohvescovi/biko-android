package paulo.com.br.bico.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface LocalCrudRepository<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: MutableList<T>)

    @Delete
    fun delete(entity: T)

    @Delete
    fun delete(entity: MutableList<T>)


}