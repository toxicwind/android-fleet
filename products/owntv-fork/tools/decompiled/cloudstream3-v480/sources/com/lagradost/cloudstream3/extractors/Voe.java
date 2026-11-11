package com.lagradost.cloudstream3.extractors;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Voe.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00110\u00152\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00110\u0015H\u0096@¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J\u0018\u0010!\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Voe;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "redirectRegex", "Lkotlin/text/Regex;", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptF7", "Lcom/google/gson/JsonObject;", "p8", "rot13", TvContractCompat.PARAM_INPUT, "replacePatterns", "removeUnderscores", "charShift", "shift", "", "reverse", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Voe extends ExtractorApi {
    private final String name = "Voe";
    private final String mainUrl = "https://voe.sx";
    private final boolean requiresReferer = true;
    private final Regex redirectRegex = new Regex("window.location.href\\s*=\\s*'([^']+)';");

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Voe$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Voe.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Voe", f = "Voe.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {64, 67, 79, 89}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "res", "redirectUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "res", "redirectUrl", "encodedString", "decryptedJson", "m3u8", "mp4", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "res", "redirectUrl", "encodedString", "decryptedJson", "m3u8", "mp4"}, nl = {65, 69, 84, 88}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Voe.getUrl$suspendImpl(Voe.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function2, continuation);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:47:0x0217  */
    /* JADX WARN: Code duplicated, block: B:49:0x021a  */
    /* JADX WARN: Code duplicated, block: B:51:0x0224  */
    /* JADX WARN: Code duplicated, block: B:53:0x0230  */
    /* JADX WARN: Code duplicated, block: B:54:0x0235  */
    /* JADX WARN: Code duplicated, block: B:57:0x023e  */
    /* JADX WARN: Code duplicated, block: B:58:0x0243  */
    /* JADX WARN: Code duplicated, block: B:60:0x0246  */
    /* JADX WARN: Code duplicated, block: B:63:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:67:0x02ef A[LOOP:0: B:65:0x02e9->B:67:0x02ef, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:69:0x0303  */
    /* JADX WARN: Code duplicated, block: B:71:0x0312  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x039d, code lost:
    
        if (r1 == r2) goto L73;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:60:0x0246, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:71:0x0312, please report this as an issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Voe r25, java.lang.String r26, java.lang.String r27, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r28, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r29, kotlin.coroutines.Continuation<? super kotlin.Unit> r30) {
        /*
            Method dump skipped, instruction units count: 934
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.Voe.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Voe, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Voe$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: Voe.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Voe$getUrl$2", f = "Voe.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $url;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$url = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$url, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ExtractorLink extractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            extractorLink.setReferer(this.$url);
            extractorLink.setQuality(Qualities.Unknown.getValue());
            return Unit.INSTANCE;
        }
    }

    private final JsonObject decryptF7(String p8) {
        try {
            JsonObject asJsonObject = JsonParser.parseString(MainAPIKt.base64Decode(reverse(charShift(MainAPIKt.base64Decode(removeUnderscores(replacePatterns(rot13(p8)))), 3)))).getAsJsonObject();
            Intrinsics.checkNotNull(asJsonObject);
            return asJsonObject;
        } catch (Exception e) {
            System.out.println((Object) ("Decryption error: " + e.getMessage()));
            return new JsonObject();
        }
    }

    private final String rot13(String input) {
        int i;
        String str = input;
        ArrayList arrayList = new ArrayList(str.length());
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            int i3 = 65;
            if ('A' <= cCharAt && cCharAt < '[') {
                i = (cCharAt - '4') % 26;
            } else {
                i3 = 97;
                if ('a' <= cCharAt && cCharAt < '{') {
                    i = (cCharAt - 'T') % 26;
                }
                arrayList.add(Character.valueOf(cCharAt));
            }
            cCharAt = (char) (i + i3);
            arrayList.add(Character.valueOf(cCharAt));
        }
        return CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null);
    }

    private final String replacePatterns(String input) {
        Iterator it = CollectionsKt.listOf((Object[]) new String[]{"@$", "^^", "~@", "%?", "*~", "!!", "#&"}).iterator();
        while (it.hasNext()) {
            input = new Regex(Regex.INSTANCE.escape((String) it.next())).replace(input, "_");
        }
        return input;
    }

    private final String removeUnderscores(String input) {
        return StringsKt.replace$default(input, "_", "", false, 4, (Object) null);
    }

    private final String charShift(String input, int shift) {
        String str = input;
        ArrayList arrayList = new ArrayList(str.length());
        for (int i = 0; i < str.length(); i++) {
            arrayList.add(Character.valueOf((char) (str.charAt(i) - shift)));
        }
        return CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null);
    }

    private final String reverse(String input) {
        return StringsKt.reversed((CharSequence) input).toString();
    }
}
