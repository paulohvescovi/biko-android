package paulo.com.br.bico.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@ColumnInfo(name = "token") var token: String? = null,
                @ColumnInfo(name = "email") var email: String? = null,
                @ColumnInfo(name = "senha") var senha: String? = null,
                @ColumnInfo(name = "nome") var nome: String? = null,
                @ColumnInfo(name = "sobrenome") var sobrenome: String? = null,
                @PrimaryKey var id: Int? = null)