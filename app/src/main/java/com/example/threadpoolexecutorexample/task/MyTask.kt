package com.example.threadpoolexecutorexample.task

import android.util.Log
import com.example.threadpoolexecutorexample.util.HandlerUtil
import java.util.concurrent.TimeUnit

class MyTask(val countTime: Int, val position: Int) : Runnable {

    override fun run() {

        HandlerUtil.sendMessage("thread_name:${Thread.currentThread().name}:$position")
        var time: Int = 0
        while (countTime > time) {

            time += 1
            HandlerUtil.sendMessage("thread_update:${countTime - time} Seconds remaining:${((time.toFloat() / countTime.toFloat()) * 100).toInt()}:$position")
            Thread.sleep(2000)
        }
        HandlerUtil.sendMessage("thread_done:$position")

    }

}