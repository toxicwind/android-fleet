package io.ktor.util;

import io.ktor.http.auth.AuthScheme;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_concurrentKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ChannelResult;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: CryptoJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0005\u001aD\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u00022\u0006\u0010\u0001\u001a\u00020\u00002!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00000\u0002¢\u0006\u0004\b\b\u0010\t\u001a3\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0002H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a\u0015\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0015\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001a\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0017\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0018\u0010\u0019\u001a\"\u0010\u001e\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0015\u001a\u00020\u0014H\u0082@¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"", "algorithm", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", ES6Iterator.VALUE_PROPERTY, "salt", "", "getDigestFunction", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function1;", "text", "getDigest$CryptoKt__CryptoJvmKt", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)[B", "getDigest", "bytes", "sha1", "([B)[B", "Lio/ktor/util/Digest;", AuthScheme.Digest, "(Ljava/lang/String;)Lio/ktor/util/Digest;", "", "length", "generateNonceSuspend", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateNonceBlocking", "(I)Ljava/lang/String;", "", "initial", "generateNonceLong$CryptoKt__CryptoJvmKt", "(Ljava/lang/CharSequence;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateNonceLong", "ktor-utils"}, k = 5, mv = {2, 3, 0}, xi = 48, xs = "io/ktor/util/CryptoKt")
public final /* synthetic */ class CryptoKt__CryptoJvmKt {

    /* JADX INFO: renamed from: io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceSuspend$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CryptoJvm.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.util.CryptoKt__CryptoJvmKt", f = "CryptoJvm.kt", i = {0, 1, 1, 2, 2}, l = {68, 75, 77}, m = "generateNonceSuspend", n = {"length", "nonce", "length", "nonce", "length"}, s = {"I$0", "L$0", "I$0", "L$0", "I$0"}, v = 1)
    static final class C09871 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09871(Continuation<? super C09871> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CryptoKt.generateNonceSuspend(0, this);
        }
    }

    public static final Function1<String, byte[]> getDigestFunction(final String algorithm, final Function1<? super String, String> salt) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(salt, "salt");
        return new Function1() { // from class: io.ktor.util.CryptoKt__CryptoJvmKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CryptoKt__CryptoJvmKt.getDigestFunction$lambda$0$CryptoKt__CryptoJvmKt(algorithm, salt, (String) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] getDigestFunction$lambda$0$CryptoKt__CryptoJvmKt(String str, Function1 function1, String e) {
        Intrinsics.checkNotNullParameter(e, "e");
        return getDigest$CryptoKt__CryptoJvmKt(e, str, function1);
    }

    private static final byte[] getDigest$CryptoKt__CryptoJvmKt(String str, String str2, Function1<? super String, String> function1) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str2);
        byte[] bytes = function1.invoke(str).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        messageDigest.update(bytes);
        byte[] bytes2 = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes2);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "with(...)");
        return bArrDigest;
    }

    public static final byte[] sha1(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return bArrDigest;
    }

    public static final Digest Digest(String name) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(name, "name");
        MessageDigest messageDigest = MessageDigest.getInstance(name);
        Intrinsics.checkNotNullExpressionValue(messageDigest, "getInstance(...)");
        return DigestImpl.m8823boximpl(DigestImpl.m8825constructorimpl(messageDigest));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x008c, code lost:
    
        if (r9 == r1) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object generateNonceSuspend(int r8, kotlin.coroutines.Continuation<? super java.lang.String> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.util.CryptoKt__CryptoJvmKt.C09871
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceSuspend$1 r0 = (io.ktor.util.CryptoKt__CryptoJvmKt.C09871) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceSuspend$1 r0 = new io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceSuspend$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "substring(...)"
            r4 = 0
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L51
            if (r2 == r7) goto L4b
            if (r2 == r6) goto L41
            if (r2 != r5) goto L39
            int r8 = r0.I$0
            java.lang.Object r8 = r0.L$0
            java.lang.String r8 = (java.lang.String) r8
            kotlin.ResultKt.throwOnFailure(r9)
            return r9
        L39:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L41:
            int r8 = r0.I$0
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L8f
        L4b:
            int r8 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L66
        L51:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ktor.util.NonceKt.ensureNonceGeneratorRunning()
            kotlinx.coroutines.channels.Channel r9 = io.ktor.util.NonceKt.getNonceChannel()
            r0.I$0 = r8
            r0.label = r7
            java.lang.Object r9 = r9.receive(r0)
            if (r9 != r1) goto L66
            goto Lac
        L66:
            java.lang.String r9 = (java.lang.String) r9
            int r2 = r9.length()
            if (r2 < r8) goto L76
            java.lang.String r8 = r9.substring(r4, r8)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r3)
            return r8
        L76:
            r2 = 32
            if (r8 > r2) goto L99
            kotlinx.coroutines.channels.Channel r2 = io.ktor.util.NonceKt.getNonceChannel()
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$0 = r9
            r0.I$0 = r8
            r0.label = r6
            java.lang.Object r9 = r2.receive(r0)
            if (r9 != r1) goto L8f
            goto Lac
        L8f:
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r8 = r9.substring(r4, r8)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r3)
            return r8
        L99:
            r2 = r9
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$0 = r9
            r0.I$0 = r8
            r0.label = r5
            java.lang.Object r8 = generateNonceLong$CryptoKt__CryptoJvmKt(r2, r8, r0)
            if (r8 != r1) goto Lad
        Lac:
            return r1
        Lad:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.CryptoKt__CryptoJvmKt.generateNonceSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object generateNonceSuspend$default(int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 32;
        }
        return CryptoKt.generateNonceSuspend(i, continuation);
    }

    public static /* synthetic */ String generateNonceBlocking$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 32;
        }
        return CryptoKt.generateNonceBlocking(i);
    }

    public static final String generateNonceBlocking(int i) {
        String str = (String) ChannelResult.m10500getOrNullimpl(NonceKt.getNonceChannel().mo10489tryReceivePtdJZtk());
        if (str != null && str.length() >= i) {
            String strSubstring = str.substring(0, i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring;
        }
        NonceKt.ensureNonceGeneratorRunning();
        return (String) BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new AnonymousClass1(i, str, null), 1, null);
    }

    /* JADX INFO: renamed from: io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceBlocking$1, reason: invalid class name */
    /* JADX INFO: compiled from: CryptoJvm.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceBlocking$1", f = "CryptoJvm.kt", i = {}, l = {97, 99}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ int $length;
        final /* synthetic */ String $nonce;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i, String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$length = i;
            this.$nonce = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$length, this.$nonce, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x0034, code lost:
        
            if (r5 == r0) goto L19;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0053, code lost:
        
            if (r5 == r0) goto L19;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L1e
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r5)
                goto L56
            L12:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L1a:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L37
            L1e:
                kotlin.ResultKt.throwOnFailure(r5)
                int r5 = r4.$length
                r1 = 32
                if (r5 > r1) goto L46
                kotlinx.coroutines.channels.Channel r5 = io.ktor.util.NonceKt.getNonceChannel()
                r1 = r4
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r4.label = r3
                java.lang.Object r5 = r5.receive(r1)
                if (r5 != r0) goto L37
                goto L55
            L37:
                java.lang.String r5 = (java.lang.String) r5
                r0 = 0
                int r4 = r4.$length
                java.lang.String r4 = r5.substring(r0, r4)
                java.lang.String r5 = "substring(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
                return r4
            L46:
                java.lang.String r1 = r4.$nonce
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r3 = r4
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r4.label = r2
                java.lang.Object r5 = io.ktor.util.CryptoKt__CryptoJvmKt.access$generateNonceLong$CryptoKt__CryptoJvmKt(r1, r5, r3)
                if (r5 != r0) goto L56
            L55:
                return r0
            L56:
                java.lang.String r5 = (java.lang.String) r5
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.CryptoKt__CryptoJvmKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x0061  */
    /* JADX WARN: Code duplicated, block: B:22:0x007b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x007c  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x007c -> B:24:0x0082). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object generateNonceLong$CryptoKt__CryptoJvmKt(java.lang.CharSequence r10, int r11, kotlin.coroutines.Continuation<? super java.lang.String> r12) {
        /*
            boolean r0 = r12 instanceof io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceLong$1
            if (r0 == 0) goto L14
            r0 = r12
            io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceLong$1 r0 = (io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceLong$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceLong$1 r0 = new io.ktor.util.CryptoKt__CryptoJvmKt$generateNonceLong$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L4a
            if (r2 != r3) goto L42
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r2 = r0.L$2
            java.lang.StringBuilder r2 = (java.lang.StringBuilder) r2
            java.lang.Object r5 = r0.L$1
            java.lang.StringBuilder r5 = (java.lang.StringBuilder) r5
            java.lang.Object r6 = r0.L$0
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            kotlin.ResultKt.throwOnFailure(r12)
            r9 = r11
            r11 = r10
            r10 = r6
            r6 = r5
            r5 = r2
            r2 = r0
            r0 = r9
            goto L82
        L42:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L4a:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r11)
            if (r10 == 0) goto L57
            r12.append(r10)
        L57:
            r2 = r12
            r5 = r2
            r12 = r11
            r11 = r4
        L5b:
            int r6 = r2.length()
            if (r12 <= r6) goto L9d
            kotlinx.coroutines.channels.Channel r6 = io.ktor.util.NonceKt.getNonceChannel()
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r0.L$0 = r7
            r0.L$1 = r5
            r0.L$2 = r2
            r0.I$0 = r12
            r0.I$1 = r11
            r0.label = r3
            java.lang.Object r6 = r6.receive(r0)
            if (r6 != r1) goto L7c
            return r1
        L7c:
            r9 = r0
            r0 = r12
            r12 = r6
            r6 = r5
            r5 = r2
            r2 = r9
        L82:
            java.lang.String r12 = (java.lang.String) r12
            r7 = r12
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            int r8 = r5.length()
            int r8 = r0 - r8
            int r12 = r12.length()
            int r12 = kotlin.ranges.RangesKt.coerceAtMost(r8, r12)
            r5.append(r7, r4, r12)
            r12 = r0
            r0 = r2
            r2 = r5
            r5 = r6
            goto L5b
        L9d:
            java.lang.String r10 = r5.toString()
            java.lang.String r10 = r10.substring(r4, r12)
            java.lang.String r11 = "substring(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.CryptoKt__CryptoJvmKt.generateNonceLong$CryptoKt__CryptoJvmKt(java.lang.CharSequence, int, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
