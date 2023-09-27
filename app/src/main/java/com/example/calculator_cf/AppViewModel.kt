package com.example.calculator_cf

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.reflect.typeOf

class AppViewModel : ViewModel() {

    //LiveData CF
    private val _liveCF = MutableLiveData("")
    val final_result: LiveData<String>
        get() = _liveCF


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

        for (Letter_Name in name) {
            if (Letter_Name in consonants) {

                char_result.add(Letter_Name)
                if (char_result.size == 3) {
                    break
                }

            } else if (Letter_Name in vocals){
                char_result.add(Letter_Name)
                    if (char_result.size == 3) {
                        break
                    }
            }
        }

        var final_result = char_result.joinToString("")
        return final_result
    }

}











