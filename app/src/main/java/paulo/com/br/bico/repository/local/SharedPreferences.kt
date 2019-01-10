package paulo.com.br.bico.repository.local

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class SharedPreferences {

    companion object {
        fun save(context: Context, name: String, value: String) {
            val shared = getShared(context)
            val editor = shared.edit()
            editor.putString(name, value)

            // Commit the edits!
            editor.commit()
        }

        fun saveBoolean(context: Context, name: String, value: Boolean) {
            val shared = getShared(context)
            val editor = shared.edit()
            editor.putBoolean(name, value)

            // Commit the edits!
            editor.commit()
        }

        fun saveInt(context: Context, name: String, value: Int) {
            val shared = getShared(context)
            val editor = shared.edit()
            editor.putInt(name, value)

            // Commit the edits!
            editor.commit()
        }

        fun saveDate(context: Context, name: String, value: Date) {
            val shared = getShared(context)
            val editor = shared.edit()
            editor.putLong(name, value.time)

            // Commit the edits!
            editor.commit()
        }

        fun saveDateTodayPlusDays(context: Context, name: String, dias: Int) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, dias)

            saveDate(context, name, calendar.time)
        }


        fun get(context: Context, name: String): String {
            val shared = getShared(context)
            return shared.getString(name, "")
        }

        fun getInt(context: Context, name: String): Int {
            val shared = getShared(context)
            return shared.getInt(name, 0)
        }

        fun getBoolean(context: Context, name: String): Boolean {
            val shared = getShared(context)
            return shared.getBoolean(name, false)
        }

        fun getBoolean(context: Context, name: String, default: Boolean): Boolean {
            val shared = getShared(context)
            return shared.getBoolean(name, default)
        }

        fun getDate(context: Context, name: String): Date? {
            val shared = getShared(context)
            val longDate = shared.getLong(name, 0)

            if ( longDate <= 0 )
                return null

            return Date( longDate )
        }


        private fun getShared(context: Context): SharedPreferences {
            return context.getSharedPreferences("bicoPreferences", 0)
        }
    }

}