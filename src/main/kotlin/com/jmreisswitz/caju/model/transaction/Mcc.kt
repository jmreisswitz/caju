package com.jmreisswitz.caju.model.transaction

class Mcc(
    val code: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Mcc

        return code == other.code
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}
