package com.crazylegend.coroutines

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*


/**
 * Created by hristijan on 8/26/19 to long live and prosper !
 */

inline fun <T> AndroidViewModel.makeIOCall(
        crossinline onCallExecuted: () -> Unit = {},
        crossinline onErrorAction: (throwable: Throwable) -> Unit = { _ -> },
        crossinline ioCall: suspend () -> T,
        crossinline onCalled: (model: T) -> Unit): Job {

    return viewModelScope.launch(mainDispatcher) {
        supervisorScope {
            try {
                val task = async(ioDispatcher) {
                    ioCall()
                }
                onCalled(task.await())
            } catch (t: Throwable) {
                t.printStackTrace()
                onErrorAction(t)
            } finally {
                onCallExecuted()
            }
        }
    }
}

inline fun <T> CoroutineScope.makeIOCall(
        crossinline onCallExecuted: () -> Unit = {},
        crossinline onErrorAction: (throwable: Throwable) -> Unit = { _ -> },
        crossinline ioCall: suspend () -> T,
        crossinline onCalled: (model: T) -> Unit): Job {

    return launch(mainDispatcher) {
        supervisorScope {
            try {
                val task = async(ioDispatcher) {
                    ioCall()
                }
                onCalled(task.await())
            } catch (t: Throwable) {
                t.printStackTrace()
                onErrorAction(t)
            } finally {
                onCallExecuted()
            }
        }
    }
}


inline fun AndroidViewModel.makeIOCall(
        crossinline onCallExecuted: () -> Unit = {},
        crossinline ioCall: suspend () -> Unit): Job {

    return viewModelScope.launch(mainDispatcher) {
        supervisorScope {
            try {
                val task = async(ioDispatcher) {
                    ioCall()
                }
                task.await()
            } catch (t: Throwable) {
                t.printStackTrace()
            } finally {
                onCallExecuted()
            }
        }
    }
}

inline fun AndroidViewModel.makeIOCall(
        crossinline onCallExecuted: () -> Unit = {},
        crossinline onErrorAction: (throwable: Throwable) -> Unit = { _ -> },
        crossinline ioCall: suspend () -> Unit): Job {

    return viewModelScope.launch(mainDispatcher) {
        supervisorScope {
            try {
                val task = async(ioDispatcher) {
                    ioCall()
                }
                task.await()
            } catch (t: Throwable) {
                t.printStackTrace()
                onErrorAction(t)
            } finally {
                onCallExecuted()
            }
        }
    }
}


inline fun CoroutineScope.makeIOCall(
        crossinline onCallExecuted: () -> Unit = {},
        crossinline ioCall: suspend () -> Unit): Job {

    return launch(mainDispatcher) {
        supervisorScope {
            try {
                val task = async(ioDispatcher) {
                    ioCall()
                }
                task.await()
            } catch (t: Throwable) {
                t.printStackTrace()
            } finally {
                onCallExecuted()
            }
        }

    }
}


inline fun CoroutineScope.makeIOCall(
        crossinline onCallExecuted: () -> Unit = {},
        crossinline onErrorAction: (throwable: Throwable) -> Unit = { _ -> },
        crossinline ioCall: suspend () -> Unit): Job {
    return launch(mainDispatcher) {
        supervisorScope {
            try {
                val task = async(ioDispatcher) {
                    ioCall()
                }
                task.await()
            } catch (t: Throwable) {
                t.printStackTrace()
                onErrorAction(t)
            } finally {
                onCallExecuted()
            }
        }
    }
}