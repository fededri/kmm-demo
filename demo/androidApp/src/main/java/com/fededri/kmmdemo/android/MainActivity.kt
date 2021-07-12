package com.fededri.kmmdemo.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.fededri.kmmdemo.models.MovieListType
import com.fededri.kmmdemo.networking.TMDApi
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val api: TMDApi by lazy {
        TMDApi(ThreadInfoImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            getMovies()
        }
    }

    private fun getMovies(){
        lifecycleScope.launchWhenResumed {
            withContext(Dispatchers.Main) {
                api.getMovies(MovieListType.POPULAR)
                //Toast.makeText(this@MainActivity, "Found ${movies.size} movies", Toast.LENGTH_LONG).show()
            }
        }
    }
}
