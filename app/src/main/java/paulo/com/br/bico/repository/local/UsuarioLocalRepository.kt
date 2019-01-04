
package paulo.com.br.bico.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import paulo.com.br.bico.entity.Usuario

@Dao
interface UsuarioLocalRepository {

    @Query("SELECT * FROM Usuario u where u.usuario = :userid limit 1")
    fun findByUserId(userid: String): List<Usuario>

    @Delete
    fun delete(usuario: Usuario)
}