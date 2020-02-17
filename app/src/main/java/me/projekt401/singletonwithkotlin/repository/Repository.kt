package me.projekt401.singletonwithkotlin.repository

import androidx.lifecycle.LiveData
import me.projekt401.singletonwithkotlin.api.RetrofitBuilder
import me.projekt401.singletonwithkotlin.model.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User>{
        job = Job()
        return object: LiveData<User>(){

            // When this LiveData object is called I want to get the value for this.
            override fun onActive() {
                super.onActive()

                // Check if the job is not null, and if is NOT null then run the code inside Let
                job?.let {theJob ->
                    // Creates a unique Coroutine on a background thread that is referencing this job
                    // If the job gets cancelled that it would cancel whatever is inside the IO scope.
                    CoroutineScope(IO + theJob).launch {
                        val user = RetrofitBuilder.apiService.getUser(userId)

                        // Using LiveData it is not possible to set a value from a background thread.
                        // That would lead to a crash.
                        // So it is necessary to switch the context to the Main thread.
                        withContext(Main){
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs(){
       job?.cancel()
    }
}