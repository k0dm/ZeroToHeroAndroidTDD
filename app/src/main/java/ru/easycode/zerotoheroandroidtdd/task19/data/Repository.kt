package ru.easycode.zerotoheroandroidtdd.task19.data

import ru.easycode.zerotoheroandroidtdd.task19.LoadResult
import java.net.UnknownHostException

interface Repository {

    suspend fun load(): LoadResult

    class Base(
        private val service: SimpleService,
        private val url: String = "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json",
    ) : Repository {
        override suspend fun load(): LoadResult{
            return try {
                val result = service.fetch(url)
                LoadResult.Success(result)
            }catch (e: Exception) {
                LoadResult.Error(e is UnknownHostException)
            }
        }
    }
}