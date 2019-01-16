package paulo.com.br.bico.repository.remote.configuration

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response
import paulo.com.br.bico.service.impl.UserServiceImpl

class TokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!chain.request().url().toString().contains("/login")) {
            var request = chain.request()
            request = request.newBuilder()
                    .addHeader("Authorization", UserServiceImpl.token)
                    .build()
            return chain.proceed(request)
        } else {
            var request = chain.request()
            request = request.newBuilder()
                    .build()
            return chain.proceed(request)
        }
    }
}