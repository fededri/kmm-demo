package com.fededri.kmmdemo.networking

import com.fededri.kmmdemo.ThreadInfo
import com.fededri.kmmdemo.freeze
import com.fededri.kmmdemo.models.Movie
import com.fededri.kmmdemo.networking.models.GetMoviesResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlinx.serialization.json.Json as KotlinxJson

class TMDApi(val threadInfo: ThreadInfo) {

    private val baseUrl = "https://api.themoviedb.org/3"
    private val apiKey: String = ""
    private val contentType = "application/json;charset=utf-8"
    private val httpClient = HttpClient() {

        install(JsonFeature) {
            val json = KotlinxJson { useAlternativeNames = false; ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }

        ResponseObserver { response ->
            println("HTTP status: ${response.status.value}")
        }
    }

    @Throws(Exception::class)
    suspend fun getMovies(type: MovieListType): List<Movie> {
        val listType = type.value

        val scope = CoroutineScope(Dispatchers.Default)
        val movies = scope.async {
            if (threadInfo.isMainThread()) {
                println("WARNING: RUNNING QUERY ON MAIN THREAD")
            }
            val response =
                httpClient.get<GetMoviesResponse>("${baseUrl}/movie/$listType") {
                    headers {
                        append("Accept", contentType)
                    }

                    parameter("api_key", apiKey)
                }
            response.results.map {
                Movie(name = it.title)
            }
        }
        return movies.await()
    }

    fun dispose() {
        httpClient.close()
    }
}