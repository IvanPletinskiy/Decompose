package com.arkivanov.decompose.extensions.compose.jetpack

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onDispose
import androidx.compose.runtime.remember
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver

@Composable
fun <T : Any> Value<T>.asState(): State<T> {
    val composeState = remember(this) { mutableStateOf(value) }

    val observer =
        remember(this) {
            val observer: ValueObserver<T> = { composeState.value = it }
            subscribe(observer)
            observer
        }

    onDispose { unsubscribe(observer) }

    return composeState
}

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use `val state by value.asState()`")
@Composable
operator fun <T : Any> Value<T>.invoke(render: @Composable (T) -> Unit) {
    observe(render)
}

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use `val state by value.asState()`")
@Composable
fun <T : Any> Value<T>.observe(observer: @Composable (T) -> Unit) {
    observer(asState().value)
}
