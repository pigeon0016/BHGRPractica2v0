package com.example.bhgrpractica2v0

import adapter.equiposadapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.bhgrpractica2v0.databinding.ActivityMainBinding
import db.dbequipos
import model.Equipos

class MainActivity : AppCompatActivity() {                          // NO CAMBIA

    // se crea una variable global para el uso de viewbinding
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaequipos: ArrayList<Equipos>


    override fun onCreate(savedInstanceState: Bundle?) {            // NO CAMBIA
        super.onCreate(savedInstanceState)                          // NO CAMBIA


        binding = ActivityMainBinding.inflate(layoutInflater)      // se requiere cuando se usa binding
        setContentView(binding.root)                                 // se requiere cuando se usa binding



         val dbequipos = dbequipos(this)
        listaequipos = dbequipos.verEquipos()

        if(listaequipos.size == 0) binding.lvsinregistros.visibility = View.VISIBLE
        else binding.lvsinregistros.visibility = View.INVISIBLE

        val equiposadapter = equiposadapter(this,listaequipos)

        binding.lvequipos.adapter = equiposadapter
        binding.lvequipos.setOnItemClickListener { adapterView, view, i, l ->
            //l es el id
            //i es la posición
            val intent = Intent(this, DetallesActividad::class.java)
            intent.putExtra("ID", l.toInt())

            startActivity(intent)
            finish()
        }
    }

    fun click1(view: View) {
        //eventos del clicl fltante
        startActivity(Intent(this, InsertActivity::class.java)) // Te manda a la actividad de insert Activity
    }
}