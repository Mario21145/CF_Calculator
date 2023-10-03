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
        if(_liveCF == MutableLiveData(" ")){
            _liveCF.value = parameter
        } else {
             _liveCF.value = _liveCF.value + parameter
        }

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

    fun calcDay(day: List<Char>){

    }

    fun calcMonth(month : String){

    }

}
































