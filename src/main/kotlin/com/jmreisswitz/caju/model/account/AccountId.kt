package com.jmreisswitz.caju.model.account

class AccountId(val value: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccountId

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}