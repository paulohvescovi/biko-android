package paulo.com.br.bico.repository.remote.configuration

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient




class WebserviceApi private constructor(){

    companion object {
        fun get(): WebserviceApi {
            return WebserviceApi()
        }
    }

    fun <S> createService(serviceClass: Class<S>): S {

        var gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        var gson = gsonBuilder.create()

        val client = OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())
                .build()

        return Retrofit.Builder()
                .baseUrl("http://200.195.135.2:9094/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build()
                .create(serviceClass)
    }
}