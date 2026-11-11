package com.lagradost.cloudstream3.extractors;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Acefile.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Acefile;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Source", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Acefile extends ExtractorApi {
    private final boolean requiresReferer;
    private final String name = "Acefile";
    private final String mainUrl = "https://acefile.co";

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Acefile$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Acefile.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Acefile", f = "Acefile.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {19, 24, 27}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "script", NotificationCompat.CATEGORY_SERVICE, "serverUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "id", "script", NotificationCompat.CATEGORY_SERVICE, "serverUrl", "video"}, nl = {20, 41, 26}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
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
            return Acefile.getUrl$suspendImpl(Acefile.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:64:0x027f  */
    /* JADX WARN: Code duplicated, block: B:65:0x0284  */
    /* JADX WARN: Code duplicated, block: B:68:0x0290  */
    /* JADX WARN: Code duplicated, block: B:70:0x0293  */
    /* JADX WARN: Code duplicated, block: B:73:0x02e1  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object getUrl$suspendImpl(Acefile acefile, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        String str3;
        int i;
        String str4;
        String str5;
        Function1<? super SubtitleFile, Unit> function3;
        Function1<? super ExtractorLink, Unit> function4;
        String str6;
        Object obj;
        Acefile acefile2;
        List<String> groupValues;
        String andUnpack;
        int i2;
        String str7;
        String str8;
        String strReplace$default;
        String str9;
        Object obj2;
        String str10;
        String str11;
        Function1<? super SubtitleFile, Unit> function5;
        String str12;
        Function1<? super ExtractorLink, Unit> function6;
        Acefile acefile3;
        List<String> groupValues2;
        String str13;
        List<String> groupValues3;
        Object safe;
        Source source;
        String data;
        String name;
        String str14;
        String name2;
        Function1<? super ExtractorLink, Unit> function7;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = acefile.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = acefile.new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objNewExtractorLink$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass2.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objNewExtractorLink$default);
            MatchResult matchResultFind$default = Regex.find$default(new Regex("/(?:d|download|player|f|file)/(\\w+)"), str, 0, 2, null);
            String str15 = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) ? null : groupValues.get(1);
            Requests app2 = MainActivityKt.getApp();
            StringBuilder sb = new StringBuilder();
            sb.append(acefile.getMainUrl());
            sb.append("/player/");
            if (str15 == null) {
                return Unit.INSTANCE;
            }
            sb.append(str15);
            String string = sb.toString();
            anonymousClass2.L$0 = acefile;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass2.L$4 = function2;
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str15);
            anonymousClass2.label = 1;
            String str16 = str15;
            str3 = null;
            i = 0;
            Object obj3 = Requests.get$default(app2, string, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
            if (obj3 != coroutine_suspended) {
                anonymousClass2 = anonymousClass2;
                str4 = str;
                str5 = str2;
                function3 = function1;
                function4 = function2;
                str6 = str16;
                obj = obj3;
                acefile2 = acefile;
            }
            anonymousClass2 = anonymousClass2;
            return coroutine_suspended;
        }
        if (i3 == 1) {
            String str17 = (String) anonymousClass2.L$5;
            Function1<? super ExtractorLink, Unit> function8 = (Function1) anonymousClass2.L$4;
            Function1<? super SubtitleFile, Unit> function9 = (Function1) anonymousClass2.L$3;
            String str18 = (String) anonymousClass2.L$2;
            String str19 = (String) anonymousClass2.L$1;
            Acefile acefile4 = (Acefile) anonymousClass2.L$0;
            ResultKt.throwOnFailure(objNewExtractorLink$default);
            str6 = str17;
            function4 = function8;
            i = 0;
            function3 = function9;
            str5 = str18;
            str4 = str19;
            acefile2 = acefile4;
            obj = objNewExtractorLink$default;
            str3 = null;
        } else {
            if (i3 == 2) {
                String str20 = (String) anonymousClass2.L$8;
                String str21 = (String) anonymousClass2.L$7;
                String str22 = (String) anonymousClass2.L$6;
                str12 = (String) anonymousClass2.L$5;
                Function1<? super ExtractorLink, Unit> function10 = (Function1) anonymousClass2.L$4;
                function5 = (Function1) anonymousClass2.L$3;
                String str23 = (String) anonymousClass2.L$2;
                str11 = (String) anonymousClass2.L$1;
                acefile3 = (Acefile) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objNewExtractorLink$default);
                str9 = str20;
                andUnpack = str22;
                str5 = str23;
                str10 = str21;
                function6 = function10;
                obj2 = objNewExtractorLink$default;
                str3 = null;
                NiceResponse niceResponse = (NiceResponse) obj2;
                try {
                    ResponseParser parser = niceResponse.getParser();
                    Intrinsics.checkNotNull(parser);
                    safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Source.class));
                } catch (Exception e) {
                    e.printStackTrace();
                    safe = str3;
                }
                source = (Source) safe;
                if (source != null) {
                    data = source.getData();
                } else {
                    data = str3;
                }
                name = acefile3.getName();
                str14 = str9;
                name2 = acefile3.getName();
                if (data == null) {
                    anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(acefile3);
                    anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str11);
                    anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                    anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function5);
                    anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function6);
                    anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str12);
                    anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(andUnpack);
                    anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str10);
                    anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(str14);
                    anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(data);
                    anonymousClass2.L$10 = function6;
                    anonymousClass2.label = 3;
                    objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, data, null, null, anonymousClass2, 24, null);
                    if (objNewExtractorLink$default != coroutine_suspended) {
                        function7 = function6;
                    }
                    anonymousClass2 = anonymousClass2;
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
            if (i3 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function7 = (Function1) anonymousClass2.L$10;
            ResultKt.throwOnFailure(objNewExtractorLink$default);
        }
        function7.invoke(objNewExtractorLink$default);
        return Unit.INSTANCE;
        andUnpack = ExtractorApiKt.getAndUnpack(((NiceResponse) obj).getText());
        String str24 = andUnpack;
        MatchResult matchResultFind$default2 = Regex.find$default(new Regex("service\\s*=\\s*['\"]([^'\"]+)"), str24, i, 2, str3);
        if (matchResultFind$default2 == null || (groupValues3 = matchResultFind$default2.getGroupValues()) == null) {
            i2 = 1;
            str7 = str3;
        } else {
            i2 = 1;
            str7 = groupValues3.get(1);
        }
        MatchResult matchResultFind$default3 = Regex.find$default(new Regex("['\"](\\S+check&id\\S+?)['\"]"), str24, i, 2, str3);
        if (matchResultFind$default3 == null || (groupValues2 = matchResultFind$default3.getGroupValues()) == null || (str13 = groupValues2.get(i2)) == null) {
            str8 = str7;
            strReplace$default = str3;
        } else {
            if (str7 == null) {
                return Unit.INSTANCE;
            }
            String str25 = str7;
            strReplace$default = StringsKt.replace$default(str13, "\"+service+\"", str25, false, 4, (Object) null);
            str8 = str25;
        }
        Requests app3 = MainActivityKt.getApp();
        if (strReplace$default == null) {
            return Unit.INSTANCE;
        }
        String str26 = acefile2.getMainUrl() + '/';
        anonymousClass2.L$0 = acefile2;
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str4);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$4 = function4;
        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str6);
        anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(andUnpack);
        anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str8);
        anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(strReplace$default);
        anonymousClass2.label = 2;
        Acefile acefile5 = acefile2;
        AnonymousClass1 anonymousClass3 = anonymousClass2;
        Function1<? super ExtractorLink, Unit> function11 = function4;
        str9 = strReplace$default;
        obj2 = Requests.get$default(app3, str9, null, str26, null, null, false, 0, null, 0L, null, false, null, anonymousClass3, 4090, null);
        anonymousClass2 = anonymousClass3;
        if (obj2 != coroutine_suspended) {
            str10 = str8;
            str11 = str4;
            function5 = function3;
            str12 = str6;
            function6 = function11;
            acefile3 = acefile5;
            NiceResponse niceResponse2 = (NiceResponse) obj2;
            ResponseParser parser2 = niceResponse2.getParser();
            Intrinsics.checkNotNull(parser2);
            safe = parser2.parseSafe(niceResponse2.getText(), Reflection.getOrCreateKotlinClass(Source.class));
            source = (Source) safe;
            if (source != null) {
                data = source.getData();
            } else {
                data = str3;
            }
            name = acefile3.getName();
            str14 = str9;
            name2 = acefile3.getName();
            if (data == null) {
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(acefile3);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str11);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(str5);
                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function5);
                anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(function6);
                anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(str12);
                anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(andUnpack);
                anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(str10);
                anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(str14);
                anonymousClass2.L$9 = SpillingKt.nullOutSpilledVariable(data);
                anonymousClass2.L$10 = function6;
                anonymousClass2.label = 3;
                objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default(name, name2, data, null, null, anonymousClass2, 24, null);
                if (objNewExtractorLink$default != coroutine_suspended) {
                    function7 = function6;
                    function7.invoke(objNewExtractorLink$default);
                    return Unit.INSTANCE;
                }
            } else {
                return Unit.INSTANCE;
            }
        }
        anonymousClass2 = anonymousClass2;
        return coroutine_suspended;
    }

    /* JADX INFO: compiled from: Acefile.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Acefile$Source;", "", "data", "", "<init>", "(Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Source {
        private final String data;

        /* JADX WARN: Multi-variable type inference failed */
        public Source() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Source copy$default(Source source, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = source.data;
            }
            return source.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getData() {
            return this.data;
        }

        public final Source copy(String data) {
            return new Source(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Source) && Intrinsics.areEqual(this.data, ((Source) other).data);
        }

        public int hashCode() {
            String str = this.data;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "Source(data=" + this.data + ')';
        }

        public Source(String str) {
            this.data = str;
        }

        public /* synthetic */ Source(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        public final String getData() {
            return this.data;
        }
    }
}
