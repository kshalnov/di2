package ru.gb.course1.di1.di

import android.annotation.SuppressLint
import kotlin.reflect.KClass

@SuppressLint("StaticFieldLeak")
object Di {
    private val dependenciesMap: HashMap<Qualifier, DependencyFactory<*>> = HashMap()

    fun get(qualifier: Qualifier): Any {
        return dependenciesMap[qualifier]?.get()
            ?: throw IllegalArgumentException("Нет такой зависимости")
    }

    fun put(qualifier: Qualifier, dependency: DependencyFactory<*>) {
        dependenciesMap[qualifier] = dependency
    }

}

data class Qualifier(
    val clazz: KClass<*>,
    val name: String = "default"
)

interface DependencyFactory<T> {
    fun get(): T
}

class Factory<T>(val create: () -> T) : DependencyFactory<T> {
    override fun get(): T {
        return create()
    }
}

class Singleton<T>(val create: () -> T) : DependencyFactory<T> {
    private var dependency: T? = null
    override fun get(): T {
        if (dependency == null) {
            dependency = create()
        }
        return dependency!!
    }
}

inline fun <reified T> put(dependencyFactory: DependencyFactory<T>) {
    Di.put(Qualifier(T::class), dependencyFactory)
}

inline fun <reified T> put(name: String, dependencyFactory: DependencyFactory<T>) {
    Di.put(Qualifier(T::class, name), dependencyFactory)
}

inline fun <reified T> get(): T {
    return Di.get(Qualifier(T::class)) as T
}

inline fun <reified T> get(name: String): T {
    return Di.get(Qualifier(T::class, name)) as T
}

inline fun <reified T> inject(): Lazy<T> = lazy {
    return@lazy get<T>()
}

inline fun <reified T> factory(name: String, noinline function: () -> T) {
    put(name, Factory(function))
}

inline fun <reified T> single(name: String, noinline function: () -> T) {
    put(name, Singleton(function))
}

inline fun <reified T> factory(noinline function: () -> T) {
    put(Factory(function))
}

inline fun <reified T> single(noinline function: () -> T) {
    put(Singleton(function))
}