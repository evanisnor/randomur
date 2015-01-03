package com.ewisnor.randomur.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by evan on 2015-01-02.
 */
public class JsonHelper {

    public static GsonBuilder getGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder;
    }

    public static <T extends Object> T parseJson(String json, Class<T> resultType) {
        Gson gson = JsonHelper.getGsonBuilder().create();
        return gson.fromJson(json, resultType);
    }
}
