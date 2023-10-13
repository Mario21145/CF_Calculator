package com.example.calculator_cf

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    private lateinit var v : AppViewModel

    @Before
    fun setUp() {
        v = AppViewModel()
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    //Setter
    @Test
    fun setName(){
        v.setName("Marco")
        assertEquals("Marco" , v.name.value)
    }

    @Test
    fun setSurname(){
        v.setSurname("Rossi")
        assertEquals("Rossi" , v.surname.value)
    }

    @Test
    fun setDate(){
        v.setDate("1999")
        assertEquals("1999" , v.date.value)
    }

    @Test
    fun setMonth(){
        v.setMonth("Aprile")
        assertEquals("Aprile" , v.month.value)
    }

    @Test
    fun setDay(){
        v.setDay(1)
        assertEquals("1" , v.day.value.toString())
    }

    @Test
    fun setSex(){
        v.setSex("uomo")
        assertEquals("uomo" , v.sex.value)
    }

    @Test
    fun setCity(){
        v.setCity("napoli")
        assertEquals("napoli" , v.city.value)
    }



    //Metodi per calcolare il codice fiscale
    @Test
    fun testSetCf(){
        v.setCF("MRC")
        assertEquals("MRC" , v.live_CF.value)
    }

    @Test
    fun testUpdateCf(){
        v.setCF("MRC")
        v.updateCF(0..1)
        assertEquals("C" , v.live_CF.value)
    }

    @Test
    fun testResetCf() {
        v.setCF("XXXXXXXXXXXXXXXX")
        v.reset()
        assertEquals("", v.live_CF.value)
    }

    @Test
    fun testCalcDate(){
        val result = v.calcDate("1999")
        assertEquals("99" , result )
    }

    @Test
    fun testCalcDay(){
        val result = v.calcDay("30")
        assertEquals("30" , result)
    }

    @Test
    fun textCalcMonth(){
        val result = v.calcMonth("Gennaio")
        assertEquals("A" , result)
    }

    @Test
    fun textCalcCity(){
        val result = v.calcCity("napoli")
        assertEquals("F839" , result)
    }

    @Test
    fun textCalcLastLetter(){
        val result = v.calcLastLetter("RSSMRC99D01F839")
        assertEquals("P" , result)
    }








}