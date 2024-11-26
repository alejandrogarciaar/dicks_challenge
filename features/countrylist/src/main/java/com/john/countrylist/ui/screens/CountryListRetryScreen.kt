package com.john.countrylist.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.john.countrylist.R

@Composable
fun CountryListRetryScreen(
    onRetryClicked: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.country_list_empty_state))
            Spacer(modifier = Modifier.size(8.dp))
            Button(
                onClick = { onRetryClicked.invoke() },
                content = { Text(text = stringResource(id = R.string.country_list_retry)) }
            )
        }
    }
}