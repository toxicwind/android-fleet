package com.fleeksoft.charset;

import com.fleeksoft.charset.internal.CharsetLookup;
import java.nio.charset.Charset;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Charsets.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\u0017\u0010\u001a\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0018R\u001f\u0010\u0004\u001a\u00060\u0005j\u0002`\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\u000b\u001a\u00060\u0005j\u0002`\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\bR\u001f\u0010\u000e\u001a\u00060\u0005j\u0002`\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\n\u001a\u0004\b\u000f\u0010\bR\u001f\u0010\u0011\u001a\u00060\u0005j\u0002`\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0012\u0010\bR\u001f\u0010\u0014\u001a\u00060\u0005j\u0002`\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\n\u001a\u0004\b\u0015\u0010\b¨\u0006\u001f"}, d2 = {"Lcom/fleeksoft/charset/Charsets;", "", "<init>", "()V", "UTF8", "Ljava/nio/charset/Charset;", "Lcom/fleeksoft/charset/Charset;", "getUTF8", "()Ljava/nio/charset/Charset;", "UTF8$delegate", "Lkotlin/Lazy;", "US_ASCII", "getUS_ASCII", "US_ASCII$delegate", "ISO_8859_1", "getISO_8859_1", "ISO_8859_1$delegate", "UTF_16", "getUTF_16", "UTF_16$delegate", "UTF_32", "getUTF_32", "UTF_32$delegate", "normalizeCharsetName", "", "name", "forName", "(Ljava/lang/String;)Ljava/nio/charset/Charset;", "isSupported", "", "charsetName", "charset_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Charsets {
    public static final Charsets INSTANCE = new Charsets();

    /* JADX INFO: renamed from: UTF8$delegate, reason: from kotlin metadata */
    private static final Lazy UTF8 = LazyKt.lazy(new Function0() { // from class: com.fleeksoft.charset.Charsets$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Charsets.INSTANCE.forName("UTF-8");
        }
    });

    /* JADX INFO: renamed from: US_ASCII$delegate, reason: from kotlin metadata */
    private static final Lazy US_ASCII = LazyKt.lazy(new Function0() { // from class: com.fleeksoft.charset.Charsets$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Charsets.INSTANCE.forName("US-ASCII");
        }
    });

    /* JADX INFO: renamed from: ISO_8859_1$delegate, reason: from kotlin metadata */
    private static final Lazy ISO_8859_1 = LazyKt.lazy(new Function0() { // from class: com.fleeksoft.charset.Charsets$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Charsets.INSTANCE.forName("ISO-8859-1");
        }
    });

    /* JADX INFO: renamed from: UTF_16$delegate, reason: from kotlin metadata */
    private static final Lazy UTF_16 = LazyKt.lazy(new Function0() { // from class: com.fleeksoft.charset.Charsets$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Charsets.INSTANCE.forName("UTF-16");
        }
    });

    /* JADX INFO: renamed from: UTF_32$delegate, reason: from kotlin metadata */
    private static final Lazy UTF_32 = LazyKt.lazy(new Function0() { // from class: com.fleeksoft.charset.Charsets$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Charsets.INSTANCE.forName("UTF-32");
        }
    });

    private Charsets() {
    }

    public final Charset getUTF8() {
        return (Charset) UTF8.getValue();
    }

    public final Charset getUS_ASCII() {
        return (Charset) US_ASCII.getValue();
    }

    public final Charset getISO_8859_1() {
        return (Charset) ISO_8859_1.getValue();
    }

    public final Charset getUTF_16() {
        return (Charset) UTF_16.getValue();
    }

    public final Charset getUTF_32() {
        return (Charset) UTF_32.getValue();
    }

    public final String normalizeCharsetName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String lowerCase = StringsKt.replace$default(StringsKt.replace$default(name, "-", "", false, 4, (Object) null), "_", "", false, 4, (Object) null).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    public final Charset forName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return CharsetLookup.INSTANCE.forName(name);
    }

    public final boolean isSupported(String charsetName) {
        Intrinsics.checkNotNullParameter(charsetName, "charsetName");
        return CharsetLookup.INSTANCE.isSupported(charsetName);
    }
}
