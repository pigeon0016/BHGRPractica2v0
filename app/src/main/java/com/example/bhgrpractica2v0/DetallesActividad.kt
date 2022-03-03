package com.example.bhgrpractica2v0

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import com.example.bhgrpractica2v0.databinding.ActivityDetallesActividadBinding
import db.dbequipos
import model.Equipos
import java.util.*

class DetallesActividad : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesActividadBinding
    private lateinit var dbequipos: dbequipos
    private lateinit var paises: ArrayAdapter<String> //spiner

    var equipos: Equipos? = null
    var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /////////////////////////////////////////////////spiner conf
        //se crea el array adapter

        paises = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item)
        //decirle a la vista q esto es un adapter
        binding.paiseslista.adapter = paises
        //llenamos el spinner
        paises.addAll(Arrays.asList("ESCOGE UN PAIS","Alemania","Argentina","Inglaterra","Mexico","España","Brasil","Otro"))

        //no usamos aca la parte de edicion del spinner
        //para manipular la seleccion
        //binding.paiseslista.onItemSelectedListener = this
        //binding.paiseslista.adapter = paises
        /////////////////////////////////////////////spinner final




        ///////////////////////////////////////////////////////////////////////////
        if(savedInstanceState == null){
            val bundle = intent.extras
            if(bundle != null){
              id = bundle.getInt("ID",0)
            }
        }else{
            id = savedInstanceState.getSerializable("ID") as Int
        }

        dbequipos = dbequipos(this)
        equipos = dbequipos.verEquipo(id)

        //Toast.makeText(this, "$lugar", Toast.LENGTH_LONG).show()
        /////////////////////////////////////////////////////////////////////////////

        if(equipos != null){
            with(binding){
                tietNombre.setText(equipos?.Nombre)
                tietFundacion.setText(equipos?.Fundacion)
                equipos?.idarray?.let { paiseslista.setSelection(it) } //con este se logra que cargue el pais en el spinner
               // paiseslista.setSelection(equipos?.Pais)
                ///////////////////pone la bandera arriba
                var temppais = equipos?.Pais
                if(temppais == "Alemania"){binding.ivcountry.setImageResource(R.drawable.flagale)}
                if(temppais == "Argentina"){binding.ivcountry.setImageResource(R.drawable.flagarg)}
                if(temppais == "Inglaterra"){binding.ivcountry.setImageResource(R.drawable.flageng)}
                if(temppais == "Mexico"){binding.ivcountry.setImageResource(R.drawable.flasgmex)}
                if(temppais == "España"){binding.ivcountry.setImageResource(R.drawable.flagesp)}
                if(temppais == "Brasil"){binding.ivcountry.setImageResource(R.drawable.flagbra)}
                if(temppais == "Otro"){binding.ivcountry.setImageResource(R.drawable.ball)}
                //////////////// fin de pone la bandera

                tietNombre.inputType = InputType.TYPE_NULL
                tietFundacion.inputType = InputType.TYPE_NULL

                //¿COMO SE BLOQUEA EL ARRAY?


            }
        }
        ////////////////////////////////////////////////////////////////////////////////

    }
         /////////////////////// al picar el boton de editar o borrar
    fun click03(view: View) {
        when(view.id){
            R.id.btnEdit -> {
                val intent = Intent(this, EditarActivity::class.java)
                intent.putExtra("ID",id)
                startActivity(intent)
                finish()
            }
            R.id.btnDelete -> {
                AlertDialog.Builder(this)
                    .setTitle("Confirmacion")
                    .setMessage("De verdad quieres eliminar el registro ${equipos?.Nombre}?")
                    .setPositiveButton("Si",DialogInterface.OnClickListener{ dialogInterface, i ->
                        if(dbequipos.IMACBaja(id)){
                            Toast.makeText(this, "Registro borrado", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                        //Si se desea realizar alguna acción cuando el usuario selecciona NO
                    })
                    .show()
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }



   // override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {    }

    //override fun onNothingSelected(p0: AdapterView<*>?) {    }



}
