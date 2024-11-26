package com.john.countrylist.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.john.core.actions.CountryDetailAction
import com.john.core.navigation.DefaultActionDispatcher
import com.john.core.navigation.DestinationType
import com.john.countrylist.ui.actions.CountryListScreenActions
import com.john.countrylist.ui.routing.CountryListRoute
import com.john.countrylist.ui.viewModel.CountryListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CountryListActivity : ComponentActivity() {

    @Inject
    lateinit var defaultActionDispatcher: DefaultActionDispatcher

    private val viewModel: CountryListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CountryListRoute(
                    viewModel = viewModel,
                    actions = CountryListScreenActions(
                        onCountryClicked = { country ->
                            defaultActionDispatcher.invoke(
                                destination = DestinationType.COUNTRY_DETAIL,
                                action = CountryDetailAction(
                                    countryName = country.name.common
                                )
                            )
                        },
                        onQueryChanged = { query ->
                            viewModel.getCountriesByQuery(query)
                        },
                        onRetryClicked = {
                            viewModel.getCountryList()
                        }
                    )
                )
            }
        }
    }
}