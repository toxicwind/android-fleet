package com.lagradost.cloudstream3.extractors;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: Videa.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00100\u0014H\u0096@¢\u0006\u0002\u0010\u0018J.\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u0014H\u0082@¢\u0006\u0002\u0010\u001bJ\"\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001d2\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J*\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00052\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00100\u0014H\u0082@¢\u0006\u0002\u0010\u001bJ\u0018\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0005H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Videa;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "videaSecret", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getXmlUrl", "cookieCallback", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateTokens", "Lkotlin/Triple;", "nonce", "parseVideoSources", "xml", "rc4DecryptBytes", "encryptedBytes", "", "key", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Videa extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "Videa";
    private final String mainUrl = "https://videa.hu";
    private final String videaSecret = "xHb0ZvME5q8CBcoQi6AngerDu3FGO9fkUlwPmLVY_RTzj2hJIS4NasXWKy1td7p";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Videa$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Videa.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Videa", f = "Videa.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {35, 36, 64}, m = "getUrl", n = {ImagesContract.URL, "referer", "subtitleCallback", "callback", "currentUrl", "key", "visitedUrls", PeertubeParsingHelper.COUNT_KEY, ImagesContract.URL, "referer", "subtitleCallback", "callback", "currentUrl", "key", "visitedUrls", "webUrl", PeertubeParsingHelper.COUNT_KEY, ImagesContract.URL, "referer", "subtitleCallback", "callback", "currentUrl", "key", "visitedUrls", "webUrl", "response", TtmlNode.TAG_BODY, "rawBytes", "videaXml", "redirectMatch", PeertubeParsingHelper.COUNT_KEY, "isXml"}, nl = {36, 37, 65}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0", "I$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
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
            return Videa.this.getUrl(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Videa$getXmlUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Videa.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Videa", f = "Videa.kt", i = {0, 0, 1, 1, 1, 1, 1, 1}, l = {71, 96}, m = "getXmlUrl", n = {ImagesContract.URL, "cookieCallback", ImagesContract.URL, "cookieCallback", "response", ContentDisposition.Parameters.Size, "html", "playerUrl"}, nl = {72, 97}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 2)
    static final class C07221 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C07221(Continuation<? super C07221> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Videa.this.getXmlUrl(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Videa$parseVideoSources$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Videa.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Videa", f = "Videa.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {160}, m = "parseVideoSources", n = {"xml", "callback", "sourceRegex", "sources", "sourceMatch", "sourceName", "exp", "sourceUrl", "hashMatch", "match", "hash", "finalUrl", "$i$a$-let-Videa$parseVideoSources$2"}, nl = {159}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0"}, v = 2)
    static final class C07231 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
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

        C07231(Continuation<? super C07231> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Videa.this.parseVideoSources(null, null, this);
        }
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

    /* JADX WARN: Code duplicated, block: B:25:0x013a  */
    /* JADX WARN: Code duplicated, block: B:28:0x014a  */
    /* JADX WARN: Code duplicated, block: B:30:0x014d  */
    /* JADX WARN: Code duplicated, block: B:33:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:36:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:47:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:49:0x0201  */
    /* JADX WARN: Code duplicated, block: B:50:0x0206  */
    /* JADX WARN: Code duplicated, block: B:52:0x0212  */
    /* JADX WARN: Code duplicated, block: B:54:0x0215  */
    /* JADX WARN: Code duplicated, block: B:57:0x0245  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x02c7, code lost:
    
        if (parseVideoSources(r7, r9, r13) == r3) goto L62;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:54:0x0215, please report this as an issue */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x01b5 -> B:34:0x01c4). Please report as a decompilation issue!!! */
    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getUrl(java.lang.String r32, java.lang.String r33, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r34, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r35, kotlin.coroutines.Continuation<? super kotlin.Unit> r36) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 717
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.Videa.getUrl(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static final Unit getUrl$lambda$0(String cookie) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:45:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:47:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:48:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:51:0x01ce A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:52:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:54:0x0203  */
    /* JADX WARN: Code duplicated, block: B:55:0x0218  */
    /* JADX WARN: Code duplicated, block: B:57:0x0223  */
    /* JADX WARN: Code duplicated, block: B:60:0x0260  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Instruction removed from duplicated block: B:54:0x0203, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:57:0x0223, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r21v1 */
    public final Object getXmlUrl(String str, Function1<? super String, Unit> function1, Continuation<? super String> continuation) {
        C07221 c07221;
        Object obj;
        String str2;
        Object obj2;
        String str3;
        C07221 c07222;
        String str4;
        Object obj3;
        Function1<? super String, Unit> function2;
        String str5;
        ?? r12;
        int i;
        boolean z;
        String string;
        String str6;
        String str7;
        Function1<? super String, Unit> function3;
        String str8;
        ?? r13;
        int i2;
        MatchResult matchResultFind$default;
        String str9;
        String str10;
        MatchResult matchResultFind$default2;
        if (continuation instanceof C07221) {
            c07221 = (C07221) continuation;
            if ((c07221.label & Integer.MIN_VALUE) != 0) {
                c07221.label -= Integer.MIN_VALUE;
            } else {
                c07221 = new C07221(continuation);
            }
        } else {
            c07221 = new C07221(continuation);
        }
        Object obj4 = c07221.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c07221.label;
        if (i3 != 0) {
            if (i3 == 1) {
                Function1<? super String, Unit> function4 = (Function1) c07221.L$1;
                str4 = (String) c07221.L$0;
                ResultKt.throwOnFailure(obj4);
                str2 = "sl=([^;]+)";
                str3 = "Set-Cookie";
                obj3 = obj4;
                obj = coroutine_suspended;
                function2 = function4;
                c07222 = c07221;
                obj2 = null;
            } else {
                if (i3 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                str7 = (String) c07221.L$5;
                function3 = (Function1) c07221.L$1;
                ResultKt.throwOnFailure(obj4);
                str6 = "sl=([^;]+)";
                str3 = "Set-Cookie";
            }
            NiceResponse niceResponse = (NiceResponse) obj4;
            String text = niceResponse.getText();
            str8 = niceResponse.getHeaders().get(str3);
            if (str8 != null) {
                r13 = 0;
                r13 = 0;
                i2 = 2;
                matchResultFind$default2 = Regex.find$default(new Regex(str6), str8, 0, 2, null);
                if (matchResultFind$default2 != null) {
                    function3.invoke(matchResultFind$default2.getValue());
                }
            } else {
                r13 = 0;
                i2 = 2;
            }
            matchResultFind$default = Regex.find$default(new Regex("_xt\\s*=\\s*\"([^\"]+)"), text, r13, i2, null);
            if (matchResultFind$default == null) {
                return null;
            }
            Triple<String, String, String> tripleGenerateTokens = generateTokens(matchResultFind$default.getGroupValues().get(1));
            String strComponent1 = tripleGenerateTokens.component1();
            String strComponent2 = tripleGenerateTokens.component2();
            tripleGenerateTokens.component3();
            str9 = str7;
            if (StringsKt.contains$default(str9, "f=", (boolean) r13, i2, (Object) null)) {
                str10 = "f=" + StringsKt.substringBefore$default(StringsKt.substringAfter$default(str7, "f=", (String) null, i2, (Object) null), "&", (String) null, i2, (Object) null);
            } else {
                if (StringsKt.contains$default(str9, "v=", (boolean) r13, i2, (Object) null)) {
                    return null;
                }
                str10 = "v=" + StringsKt.substringBefore$default(StringsKt.substringAfter$default(str7, "v=", (String) null, i2, (Object) null), "&", (String) null, i2, (Object) null);
            }
            return getMainUrl() + "/player/xml?platform=desktop&" + str10 + "&_s=" + strComponent1 + "&_t=" + strComponent2;
        }
        ResultKt.throwOnFailure(obj4);
        obj = coroutine_suspended;
        Requests app2 = MainActivityKt.getApp();
        c07221.L$0 = str;
        c07221.L$1 = function1;
        c07221.label = 1;
        str2 = "sl=([^;]+)";
        C07221 c07223 = c07221;
        obj2 = null;
        str3 = "Set-Cookie";
        Object obj5 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c07223, 4094, null);
        c07222 = c07223;
        if (obj5 != obj) {
            str4 = str;
            obj3 = obj5;
            function2 = function1;
        }
        return obj;
        NiceResponse niceResponse2 = (NiceResponse) obj3;
        Long size = niceResponse2.getSize();
        if (size != null && size.longValue() > 5000000) {
            return obj2;
        }
        String text2 = niceResponse2.getText();
        String str11 = niceResponse2.getHeaders().get(str3);
        if (str11 != null) {
            str5 = str2;
            r12 = 0;
            r12 = 0;
            i = 2;
            MatchResult matchResultFind$default3 = Regex.find$default(new Regex(str5), str11, 0, 2, obj2);
            if (matchResultFind$default3 != null) {
                function2.invoke(matchResultFind$default3.getValue());
            }
        } else {
            str5 = str2;
            r12 = 0;
            i = 2;
        }
        if (StringsKt.contains$default(str4, "/player", (boolean) r12, i, obj2)) {
            string = str4;
            z = true;
        } else {
            MatchResult matchResultFind$default4 = Regex.find$default(new Regex("<iframe.*?src=\"(/player\\?[^\\\"]+)"), text2, r12, i, obj2);
            if (matchResultFind$default4 == null) {
                return obj2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(getMainUrl());
            z = true;
            sb.append(matchResultFind$default4.getGroupValues().get(1));
            string = sb.toString();
            if (string == null) {
                return obj2;
            }
        }
        Requests app3 = MainActivityKt.getApp();
        c07222.L$0 = SpillingKt.nullOutSpilledVariable(str4);
        c07222.L$1 = function2;
        c07222.L$2 = SpillingKt.nullOutSpilledVariable(niceResponse2);
        c07222.L$3 = SpillingKt.nullOutSpilledVariable(size);
        c07222.L$4 = SpillingKt.nullOutSpilledVariable(text2);
        c07222.L$5 = string;
        c07222.label = i;
        C07221 c07224 = c07222;
        String str12 = string;
        Function1<? super String, Unit> function5 = function2;
        str6 = str5;
        Object obj6 = Requests.get$default(app3, str12, null, null, null, null, false, 0, null, 0L, null, false, null, c07224, 4094, null);
        if (obj6 != obj) {
            obj4 = obj6;
            str7 = str12;
            function3 = function5;
            NiceResponse niceResponse3 = (NiceResponse) obj4;
            String text3 = niceResponse3.getText();
            str8 = niceResponse3.getHeaders().get(str3);
            if (str8 != null) {
                r13 = 0;
                r13 = 0;
                i2 = 2;
                matchResultFind$default2 = Regex.find$default(new Regex(str6), str8, 0, 2, null);
                if (matchResultFind$default2 != null) {
                    function3.invoke(matchResultFind$default2.getValue());
                }
            } else {
                r13 = 0;
                i2 = 2;
            }
            matchResultFind$default = Regex.find$default(new Regex("_xt\\s*=\\s*\"([^\"]+)"), text3, r13, i2, null);
            if (matchResultFind$default == null) {
                return null;
            }
            Triple<String, String, String> tripleGenerateTokens2 = generateTokens(matchResultFind$default.getGroupValues().get(1));
            String strComponent3 = tripleGenerateTokens2.component1();
            String strComponent4 = tripleGenerateTokens2.component2();
            tripleGenerateTokens2.component3();
            str9 = str7;
            if (StringsKt.contains$default(str9, "f=", (boolean) r13, i2, (Object) null)) {
                str10 = "f=" + StringsKt.substringBefore$default(StringsKt.substringAfter$default(str7, "f=", (String) null, i2, (Object) null), "&", (String) null, i2, (Object) null);
            } else {
                if (StringsKt.contains$default(str9, "v=", (boolean) r13, i2, (Object) null)) {
                    return null;
                }
                str10 = "v=" + StringsKt.substringBefore$default(StringsKt.substringAfter$default(str7, "v=", (String) null, i2, (Object) null), "&", (String) null, i2, (Object) null);
            }
            return getMainUrl() + "/player/xml?platform=desktop&" + str10 + "&_s=" + strComponent3 + "&_t=" + strComponent4;
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object getXmlUrl$default(Videa videa, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.extractors.Videa$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Videa.getXmlUrl$lambda$0((String) obj2);
                }
            };
        }
        return videa.getXmlUrl(str, function1, continuation);
    }

    static final Unit getXmlUrl$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    private final Triple<String, String, String> generateTokens(String nonce) {
        String strTake = StringsKt.take(nonce, 32);
        String strSubstring = nonce.substring(32);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String str = "";
        for (int i = 0; i < 32; i++) {
            str = str + strSubstring.charAt(i - (StringsKt.indexOf$default((CharSequence) this.videaSecret, strTake.charAt(i), 0, false, 6, (Object) null) - 31));
        }
        IntRange intRange = new IntRange(1, 8);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            arrayList.add(Character.valueOf(StringsKt.random("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", Random.INSTANCE)));
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null);
        StringBuilder sb = new StringBuilder();
        String strSubstring2 = str.substring(16);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        sb.append(strSubstring2);
        sb.append(strJoinToString$default);
        return new Triple<>(strJoinToString$default, StringsKt.take(str, 16), sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:19:0x00da  */
    /* JADX WARN: Code duplicated, block: B:24:0x01cc A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:27:0x01dc A[LOOP:0: B:15:0x009e->B:27:0x01dc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:31:0x0110 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r8v14, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4, types: [T, java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x01cd -> B:26:0x01d1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object parseVideoSources(java.lang.String r24, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super kotlin.Unit> r26) {
        /*
            Method dump skipped, instruction units count: 483
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.Videa.parseVideoSources(java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String rc4DecryptBytes(byte[] encryptedBytes, String key) {
        byte[] bArrBase64DecodeArray = encryptedBytes;
        int length = bArrBase64DecodeArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                bArrBase64DecodeArray = MainAPIKt.base64DecodeArray(StringsKt.trim((CharSequence) StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.decodeToString(bArrBase64DecodeArray), "\r", "", false, 4, (Object) null), "\n", "", false, 4, (Object) null), Stream.ID_UNKNOWN, "", false, 4, (Object) null)).toString());
                break;
            }
            int i2 = bArrBase64DecodeArray[i] & 255;
            if ((32 > i2 || i2 >= 127) && i2 != 10 && i2 != 13) {
                break;
            }
            i++;
        }
        byte[] bArrEncodeToByteArray = StringsKt.encodeToByteArray(key);
        int[] iArr = new int[256];
        for (int i3 = 0; i3 < 256; i3++) {
            iArr[i3] = i3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < 256; i5++) {
            int i6 = iArr[i5];
            i4 = ((i4 + i6) + (bArrEncodeToByteArray[i5 % bArrEncodeToByteArray.length] & 255)) % 256;
            int i7 = iArr[i4];
            iArr[i4] = i6;
            Unit unit = Unit.INSTANCE;
            iArr[i5] = i7;
        }
        byte[] bArr = new byte[bArrBase64DecodeArray.length];
        int length2 = bArrBase64DecodeArray.length;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < length2; i10++) {
            i8 = (i8 + 1) % 256;
            int i11 = iArr[i8];
            i9 = (i9 + i11) % 256;
            int i12 = iArr[i9];
            iArr[i9] = i11;
            Unit unit2 = Unit.INSTANCE;
            iArr[i8] = i12;
            bArr[i10] = (byte) (iArr[(i12 + iArr[i9]) % 256] ^ (bArrBase64DecodeArray[i10] & 255));
        }
        return StringsKt.decodeToString(bArr);
    }
}
