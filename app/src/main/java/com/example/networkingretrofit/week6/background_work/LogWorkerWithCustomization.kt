package com.example.networkingretrofit.week6.background_work

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.networkingretrofit.debugLog

// Define a worker class named LogWorkerWithCustomization
class LogWorkerWithCustomization(
    // The constructor takes a context and worker parameters
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    // Override the doWork method to define the task to be performed
    override fun doWork(): Result {
        try {
            // Retrieve the input data passed to the worker
            val data = inputData

            // Log a message indicating the worker's class name
            debugLog("This is printed from: ${this::class.simpleName}")
            // Log a message indicating the input data received
            debugLog("This is the input data we received")
            // Log the name received from the input data
            debugLog("Name: ${data.getString("name")}")
            // Log the age received from the input data
            debugLog("Age: ${data.getInt("age", 0)}")

            // Create output data indicating success
            val outputData = Data.Builder()
                .putString("result", "Success")
                .build()

            // Return a success result with the output data
            return Result.success(outputData)
        } catch (e: Exception) {
            // Create output data indicating failure and include the error message
            val outputData = Data.Builder()
                .putString("result", "Failure")
                .putString("error", e.message)
                .build()

            // Return a failure result with the output data
            return Result.failure(outputData)
        }
    }
}
