package com.wirebarley.data.model

import com.wirebarley.domain.model.Quotes

data class QuotesEntity(
    val usdKrw: Double,
    val usdPhp: Double,
    val usdJpy: Double
)

fun QuotesEntity.asDomain() = Quotes(
    usdKrw = this.usdKrw,
    usdPhp = this.usdPhp,
    usdJpy = this.usdJpy
)
