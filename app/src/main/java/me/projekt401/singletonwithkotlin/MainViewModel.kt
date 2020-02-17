package me.projekt401.singletonwithkotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import me.projekt401.singletonwithkotlin.model.User
import me.projekt401.singletonwithkotlin.repository.Repository

// This is NOT a singleton because it is bound to MainActivity
class MainViewModel : ViewModel(){

    private val _userId: MutableLiveData<String> = MutableLiveData()

    val user: LiveData<User> = Transformations
            // SwitchMap operator would trigger if the user changes
        .switchMap(_userId){userId ->
            // And would execute the action inside of this lambda
            // Would take in the userId: String and return an object type LiveData<User>
            // It is mapping one user type String to LiveData<User>
            Repository.getUser(userId)
        }

    fun setUserId(userId: String){
        // If the userId is changed then the SwitchMap would be triggered
        val update = userId
        if(_userId.value == update){
            return
        }
        _userId.value = update
    }

    fun cancelJobs(){
        Repository.cancelJobs()
    }
}