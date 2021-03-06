package com.arkivanov.todo.main

import com.arkivanov.todo.add.TodoAdd
import com.arkivanov.todo.list.TodoList

interface TodoMain {

    val model: Model

    interface Model {
        val list: TodoList
        val add: TodoAdd
    }

    sealed class Input {
        data class Update(val id: Long, val text: String, val isDone: Boolean) : Input()
    }

    sealed class Output {
        data class Selected(val id: Long) : Output()
    }
}
