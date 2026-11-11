package com.lagradost.nicehttp;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Requests.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/lagradost/nicehttp/JsonAsString;", "", TypedValues.Custom.S_STRING, "", "<init>", "(Ljava/lang/String;)V", "getString", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class JsonAsString {
    private final String string;

    public static /* synthetic */ JsonAsString copy$default(JsonAsString jsonAsString, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jsonAsString.string;
        }
        return jsonAsString.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getString() {
        return this.string;
    }

    public final JsonAsString copy(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return new JsonAsString(string);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof JsonAsString) && Intrinsics.areEqual(this.string, ((JsonAsString) other).string);
    }

    public int hashCode() {
        return this.string.hashCode();
    }

    public String toString() {
        return "JsonAsString(string=" + this.string + ')';
    }

    public JsonAsString(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        this.string = string;
    }

    public final String getString() {
        return this.string;
    }
}
