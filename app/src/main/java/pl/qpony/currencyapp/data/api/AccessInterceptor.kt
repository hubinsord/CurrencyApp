package pl.qpony.currencyapp.data.api

import okhttp3.Interceptor
import kotlin.Throws
import okhttp3.Request
import okhttp3.Response
import pl.qpony.currencyapp.core.di.Constants
import java.io.IOException

class AccessInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("access_key", Constants.FIXER_API_ACCESS_KEY)
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}