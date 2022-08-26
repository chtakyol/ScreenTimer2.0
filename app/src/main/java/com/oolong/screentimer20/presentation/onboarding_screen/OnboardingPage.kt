package com.oolong.screentimer20.presentation.onboarding_screen

sealed class OnboardingPage(
    val title: String,
    val description: String
) {
    object First: OnboardingPage(
        title = "Screen Timer",
        description = "Easy and Minimalistic"
    )

    object Second: OnboardingPage(
        title = "Set the time",
        description = "At the end of the time you set, your screen will be turn off."
    )

    object Third: OnboardingPage(
        title = "Permissions",
        description = "To provide turning off the screen we need to register the app as device admin. Before remove the app you need to disable admin privilege. You can find this preference with searching 'Device Admin Apps' in phone setting."
    )
}
