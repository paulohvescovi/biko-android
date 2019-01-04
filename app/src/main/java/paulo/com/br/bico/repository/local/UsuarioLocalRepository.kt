
package paulo.com.br.bico.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import paulo.com.br.bico.entity.Usuario

@Dao
interface UsuarioLocalRepository {

    @Query("DELETE FROM Usuario where id = 1")
    fun delete()

    @Query("SELECT * FROM Usuario where id = 1")
    fun find(): Usuario

    @Query("SELECT * FROM Usuario")
    fun findAll(): List<Usuario>

    @Update
    fun update(usuario : Usuario)

    @Insert
    fun insert(usuario : Usuario)

}