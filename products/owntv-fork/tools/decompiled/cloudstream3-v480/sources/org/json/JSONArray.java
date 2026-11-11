package org.json;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.ui.settings.extensions.RepoAdapter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes8.dex */
public class JSONArray implements Iterable<Object> {
    private final ArrayList<Object> myArrayList;

    public JSONArray() {
        this.myArrayList = new ArrayList<>();
    }

    public JSONArray(JSONTokener jSONTokener) throws JSONException {
        this(jSONTokener, jSONTokener.getJsonParserConfiguration());
    }

    public JSONArray(JSONTokener jSONTokener, JSONParserConfiguration jSONParserConfiguration) throws JSONException {
        this();
        boolean z = jSONTokener.getPrevious() == 0;
        if (jSONTokener.nextClean() != '[') {
            throw jSONTokener.syntaxError("A JSONArray text must start with '['");
        }
        char cNextClean = jSONTokener.nextClean();
        if (cNextClean == 0) {
            throw jSONTokener.syntaxError("Expected a ',' or ']'");
        }
        if (cNextClean == ',' && jSONParserConfiguration.isStrictMode()) {
            throw jSONTokener.syntaxError("Array content starts with a ','");
        }
        if (cNextClean != ']') {
            jSONTokener.back();
            do {
                if (jSONTokener.nextClean() == ',') {
                    jSONTokener.back();
                    this.myArrayList.add(JSONObject.NULL);
                } else {
                    jSONTokener.back();
                    this.myArrayList.add(jSONTokener.nextValue());
                }
            } while (!checkForSyntaxError(jSONTokener, jSONParserConfiguration, z));
            return;
        }
        if (z && jSONParserConfiguration.isStrictMode() && jSONTokener.nextClean() != 0) {
            throw jSONTokener.syntaxError("Strict mode error: Unparsed characters found at end of input text");
        }
    }

    private static boolean checkForSyntaxError(JSONTokener jSONTokener, JSONParserConfiguration jSONParserConfiguration, boolean z) {
        char cNextClean = jSONTokener.nextClean();
        if (cNextClean == 0) {
            throw jSONTokener.syntaxError("Expected a ',' or ']'");
        }
        if (cNextClean != ',') {
            if (cNextClean == ']') {
                if (z && jSONParserConfiguration.isStrictMode() && jSONTokener.nextClean() != 0) {
                    throw jSONTokener.syntaxError("Strict mode error: Unparsed characters found at end of input text");
                }
                return true;
            }
            throw jSONTokener.syntaxError("Expected a ',' or ']'");
        }
        char cNextClean2 = jSONTokener.nextClean();
        if (cNextClean2 == 0) {
            throw jSONTokener.syntaxError("Expected a ',' or ']'");
        }
        if (cNextClean2 == ']') {
            if (jSONParserConfiguration.isStrictMode()) {
                throw jSONTokener.syntaxError("Strict mode error: Expected another array element");
            }
            return true;
        }
        if (cNextClean2 == ',') {
            if (jSONParserConfiguration.isStrictMode()) {
                throw jSONTokener.syntaxError("Strict mode error: Expected a valid array element");
            }
            return true;
        }
        jSONTokener.back();
        return false;
    }

    public JSONArray(String str) throws JSONException {
        this(str, new JSONParserConfiguration());
    }

    public JSONArray(String str, JSONParserConfiguration jSONParserConfiguration) throws JSONException {
        this(new JSONTokener(str, jSONParserConfiguration), jSONParserConfiguration);
    }

    public JSONArray(Collection<?> collection) {
        this(collection, 0, new JSONParserConfiguration());
    }

    public JSONArray(Collection<?> collection, JSONParserConfiguration jSONParserConfiguration) {
        this(collection, 0, jSONParserConfiguration);
    }

    JSONArray(Collection<?> collection, int i, JSONParserConfiguration jSONParserConfiguration) {
        if (i > jSONParserConfiguration.getMaxNestingDepth()) {
            throw new JSONException("JSONArray has reached recursion depth limit of " + jSONParserConfiguration.getMaxNestingDepth());
        }
        if (collection == null) {
            this.myArrayList = new ArrayList<>();
        } else {
            this.myArrayList = new ArrayList<>(collection.size());
            addAll(collection, true, i, jSONParserConfiguration);
        }
    }

