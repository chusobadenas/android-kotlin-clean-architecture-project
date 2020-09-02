package com.jesusbadenas.kotlin_clean_architecture_project.common

abstract class Mapper<in T, E> {

    abstract fun mapFrom(from: T): E

    abstract fun mapFrom(from: List<T>): List<E>
}
