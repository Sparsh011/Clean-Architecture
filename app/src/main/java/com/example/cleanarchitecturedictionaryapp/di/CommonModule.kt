package com.example.cleanarchitecturedictionaryapp.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitecturedictionaryapp.data.local.CommonDatabase
import com.example.cleanarchitecturedictionaryapp.data.local.Converters
import com.example.cleanarchitecturedictionaryapp.data.remote.DictionaryApi
import com.example.cleanarchitecturedictionaryapp.data.remote.StackOverflowApi
import com.example.cleanarchitecturedictionaryapp.data.repository.StackOverflowRepositoryImpl
import com.example.cleanarchitecturedictionaryapp.data.repository.WordInfoRepositoryImpl
import com.example.cleanarchitecturedictionaryapp.data.util.GsonParser
import com.example.cleanarchitecturedictionaryapp.domain.repository.StackOverflowRepository
import com.example.cleanarchitecturedictionaryapp.domain.repository.WordInfoRepository
import com.example.cleanarchitecturedictionaryapp.domain.use_cases.GetStackOverflowInfo
import com.example.cleanarchitecturedictionaryapp.domain.use_cases.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.dnsoverhttps.DnsOverHttps
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.InetAddress
import java.net.Proxy
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository) : GetWordInfo {
        return GetWordInfo(repository = repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: CommonDatabase,
        api: DictionaryApi
    ) : WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app : Application) : CommonDatabase {
        return Room.databaseBuilder(
            app,
            CommonDatabase::class.java,
            "word_info_db"
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi() : DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStackoverflowApi() : StackOverflowApi {
        val appCache = Cache(File("cacheDir", "okhttpcache"), 10 * 1024 * 1024)

        val bootstrapClient = OkHttpClient.Builder()
            .cache(appCache)
            .proxy(Proxy.NO_PROXY)
            .build()

        val dns = DnsOverHttps.Builder()
            .client(bootstrapClient)
            .url("https://dns.google/dns-query".toHttpUrl())
            .bootstrapDnsHosts(InetAddress.getByName("8.8.4.4"), InetAddress.getByName("8.8.8.8"))
            .build()

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .dns(dns)
            .proxy(Proxy.NO_PROXY)
            .build()

        return Retrofit.Builder()
            .baseUrl(StackOverflowApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(StackOverflowApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStackoverflowRepository(
        api: StackOverflowApi,
        db: CommonDatabase,
    ) : StackOverflowRepository {
        val toDate = System.currentTimeMillis() / 1000
        val fromDate = toDate - ( 30 * 24 * 60 * 60)

        return StackOverflowRepositoryImpl(
            api = api,
            dao = db.dao,
            toDate = toDate,
            fromDate = fromDate
        )
    }

    @Provides
    @Singleton
    fun provideStackOverflowUseCase(repository: StackOverflowRepository) : GetStackOverflowInfo {
        return GetStackOverflowInfo(repository = repository)
    }
}