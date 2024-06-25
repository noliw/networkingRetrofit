package week6

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.networkingretrofit.debugLog

class LogWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    // Override the doWork() method to define what the Worker should do
    override fun doWork(): Result {
        debugLog("This is printed from: ${this::class.simpleName}")
        // Indicate that the work finished successfully
        return Result.success()
    }
}