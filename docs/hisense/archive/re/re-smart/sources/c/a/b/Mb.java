package c.a.b;

import com.google.common.base.Preconditions;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class Mb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1075a = Logger.getLogger(Mb.class.getName());

    public static Object a(String str) {
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            return a(jsonReader);
        } finally {
            try {
                jsonReader.close();
            } catch (IOException e2) {
                f1075a.log(Level.WARNING, "Failed to close", (Throwable) e2);
            }
        }
    }

    public static Object a(JsonReader jsonReader) throws IOException {
        boolean z;
        Preconditions.checkState(jsonReader.hasNext(), "unexpected end of JSON");
        int iOrdinal = jsonReader.peek().ordinal();
        if (iOrdinal == 0) {
            jsonReader.beginArray();
            ArrayList arrayList = new ArrayList();
            while (jsonReader.hasNext()) {
                arrayList.add(a(jsonReader));
            }
            z = jsonReader.peek() == JsonToken.END_ARRAY;
            StringBuilder sbA = b.a.a.a.a.a("Bad token: ");
            sbA.append(jsonReader.getPath());
            Preconditions.checkState(z, sbA.toString());
            jsonReader.endArray();
            return Collections.unmodifiableList(arrayList);
        }
        if (iOrdinal == 2) {
            jsonReader.beginObject();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            while (jsonReader.hasNext()) {
                linkedHashMap.put(jsonReader.nextName(), a(jsonReader));
            }
            z = jsonReader.peek() == JsonToken.END_OBJECT;
            StringBuilder sbA2 = b.a.a.a.a.a("Bad token: ");
            sbA2.append(jsonReader.getPath());
            Preconditions.checkState(z, sbA2.toString());
            jsonReader.endObject();
            return Collections.unmodifiableMap(linkedHashMap);
        }
        if (iOrdinal == 5) {
            return jsonReader.nextString();
        }
        if (iOrdinal == 6) {
            return Double.valueOf(jsonReader.nextDouble());
        }
        if (iOrdinal == 7) {
            return Boolean.valueOf(jsonReader.nextBoolean());
        }
        if (iOrdinal == 8) {
            jsonReader.nextNull();
            return null;
        }
        StringBuilder sbA3 = b.a.a.a.a.a("Bad token: ");
        sbA3.append(jsonReader.getPath());
        throw new IllegalStateException(sbA3.toString());
    }
}
