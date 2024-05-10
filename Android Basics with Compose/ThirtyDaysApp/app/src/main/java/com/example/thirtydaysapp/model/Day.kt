package com.example.thirtydaysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @StringRes val dayId: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val imageResourceId: Int,
)