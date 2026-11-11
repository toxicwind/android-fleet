package dev.whyoleg.cryptography.materials;

import dev.whyoleg.cryptography.materials.EncodingFormat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations;

/* JADX INFO: compiled from: Decoder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0004J\u001f\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0096À\u0004¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH¦\u0080\u0004¢\u0006\u0002\u0010\u000bJ\u001f\u0010\f\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0096À\u0004¢\u0006\u0002\u0010\u000fJ\u001f\u0010\u0010\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004¢\u0006\u0002\u0010\u0011ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/materials/Decoder;", "F", "Ldev/whyoleg/cryptography/materials/EncodingFormat;", "M", "", "decodeFromByteArray", "format", "bytes", "", "(Ldev/whyoleg/cryptography/materials/EncodingFormat;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decodeFromByteArrayBlocking", "(Ldev/whyoleg/cryptography/materials/EncodingFormat;[B)Ljava/lang/Object;", "decodeFromByteString", "byteString", "Lkotlinx/io/bytestring/ByteString;", "(Ldev/whyoleg/cryptography/materials/EncodingFormat;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decodeFromByteStringBlocking", "(Ldev/whyoleg/cryptography/materials/EncodingFormat;Lkotlinx/io/bytestring/ByteString;)Ljava/lang/Object;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface Decoder<F extends EncodingFormat, M> {
    Object decodeFromByteArray(F f, byte[] bArr, Continuation<? super M> continuation);

    M decodeFromByteArrayBlocking(F format, byte[] bytes);

    Object decodeFromByteString(F f, ByteString byteString, Continuation<? super M> continuation);

    M decodeFromByteStringBlocking(F format, ByteString byteString);

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.materials.Decoder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Decoder.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ <F extends EncodingFormat, M> Object decodeFromByteArray$suspendImpl(Decoder<F, M> decoder, F f, byte[] bArr, Continuation<? super M> continuation) {
            return decoder.decodeFromByteArrayBlocking(f, bArr);
        }

        public static /* synthetic */ <F extends EncodingFormat, M> Object decodeFromByteString$suspendImpl(Decoder<F, M> decoder, F f, ByteString byteString, Continuation<? super M> continuation) {
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return decoder.decodeFromByteArray(f, byteString.getData(), continuation);
        }

        public static Object $default$decodeFromByteStringBlocking(Decoder _this, EncodingFormat format, ByteString byteString) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(byteString, "byteString");
            UnsafeByteStringOperations unsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
            return _this.decodeFromByteArrayBlocking(format, byteString.getData());
        }
    }
}
