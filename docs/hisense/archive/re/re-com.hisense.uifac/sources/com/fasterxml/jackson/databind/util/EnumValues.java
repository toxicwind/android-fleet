package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class EnumValues implements Serializable {
    private static final long serialVersionUID = 1;
    private final Class<Enum<?>> _enumClass;
    private final SerializableString[] _textual;
    private final Enum<?>[] _values;

    private EnumValues(Class<Enum<?>> cls, SerializableString[] serializableStringArr) {
        this._enumClass = cls;
        this._values = cls.getEnumConstants();
        this._textual = serializableStringArr;
    }

    public static EnumValues constructFromName(MapperConfig<?> mapperConfig, Class<Enum<?>> cls) {
        Class<? extends Enum<?>> clsFindEnumType = ClassUtil.findEnumType(cls);
        Enum<?>[] enumArr = (Enum[]) clsFindEnumType.getEnumConstants();
        if (enumArr == null) {
            throw new IllegalArgumentException("Cannot determine enum constants for Class " + cls.getName());
        }
        String[] strArrFindEnumValues = mapperConfig.getAnnotationIntrospector().findEnumValues(clsFindEnumType, enumArr, new String[enumArr.length]);
        SerializableString[] serializableStringArr = new SerializableString[enumArr.length];
        int length = enumArr.length;
        for (int i = 0; i < length; i++) {
            Enum<?> r5 = enumArr[i];
            String strName = strArrFindEnumValues[i];
            if (strName == null) {
                strName = r5.name();
            }
            serializableStringArr[r5.ordinal()] = mapperConfig.compileString(strName);
        }
        return construct(cls, serializableStringArr);
    }

    public static EnumValues construct(Class<Enum<?>> cls, SerializableString[] serializableStringArr) {
        return new EnumValues(cls, serializableStringArr);
    }

    public SerializableString serializedValueFor(Enum<?> r1) {
        return this._textual[r1.ordinal()];
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }
}
