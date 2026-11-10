package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapterFactory DOUBLE_FACTORY = new AnonymousClass1(ToNumberPolicy.DOUBLE);
    public final Gson gson;
    public final ToNumberStrategy toNumberStrategy;

    /* JADX INFO: renamed from: com.google.gson.internal.bind.ObjectTypeAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements TypeAdapterFactory {
        public final /* synthetic */ ToNumberStrategy val$toNumberStrategy;

        public AnonymousClass1(ToNumberStrategy toNumberStrategy) {
            this.val$toNumberStrategy = toNumberStrategy;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            AnonymousClass1 anonymousClass1 = null;
            if (typeToken.getRawType() == Object.class) {
                return new ObjectTypeAdapter(gson, this.val$toNumberStrategy, anonymousClass1);
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: com.google.gson.internal.bind.ObjectTypeAdapter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public ObjectTypeAdapter(Gson gson, ToNumberStrategy toNumberStrategy) {
        this.gson = gson;
        this.toNumberStrategy = toNumberStrategy;
    }

    public static TypeAdapterFactory getFactory(ToNumberStrategy toNumberStrategy) {
        return toNumberStrategy == ToNumberPolicy.DOUBLE ? DOUBLE_FACTORY : new AnonymousClass1(toNumberStrategy);
    }

    public static TypeAdapterFactory newFactory(ToNumberStrategy toNumberStrategy) {
        return new AnonymousClass1(toNumberStrategy);
    }

    @Override // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader) throws IOException {
        int iOrdinal = jsonReader.peek().ordinal();
        if (iOrdinal == 0) {
            ArrayList arrayList = new ArrayList();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                arrayList.add(read(jsonReader));
            }
            jsonReader.endArray();
            return arrayList;
        }
        if (iOrdinal == 2) {
            LinkedTreeMap linkedTreeMap = new LinkedTreeMap(LinkedTreeMap.NATURAL_ORDER);
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                linkedTreeMap.put(jsonReader.nextName(), read(jsonReader));
            }
            jsonReader.endObject();
            return linkedTreeMap;
        }
        if (iOrdinal == 5) {
            return jsonReader.nextString();
        }
        if (iOrdinal == 6) {
            return this.toNumberStrategy.readNumber(jsonReader);
        }
        if (iOrdinal == 7) {
            return Boolean.valueOf(jsonReader.nextBoolean());
        }
        if (iOrdinal != 8) {
            throw new IllegalStateException();
        }
        jsonReader.nextNull();
        return null;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Object obj) {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter adapter = this.gson.getAdapter(obj.getClass());
        if (!(adapter instanceof ObjectTypeAdapter)) {
            adapter.write(jsonWriter, obj);
        } else {
            jsonWriter.beginObject();
            jsonWriter.endObject();
        }
    }

    public /* synthetic */ ObjectTypeAdapter(Gson gson, ToNumberStrategy toNumberStrategy, AnonymousClass1 anonymousClass1) {
        this.gson = gson;
        this.toNumberStrategy = toNumberStrategy;
    }
}
