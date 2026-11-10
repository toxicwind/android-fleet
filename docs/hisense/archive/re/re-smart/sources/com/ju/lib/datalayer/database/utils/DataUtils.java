package com.ju.lib.datalayer.database.utils;

import android.annotation.TargetApi;
import android.database.Cursor;
import com.ju.lib.datalayer.database.JuOrmLog;
import com.ju.lib.datalayer.database.asist.Checker;
import com.ju.lib.datalayer.database.model.EntityTable;
import com.ju.lib.datalayer.database.model.PrimaryKey;
import com.ju.lib.datalayer.database.model.Property;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DataUtils {
    public static final String BLOB = " BLOB ";
    public static final int CLASS_TYPE_BOOLEAN = 2;
    public static final int CLASS_TYPE_BYTE = 8;
    public static final int CLASS_TYPE_BYTE_ARRAY = 9;
    public static final int CLASS_TYPE_CHAR = 10;
    public static final int CLASS_TYPE_DATE = 11;
    public static final int CLASS_TYPE_DOUBLE = 3;
    public static final int CLASS_TYPE_FLOAT = 4;
    public static final int CLASS_TYPE_INT = 6;
    public static final int CLASS_TYPE_LONG = 5;
    public static final int CLASS_TYPE_NONE = 0;
    public static final int CLASS_TYPE_SERIALIZABLE = 12;
    public static final int CLASS_TYPE_SHORT = 7;
    public static final int CLASS_TYPE_STRING = 1;
    public static final int CLASS_TYPE_UNKNOWN = 13;
    public static final int FIELD_TYPE_BLOB = 4;
    public static final int FIELD_TYPE_DATE = 5;
    public static final int FIELD_TYPE_LONG = 1;
    public static final int FIELD_TYPE_NULL = 0;
    public static final int FIELD_TYPE_REAL = 2;
    public static final int FIELD_TYPE_SERIALIZABLE = 6;
    public static final int FIELD_TYPE_STRING = 3;
    public static final String INTEGER = " INTEGER ";
    public static final String NULL = " NULL ";
    public static final String REAL = " REAL ";
    public static final String TAG = "DataUtils";
    public static final String TEXT = " TEXT ";
    public static final long serialVersionUID = 6668874253056236676L;

    public static List<?> arrayToList(Object[] objArr) {
        return Arrays.asList(objArr);
    }

    public static Object byteToObject(byte[] bArr) throws Throwable {
        ObjectInputStream objectInputStream = null;
        try {
            ObjectInputStream objectInputStream2 = new ObjectInputStream(new ByteArrayInputStream(bArr));
            try {
                Object object = objectInputStream2.readObject();
                objectInputStream2.close();
                return object;
            } catch (Throwable th) {
                th = th;
                objectInputStream = objectInputStream2;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @TargetApi(9)
    public static <T> T[] concat(T[] tArr, T[] tArr2) {
        T[] tArr3 = (T[]) Arrays.copyOf(tArr, tArr.length + tArr2.length);
        System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
        return tArr3;
    }

    @TargetApi(9)
    public static <T> T[] concatAll(T[] tArr, T[]... tArr2) {
        int length = tArr.length;
        for (T[] tArr3 : tArr2) {
            length += tArr3.length;
        }
        T[] tArr4 = (T[]) Arrays.copyOf(tArr, length);
        int length2 = tArr.length;
        for (T[] tArr5 : tArr2) {
            System.arraycopy(tArr5, 0, tArr4, length2, tArr5.length);
            length2 += tArr5.length;
        }
        return tArr4;
    }

    public static int getFieldClassType(Field field) {
        Class<?> type = field.getType();
        if (CharSequence.class.isAssignableFrom(type)) {
            return 1;
        }
        if (Boolean.TYPE.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type)) {
            return 2;
        }
        if (Double.TYPE.isAssignableFrom(type) || Double.class.isAssignableFrom(type)) {
            return 3;
        }
        if (Float.TYPE.isAssignableFrom(type) || Float.class.isAssignableFrom(type)) {
            return 4;
        }
        if (Long.TYPE.isAssignableFrom(type) || Long.class.isAssignableFrom(type)) {
            return 5;
        }
        if (Integer.TYPE.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)) {
            return 6;
        }
        if (Short.TYPE.isAssignableFrom(type) || Short.class.isAssignableFrom(type)) {
            return 7;
        }
        if (Byte.TYPE.isAssignableFrom(type) || Byte.class.isAssignableFrom(type)) {
            return 8;
        }
        if (byte[].class.isAssignableFrom(type) || Byte[].class.isAssignableFrom(type)) {
            return 9;
        }
        if (Character.TYPE.isAssignableFrom(type) || Character.class.isAssignableFrom(type)) {
            return 10;
        }
        if (Date.class.isAssignableFrom(type)) {
            return 11;
        }
        return Serializable.class.isAssignableFrom(type) ? 12 : 13;
    }

    public static String getSQLDataType(int i) {
        switch (i) {
            case 1:
            case 2:
            case 10:
                return TEXT;
            case 3:
            case 4:
                return REAL;
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
                return INTEGER;
            case 9:
            default:
                return BLOB;
        }
    }

    public static int getType(Object obj) {
        if (obj == null) {
            return 0;
        }
        if ((obj instanceof CharSequence) || (obj instanceof Boolean) || (obj instanceof Character)) {
            return 3;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return 2;
        }
        if (obj instanceof Number) {
            return 1;
        }
        if (obj instanceof Date) {
            return 5;
        }
        if (obj instanceof byte[]) {
            return 4;
        }
        return obj instanceof Serializable ? 6 : 0;
    }

    public static void injectDataToObject(Cursor cursor, Object obj, EntityTable entityTable) throws IllegalAccessException {
        PrimaryKey primaryKey;
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            String columnName = cursor.getColumnName(i);
            Property property = Checker.isEmpty(entityTable.pmap) ? null : entityTable.pmap.get(columnName);
            if (property == null && (primaryKey = entityTable.key) != null && columnName.equals(primaryKey.column)) {
                property = entityTable.key;
            }
            if (property != null) {
                Field field = property.field;
                field.setAccessible(true);
                switch (property.classType) {
                    case 1:
                        field.set(obj, cursor.getString(i));
                        break;
                    case 2:
                        field.set(obj, Boolean.valueOf(Boolean.parseBoolean(cursor.getString(i))));
                        break;
                    case 3:
                        field.set(obj, Double.valueOf(cursor.getDouble(i)));
                        break;
                    case 4:
                        field.set(obj, Float.valueOf(cursor.getFloat(i)));
                        break;
                    case 5:
                        field.set(obj, Long.valueOf(cursor.getLong(i)));
                        break;
                    case 6:
                        field.set(obj, Integer.valueOf(cursor.getInt(i)));
                        break;
                    case 7:
                        field.set(obj, Short.valueOf(cursor.getShort(i)));
                        break;
                    case 8:
                        if (cursor.getString(i) != null) {
                            field.set(obj, Byte.valueOf(Byte.parseByte(cursor.getString(i))));
                        }
                        break;
                    case 9:
                        field.set(obj, cursor.getBlob(i));
                        break;
                    case 10:
                        String string = cursor.getString(i);
                        if (!Checker.isEmpty(string)) {
                            field.set(obj, Character.valueOf(string.charAt(0)));
                        }
                        break;
                    case 11:
                        Long lValueOf = Long.valueOf(cursor.getLong(i));
                        if (lValueOf != null) {
                            field.set(obj, new Date(lValueOf.longValue()));
                        }
                        break;
                    case 12:
                        byte[] blob = cursor.getBlob(i);
                        if (blob != null) {
                            field.set(obj, byteToObject(blob));
                        }
                        break;
                }
            } else {
                JuOrmLog.w(TAG, "数据库字段[" + columnName + "]已在实体中被移除");
            }
        }
    }

    public static byte[] objectToByte(Object obj) throws Throwable {
        ObjectOutputStream objectOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream2.writeObject(obj);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                objectOutputStream2.close();
                return byteArray;
            } catch (Throwable th) {
                th = th;
                objectOutputStream = objectOutputStream2;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static Object[] arrayToList(Collection<?> collection) {
        return collection.toArray();
    }
}
