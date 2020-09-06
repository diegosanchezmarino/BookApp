package com.dsm.bookapplication.network.deserializers

import com.dsm.bookapplication.model.Book
import com.google.gson.*
import java.lang.reflect.Type

class GetBooksDeserializer: JsonDeserializer<List<Book>> {
    override fun deserialize(jsonElement: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): List<Book> {

        val payloadArray: JsonArray = jsonElement.asJsonObject["payload"].asJsonArray

        var bookList = ArrayList<Book>()
        payloadArray.forEach {
            var name = it.asJsonObject.get("book").asString
            var minimumAmount = it.asJsonObject.get("minimum_amount").asBigDecimal
            var maximumAmount = it.asJsonObject.get("maximum_amount").asBigDecimal
            var minimumPrice = it.asJsonObject.get("minimum_price").asBigDecimal
            var maximumPrice = it.asJsonObject.get("maximum_price").asBigDecimal
            var minimumValue = it.asJsonObject.get("minimum_value").asBigDecimal
            var maximumValue = it.asJsonObject.get("maximum_value").asBigDecimal
            bookList.add(Book(
                name = name,
                minimumAmount = minimumAmount,
                maximumAmount = maximumAmount,
                minimumPrice = minimumPrice,
                maximumPrice = maximumPrice,
                minimumValue = minimumValue,
                maximumValue = maximumValue))
        }

        return bookList
    }


}