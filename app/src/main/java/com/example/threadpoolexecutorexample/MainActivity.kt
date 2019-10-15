package com.example.threadpoolexecutorexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.threadpoolexecutorexample.task.MyRunnable
import com.example.threadpoolexecutorexample.util.HandlerUtil
import com.example.threadpoolexecutorexample.util.RandomGenerator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Handler.Callback {

    lateinit var myRunnable: MyRunnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = Handler(this)


        run_my_tasks_button.setOnClickListener {

            myRunnable = MyRunnable(
                handler,
                mutableListOf(
                    RandomGenerator.getRandomNumber(),
                    RandomGenerator.getRandomNumber(),
                    RandomGenerator.getRandomNumber(),
                    RandomGenerator.getRandomNumber(),
                    RandomGenerator.getRandomNumber(),
                    RandomGenerator.getRandomNumber(),
                    RandomGenerator.getRandomNumber()
                )
            )
            val thread = Thread(myRunnable)
            thread.start()
        }
    }

    override fun handleMessage(message: Message): Boolean {
        val messageString = message.data.getString(HandlerUtil.messageKey)

        if (messageString?.startsWith("thread_name") == true) {
            when (messageString.split(":")[2]) {
                "0" -> {
                    tone_title_textview.text = messageString.split(":")[1]
                }
                "1" -> {
                    ttwo_title_textview.text = messageString.split(":")[1]
                }
                "2" -> {
                    tthree_title_textview.text = messageString.split(":")[1]
                }
            }
        } else if (messageString?.startsWith("thread_update") == true) {
            when (messageString.split(":")[3]) {
                "0" -> {
                    tone_content_textview.text = messageString.split(":")[1]
                    tone_progressbar.progress = messageString.split(":")[2].toInt()
                }
                "1" -> {
                    ttwo_content_textview.text = messageString.split(":")[1]
                    ttwo_progressbar.progress = messageString.split(":")[2].toInt()
                }
                "2" -> {
                    tthree_content_textview.text = messageString.split(":")[1]
                    tthree_progressbar.progress = messageString.split(":")[2].toInt()
                }
            }
        } else {
            messageString?.let { msgString ->
                when (msgString.split(":")[1]) {
                    "0" -> {
                        tone_content_textview.text = "TASK DONE"
                    }
                    "1" -> {
                        ttwo_content_textview.text = "TASK DONE"
                    }
                    "2" -> {
                        tthree_content_textview.text = "TASK DONE"
                    }
                }
            }
        }


        return true
    }
}
