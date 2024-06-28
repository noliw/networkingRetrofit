package com.example.networkingretrofit.week6.multi_language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.networkingretrofit.R
import com.example.networkingretrofit.week6.VerticalSpacer

private const val s = "Writing Pragraphs"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiLanguageScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "MultiLanguageScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.writing_paragraphs), style = MaterialTheme.typography.titleMedium)
            VerticalSpacer(size = 8)
            Text(text = stringResource(R.string.paragraph_description).trimIndent())
            VerticalSpacer(size = 16)
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.Click))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiLanguageScreenPreview() {
    MultiLanguageScreen()
}