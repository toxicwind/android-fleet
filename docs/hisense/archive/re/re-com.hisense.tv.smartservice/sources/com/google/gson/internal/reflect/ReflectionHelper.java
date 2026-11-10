package com.google.gson.internal.reflect;

import b.a.a.a.a;
import com.google.gson.JsonIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class ReflectionHelper {
    public static String constructorToString(Constructor<?> constructor) {
        StringBuilder sb = new StringBuilder(constructor.getDeclaringClass().getName());
        sb.append('#');
        sb.append(constructor.getDeclaringClass().getSimpleName());
        sb.append('(');
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(parameterTypes[i].getSimpleName());
        }
        sb.append(')');
        return sb.toString();
    }

    public static void makeAccessible(Field field) {
        try {
            field.setAccessible(true);
        } catch (Exception e2) {
            StringBuilder sbA = a.a("Failed making field '");
            sbA.append(field.getDeclaringClass().getName());
            sbA.append("#");
            sbA.append(field.getName());
            sbA.append("' accessible; either change its visibility or write a custom TypeAdapter for its declaring type");
            throw new JsonIOException(sbA.toString(), e2);
        }
    }

    public static String tryMakeAccessible(Constructor<?> constructor) {
        try {
            constructor.setAccessible(true);
            return null;
        } catch (Exception e2) {
            StringBuilder sbA = a.a("Failed making constructor '");
            sbA.append(constructorToString(constructor));
            sbA.append("' accessible; either change its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: ");
            sbA.append(e2.getMessage());
            return sbA.toString();
        }
    }
}
