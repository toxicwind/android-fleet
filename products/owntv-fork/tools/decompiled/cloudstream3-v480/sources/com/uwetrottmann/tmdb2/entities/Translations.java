package com.uwetrottmann.tmdb2.entities;

/* JADX INFO: loaded from: classes7.dex */
public class Translations {
    public Integer id;
    public java.util.List<Translation> translations;

    public static class Translation {
        public Data data;
        public String english_name;
        public String iso_3166_1;
        public String iso_639_1;
        public String name;

        public static class Data {
            public String homepage;
            public String name;
            public String overview;
            public String title;
        }
    }
}
