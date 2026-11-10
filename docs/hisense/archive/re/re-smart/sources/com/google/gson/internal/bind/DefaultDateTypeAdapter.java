package com.google.gson.internal.bind;

import b.a.a.a.a;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultDateTypeAdapter<T extends Date> extends TypeAdapter<T> {
    public static final String SIMPLE_NAME = "DefaultDateTypeAdapter";
    public final List<DateFormat> dateFormats;
    public final DateType<T> dateType;

    private Date deserializeToDate(JsonReader jsonReader) throws IOException {
        String strNextString = jsonReader.nextString();
        synchronized (this.dateFormats) {
            Iterator<DateFormat> it = this.dateFormats.iterator();
            while (it.hasNext()) {
                try {
                    return it.next().parse(strNextString);
                } catch (ParseException unused) {
                }
            }
            try {
                return ISO8601Utils.parse(strNextString, new ParsePosition(0));
            } catch (ParseException e2) {
                StringBuilder sbB = a.b("Failed parsing '", strNextString, "' as Date; at path ");
                sbB.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(sbB.toString(), e2);
            }
        }
    }

    public String toString() {
        DateFormat dateFormat = this.dateFormats.get(0);
        if (dateFormat instanceof SimpleDateFormat) {
            StringBuilder sbA = a.a("DefaultDateTypeAdapter(");
            sbA.append(((SimpleDateFormat) dateFormat).toPattern());
            sbA.append(')');
            return sbA.toString();
        }
        StringBuilder sbA2 = a.a("DefaultDateTypeAdapter(");
        sbA2.append(dateFormat.getClass().getSimpleName());
        sbA2.append(')');
        return sbA2.toString();
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return (T) this.dateType.deserialize(deserializeToDate(jsonReader));
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Date date) {
        String str;
        if (date == null) {
            jsonWriter.nullValue();
            return;
        }
        DateFormat dateFormat = this.dateFormats.get(0);
        synchronized (this.dateFormats) {
            str = dateFormat.format(date);
        }
        jsonWriter.value(str);
    }

    public static abstract class DateType<T extends Date> {
        public static final DateType<Date> DATE = new DateType<Date>(Date.class) { // from class: com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType.1
            @Override // com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType
            public Date deserialize(Date date) {
                return date;
            }
        };
        public final Class<T> dateClass;

        public DateType(Class<T> cls) {
            this.dateClass = cls;
        }

        private final TypeAdapterFactory createFactory(DefaultDateTypeAdapter<T> defaultDateTypeAdapter) {
            return TypeAdapters.newFactory(this.dateClass, defaultDateTypeAdapter);
        }

        public final TypeAdapterFactory createAdapterFactory(String str) {
            return TypeAdapters.newFactory(this.dateClass, new DefaultDateTypeAdapter(this, str));
        }

        public final TypeAdapterFactory createDefaultsAdapterFactory() {
            return TypeAdapters.newFactory(this.dateClass, new DefaultDateTypeAdapter(this, 2, 2));
        }

        public abstract T deserialize(Date date);

        public final TypeAdapterFactory createAdapterFactory(int i) {
            return TypeAdapters.newFactory(this.dateClass, new DefaultDateTypeAdapter(this, i));
        }

        public final TypeAdapterFactory createAdapterFactory(int i, int i2) {
            return TypeAdapters.newFactory(this.dateClass, new DefaultDateTypeAdapter(this, i, i2));
        }
    }

    public DefaultDateTypeAdapter(DateType<T> dateType, String str) {
        this.dateFormats = new ArrayList();
        if (dateType != null) {
            this.dateType = dateType;
            this.dateFormats.add(new SimpleDateFormat(str, Locale.US));
            if (Locale.getDefault().equals(Locale.US)) {
                return;
            }
            this.dateFormats.add(new SimpleDateFormat(str));
            return;
        }
        throw new NullPointerException();
    }

    public DefaultDateTypeAdapter(DateType<T> dateType, int i) {
        this.dateFormats = new ArrayList();
        if (dateType != null) {
            this.dateType = dateType;
            this.dateFormats.add(DateFormat.getDateInstance(i, Locale.US));
            if (!Locale.getDefault().equals(Locale.US)) {
                this.dateFormats.add(DateFormat.getDateInstance(i));
            }
            if (JavaVersion.isJava9OrLater()) {
                this.dateFormats.add(PreJava9DateFormatProvider.getUSDateFormat(i));
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    public DefaultDateTypeAdapter(DateType<T> dateType, int i, int i2) {
        this.dateFormats = new ArrayList();
        if (dateType != null) {
            this.dateType = dateType;
            this.dateFormats.add(DateFormat.getDateTimeInstance(i, i2, Locale.US));
            if (!Locale.getDefault().equals(Locale.US)) {
                this.dateFormats.add(DateFormat.getDateTimeInstance(i, i2));
            }
            if (JavaVersion.isJava9OrLater()) {
                this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(i, i2));
                return;
            }
            return;
        }
        throw new NullPointerException();
    }
}
