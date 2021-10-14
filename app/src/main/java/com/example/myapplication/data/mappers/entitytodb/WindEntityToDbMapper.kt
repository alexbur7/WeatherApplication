package com.example.myapplication.data.mappers.entitytodb

import com.example.myapplication.data.db.entity.WindDb
import com.example.myapplication.domain.entity.WindEntity
import javax.inject.Inject

class WindEntityToDbMapper @Inject constructor() : (WindEntity) -> WindDb {

    override fun invoke(wind: WindEntity): WindDb {
        return WindDb(
            speed = wind.speed
        )
    }
}
