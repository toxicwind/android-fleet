package com.uwetrottmann.tmdb2.entities;

import java.util.HashMap;

/* JADX INFO: loaded from: classes7.dex */
public class Certifications {
    public HashMap<String, java.util.List<Certification>> certifications;

    public static class Certification {
        public String certification;
        public String meaning;
        public Integer order;
    }
}
