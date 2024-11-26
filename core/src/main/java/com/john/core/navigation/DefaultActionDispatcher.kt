package com.john.core.navigation

interface DefaultActionDispatcher {
    fun invoke(
        destination: DestinationType,
        action: DefaultAction
    )
}