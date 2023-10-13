package com.example.calculator_cf

import android.os.Bundle
import android.widget.EditText
import androidx.databinding.adapters.ViewBindingAdapter.OnViewAttachedToWindow
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.selects.select
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.core.IsAnything.anything

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var v: AppViewModel

    @Before
    fun setup() {
        v = AppViewModel()
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.calculator_cf", appContext.packageName)
    }

    //Surname
    @Test
    fun checkIfResultIsShowed_Surname() {
        launchFragmentInContainer<GetSurname>(themeResId = R.style.Theme_Calculator_CF)
        onView(withId(R.id.editText_Surname)).perform(typeText("rossi"))
        onView(withId(R.id.Live_CF_Text)).check(matches(withText(containsString("RSS"))))
    }

    //Name
    @Test
    fun checkIfResultIsShowed_Name() {
        launchFragmentInContainer<getName>(themeResId = R.style.Theme_Calculator_CF)
        onView(withId(R.id.editTextText)).perform(typeText("Mario"))
        onView(withId(R.id.Live_CF_Text)).check(matches(withText(containsString("MRA"))))
    }

    //Date
    @Test
    fun checkIfResultIsShowed_Date() {
        launchFragmentInContainer<getDate>(themeResId = R.style.Theme_Calculator_CF)

        //Date EditText
        onView(withId(R.id.date)).perform(typeText("1999"))
        //Spinner
        onView(withId(R.id.month)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.month)).check(matches(withSpinnerText(containsString("Gennaio"))))
        //Day EditText
        onView(withId(R.id.day)).perform(typeText("01"))

        onView(withId(R.id.Live_CF_Text)).check(matches(withText(containsString("99A01"))))
    }

    //Sex
//    @Test
//    fun checkIfResultIsShowed_Sex() {
//        launchFragmentInContainer<getSex>(themeResId = R.style.Theme_Calculator_CF)
//        onView(withId(R.id.men)).perform(click())
//
//        onView(withId(R.id.men)).check(matches(isChecked())).check(matches(withText(containsString("01"))))
//        onView(withId(R.id.women)).check(matches(isChecked())).check(matches(withText(containsString("41"))))
//    }



    @Test
    fun checkIfResultIsShowed_City(){
        launchFragmentInContainer<getCity>(themeResId = R.style.Theme_Calculator_CF)
        onView(withId(R.id.cities)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.cities)).check(matches(withSpinnerText(containsString("cardito"))))
    }





    @Test
    fun navigateFromHomeToSurname() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val homeScenario = launchFragmentInContainer<Home>(
            themeResId =
            R.style.Theme_Calculator_CF
        )

        homeScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.navigation_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        assertEquals(navController.currentDestination?.id, R.id.home)
    }

//    @Test
//    fun navigateFromSurnameToName() {
//
//        val navController = TestNavHostController(
//            ApplicationProvider.getApplicationContext()
//        )
//
//        val nameScenario = launchFragmentInContainer<GetSurname>(
//            themeResId =
//            R.style.Theme_Calculator_CF
//        )
//
//        nameScenario.onFragment { fragment ->
//            navController.setGraph(R.navigation.navigation_graph)
//            Navigation.setViewNavController(fragment.requireView(), navController)
//        }
//
//        assertEquals(navController.currentDestination?.id, R.id.getName)
//    }


}