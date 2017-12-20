package com.sarmady.contactkotlin.domain.usecase

import com.sarmady.fashiononwheels.domain.excutor.PostExecutionThread
import com.sarmady.fashiononwheels.domain.excutor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


abstract class UseCase<T, in Params>(val threadExecutor: ThreadExecutor,
                                     val uiExecutor: PostExecutionThread) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(params: Params, observer: DisposableObserver<T>) {
        disposables.add(buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutor.scheduler)
                .subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }
}