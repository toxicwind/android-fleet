package org.json;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter;
import j$.util.DesugarCollections;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: classes8.dex */
public class JSONObject {
    private final Map<String, Object> map;
    static final Pattern NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?");
    public static final Object NULL = new Null();
    private static final Set<String> EXCLUDED_RECORD_METHOD_NAMES = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList("get", "is", "set", "toString", "hashCode", "equals", "clone", "notify", "notifyAll", "wait")));

    private static final class Null {
        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public int hashCode() {
            return 0;
        }

        private Null() {
        }

        public String toString() {
            return AbstractJsonLexerKt.NULL;
        }
    }

    public Class<? extends Map> getMapType() {
        return this.map.getClass();
    }

    public JSONObject() {
        this.map = new HashMap();
    }

    public JSONObject(JSONObject jSONObject, String... strArr) {
        this(strArr.length);
        for (String str : strArr) {
            try {
                putOnce(str, jSONObject.opt(str));
            } catch (Exception unused) {
            }
        }
    }

    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this(jSONTokener, jSONTokener.getJsonParserConfiguration());
    }

    public JSONObject(JSONTokener jSONTokener, JSONParserConfiguration jSONParserConfiguration) throws JSONException {
        this();
        boolean z = jSONTokener.getPrevious() == 0;
        if (jSONTokener.nextClean() != '{') {
            throw jSONTokener.syntaxError("A JSONObject text must begin with '{'");
        }
        while (!parseJSONObject(jSONTokener, jSONParserConfiguration, z)) {
        }
    }

    private boolean parseJSONObject(JSONTokener jSONTokener, JSONParserConfiguration jSONParserConfiguration, boolean z) {
        char cNextClean = jSONTokener.nextClean();
        if (cNextClean == 0) {
            throw jSONTokener.syntaxError("A JSONObject text must end with '}'");
        }
        if (cNextClean == '}') {
            if (z && jSONParserConfiguration.isStrictMode() && jSONTokener.nextClean() != 0) {
                throw jSONTokener.syntaxError("Strict mode error: Unparsed characters found at end of input text");
            }
            return true;
        }
        Object objNextSimpleValue = jSONTokener.nextSimpleValue(cNextClean);
        String string = objNextSimpleValue.toString();
        checkKeyForStrictMode(jSONTokener, jSONParserConfiguration, objNextSimpleValue);
        if (jSONTokener.nextClean() != ':') {
            throw jSONTokener.syntaxError("Expected a ':' after a key");
        }
        if (string != null) {
            if (opt(string) != null && !jSONParserConfiguration.isOverwriteDuplicateKey()) {
                throw jSONTokener.syntaxError("Duplicate key \"" + string + "\"");
            }
            Object objNextValue = jSONTokener.nextValue();
            if (objNextValue != null) {
                put(string, objNextValue);
            }
        }
        return parseEndOfKeyValuePair(jSONTokener, jSONParserConfiguration, z);
    }

    private static boolean parseEndOfKeyValuePair(JSONTokener jSONTokener, JSONParserConfiguration jSONParserConfiguration, boolean z) {
        char cNextClean = jSONTokener.nextClean();
        if (cNextClean == ',') {
            if (jSONTokener.nextClean() == '}') {
                if (jSONParserConfiguration.isStrictMode()) {
                    throw jSONTokener.syntaxError("Strict mode error: Expected another object element");
                }
                return true;
            }
            if (jSONTokener.end()) {
                throw jSONTokener.syntaxError("A JSONObject text must end with '}'");
            }
            jSONTokener.back();
            return false;
        }
        if (cNextClean == ';') {
            if (jSONParserConfiguration.isStrictMode()) {
                throw jSONTokener.syntaxError("Strict mode error: Invalid character ';' found");
            }
            return false;
        }
        if (cNextClean == '}') {
            if (z && jSONParserConfiguration.isStrictMode() && jSONTokener.nextClean() != 0) {
                throw jSONTokener.syntaxError("Strict mode error: Unparsed characters found at end of input text");
            }
            return true;
        }
        throw jSONTokener.syntaxError("Expected a ',' or '}'");
    }

    private static void checkKeyForStrictMode(JSONTokener jSONTokener, JSONParserConfiguration jSONParserConfiguration, Object obj) {
        if (jSONParserConfiguration == null || !jSONParserConfiguration.isStrictMode()) {
            return;
        }
        if (obj instanceof Boolean) {
            throw jSONTokener.syntaxError(String.format("Strict mode error: key '%s' cannot be boolean", obj.toString()));
        }
        if (obj == NULL) {
            throw jSONTokener.syntaxError(String.format("Strict mode error: key '%s' cannot be null", obj.toString()));
        }
        if (obj instanceof Number) {
            throw jSONTokener.syntaxError(String.format("Strict mode error: key '%s' cannot be number", obj.toString()));
        }
    }

    public JSONObject(Map<?, ?> map) {
        this(map, 0, new JSONParserConfiguration());
    }

    public JSONObject(Map<?, ?> map, JSONParserConfiguration jSONParserConfiguration) {
        this(map, 0, jSONParserConfiguration);
    }

    private JSONObject(Map<?, ?> map, int i, JSONParserConfiguration jSONParserConfiguration) {
        if (i > jSONParserConfiguration.getMaxNestingDepth()) {
            throw new JSONException("JSONObject has reached recursion depth limit of " + jSONParserConfiguration.getMaxNestingDepth());
        }
        if (map == null) {
            this.map = new HashMap();
            return;
        }
        this.map = new HashMap(map.size());
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey() == null) {
                throw new NullPointerException("Null key.");
            }
            Object value = entry.getValue();
            if (value != null || jSONParserConfiguration.isUseNativeNulls()) {
                testValidity(value);
                this.map.put(String.valueOf(entry.getKey()), wrap(value, i + 1, jSONParserConfiguration));
            }
        }
    }

    public JSONObject(Object obj) {
        this();
        populateMap(obj, new JSONParserConfiguration());
    }

    public JSONObject(Object obj, JSONParserConfiguration jSONParserConfiguration) {
        this();
        populateMap(obj, jSONParserConfiguration);
    }

    private JSONObject(Object obj, Set<Object> set) {
        this();
        populateMap(obj, set, new JSONParserConfiguration());
    }

    public JSONObject(Object obj, String... strArr) {
        this(strArr.length);
        Class<?> cls = obj.getClass();
        for (String str : strArr) {
            try {
                putOpt(str, cls.getField(str).get(obj));
            } catch (Exception unused) {
            }
        }
    }

    public JSONObject(String str) throws JSONException {
        this(str, new JSONParserConfiguration());
    }

    public JSONObject(String str, JSONParserConfiguration jSONParserConfiguration) throws JSONException {
        this(new JSONTokener(str, jSONParserConfiguration), jSONParserConfiguration);
    }

    public JSONObject(String str, Locale locale) throws JSONException {
        this();
        ResourceBundle bundle = ResourceBundle.getBundle(str, locale, Thread.currentThread().getContextClassLoader());
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String strNextElement = keys.nextElement();
            if (strNextElement != null) {
                String str2 = strNextElement;
                String[] strArrSplit = str2.split("\\.");
                int length = strArrSplit.length - 1;
                JSONObject jSONObject = this;
                for (int i = 0; i < length; i++) {
                    String str3 = strArrSplit[i];
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str3);
                    if (jSONObjectOptJSONObject == null) {
                        jSONObjectOptJSONObject = new JSONObject();
                        jSONObject.put(str3, jSONObjectOptJSONObject);
                    }
                    jSONObject = jSONObjectOptJSONObject;
                }
                jSONObject.put(strArrSplit[length], bundle.getString(str2));
            }
        }
    }

    protected JSONObject(int i) {
        this.map = new HashMap(i);
    }

    public JSONObject accumulate(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object objOpt = opt(str);
        if (objOpt == null) {
            if (obj instanceof JSONArray) {
                obj = new JSONArray().put(obj);
            }
            put(str, obj);
            return this;
        }
        if (objOpt instanceof JSONArray) {
            ((JSONArray) objOpt).put(obj);
            return this;
        }
        put(str, new JSONArray().put(objOpt).put(obj));
        return this;
    }

    public JSONObject append(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object objOpt = opt(str);
        if (objOpt == null) {
            put(str, new JSONArray().put(obj));
            return this;
        }
        if (objOpt instanceof JSONArray) {
            put(str, ((JSONArray) objOpt).put(obj));
            return this;
        }
        throw wrongValueFormatException(str, "JSONArray", null, null);
    }

    public static String doubleToString(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return AbstractJsonLexerKt.NULL;
        }
        String string = Double.toString(d);
        if (string.indexOf(46) <= 0 || string.indexOf(101) >= 0 || string.indexOf(69) >= 0) {
            return string;
        }
        while (string.endsWith("0")) {
            string = string.substring(0, string.length() - 1);
        }
        return string.endsWith(".") ? string.substring(0, string.length() - 1) : string;
    }

    public Object get(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        }
        Object objOpt = opt(str);
        if (objOpt != null) {
            return objOpt;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] not found.");
    }

    public <E extends Enum<E>> E getEnum(Class<E> cls, String str) throws JSONException {
        E e = (E) optEnum(cls, str);
        if (e != null) {
            return e;
        }
        throw wrongValueFormatException(str, "enum of type " + quote(cls.getSimpleName()), opt(str), null);
    }

    public boolean getBoolean(String str) throws JSONException {
        Object obj = get(str);
        if (Boolean.FALSE.equals(obj)) {
            return false;
        }
        boolean z = obj instanceof String;
        if (z && "false".equalsIgnoreCase((String) obj)) {
            return false;
        }
        if (Boolean.TRUE.equals(obj)) {
            return true;
        }
        if (z && "true".equalsIgnoreCase((String) obj)) {
            return true;
        }
        throw wrongValueFormatException(str, "Boolean", obj, null);
    }

    public BigInteger getBigInteger(String str) throws JSONException {
        Object obj = get(str);
        BigInteger bigIntegerObjectToBigInteger = objectToBigInteger(obj, null);
        if (bigIntegerObjectToBigInteger != null) {
            return bigIntegerObjectToBigInteger;
        }
        throw wrongValueFormatException(str, "BigInteger", obj, null);
    }

    public BigDecimal getBigDecimal(String str) throws JSONException {
        Object obj = get(str);
        BigDecimal bigDecimalObjectToBigDecimal = objectToBigDecimal(obj, null);
        if (bigDecimalObjectToBigDecimal != null) {
            return bigDecimalObjectToBigDecimal;
        }
        throw wrongValueFormatException(str, "BigDecimal", obj, null);
    }

    public double getDouble(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, "double", obj, e);
        }
    }

    public float getFloat(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, TypedValues.Custom.S_FLOAT, obj, e);
        }
    }

    public Number getNumber(String str) throws JSONException {
        Object obj = get(str);
        try {
            if (obj instanceof Number) {
                return (Number) obj;
            }
            return stringToNumber(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, "number", obj, e);
        }
    }

    public int getInt(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, "int", obj, e);
        }
    }

    public JSONArray getJSONArray(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        throw wrongValueFormatException(str, "JSONArray", obj, null);
    }

    public JSONObject getJSONObject(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw wrongValueFormatException(str, "JSONObject", obj, null);
    }

    public long getLong(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, "long", obj, e);
        }
    }

    public static String[] getNames(JSONObject jSONObject) {
        if (jSONObject.isEmpty()) {
            return null;
        }
        return (String[]) jSONObject.keySet().toArray(new String[jSONObject.length()]);
    }

    public static String[] getNames(Object obj) {
        Field[] fields;
        int length;
        if (obj == null || (length = (fields = obj.getClass().getFields()).length) == 0) {
            return null;
        }
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = fields[i].getName();
        }
        return strArr;
    }

    public String getString(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw wrongValueFormatException(str, TypedValues.Custom.S_STRING, obj, null);
    }

    public boolean has(String str) {
        return this.map.containsKey(str);
    }

    public JSONObject increment(String str) throws JSONException {
        Object objOpt = opt(str);
        if (objOpt == null) {
            put(str, 1);
            return this;
        }
        if (objOpt instanceof Integer) {
            put(str, ((Integer) objOpt).intValue() + 1);
            return this;
        }
        if (objOpt instanceof Long) {
            put(str, ((Long) objOpt).longValue() + 1);
            return this;
        }
        if (objOpt instanceof BigInteger) {
            put(str, ((BigInteger) objOpt).add(BigInteger.ONE));
            return this;
        }
        if (objOpt instanceof Float) {
            put(str, ((Float) objOpt).floatValue() + 1.0f);
            return this;
        }
        if (objOpt instanceof Double) {
            put(str, ((Double) objOpt).doubleValue() + 1.0d);
            return this;
        }
        if (objOpt instanceof BigDecimal) {
            put(str, ((BigDecimal) objOpt).add(BigDecimal.ONE));
            return this;
        }
        throw new JSONException("Unable to increment [" + quote(str) + "].");
    }

    public boolean isNull(String str) {
        return NULL.equals(opt(str));
    }

    public Iterator<String> keys() {
        return keySet().iterator();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    protected Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public int length() {
        return this.map.size();
    }

    public void clear() {
        this.map.clear();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public JSONArray names() {
        if (this.map.isEmpty()) {
            return null;
        }
        return new JSONArray((Collection<?>) this.map.keySet());
    }

    public static String numberToString(Number number) throws JSONException {
        if (number == null) {
            throw new JSONException("Null pointer");
        }
        testValidity(number);
        String string = number.toString();
        if (string.indexOf(46) <= 0 || string.indexOf(101) >= 0 || string.indexOf(69) >= 0) {
            return string;
        }
        while (string.endsWith("0")) {
            string = string.substring(0, string.length() - 1);
        }
        return string.endsWith(".") ? string.substring(0, string.length() - 1) : string;
    }

    public Object opt(String str) {
        if (str == null) {
            return null;
        }
        return this.map.get(str);
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, String str) {
        return (E) optEnum(cls, str, null);
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, String str, E e) {
        try {
            Object objOpt = opt(str);
            if (NULL.equals(objOpt)) {
                return e;
            }
            if (cls.isAssignableFrom(objOpt.getClass())) {
                return (E) objOpt;
            }
            return (E) Enum.valueOf(cls, objOpt.toString());
        } catch (IllegalArgumentException | NullPointerException unused) {
        }
    }

    public boolean optBoolean(String str) {
        return optBoolean(str, false);
    }

    public boolean optBoolean(String str, boolean z) {
        Object objOpt = opt(str);
        if (NULL.equals(objOpt)) {
            return z;
        }
        if (objOpt instanceof Boolean) {
            return ((Boolean) objOpt).booleanValue();
        }
        try {
            return getBoolean(str);
        } catch (Exception unused) {
            return z;
        }
    }

    public Boolean optBooleanObject(String str) {
        return optBooleanObject(str, false);
    }

    public Boolean optBooleanObject(String str, Boolean bool) {
        Object objOpt = opt(str);
        if (!NULL.equals(objOpt)) {
            if (objOpt instanceof Boolean) {
                Boolean bool2 = (Boolean) objOpt;
                bool2.booleanValue();
                return bool2;
            }
            try {
                return Boolean.valueOf(getBoolean(str));
            } catch (Exception unused) {
            }
        }
        return bool;
    }

    public BigDecimal optBigDecimal(String str, BigDecimal bigDecimal) {
        return objectToBigDecimal(opt(str), bigDecimal);
    }

    static BigDecimal objectToBigDecimal(Object obj, BigDecimal bigDecimal) {
        return objectToBigDecimal(obj, bigDecimal, true);
    }

    static BigDecimal objectToBigDecimal(Object obj, BigDecimal bigDecimal, boolean z) {
        if (!NULL.equals(obj)) {
            if (obj instanceof BigDecimal) {
                return (BigDecimal) obj;
            }
            if (obj instanceof BigInteger) {
                return new BigDecimal((BigInteger) obj);
            }
            if ((obj instanceof Double) || (obj instanceof Float)) {
                Number number = (Number) obj;
                if (numberIsFinite(number)) {
                    if (z) {
                        return new BigDecimal(number.doubleValue());
                    }
                    return new BigDecimal(obj.toString());
                }
            } else {
                if ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                    return new BigDecimal(((Number) obj).longValue());
                }
                try {
                    return new BigDecimal(obj.toString());
                } catch (Exception unused) {
                }
            }
        }
        return bigDecimal;
    }

    public BigInteger optBigInteger(String str, BigInteger bigInteger) {
        return objectToBigInteger(opt(str), bigInteger);
    }

    static BigInteger objectToBigInteger(Object obj, BigInteger bigInteger) {
        if (!NULL.equals(obj)) {
            if (obj instanceof BigInteger) {
                return (BigInteger) obj;
            }
            if (obj instanceof BigDecimal) {
                return ((BigDecimal) obj).toBigInteger();
            }
            if ((obj instanceof Double) || (obj instanceof Float)) {
                Number number = (Number) obj;
                if (numberIsFinite(number)) {
                    return BigDecimal.valueOf(number.doubleValue()).toBigInteger();
                }
            } else {
                if ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                    return BigInteger.valueOf(((Number) obj).longValue());
                }
                try {
                    String string = obj.toString();
                    if (isDecimalNotation(string)) {
                        return new BigDecimal(string).toBigInteger();
                    }
                    return new BigInteger(string);
                } catch (Exception unused) {
                }
            }
        }
        return bigInteger;
    }

    public double optDouble(String str) {
        return optDouble(str, Double.NaN);
    }

    public double optDouble(String str, double d) {
        Number numberOptNumber = optNumber(str);
        return numberOptNumber == null ? d : numberOptNumber.doubleValue();
    }

    public Double optDoubleObject(String str) {
        return optDoubleObject(str, Double.valueOf(Double.NaN));
    }

    public Double optDoubleObject(String str, Double d) {
        Number numberOptNumber = optNumber(str);
        return numberOptNumber == null ? d : Double.valueOf(numberOptNumber.doubleValue());
    }

    public float optFloat(String str) {
        return optFloat(str, Float.NaN);
    }

    public float optFloat(String str, float f) {
        Number numberOptNumber = optNumber(str);
        return numberOptNumber == null ? f : numberOptNumber.floatValue();
    }

    public Float optFloatObject(String str) {
        return optFloatObject(str, Float.valueOf(Float.NaN));
    }

    public Float optFloatObject(String str, Float f) {
        Number numberOptNumber = optNumber(str);
        return numberOptNumber == null ? f : Float.valueOf(numberOptNumber.floatValue());
    }

    public int optInt(String str) {
        return optInt(str, 0);
    }

    public int optInt(String str, int i) {
        Number numberOptNumber = optNumber(str, null);
        return numberOptNumber == null ? i : numberOptNumber.intValue();
    }

    public Integer optIntegerObject(String str) {
        return optIntegerObject(str, 0);
    }

    public Integer optIntegerObject(String str, Integer num) {
        Number numberOptNumber = optNumber(str, null);
        return numberOptNumber == null ? num : Integer.valueOf(numberOptNumber.intValue());
    }

    public JSONArray optJSONArray(String str) {
        return optJSONArray(str, null);
    }

    public JSONArray optJSONArray(String str, JSONArray jSONArray) {
        Object objOpt = opt(str);
        return objOpt instanceof JSONArray ? (JSONArray) objOpt : jSONArray;
    }

    public JSONObject optJSONObject(String str) {
        return optJSONObject(str, null);
    }

    public JSONObject optJSONObject(String str, JSONObject jSONObject) {
        Object objOpt = opt(str);
        return objOpt instanceof JSONObject ? (JSONObject) objOpt : jSONObject;
    }

    public long optLong(String str) {
        return optLong(str, 0L);
    }

    public long optLong(String str, long j) {
        Number numberOptNumber = optNumber(str, null);
        return numberOptNumber == null ? j : numberOptNumber.longValue();
    }

    public Long optLongObject(String str) {
        return optLongObject(str, 0L);
    }

    public Long optLongObject(String str, Long l) {
        Number numberOptNumber = optNumber(str, null);
        return numberOptNumber == null ? l : Long.valueOf(numberOptNumber.longValue());
    }

    public Number optNumber(String str) {
        return optNumber(str, null);
    }

    public Number optNumber(String str, Number number) {
        Object objOpt = opt(str);
        if (!NULL.equals(objOpt)) {
            if (objOpt instanceof Number) {
                return (Number) objOpt;
            }
            try {
                return stringToNumber(objOpt.toString());
            } catch (Exception unused) {
            }
        }
        return number;
    }

    public String optString(String str) {
        return optString(str, "");
    }

    public String optString(String str, String str2) {
        Object objOpt = opt(str);
        return NULL.equals(objOpt) ? str2 : objOpt.toString();
    }

    private void populateMap(Object obj, JSONParserConfiguration jSONParserConfiguration) {
        populateMap(obj, Collections.newSetFromMap(new IdentityHashMap()), jSONParserConfiguration);
    }

    private void populateMap(Object obj, Set<Object> set, JSONParserConfiguration jSONParserConfiguration) {
        JSONObject jSONObject;
        Object obj2;
        Set<Object> set2;
        JSONParserConfiguration jSONParserConfiguration2;
        String keyNameFromMethod;
        Class<?> cls = obj.getClass();
        boolean zIsRecordType = isRecordType(cls);
        Method[] methods = getMethods(cls);
        int length = methods.length;
        int i = 0;
        while (i < length) {
            Method method = methods[i];
            if (!isValidMethod(method) || (keyNameFromMethod = getKeyNameFromMethod(method, zIsRecordType)) == null || keyNameFromMethod.isEmpty()) {
                jSONObject = this;
                obj2 = obj;
                set2 = set;
                jSONParserConfiguration2 = jSONParserConfiguration;
            } else {
                jSONObject = this;
                obj2 = obj;
                set2 = set;
                jSONParserConfiguration2 = jSONParserConfiguration;
                jSONObject.processMethod(obj2, set2, jSONParserConfiguration2, method, keyNameFromMethod);
            }
            i++;
            this = jSONObject;
            obj = obj2;
            set = set2;
            jSONParserConfiguration = jSONParserConfiguration2;
        }
    }

    private void processMethod(Object obj, Set<Object> set, JSONParserConfiguration jSONParserConfiguration, Method method, String str) {
        try {
            Object objInvoke = method.invoke(obj, null);
            if (objInvoke != null || jSONParserConfiguration.isUseNativeNulls()) {
                if (set.contains(objInvoke)) {
                    throw recursivelyDefinedObjectException(str);
                }
                set.add(objInvoke);
                testValidity(objInvoke);
                this.map.put(str, wrap(objInvoke, set));
                set.remove(objInvoke);
                closeClosable(objInvoke);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
        }
    }

    private static boolean isRecordType(Class<?> cls) {
        try {
            return ((Boolean) Class.class.getMethod("isRecord", null).invoke(cls, null)).booleanValue();
        } catch (NoSuchMethodException | Exception unused) {
            return false;
        }
    }

    private static Method[] getMethods(Class<?> cls) {
        return cls.getClassLoader() != null ? cls.getMethods() : cls.getDeclaredMethods();
    }

    private static boolean isValidMethodName(String str) {
        return ("getClass".equals(str) || "getDeclaringClass".equals(str)) ? false : true;
    }

    private static String getKeyNameFromMethod(Method method, boolean z) {
        String strSubstring;
        int annotationDepth;
        int annotationDepth2 = getAnnotationDepth(method, JSONPropertyIgnore.class);
        if (annotationDepth2 > 0 && ((annotationDepth = getAnnotationDepth(method, JSONPropertyName.class)) < 0 || annotationDepth2 <= annotationDepth)) {
            return null;
        }
        JSONPropertyName jSONPropertyName = (JSONPropertyName) getAnnotation(method, JSONPropertyName.class);
        if (annotationValueNotEmpty(jSONPropertyName)) {
            return jSONPropertyName.value();
        }
        String name = method.getName();
        if (name.startsWith("get") && name.length() > 3) {
            strSubstring = name.substring(3);
        } else if (name.startsWith("is") && name.length() > 2) {
            strSubstring = name.substring(2);
        } else {
            if (z && isRecordStyleAccessor(name, method)) {
                return name;
            }
            return null;
        }
        if (strSubstring.isEmpty() || Character.isLowerCase(strSubstring.charAt(0))) {
            return null;
        }
        if (strSubstring.length() == 1) {
            return strSubstring.toLowerCase(Locale.ROOT);
        }
        if (Character.isUpperCase(strSubstring.charAt(1))) {
            return strSubstring;
        }
        return strSubstring.substring(0, 1).toLowerCase(Locale.ROOT) + strSubstring.substring(1);
    }

    private static boolean isRecordStyleAccessor(String str, Method method) {
        Class<?> declaringClass;
        if (!str.isEmpty() && Character.isLowerCase(str.charAt(0)) && !EXCLUDED_RECORD_METHOD_NAMES.contains(str) && (declaringClass = method.getDeclaringClass()) != null && declaringClass != Object.class && !Enum.class.isAssignableFrom(declaringClass) && !Number.class.isAssignableFrom(declaringClass)) {
            String name = declaringClass.getName();
            if (!name.startsWith("java.") && !name.startsWith("javax.")) {
                return true;
            }
        }
        return false;
    }

    private static boolean annotationValueNotEmpty(JSONPropertyName jSONPropertyName) {
        return (jSONPropertyName == null || jSONPropertyName.value() == null || jSONPropertyName.value().isEmpty()) ? false : true;
    }

    private static boolean isValidMethod(Method method) {
        int modifiers = method.getModifiers();
        return Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && method.getParameterTypes().length == 0 && !method.isBridge() && method.getReturnType() != Void.TYPE && isValidMethodName(method.getName());
    }

    private static void closeClosable(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException unused) {
            }
        }
    }

    private static <A extends Annotation> A getAnnotation(Method method, Class<A> cls) {
        if (method != null && cls != null) {
            if (method.isAnnotationPresent(cls)) {
                return (A) method.getAnnotation(cls);
            }
            Class<?> declaringClass = method.getDeclaringClass();
            if (declaringClass.getSuperclass() == null) {
                return null;
            }
            for (Class<?> cls2 : declaringClass.getInterfaces()) {
                try {
                    return (A) getAnnotation(cls2.getMethod(method.getName(), method.getParameterTypes()), cls);
                } catch (NoSuchMethodException | SecurityException unused) {
                }
            }
            if (Object.class.equals(declaringClass.getSuperclass())) {
                return null;
            }
            try {
                return (A) getAnnotation(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()), cls);
            } catch (NoSuchMethodException | SecurityException unused2) {
            }
        }
        return null;
    }

    private static int getAnnotationDepth(Method method, Class<? extends Annotation> cls) {
        if (method != null && cls != null) {
            if (method.isAnnotationPresent(cls)) {
                return 1;
            }
            Class<?> declaringClass = method.getDeclaringClass();
            if (declaringClass.getSuperclass() == null) {
                return -1;
            }
            for (Class<?> cls2 : declaringClass.getInterfaces()) {
                try {
                    int annotationDepth = getAnnotationDepth(cls2.getMethod(method.getName(), method.getParameterTypes()), cls);
                    if (annotationDepth > 0) {
                        return annotationDepth + 1;
                    }
                } catch (NoSuchMethodException | SecurityException unused) {
                }
            }
            if (Object.class.equals(declaringClass.getSuperclass())) {
                return -1;
            }
            try {
                int annotationDepth2 = getAnnotationDepth(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()), cls);
                if (annotationDepth2 > 0) {
                    return annotationDepth2 + 1;
                }
            } catch (NoSuchMethodException | SecurityException unused2) {
            }
        }
        return -1;
    }

    public JSONObject put(String str, boolean z) throws JSONException {
        return put(str, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONObject put(String str, Collection<?> collection) throws JSONException {
        return put(str, new JSONArray(collection));
    }

    public JSONObject put(String str, double d) throws JSONException {
        return put(str, Double.valueOf(d));
    }

    public JSONObject put(String str, float f) throws JSONException {
        return put(str, Float.valueOf(f));
    }

    public JSONObject put(String str, int i) throws JSONException {
        return put(str, Integer.valueOf(i));
    }

    public JSONObject put(String str, long j) throws JSONException {
        return put(str, Long.valueOf(j));
    }

    public JSONObject put(String str, Map<?, ?> map) throws JSONException {
        return put(str, new JSONObject(map));
    }

    public JSONObject put(String str, Object obj) throws JSONException {
        if (str == null) {
            throw new NullPointerException("Null key.");
        }
        if (obj != null) {
            testValidity(obj);
            this.map.put(str, obj);
            return this;
        }
        remove(str);
        return this;
    }

    public JSONObject putOnce(String str, Object obj) throws JSONException {
        if (str == null || obj == null) {
            return this;
        }
        if (opt(str) != null) {
            throw new JSONException("Duplicate key \"" + str + "\"");
        }
        return put(str, obj);
    }

    public JSONObject putOpt(String str, Object obj) throws JSONException {
        return (str == null || obj == null) ? this : put(str, obj);
    }

    public Object query(String str) {
        return query(new JSONPointer(str));
    }

    public Object query(JSONPointer jSONPointer) {
        return jSONPointer.queryFrom(this);
    }

    public Object optQuery(String str) {
        return optQuery(new JSONPointer(str));
    }

    public Object optQuery(JSONPointer jSONPointer) {
        try {
            return jSONPointer.queryFrom(this);
        } catch (JSONPointerException unused) {
            return null;
        }
    }

    public static String quote(String str) {
        if (str == null || str.isEmpty()) {
            return "\"\"";
        }
        try {
            return quote(str, new StringBuilderWriter(str.length() + 2)).toString();
        } catch (IOException unused) {
            return "";
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    public static Writer quote(String str, Writer writer) throws IOException {
        if (str == null || str.isEmpty()) {
            writer.write("\"\"");
            return writer;
        }
        int length = str.length();
        writer.write(34);
        int i = 0;
        char c = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\f') {
                writer.write("\\f");
            } else if (cCharAt == '\r') {
                writer.write("\\r");
            } else if (cCharAt == '\"') {
                writer.write(92);
                writer.write(cCharAt);
            } else if (cCharAt == '/') {
                if (c == '<') {
                    writer.write(92);
                }
                writer.write(cCharAt);
            } else if (cCharAt == '\\') {
                writer.write(92);
                writer.write(cCharAt);
            } else {
                switch (cCharAt) {
                    case '\b':
                        writer.write("\\b");
                        break;
                    case '\t':
                        writer.write("\\t");
                        break;
                    case '\n':
                        writer.write("\\n");
                        break;
                    default:
                        writeAsHex(writer, cCharAt);
                        break;
                }
            }
            i++;
            c = cCharAt;
        }
        writer.write(34);
        return writer;
    }

    private static void writeAsHex(Writer writer, char c) throws IOException {
        if (c < ' ' || ((c >= 128 && c < 160) || (c >= 8192 && c < 8448))) {
            writer.write("\\u");
            String hexString = Integer.toHexString(c);
            writer.write("0000", 0, 4 - hexString.length());
            writer.write(hexString);
            return;
        }
        writer.write(c);
    }

    public Object remove(String str) {
        return this.map.remove(str);
    }

    public boolean similar(Object obj) {
        try {
            if ((obj instanceof JSONObject) && keySet().equals(((JSONObject) obj).keySet())) {
                return checkSimilarEntries(obj);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean checkSimilarEntries(Object obj) {
        for (Map.Entry<String, Object> entry : entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object obj2 = ((JSONObject) obj).get(key);
            if (value != obj2 && (value == null || !checkObjectType(value, obj2))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkObjectType(Object obj, Object obj2) {
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).similar(obj2);
        }
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).similar(obj2);
        }
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            return isNumberSimilar((Number) obj, (Number) obj2);
        }
        if ((obj instanceof JSONString) && (obj2 instanceof JSONString)) {
            return ((JSONString) obj).toJSONString().equals(((JSONString) obj2).toJSONString());
        }
        return obj.equals(obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static boolean isNumberSimilar(Number number, Number number2) {
        if (numberIsFinite(number) && numberIsFinite(number2)) {
            if (number.getClass().equals(number2.getClass()) && (number instanceof Comparable)) {
                return ((Comparable) number).compareTo(number2) == 0;
            }
            BigDecimal bigDecimalObjectToBigDecimal = objectToBigDecimal(number, null, false);
            BigDecimal bigDecimalObjectToBigDecimal2 = objectToBigDecimal(number2, null, false);
            if (bigDecimalObjectToBigDecimal != null && bigDecimalObjectToBigDecimal2 != null && bigDecimalObjectToBigDecimal.compareTo(bigDecimalObjectToBigDecimal2) == 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean numberIsFinite(Number number) {
        if (number instanceof Double) {
            Double d = (Double) number;
            if (d.isInfinite() || d.isNaN()) {
                return false;
            }
        }
        if (!(number instanceof Float)) {
            return true;
        }
        Float f = (Float) number;
        return (f.isInfinite() || f.isNaN()) ? false : true;
    }

    protected static boolean isDecimalNotation(String str) {
        return str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1 || "-0".equals(str);
    }

    public static Object stringToValue(String str) {
        if ("".equals(str)) {
            return str;
        }
        if ("true".equalsIgnoreCase(str)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(str)) {
            return Boolean.FALSE;
        }
        if (AbstractJsonLexerKt.NULL.equalsIgnoreCase(str)) {
            return NULL;
        }
        char cCharAt = str.charAt(0);
        if ((cCharAt < '0' || cCharAt > '9') && cCharAt != '-') {
            return str;
        }
        try {
            return stringToNumber(str);
        } catch (Exception unused) {
            return str;
        }
    }

    protected static Number stringToNumber(String str) throws NumberFormatException {
        char cCharAt = str.charAt(0);
        if ((cCharAt >= '0' && cCharAt <= '9') || cCharAt == '-') {
            if (isDecimalNotation(str)) {
                return getNumber(str, cCharAt);
            }
            checkForInvalidNumberFormat(str, cCharAt);
            BigInteger bigInteger = new BigInteger(str);
            if (bigInteger.bitLength() <= 31) {
                return Integer.valueOf(bigInteger.intValue());
            }
            return bigInteger.bitLength() <= 63 ? Long.valueOf(bigInteger.longValue()) : bigInteger;
        }
        throw new NumberFormatException("val [" + str + "] is not a valid number.");
    }

    private static void checkForInvalidNumberFormat(String str, char c) {
        if (c == '0' && str.length() > 1) {
            char cCharAt = str.charAt(1);
            if (cCharAt < '0' || cCharAt > '9') {
                return;
            }
            throw new NumberFormatException("val [" + str + "] is not a valid number.");
        }
        if (c != '-' || str.length() <= 2) {
            return;
        }
        char cCharAt2 = str.charAt(1);
        char cCharAt3 = str.charAt(2);
        if (cCharAt2 != '0' || cCharAt3 < '0' || cCharAt3 > '9') {
            return;
        }
        throw new NumberFormatException("val [" + str + "] is not a valid number.");
    }

    private static Number getNumber(String str, char c) {
        try {
            try {
                BigDecimal bigDecimal = new BigDecimal(str);
                return (c == '-' && BigDecimal.ZERO.compareTo(bigDecimal) == 0) ? Double.valueOf(-0.0d) : bigDecimal;
            } catch (NumberFormatException unused) {
                throw new NumberFormatException("val [" + str + "] is not a valid number.");
            }
        } catch (NumberFormatException unused2) {
            Double dValueOf = Double.valueOf(str);
            if (!dValueOf.isNaN() && !dValueOf.isInfinite()) {
                return dValueOf;
            }
            throw new NumberFormatException("val [" + str + "] is not a valid number.");
        }
        throw new NumberFormatException("val [" + str + "] is not a valid number.");
    }

    public static void testValidity(Object obj) throws JSONException {
        if ((obj instanceof Number) && !numberIsFinite((Number) obj)) {
            throw new JSONException("JSON does not allow non-finite numbers.");
        }
    }

    public JSONArray toJSONArray(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.isEmpty()) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            jSONArray2.put(opt(jSONArray.getString(i)));
        }
        return jSONArray2;
    }

    public String toString() {
        try {
            return toString(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString(int i) throws JSONException {
        return write(new StringBuilderWriter(Math.max(this.map.size() * 6, 16)), i, 0).toString();
    }

    public static String valueToString(Object obj) throws JSONException {
        return JSONWriter.valueToString(obj);
    }

    public static Object wrap(Object obj) {
        return wrap(obj, null);
    }

    static Object wrap(Object obj, int i, JSONParserConfiguration jSONParserConfiguration) {
        return wrap(obj, null, i, jSONParserConfiguration);
    }

    private static Object wrap(Object obj, Set<Object> set) {
        return wrap(obj, set, 0, new JSONParserConfiguration());
    }

    private static Object wrap(Object obj, Set<Object> set, int i, JSONParserConfiguration jSONParserConfiguration) {
        try {
            Object obj2 = NULL;
            if (obj2.equals(obj)) {
                return obj2;
            }
            if (!(obj instanceof JSONObject) && !(obj instanceof JSONArray) && !(obj instanceof JSONString) && !(obj instanceof String) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Short) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Boolean) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof BigInteger) && !(obj instanceof BigDecimal) && !(obj instanceof Enum)) {
                if (obj instanceof Collection) {
                    return new JSONArray((Collection) obj, i, jSONParserConfiguration);
                }
                if (obj.getClass().isArray()) {
                    return new JSONArray(obj);
                }
                if (obj instanceof Map) {
                    return new JSONObject((Map) obj, i, jSONParserConfiguration);
                }
                Package r4 = obj.getClass().getPackage();
                String name = r4 != null ? r4.getName() : "";
                if (!name.startsWith("java.") && !name.startsWith("javax.") && obj.getClass().getClassLoader() != null) {
                    if (set != null) {
                        return new JSONObject(obj, set);
                    }
                    return new JSONObject(obj);
                }
                return obj.toString();
            }
            return obj;
        } catch (JSONException e) {
            throw e;
        } catch (Exception unused) {
            return null;
        }
    }

    public Writer write(Writer writer) throws JSONException {
        return write(writer, 0, 0);
    }

    static final Writer writeValue(Writer writer, Object obj, int i, int i2) throws JSONException, IOException {
        if (obj == null || obj.equals(null)) {
            writer.write(AbstractJsonLexerKt.NULL);
            return writer;
        }
        if (obj instanceof JSONString) {
            processJsonStringToWriteValue(writer, obj);
            return writer;
        }
        if (obj instanceof String) {
            quote(obj.toString(), writer);
            return writer;
        }
        if (obj instanceof Number) {
            processNumberToWriteValue(writer, (Number) obj);
            return writer;
        }
        if (obj instanceof Boolean) {
            writer.write(obj.toString());
            return writer;
        }
        if (obj instanceof Enum) {
            writer.write(quote(((Enum) obj).name()));
            return writer;
        }
        if (obj instanceof JSONObject) {
            ((JSONObject) obj).write(writer, i, i2);
            return writer;
        }
        if (obj instanceof JSONArray) {
            ((JSONArray) obj).write(writer, i, i2);
            return writer;
        }
        if (obj instanceof Map) {
            new JSONObject((Map<?, ?>) obj).write(writer, i, i2);
            return writer;
        }
        if (obj instanceof Collection) {
            new JSONArray((Collection<?>) obj).write(writer, i, i2);
            return writer;
        }
        if (obj.getClass().isArray()) {
            new JSONArray(obj).write(writer, i, i2);
            return writer;
        }
        quote(obj.toString(), writer);
        return writer;
    }

    private static void processJsonStringToWriteValue(Writer writer, Object obj) throws IOException {
        try {
            String jSONString = ((JSONString) obj).toJSONString();
            writer.write(jSONString != null ? jSONString.toString() : quote(obj.toString()));
        } catch (Exception e) {
            throw new JSONException(e);
        }
    }

    private static void processNumberToWriteValue(Writer writer, Number number) throws IOException {
        String strNumberToString = numberToString(number);
        if (NUMBER_PATTERN.matcher(strNumberToString).matches()) {
            writer.write(strNumberToString);
        } else {
            quote(strNumberToString, writer);
        }
    }

    static final void indent(Writer writer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            writer.write(32);
        }
    }

    public Writer write(Writer writer, int i, int i2) throws JSONException {
        try {
            int length = length();
            writer.write(123);
            if (length == 1) {
                Map.Entry<String, Object> next = entrySet().iterator().next();
                String key = next.getKey();
                writer.write(quote(key));
                writer.write(58);
                if (i > 0) {
                    writer.write(32);
                }
                attemptWriteValue(writer, i, i2, next, key);
            } else if (length != 0) {
                writeContent(writer, i, i2, false);
            }
            writer.write(125);
            return writer;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    private void writeContent(Writer writer, int i, int i2, boolean z) throws IOException {
        int i3 = i2 + i;
        for (Map.Entry<String, Object> entry : entrySet()) {
            if (z) {
                writer.write(44);
            }
            if (i > 0) {
                writer.write(10);
            }
            indent(writer, i3);
            String key = entry.getKey();
            writer.write(quote(key));
            writer.write(58);
            if (i > 0) {
                writer.write(32);
            }
            attemptWriteValue(writer, i, i3, entry, key);
            z = true;
        }
        if (i > 0) {
            writer.write(10);
        }
        indent(writer, i2);
    }

    private static void attemptWriteValue(Writer writer, int i, int i2, Map.Entry<String, ?> entry, String str) {
        try {
            writeValue(writer, entry.getValue(), i, i2);
        } catch (Exception e) {
            throw new JSONException("Unable to write JSONObject value for key: " + str, e);
        }
    }

    public Map<String, Object> toMap() {
        Object value;
        HashMap map = new HashMap();
        for (Map.Entry<String, Object> entry : entrySet()) {
            if (entry.getValue() == null || NULL.equals(entry.getValue())) {
                value = null;
            } else if (entry.getValue() instanceof JSONObject) {
                value = ((JSONObject) entry.getValue()).toMap();
            } else if (entry.getValue() instanceof JSONArray) {
                value = ((JSONArray) entry.getValue()).toList();
            } else {
                value = entry.getValue();
            }
            map.put(entry.getKey(), value);
        }
        return map;
    }

    private static JSONException wrongValueFormatException(String str, String str2, Object obj, Throwable th) {
        if (obj == null) {
            return new JSONException("JSONObject[" + quote(str) + "] is not a " + str2 + " (null).", th);
        }
        if ((obj instanceof Map) || (obj instanceof Iterable) || (obj instanceof JSONObject)) {
            return new JSONException("JSONObject[" + quote(str) + "] is not a " + str2 + " (" + obj.getClass() + ").", th);
        }
        return new JSONException("JSONObject[" + quote(str) + "] is not a " + str2 + " (" + obj.getClass() + RepoAdapter.SHAREABLE_REPO_SEPARATOR + obj + ").", th);
    }

    private static JSONException recursivelyDefinedObjectException(String str) {
        return new JSONException("JavaBean object contains recursively defined member variable of key " + quote(str));
    }

    private Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Object[].class;
        }
        return Object.class;
    }

    private Type getElementType(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            return actualTypeArguments.length > 0 ? actualTypeArguments[0] : Object.class;
        }
        return Object.class;
    }

    private Type[] getMapTypes(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                return actualTypeArguments;
            }
        }
        return new Type[]{Object.class, Object.class};
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) new JSONObject(str).fromJson(cls);
    }

    public <T> T fromJson(Class<T> cls) {
        try {
            T tNewInstance = cls.getDeclaredConstructor(null).newInstance(null);
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                if (has(name)) {
                    field.set(tNewInstance, convertValue(get(name), field.getGenericType()));
                }
            }
            return tNewInstance;
        } catch (NoSuchMethodException e) {
            throw new JSONException("No no-arg constructor for class: " + cls.getName(), e);
        } catch (Exception e2) {
            throw new JSONException("Failed to instantiate or set field for class: " + cls.getName(), e2);
        }
    }

    private Object convertValue(Object obj, Type type) throws JSONException {
        if (obj == null) {
            return null;
        }
        Class<?> rawType = getRawType(type);
        if (!rawType.isAssignableFrom(obj.getClass())) {
            if (rawType == Integer.TYPE || rawType == Integer.class) {
                return Integer.valueOf(((Number) obj).intValue());
            }
            if (rawType == Double.TYPE || rawType == Double.class) {
                return Double.valueOf(((Number) obj).doubleValue());
            }
            if (rawType == Float.TYPE || rawType == Float.class) {
                return Float.valueOf(((Number) obj).floatValue());
            }
            if (rawType == Long.TYPE || rawType == Long.class) {
                return Long.valueOf(((Number) obj).longValue());
            }
            if (rawType != Boolean.TYPE && rawType != Boolean.class && rawType != String.class) {
                if (rawType == BigDecimal.class) {
                    return new BigDecimal((String) obj);
                }
                if (rawType == BigInteger.class) {
                    return new BigInteger((String) obj);
                }
                if (rawType.isEnum() && (obj instanceof String)) {
                    return stringToEnum(rawType, (String) obj);
                }
                if (Collection.class.isAssignableFrom(rawType)) {
                    if (obj instanceof JSONArray) {
                        return fromJsonArray((JSONArray) obj, rawType, getElementType(type));
                    }
                } else {
                    if (Map.class.isAssignableFrom(rawType) && (obj instanceof JSONObject)) {
                        Type[] mapTypes = getMapTypes(type);
                        return convertToMap((JSONObject) obj, mapTypes[0], mapTypes[1], rawType);
                    }
                    if (!rawType.isPrimitive() && !rawType.isEnum() && (obj instanceof JSONObject)) {
                        return ((JSONObject) obj).fromJson(rawType);
                    }
                }
                return obj.toString();
            }
        }
        return obj;
    }

    private Map<?, ?> convertToMap(JSONObject jSONObject, Type type, Type type2, Class<?> cls) throws JSONException {
        try {
            HashMap map = new HashMap();
            for (String str : jSONObject.keySet()) {
                map.put(convertValue(str, type), convertValue(jSONObject.get(str), type2));
            }
            return map;
        } catch (Exception e) {
            throw new JSONException("Failed to convert JSONObject to Map: " + cls.getName(), e);
        }
    }

    private <E> E stringToEnum(Class<?> cls, String str) throws JSONException {
        try {
            return (E) cls.getMethod("valueOf", String.class).invoke(null, str);
        } catch (Exception e) {
            throw new JSONException("Failed to convert string to enum: " + str + " for " + cls.getName(), e);
        }
    }

    private <T> Collection<T> fromJsonArray(JSONArray jSONArray, Class<?> cls, Type type) throws JSONException {
        try {
            Collection collection = getCollection(cls);
            for (int i = 0; i < jSONArray.length(); i++) {
                collection.add(convertValue(jSONArray.get(i), type));
            }
            return collection;
        } catch (Exception e) {
            throw new JSONException("Failed to convert JSONArray to Collection: " + cls.getName(), e);
        }
    }

    private Collection getCollection(Class<?> cls) throws JSONException {
        if (cls == List.class || cls == ArrayList.class) {
            return new ArrayList();
        }
        if (cls == Set.class || cls == HashSet.class) {
            return new HashSet();
        }
        throw new JSONException("Unsupported Collection type: " + cls.getName());
    }
}
