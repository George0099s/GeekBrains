import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

private const val TAG = "MainServiceTAG"
const val MAIN_SERVICE_STRING_EXTRA = "MainServiceExtra"

class MainService(name: String = "MainService") : IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: ")
        createLogMessage("onHandleIntent ${intent?.getStringExtra(MAIN_SERVICE_STRING_EXTRA)}")
    }

    override fun onCreate() {
        createLogMessage("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createLogMessage("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        createLogMessage("onDestroy")
        super.onDestroy()
    }

    //Выводим уведомление в строке состояния
    private fun createLogMessage(message: String) {
        Log.d(TAG, message)
    }
}
