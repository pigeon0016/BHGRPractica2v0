package db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import model.Equipos

class dbequipos(context: Context?) : DBHelper(context) {
    //codigo del CRUD


    val context = context

    ///////////////////////////ALTA/////////////////////////
    fun IMACAlta(Nombre: String, Fundacion: String, Pais: String, idarray: Int): Long {
        val dbhelper = DBHelper(context)
        val db = dbhelper.writableDatabase
        var id: Long = 0

        try{
            val valores = ContentValues()
            valores.put("nombre",Nombre)
            valores.put("fundacion",Fundacion)
            valores.put("pais",Pais)
            valores.put("idarray",idarray)
            // ojo aqui los valores de Nombre, Fund etc tienen q ser iguales a las variables que determibamos en la funcion
            id = db.insert(TABLE_EQUIPOS,null,valores)
        }catch(e: Exception){

        }finally{
            db.close()
        }
        return id
    }
    //////////////////////// TERMINA EL ALTA////////////////////////////////////


    /////////////// obtener equipos ////////////// obtienes todos

    fun verEquipos(): ArrayList<Equipos> {  /// jala de model la clase equipos
        val dbhelper = DBHelper(context)
        val db = dbhelper.writableDatabase

        var listaequipos = ArrayList<Equipos>()
        var equipoTEMP: Equipos? = null
        var cursorusuario: Cursor? = null

        cursorusuario = db.rawQuery("SELECT * FROM $TABLE_EQUIPOS", null)

        if (cursorusuario.moveToFirst()) {
            do {
                equipoTEMP = Equipos(
                    cursorusuario.getInt(0),
                    cursorusuario.getString(1),
                    cursorusuario.getString(2),
                    cursorusuario.getString(3),
                    cursorusuario.getInt(4)
                )
                listaequipos.add(equipoTEMP)
            } while (cursorusuario.moveToNext())
        }
        cursorusuario.close()
        return listaequipos
    }
        /////////////// obtener equipos FIN///////////////////////////////// aqui solo obntienes 1 equipo


        ////////////Obtener equipo
        fun verEquipo(id: Int): Equipos?{
            val dbhelper = DBHelper(context)
            val db = dbhelper.writableDatabase

            var equipoTEMP: Equipos? = null
            var cursorusuario: Cursor? = null

            cursorusuario = db.rawQuery("SELECT * FROM $TABLE_EQUIPOS WHERE id = $id LIMIT 1", null)

            if(cursorusuario.moveToFirst()){
                equipoTEMP = Equipos(cursorusuario.getInt(0),cursorusuario.getString(1),cursorusuario.getString(2),cursorusuario.getString(3),cursorusuario.getInt(4))
            }

            cursorusuario.close()
            return equipoTEMP

        }

       /////////////////////////// fin obtener equipo

       /////////////////// funcion de actualizar equipo
      fun IMACAtualiza (id: Int,Nombre: String, Fundacion: String, Pais: String, idarray: Int): Boolean{
          var banderaCorrecto = false

           val dbhelper = DBHelper(context)
           val db = dbhelper.writableDatabase

           try{
               db.execSQL("UPDATE $TABLE_EQUIPOS SET Nombre = '$Nombre', Fundacion = '$Fundacion', Pais = '$Pais', idarray = '$idarray', WHERE id = $id ")
               banderaCorrecto = true
           }catch (e: Exception){

           }finally{
               db.close()
           }
           return banderaCorrecto

      }
    ///////////////////////// FIN de actualizar equipo

    //////////////////////////////////////// funicon de borrar un equipo
    fun IMACBaja(id: Int): Boolean{
        var flagValidacion = false

        val dbhelper = DBHelper(context)
        val db = dbhelper.writableDatabase

        try{
            db.execSQL("DELETE FROM $TABLE_EQUIPOS WHERE id = $id")
            flagValidacion = true
        }catch (e: Exception){

        }finally{
            db.close()
        }
        return flagValidacion
    }
//////////////// FIN BAJA

    }






