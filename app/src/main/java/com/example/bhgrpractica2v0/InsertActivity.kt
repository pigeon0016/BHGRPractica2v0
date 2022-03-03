package com.example.bhgrpractica2v0

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bhgrpractica2v0.databinding.ActInsertBinding
import db.dbequipos
import java.util.*

class InsertActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActInsertBinding // se relaciona con el layout act_insert
    private lateinit var paises: ArrayAdapter<String>  // variable global para el uso del array donde se cargara la info para el spinner



    // configuracion del binding///////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActInsertBinding.inflate(layoutInflater) // ve como se lrelaciona con el ACTinsert
        setContentView(binding.root)
        ///////////////////////////////7


        //se crea el array adapter
        paises = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item)
        //decirle a la vista q esto es un adapter
        binding.paiseslista.adapter = paises
        //llenamos el spinner
        paises.addAll(Arrays.asList("ESCOGE UN PAIS","Alemania","Argentina","Inglaterra","Mexico","España","Brasil","Otro"))
        //para manipular la seleccion
        binding.paiseslista.onItemSelectedListener = this
        binding.paiseslista.adapter = paises




    }


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

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
  //////////////////////////////////////////////////////////////////////////////////////


    ////////////// aqui se programna lo que se debe de hacer cuando el usuario oprime el boton agregar
    fun click02 (view: View) {
        val dbequipos = dbequipos(this)

        //binding.paiseslista.onItemSelectedListener.toString()

        with(binding){
//!country.isEmpty()

            if(!tietNombre.text.toString().isEmpty() && !tietFundacion.text.toString().isEmpty() && !country.isEmpty() ){
                val id = dbequipos.IMACAlta(tietNombre.text.toString(),tietFundacion.text.toString(),country,lugar)

                if(id > 0){ // validar si se dio de alta correctament
                    Toast.makeText(this@InsertActivity,"Registro guardado correctamjente",Toast.LENGTH_LONG).show()

                    //Reiniciaos las cajas de texto
                    tietNombre.setText("")
                    tietFundacion.setText("")
                    tietNombre.requestFocus()

                }else{
                    Toast.makeText(this@InsertActivity,"Error al guardar el registroe",Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(this@InsertActivity,"por favor llene todos los campos",Toast.LENGTH_LONG).show()
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }


}