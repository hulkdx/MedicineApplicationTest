@file:Suppress("MemberVisibilityCanBePrivate")

package hulkdx.com.data.remote

import com.google.gson.GsonBuilder
import hulkdx.com.data.model.MedicineListRemoteEntity
import java.util.concurrent.TimeUnit

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query




/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
interface RemoteService {

    @GET("download.php")
    fun medicineList(@Query("filetype") fileType: String): Single<MedicineListRemoteEntity>

    object Factory {

        fun create(): RemoteService {
            val client = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()

            val json = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(json))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(RemoteService::class.java)
        }
    }
}
