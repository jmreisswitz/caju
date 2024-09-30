package com.jmreisswitz.caju.application

import java.util.concurrent.TimeUnit

interface DistributedLock {
    fun <T> lock(key: String, timeout: Long, timeUnit: TimeUnit, action: () -> T): T?
}