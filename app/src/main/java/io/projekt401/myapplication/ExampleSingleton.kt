package io.projekt401.myapplication

import io.projekt401.myapplication.model.User

object ExampleSingleton {
    val singletonUser: User by lazy {
        User("test@test", "User", "image.png")
    }
}