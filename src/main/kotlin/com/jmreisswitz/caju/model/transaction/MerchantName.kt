package com.jmreisswitz.caju.model.transaction

class MerchantName(
    val value: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MerchantName

        return value.uppercase() == other.value.uppercase()
    }

    override fun hashCode(): Int {
        return value.uppercase().hashCode()
    }
}
