package com.example.myapplication.data.mappers.dbtoentity

import com.example.myapplication.data.db.entity.WindDb
import com.example.myapplication.domain.entity.WindEntity
import javax.inject.Inject

class WindDbToEntityMapper @Inject constructor() : (WindDb) -> WindEntity {

    override fun invoke(wind: WindDb): WindEntity {
        return WindEntity(
            speed = wind.speed
        )
    }
}
