package com.andrewtsaturov.groupchat.presentation.common

import io.reactivex.Scheduler

interface ISchedulers {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun trampoline(): Scheduler
    fun newThread(): Scheduler
    fun io(): Scheduler
}