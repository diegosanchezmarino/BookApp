package com.dsm.bookapplication.network.deserializers

import com.dsm.bookapplication.model.Ticker
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class GetTickerDeserializer: JsonDeserializer<Ticker> {

    override fun deserialize(jsonElement: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Ticker {

        val payloadObject: JsonObject = jsonElement.asJsonObject["payload"].asJsonObject

        var high = payloadObject.get("high").asBigDecimal
        var last = payloadObject.get("last").asBigDecimal
        var volume = payloadObject.get("volume").asBigDecimal
        var vwap = payloadObject.get("vwap").asBigDecimal
        var low = payloadObject.get("low").asBigDecimal
        var ask = payloadObject.get("ask").asBigDecimal
        var bid = payloadObject.get("bid").asBigDecimal
        var change24 = payloadObject.get("change_24").asBigDecimal
        var createdAt = payloadObject.get("created_at").asString
        var book = payloadObject.get("book").asString

        return Ticker(volume = volume, dayHigh = high, last = last,
            vwap = vwap, dayLow = low, ask = ask, bid = bid, createdAt = createdAt,
            bookName = book, change24 = change24)


    }


}