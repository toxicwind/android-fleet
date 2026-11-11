package com.fleeksoft.charset.internal;

import com.fleeksoft.charset.Charsets;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CharsetLookup.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u000b"}, d2 = {"Lcom/fleeksoft/charset/internal/CharsetLookup;", "", "<init>", "()V", "forName", "Ljava/nio/charset/Charset;", "Lcom/fleeksoft/charset/Charset;", "charsetName", "", "isSupported", "", "charset_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CharsetLookup {
    public static final CharsetLookup INSTANCE = new CharsetLookup();

    private CharsetLookup() {
    }

    public final Charset forName(String charsetName) {
        Intrinsics.checkNotNullParameter(charsetName, "charsetName");
        String strNormalizeCharsetName = Charsets.INSTANCE.normalizeCharsetName(charsetName);
        String str = CharsetNameMapping.INSTANCE.getStandardCharsetMapKeys().get(strNormalizeCharsetName);
        if (str == null) {
            String str2 = CharsetNameMapping.INSTANCE.getExtendedCharsetMapKeys().get(strNormalizeCharsetName);
            if (str2 != null) {
                charsetName = str2;
            }
        } else {
            charsetName = str;
        }
        Charset charsetForName = Charset.forName(charsetName);
        Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(...)");
        return charsetForName;
    }

    public final boolean isSupported(String charsetName) {
        Object objM8936constructorimpl;
        Intrinsics.checkNotNullParameter(charsetName, "charsetName");
        String strNormalizeCharsetName = Charsets.INSTANCE.normalizeCharsetName(charsetName);
        String str = CharsetNameMapping.INSTANCE.getStandardCharsetMapKeys().get(strNormalizeCharsetName);
        if (str == null) {
            String str2 = CharsetNameMapping.INSTANCE.getExtendedCharsetMapKeys().get(strNormalizeCharsetName);
            if (str2 != null) {
                charsetName = str2;
            }
        } else {
            charsetName = str;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            CharsetLookup charsetLookup = this;
            objM8936constructorimpl = Result.m8936constructorimpl(Boolean.valueOf(Charset.isSupported(charsetName)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
            objM8936constructorimpl = false;
        }
        return ((Boolean) objM8936constructorimpl).booleanValue();
    }
}
