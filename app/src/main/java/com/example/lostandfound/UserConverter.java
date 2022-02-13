package com.example.lostandfound;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;

public class UserConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static User stringToUser(String data) {

        if (data == null) {
            return new User();
        }

        Type listType = new TypeToken<User>() {}.getType();

        return gson.fromJson(data, listType);
    }


    @TypeConverter
    public static String userToString(User user) {

        return gson.toJson(user);
    }

}
