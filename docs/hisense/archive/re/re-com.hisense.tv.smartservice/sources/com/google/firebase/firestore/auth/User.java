package com.google.firebase.firestore.auth;

import androidx.annotation.Nullable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class User {
    public static final User UNAUTHENTICATED = new User(null);

    @Nullable
    public final String uid;

    public User(@Nullable String str) {
        this.uid = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || User.class != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        String str = this.uid;
        if (str != null) {
            return str.equals(user.uid);
        }
        return user.uid == null;
    }

    @Nullable
    public String getUid() {
        return this.uid;
    }

    public int hashCode() {
        String str = this.uid;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public boolean isAuthenticated() {
        return this.uid != null;
    }

    public String toString() {
        return a.a(a.a("User(uid:"), this.uid, ")");
    }
}