    public JSONArray(Iterable<?> iterable) {
        this();
        if (iterable == null) {
            return;
        }
        addAll(iterable, true);
    }

    public JSONArray(JSONArray jSONArray) {
        if (jSONArray == null) {
            this.myArrayList = new ArrayList<>();
        } else {
            this.myArrayList = new ArrayList<>(jSONArray.myArrayList);
        }
    }

    public JSONArray(Object obj) throws JSONException {
        this();
        if (!obj.getClass().isArray()) {
            throw new JSONException("JSONArray initial value should be a string or collection or array.");
        }
        addAll(obj, true, 0);
    }

    public JSONArray(int i) throws JSONException {
        if (i < 0) {
            throw new JSONException("JSONArray initial capacity cannot be negative.");
        }
        this.myArrayList = new ArrayList<>(i);
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.myArrayList.iterator();
    }

    public Object get(int i) throws JSONException {
        Object objOpt = opt(i);
        if (objOpt != null) {
            return objOpt;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public boolean getBoolean(int i) throws JSONException {
        Object obj = get(i);
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
        throw wrongValueFormatException(i, TypedValues.Custom.S_BOOLEAN, obj, null);
    }

    public double getDouble(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(i, "double", obj, e);
        }
    }

    public float getFloat(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(i, TypedValues.Custom.S_FLOAT, obj, e);
        }
    }

    public Number getNumber(int i) throws JSONException {
        Object obj = get(i);
        try {
            if (obj instanceof Number) {
                return (Number) obj;
            }
            return JSONObject.stringToNumber(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(i, "number", obj, e);
        }
    }

    public <E extends Enum<E>> E getEnum(Class<E> cls, int i) throws JSONException {
        E e = (E) optEnum(cls, i);
        if (e != null) {
            return e;
        }
        throw wrongValueFormatException(i, "enum of type " + JSONObject.quote(cls.getSimpleName()), opt(i), null);
    }

    public BigDecimal getBigDecimal(int i) throws JSONException {
        Object obj = get(i);
        BigDecimal bigDecimalObjectToBigDecimal = JSONObject.objectToBigDecimal(obj, null);
        if (bigDecimalObjectToBigDecimal != null) {
            return bigDecimalObjectToBigDecimal;
        }
        throw wrongValueFormatException(i, "BigDecimal", obj, null);
    }

    public BigInteger getBigInteger(int i) throws JSONException {
        Object obj = get(i);
        BigInteger bigIntegerObjectToBigInteger = JSONObject.objectToBigInteger(obj, null);
        if (bigIntegerObjectToBigInteger != null) {
            return bigIntegerObjectToBigInteger;
        }
        throw wrongValueFormatException(i, "BigInteger", obj, null);
    }

    public int getInt(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(i, "int", obj, e);
        }
    }

    public JSONArray getJSONArray(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        throw wrongValueFormatException(i, "JSONArray", obj, null);
    }

    public JSONObject getJSONObject(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw wrongValueFormatException(i, "JSONObject", obj, null);
    }

