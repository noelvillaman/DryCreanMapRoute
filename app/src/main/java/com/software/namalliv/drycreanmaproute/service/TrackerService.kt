package com.software.namalliv.drycreanmaproute.service

import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.software.namalliv.drycreanmaproute.util.Constants.ACTION_SERVICE_START
import com.software.namalliv.drycreanmaproute.util.Constants.ACTION_SERVICE_STOP

class TrackerService : LifecycleService(){
    companion object {
        val started = MutableLiveData<Boolean>()
    }

    private fun setInitialValue(){
        started.postValue(false)
    }
    override fun onCreate() {
        setInitialValue()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent.let {
            when(it?.action){
                ACTION_SERVICE_START -> {
                    started.postValue(true)

                }
                ACTION_SERVICE_STOP -> {
                    started.postValue(false)
                }
                else -> {}
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }


}