package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: CookieUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
public final class CookieUtilsKt$tryParseTime$hour$1$1 implements Function1<Character, Boolean> {
    public static final CookieUtilsKt$tryParseTime$hour$1$1 INSTANCE = new CookieUtilsKt$tryParseTime$hour$1$1();

    public final Boolean invoke(char c) {
        return Boolean.valueOf(CookieUtilsKt.isDigit(c));
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
        return invoke(ch.charValue());
    }
}
