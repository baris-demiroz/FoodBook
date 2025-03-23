package com.example.besinkitabi.roomdb

import androidx.room.Dao
import androidx.room.Insert

import androidx.room.Query
import com.example.besinkitabi.model.Besin

@Dao
interface BesinDAO {

    @Insert
    suspend fun insertAll(vararg besin: Besin) : List<Long> //eklenilen besinlerin id'sini Long olarak döndürüyor

    @Query("Select * From Besin Where uuid = :besinId")
    suspend fun getBesin(besinId : Int) : Besin

    @Query("Select * From Besin")
    suspend fun getAllBesin() : List<Besin>

    @Query("Delete From Besin")
    suspend fun deleteAllBesin()
}