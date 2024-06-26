package week6

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.networkingretrofit.debugLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class BackgroundWorkViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {

    // Function to schedule a one-time work
    fun scheduleOneTimeWork() {
        // Create a variable to hold the work request
        // OneTimeWorkRequest is used for tasks that need to run just once
        val workRequest: OneTimeWorkRequest = OneTimeWorkRequest.Builder(LogWorker::class.java)
            // Set an initial delay of 5 seconds before the work starts
            .setInitialDelay(5, TimeUnit.SECONDS)
            // Finalize the creation of the work request
            .build()

        // Enqueue the work request with the WorkManager
        // Here we are telling the WorkManager to add our work request to the queue of tasks to be executed
        workManager.enqueue(workRequest)
    }

    // This function schedules a one-time work request with custom configurations.
    fun scheduleOneTimeWorkWithCustomization() {
        // Create input data for the work request.
        val inputData: Data = Data.Builder()
            // Add a string data with key "name" and value "Rajesh".
            .putString("name", "Rajesh")
            // Add an integer data with key "age" and value 27.
            .putInt("age", 27)
            // Build the Data object.
            .build()

        // Create constraints for the work request.
        val constraints = Constraints.Builder()
            // Set a constraint to require that the battery is not low.
            .setRequiresBatteryNotLow(true)
            // Build the Constraints object.
            .build()

        // Create a one-time work request with custom configurations.
        val workRequest: OneTimeWorkRequest =
            OneTimeWorkRequest.Builder(LogWorkerWithCustomization::class.java)
                // Set the input data for the work request.
                .setInputData(inputData)
                // Set the constraints for the work request.
                .setConstraints(constraints)
                // Add a tag to the work request for easier identification.
                .addTag("MyCustomTag")
                // Set an initial delay of 5 seconds before the work starts.
                .setInitialDelay(5, TimeUnit.SECONDS)
                // Build the OneTimeWorkRequest object.
                .build()

        // Enqueue the work request to be executed by WorkManager.
        workManager.enqueue(workRequest)

        // Launch a coroutine to observe the work request's state.
        viewModelScope.launch {
            // Collect the WorkInfo updates for the enqueued work request.
            workManager.getWorkInfoByIdFlow(workRequest.id).collect { workInfo ->
                // Check the current state of the work request.
                when (workInfo.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        // Get the output data from the succeeded work request.
                        val result = workInfo.outputData.getString("result")
                        // Log the result obtained from the succeeded work request.
                        debugLog("Got this data from Succeeded worker: $result")
                    }

                    WorkInfo.State.FAILED -> {
                        // Get the output data from the failed work request.
                        val result = workInfo.outputData.getString("result")
                        // Log the result obtained from the failed work request.
                        debugLog("Got this data from Failed worker: $result")
                    }

                    else -> {}
                }
            }
        }
    }

    // This annotation ensures the code runs only on Android O (Oreo) and above
    @RequiresApi(Build.VERSION_CODES.O)
    fun schedulePeriodicWork() {
        // Create a periodic work request to run LogWorkerWithCustomization every 15 minutes
        val workRequest = PeriodicWorkRequest.Builder(
            LogWorkerWithCustomization::class.java, // The worker class that will perform the task
            15, // The repeat interval
            TimeUnit.MINUTES // The time unit for the interval
        )
            .setInitialDelay(5, TimeUnit.SECONDS) // Optional: Delay before the first run
            .build() // Build the work request

        // Enqueue the work request to the WorkManager
        workManager.enqueue(workRequest)
    }



}