package com.gulshansutey.newsfeeds.utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.gulshansutey.newsfeeds.model.Fact
import com.gulshansutey.newsfeeds.model.FeedResponseModel
import java.lang.reflect.Type


class NullObjectRemoverDeserializer : JsonDeserializer<FeedResponseModel> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): FeedResponseModel {
        val targetArray = json?.asJsonObject?.get("rows")?.asJsonArray

        val newArray: ArrayList<Fact> = ArrayList()
        if (targetArray != null)
            for (i in 0 until targetArray.size()) {
                if (!targetArray[i].asJsonObject.get("title").isJsonNull
                ) {
                    newArray.add(
                        GsonBuilder().create().fromJson(targetArray[i], Fact::class.java)
                    )
                }
            }
        return FeedResponseModel(json?.asJsonObject?.get("title")?.asString, newArray)
    }

}