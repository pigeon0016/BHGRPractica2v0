package com.example.bhgrpractica2v0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.bhgrpractica2v0.databinding.ActivityDetallesActividadBinding
import com.example.bhgrpractica2v0.databinding.ActivityEditarBinding
import db.dbequipos
import model.Equipos
import java.util.*

class EditarActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityEditarBinding
    private lateinit var dbequipos: dbequipos
    private lateinit var paises: ArrayAdapter<String> //spiner

    var equipos: Equipos? = null
    var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarBinding.inflate(layoutInflater)
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
        binding.paiseslista.onItemSelectedListener = this
        binding.paiseslista.adapter = paises
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






        //////////////////////////////////////////////////////////////////// este codigo posiciona los valores q tenian en elotro activity

        if(equipos != null){

            with(binding){
                tietNombre.setText(equipos?.Nombre)
                tietFundacion.setText(equipos?.Fundacion)
                equipos?.idarray?.let { paiseslista.setSelection(it) } //con este se logra que cargue el pais en el spinner
                // paiseslista.setSelection(equipos?.Pais)
                ///////////////////pone la bandera arriba
               val temppais = equipos?.Pais
                if(temppais == "Alemania"){binding.ivcountry.setImageResource(R.drawable.flagale)}
                if(temppais == "Argentina"){binding.ivcountry.setImageResource(R.drawable.flagarg)}
                if(temppais == "Inglaterra"){binding.ivcountry.setImageResource(R.drawable.flageng)}
                if(temppais == "Mexico"){binding.ivcountry.setImageResource(R.drawable.flasgmex)}
                if(temppais == "España"){binding.ivcountry.setImageResource(R.drawable.flagesp)}
                if(temppais == "Brasil"){binding.ivcountry.setImageResource(R.drawable.flagbra)}
                if(temppais == "Otro"){binding.ivcountry.setImageResource(R.drawable.ball)}
                //////////////// fin de pone la bandera
                //¿COMO SE BLOQUEA EL ARRAY?


            }
        }
        ////////////////////////////////////////////////////////////////////////////////





        //////////////////////////////////////////////////////////////////////////////////////





    }

    fun click04(view: View) {
       // Toast.makeText(this, "${binding.tietNombre.text}", Toast.LENGTH_LONG).show()

        with(binding){
            if(!tietNombre.text.toString().isEmpty() && !tietFundacion.text.toString().isEmpty() && !country.isEmpty() ){
             if(dbequipos.IMACAtualiza(id, tietNombre.text.toString(), tietFundacion.text.toString(), country,lugar))   {
                 Toast.makeText(this@EditarActivity, "Registro actualizado exitosamente", Toast.LENGTH_LONG).show()
                 val intent = Intent(this@EditarActivity, DetallesActividad::class.java)
                 intent.putExtra("ID", id)
                 startActivity(intent)
                 finish()
             }else{
                 Toast.makeText(this@EditarActivity, "Error al actualizar el registro", Toast.LENGTH_LONG).show()
             }
        }else{
                Toast.makeText(this@EditarActivity, "Ningún campo puede quedar vacío", Toast.LENGTH_LONG).show()
        }
    }
}


    /////////////////////////////////////
    //ACCIONES Q PASAN AL SELECCIONAR UNA OPCION ---------DEL SPINNER
    var country : String = ""
    var lugar : Int = 0
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var paisselected = paises.getItem(p2)
        country = paisselected.toString()
        lugar = p2.toInt()


        if(country == "Alemania"){binding.ivcountry.setImageResource(R.drawable.flagale)}
        if(country == "Argentina"){binding.ivcountry.setImageResource(R.drawable.flagarg)}
        if(country == "Inglaterra"){binding.ivcountry.setImageResource(R.drawable.flageng)}
        if(country == "Mexico"){binding.ivcountry.setImageResource(R.drawable.flasgmex)}
        if(country == "España"){binding.ivcountry.setImageResource(R.drawable.flagesp)}
        if(country == "Brasil"){binding.ivcountry.setImageResource(R.drawable.flagbra)}
        if(country == "Otro"){binding.ivcountry.setImageResource(R.drawable.ball)}

        //Toast.makeText(this, "$lugar", Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
///////////////////////////////////////////////////////////////////////////////
}