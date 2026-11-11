package com.uwetrottmann.tmdb2.entities;

import com.google.gson.JsonElement;

/* JADX INFO: loaded from: classes7.dex */
public class Changes {
    public java.util.List<Entries> changes;

    public static class Change {
        public String action;
        public String id;
        public String iso_639_1;
        public JsonElement original_value;
        public String time;
        public JsonElement value;
    }

    public static class Entries {
        public java.util.List<Change> items;
        public String key;
    }
}
