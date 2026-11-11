package dev.whyoleg.cryptography;

import io.ktor.http.ContentType;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* JADX INFO: compiled from: CryptographyException.kt */
/* JADX INFO: loaded from: classes7.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "IllegalStateException is throw instead")
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0017\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0013\bV\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\bV\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0005\u0010\tB\u0013\bV\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0005\u0010\n¨\u0006\u000b"}, d2 = {"Ldev/whyoleg/cryptography/CryptographyException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", ContentType.Message.TYPE, "", "<init>", "(Ljava/lang/String;)V", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/Throwable;)V", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class CryptographyException extends RuntimeException {
    public CryptographyException(String str) {
        super(str);
    }

    public CryptographyException(String str, Throwable th) {
        super(str, th);
    }

    public CryptographyException(Throwable th) {
        super(th);
    }
}
