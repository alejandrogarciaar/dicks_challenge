package com.john.countrydetail.actions

import android.app.Activity
import android.content.Intent
import com.john.core.actions.CountryDetailAction
import com.john.core.navigation.DefaultAction
import com.john.core.navigation.DefaultActionHandler
import com.john.countrydetail.ui.activities.CountryDetailActivity
import javax.inject.Inject

class CountryDetailActionHandler @Inject constructor(
    private val context: Activity
) : DefaultActionHandler {

    override fun invoke(defaultAction: DefaultAction) {
        (defaultAction as? CountryDetailAction)?.let {
            context.startActivity(Intent(context, CountryDetailActivity::class.java).apply {
                putExtra(CountryDetailActivity.COUNTRY_NAME_KEY, it.countryName)
            })
        }
    }
}