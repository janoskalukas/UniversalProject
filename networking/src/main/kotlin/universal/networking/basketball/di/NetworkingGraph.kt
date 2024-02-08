package universal.networking.basketball.di

import com.squareup.moshi.Moshi
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import universal.networking.basketball.BasketballApi
import universal.networking.basketball.data.BasketballServiceApi

/**
 * Networking DI graph.
 */
public object NetworkingGraph {

    public val module = module {

        single<BasketballApi> { get<Retrofit>().create(BasketballApi::class.java) }

        single {
            Moshi.Builder()
                .build()
        }

        single {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(timeout = 30, TimeUnit.SECONDS)
                .writeTimeout(timeout = 30, TimeUnit.SECONDS)
                .connectTimeout(timeout = 30, TimeUnit.SECONDS)
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(BasketballApi.API_URL)
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .build()
        }

        factoryOf(::BasketballServiceApi)
    }
}