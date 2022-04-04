package com.andrewaac.donutchallenge.ui.screens.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.jupiter.api.Assertions.assertEquals
import java.lang.IllegalStateException

class TestObserver<T> : Observer<T> {

    private val valueHistory = mutableListOf<T>()

    override fun onChanged(value: T) {
        valueHistory.add(value)
    }

    fun assertValue(expectedValue: T) {
        if (valueHistory.size < 1) {
            throw IllegalStateException("TestObserver has no values")
        }
        val latestValue = valueHistory[valueHistory.size - 1]
        assertEquals(expectedValue, latestValue)
    }
}

fun <T> LiveData<T>.test(): TestObserver<T> {
    val testObserver = TestObserver<T>()
    this.observeForever(testObserver)
    return testObserver
}
