package com.fleeksoft.charset.spi;

import java.nio.charset.Charset;
import kotlin.Metadata;

/* JADX INFO: compiled from: CharsetProvider.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u00062\u0006\u0010\u0007\u001a\u00020\bH&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/fleeksoft/charset/spi/CharsetProvider;", "", "<init>", "()V", "charsetForName", "Ljava/nio/charset/Charset;", "Lcom/fleeksoft/charset/Charset;", "charsetName", "", "(Ljava/lang/String;)Ljava/nio/charset/Charset;", "charset_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class CharsetProvider {
    public abstract Charset charsetForName(String charsetName);
}
