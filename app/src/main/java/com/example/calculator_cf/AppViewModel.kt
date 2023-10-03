package com.example.calculator_cf

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.merge
import kotlin.reflect.typeOf

class AppViewModel : ViewModel() {

    //LiveData CF
    private var _liveCF = MutableLiveData("")
    var live_CF: MutableLiveData<String> = _liveCF
        get() = _liveCF
    fun setCF(parameter : String){

        var parameter = parameter

        Log.d("p" , "$parameter")

        if(_liveCF == MutableLiveData("")){
            _liveCF.value = parameter
            Log.d("Caso vuoto" , "caso vuoto linea 28")
        } else {
             _liveCF.value = _liveCF.value + parameter
        }
    }



    init{
       // _liveCF.value  =  "XXXXXXXXXXXXXXX"
    }

   /* fun UpdateLiveData(result: String) {
        if (live_CF.value!!.isEmpty()) {
            if (live_CF.value == _liveCF.value) _liveCF.value = result
        } else {
            live_CF.value = live_CF.value.plus(result)
        }
    }*/

    //Logic Home exitProcess(0)
    fun exit_app() {
        System.exit(0)
    }

    //Getter & Setter
    private var _name = MutableLiveData("")
    var name = _name
    fun setName(name: String) {
        _name.value = name
    }

    private var _surname = MutableLiveData("")
    var surname = _surname
    fun setSurname(surname: String) {
        _surname.value = surname
    }

    private var _date = MutableLiveData("")
    var date = _date
    fun setDate(date: String) {
        _date.value = date
    }

    private var _month = MutableLiveData("")
    var month = _month
    fun setMonth(month : String){
        _month.value = month
    }

    private var _day = MutableLiveData("")
    var day = _day
    fun setDay(day : String){
        _day.value = day
    }



    fun showToast(context: Context, msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, msg, duration).show()
    }









    //Logic getName
    fun getNameAndgetSurname(name: List<Char>): String {
        val consonants = listOf(
            'B',
            'C',
            'D',
            'F',
            'G',
            'H',
            'J',
            'K',
            'L',
            'M',
            'N',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'V',
            'W',
            'X',
            'Y',
            'Z'
        )
        val vocals = listOf('A', 'E', 'I', 'O', 'U')

        var char_result = mutableListOf<Char>()
        var c = mutableListOf<Char>()
        var v = mutableListOf<Char>()


        for (letterName in name) {
            if (consonants.contains(letterName)) {
                c.add(letterName)
                Log.d("consonants", "$c")
                if (c.size == 3) {
                    char_result = c
                    break
                }
            }

            if (vocals.contains(letterName)) {
                v.add(letterName)
            }

        }


        var maxLenghtChar = 3

        if (!v.isEmpty()) {
            if (c.size != maxLenghtChar) {
                //Log.d("position" , "$position")
                when (c.size) {
                    0 -> char_result = c.plus(v).toMutableList()
                    1 -> {
                        var i = 0
                        while (i < 1) {
                            char_result = c.plus(v[i]).toMutableList()
                            i++
                        }
                    }

                    2 -> char_result = c.plus(v[0]).toMutableList()
                }
            }
        }

        var result = char_result.joinToString("")


        return result


    }


    //GetDate Fragment
    fun calcDate(date : String): String {
        if(date.length == 4){
            return date.takeLast(2)
        } else {
            return "Error"
        }
    }

    fun calcMonth(month : String) : String {
        when(month){
            "mese" -> {return "mese"}
            "Gennaio"-> {return "A"}
            "Febbraio" -> {return "B"}
            "Marzo" -> {return "C"}
            "Aprile" -> {return "D"}
            "Maggio" -> {return "E"}
            "Giugno" -> {return "H"}
            "Luglio" -> {return "L"}
            "Agosto" -> {return "M"}
            "Settembre" -> {return "P"}
            "Ottobre" -> {return "R"}
            "Novembre" -> {return "S"}
            "Dicembre" -> {return "T"}
            else -> {return "Error"}
        }
    }

    fun calcDay(day: String) : String {

        if(day.length == 2){
            return day
        }
        return ""
    }




}
































