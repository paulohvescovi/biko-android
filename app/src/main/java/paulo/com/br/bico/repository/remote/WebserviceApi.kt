package paulo.com.br.bico.repository.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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

        return Retrofit.Builder()
                .baseUrl("http://192.168.88.146:28080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(serviceClass)
    }
}