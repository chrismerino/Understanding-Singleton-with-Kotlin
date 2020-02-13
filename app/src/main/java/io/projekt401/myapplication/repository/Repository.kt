package io.projekt401.myapplication.repository

import androidx.lifecycle.LiveData
import io.projekt401.myapplication.model.User
import kotlinx.coroutines.CompletableJob

object Repository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User>{
        return object: LiveData<User>(){
            override fun onActive() {
                super.onActive()



            }
        }
    }
}