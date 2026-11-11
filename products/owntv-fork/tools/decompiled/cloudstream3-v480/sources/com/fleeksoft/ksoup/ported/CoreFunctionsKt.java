package com.fleeksoft.ksoup.ported;

import com.fleeksoft.ksoup.Platform;
import com.fleeksoft.ksoup.PlatformExtKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CoreFunctions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"jsSupportedRegex", "Lkotlin/text/Regex;", "regex", "", "ksoup_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CoreFunctionsKt {
    public static final Regex jsSupportedRegex(String regex) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        if (PlatformExtKt.isJsOrWasm(Platform.INSTANCE) && StringsKt.contains$default((CharSequence) regex, (CharSequence) "(?i)", false, 2, (Object) null)) {
            return new Regex(StringsKt.replace$default(regex, "(?i)", "", false, 4, (Object) null), RegexOption.IGNORE_CASE);
        }
        return new Regex(regex);
    }
}
