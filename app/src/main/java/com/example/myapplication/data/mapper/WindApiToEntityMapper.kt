package com.example.myapplication.data.mapper

import com.example.myapplication.data.service.api.WindApi
import com.example.myapplication.presentation.findweather.entity.WindEntity
import javax.inject.Inject

class WindApiToEntityMapper @Inject constructor() : (WindApi) -> WindEntity {
    override fun invoke(wind: WindApi): WindEntity {
        return WindEntity(
            speed = wind.speed
        )
    }
}
