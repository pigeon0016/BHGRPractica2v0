package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.bhgrpractica2v0.R
import model.Equipos
import com.example.bhgrpractica2v0.databinding.ListaEquiposBinding

class equiposadapter(contexto: Context, listaequipos: ArrayList<Equipos>): BaseAdapter() {
    private val listaequipos = listaequipos
    private val layoutInflater = LayoutInflater.from(contexto)

    override fun getCount(): Int {
       return listaequipos.size
    }

    override fun getItem(p0: Int): Any {
        return listaequipos[p0]
    }

    override fun getItemId(p0: Int): Long {
        return listaequipos[p0].id.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
      val binding = ListaEquiposBinding.inflate(layoutInflater)

        with(binding){
            tvnombre.text = listaequipos[p0].Nombre
            tvfundacion.text = listaequipos[p0].Fundacion
            tvpais.text = listaequipos[p0].Pais
            if(tvpais.text == "Alemania"){binding.ivbanderas.setImageResource(R.drawable.flagale)}
            if(tvpais.text == "Argentina"){binding.ivbanderas.setImageResource(R.drawable.flagarg)}
            if(tvpais.text == "Inglaterra"){binding.ivbanderas.setImageResource(R.drawable.flageng)}
            if(tvpais.text == "Mexico"){binding.ivbanderas.setImageResource(R.drawable.flasgmex)}
            if(tvpais.text == "España"){binding.ivbanderas.setImageResource(R.drawable.flagesp)}
            if(tvpais.text == "Brasil"){binding.ivbanderas.setImageResource(R.drawable.flagbra)}
            if(tvpais.text == "Otro"){binding.ivbanderas.setImageResource(R.drawable.ball)}



         //   ivbanderas.setImageResource(flagale)
        }
        return binding.root
    }


}