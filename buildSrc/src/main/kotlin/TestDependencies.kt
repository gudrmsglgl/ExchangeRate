object TestDependencies {
    val junit by lazy { "junit:junit:4.13.2" }
    val junitAndroidx by lazy { "androidx.test.ext:junit:1.1.5" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:3.5.1" }

    val mockk by lazy { "io.mockk:mockk:1.13.4"}

    object KoTest {
        private const val version = "5.5.0"
        val jvm by lazy { "io.kotest:kotest-runner-junit5-jvm:$version" }
        val runner by lazy { "io.kotest:kotest-runner-junit5:$version" }
        val assertion by lazy { "io.kotest:kotest-assertions-core:$version" }
        val property by lazy { "io.kotest:kotest-property:$version" }
    }

}