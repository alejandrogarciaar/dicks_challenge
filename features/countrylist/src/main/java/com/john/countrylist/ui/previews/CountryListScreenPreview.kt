package com.john.countrylist.ui.previews

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.john.countrylist.domain.models.Country
import com.john.countrylist.ui.actions.CountryListScreenActions
import com.john.countrylist.ui.screens.CountryListScreen

@Preview(showBackground = true)
@Composable
private fun CountryListScreenPreview() {
    CountryListScreen(
        items = listOf(
            Country(
                name = Country.Name(
                    common = "United Kingdom",
                    official = "United Kingdom of Great Britain and Northern Ireland"
                ), capital = listOf("Dublin"), flags = Country.Flags(
                    png = "https://goo.gl/maps/FoDtc3UKMkFsXAjHA",
                    svg = "https://www.openstreetmap.org/relation/62149"
                )
            ),
            Country(
                name = Country.Name(
                    common = "United Kingdom",
                    official = "United Kingdom of Great Britain and Northern Ireland"
                ), capital = listOf("Dublin"), flags = Country.Flags(
                    png = "https://goo.gl/maps/FoDtc3UKMkFsXAjHA",
                    svg = "https://www.openstreetmap.org/relation/62149"
                )
            ),
            Country(
                name = Country.Name(
                    common = "United Kingdom",
                    official = "United Kingdom of Great Britain and Northern Ireland"
                ), capital = listOf("Dublin"), flags = Country.Flags(
                    png = "https://goo.gl/maps/FoDtc3UKMkFsXAjHA",
                    svg = "https://www.openstreetmap.org/relation/62149"
                )
            ),
            Country(
                name = Country.Name(
                    common = "United Kingdom",
                    official = "United Kingdom of Great Britain and Northern Ireland"
                ), capital = listOf("Dublin"), flags = Country.Flags(
                    png = "https://goo.gl/maps/FoDtc3UKMkFsXAjHA",
                    svg = "https://www.openstreetmap.org/relation/62149"
                )
            ),
            Country(
                name = Country.Name(
                    common = "United Kingdom",
                    official = "United Kingdom of Great Britain and Northern Ireland"
                ), capital = listOf("Dublin"), flags = Country.Flags(
                    png = "https://goo.gl/maps/FoDtc3UKMkFsXAjHA",
                    svg = "https://www.openstreetmap.org/relation/62149"
                )
            ),
            Country(
                name = Country.Name(
                    common = "United Kingdom",
                    official = "United Kingdom of Great Britain and Northern Ireland"
                ), capital = listOf("Dublin"), flags = Country.Flags(
                    png = "https://goo.gl/maps/FoDtc3UKMkFsXAjHA",
                    svg = "https://www.openstreetmap.org/relation/62149"
                )
            ),
            Country(
                name = Country.Name(
                    common = "United Kingdom",
                    official = "United Kingdom of Great Britain and Northern Ireland"
                ), capital = listOf("Dublin"), flags = Country.Flags(
                    png = "https://goo.gl/maps/FoDtc3UKMkFsXAjHA",
                    svg = "https://www.openstreetmap.org/relation/62149"
                )
            )
        ),
        actions = CountryListScreenActions(
            onQueryChanged = {},
            onCountryClicked = {},
            onRetryClicked = {}
        ),
        paddingValues = PaddingValues(8.dp)
    )
}