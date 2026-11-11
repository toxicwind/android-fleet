package com.lagradost.cloudstream3.extractors.helper;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.algorithms.AES;
import dev.whyoleg.cryptography.operations.IvCipher;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jsoup.nodes.Document;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: GogoHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u001e\u001f B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002J*\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0002Jr\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00110\u00152\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0086@¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper;", "", "<init>", "()V", "aesCbc", "Ldev/whyoleg/cryptography/algorithms/AES$CBC;", "getEncryptionKey", "", "id", "cryptoHandler", TypedValues.Custom.S_STRING, "iv", "secretKeyString", "encrypt", "", "extractVidstream", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "", "iframeUrl", "mainApiName", "callback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "secretKey", "secretDecryptKey", "isUsingAdaptiveKeys", "isUsingAdaptiveData", "iframeDocument", "Lorg/jsoup/nodes/Document;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLorg/jsoup/nodes/Document;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "GogoSources", "GogoSource", "GogoJsonData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class GogoHelper {
    public static final GogoHelper INSTANCE = new GogoHelper();
    private static final AES.CBC aesCbc = (AES.CBC) CryptographyProvider.INSTANCE.getDefault().get(AES.CBC.INSTANCE);

    private GogoHelper() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getEncryptionKey(final String id) {
        return (String) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.extractors.helper.GogoHelper$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GogoHelper.getEncryptionKey$lambda$0(id);
            }
        });
    }

    static final String getEncryptionKey$lambda$0(String str) {
        String str2 = str;
        ArrayList arrayList = new ArrayList(str2.length());
        for (int i = 0; i < str2.length(); i++) {
            String string = Integer.toString(str2.charAt(i), CharsKt.checkRadix(16));
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            arrayList.add(string);
        }
        String strSubstring = CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null).substring(0, 32);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    static /* synthetic */ String cryptoHandler$default(GogoHelper gogoHelper, String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return gogoHelper.cryptoHandler(str, str2, str3, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String cryptoHandler(String string, String iv, String secretKeyString, boolean encrypt) {
        byte[] bArrEncodeToByteArray = StringsKt.encodeToByteArray(iv);
        IvCipher ivCipherCipher = aesCbc.keyDecoder().decodeFromByteArrayBlocking(AES.Key.Format.RAW, StringsKt.encodeToByteArray(secretKeyString)).cipher(true);
        if (!encrypt) {
            return StringsKt.decodeToString(ivCipherCipher.decryptWithIvBlocking(bArrEncodeToByteArray, MainAPIKt.base64DecodeArray(string)));
        }
        return MainAPIKt.base64Encode(ivCipherCipher.encryptWithIvBlocking(bArrEncodeToByteArray, StringsKt.encodeToByteArray(string)));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.helper.GogoHelper$extractVidstream$2, reason: invalid class name */
    /* JADX INFO: compiled from: GogoHelper.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.helper.GogoHelper$extractVidstream$2", f = "GogoHelper.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, l = {89, 101, 109, 142, 143}, m = "invokeSuspend", n = {"id", "document", "id", "document", "foundIv", "foundKey", "foundDecryptKey", ImagesContract.URL, "mainUrl", "encryptedId", "id", "document", "foundIv", "foundKey", "foundDecryptKey", ImagesContract.URL, "mainUrl", "encryptedId", "encryptRequestData", "id", "document", "foundIv", "foundKey", "foundDecryptKey", ImagesContract.URL, "mainUrl", "encryptedId", "encryptRequestData", "jsonResponse", "dataencrypted", "datadecrypted", "sources", "$this$forEach$iv", "element$iv", "it", "$i$f$forEach", "$i$a$-forEach-GogoHelper$extractVidstream$2$1", "id", "document", "foundIv", "foundKey", "foundDecryptKey", ImagesContract.URL, "mainUrl", "encryptedId", "encryptRequestData", "jsonResponse", "dataencrypted", "datadecrypted", "sources", "$this$forEach$iv", "element$iv", "it", "$i$f$forEach", "$i$a$-forEach-GogoHelper$extractVidstream$2$2"}, nl = {166, 102, 113, ByteCode.ATHROW, ByteCode.INSTANCEOF}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$17", "L$18", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$17", "L$18", "I$0", "I$1"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
        final /* synthetic */ Document $iframeDocument;
        final /* synthetic */ String $iframeUrl;
        final /* synthetic */ boolean $isUsingAdaptiveData;
        final /* synthetic */ boolean $isUsingAdaptiveKeys;
        final /* synthetic */ String $iv;
        final /* synthetic */ String $mainApiName;
        final /* synthetic */ String $secretDecryptKey;
        final /* synthetic */ String $secretKey;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(String str, String str2, String str3, boolean z, String str4, Document document, boolean z2, Function1<? super ExtractorLink, Unit> function1, String str5, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$iv = str;
            this.$secretKey = str2;
            this.$secretDecryptKey = str3;
            this.$isUsingAdaptiveKeys = z;
            this.$iframeUrl = str4;
            this.$iframeDocument = document;
            this.$isUsingAdaptiveData = z2;
            this.$callback = function1;
            this.$mainApiName = str5;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.$iv, this.$secretKey, this.$secretDecryptKey, this.$isUsingAdaptiveKeys, this.$iframeUrl, this.$iframeDocument, this.$isUsingAdaptiveData, this.$callback, this.$mainApiName, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:108:0x049b  */
        /* JADX WARN: Code duplicated, block: B:111:0x0515  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v12, types: [org.jsoup.nodes.Document] */
        /* JADX WARN: Type inference failed for: r6v2, types: [T, org.jsoup.nodes.Document] */
        /* JADX WARN: Type inference failed for: r6v21, types: [T, org.jsoup.nodes.Document] */
        /* JADX WARN: Type inference failed for: r6v53 */
        /* JADX WARN: Type inference failed for: r6v54 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:111:0x0515 -> B:112:0x051d). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:123:0x05ee -> B:124:0x05fb). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r38) {
            /*
                Method dump skipped, instruction units count: 1550
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.helper.GogoHelper.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x00ab, code lost:
        
            if (r15 == r0) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x00f6, code lost:
        
            if (r15 == r0) goto L27;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final java.lang.Object invokeSuspend$invokeGogoSource(java.lang.String r11, java.lang.String r12, com.lagradost.cloudstream3.extractors.helper.GogoHelper.GogoSource r13, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
            /*
                Method dump skipped, instruction units count: 255
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.helper.GogoHelper.AnonymousClass2.invokeSuspend$invokeGogoSource(java.lang.String, java.lang.String, com.lagradost.cloudstream3.extractors.helper.GogoHelper$GogoSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public final Object extractVidstream(String str, String str2, Function1<? super ExtractorLink, Unit> function1, String str3, String str4, String str5, boolean z, boolean z2, Document document, Continuation<? super Resource<Unit>> continuation) {
        return ArchComponentExtKt.safeApiCall(new AnonymousClass2(str3, str4, str5, z, str, document, z2, function1, str2, null), continuation);
    }

    /* JADX INFO: compiled from: GogoHelper.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B+\u0012\u0010\b\u0001\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B;\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0015\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R$\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoSources;", "", "source", "", "Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoSource;", "sourceBk", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSource$annotations", "()V", "getSource", "()Ljava/util/List;", "getSourceBk$annotations", "getSourceBk", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GogoSources {

        @JsonProperty("source")
        private final List<GogoSource> source;

        @JsonProperty("sourceBk")
        private final List<GogoSource> sourceBk;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.extractors.helper.GogoHelper$GogoSources$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GogoHelper.GogoSources._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.extractors.helper.GogoHelper$GogoSources$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GogoHelper.GogoSources._childSerializers$_anonymous_$0();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(GogoHelper$GogoSource$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(GogoHelper$GogoSource$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ GogoSources copy$default(GogoSources gogoSources, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = gogoSources.source;
            }
            if ((i & 2) != 0) {
                list2 = gogoSources.sourceBk;
            }
            return gogoSources.copy(list, list2);
        }

        @SerialName("source")
        public static /* synthetic */ void getSource$annotations() {
        }

        @SerialName("sourceBk")
        public static /* synthetic */ void getSourceBk$annotations() {
        }

        public final List<GogoSource> component1() {
            return this.source;
        }

        public final List<GogoSource> component2() {
            return this.sourceBk;
        }

        public final GogoSources copy(@JsonProperty("source") List<GogoSource> source, @JsonProperty("sourceBk") List<GogoSource> sourceBk) {
            return new GogoSources(source, sourceBk);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GogoSources)) {
                return false;
            }
            GogoSources gogoSources = (GogoSources) other;
            return Intrinsics.areEqual(this.source, gogoSources.source) && Intrinsics.areEqual(this.sourceBk, gogoSources.sourceBk);
        }

        public int hashCode() {
            List<GogoSource> list = this.source;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            List<GogoSource> list2 = this.sourceBk;
            return iHashCode + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "GogoSources(source=" + this.source + ", sourceBk=" + this.sourceBk + ')';
        }

        /* JADX INFO: compiled from: GogoHelper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoSources$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoSources;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GogoSources> serializer() {
                return GogoHelper$GogoSources$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GogoSources(int i, List list, List list2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, GogoHelper$GogoSources$$serializer.INSTANCE.getDescriptor());
            }
            this.source = list;
            this.sourceBk = list2;
        }

        public GogoSources(@JsonProperty("source") List<GogoSource> list, @JsonProperty("sourceBk") List<GogoSource> list2) {
            this.source = list;
            this.sourceBk = list2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(GogoSources self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.source);
            output.encodeNullableSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.sourceBk);
        }

        public final List<GogoSource> getSource() {
            return this.source;
        }

        public final List<GogoSource> getSourceBk() {
            return this.sourceBk;
        }
    }

    /* JADX INFO: compiled from: GogoHelper.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002*+B5\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bBC\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J7\u0010\u001c\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\nHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoSource;", "", UtilsKt.SCHEME_FILE, "", "label", "type", "default", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFile$annotations", "()V", "getFile", "()Ljava/lang/String;", "getLabel$annotations", "getLabel", "getType$annotations", "getType", "getDefault$annotations", "getDefault", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GogoSource {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("default")
        private final String default;

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("label")
        private final String label;

        @JsonProperty("type")
        private final String type;

        public static /* synthetic */ GogoSource copy$default(GogoSource gogoSource, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = gogoSource.file;
            }
            if ((i & 2) != 0) {
                str2 = gogoSource.label;
            }
            if ((i & 4) != 0) {
                str3 = gogoSource.type;
            }
            if ((i & 8) != 0) {
                str4 = gogoSource.default;
            }
            return gogoSource.copy(str, str2, str3, str4);
        }

        @SerialName("default")
        public static /* synthetic */ void getDefault$annotations() {
        }

        @SerialName(UtilsKt.SCHEME_FILE)
        public static /* synthetic */ void getFile$annotations() {
        }

        @SerialName("label")
        public static /* synthetic */ void getLabel$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDefault() {
            return this.default;
        }

        public final GogoSource copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label, @JsonProperty("type") String type, @JsonProperty("default") String str) {
            Intrinsics.checkNotNullParameter(file, "file");
            return new GogoSource(file, label, type, str);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GogoSource)) {
                return false;
            }
            GogoSource gogoSource = (GogoSource) other;
            return Intrinsics.areEqual(this.file, gogoSource.file) && Intrinsics.areEqual(this.label, gogoSource.label) && Intrinsics.areEqual(this.type, gogoSource.type) && Intrinsics.areEqual(this.default, gogoSource.default);
        }

        public int hashCode() {
            int iHashCode = this.file.hashCode() * 31;
            String str = this.label;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.type;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.default;
            return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "GogoSource(file=" + this.file + ", label=" + this.label + ", type=" + this.type + ", default=" + this.default + ')';
        }

        /* JADX INFO: compiled from: GogoHelper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoSource$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoSource;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GogoSource> serializer() {
                return GogoHelper$GogoSource$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GogoSource(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, GogoHelper$GogoSource$$serializer.INSTANCE.getDescriptor());
            }
            this.file = str;
            this.label = str2;
            this.type = str3;
            if ((i & 8) == 0) {
                this.default = null;
            } else {
                this.default = str4;
            }
        }

        public GogoSource(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String str, @JsonProperty("type") String str2, @JsonProperty("default") String str3) {
            Intrinsics.checkNotNullParameter(file, "file");
            this.file = file;
            this.label = str;
            this.type = str2;
            this.default = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(GogoSource self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.file);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.label);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.type);
            if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.default == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.default);
        }

        public /* synthetic */ GogoSource(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i & 8) != 0 ? null : str4);
        }

        public final String getFile() {
            return this.file;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getType() {
            return this.type;
        }

        public final String getDefault() {
            return this.default;
        }
    }

    /* JADX INFO: compiled from: GogoHelper.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoJsonData;", "", "data", "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GogoJsonData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final String data;

        /* JADX WARN: Multi-variable type inference failed */
        public GogoJsonData() {
            this((String) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ GogoJsonData copy$default(GogoJsonData gogoJsonData, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = gogoJsonData.data;
            }
            return gogoJsonData.copy(str);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getData() {
            return this.data;
        }

        public final GogoJsonData copy(@JsonProperty("data") String data) {
            return new GogoJsonData(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GogoJsonData) && Intrinsics.areEqual(this.data, ((GogoJsonData) other).data);
        }

        public int hashCode() {
            String str = this.data;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "GogoJsonData(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: GogoHelper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoJsonData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/extractors/helper/GogoHelper$GogoJsonData;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GogoJsonData> serializer() {
                return GogoHelper$GogoJsonData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GogoJsonData(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.data = null;
            } else {
                this.data = str;
            }
        }

        public GogoJsonData(@JsonProperty("data") String str) {
            this.data = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(GogoJsonData self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.data == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.data);
        }

        public /* synthetic */ GogoJsonData(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        public final String getData() {
            return this.data;
        }
    }
}
