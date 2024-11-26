package com.john.dickstest.actions

import com.john.core.navigation.DefaultAction
import com.john.core.navigation.DefaultActionDispatcher
import com.john.core.navigation.DefaultActionHandler
import com.john.core.navigation.DestinationType
import javax.inject.Inject

class DefaultActionDispatcherImpl @Inject constructor(
    private val actions: Map<DestinationType, @JvmSuppressWildcards DefaultActionHandler>
) : DefaultActionDispatcher {

    override fun invoke(destination: DestinationType, action: DefaultAction) {
        actions[destination]?.invoke(action)
    }
}