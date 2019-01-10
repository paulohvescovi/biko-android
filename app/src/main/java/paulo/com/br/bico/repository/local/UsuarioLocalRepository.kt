
package paulo.com.br.bico.dao

import androidx.room.Dao
import androidx.room.Query
import paulo.com.br.bico.entity.Usuario
import paulo.com.br.bico.repository.local.LocalCrudRepository

@Dao
interface UsuarioLocalRepository : LocalCrudRepository<Usuario> {

    @Query("SELECT * FROM Usuario where id = 1")
    fun find(): Usuario

}