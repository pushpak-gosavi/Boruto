package com.cartoon.boruto.domain.model

import androidx.room.Entity
import com.cartoon.boruto.utils.Constants.HERO_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = HERO_REMOTE_KEY_DATABASE_TABLE)
data class HeroRemoteKey(
    val id:Int,
    val prevKey:Int?,
    val nextKey:Int?
)
