package week6

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackgroundWorkScreen(
    backgroundWorkViewModel: BackgroundWorkViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "BackgroundWorkScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { backgroundWorkViewModel.scheduleOneTimeWork() }) {
                Text(text = "Schedule One time work")
            }

            Button(onClick = { backgroundWorkViewModel.scheduleOneTimeWorkWithCustomization() }) {
                Text(text = "Schedule One time work with customization")
            }

            Button(onClick = { backgroundWorkViewModel.schedulePeriodicWork() }) {
                Text(text = "Schedule periodic work")
            }

            Button(onClick = { backgroundWorkViewModel.scheduleImageUploadWork() }) {
                Text(text = "Schedule image upload work")
            }
        }
    }
}