package com.lagradost.cloudstream3.extractors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.net.HttpHeaders;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* JADX INFO: compiled from: PlayLtXyz.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010\u0013R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PlayLtXyz;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", ImagesContract.URL, "referer", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ResponseData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class PlayLtXyz extends ExtractorApi {
    private final String name = "PlayLt";
    private final String mainUrl = "https://play.playlt.xyz";
    private final boolean requiresReferer = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.PlayLtXyz$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayLtXyz.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.PlayLtXyz", f = "PlayLtXyz.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {24, 55, 64}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "extractedLinksList", "idUser", "idFile", "bodyText", "$this", ImagesContract.URL, "referer", "extractedLinksList", "idUser", "idFile", "bodyText", "doc", "ajaxHead", "ajaxData", "posturl", "$this", ImagesContract.URL, "referer", "extractedLinksList", "idUser", "idFile", "bodyText", "doc", "ajaxHead", "ajaxData", "posturl", "data", "itemstr", "item", "linkUrl", "$i$a$-let-PlayLtXyz$getUrl$3"}, nl = {26, 57, 63}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
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
            return PlayLtXyz.getUrl$suspendImpl(PlayLtXyz.this, (String) null, (String) null, (Continuation<? super List<? extends ExtractorLink>>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        return getUrl$suspendImpl(this, str, str2, continuation);
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: PlayLtXyz.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PlayLtXyz$ResponseData;", "", "data", "", "<init>", "(Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final /* data */ class ResponseData {

        @JsonProperty("data")
        private final String data;

        /* JADX WARN: Multi-variable type inference failed */
        public ResponseData() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ ResponseData copy$default(ResponseData responseData, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = responseData.data;
            }
            return responseData.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getData() {
            return this.data;
        }

        public final ResponseData copy(@JsonProperty("data") String data) {
            return new ResponseData(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ResponseData) && Intrinsics.areEqual(this.data, ((ResponseData) other).data);
        }

        public int hashCode() {
            String str = this.data;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "ResponseData(data=" + this.data + ')';
        }

        public ResponseData(@JsonProperty("data") String str) {
            this.data = str;
        }

        public /* synthetic */ ResponseData(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        public final String getData() {
            return this.data;
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x043f  */
    /* JADX WARN: Code duplicated, block: B:108:0x0357 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:110:0x0334 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:112:0x0388 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:117:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x030e  */
    /* JADX WARN: Code duplicated, block: B:69:0x0331  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:81:0x037d A[Catch: Exception -> 0x0331, PHI: r0
      0x037d: PHI (r0v17 java.lang.Object) = (r0v8 java.lang.Object), (r0v11 java.lang.Object), (r0v16 java.lang.Object) binds: [B:75:0x0354, B:80:0x0373, B:77:0x0357] A[DONT_GENERATE, DONT_INLINE], TryCatch #4 {Exception -> 0x0331, blocks: (B:74:0x0350, B:81:0x037d, B:84:0x0384, B:90:0x0399, B:89:0x0394, B:80:0x0373, B:73:0x0346, B:77:0x0357, B:70:0x0334, B:86:0x0388), top: B:110:0x0334, inners: #0, #1, #5 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x0383  */
    /* JADX WARN: Code duplicated, block: B:90:0x0399 A[Catch: Exception -> 0x0331, TRY_LEAVE, TryCatch #4 {Exception -> 0x0331, blocks: (B:74:0x0350, B:81:0x037d, B:84:0x0384, B:90:0x0399, B:89:0x0394, B:80:0x0373, B:73:0x0346, B:77:0x0357, B:70:0x0334, B:86:0x0388), top: B:110:0x0334, inners: #0, #1, #5 }] */
    /* JADX WARN: Code duplicated, block: B:93:0x03ae  */
    /* JADX WARN: Code duplicated, block: B:95:0x03b4  */
    /* JADX WARN: Code duplicated, block: B:98:0x03bf  */
    /* JADX WARN: Instruction removed from duplicated block: B:67:0x030e, please report this as an issue */
    static /* synthetic */ Object getUrl$suspendImpl(PlayLtXyz playLtXyz, String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        AnonymousClass1 anonymousClass1;
        char c;
        String str3;
        Object obj;
        String str4;
        List list;
        Object obj2;
        String str5;
        String str6;
        String str7;
        int i;
        int i2;
        Object next;
        Map map;
        Map map2;
        String str8;
        Object objPost$default;
        PlayLtXyz playLtXyz2;
        String str9;
        List list2;
        Document document;
        String str10;
        String str11;
        String str12;
        List<String> groupValues;
        List<String> groupValues2;
        String string;
        String string2;
        NiceResponse niceResponse;
        String text;
        String str13;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object objDecodeFromString;
        ResponseData responseData;
        String data;
        List list3;
        List list4;
        PlayLtXyz playLtXyz3 = playLtXyz;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = playLtXyz3.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = playLtXyz3.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objNewExtractorLink = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass2.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objNewExtractorLink);
            ArrayList arrayList = new ArrayList();
            Requests app2 = MainActivityKt.getApp();
            anonymousClass2.L$0 = playLtXyz3;
            anonymousClass2.L$1 = str;
            anonymousClass2.L$2 = str2;
            anonymousClass2.L$3 = arrayList;
            anonymousClass2.L$4 = "";
            anonymousClass2.L$5 = "";
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable("");
            anonymousClass2.label = 1;
            c = 1;
            str3 = "";
            obj = null;
            Object obj3 = Requests.get$default(app2, str, null, str2, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4090, null);
            if (obj3 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                str4 = str2;
                list = arrayList;
                obj2 = obj3;
                str5 = str3;
                str6 = str5;
                str7 = str;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i3 == 1) {
            String str14 = (String) anonymousClass2.L$5;
            String str15 = (String) anonymousClass2.L$4;
            List list5 = (List) anonymousClass2.L$3;
            String str16 = (String) anonymousClass2.L$2;
            String str17 = (String) anonymousClass2.L$1;
            PlayLtXyz playLtXyz4 = (PlayLtXyz) anonymousClass2.L$0;
            ResultKt.throwOnFailure(objNewExtractorLink);
            c = 1;
            str3 = "";
            str4 = str16;
            str6 = str14;
            str5 = str15;
            list = list5;
            str7 = str17;
            playLtXyz3 = playLtXyz4;
            obj2 = objNewExtractorLink;
            obj = null;
        } else {
            if (i3 == 2) {
                String str18 = (String) anonymousClass2.L$10;
                Map map3 = (Map) anonymousClass2.L$9;
                map = (Map) anonymousClass2.L$8;
                document = (Document) anonymousClass2.L$7;
                str9 = (String) anonymousClass2.L$6;
                str11 = (String) anonymousClass2.L$5;
                str12 = (String) anonymousClass2.L$4;
                list2 = (List) anonymousClass2.L$3;
                String str19 = (String) anonymousClass2.L$2;
                str10 = (String) anonymousClass2.L$1;
                PlayLtXyz playLtXyz5 = (PlayLtXyz) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink);
                str4 = str19;
                str3 = "";
                playLtXyz2 = playLtXyz5;
                str8 = str18;
                map2 = map3;
                objPost$default = objNewExtractorLink;
                niceResponse = (NiceResponse) objPost$default;
                if (niceResponse.getIsSuccessful()) {
                    return list2;
                }
                text = niceResponse.getText();
                str13 = str8;
                Log.INSTANCE.i(playLtXyz2.getName(), "Result => (data) " + text);
                AppUtils appUtils = AppUtils.INSTANCE;
                if (text == null) {
                    objDecodeFromString = null;
                } else {
                    try {
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(BuiltinSerializersKt.getNullable(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.extractors.PlayLtXyz.ResponseData")));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                try {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                                } catch (SerializationException e) {
                                    ArchComponentExtKt.logError(e);
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<ResponseData>() { // from class: com.lagradost.cloudstream3.extractors.PlayLtXyz$getUrl$suspendImpl$$inlined$tryParseJson$1
                                    });
                                } catch (Throwable unused) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<ResponseData>() { // from class: com.lagradost.cloudstream3.extractors.PlayLtXyz$getUrl$suspendImpl$$inlined$tryParseJson$1
                                    });
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<ResponseData>() { // from class: com.lagradost.cloudstream3.extractors.PlayLtXyz$getUrl$suspendImpl$$inlined$tryParseJson$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(ResponseData.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<ResponseData>() { // from class: com.lagradost.cloudstream3.extractors.PlayLtXyz$getUrl$suspendImpl$$inlined$tryParseJson$1
                                });
                            }
                        }
                    } catch (Exception unused2) {
                        objDecodeFromString = null;
                    }
                }
                responseData = (ResponseData) objDecodeFromString;
                if (responseData != null) {
                    return list2;
                }
                data = responseData.getData();
                if (data == null) {
                    data = str3;
                }
                if (!StringsKt.isBlank(data)) {
                    return list2;
                }
                String name = playLtXyz2.getName();
                String name2 = playLtXyz2.getName();
                PlayLtXyz playLtXyz6 = playLtXyz2;
                ExtractorLinkType extractorLinkType = ExtractorLinkType.M3U8;
                String str20 = data;
                PlayLtXyz$getUrl$3$1 playLtXyz$getUrl$3$1 = new PlayLtXyz$getUrl$3$1(str10, null);
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(playLtXyz6);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str10);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
                anonymousClass2.L$3 = list2;
                anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str12);
                anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str11);
                anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str9);
                anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(document);
                anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(map);
                anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(map2);
                anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str13);
                anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(niceResponse);
                anonymousClass2.L$12 = SpillingKt.nullOutSpilledVariable(text);
                anonymousClass2.L$13 = SpillingKt.nullOutSpilledVariable(responseData);
                anonymousClass2.L$14 = SpillingKt.nullOutSpilledVariable(str20);
                anonymousClass2.L$15 = list2;
                anonymousClass2.I$0 = 0;
                anonymousClass2.label = 3;
                objNewExtractorLink = ExtractorApiKt.newExtractorLink(name, name2, str20, extractorLinkType, playLtXyz$getUrl$3$1, anonymousClass2);
                if (objNewExtractorLink != coroutine_suspended) {
                    list3 = list2;
                    list4 = list3;
                }
                anonymousClass2 = anonymousClass2;
                return coroutine_suspended;
            }
            if (i3 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = anonymousClass2.I$0;
            list3 = (List) anonymousClass2.L$15;
            list4 = (List) anonymousClass2.L$3;
            ResultKt.throwOnFailure(objNewExtractorLink);
        }
        list3.add(objNewExtractorLink);
        return list4;
        Document document2 = ((NiceResponse) obj2).getDocument();
        Elements elementsSelect = document2.select("script");
        Intrinsics.checkNotNullExpressionValue(elementsSelect, "select(...)");
        Iterator<T> it = elementsSelect.iterator();
        do {
            if (!it.hasNext()) {
                i = 0;
                i2 = 2;
                next = obj;
                break;
            }
            next = it.next();
            string2 = ((Element) next).toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            i = 0;
            i2 = 2;
        } while (!StringsKt.contains$default((CharSequence) string2, (CharSequence) "var idUser", false, 2, obj));
        Element element = (Element) next;
        String str21 = (element == null || (string = element.toString()) == null) ? str3 : string;
        String str22 = str21;
        if (!StringsKt.isBlank(str22)) {
            MatchResult matchResultFind$default = Regex.find$default(new Regex("(?<=var idUser = \")(.*)(?=\";)"), str22, i, i2, obj);
            if (matchResultFind$default == null || (groupValues2 = matchResultFind$default.getGroupValues()) == null || (str5 = groupValues2.get(i)) == null) {
                str5 = str3;
            }
            MatchResult matchResultFind$default2 = Regex.find$default(new Regex("(?<=var idfile = \")(.*)(?=\";)"), str22, i, i2, obj);
            if (matchResultFind$default2 == null || (groupValues = matchResultFind$default2.getGroupValues()) == null || (str6 = groupValues.get(i)) == null) {
                str6 = str3;
            }
        }
        String str23 = str6;
        String str24 = str5;
        if (StringsKt.isBlank(str24) || StringsKt.isBlank(str23)) {
            return list;
        }
        Pair[] pairArr = new Pair[5];
        pairArr[i] = new Pair("Origin", playLtXyz3.getMainUrl());
        pairArr[c] = new Pair("Referer", playLtXyz3.getMainUrl());
        pairArr[i2] = new Pair(HttpHeaders.SEC_FETCH_SITE, "same-site");
        pairArr[3] = new Pair(HttpHeaders.SEC_FETCH_MODE, "cors");
        pairArr[4] = new Pair(HttpHeaders.SEC_FETCH_DEST, "empty");
        Map mapMapOf = MapsKt.mapOf(pairArr);
        Pair[] pairArr2 = new Pair[i2];
        pairArr2[i] = new Pair("referrer", str4 == null ? playLtXyz3.getMainUrl() : str4);
        pairArr2[c] = new Pair("typeend", "html");
        Map mapMapOf2 = MapsKt.mapOf(pairArr2);
        String str25 = "https://api-plhq.playlt.xyz/apiv5/" + str24 + '/' + str23;
        Requests app3 = MainActivityKt.getApp();
        anonymousClass2.L$0 = playLtXyz3;
        anonymousClass2.L$1 = str7;
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass2.L$3 = list;
        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str24);
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str23);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str21);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(document2);
        anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(mapMapOf);
        anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(mapMapOf2);
        anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str25);
        anonymousClass2.label = i2;
        AnonymousClass1 anonymousClass3 = anonymousClass2;
        List list6 = list;
        map = mapMapOf;
        map2 = mapMapOf2;
        str8 = str25;
        String str26 = str7;
        objPost$default = Requests.post$default(app3, str8, map, null, null, null, map2, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass3, 65500, null);
        anonymousClass2 = anonymousClass3;
        if (objPost$default != coroutine_suspended) {
            playLtXyz2 = playLtXyz3;
            str9 = str21;
            list2 = list6;
            document = document2;
            str10 = str26;
            str11 = str23;
            str12 = str24;
            niceResponse = (NiceResponse) objPost$default;
            if (niceResponse.getIsSuccessful()) {
                return list2;
            }
            text = niceResponse.getText();
            str13 = str8;
            Log.INSTANCE.i(playLtXyz2.getName(), "Result => (data) " + text);
            AppUtils appUtils2 = AppUtils.INSTANCE;
            if (text == null) {
                objDecodeFromString = null;
            } else {
                Result.Companion companion5 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(BuiltinSerializersKt.getNullable(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.extractors.PlayLtXyz.ResponseData")));
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                        objM8936constructorimpl = null;
                    }
                    kSerializer = (KSerializer) objM8936constructorimpl;
                    if (kSerializer != null) {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<ResponseData>() { // from class: com.lagradost.cloudstream3.extractors.PlayLtXyz$getUrl$suspendImpl$$inlined$tryParseJson$1
                        });
                    }
                } else {
                    Result.Companion companion6 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(ResponseData.class), null, 2, null));
                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                        objM8936constructorimpl = null;
                    }
                    kSerializer = (KSerializer) objM8936constructorimpl;
                    if (kSerializer != null) {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<ResponseData>() { // from class: com.lagradost.cloudstream3.extractors.PlayLtXyz$getUrl$suspendImpl$$inlined$tryParseJson$1
                        });
                    }
                }
            }
            responseData = (ResponseData) objDecodeFromString;
            if (responseData != null) {
                return list2;
            }
            data = responseData.getData();
            if (data == null) {
                data = str3;
            }
            if (!StringsKt.isBlank(data)) {
                return list2;
            }
            String name3 = playLtXyz2.getName();
            String name4 = playLtXyz2.getName();
            PlayLtXyz playLtXyz7 = playLtXyz2;
            ExtractorLinkType extractorLinkType2 = ExtractorLinkType.M3U8;
            String str27 = data;
            PlayLtXyz$getUrl$3$1 playLtXyz$getUrl$3$2 = new PlayLtXyz$getUrl$3$1(str10, null);
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(playLtXyz7);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str10);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str4);
            anonymousClass2.L$3 = list2;
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(str12);
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str11);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(str9);
            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(document);
            anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(map);
            anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(map2);
            anonymousClass2.L$10 = SpillingKt.nullOutSpilledVariable(str13);
            anonymousClass2.L$11 = SpillingKt.nullOutSpilledVariable(niceResponse);
            anonymousClass2.L$12 = SpillingKt.nullOutSpilledVariable(text);
            anonymousClass2.L$13 = SpillingKt.nullOutSpilledVariable(responseData);
            anonymousClass2.L$14 = SpillingKt.nullOutSpilledVariable(str27);
            anonymousClass2.L$15 = list2;
            anonymousClass2.I$0 = 0;
            anonymousClass2.label = 3;
            objNewExtractorLink = ExtractorApiKt.newExtractorLink(name3, name4, str27, extractorLinkType2, playLtXyz$getUrl$3$2, anonymousClass2);
            if (objNewExtractorLink != coroutine_suspended) {
                list3 = list2;
                list4 = list3;
                list3.add(objNewExtractorLink);
                return list4;
            }
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }
}
