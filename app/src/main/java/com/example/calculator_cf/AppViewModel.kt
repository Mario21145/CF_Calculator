package com.example.calculator_cf

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.merge
import kotlin.reflect.typeOf

class AppViewModel : ViewModel() {

    //LiveData CF

    private var _liveCF = MutableLiveData("")
    val live_CF: MutableLiveData<String>
        get() = _liveCF

    private var _currentCF = MutableLiveData("")
    var currentCF = MutableLiveData("")
        get() = _currentCF

    //Logic Home exitProcess(0)
    fun exit_app() {
        System.exit(0)
        //reset status 1
    }

    //Logic getName

    fun check_Consonants_And_Vocals(name: List<Char>): String {
        val consonants = listOf(
            'b',
            'c',
            'd',
            'f',
            'g',
            'h',
            'j',
            'k',
            'l',
            'm',
            'n',
            'p',
            'q',
            'r',
            's',
            't',
            'v',
            'w',
            'x',
            'y',
            'z'
        )
        val vocals = listOf('a', 'e', 'i', 'o', 'u')

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

        if(!v.isEmpty()){
            if(c.size != maxLenghtChar){
                //Log.d("position" , "$position")
                when(c.size){
                    0 -> char_result = c.plus(v).toMutableList()
                    1 -> {
                        var i = 0
                         while (i < 1){
                             char_result =  c.plus(v[i]).toMutableList()
                             i++
                         }
                    }
                    2 -> char_result = c.plus(v[0]).toMutableList()
                }
            }
        }

        var result = char_result.joinToString("")


        if(live_CF.value!!.isEmpty()){
            live_CF.value = result
        } else {
            live_CF.value = live_CF.value + result


        }

        return result
    }


    fun UpdateLiveData(){

    }




    

    
}
































