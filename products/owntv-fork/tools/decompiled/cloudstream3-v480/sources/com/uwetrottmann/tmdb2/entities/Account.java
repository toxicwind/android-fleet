package com.uwetrottmann.tmdb2.entities;

/* JADX INFO: loaded from: classes7.dex */
public class Account {
    public Avatar avatar;
    public Integer id;
    public Boolean include_adult;
    public String iso_3166_1;
    public String iso_639_1;
    public String name;
    public String username;

    public static class Avatar {
        public GRAvatar gravatar;

        public static class GRAvatar {
            public String hash;
        }
    }
}
