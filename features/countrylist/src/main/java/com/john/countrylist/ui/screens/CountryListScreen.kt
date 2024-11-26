package com.john.countrylist.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.john.countrylist.domain.models.Country
import com.john.countrylist.ui.actions.CountryListScreenActions

@Composable
internal fun CountryListScreen(
    paddingValues: PaddingValues,
    items: List<Country>,
    actions: CountryListScreenActions
) {
    LazyColumn(
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(paddingValues),
        content = {
            items(items) { country ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { actions.onCountryClicked(country) }
                ) {
                    AsyncImage(
                        model = country.flags.png,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(12.dp)
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = country.name.common,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = country.name.official,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = country.capital?.firstOrNull().orEmpty(),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    )
}
