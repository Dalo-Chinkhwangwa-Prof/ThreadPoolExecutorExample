package com.example.threadpoolexecutorexample.util

import android.os.Bundle
import android.os.Handler
import android.os.Message
import java.util.concurrent.*

object HandlerUtil {

    const val messageKey = "message_key"

    private const val MIN_POOL_COUNT = 3
    private const val MAX_POOL_COUNT = 5
    private const val TIME_LIMIT: Long = 30
    private val TIME_UNIT = TimeUnit.SECONDS
    private val taskQueue: BlockingQueue<Runnable> = LinkedBlockingQueue<Runnable>()

    lateinit var handler: Handler
    lateinit var taskList: MutableList<Runnable>
    var threadPoolExecutor: ThreadPoolExecutor? = null


    fun setProperties(handler: Handler, taskList: MutableList<Runnable>) {
        this.handler = handler
        this.taskList = taskList
    }

    fun executeTasksInThreadPool() {
        if (threadPoolExecutor == null)
            threadPoolExecutor =
                ThreadPoolExecutor(MIN_POOL_COUNT, MAX_POOL_COUNT, TIME_LIMIT, TIME_UNIT, taskQueue)

        taskList.forEach { currentTask ->

            threadPoolExecutor?.execute(currentTask)
        }
    }

    fun sendMessage(message: String){
        val msg = Message()

        val bundle = Bundle()
        bundle.putString(messageKey, message)
        msg.data = bundle

        handler.sendMessage(msg)

    }


}