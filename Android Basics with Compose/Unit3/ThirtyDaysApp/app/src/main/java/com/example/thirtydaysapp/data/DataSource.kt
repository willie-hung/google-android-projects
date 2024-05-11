package com.example.thirtydaysapp.data

import com.example.thirtydaysapp.R
import com.example.thirtydaysapp.model.Day

class Datasource() {
    fun loadAffirmations(): List<Day> {
        return listOf<Day>(
            Day(R.string.day_id_1, R.string.day_title_1, R.string.day_description_1, R.drawable.image_a),
            Day(R.string.day_id_2, R.string.day_title_2, R.string.day_description_2, R.drawable.image_b),
            Day(R.string.day_id_3, R.string.day_title_3, R.string.day_description_3, R.drawable.image_c),
            Day(R.string.day_id_4, R.string.day_title_4, R.string.day_description_4, R.drawable.image_d),
        )
    }
}