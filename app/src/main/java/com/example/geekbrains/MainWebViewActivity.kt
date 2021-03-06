package com.example.geekbrains

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.geekbrains.databinding.MainActivityWebViewBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainWebViewActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener(clickListener)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }*/
    }

    var clickListener: View.OnClickListener = object : View.OnClickListener {

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onClick(v: View?) {
            try {
                val uri = URL(binding.url.text.toString())
                val handler = Handler()
                Thread {
                    var urlConnection: HttpsURLConnection? = null
                    try {
                        urlConnection = uri.openConnection() as HttpsURLConnection
                        urlConnection.requestMethod =
                            "GET" // установка метода получения данных — GET
                        urlConnection.readTimeout =
                            5000 // установка таймаута — 10 000 миллисекунд
                        val reader =
                            BufferedReader(InputStreamReader(urlConnection.inputStream)) // читаем  данные в поток
                        val result = getLines(reader)
                        handler.post {
                            Log.d("my_tag", "onClick: ${result}")
                            binding.webview.loadData(result, "text/html; charset=utf-8", "utf-8")
//                            binding.webview.loadUrl(binding.url.text.toString())
                        }
                    } catch (e: Exception) {
                        Log.e("my_tag", "Fail connection", e)
                        e.printStackTrace()
                    } finally {
                        urlConnection?.disconnect()
                    }
                }.start()
            } catch (e: MalformedURLException) {
                Log.e("my_tag", "Fail URI", e)
                e.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}