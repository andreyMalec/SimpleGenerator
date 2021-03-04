package com.malec.simplegenerator.ui

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.malec.simplegenerator.NumberDataSource
import com.malec.simplegenerator.generator.PrimeGenerator
import com.malec.simplegenerator.model.Number
import com.malec.simplegenerator.model.NumberType
import com.malec.simplegenerator.model.NumberTypes
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainViewModel : ViewModel() {
    private val _numbers = MutableLiveData<PagedList<Number>>()
    val numbers: LiveData<PagedList<Number>>
        get() = _numbers

    private val _numberType = MutableLiveData(NumberTypes.PRIME)
    val numberType: LiveData<NumberType>
        get() = _numberType

    private lateinit var pagedList: PagedList<Number>

    private val mainExecutor = object : Executor {
        private val handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            handler.post(command)
        }
    }

    init {
        update()
    }

    private fun update() {
        val pageSize = numberType.value?.pageSize ?: 10
        val generator = numberType.value?.generator ?: PrimeGenerator()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(pageSize)
            .build()

        pagedList = PagedList.Builder(NumberDataSource(generator, pageSize), config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(mainExecutor)
            .build()
        _numbers.value = pagedList
    }

    fun setPrime() {
        if (numberType.value != NumberTypes.PRIME) {
            _numberType.value = NumberTypes.PRIME

            update()
        }
    }

    fun setFibonacci() {
        if (numberType.value != NumberTypes.FIBONACCI) {
            _numberType.value = NumberTypes.FIBONACCI

            update()
        }
    }
}