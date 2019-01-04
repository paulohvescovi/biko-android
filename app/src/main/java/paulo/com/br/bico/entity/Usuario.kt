package paulo.com.br.bico.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import paulo.com.br.bico.enumeration.TipoUsuario

@Entity
data class Usuario(@ColumnInfo(name = "email") var email: String? = null,
                   @ColumnInfo(name = "senha") var senha: String? = null,
                   @PrimaryKey var id: Int? = null)