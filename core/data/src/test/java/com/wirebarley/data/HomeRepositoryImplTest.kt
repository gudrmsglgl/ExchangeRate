package com.wirebarley.data

import com.wirebarley.data.model.CurrencyResponseEntity
import com.wirebarley.data.model.QuotesEntity
import com.wirebarley.data.model.asDomain
import com.wirebarley.data.source.HomeRemoteSource
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class HomeRepositoryImplTest : BehaviorSpec ({
    val homeRemoteSource = mockk<HomeRemoteSource>(relaxed = true)
    val homeRepositoryImpl = HomeRepositoryImpl(homeRemoteSource)

    given("환율을 USD 기준으로 호출한 상황에서") {
        val responseEntity = CurrencyResponseEntity(
            source = "USD",
            success = true,
            timestamp = 1675333743,
            quotes = QuotesEntity(
                usdKrw = 1223.689876,
                usdJpy = 128.9204,
                usdPhp = 53.8725
            )
        )
        coEvery { homeRemoteSource.getOtherCurrencyData() } returns responseEntity

        When("HomeRepositoryImpl 을 실행할 때") {
            val actualCurrencyResponse = homeRepositoryImpl.getOtherCurrencyData()

            Then("HomeRemoteSource 호출 실행 검증") {
                coVerify {
                    homeRemoteSource.getOtherCurrencyData()
                }
            }

            Then("Entity 에서 도메인 데이터로 데이터 변환 검증 > source, timestamp \n quotes(USDKRW, USDJPY, USDPHP)") {
                val expect = responseEntity.asDomain()
                actualCurrencyResponse.source shouldBe expect.source
                actualCurrencyResponse.timestamp shouldBe expect.timestamp
                actualCurrencyResponse.quotes.usdKrw shouldBe expect.quotes.usdKrw
                actualCurrencyResponse.quotes.usdJpy shouldBe expect.quotes.usdJpy
                actualCurrencyResponse.quotes.usdPhp shouldBe expect.quotes.usdPhp
            }
        }
    }
})