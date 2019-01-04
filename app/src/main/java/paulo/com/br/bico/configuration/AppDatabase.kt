package paulo.com.br.bico.configuration


import android.os.Parcelable
import androidx.room.Database
import androidx.room.RoomDatabase
import paulo.com.br.bico.dao.UsuarioLocalRepository
import paulo.com.br.bico.entity.Usuario

@Database(entities = arrayOf(Usuario::class), version = 1)
abstract class AppDatabase() : RoomDatabase(), Parcelable {

    abstract fun usuarioDao(): UsuarioLocalRepository

}