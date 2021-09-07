package com.fededri.kmmdemo.networking

import com.fededri.kmmdemo.models.Movie
import com.fededri.kmmdemo.models.MovieListType
import com.fededri.kmmdemo.networking.models.GetMoviesResponse
import com.fededri.kmmdemo.networking.models.getPosterUrl
import io.github.fededri.arch.ThreadInfo
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json as KotlinxJson

class TMDApi(private val threadInfo: ThreadInfo) {

    private val baseUrl = "https://api.themoviedb.org/3"
    private val apiKey: String = "f2dd2ffff4c39e0f8005172a7b1e823d"
    private val contentType = "application/json;charset=utf-8"
    private val httpClient = HttpClient() {

        install(JsonFeature) {
            val json = KotlinxJson {
                /**
                 * Seems to be a bug where leaving this flag as true throws an InvalidMutabilityException
                 */
                useAlternativeNames = false
                ignoreUnknownKeys = true
            }
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
                   baseConfiguration()
                }
            response.results.map {
                Movie(
                    name = it.title,
                    title = it.title,
                    popularity = it.popularity,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    overview = it.overview,
                    posterPath = it.getPosterUrl()
                )
            }
        }
        return movies.await()
    }

    fun dispose() {
        httpClient.close()
    }

    private fun HttpRequestBuilder.baseConfiguration(){
        headers {
            append("Accept", contentType)
        }

        parameter("api_key", apiKey)
    }
}