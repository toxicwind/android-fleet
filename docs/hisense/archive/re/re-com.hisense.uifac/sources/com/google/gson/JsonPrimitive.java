package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class JsonPrimitive extends JsonElement {
    private static final Class<?>[] PRIMITIVE_TYPES = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object value;

    public JsonPrimitive(Boolean bool) {
        setValue(bool);
    }

    public JsonPrimitive(Number number) {
        setValue(number);
    }

    public JsonPrimitive(String str) {
        setValue(str);
    }

    void setValue(Object obj) {
        if (obj instanceof Character) {
            this.value = String.valueOf(((Character) obj).charValue());
        } else {
            C$Gson$Preconditions.checkArgument((obj instanceof Number) || isPrimitiveOrString(obj));
            this.value = obj;
        }
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    Boolean getAsBooleanWrapper() {
        return (Boolean) this.value;
    }

    public boolean getAsBoolean() {
        if (isBoolean()) {
            return getAsBooleanWrapper().booleanValue();
        }
        return Boolean.parseBoolean(getAsString());
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public Number getAsNumber() {
        Object obj = this.value;
        return obj instanceof String ? new LazilyParsedNumber((String) this.value) : (Number) obj;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    public String getAsString() {
        if (isNumber()) {
            return getAsNumber().toString();
        }
        if (isBoolean()) {
            return getAsBooleanWrapper().toString();
        }
        return (String) this.value;
    }

    public double getAsDouble() {
        return isNumber() ? getAsNumber().doubleValue() : Double.parseDouble(getAsString());
    }

    public long getAsLong() {
        return isNumber() ? getAsNumber().longValue() : Long.parseLong(getAsString());
    }

    public int getAsInt() {
        return isNumber() ? getAsNumber().intValue() : Integer.parseInt(getAsString());
    }

    private static boolean isPrimitiveOrString(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : PRIMITIVE_TYPES) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long jDoubleToLongBits;
        if (this.value == null) {
            return 31;
        }
        if (isIntegral(this)) {
            jDoubleToLongBits = getAsNumber().longValue();
        } else {
            Object obj = this.value;
            if (obj instanceof Number) {
                jDoubleToLongBits = Double.doubleToLongBits(getAsNumber().doubleValue());
            } else {
                return obj.hashCode();
            }
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || JsonPrimitive.class != obj.getClass()) {
            return false;
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) obj;
        if (this.value == null) {
            return jsonPrimitive.value == null;
        }
        if (isIntegral(this) && isIntegral(jsonPrimitive)) {
            return getAsNumber().longValue() == jsonPrimitive.getAsNumber().longValue();
        }
        if ((this.value instanceof Number) && (jsonPrimitive.value instanceof Number)) {
            double dDoubleValue = getAsNumber().doubleValue();
            double dDoubleValue2 = jsonPrimitive.getAsNumber().doubleValue();
            if (dDoubleValue != dDoubleValue2) {
                return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
            }
            return true;
        }
        return this.value.equals(jsonPrimitive.value);
    }

    private static boolean isIntegral(JsonPrimitive jsonPrimitive) {
        Object obj = jsonPrimitive.value;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }
}
