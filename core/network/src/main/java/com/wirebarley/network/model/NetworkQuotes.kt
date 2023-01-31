package com.wirebarley.network.model

import com.wirebarley.data.model.QuotesEntity
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class NetworkQuotes(
    @SerialName("USDKRW")
    val usdKrw: Double,
    @SerialName("USDPHP")
    val usdPhp: Double,
    @SerialName("USDJPY")
    val usdJpy: Double
)

fun NetworkQuotes.asEntity() = QuotesEntity(
    usdKrw = this.usdKrw,
    usdPhp = this.usdPhp,
    usdJpy = this.usdJpy
)