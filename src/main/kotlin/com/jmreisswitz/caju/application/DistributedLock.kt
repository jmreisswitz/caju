package com.jmreisswitz.caju.application

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class DistributedLock(private val redisTemplate: RedisTemplate<String, String>) {

    fun <T> lock(key: String, timeout: Long, timeUnit: TimeUnit, action: () -> T): T? {
        val lockKey = "lock:$key"
        val lockValue = System.currentTimeMillis().toString()

        return if (tryLock(lockKey, lockValue, timeout, timeUnit)) {
            try {
                action()
            } finally {
                unlock(lockKey, lockValue)
            }
        } else {
            null
        }
    }

    private fun tryLock(key: String, value: String, timeout: Long, timeUnit: TimeUnit): Boolean {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit) == true
    }

    private fun unlock(key: String, value: String) {
        val currentValue = redisTemplate.opsForValue().get(key)
        if (currentValue == value) {
            redisTemplate.delete(key)
        }
    }
}