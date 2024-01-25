package universal.networking.basketball.di

import com.squareup.moshi.Moshi
import universal.networking.basketball.BasketballApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

public object NetworkingModule {

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
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                // Add any other OkHttpClient configurations here
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(BasketballApi.API_URL)
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .build()
        }
    }
}