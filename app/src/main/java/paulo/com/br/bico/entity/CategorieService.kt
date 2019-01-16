package paulo.com.br.bico.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CategorieService(@ColumnInfo(name = "descricao") var descricao: String? = null,
                       @PrimaryKey var id: Int? = null
)