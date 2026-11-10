package com.fasterxml.jackson.annotation;

/* JADX INFO: loaded from: classes.dex */
public enum OptBoolean {
    TRUE,
    FALSE,
    DEFAULT;

    public Boolean asBoolean() {
        if (this == DEFAULT) {
            return null;
        }
        return this == TRUE ? Boolean.TRUE : Boolean.FALSE;
    }

    public static boolean equals(Boolean bool, Boolean bool2) {
        if (bool == null) {
            return bool2 == null;
        }
        return bool.equals(bool2);
    }
}
