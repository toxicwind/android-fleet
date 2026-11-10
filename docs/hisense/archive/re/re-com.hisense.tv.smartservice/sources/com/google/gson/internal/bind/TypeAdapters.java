package com.google.gson.internal.bind;

import b.a.a.a.a;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: loaded from: classes.dex */
public final class TypeAdapters {
    public static final TypeAdapter<Class> CLASS = new TypeAdapter<Class>() { // from class: com.google.gson.internal.bind.TypeAdapters.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Class read(JsonReader jsonReader) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Class cls) {
            StringBuilder sbA = a.a("Attempted to serialize java.lang.Class: ");
            sbA.append(cls.getName());
            sbA.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(sbA.toString());
        }
    }.nullSafe();
    public static final TypeAdapterFactory CLASS_FACTORY = new AnonymousClass31(Class.class, CLASS);
    public static final TypeAdapter<BitSet> BIT_SET = new TypeAdapter<BitSet>() { // from class: com.google.gson.internal.bind.TypeAdapters.2
        @Override // com.google.gson.TypeAdapter
        public BitSet read(JsonReader jsonReader) throws IOException {
            boolean zNextBoolean;
            BitSet bitSet = new BitSet();
            jsonReader.beginArray();
            JsonToken jsonTokenPeek = jsonReader.peek();
            int i = 0;
            while (jsonTokenPeek != JsonToken.END_ARRAY) {
                int iOrdinal = jsonTokenPeek.ordinal();
                if (iOrdinal == 5 || iOrdinal == 6) {
                    int iNextInt = jsonReader.nextInt();
                    if (iNextInt == 0) {
                        zNextBoolean = false;
                    } else {
                        if (iNextInt != 1) {
                            StringBuilder sbA = a.a("Invalid bitset value ", iNextInt, ", expected 0 or 1; at path ");
                            sbA.append(jsonReader.getPreviousPath());
                            throw new JsonSyntaxException(sbA.toString());
                        }
                        zNextBoolean = true;
                    }
                } else {
                    if (iOrdinal != 7) {
                        throw new JsonSyntaxException("Invalid bitset value type: " + jsonTokenPeek + "; at path " + jsonReader.getPath());
                    }
                    zNextBoolean = jsonReader.nextBoolean();
                }
                if (zNextBoolean) {
                    bitSet.set(i);
                }
                i++;
                jsonTokenPeek = jsonReader.peek();
            }
            jsonReader.endArray();
            return bitSet;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, BitSet bitSet) {
            jsonWriter.beginArray();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                jsonWriter.value(bitSet.get(i) ? 1L : 0L);
            }
            jsonWriter.endArray();
        }
    }.nullSafe();
    public static final TypeAdapterFactory BIT_SET_FACTORY = new AnonymousClass31(BitSet.class, BIT_SET);
    public static final TypeAdapter<Boolean> BOOLEAN = new TypeAdapter<Boolean>() { // from class: com.google.gson.internal.bind.TypeAdapters.3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Boolean read(JsonReader jsonReader) throws IOException {
            JsonToken jsonTokenPeek = jsonReader.peek();
            if (jsonTokenPeek != JsonToken.NULL) {
                return jsonTokenPeek == JsonToken.STRING ? Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString())) : Boolean.valueOf(jsonReader.nextBoolean());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Boolean bool) {
            jsonWriter.value(bool);
        }
    };
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING = new TypeAdapter<Boolean>() { // from class: com.google.gson.internal.bind.TypeAdapters.4
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Boolean read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Boolean.valueOf(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Boolean bool) {
            jsonWriter.value(bool == null ? "null" : bool.toString());
        }
    };
    public static final TypeAdapterFactory BOOLEAN_FACTORY = new AnonymousClass32(Boolean.TYPE, Boolean.class, BOOLEAN);
    public static final TypeAdapter<Number> BYTE = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.5
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt <= 255 && iNextInt >= -128) {
                    return Byte.valueOf((byte) iNextInt);
                }
                StringBuilder sbA = a.a("Lossy conversion from ", iNextInt, " to byte; at path ");
                sbA.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(sbA.toString());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapterFactory BYTE_FACTORY = new AnonymousClass32(Byte.TYPE, Byte.class, BYTE);
    public static final TypeAdapter<Number> SHORT = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.6
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                int iNextInt = jsonReader.nextInt();
                if (iNextInt <= 65535 && iNextInt >= -32768) {
                    return Short.valueOf((short) iNextInt);
                }
                StringBuilder sbA = a.a("Lossy conversion from ", iNextInt, " to short; at path ");
                sbA.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(sbA.toString());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapterFactory SHORT_FACTORY = new AnonymousClass32(Short.TYPE, Short.class, SHORT);
    public static final TypeAdapter<Number> INTEGER = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.7
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(jsonReader.nextInt());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapterFactory INTEGER_FACTORY = new AnonymousClass32(Integer.TYPE, Integer.class, INTEGER);
    public static final TypeAdapter<AtomicInteger> ATOMIC_INTEGER = new TypeAdapter<AtomicInteger>() { // from class: com.google.gson.internal.bind.TypeAdapters.8
        @Override // com.google.gson.TypeAdapter
        public AtomicInteger read(JsonReader jsonReader) {
            try {
                return new AtomicInteger(jsonReader.nextInt());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, AtomicInteger atomicInteger) {
            jsonWriter.value(atomicInteger.get());
        }
    }.nullSafe();
    public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY = new AnonymousClass31(AtomicInteger.class, ATOMIC_INTEGER);
    public static final TypeAdapter<AtomicBoolean> ATOMIC_BOOLEAN = new TypeAdapter<AtomicBoolean>() { // from class: com.google.gson.internal.bind.TypeAdapters.9
        @Override // com.google.gson.TypeAdapter
        public AtomicBoolean read(JsonReader jsonReader) {
            return new AtomicBoolean(jsonReader.nextBoolean());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, AtomicBoolean atomicBoolean) {
            jsonWriter.value(atomicBoolean.get());
        }
    }.nullSafe();
    public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY = new AnonymousClass31(AtomicBoolean.class, ATOMIC_BOOLEAN);
    public static final TypeAdapter<AtomicIntegerArray> ATOMIC_INTEGER_ARRAY = new TypeAdapter<AtomicIntegerArray>() { // from class: com.google.gson.internal.bind.TypeAdapters.10
        @Override // com.google.gson.TypeAdapter
        public AtomicIntegerArray read(JsonReader jsonReader) throws IOException {
            ArrayList arrayList = new ArrayList();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                try {
                    arrayList.add(Integer.valueOf(jsonReader.nextInt()));
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            jsonReader.endArray();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, AtomicIntegerArray atomicIntegerArray) {
            jsonWriter.beginArray();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                jsonWriter.value(atomicIntegerArray.get(i));
            }
            jsonWriter.endArray();
        }
    }.nullSafe();
    public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY = new AnonymousClass31(AtomicIntegerArray.class, ATOMIC_INTEGER_ARRAY);
    public static final TypeAdapter<Number> LONG = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.11
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Long.valueOf(jsonReader.nextLong());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapter<Number> FLOAT = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.12
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Float.valueOf((float) jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapter<Number> DOUBLE = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.13
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Double.valueOf(jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapter<Character> CHARACTER = new TypeAdapter<Character>() { // from class: com.google.gson.internal.bind.TypeAdapters.14
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Character read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String strNextString = jsonReader.nextString();
            if (strNextString.length() == 1) {
                return Character.valueOf(strNextString.charAt(0));
            }
            StringBuilder sbB = a.b("Expecting character, got: ", strNextString, "; at ");
            sbB.append(jsonReader.getPreviousPath());
            throw new JsonSyntaxException(sbB.toString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Character ch) {
            jsonWriter.value(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final TypeAdapterFactory CHARACTER_FACTORY = new AnonymousClass32(Character.TYPE, Character.class, CHARACTER);
    public static final TypeAdapter<String> STRING = new TypeAdapter<String>() { // from class: com.google.gson.internal.bind.TypeAdapters.15
        @Override // com.google.gson.TypeAdapter
        public String read(JsonReader jsonReader) throws IOException {
            JsonToken jsonTokenPeek = jsonReader.peek();
            if (jsonTokenPeek != JsonToken.NULL) {
                return jsonTokenPeek == JsonToken.BOOLEAN ? Boolean.toString(jsonReader.nextBoolean()) : jsonReader.nextString();
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, String str) {
            jsonWriter.value(str);
        }
    };
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL = new TypeAdapter<BigDecimal>() { // from class: com.google.gson.internal.bind.TypeAdapters.16
        @Override // com.google.gson.TypeAdapter
        public BigDecimal read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String strNextString = jsonReader.nextString();
            try {
                return new BigDecimal(strNextString);
            } catch (NumberFormatException e2) {
                StringBuilder sbB = a.b("Failed parsing '", strNextString, "' as BigDecimal; at path ");
                sbB.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(sbB.toString(), e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) {
            jsonWriter.value(bigDecimal);
        }
    };
    public static final TypeAdapter<BigInteger> BIG_INTEGER = new TypeAdapter<BigInteger>() { // from class: com.google.gson.internal.bind.TypeAdapters.17
        @Override // com.google.gson.TypeAdapter
        public BigInteger read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String strNextString = jsonReader.nextString();
            try {
                return new BigInteger(strNextString);
            } catch (NumberFormatException e2) {
                StringBuilder sbB = a.b("Failed parsing '", strNextString, "' as BigInteger; at path ");
                sbB.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(sbB.toString(), e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, BigInteger bigInteger) {
            jsonWriter.value(bigInteger);
        }
    };
    public static final TypeAdapter<LazilyParsedNumber> LAZILY_PARSED_NUMBER = new TypeAdapter<LazilyParsedNumber>() { // from class: com.google.gson.internal.bind.TypeAdapters.18
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public LazilyParsedNumber read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return new LazilyParsedNumber(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, LazilyParsedNumber lazilyParsedNumber) {
            jsonWriter.value(lazilyParsedNumber);
        }
    };
    public static final TypeAdapterFactory STRING_FACTORY = new AnonymousClass31(String.class, STRING);
    public static final TypeAdapter<StringBuilder> STRING_BUILDER = new TypeAdapter<StringBuilder>() { // from class: com.google.gson.internal.bind.TypeAdapters.19
        @Override // com.google.gson.TypeAdapter
        public StringBuilder read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return new StringBuilder(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, StringBuilder sb) {
            jsonWriter.value(sb == null ? null : sb.toString());
        }
    };
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY = new AnonymousClass31(StringBuilder.class, STRING_BUILDER);
    public static final TypeAdapter<StringBuffer> STRING_BUFFER = new TypeAdapter<StringBuffer>() { // from class: com.google.gson.internal.bind.TypeAdapters.20
        @Override // com.google.gson.TypeAdapter
        public StringBuffer read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return new StringBuffer(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, StringBuffer stringBuffer) {
            jsonWriter.value(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY = new AnonymousClass31(StringBuffer.class, STRING_BUFFER);
    public static final TypeAdapter<URL> URL = new TypeAdapter<URL>() { // from class: com.google.gson.internal.bind.TypeAdapters.21
        @Override // com.google.gson.TypeAdapter
        public URL read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String strNextString = jsonReader.nextString();
            if ("null".equals(strNextString)) {
                return null;
            }
            return new URL(strNextString);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, URL url) {
            jsonWriter.value(url == null ? null : url.toExternalForm());
        }
    };
    public static final TypeAdapterFactory URL_FACTORY = new AnonymousClass31(URL.class, URL);
    public static final TypeAdapter<URI> URI = new TypeAdapter<URI>() { // from class: com.google.gson.internal.bind.TypeAdapters.22
        @Override // com.google.gson.TypeAdapter
        public URI read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                String strNextString = jsonReader.nextString();
                if ("null".equals(strNextString)) {
                    return null;
                }
                return new URI(strNextString);
            } catch (URISyntaxException e2) {
                throw new JsonIOException(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, URI uri) {
            jsonWriter.value(uri == null ? null : uri.toASCIIString());
        }
    };
    public static final TypeAdapterFactory URI_FACTORY = new AnonymousClass31(URI.class, URI);
    public static final TypeAdapter<InetAddress> INET_ADDRESS = new TypeAdapter<InetAddress>() { // from class: com.google.gson.internal.bind.TypeAdapters.23
        @Override // com.google.gson.TypeAdapter
        public InetAddress read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return InetAddress.getByName(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, InetAddress inetAddress) {
            jsonWriter.value(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY = new AnonymousClass34(InetAddress.class, INET_ADDRESS);
    public static final TypeAdapter<UUID> UUID = new TypeAdapter<UUID>() { // from class: com.google.gson.internal.bind.TypeAdapters.24
        @Override // com.google.gson.TypeAdapter
        public UUID read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String strNextString = jsonReader.nextString();
            try {
                return UUID.fromString(strNextString);
            } catch (IllegalArgumentException e2) {
                StringBuilder sbB = a.b("Failed parsing '", strNextString, "' as UUID; at path ");
                sbB.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(sbB.toString(), e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, UUID uuid) {
            jsonWriter.value(uuid == null ? null : uuid.toString());
        }
    };
    public static final TypeAdapterFactory UUID_FACTORY = new AnonymousClass31(UUID.class, UUID);
    public static final TypeAdapter<Currency> CURRENCY = new TypeAdapter<Currency>() { // from class: com.google.gson.internal.bind.TypeAdapters.25
        @Override // com.google.gson.TypeAdapter
        public Currency read(JsonReader jsonReader) throws IOException {
            String strNextString = jsonReader.nextString();
            try {
                return Currency.getInstance(strNextString);
            } catch (IllegalArgumentException e2) {
                StringBuilder sbB = a.b("Failed parsing '", strNextString, "' as Currency; at path ");
                sbB.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(sbB.toString(), e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Currency currency) {
            jsonWriter.value(currency.getCurrencyCode());
        }
    }.nullSafe();
    public static final TypeAdapterFactory CURRENCY_FACTORY = new AnonymousClass31(Currency.class, CURRENCY);
    public static final TypeAdapter<Calendar> CALENDAR = new TypeAdapter<Calendar>() { // from class: com.google.gson.internal.bind.TypeAdapters.26
        public static final String DAY_OF_MONTH = "dayOfMonth";
        public static final String HOUR_OF_DAY = "hourOfDay";
        public static final String MINUTE = "minute";
        public static final String MONTH = "month";
        public static final String SECOND = "second";
        public static final String YEAR = "year";

        @Override // com.google.gson.TypeAdapter
        public Calendar read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (jsonReader.peek() != JsonToken.END_OBJECT) {
                String strNextName = jsonReader.nextName();
                int iNextInt = jsonReader.nextInt();
                if (YEAR.equals(strNextName)) {
                    i = iNextInt;
                } else if (MONTH.equals(strNextName)) {
                    i2 = iNextInt;
                } else if (DAY_OF_MONTH.equals(strNextName)) {
                    i3 = iNextInt;
                } else if (HOUR_OF_DAY.equals(strNextName)) {
                    i4 = iNextInt;
                } else if (MINUTE.equals(strNextName)) {
                    i5 = iNextInt;
                } else if (SECOND.equals(strNextName)) {
                    i6 = iNextInt;
                }
            }
            jsonReader.endObject();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Calendar calendar) {
            if (calendar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(YEAR);
            jsonWriter.value(calendar.get(1));
            jsonWriter.name(MONTH);
            jsonWriter.value(calendar.get(2));
            jsonWriter.name(DAY_OF_MONTH);
            jsonWriter.value(calendar.get(5));
            jsonWriter.name(HOUR_OF_DAY);
            jsonWriter.value(calendar.get(11));
            jsonWriter.name(MINUTE);
            jsonWriter.value(calendar.get(12));
            jsonWriter.name(SECOND);
            jsonWriter.value(calendar.get(13));
            jsonWriter.endObject();
        }
    };
    public static final TypeAdapterFactory CALENDAR_FACTORY = new AnonymousClass33(Calendar.class, GregorianCalendar.class, CALENDAR);
    public static final TypeAdapter<Locale> LOCALE = new TypeAdapter<Locale>() { // from class: com.google.gson.internal.bind.TypeAdapters.27
        @Override // com.google.gson.TypeAdapter
        public Locale read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (strNextToken2 == null && strNextToken3 == null) {
                return new Locale(strNextToken);
            }
            return strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Locale locale) {
            jsonWriter.value(locale == null ? null : locale.toString());
        }
    };
    public static final TypeAdapterFactory LOCALE_FACTORY = new AnonymousClass31(Locale.class, LOCALE);
    public static final TypeAdapter<JsonElement> JSON_ELEMENT = new TypeAdapter<JsonElement>() { // from class: com.google.gson.internal.bind.TypeAdapters.28
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public JsonElement read(JsonReader jsonReader) throws IOException {
            if (jsonReader instanceof JsonTreeReader) {
                return ((JsonTreeReader) jsonReader).nextJsonElement();
            }
            int iOrdinal = jsonReader.peek().ordinal();
            if (iOrdinal == 0) {
                JsonArray jsonArray = new JsonArray();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonArray.add(read(jsonReader));
                }
                jsonReader.endArray();
                return jsonArray;
            }
            if (iOrdinal == 2) {
                JsonObject jsonObject = new JsonObject();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    jsonObject.add(jsonReader.nextName(), read(jsonReader));
                }
                jsonReader.endObject();
                return jsonObject;
            }
            if (iOrdinal == 5) {
                return new JsonPrimitive(jsonReader.nextString());
            }
            if (iOrdinal == 6) {
                return new JsonPrimitive(new LazilyParsedNumber(jsonReader.nextString()));
            }
            if (iOrdinal == 7) {
                return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
            }
            if (iOrdinal != 8) {
                throw new IllegalArgumentException();
            }
            jsonReader.nextNull();
            return JsonNull.INSTANCE;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, JsonElement jsonElement) {
            if (jsonElement == null || jsonElement.isJsonNull()) {
                jsonWriter.nullValue();
                return;
            }
            if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (asJsonPrimitive.isNumber()) {
                    jsonWriter.value(asJsonPrimitive.getAsNumber());
                    return;
                } else if (asJsonPrimitive.isBoolean()) {
                    jsonWriter.value(asJsonPrimitive.getAsBoolean());
                    return;
                } else {
                    jsonWriter.value(asJsonPrimitive.getAsString());
                    return;
                }
            }
            if (jsonElement.isJsonArray()) {
                jsonWriter.beginArray();
                Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
                while (it.hasNext()) {
                    write(jsonWriter, it.next());
                }
                jsonWriter.endArray();
                return;
            }
            if (!jsonElement.isJsonObject()) {
                StringBuilder sbA = a.a("Couldn't write ");
                sbA.append(jsonElement.getClass());
                throw new IllegalArgumentException(sbA.toString());
            }
            jsonWriter.beginObject();
            for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                jsonWriter.name(entry.getKey());
                write(jsonWriter, entry.getValue());
            }
            jsonWriter.endObject();
        }
    };
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY = new AnonymousClass34(JsonElement.class, JSON_ELEMENT);
    public static final TypeAdapterFactory ENUM_FACTORY = new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.TypeAdapters.29
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                return null;
            }
            if (!rawType.isEnum()) {
                rawType = rawType.getSuperclass();
            }
            return new EnumTypeAdapter(rawType);
        }
    };

    /* JADX INFO: renamed from: com.google.gson.internal.bind.TypeAdapters$31, reason: invalid class name */
    public class AnonymousClass31 implements TypeAdapterFactory {
        public final /* synthetic */ Class val$type;
        public final /* synthetic */ TypeAdapter val$typeAdapter;

        public AnonymousClass31(Class cls, TypeAdapter typeAdapter) {
            this.val$type = cls;
            this.val$typeAdapter = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == this.val$type) {
                return this.val$typeAdapter;
            }
            return null;
        }

        public String toString() {
            StringBuilder sbA = a.a("Factory[type=");
            sbA.append(this.val$type.getName());
            sbA.append(",adapter=");
            return a.a(sbA, this.val$typeAdapter, "]");
        }
    }

    /* JADX INFO: renamed from: com.google.gson.internal.bind.TypeAdapters$32, reason: invalid class name */
    public class AnonymousClass32 implements TypeAdapterFactory {
        public final /* synthetic */ Class val$boxed;
        public final /* synthetic */ TypeAdapter val$typeAdapter;
        public final /* synthetic */ Class val$unboxed;

        public AnonymousClass32(Class cls, Class cls2, TypeAdapter typeAdapter) {
            this.val$unboxed = cls;
            this.val$boxed = cls2;
            this.val$typeAdapter = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (rawType == this.val$unboxed || rawType == this.val$boxed) {
                return this.val$typeAdapter;
            }
            return null;
        }

        public String toString() {
            StringBuilder sbA = a.a("Factory[type=");
            sbA.append(this.val$boxed.getName());
            sbA.append("+");
            sbA.append(this.val$unboxed.getName());
            sbA.append(",adapter=");
            return a.a(sbA, this.val$typeAdapter, "]");
        }
    }

    /* JADX INFO: renamed from: com.google.gson.internal.bind.TypeAdapters$33, reason: invalid class name */
    public class AnonymousClass33 implements TypeAdapterFactory {
        public final /* synthetic */ Class val$base;
        public final /* synthetic */ Class val$sub;
        public final /* synthetic */ TypeAdapter val$typeAdapter;

        public AnonymousClass33(Class cls, Class cls2, TypeAdapter typeAdapter) {
            this.val$base = cls;
            this.val$sub = cls2;
            this.val$typeAdapter = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (rawType == this.val$base || rawType == this.val$sub) {
                return this.val$typeAdapter;
            }
            return null;
        }

        public String toString() {
            StringBuilder sbA = a.a("Factory[type=");
            sbA.append(this.val$base.getName());
            sbA.append("+");
            sbA.append(this.val$sub.getName());
            sbA.append(",adapter=");
            return a.a(sbA, this.val$typeAdapter, "]");
        }
    }

    /* JADX INFO: renamed from: com.google.gson.internal.bind.TypeAdapters$34, reason: invalid class name */
    public class AnonymousClass34 implements TypeAdapterFactory {
        public final /* synthetic */ Class val$clazz;
        public final /* synthetic */ TypeAdapter val$typeAdapter;

        public AnonymousClass34(Class cls, TypeAdapter typeAdapter) {
            this.val$clazz = cls;
            this.val$typeAdapter = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T2> TypeAdapter<T2> create(Gson gson, TypeToken<T2> typeToken) {
            final Class<? super T2> rawType = typeToken.getRawType();
            if (this.val$clazz.isAssignableFrom(rawType)) {
                return (TypeAdapter<T2>) new TypeAdapter<T1>() { // from class: com.google.gson.internal.bind.TypeAdapters.34.1
                    @Override // com.google.gson.TypeAdapter
                    public T1 read(JsonReader jsonReader) {
                        T1 t1 = (T1) AnonymousClass34.this.val$typeAdapter.read(jsonReader);
                        if (t1 == null || rawType.isInstance(t1)) {
                            return t1;
                        }
                        StringBuilder sbA = a.a("Expected a ");
                        sbA.append(rawType.getName());
                        sbA.append(" but was ");
                        sbA.append(t1.getClass().getName());
                        sbA.append("; at path ");
                        sbA.append(jsonReader.getPreviousPath());
                        throw new JsonSyntaxException(sbA.toString());
                    }

                    @Override // com.google.gson.TypeAdapter
                    public void write(JsonWriter jsonWriter, T1 t1) {
                        AnonymousClass34.this.val$typeAdapter.write(jsonWriter, t1);
                    }
                };
            }
            return null;
        }

        public String toString() {
            StringBuilder sbA = a.a("Factory[typeHierarchy=");
            sbA.append(this.val$clazz.getName());
            sbA.append(",adapter=");
            return a.a(sbA, this.val$typeAdapter, "]");
        }
    }

    /* JADX INFO: renamed from: com.google.gson.internal.bind.TypeAdapters$35, reason: invalid class name */
    static /* synthetic */ class AnonymousClass35 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        public final Map<String, T> nameToConstant = new HashMap();
        public final Map<T, String> constantToName = new HashMap();

        public EnumTypeAdapter(final Class<T> cls) {
            try {
                for (Field field : (Field[]) AccessController.doPrivileged(new PrivilegedAction<Field[]>() { // from class: com.google.gson.internal.bind.TypeAdapters.EnumTypeAdapter.1
                    @Override // java.security.PrivilegedAction
                    public Field[] run() {
                        Field[] declaredFields = cls.getDeclaredFields();
                        ArrayList arrayList = new ArrayList(declaredFields.length);
                        for (Field field2 : declaredFields) {
                            if (field2.isEnumConstant()) {
                                arrayList.add(field2);
                            }
                        }
                        Field[] fieldArr = (Field[]) arrayList.toArray(new Field[0]);
                        AccessibleObject.setAccessible(fieldArr, true);
                        return fieldArr;
                    }
                })) {
                    Enum r4 = (Enum) field.get(null);
                    String strName = r4.name();
                    SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
                    if (serializedName != null) {
                        strName = serializedName.value();
                        for (String str : serializedName.alternate()) {
                            this.nameToConstant.put(str, (T) r4);
                        }
                    }
                    this.nameToConstant.put(strName, (T) r4);
                    this.constantToName.put((T) r4, strName);
                }
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return this.nameToConstant.get(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) {
            jsonWriter.value(t == null ? null : this.constantToName.get(t));
        }
    }

    public TypeAdapters() {
        throw new UnsupportedOperationException();
    }

    public static <TT> TypeAdapterFactory newFactory(final TypeToken<TT> typeToken, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.TypeAdapters.30
            @Override // com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken2) {
                if (typeToken2.equals(typeToken)) {
                    return typeAdapter;
                }
                return null;
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(Class<TT> cls, Class<? extends TT> cls2, TypeAdapter<? super TT> typeAdapter) {
        return new AnonymousClass33(cls, cls2, typeAdapter);
    }

    public static <T1> TypeAdapterFactory newTypeHierarchyFactory(Class<T1> cls, TypeAdapter<T1> typeAdapter) {
        return new AnonymousClass34(cls, typeAdapter);
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> cls, TypeAdapter<TT> typeAdapter) {
        return new AnonymousClass31(cls, typeAdapter);
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> cls, Class<TT> cls2, TypeAdapter<? super TT> typeAdapter) {
        return new AnonymousClass32(cls, cls2, typeAdapter);
    }
}
