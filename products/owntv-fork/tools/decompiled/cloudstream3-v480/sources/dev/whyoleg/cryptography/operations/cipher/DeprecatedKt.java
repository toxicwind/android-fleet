package dev.whyoleg.cryptography.operations.cipher;

import com.google.android.gms.cast.MediaError;
import io.ktor.http.ContentType;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: deprecated.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*J\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0000\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\t\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\r\"\u00020\u000e2\u00020\u000eB<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\r\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u000f\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u0010\"\u00020\u00112\u00020\u0011B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0010\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u0012\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u0013\"\u00020\u00142\u00020\u0014B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0013\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u0015\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u0016\"\u00020\u00172\u00020\u0017B<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0016\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u0018\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f*J\b\u0007\u0010\u0019\"\u00020\u001a2\u00020\u001aB<\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\"\b\u0005\u0012\u001e\b\u000bB\u001a\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0019\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\u001b\u0012\n\b\n\u0012\u0006\b\n0\u000b8\f¨\u0006\u001c"}, d2 = {"Cipher", "Ldev/whyoleg/cryptography/operations/Cipher;", "Lkotlin/Deprecated;", ContentType.Message.TYPE, "Moved to another package", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "imports", "dev.whyoleg.cryptography.operations.Cipher", "level", "Lkotlin/DeprecationLevel;", MediaError.ERROR_TYPE_ERROR, "Encryptor", "Ldev/whyoleg/cryptography/operations/Encryptor;", "dev.whyoleg.cryptography.operations.Encryptor", "Decryptor", "Ldev/whyoleg/cryptography/operations/Decryptor;", "dev.whyoleg.cryptography.operations.Decryptor", "AuthenticatedCipher", "Ldev/whyoleg/cryptography/operations/AuthenticatedCipher;", "dev.whyoleg.cryptography.operations.AuthenticatedCipher", "AuthenticatedEncryptor", "Ldev/whyoleg/cryptography/operations/AuthenticatedEncryptor;", "dev.whyoleg.cryptography.operations.AuthenticatedEncryptor", "AuthenticatedDecryptor", "Ldev/whyoleg/cryptography/operations/AuthenticatedDecryptor;", "dev.whyoleg.cryptography.operations.AuthenticatedDecryptor", "cryptography-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DeprecatedKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "AuthenticatedCipher", imports = {"dev.whyoleg.cryptography.operations.AuthenticatedCipher"}))
    public static /* synthetic */ void AuthenticatedCipher$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "AuthenticatedDecryptor", imports = {"dev.whyoleg.cryptography.operations.AuthenticatedDecryptor"}))
    public static /* synthetic */ void AuthenticatedDecryptor$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "AuthenticatedEncryptor", imports = {"dev.whyoleg.cryptography.operations.AuthenticatedEncryptor"}))
    public static /* synthetic */ void AuthenticatedEncryptor$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "Cipher", imports = {"dev.whyoleg.cryptography.operations.Cipher"}))
    public static /* synthetic */ void Cipher$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "Decryptor", imports = {"dev.whyoleg.cryptography.operations.Decryptor"}))
    public static /* synthetic */ void Decryptor$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Moved to another package", replaceWith = @ReplaceWith(expression = "Encryptor", imports = {"dev.whyoleg.cryptography.operations.Encryptor"}))
    public static /* synthetic */ void Encryptor$annotations() {
    }
}
