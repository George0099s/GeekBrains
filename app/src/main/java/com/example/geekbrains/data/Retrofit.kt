import com.example.geekbrains.data.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Service? = null

    fun getClient(): Service {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.weather.yandex.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service::class.java)
        }
        return retrofit!!
    }

}