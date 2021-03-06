package com.arkivanov.todo.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.arkivanov.decompose.extensions.compose.jetpack.rootComponent
import com.arkivanov.todo.app.ui.ComposeAppTheme
import com.arkivanov.todo.database.TodoDatabase
import com.arkivanov.todo.root.TodoRoot
import com.arkivanov.todo.root.invoke

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    rootComponent { componentContext ->
                        TodoRoot(componentContext, TodoDatabase(this))
                    }.model()
                }
            }
        }
    }
}
