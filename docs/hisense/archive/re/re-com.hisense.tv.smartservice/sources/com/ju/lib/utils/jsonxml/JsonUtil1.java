package com.ju.lib.utils.jsonxml;

import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.ju.lib.utils.log.LogUtil;
import java.io.Reader;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes2.dex */
public class JsonUtil1 {
    public static final String TAG = "LogEngine_t_JsonUtil";

    public static <T> T fromJson(String str, Class<T> cls) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        T t = (T) new Gson().fromJson(str, (Class) cls);
        LogUtil.i(TAG, "fromJson(String json, Class<T> classOfT)   time= ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return t;
    }

    public static <T> T fromJsonEnableComplexMapKeySerialization(String str, Class<T> cls) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        T t = (T) new GsonBuilder().enableComplexMapKeySerialization().create().fromJson(str, (Class) cls);
        LogUtil.i(TAG, "fromJsonEnableComplexMapKeySerialization(String json, Class<T> classOfT)   time= ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return t;
    }

    public static boolean isGoodJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            new JsonParser().parse(str);
            return true;
        } catch (JsonParseException unused) {
            Log.e(TAG, "bad json: " + str);
            return false;
        }
    }

    public static String toJson(Object obj) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String json = new Gson().toJson(obj);
        LogUtil.i(TAG, "toJson(Object object)   time= ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return json;
    }

    public static <T> T fromJson(String str, Type type) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        T t = (T) new Gson().fromJson(str, type);
        LogUtil.i(TAG, "fromJson(String json, Type typeOfT)   time= ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return t;
    }

    public static String toJson(Object obj, Type type) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String json = new Gson().toJson(obj, type);
        LogUtil.i(TAG, "toJson(Object fromJson, Type typeOfSrc)   time= ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return json;
    }

    public static <T> T fromJsonEnableComplexMapKeySerialization(String str, Type type) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        T t = (T) new GsonBuilder().enableComplexMapKeySerialization().create().fromJson(str, type);
        LogUtil.i(TAG, "fromJsonEnableComplexMapKeySerialization(String json, Type typeOfT)   time= ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return t;
    }

    public static <T> T fromJson(Reader reader, Type type) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        T t = (T) new Gson().fromJson(reader, type);
        LogUtil.i(TAG, "fromJson(Reader json, Type typeOfT)   time= ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        return t;
    }
}
