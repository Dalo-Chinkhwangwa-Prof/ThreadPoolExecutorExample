package com.example.threadpoolexecutorexample.util

object RandomGenerator {

    fun getRandomNumber(): Int{
        return (1 until 21).random()
    }
}