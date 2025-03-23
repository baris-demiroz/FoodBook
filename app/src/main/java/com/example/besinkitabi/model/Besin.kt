package com.example.besinkitabi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Besin(
    @ColumnInfo("isim")
    @SerializedName("isim") //bu jsondakiyle aynı olmalı
    val besinIsim : String?, // değişkenin olmama ihtimalinden nullable yapıyoruz

    @ColumnInfo("kalori")
    @SerializedName("kalori") //bu jsondakiyle aynı olmalı
    val besinKalori : String?,

    @ColumnInfo("karbonhidrat")
    @SerializedName("karbonhidrat") //bu jsondakiyle aynı olmalı
    val besinKarbonhidrat : String?,

    @ColumnInfo("protein")
    @SerializedName("protein") //bu jsondakiyle aynı olmalı
    val besinProtein : String?,

    @ColumnInfo("yag")
    @SerializedName("yag") //bu jsondakiyle aynı olmalı
    val besinYag : String?,

    @ColumnInfo("gorsel")
    @SerializedName("gorsel") //bu jsondakiyle aynı olmalı
    val besinGorsel : String?

) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}


/*
 İsimleri jsonlarla aynı yaparsak Genelde ben bunu kullanırım ama isimler çok değişikse üstteki gibi aynı olmayan şekildede yapılabilir


data class Besin(
    val isim : String?, değiken ismi farklı ise @SerializedName("isim") konulur
    val kalori : String?,
    val karbonhidrat : String?,
    val protein : String?,
    val yag : String?,
    val gorsel : String?


)

 */
