package com.sarmady.contactkotlin


import org.junit.jupiter.api.*
import java.util.logging.Logger


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
interface TestLifecycleLogger {

    companion object {
        val LOG: Logger = Logger.getLogger(TestLifecycleLogger::class.java.name)
    }

    @BeforeEach
    fun beforeEachTest(testInfo: TestInfo) {
        LOG.info {
            String.format("About to execute [%s]",
                    testInfo.displayName)
        }
    }

    @AfterEach
    fun afterEachTest(testInfo: TestInfo) {
        LOG.info {
            String.format("Finished executing [%s]",
                    testInfo.displayName)
        }
    }
}