package paulo.com.br.bico.repository.local

import androidx.room.Dao
import androidx.room.Query
import paulo.com.br.bico.entity.CategorieService
import paulo.com.br.bico.entity.CategorieServiceFull

@Dao
interface CategorieServiceLocalRepository: LocalCrudRepository<CategorieService> {

    @Query("SELECT * FROM CategorieService")
    fun all(): List<CategorieServiceFull>

}