    public long getLong(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(i, "long", obj, e);
        }
    }

    public String getString(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw wrongValueFormatException(i, "String", obj, null);
    }

    public boolean isNull(int i) {
        return JSONObject.NULL.equals(opt(i));
    }

    public String join(String str) throws JSONException {
        int length = length();
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(JSONObject.valueToString(this.myArrayList.get(0)));
        for (int i = 1; i < length; i++) {
            sb.append(str);
            sb.append(JSONObject.valueToString(this.myArrayList.get(i)));
        }
        return sb.toString();
    }

    public int length() {
        return this.myArrayList.size();
    }

    public void clear() {
        this.myArrayList.clear();
    }

    public Object opt(int i) {
        if (i < 0 || i >= length()) {
            return null;
        }
        return this.myArrayList.get(i);
    }

    public boolean optBoolean(int i) {
        return optBoolean(i, false);
    }

    public boolean optBoolean(int i, boolean z) {
        try {
            return getBoolean(i);
        } catch (Exception unused) {
            return z;
        }
    }

    public Boolean optBooleanObject(int i) {
        return optBooleanObject(i, false);
    }

    public Boolean optBooleanObject(int i, Boolean bool) {
        try {
            return Boolean.valueOf(getBoolean(i));
        } catch (Exception unused) {
            return bool;
        }
    }

    public double optDouble(int i) {
        return optDouble(i, Double.NaN);
    }

    public double optDouble(int i, double d) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? d : numberOptNumber.doubleValue();
    }

    public Double optDoubleObject(int i) {
        return optDoubleObject(i, Double.valueOf(Double.NaN));
    }

    public Double optDoubleObject(int i, Double d) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? d : Double.valueOf(numberOptNumber.doubleValue());
    }

    public float optFloat(int i) {
        return optFloat(i, Float.NaN);
    }

    public float optFloat(int i, float f) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? f : numberOptNumber.floatValue();
    }

    public Float optFloatObject(int i) {
        return optFloatObject(i, Float.valueOf(Float.NaN));
    }

    public Float optFloatObject(int i, Float f) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? f : Float.valueOf(numberOptNumber.floatValue());
    }

    public int optInt(int i) {
        return optInt(i, 0);
    }

    public int optInt(int i, int i2) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? i2 : numberOptNumber.intValue();
    }

    public Integer optIntegerObject(int i) {
        return optIntegerObject(i, 0);
    }

    public Integer optIntegerObject(int i, Integer num) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? num : Integer.valueOf(numberOptNumber.intValue());
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, int i) {
        return (E) optEnum(cls, i, null);
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, int i, E e) {
        try {
            Object objOpt = opt(i);
            if (JSONObject.NULL.equals(objOpt)) {
                return e;
            }
            if (cls.isAssignableFrom(objOpt.getClass())) {
                return (E) objOpt;
            }
            return (E) Enum.valueOf(cls, objOpt.toString());
        } catch (IllegalArgumentException | NullPointerException unused) {
        }
    }

    public BigInteger optBigInteger(int i, BigInteger bigInteger) {
        return JSONObject.objectToBigInteger(opt(i), bigInteger);
    }

    public BigDecimal optBigDecimal(int i, BigDecimal bigDecimal) {
        return JSONObject.objectToBigDecimal(opt(i), bigDecimal);
    }

    public JSONArray optJSONArray(int i) {
        return optJSONArray(i, null);
    }

    public JSONArray optJSONArray(int i, JSONArray jSONArray) {
        Object objOpt = opt(i);
        return objOpt instanceof JSONArray ? (JSONArray) objOpt : jSONArray;
    }

    public JSONObject optJSONObject(int i) {
        return optJSONObject(i, null);
    }

    public JSONObject optJSONObject(int i, JSONObject jSONObject) {
        Object objOpt = opt(i);
        return objOpt instanceof JSONObject ? (JSONObject) objOpt : jSONObject;
    }

    public long optLong(int i) {
        return optLong(i, 0L);
    }

    public long optLong(int i, long j) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? j : numberOptNumber.longValue();
    }

    public Long optLongObject(int i) {
        return optLongObject(i, 0L);
    }

    public Long optLongObject(int i, Long l) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? l : Long.valueOf(numberOptNumber.longValue());
    }

    public Number optNumber(int i) {
        return optNumber(i, null);
    }

    public Number optNumber(int i, Number number) {
        Object objOpt = opt(i);
        if (!JSONObject.NULL.equals(objOpt)) {
            if (objOpt instanceof Number) {
                return (Number) objOpt;
            }
            if (objOpt instanceof String) {
                try {
                    return JSONObject.stringToNumber((String) objOpt);
                } catch (Exception unused) {
                }
            }
        }
        return number;
    }

    public String optString(int i) {
        return optString(i, "");
    }

    public String optString(int i, String str) {
        Object objOpt = opt(i);
        return JSONObject.NULL.equals(objOpt) ? str : objOpt.toString();
    }

    public JSONArray put(boolean z) {
        return put(z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONArray put(Collection<?> collection) {
        return put(new JSONArray(collection));
    }

    public JSONArray put(double d) throws JSONException {
        return put(Double.valueOf(d));
    }

    public JSONArray put(float f) throws JSONException {
        return put(Float.valueOf(f));
    }

    public JSONArray put(int i) {
        return put(Integer.valueOf(i));
    }

    public JSONArray put(long j) {
        return put(Long.valueOf(j));
    }

    public JSONArray put(Map<?, ?> map) {
        return put(new JSONObject(map));
    }

    public JSONArray put(Object obj) {
        JSONObject.testValidity(obj);
        this.myArrayList.add(obj);
        return this;
    }

    public JSONArray put(int i, boolean z) throws JSONException {
        return put(i, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONArray put(int i, Collection<?> collection) throws JSONException {
        return put(i, new JSONArray(collection));
    }

    public JSONArray put(int i, double d) throws JSONException {
        return put(i, Double.valueOf(d));
    }

    public JSONArray put(int i, float f) throws JSONException {
        return put(i, Float.valueOf(f));
    }

    public JSONArray put(int i, int i2) throws JSONException {
        return put(i, Integer.valueOf(i2));
    }

    public JSONArray put(int i, long j) throws JSONException {
        return put(i, Long.valueOf(j));
    }

    public JSONArray put(int i, Map<?, ?> map) throws JSONException {
        put(i, new JSONObject(map, new JSONParserConfiguration()));
        return this;
    }

    public JSONArray put(int i, Map<?, ?> map, JSONParserConfiguration jSONParserConfiguration) throws JSONException {
        put(i, new JSONObject(map, jSONParserConfiguration));
        return this;
    }

    public JSONArray put(int i, Object obj) throws JSONException {
        if (i < 0) {
            throw new JSONException("JSONArray[" + i + "] not found.");
        }
        if (i < length()) {
            JSONObject.testValidity(obj);
            this.myArrayList.set(i, obj);
            return this;
        }
        if (i == length()) {
            return put(obj);
        }
        this.myArrayList.ensureCapacity(i + 1);
        while (i != length()) {
            this.myArrayList.add(JSONObject.NULL);
        }
        return put(obj);
    }

    public JSONArray putAll(Collection<?> collection) {
        addAll((Iterable<?>) collection, false);
        return this;
    }

    public JSONArray putAll(Iterable<?> iterable) {
        addAll(iterable, false);
        return this;
    }

    public JSONArray putAll(JSONArray jSONArray) {
        this.myArrayList.addAll(jSONArray.myArrayList);
        return this;
    }

    public JSONArray putAll(Object obj) throws JSONException {
        addAll(obj, false);
        return this;
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

    public Object remove(int i) {
        if (i < 0 || i >= length()) {
            return null;
        }
        return this.myArrayList.remove(i);
    }

    public boolean similar(Object obj) {
        if (!(obj instanceof JSONArray)) {
            return false;
        }
        int length = length();
        JSONArray jSONArray = (JSONArray) obj;
        if (length != jSONArray.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            Object obj2 = this.myArrayList.get(i);
            Object obj3 = jSONArray.myArrayList.get(i);
            if (obj2 != obj3 && (obj2 == null || !isSimilar(obj2, obj3))) {
                return false;
            }
        }
        return true;
    }

    private boolean isSimilar(Object obj, Object obj2) {
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).similar(obj2);
        }
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).similar(obj2);
        }
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            return JSONObject.isNumberSimilar((Number) obj, (Number) obj2);
        }
        if ((obj instanceof JSONString) && (obj2 instanceof JSONString)) {
            return ((JSONString) obj).toJSONString().equals(((JSONString) obj2).toJSONString());
        }
        return obj.equals(obj2);
    }

    public JSONObject toJSONObject(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.isEmpty() || isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            jSONObject.put(jSONArray.getString(i), opt(i));
        }
        return jSONObject;
    }

    public String toString() {
        try {
            return toString(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString(int i) throws JSONException {
        return write(new StringBuilderWriter(Math.max(this.myArrayList.size() * 2, 16)), i, 0).toString();
    }

    public Writer write(Writer writer) throws JSONException {
        return write(writer, 0, 0);
    }

    public Writer write(Writer writer, int i, int i2) throws JSONException {
        try {
            int length = length();
            writer.write(91);
            int i3 = 0;
            if (length == 1) {
                writeArrayAttempt(writer, i, i2, 0);
            } else if (length != 0) {
                int i4 = i2 + i;
                boolean z = false;
                while (i3 < length) {
                    if (z) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    JSONObject.indent(writer, i4);
                    writeArrayAttempt(writer, i, i4, i3);
                    i3++;
                    z = true;
                }
                if (i > 0) {
                    writer.write(10);
                }
                JSONObject.indent(writer, i2);
            }
            writer.write(93);
            return writer;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    private void writeArrayAttempt(Writer writer, int i, int i2, int i3) {
        try {
            JSONObject.writeValue(writer, this.myArrayList.get(i3), i, i2);
        } catch (Exception e) {
            throw new JSONException("Unable to write JSONArray value at index: " + i3, e);
        }
    }

    public List<Object> toList() {
        ArrayList arrayList = new ArrayList(this.myArrayList.size());
        for (Object obj : this.myArrayList) {
            if (obj == null || JSONObject.NULL.equals(obj)) {
                arrayList.add(null);
            } else if (obj instanceof JSONArray) {
                arrayList.add(((JSONArray) obj).toList());
            } else if (obj instanceof JSONObject) {
                arrayList.add(((JSONObject) obj).toMap());
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public boolean isEmpty() {
        return this.myArrayList.isEmpty();
    }

    private void addAll(Collection<?> collection, boolean z, int i, JSONParserConfiguration jSONParserConfiguration) {
        ArrayList<Object> arrayList = this.myArrayList;
        arrayList.ensureCapacity(arrayList.size() + collection.size());
        if (z) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                put(JSONObject.wrap(it.next(), i + 1, jSONParserConfiguration));
            }
        } else {
            Iterator<?> it2 = collection.iterator();
            while (it2.hasNext()) {
                put(it2.next());
            }
        }
    }

    private void addAll(Iterable<?> iterable, boolean z) {
        if (z) {
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                put(JSONObject.wrap(it.next()));
            }
        } else {
            Iterator<?> it2 = iterable.iterator();
            while (it2.hasNext()) {
                put(it2.next());
            }
        }
    }

    private void addAll(Object obj, boolean z) throws JSONException {
        addAll(obj, z, 0);
    }

    private void addAll(Object obj, boolean z, int i) {
        addAll(obj, z, i, new JSONParserConfiguration());
    }

    private void addAll(Object obj, boolean z, int i, JSONParserConfiguration jSONParserConfiguration) throws JSONException {
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            ArrayList<Object> arrayList = this.myArrayList;
            arrayList.ensureCapacity(arrayList.size() + length);
            int i2 = 0;
            if (z) {
                while (i2 < length) {
                    put(JSONObject.wrap(Array.get(obj, i2), i + 1, jSONParserConfiguration));
                    i2++;
                }
                return;
            } else {
                while (i2 < length) {
                    put(Array.get(obj, i2));
                    i2++;
                }
                return;
            }
        }
        if (obj instanceof JSONArray) {
            this.myArrayList.addAll(((JSONArray) obj).myArrayList);
        } else if (obj instanceof Collection) {
            addAll((Collection<?>) obj, z, i, jSONParserConfiguration);
        } else {
            if (obj instanceof Iterable) {
                addAll((Iterable<?>) obj, z);
                return;
            }
            throw new JSONException("JSONArray initial value should be a string or collection or array.");
        }
    }

    private static JSONException wrongValueFormatException(int i, String str, Object obj, Throwable th) {
        if (obj == null) {
            return new JSONException("JSONArray[" + i + "] is not a " + str + " (null).", th);
        }
        if ((obj instanceof Map) || (obj instanceof Iterable) || (obj instanceof JSONObject)) {
            return new JSONException("JSONArray[" + i + "] is not a " + str + " (" + obj.getClass() + ").", th);
        }
        return new JSONException("JSONArray[" + i + "] is not a " + str + " (" + obj.getClass() + RepoAdapter.SHAREABLE_REPO_SEPARATOR + obj + ").", th);
    }
}
