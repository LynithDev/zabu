package dev.lynith.core.utils

import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ScheduleUtils {

    companion object {

        @JvmStatic
        fun scheduleTask(task: () -> Unit, delay: Long) {
            val executor = ScheduledThreadPoolExecutor(1)

            executor.schedule(task, delay, TimeUnit.MILLISECONDS)
        }

    }
}
