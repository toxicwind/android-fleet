package io.ktor.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: Text.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000b\u001a\u00020\nH\u0096\u0080\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\r\u001a\u00020\u0002H\u0096\u0080\u0004¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/util/CaseInsensitiveString;", "", "", "content", "<init>", "(Ljava/lang/String;)V", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getContent", "hash", "I", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CaseInsensitiveString {
    private final String content;
    private final int hash;

    public CaseInsensitiveString(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.content = content;
        int length = content.length();
        int lowerCase = 0;
        for (int i = 0; i < length; i++) {
            lowerCase = (lowerCase * 31) + Character.toLowerCase(content.charAt(i));
        }
        this.hash = lowerCase;
    }

    public final String getContent() {
        return this.content;
    }

    public boolean equals(Object other) {
        String str;
        CaseInsensitiveString caseInsensitiveString = other instanceof CaseInsensitiveString ? (CaseInsensitiveString) other : null;
        return (caseInsensitiveString == null || (str = caseInsensitiveString.content) == null || !StringsKt.equals(str, this.content, true)) ? false : true;
    }

    /* JADX INFO: renamed from: hashCode, reason: from getter */
    public int getHash() {
        return this.hash;
    }

    public String toString() {
        return this.content;
    }
}
