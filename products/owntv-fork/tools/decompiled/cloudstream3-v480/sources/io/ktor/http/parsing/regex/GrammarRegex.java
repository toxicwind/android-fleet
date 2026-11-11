package io.ktor.http.parsing.regex;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RegexParserGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lio/ktor/http/parsing/regex/GrammarRegex;", "", "", "regexRaw", "", "groupsCountRaw", "", "group", "<init>", "(Ljava/lang/String;IZ)V", "regex", "Ljava/lang/String;", "getRegex", "()Ljava/lang/String;", "groupsCount", "I", "getGroupsCount", "()I", "ktor-http"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class GrammarRegex {
    private final int groupsCount;
    private final String regex;

    public GrammarRegex(String regexRaw, int i, boolean z) {
        Intrinsics.checkNotNullParameter(regexRaw, "regexRaw");
        if (z) {
            regexRaw = "(" + regexRaw + ')';
        }
        this.regex = regexRaw;
        this.groupsCount = z ? i + 1 : i;
    }

    public /* synthetic */ GrammarRegex(String str, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? false : z);
    }

    public final String getRegex() {
        return this.regex;
    }

    public final int getGroupsCount() {
        return this.groupsCount;
    }
}
