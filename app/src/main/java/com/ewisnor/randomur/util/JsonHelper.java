package com.ewisnor.randomur.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Helper functions for deserializing JSON with the GSON library.
 *
 * Created by evan on 2015-01-02.
 */
public class JsonHelper {

    /**
     * Get a configured GSON builder.
     * @return GsonBuilder
     */
    public static GsonBuilder getGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder;
    }

    /**
     * Parse a JSON string into the specified model class.
     *
     * @param json JSON string
     * @param resultType The model type that the JSON will be deserialized into
     * @param <T> The model type that the JSON will be deserialized into
     * @return A populated instance of the model type
     */
    public static <T extends Object> T parseJson(String json, Class<T> resultType) {
        Gson gson = JsonHelper.getGsonBuilder().create();
        return gson.fromJson(json, resultType);
    }
}
