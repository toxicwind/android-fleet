package com.uwetrottmann.tmdb2.entities;

/* JADX INFO: loaded from: classes7.dex */
public class TvCastCredit extends BaseTvCredit {
    public Integer order;
    public java.util.List<Role> roles;

    public static class Role {
        public String character;
        public String credit_id;
        public Integer episode_count;
    }
}
