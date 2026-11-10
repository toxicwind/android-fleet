package com.ju.lib.datalayer.database.utils;

import android.database.Cursor;
import com.ju.lib.datalayer.database.annotation.Column;
import com.ju.lib.datalayer.database.annotation.Ignore;
import com.ju.lib.datalayer.database.annotation.MapCollection;
import com.ju.lib.datalayer.database.annotation.Mapping;
import com.ju.lib.datalayer.database.model.PrimaryKey;
import com.ju.lib.datalayer.database.model.Property;
import com.ju.lib.datalayer.database.table.TableManager;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class FieldUtils {
    public static Object get(Field field, Object obj) {
        field.setAccessible(true);
        return field.get(obj);
    }

    public static List<Field> getAllDeclaredFields(Class<?> cls) {
        LinkedList linkedList = new LinkedList();
        while (cls != null && cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                if (!isInvalid(field)) {
                    linkedList.addLast(field);
                }
            }
            cls = cls.getSuperclass();
        }
        return linkedList;
    }

    public static Object getAssignedKeyObject(PrimaryKey primaryKey, Object obj) {
        Object obj2 = get(primaryKey.field, obj);
        if (primaryKey.isAssignedByMyself() || (primaryKey.isAssignedBySystem() && obj2 != null && ((Number) obj2).longValue() > 0)) {
            return obj2;
        }
        return null;
    }

    public static Class<?> getComponentType(Field field) {
        return field.getType().getComponentType();
    }

    public static Class<?> getGenericType(Field field) {
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            if (genericType instanceof Class) {
                return (Class) genericType;
            }
            return null;
        }
        Type type = ((ParameterizedType) genericType).getActualTypeArguments()[0];
        if (type instanceof Class) {
            return (Class) type;
        }
        return null;
    }

    public static Object getPrimaryKey(Object obj) {
        Field field = TableManager.getTable(obj).key.field;
        if (field != null) {
            try {
                return get(field, obj);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static Object getPrimaryKeyFromCursor(Cursor cursor, Property property, int i) {
        int i2 = property.classType;
        if (i2 == 4) {
            return Float.valueOf(cursor.getFloat(i));
        }
        if (i2 == 5) {
            return Long.valueOf(cursor.getLong(i));
        }
        if (i2 != 6) {
            return i2 != 7 ? cursor.getString(i) : Short.valueOf(cursor.getShort(i));
        }
        return Integer.valueOf(cursor.getInt(i));
    }

    public static boolean isIgnored(Field field) {
        return field.getAnnotation(Ignore.class) != null || (field.getAnnotation(Column.class) == null && field.getAnnotation(Mapping.class) == null && field.getAnnotation(MapCollection.class) == null);
    }

    public static boolean isInteger(Field field) {
        return field.getType() == Integer.TYPE || field.getType() != Integer.class;
    }

    public static boolean isInvalid(Field field) {
        return (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) || isIgnored(field) || field.isSynthetic();
    }

    public static boolean isLong(Field field) {
        return field.getType() == Long.TYPE || field.getType() == Long.class;
    }

    public static boolean isNumber(Class<?> cls) {
        return cls == Long.TYPE || cls == Long.class || cls == Integer.TYPE || cls == Integer.class || cls == Short.TYPE || cls == Short.class || cls == Byte.TYPE || cls == Byte.class;
    }

    public static boolean isSerializable(Field field) {
        for (Class<?> cls : field.getType().getInterfaces()) {
            if (Serializable.class == cls) {
                return true;
            }
        }
        return false;
    }

    public static void set(Field field, Object obj, Object obj2) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(obj, obj2);
    }

    public static boolean setKeyValueIfneed(Object obj, PrimaryKey primaryKey, Object obj2, long j) {
        if (primaryKey == null || !primaryKey.isAssignedBySystem()) {
            return false;
        }
        if (obj2 != null && ((Number) obj2).longValue() >= 1) {
            return false;
        }
        setNumber(obj, primaryKey.field, j);
        return true;
    }

    public static void setNumber(Object obj, Field field, long j) throws IllegalAccessException {
        field.setAccessible(true);
        Class<?> type = field.getType();
        if (type == Long.TYPE) {
            field.setLong(obj, j);
            return;
        }
        if (type == Integer.TYPE) {
            field.setInt(obj, (int) j);
            return;
        }
        if (type == Short.TYPE) {
            field.setShort(obj, (short) j);
            return;
        }
        if (type == Byte.TYPE) {
            field.setByte(obj, (byte) j);
            return;
        }
        if (type == Long.class) {
            field.set(obj, new Long(j));
            return;
        }
        if (type == Integer.class) {
            field.set(obj, new Integer((int) j));
        } else if (type == Short.class) {
            field.set(obj, new Short((short) j));
        } else {
            if (type != Byte.class) {
                throw new RuntimeException("field is not a number class");
            }
            field.set(obj, new Byte((byte) j));
        }
    }
}
