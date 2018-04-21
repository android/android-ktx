package androidx.core.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer

/**
 * distinct
 */

private class DistinctExt<T> : MediatorObserver<T, T> {

    override fun run(source: LiveData<T>, mediator: MediatorLiveData<T>, value: T?) {
        if (value != mediator.value) {
            mediator.value = value
        }
    }
}

fun <T> LiveData<T>.distinct(): LiveData<T> = createMediator(this, DistinctExt())
fun <T> NonNullMediatorLiveData<T>.distinct(): NonNullMediatorLiveData<T> = createMediator(this, DistinctExt())

/**
 * filter
 */

private class FilterExt<T>(private val predicate: (T?) -> Boolean) : MediatorObserver<T, T> {

    override fun run(source: LiveData<T>, mediator: MediatorLiveData<T>, value: T?) {
        if (predicate(value)) {
            mediator.value = value
        }
    }
}

fun <T> LiveData<T>.filter(predicate: (T?) -> Boolean): LiveData<T> = createMediator(this, FilterExt<T>(predicate))
fun <T> NonNullMediatorLiveData<T>.filter(predicate: (T) -> Boolean): NonNullMediatorLiveData<T> = createMediator(this, FilterExt<T>({
    predicate(it!!)
}))

/**
 * first
 */

private class FirstExt<T>() : MediatorObserver<T, T> {

    override fun run(source: LiveData<T>, mediator: MediatorLiveData<T>, value: T?) {
        mediator.value = value
        mediator.removeSource(source)
    }
}

fun <T> LiveData<T>.first(): LiveData<T> = createMediator(this, FirstExt())
fun <T> NonNullMediatorLiveData<T>.first(): NonNullMediatorLiveData<T> = createMediator(this, FirstExt())

/**
 * map
 */

private class MapExt<T, R>(private val mapper: (T?) -> R?) : MediatorObserver<T, R> {

    override fun run(source: LiveData<T>, mediator: MediatorLiveData<R>, value: T?) {
        mediator.value = mapper(value)
    }
}

fun <T, R> LiveData<T>.map(mapper: (T?) -> R?): LiveData<R> = createMediator(this, MapExt<T, R>(mapper))
fun <T, R> NonNullMediatorLiveData<T>.map(mapper: (T) -> R): NonNullMediatorLiveData<R> = createMediator(this, MapExt<T, R>({
    return@MapExt mapper(it!!)!!
}))

/**
 * nonNull
 */
fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
    val mediator: NonNullMediatorLiveData<T> = NonNullMediatorLiveData()
    mediator.addSource(this, Observer { it?.let { mediator.value = it } })
    return mediator
}

/**
 * observers
 */

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (t: T?) -> Unit): Removable {
    val removable: RemovableImpl<T> = RemovableImpl(this, Observer { observer(it) })
    observe(owner, removable.observer)
    return removable
}

fun <T> LiveData<T>.observeForever(observer: (t: T?) -> Unit): Removable {
    val removable: RemovableImpl<T> = RemovableImpl(this, Observer { observer(it) })
    observeForever(removable.observer)
    return removable
}

fun <T> NonNullMediatorLiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit): Removable {
    val removable: RemovableImpl<T> = RemovableImpl(this, Observer { it?.let(observer) })
    observe(owner, removable.observer)
    return removable
}

fun <T> NonNullMediatorLiveData<T>.observeForever(observer: (t: T) -> Unit): Removable {
    val removable: RemovableImpl<T> = RemovableImpl(this, Observer { it?.let(observer) })
    observeForever(removable.observer)
    return removable
}

/**
 * Supporting classes
 */

class NonNullMediatorLiveData<T> : MediatorLiveData<T>()

interface Removable {

    fun removeObserver()
}

private interface MediatorObserver<IN, OUT> {

    fun run(source: LiveData<IN>, mediator: MediatorLiveData<OUT>, value: IN?)
}

private fun <IN, OUT> createMediator(source: LiveData<IN>, observer: MediatorObserver<IN, OUT>): NonNullMediatorLiveData<OUT> {
    val mediator: NonNullMediatorLiveData<OUT> = NonNullMediatorLiveData()
    mediator.addSource(source, Observer { observer.run(source, mediator, it) })
    return mediator
}

private class RemovableImpl<T>(private val liveData: LiveData<T>, val observer: Observer<T>) : Removable {

    override fun removeObserver() {
        liveData.removeObserver(observer)
    }
}