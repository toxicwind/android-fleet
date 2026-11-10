package com.google.firebase.firestore.auth;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class Token {
    public final User user;

    @Nullable
    public final String value;

    public Token(@Nullable String str, User user) {
        this.value = str;
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    @Nullable
    public String getValue() {
        return this.value;
    }
}
