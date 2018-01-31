package com.sarmady.contactkotlin.domain.usecase

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers


open class RetryDisposableObserver<T> : SimpleDisposableObserver<T>() {

    var emitter: ObservableEmitter<Any?>? = null
    val retryObservable = Observable.create<Any?> { emitter -> this@RetryDisposableObserver.emitter = emitter }

    fun setUpObservable(observable: Observable<T>): Observable<T> =
            observable.doOnError { onError({ emitter?.onNext(0) }, { emitter?.onComplete() }) }
                    .retryWhen { error -> error.flatMap { retryObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()) } }

    override fun onError(e: Throwable) {
    }

    open fun onError(onRetry: () -> Unit, onClose: () -> Unit) {
    }
}