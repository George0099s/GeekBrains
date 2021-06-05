package com.example.geekbrains.multythread

import android.os.Looper

class HandlerThread : Thread() {

    override fun run() {
        Looper.prepare()
        Looper.loop()
    }
}