package com.example.calculator_cf

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator_cf.data.Dataset


class AppViewModel : ViewModel() {

    //Dataset
    var data = Dataset()

    //LiveData CF



    private val _liveCF = MutableLiveData<String>("test")
    val live_CF: LiveData<String> = _liveCF

    fun calcCF(result : String) {

        if(_liveCF == MutableLiveData("")){
            _liveCF.value = result
        } else {
            _liveCF.value = result
        }

    }

    //Logic Home exitProcess(0)
    fun exit_app() {
        System.exit(0)
    }

    //Getter & Setter

    private var _surname = MutableLiveData("")
    var surname = _surname
    fun setSurname(surname: String) {
        _surname.value = surname
    }

    private var _name = MutableLiveData("")
    var name = _name
    fun setName(name: String) {
        _name.value = name
    }

    private var _date = MutableLiveData("")
    var date = _date
    fun setDate(date: String) {
        _date.value = date
    }

    private var _month = MutableLiveData("")
    var month = _month
    fun setMonth(month: String) {
        _month.value = month
    }

    private var _day = MutableLiveData("")
    var day = _day
    fun setDay(day: String) {
        _day.value = day
    }

    private var _sex = MutableLiveData("")
    var sex = _sex
    fun setSex(sex: String) {
        _sex.value = sex
    }

    private var _city = MutableLiveData("")
    var city = _city
    fun setCity(city: String) {
        _city.value = city
    }

    fun showToast(context: Context, msg: String, duration: Int) {
        Toast.makeText(context, msg, duration).show()
    }


    //Logic getName
    fun calcConsonants(name: List<Char>): String {
        var char_result = mutableListOf<Char>()
        var c = mutableListOf<Char>()
        var v = mutableListOf<Char>()

        for (letterName in name) {
            if (data.consonants.contains(letterName)) {
                c.add(letterName)
                Log.d("consonants", "$c")
                if (c.size == 3) {
                    char_result = c
                    break
                }
            }

            if (data.vocals.contains(letterName)) {
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
    fun calcDate(date: String): String {
        if (date.length == 4) {
            return date.takeLast(2)
        } else {
            return "Error"
        }
    }

    fun calcMonth(month: String): String {
        when (month) {

            data.months[0] -> {
                return "A"
            }

            data.months[1] -> {
                return "B"
            }

            data.months[2] -> {
                return "C"
            }

            data.months[3] -> {
                return "D"
            }

            data.months[4] -> {
                return "E"
            }

            data.months[5] -> {
                return "H"
            }

            data.months[6] -> {
                return "L"
            }

            data.months[7] -> {
                return "M"
            }

            data.months[8] -> {
                return "P"
            }

            data.months[9] -> {
                return "R"
            }

            data.months[10] -> {
                return "S"
            }

            data.months[11] -> {
                return "T"
            }

            data.months[12] -> {
                return "mese"
            }

            else -> {
                return "Error"
            }
        }
    }

    fun calcDay(day: String): String {

        if (day.length == 2) {
            return day
        }
        return ""
    }

    fun calcCity(city: String): String {
        when (city) {
            "cardito" -> {
                return "B759"
            }
            "" -> {}
            "" -> {}
            "" -> {}
            "" -> {}
        }
        return " return ErrorCity "
    }

    fun calcSex(sex : String){
        if(sex == "men"){
            Log.d("Sex" , "Il sesso selezionato è uomo")
        } else if( sex == "women"){
        }
    }




}
































