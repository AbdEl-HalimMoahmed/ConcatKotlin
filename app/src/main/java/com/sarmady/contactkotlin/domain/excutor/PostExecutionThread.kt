package com.sarmady.fashiononwheels.domain.excutor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}
