package com.example.besinkitabi.service

import com.example.besinkitabi.model.Besin
import retrofit2.http.GET

interface BesinAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json

    //baseurl : https://raw.githubusercontent.com/
    //endpoind : atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json")
    suspend fun getBesin() : List<Besin>
}