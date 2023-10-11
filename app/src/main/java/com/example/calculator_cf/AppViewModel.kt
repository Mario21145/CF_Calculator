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

    private var _liveCF = MutableLiveData("")
    val live_CF: LiveData<String>
        get() = _liveCF


    fun reset() {
        _name = MutableLiveData("")
        _surname = MutableLiveData("")
        _date = MutableLiveData("")
        _month = MutableLiveData("")
        _day = MutableLiveData(0)
        _city = MutableLiveData("")
        _liveCF = MutableLiveData("")
    }


    fun updateCF(rangePosition: IntRange) {

        val convertedLiveCf: MutableList<Char> = _liveCF.value?.toMutableList() ?: mutableListOf()
        if (rangePosition.first in 0 until convertedLiveCf.size && rangePosition.last in 0 until convertedLiveCf.size) {
            for (i in rangePosition) {
                convertedLiveCf[i] = ' '
            }
            convertedLiveCf.removeAll { it == ' ' }
            _liveCF.value = convertedLiveCf.joinToString("")

        }
    }

    fun setCF(result: String) {

        Log.d("liveCfViewModel", "${live_CF.value}")

        if (_liveCF == MutableLiveData("")) {
            _liveCF.value = result
        } else {
            _liveCF.value = _liveCF.value + result
        }

    }

    //Logic Home exitProcess(0)
    fun exit_app() {
        System.exit(0)
    }

    //Getter & Setter

    private var _surname = MutableLiveData("")
    var surname: LiveData<String> = _surname
    fun setSurname(surname: String) {
        _surname.value = surname
    }

    private var _name = MutableLiveData("")
    var name: LiveData<String> = _name
    fun setName(name: String) {
        _name.value = name
    }

    private var _date = MutableLiveData("")
    var date: LiveData<String> = _date
    fun setDate(date: String) {
        _date.value = date
    }

    private var _month = MutableLiveData("")
    var month: LiveData<String> = _month
    fun setMonth(month: String) {
        _month.value = month
    }

    private var _day = MutableLiveData(0)
    var day: LiveData<Int> = _day
    fun setDay(day: Int) {
        _day.value = day
    }

    private var _sex = MutableLiveData("")
    var sex: LiveData<String> = _sex
    fun setSex(sex: String) {
        _sex.value = sex
    }

    private var _city = MutableLiveData("")
    var city: LiveData<String> = _city
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


    fun calcDate(date: String): String {
        if (date.length == 4) {
            return date.takeLast(2)
        } else {
            return ""
        }
    }

    fun calcDay(day: String): String {
        if (day.length == 2) {
            return day.takeLast(2)
        }
        return ""
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

            else -> {
                return ""
            }
        }
    }

    fun calcCity(city: String): String {
        when (city) {
            data.cities[0] -> {
                return "B759"
            }

            data.cities[1] -> {
                return "A064"
            }

            data.cities[2] -> {
                return "D789"
            }

            data.cities[3] -> {
                return "A512"
            }

            data.cities[4] -> {
                return "F839"
            }
        }
        return ""
    }

    fun calcSex(sex: String) {
        if (sex == "uomo") {
            if(_day.value!! > 31){
                _day.value = _day.value
            }
        } else if (sex == "donna") {
            _day.value = _day.value!! + 40
        }
    }


    fun calcLastLetter(cf: String?): String {
        if(cf?.length == 15) {
            var sum = 0
            for ((index, char) in cf.withIndex()) {
                if (index % 2 == 0) {
                    sum += data.evenValues[char] ?: 0
                } else {
                    sum += data.oddValues[char] ?: 0
                }
            }

            val controlValue = sum % 26
            val controlChar = data.letterValues.entries.first { it.value == controlValue }.key

            return controlChar.toString()
        }
        return ""
    }


}
































