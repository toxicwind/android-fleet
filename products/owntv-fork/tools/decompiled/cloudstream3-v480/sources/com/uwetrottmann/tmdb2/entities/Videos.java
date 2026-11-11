package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.VideoType;

/* JADX INFO: loaded from: classes7.dex */
public class Videos {
    public Integer id;
    public java.util.List<Video> results;

    public static class Video {
        public String id;
        public String iso_3166_1;
        public String iso_639_1;
        public String key;
        public String name;
        public String site;
        public Integer size;
        public VideoType type;
    }
}
