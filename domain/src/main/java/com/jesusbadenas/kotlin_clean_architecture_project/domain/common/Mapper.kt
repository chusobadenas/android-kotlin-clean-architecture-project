package com.jesusbadenas.kotlin_clean_architecture_project.domain.common

import io.reactivex.Observable.fromCallable

abstract class Mapper<in T, E> {

    abstract fun mapFrom(from: T): E

    abstract fun mapFrom(from: List<T>): List<E>

    fun observable(from: T) = fromCallable { mapFrom(from) }

    fun observable(from: List<T>) = fromCallable { from.map { mapFrom(it) } }
}
