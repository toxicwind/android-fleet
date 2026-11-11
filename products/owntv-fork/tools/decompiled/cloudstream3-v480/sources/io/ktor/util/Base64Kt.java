package io.ktor.util;

import io.ktor.utils.io.core.BytePacketBuilderKt;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.io.Buffer;
import kotlinx.io.Source;
import kotlinx.io.SourcesKt;

/* JADX INFO: compiled from: Base64.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0013\u0010\u0001\u001a\u00020\u0000*\u00020\u0000H\u0007¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0013\u0010\u0001\u001a\u00020\u0000*\u00020\u0003H\u0007¢\u0006\u0004\b\u0001\u0010\u0004\u001a\u0013\u0010\u0001\u001a\u00020\u0000*\u00020\u0005H\u0007¢\u0006\u0004\b\u0001\u0010\u0006\u001a\u0013\u0010\u0007\u001a\u00020\u0000*\u00020\u0000H\u0007¢\u0006\u0004\b\u0007\u0010\u0002\u001a\u0013\u0010\b\u001a\u00020\u0003*\u00020\u0000H\u0007¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\b\u001a\u00060\u0005j\u0002`\n*\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\u000b¨\u0006\f"}, d2 = {"", "encodeBase64", "(Ljava/lang/String;)Ljava/lang/String;", "", "([B)Ljava/lang/String;", "Lkotlinx/io/Source;", "(Lkotlinx/io/Source;)Ljava/lang/String;", "decodeBase64String", "decodeBase64Bytes", "(Ljava/lang/String;)[B", "Lio/ktor/utils/io/core/Input;", "(Lkotlinx/io/Source;)Lkotlinx/io/Source;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class Base64Kt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `Base64.Default.encode()` from the Kotlin standard library.", replaceWith = @ReplaceWith(expression = "Base64.encode(encodeToByteArray())", imports = {"kotlin.io.encoding.Base64"}))
    public static final String encodeBase64(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Base64.encode$default(Base64.INSTANCE, StringsKt.encodeToByteArray(str), 0, 0, 6, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `Base64.Default.encode()` from the Kotlin standard library.", replaceWith = @ReplaceWith(expression = "Base64.encode(this)", imports = {"kotlin.io.encoding.Base64"}))
    public static final String encodeBase64(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return Base64.encode$default(Base64.INSTANCE, bArr, 0, 0, 6, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `Base64.Default.encode()` from the Kotlin standard library.", replaceWith = @ReplaceWith(expression = "Base64.encode(readByteArray())", imports = {"kotlin.io.encoding.Base64"}))
    public static final String encodeBase64(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return Base64.encode$default(Base64.INSTANCE, SourcesKt.readByteArray(source), 0, 0, 6, null);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Ambiguous and lenient. Use `Base64.Default.decode()` from the Kotlin standard library.", replaceWith = @ReplaceWith(expression = "Base64.decode(this).decodeToString()", imports = {"kotlin.io.encoding.Base64"}))
    public static final String decodeBase64String(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return StringsKt.decodeToString(decodeBase64Bytes(str));
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Ambiguous and lenient. Use `Base64.Default.decode()` from the Kotlin standard library.", replaceWith = @ReplaceWith(expression = "Base64.decode(this)", imports = {"kotlin.io.encoding.Base64"}))
    public static final byte[] decodeBase64Bytes(String str) {
        Object objM8936constructorimpl;
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(Base64.decode$default(Base64.INSTANCE.withPadding(Base64.PaddingOption.PRESENT_OPTIONAL), str, 0, 0, 6, (Object) null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            objM8936constructorimpl = Base64.decode$default(Base64.INSTANCE.getUrlSafe().withPadding(Base64.PaddingOption.PRESENT_OPTIONAL), str, 0, 0, 6, (Object) null);
        }
        return (byte[]) objM8936constructorimpl;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Ambiguous and lenient. Use `Base64.Default.decode()` from the Kotlin standard library.", replaceWith = @ReplaceWith(expression = "buildPacket { writeFully(Base64.decode(readByteArray())) }", imports = {"kotlin.io.encoding.Base64"}))
    public static final Source decodeBase64Bytes(Source source) {
        Object objM8936constructorimpl;
        Intrinsics.checkNotNullParameter(source, "<this>");
        Buffer buffer = new Buffer();
        Buffer buffer2 = buffer;
        byte[] byteArray = SourcesKt.readByteArray(source);
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(Base64.decode$default(Base64.INSTANCE.withPadding(Base64.PaddingOption.PRESENT_OPTIONAL), byteArray, 0, 0, 6, (Object) null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            objM8936constructorimpl = Base64.decode$default(Base64.INSTANCE.getUrlSafe().withPadding(Base64.PaddingOption.PRESENT_OPTIONAL), byteArray, 0, 0, 6, (Object) null);
        }
        BytePacketBuilderKt.writeFully$default(buffer2, (byte[]) objM8936constructorimpl, 0, 0, 6, null);
        return buffer;
    }
}
