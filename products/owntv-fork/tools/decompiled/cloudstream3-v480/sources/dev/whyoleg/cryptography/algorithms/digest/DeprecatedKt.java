package dev.whyoleg.cryptography.algorithms.digest;

import com.google.android.gms.cast.MediaError;
import io.ktor.http.ContentType;
import io.ktor.http.auth.AuthScheme;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: deprecated.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*J\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0000\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\t\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\r\"\u00020\u000e2\u00020\u000eB<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\r\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u000f\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u0010\"\u00020\u00112\u00020\u0011B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0010\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u0012\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u0013\"\u00020\u00142\u00020\u0014B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0013\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u0015\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u0016\"\u00020\u00172\u00020\u0017B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0016\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u0018\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u0019\"\u00020\u001a2\u00020\u001aB<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0019\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u001b\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u001c\"\u00020\u001d2\u00020\u001dB<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u001c\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u001e\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u001f\"\u00020 2\u00020 B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u001f\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(!\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\"\"\u00020#2\u00020#B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\"\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b($\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010%\"\u00020&2\u00020&B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(%\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b('\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010(\"\u00020)2\u00020)B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b((\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(*\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f¨\u0006+"}, d2 = {AuthScheme.Digest, "Ldev/whyoleg/cryptography/algorithms/Digest;", "Lkotlin/Deprecated;", ContentType.Message.TYPE, "Moved to another package", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "imports", "dev.whyoleg.cryptography.algorithms.Digest", "level", "Lkotlin/DeprecationLevel;", MediaError.ERROR_TYPE_ERROR, "MD5", "Ldev/whyoleg/cryptography/algorithms/MD5;", "dev.whyoleg.cryptography.algorithms.MD5", "SHA1", "Ldev/whyoleg/cryptography/algorithms/SHA1;", "dev.whyoleg.cryptography.algorithms.SHA1", "SHA224", "Ldev/whyoleg/cryptography/algorithms/SHA224;", "dev.whyoleg.cryptography.algorithms.SHA224", "SHA256", "Ldev/whyoleg/cryptography/algorithms/SHA256;", "dev.whyoleg.cryptography.algorithms.SHA256", "SHA384", "Ldev/whyoleg/cryptography/algorithms/SHA384;", "dev.whyoleg.cryptography.algorithms.SHA384", "SHA512", "Ldev/whyoleg/cryptography/algorithms/SHA512;", "dev.whyoleg.cryptography.algorithms.SHA512", "SHA3_224", "Ldev/whyoleg/cryptography/algorithms/SHA3_224;", "dev.whyoleg.cryptography.algorithms.SHA3_224", "SHA3_256", "Ldev/whyoleg/cryptography/algorithms/SHA3_256;", "dev.whyoleg.cryptography.algorithms.SHA3_256", "SHA3_384", "Ldev/whyoleg/cryptography/algorithms/SHA3_384;", "dev.whyoleg.cryptography.algorithms.SHA3_384", "SHA3_512", "Ldev/whyoleg/cryptography/algorithms/SHA3_512;", "dev.whyoleg.cryptography.algorithms.SHA3_512", "cryptography-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DeprecatedKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = AuthScheme.Digest, imports = {"dev.whyoleg.cryptography.algorithms.Digest"}))
    public static /* synthetic */ void Digest$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "MD5", imports = {"dev.whyoleg.cryptography.algorithms.MD5"}))
    public static /* synthetic */ void MD5$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "SHA1", imports = {"dev.whyoleg.cryptography.algorithms.SHA1"}))
    public static /* synthetic */ void SHA1$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "SHA224", imports = {"dev.whyoleg.cryptography.algorithms.SHA224"}))
    public static /* synthetic */ void SHA224$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "SHA256", imports = {"dev.whyoleg.cryptography.algorithms.SHA256"}))
    public static /* synthetic */ void SHA256$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "SHA384", imports = {"dev.whyoleg.cryptography.algorithms.SHA384"}))
    public static /* synthetic */ void SHA384$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "SHA3_224", imports = {"dev.whyoleg.cryptography.algorithms.SHA3_224"}))
    public static /* synthetic */ void SHA3_224$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "SHA3_256", imports = {"dev.whyoleg.cryptography.algorithms.SHA3_256"}))
    public static /* synthetic */ void SHA3_256$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "SHA3_384", imports = {"dev.whyoleg.cryptography.algorithms.SHA3_384"}))
    public static /* synthetic */ void SHA3_384$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "SHA3_512", imports = {"dev.whyoleg.cryptography.algorithms.SHA3_512"}))
    public static /* synthetic */ void SHA3_512$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "SHA512", imports = {"dev.whyoleg.cryptography.algorithms.SHA512"}))
    public static /* synthetic */ void SHA512$annotations() {
    }
}
