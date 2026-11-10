package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class JsonObject extends JsonElement {
    private final Map<String, JsonElement> members = new LinkedHashMap();

    public void add(String str, JsonElement jsonElement) {
        if (jsonElement == null) {
            jsonElement = JsonNull.INSTANCE;
        }
        Map<String, JsonElement> map = this.members;
        C$Gson$Preconditions.checkNotNull(str);
        map.put(str, jsonElement);
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return this.members.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof JsonObject) && ((JsonObject) obj).members.equals(this.members));
    }

    public int hashCode() {
        return this.members.hashCode();
    }
}
