package paulo.com.br.bico.repository.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import paulo.com.br.bico.configuration.Constants
import paulo.com.br.bico.dao.UserLocalRepository
import paulo.com.br.bico.entity.CategorieService
import paulo.com.br.bico.entity.SubCategoriaService
import paulo.com.br.bico.entity.User

@Database(entities = arrayOf(User::class, CategorieService::class, SubCategoriaService::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UserLocalRepository
    abstract fun categorieServiceDao(): CategorieServiceLocalRepository

    companion object {


        internal val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        /*static final Migration MIGRATION_1_3 = new Migration(1, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE usuario ADD COLUMN prospect NUMBER");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE usuario ADD COLUMN prospect NUMBER");
        }
    };


    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE usuario ADD COLUMN ehProspect INTEGER");
        }
    };*/


    operator fun get(context: Context): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                Constants.DATABASE
        )
                .addMigrations(
                        MIGRATION_1_2
                )
                .build()
    }

    fun getAllowMainThreadQueries(context: Context): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                Constants.DATABASE
        )
                .addMigrations(
                        MIGRATION_1_2
                )
                .allowMainThreadQueries()
                .build()
    }
    }

}