package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes.dex */
public final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    public final Gson context;
    public final TypeAdapter<T> delegate;
    public final Type type;

    public TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.context = gson;
        this.delegate = typeAdapter;
        this.type = type;
    }

    private Type getRuntimeTypeIfMoreSpecific(Type type, Object obj) {
        if (obj != null) {
            return (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type;
        }
        return type;
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader jsonReader) {
        return this.delegate.read(jsonReader);
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, T t) {
        TypeAdapter<T> adapter = this.delegate;
        Type runtimeTypeIfMoreSpecific = getRuntimeTypeIfMoreSpecific(this.type, t);
        if (runtimeTypeIfMoreSpecific != this.type) {
            adapter = this.context.getAdapter(new TypeToken<>(runtimeTypeIfMoreSpecific));
            if (adapter instanceof ReflectiveTypeAdapterFactory.Adapter) {
                TypeAdapter<T> typeAdapter = this.delegate;
                if (!(typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter)) {
                    adapter = typeAdapter;
                }
            }
        }
        adapter.write(jsonWriter, t);
    }
}
