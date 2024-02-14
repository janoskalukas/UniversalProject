package universal.networking.nba.di

import com.squareup.moshi.Moshi
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Networking base DI graph.
 */
public object NetworkingBaseGraph {

    public val module = module {

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
                .baseUrl("https://www.balldontlie.io/api/v1/")
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .build()
        }
    }
}