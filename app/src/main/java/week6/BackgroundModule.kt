package week6

import android.content.Context
import androidx.startup.Initializer
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

// This class is a Hilt module that provides dependencies.
@Module
// This annotation specifies that the module is installed in the SingletonComponent,
// making its provided dependencies available throughout the entire application.
@InstallIn(SingletonComponent::class)
// This class is named BackgroundWorkModule and implements the Initializer interface for WorkManager.
// It provides the necessary configuration to initialize WorkManager.
class BackgroundWorkModule : Initializer<WorkManager> {

    // This annotation indicates that the following method provides a dependency.
    @Provides
    // This method overrides the create method to provide a WorkManager instance.
    // It takes the application context as a parameter and returns the WorkManager instance.
    override fun create(@ApplicationContext context: Context): WorkManager {
        // More info on why we've disabled this https://stackoverflow.com/a/77142935/6315400
        // This commented-out line shows how to create a custom configuration for WorkManager.
        // val configuration = Configuration.Builder().build()
        // This commented-out line shows how to initialize WorkManager with the custom configuration.
        // WorkManager.initialize(context, configuration)
        // This line returns the WorkManager instance for the given context.
        return WorkManager.getInstance(context)
    }

    // This method specifies any dependencies required by this initializer.
    // It returns an empty list, indicating no dependencies.
    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
