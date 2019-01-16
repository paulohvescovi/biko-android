
package paulo.com.br.bico.dao

import androidx.room.Dao
import androidx.room.Query
import paulo.com.br.bico.entity.User
import paulo.com.br.bico.repository.local.LocalCrudRepository

@Dao
interface UserLocalRepository : LocalCrudRepository<User> {

    @Query("SELECT * FROM User where id = 1")
    fun find(): User

}