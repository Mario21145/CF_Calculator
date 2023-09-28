package com.example.calculator_cf

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.reflect.typeOf

class AppViewModel : ViewModel() {

    //LiveData CF

    private var _liveCF = MutableLiveData("")
    var live_CF: MutableLiveData<String> = MutableLiveData<String>("Testsss")
        get() = _liveCF

    //Logic Home exitProcess(0)
    fun exit_app() {
        System.exit(0)
        //reset status 1
    }

    //Logic getName

    fun check_Consonants_And_Vocals(name: List<Char>) : String{

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

        for (letterName in name) {
            if (letterName in consonants) {

                char_result.add(letterName)
                if (char_result.size == 3) {
                    break
                }

            } else if (letterName in vocals) {
                char_result.add(letterName)
                if (char_result.size == 3) {
                    break
                }
            }
        }

        var result = char_result.joinToString("")
        return result
    }

    fun checkIsEmpty(id : List<Char>) : Boolean{
        if(id.isEmpty()){
            return true
        }
            return false
    }





















}











