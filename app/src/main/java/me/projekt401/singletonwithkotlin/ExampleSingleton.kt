package me.projekt401.singletonwithkotlin

import me.projekt401.singletonwithkotlin.model.User

object ExampleSingleton {
    // This object would be null until is called and would get initilized once and never again.
    val singletonUser: User by lazy {
        User("test@test", "User", "image.png")
    }
}