package com.wirebarley.network.model

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
