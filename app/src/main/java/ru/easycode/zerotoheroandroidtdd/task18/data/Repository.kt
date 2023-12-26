package ru.easycode.zerotoheroandroidtdd.task18.data

interface Repository {

    suspend fun load(): SimpleResponse

    class Base(
        private val service: SimpleService,
        private val url: String = "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json",
    ) : Repository {
        override suspend fun load() = service.fetch(url)
    }
}