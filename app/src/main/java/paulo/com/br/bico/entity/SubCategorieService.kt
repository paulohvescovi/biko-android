package paulo.com.br.bico.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubCategoriaService(@ColumnInfo(name = "descricao") var descricao: String? = null,
                               @ColumnInfo(name = "descricaoDetalhada") var descricaoDetalhada: String? = null,
                               @ColumnInfo(name = "imageUrl") var imageUrl: String? = null,
                               @ColumnInfo(name = "categoriaId") var categoriaId: Int? = null,
                               @PrimaryKey var id: Int? = null



)