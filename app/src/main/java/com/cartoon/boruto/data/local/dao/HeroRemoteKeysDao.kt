package com.cartoon.boruto.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cartoon.boruto.domain.model.HeroRemoteKeys

@Dao
interface HeroRemoteKeysDao {

    @Query("SELECT * FROM hero_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id:Int):HeroRemoteKeys?

    @Insert ( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKey(heroRemoteKeys:List<HeroRemoteKeys>)

    @Query ("DELETE FROM hero_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}