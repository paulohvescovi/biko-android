package paulo.com.br.bico.entity

import androidx.room.Embedded
import androidx.room.Relation


data class CategorieServiceFull(

    @Embedded
    var category: CategorieService? = null,

    @Relation(parentColumn = "id", entityColumn = "categoriaId", entity = SubCategoriaService::class)
    var subCategoryList: List<SubCategoriaService>? = null

)
{
    fun descricao() = category?.descricao
}