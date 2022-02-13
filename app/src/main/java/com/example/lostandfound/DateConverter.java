package com.example.lostandfound;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    private static Gson gson = new Gson();

    @TypeConverter
    public static Date stringToDate(String data) {

        if (data == null) {
            return new Date();
        }

        Type listType = new TypeToken<Date>() {}.getType();

        return gson.fromJson(data, listType);
    }


    @TypeConverter
    public static String dateToString(Date date) {

        return gson.toJson(date);
    }
}