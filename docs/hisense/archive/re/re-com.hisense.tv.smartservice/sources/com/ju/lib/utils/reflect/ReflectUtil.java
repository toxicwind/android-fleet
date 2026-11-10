package com.ju.lib.utils.reflect;

import b.a.a.a.a;
import com.ju.lib.utils.log.LogUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes2.dex */
public class ReflectUtil {
    public static final String TAG = "ReflectUtil";

    public static final Field _getField(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            LogUtil.e(TAG, "can not find a field with name " + str + " in class " + cls);
            return null;
        }
    }

    public static final Class getActualTypeClasses(Object obj, int i) {
        Class actualTypeClasses1 = getActualTypeClasses1(obj, i);
        return actualTypeClasses1 != null ? actualTypeClasses1 : getActualTypeClasses2(obj, i);
    }

    public static final Class getActualTypeClasses1(Object obj, int i) {
        TypeVariable<Class<?>>[] typeParameters = obj.getClass().getTypeParameters();
        if (typeParameters == null) {
            LogUtil.w(TAG, "variables is null when getTypeParameters.");
            return null;
        }
        if (typeParameters.length <= i) {
            LogUtil.w(TAG, "variables index out of bounds when getTypeParameters.");
            return null;
        }
        Type[] bounds = typeParameters[i].getBounds();
        if (bounds == null) {
            LogUtil.w(TAG, "bounds is null when getTypeParameters.");
            return null;
        }
        for (Type type : bounds) {
            if (type instanceof ParameterizedType) {
                StringBuilder sbA = a.a("type : ");
                ParameterizedType parameterizedType = (ParameterizedType) type;
                sbA.append(parameterizedType.getRawType());
                LogUtil.w(TAG, sbA.toString());
                return (Class) parameterizedType.getRawType();
            }
        }
        LogUtil.w(TAG, "no actual type when getTypeParameters, use Object.class.");
        return Object.class;
    }

    public static final Class getActualTypeClasses2(Object obj, int i) {
        ParameterizedType parameterizedType = (ParameterizedType) obj.getClass().getGenericSuperclass();
        if (parameterizedType == null) {
            LogUtil.w(TAG, "type is null when getGenericSuperclass.");
            return null;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments != null && actualTypeArguments.length >= i) {
            return (Class) actualTypeArguments[i];
        }
        LogUtil.w(TAG, "types is null or index out of bounds when getGenericSuperclass.");
        return null;
    }

    public static final <T> Constructor<T> getConstructor(String str, ClassLoader classLoader, Class... clsArr) {
        try {
            return getConstructor(Class.forName(str, true, classLoader), clsArr);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final Field getField(String str, String str2) {
        try {
            return getField(Class.forName(str), str2);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final Object getFieldObject(String str, String str2, Object obj) {
        Field field = getField(str, str2);
        if (field == null) {
            return null;
        }
        try {
            return field.get(obj);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final Method getMethod(String str, String str2, Class<?>... clsArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static final <T> T newInstance(Constructor<T> constructor, Object... objArr) {
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static final void setField(Class<?> cls, Object obj, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(str);
        boolean zIsAccessible = declaredField.isAccessible();
        if (!zIsAccessible) {
            declaredField.setAccessible(true);
        }
        declaredField.set(obj, obj2);
        if (zIsAccessible) {
            return;
        }
        declaredField.setAccessible(zIsAccessible);
    }

    public static final <T> Constructor<T> getConstructor(String str, Class... clsArr) {
        try {
            return getConstructor(Class.forName(str), clsArr);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final Field getField(Class cls, String str) {
        try {
            Field field_getField = _getField(cls, str);
            while (field_getField == null) {
                cls = cls.getSuperclass();
                if (cls == null) {
                    return null;
                }
                field_getField = _getField(cls, str);
            }
            field_getField.setAccessible(true);
            return field_getField;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final Object getFieldObject(Class cls, String str, Object obj) {
        Field field = getField(cls, str);
        if (field == null) {
            return null;
        }
        try {
            return field.get(obj);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final <T> Constructor<T> getConstructor(Class<T> cls, Class... clsArr) {
        try {
            return cls.getDeclaredConstructor(clsArr);
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
