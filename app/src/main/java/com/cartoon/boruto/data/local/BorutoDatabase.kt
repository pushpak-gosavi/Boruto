package com.cartoon.boruto.data.local

import androidx.room.Database
import com.cartoon.boruto.data.local.dao.HeroDao
import com.cartoon.boruto.data.local.dao.HeroRemoteKeyDao
import com.cartoon.boruto.domain.model.Hero
import com.cartoon.boruto.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
abstract class BorutoDatabase{

    abstract fun heroDao():HeroDao
    abstract fun heroRemoteKeyDao():HeroRemoteKeyDao

}