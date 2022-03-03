package db
/////////////////////////////////////////// se crea la clase tipo data
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.telephony.TelephonyCallback

open class DBHelper(context: Context?) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    override fun onCreate(p0: SQLiteDatabase?) {
       p0?.execSQL("CREATE TABLE $TABLE_EQUIPOS (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, fundacion TEXT NOT NULL,pais TEXT NOT NULL,idarray INTEGER NOT NULL)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
       p0?.execSQL("DROP TABLE $TABLE_EQUIPOS")
        onCreate(p0)
    }

          //static
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "equiposfut.db"
              public const val TABLE_EQUIPOS = "equipos"
    }
}
