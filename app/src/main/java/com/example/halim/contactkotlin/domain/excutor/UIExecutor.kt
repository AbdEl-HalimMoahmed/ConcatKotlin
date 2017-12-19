package com.halim.fashiononwheels.domain.excutor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class UIExecutor @Inject constructor() : PostExecutionThread {
    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}