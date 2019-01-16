package paulo.com.br.bico.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategorieService(@ColumnInfo(name = "descricao") var descricao: String? = null,
                            @Transient var subCategoriaList: List<SubCategoriaService>? = null,
                            @PrimaryKey var id: Int? = null
)