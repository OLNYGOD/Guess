package com.example.guess

import android.content.res.Resources
import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest{
    @Rule
    @JvmField
    val rule = ActivityScenarioRule<MaterialActivity>(MaterialActivity::class.java) //從MaterialActivity取得資料
    lateinit var resources :Resources
    @Test
    fun guessWorng(){
        val scenario  = rule.scenario
        var secret = 0
        scenario.onActivity { secret = it.secretNumber.secret  //從secretNumber取得seret
        resources = it.resources}
        Log.d(MaterialActivityTest::class.java.simpleName, "secret : " +secret)
        for( n in 1..10){
            if (n != secret){
                //Espresso test UI for single app
                onView(withId(R.id.ed_number))
                    .perform(clearText())                //清除資料
                onView(withId(R.id.ed_number))            //取得id資料
                    .perform(typeText(n.toString()))     //viewaction 執行輸入5這個動作
                onView(withId(R.id.button_ok))
                    .perform(click())                    //按下按鈕
                var message =
                    if (n > secret) resources.getString(R.string.bigger)
                    else resources.getString(R.string.smaller)
                Log.d(MaterialActivityTest::class.java.simpleName, "message : " +message  )
                onView(withText(message)).check(matches(isDisplayed()))  //檢查訊息有無跳出
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }
        }
        onView(withId(R.id.ed_number)).perform(closeSoftKeyboard())
        onView(withId(R.id.fab)).perform(click())
        onView(withText("Replay game")).check(matches(isDisplayed()))
        onView(withText(resources.getString(R.string.ok))).perform(click())
        onView(withId(R.id.counter)).check(matches(withText("0")))
    }
}