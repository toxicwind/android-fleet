package com.ju.lib.datalayer.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class SharedPreferencesUtils {
    public static final String DEFAULT_FILENAME = "ju";

    public static boolean getBoolean(Context context, String str, String str2, boolean z) {
        return context.getSharedPreferences(str, 0).getBoolean(str2, z);
    }

    public static SharedPreferences.Editor getEditor(Context context, String str, int i) {
        return context.getSharedPreferences(str, i).edit();
    }

    public static float getFloat(Context context, String str, float f2) {
        return context.getSharedPreferences(DEFAULT_FILENAME, 0).getFloat(str, f2);
    }

    public static int getInt(Context context, String str, int i) {
        return context.getSharedPreferences(DEFAULT_FILENAME, 0).getInt(str, i);
    }

    public static long getLong(Context context, String str, long j) {
        return context.getSharedPreferences(DEFAULT_FILENAME, 0).getLong(str, j);
    }

    public static SharedPreferences getSharedPreferences(Context context, String str, int i) {
        return context.getSharedPreferences(str, i);
    }

    public static String getString(Context context, String str, String str2) {
        return context.getSharedPreferences(DEFAULT_FILENAME, 0).getString(str, str2);
    }

    public static Set<String> getStringSet(Context context, String str, String str2, Set<String> set) {
        return context.getSharedPreferences(str, 0).getStringSet(str2, set);
    }

    public static boolean putBoolean(Context context, String str, boolean z) {
        return putBoolean(context, DEFAULT_FILENAME, str, z);
    }

    public static boolean putFloat(Context context, String str, float f2) {
        return putFloat(context, DEFAULT_FILENAME, str, f2);
    }

    public static boolean putInt(Context context, String str, int i) {
        return putInt(context, DEFAULT_FILENAME, str, i);
    }

    public static boolean putLong(Context context, String str, long j) {
        return putLong(context, DEFAULT_FILENAME, str, j);
    }

    public static boolean putString(Context context, String str, String str2) {
        return putString(context, DEFAULT_FILENAME, str, str2);
    }

    public static boolean putStringMap(Context context, Map<String, Object> map) {
        return putStringMap(context, DEFAULT_FILENAME, map);
    }

    public static boolean putStringSet(Context context, String str, Set set) {
        return putStringSet(context, DEFAULT_FILENAME, str, set);
    }

    public static boolean putBoolean(Context context, String str, String str2, boolean z) {
        return putBoolean(context, str, str2, z, 0);
    }

    public static boolean putFloat(Context context, String str, String str2, float f2) {
        return putFloat(context, str, str2, f2, 0);
    }

    public static boolean putInt(Context context, String str, String str2, int i) {
        return putInt(context, str, str2, i, 0);
    }

    public static boolean putLong(Context context, String str, String str2, long j) {
        return putLong(context, str, str2, j, 0);
    }

    public static boolean putString(Context context, String str, String str2, String str3) {
        return putString(context, str, str2, str3, 0);
    }

    public static boolean putStringMap(Context context, String str, Map<String, Object> map) {
        return putStringMap(context, str, map, 0);
    }

    public static boolean putStringSet(Context context, String str, String str2, Set set) {
        return putStringSet(context, str, str2, set, 0);
    }

    public static boolean getBoolean(Context context, String str, String str2, boolean z, int i) {
        return context.getSharedPreferences(str, i).getBoolean(str2, z);
    }

    public static float getFloat(Context context, String str, String str2, float f2) {
        return context.getSharedPreferences(str, 0).getFloat(str2, f2);
    }

    public static int getInt(Context context, String str, String str2, int i) {
        return context.getSharedPreferences(str, 0).getInt(str2, i);
    }

    public static long getLong(Context context, String str, String str2, long j) {
        return context.getSharedPreferences(str, 0).getLong(str2, j);
    }

    public static String getString(Context context, String str, String str2, String str3) {
        return context.getSharedPreferences(str, 0).getString(str2, str3);
    }

    public static Set<String> getStringSet(Context context, String str, String str2, Set<String> set, int i) {
        return context.getSharedPreferences(str, i).getStringSet(str2, set);
    }

    public static boolean putBoolean(Context context, String str, String str2, boolean z, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, i).edit();
        editorEdit.putBoolean(str2, z);
        return editorEdit.commit();
    }

    public static boolean putFloat(Context context, String str, String str2, float f2, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, i).edit();
        editorEdit.putFloat(str2, f2);
        return editorEdit.commit();
    }

    public static boolean putInt(Context context, String str, String str2, int i, int i2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, i2).edit();
        editorEdit.putInt(str2, i);
        return editorEdit.commit();
    }

    public static boolean putLong(Context context, String str, String str2, long j, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, i).edit();
        editorEdit.putLong(str2, j);
        return editorEdit.commit();
    }

    public static boolean putString(Context context, String str, String str2, String str3, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, i).edit();
        editorEdit.putString(str2, str3);
        return editorEdit.commit();
    }

    public static boolean putStringMap(Context context, String str, Map<String, Object> map, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, i).edit();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            editorEdit.putString(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return editorEdit.commit();
    }

    public static boolean putStringSet(Context context, String str, String str2, Set<String> set, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, i).edit();
        editorEdit.putStringSet(str2, set);
        return editorEdit.commit();
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return context.getSharedPreferences(DEFAULT_FILENAME, 0).getBoolean(str, z);
    }

    public static float getFloat(Context context, String str, String str2, float f2, int i) {
        return context.getSharedPreferences(str, i).getFloat(str2, f2);
    }

    public static int getInt(Context context, String str, String str2, int i, int i2) {
        return context.getSharedPreferences(str, i2).getInt(str2, i);
    }

    public static long getLong(Context context, String str, String str2, long j, int i) {
        return context.getSharedPreferences(str, i).getLong(str2, j);
    }

    public static String getString(Context context, String str, String str2, String str3, int i) {
        return context.getSharedPreferences(str, i).getString(str2, str3);
    }

    public static Set<String> getStringSet(Context context, String str, Set<String> set) {
        return context.getSharedPreferences(DEFAULT_FILENAME, 0).getStringSet(str, set);
    }
}
