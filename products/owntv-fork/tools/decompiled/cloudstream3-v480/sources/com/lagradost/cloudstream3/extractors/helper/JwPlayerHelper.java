package com.lagradost.cloudstream3.extractors.helper;

import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: JWPlayerHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jd\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00110\u000f2\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u0015H\u0086@¢\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002J%\u0010\u001a\u001a\u00020\u000b*\u00020\u000b2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u001c\"\u00020\u000bH\u0002¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/JwPlayerHelper;", "", "<init>", "()V", "sourceRegex", "Lkotlin/text/Regex;", "tracksRegex", "m3u8Regex", "extractStreamLinks", "", "script", "", "sourceName", "mainUrl", "callback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "", "subtitleCallback", "Lcom/lagradost/cloudstream3/SubtitleFile;", "headers", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "canParseJwScript", "fixUrl", ImagesContract.URL, "addMarks", "strings", "", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "Source", "Track", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JwPlayerHelper {
    public static final JwPlayerHelper INSTANCE = new JwPlayerHelper();
    private static final Regex sourceRegex = new Regex("\"?sources\"?:\\s*(\\[.*?\\])");
    private static final Regex tracksRegex = new Regex("\"?tracks\"?:\\s*(\\[.*?\\])");
    private static final Regex m3u8Regex = new Regex("[:=]\\s*\\\"([^\\\"\\s]+(\\.m3u8|master\\.txt)[^\\\"\\s]*)");

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper$extractStreamLinks$1, reason: invalid class name */
    /* JADX INFO: compiled from: JWPlayerHelper.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper", f = "JWPlayerHelper.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {58, 70, 103, 123}, m = "extractStreamLinks", n = {"script", "sourceName", "mainUrl", "callback", "subtitleCallback", "headers", "sourceMatches", "$this$flatMap$iv", "$this$flatMapTo$iv$iv", "destination$iv$iv", "element$iv$iv", "link", "$i$f$flatMap", "$i$f$flatMapTo", "$i$a$-flatMap-JwPlayerHelper$extractStreamLinks$extractedLinks$1", "script", "sourceName", "mainUrl", "callback", "subtitleCallback", "headers", "sourceMatches", "$this$flatMap$iv", "$this$flatMapTo$iv$iv", "destination$iv$iv", "element$iv$iv", "link", "$i$f$flatMap", "$i$f$flatMapTo", "$i$a$-flatMap-JwPlayerHelper$extractStreamLinks$extractedLinks$1", "script", "sourceName", "mainUrl", "callback", "subtitleCallback", "headers", "sourceMatches", "extractedLinks", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "match", "link", "$i$f$map", "$i$f$mapTo", "$i$a$-map-JwPlayerHelper$extractStreamLinks$2", "script", "sourceName", "mainUrl", "callback", "subtitleCallback", "headers", "sourceMatches", "extractedLinks", "tracksMatches", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-JwPlayerHelper$extractStreamLinks$subtitleFiles$2"}, nl = {64, 69, 110, 126}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$11", "L$12", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$11", "L$12", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$12", "L$13", "L$14", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$13", "L$14", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
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
            return JwPlayerHelper.this.extractStreamLinks(null, null, null, null, null, null, this);
        }
    }

    private JwPlayerHelper() {
    }

    /* JADX WARN: Code duplicated, block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:66:0x046d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x0369 -> B:56:0x0378). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x046d -> B:67:0x0477). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:96:0x05d8 -> B:97:0x05ea). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object extractStreamLinks(java.lang.String r29, java.lang.String r30, java.lang.String r31, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r32, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r33, java.util.Map<java.lang.String, java.lang.String> r34, kotlin.coroutines.Continuation<? super java.lang.Boolean> r35) {
        /*
            Method dump skipped, instruction units count: 1612
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper.extractStreamLinks(java.lang.String, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object extractStreamLinks$default(JwPlayerHelper jwPlayerHelper, String str, String str2, String str3, Function1 function1, Function1 function2, Map map, Continuation continuation, int i, Object obj) {
        if ((i & 32) != 0) {
            map = MapsKt.emptyMap();
        }
        return jwPlayerHelper.extractStreamLinks(str, str2, str3, function1, function2, map, continuation);
    }

    static final Iterable extractStreamLinks$lambda$0(MatchResult sourceMatch) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(sourceMatch, "sourceMatch");
        String strAddMarks = INSTANCE.addMarks(sourceMatch.getGroupValues().get(1), UtilsKt.SCHEME_FILE, "label", "type");
        AppUtils appUtils = AppUtils.INSTANCE;
        Object obj = null;
        try {
            if (strAddMarks != null) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(new ArrayListSerializer(Source.INSTANCE.serializer()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                    }
                }
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                if (kSerializer != null) {
                    try {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, strAddMarks);
                    } catch (SerializationException e) {
                        ArchComponentExtKt.logError(e);
                        objDecodeFromString = MainAPIKt.getMapper().readValue(strAddMarks, new TypeReference<List<? extends Source>>() { // from class: com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper$extractStreamLinks$lambda$0$$inlined$tryParseJson$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(strAddMarks, new TypeReference<List<? extends Source>>() { // from class: com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper$extractStreamLinks$lambda$0$$inlined$tryParseJson$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(strAddMarks, new TypeReference<List<? extends Source>>() { // from class: com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper$extractStreamLinks$lambda$0$$inlined$tryParseJson$1
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        List listEmptyList = (List) obj;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return listEmptyList;
    }

    static final Iterable extractStreamLinks$lambda$3(MatchResult trackMatch) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(trackMatch, "trackMatch");
        String strAddMarks = INSTANCE.addMarks(trackMatch.getGroupValues().get(1), UtilsKt.SCHEME_FILE, "kind", "label");
        AppUtils appUtils = AppUtils.INSTANCE;
        Object obj = null;
        try {
            if (strAddMarks != null) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(new ArrayListSerializer(Track.INSTANCE.serializer()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                    }
                }
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                if (kSerializer != null) {
                    try {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, strAddMarks);
                    } catch (SerializationException e) {
                        ArchComponentExtKt.logError(e);
                        objDecodeFromString = MainAPIKt.getMapper().readValue(strAddMarks, new TypeReference<List<? extends Track>>() { // from class: com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper$extractStreamLinks$lambda$3$$inlined$tryParseJson$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(strAddMarks, new TypeReference<List<? extends Track>>() { // from class: com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper$extractStreamLinks$lambda$3$$inlined$tryParseJson$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(strAddMarks, new TypeReference<List<? extends Track>>() { // from class: com.lagradost.cloudstream3.extractors.helper.JwPlayerHelper$extractStreamLinks$lambda$3$$inlined$tryParseJson$1
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        List listEmptyList = (List) obj;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return listEmptyList;
    }

    public final boolean canParseJwScript(String script) {
        Intrinsics.checkNotNullParameter(script, "script");
        return sourceRegex.containsMatchIn(script);
    }

    private final String fixUrl(String url, String mainUrl) {
        if (StringsKt.startsWith$default(url, "/", false, 2, (Object) null)) {
            return mainUrl + url;
        }
        if (StringsKt.startsWith$default(url, "http", false, 2, (Object) null)) {
            return url;
        }
        return mainUrl + '/' + url;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JWPlayerHelper.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 '2\u00020\u0001:\u0002&'B)\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/JwPlayerHelper$Source;", "", UtilsKt.SCHEME_FILE, "", "label", "type", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFile$annotations", "()V", "getFile", "()Ljava/lang/String;", "getLabel$annotations", "getLabel", "getType$annotations", "getType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    static final /* data */ class Source {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("label")
        private final String label;

        @JsonProperty("type")
        private final String type;

        public static /* synthetic */ Source copy$default(Source source, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = source.file;
            }
            if ((i & 2) != 0) {
                str2 = source.label;
            }
            if ((i & 4) != 0) {
                str3 = source.type;
            }
            return source.copy(str, str2, str3);
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

        public final Source copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label, @JsonProperty("type") String type) {
            Intrinsics.checkNotNullParameter(file, "file");
            return new Source(file, label, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Source)) {
                return false;
            }
            Source source = (Source) other;
            return Intrinsics.areEqual(this.file, source.file) && Intrinsics.areEqual(this.label, source.label) && Intrinsics.areEqual(this.type, source.type);
        }

        public int hashCode() {
            int iHashCode = this.file.hashCode() * 31;
            String str = this.label;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.type;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Source(file=" + this.file + ", label=" + this.label + ", type=" + this.type + ')';
        }

        /* JADX INFO: compiled from: JWPlayerHelper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/JwPlayerHelper$Source$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/extractors/helper/JwPlayerHelper$Source;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Source> serializer() {
                return JwPlayerHelper$Source$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Source(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, JwPlayerHelper$Source$$serializer.INSTANCE.getDescriptor());
            }
            this.file = str;
            this.label = str2;
            this.type = str3;
        }

        public Source(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String str, @JsonProperty("type") String str2) {
            Intrinsics.checkNotNullParameter(file, "file");
            this.file = file;
            this.label = str;
            this.type = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Source self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.file);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.label);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.type);
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
    }

    /* JADX INFO: compiled from: JWPlayerHelper.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/JwPlayerHelper$Track;", "", UtilsKt.SCHEME_FILE, "", "label", "kind", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFile$annotations", "()V", "getFile", "()Ljava/lang/String;", "getLabel$annotations", "getLabel", "getKind$annotations", "getKind", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Track {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("kind")
        private final String kind;

        @JsonProperty("label")
        private final String label;

        public Track() {
            this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Track copy$default(Track track, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = track.file;
            }
            if ((i & 2) != 0) {
                str2 = track.label;
            }
            if ((i & 4) != 0) {
                str3 = track.kind;
            }
            return track.copy(str, str2, str3);
        }

        @SerialName(UtilsKt.SCHEME_FILE)
        public static /* synthetic */ void getFile$annotations() {
        }

        @SerialName("kind")
        public static /* synthetic */ void getKind$annotations() {
        }

        @SerialName("label")
        public static /* synthetic */ void getLabel$annotations() {
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
        public final String getKind() {
            return this.kind;
        }

        public final Track copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label, @JsonProperty("kind") String kind) {
            return new Track(file, label, kind);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Track)) {
                return false;
            }
            Track track = (Track) other;
            return Intrinsics.areEqual(this.file, track.file) && Intrinsics.areEqual(this.label, track.label) && Intrinsics.areEqual(this.kind, track.kind);
        }

        public int hashCode() {
            String str = this.file;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.label;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.kind;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "Track(file=" + this.file + ", label=" + this.label + ", kind=" + this.kind + ')';
        }

        /* JADX INFO: compiled from: JWPlayerHelper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/JwPlayerHelper$Track$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/extractors/helper/JwPlayerHelper$Track;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Track> serializer() {
                return JwPlayerHelper$Track$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Track(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.file = null;
            } else {
                this.file = str;
            }
            if ((i & 2) == 0) {
                this.label = null;
            } else {
                this.label = str2;
            }
            if ((i & 4) == 0) {
                this.kind = null;
            } else {
                this.kind = str3;
            }
        }

        public Track(@JsonProperty(UtilsKt.SCHEME_FILE) String str, @JsonProperty("label") String str2, @JsonProperty("kind") String str3) {
            this.file = str;
            this.label = str2;
            this.kind = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Track self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.file != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.file);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.label != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.label);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && self.kind == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.kind);
        }

        public /* synthetic */ Track(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
        }

        public final String getFile() {
            return this.file;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getKind() {
            return this.kind;
        }
    }

    private final String addMarks(String str, String... strArr) {
        for (String str2 : strArr) {
            str = new Regex("\"?" + str2 + "\"?").replace(str, "\"" + str2 + '\"');
        }
        return str;
    }
}
