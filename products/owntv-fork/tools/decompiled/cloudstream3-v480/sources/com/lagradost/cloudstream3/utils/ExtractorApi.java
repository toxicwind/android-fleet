package com.lagradost.cloudstream3.utils;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExtractorApi.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JJ\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00130\u00172\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00130\u0017H\u0096@¢\u0006\u0002\u0010\u001bJJ\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00130\u00172\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00130\u0017H\u0086@¢\u0006\u0002\u0010\u001bJ*\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u001d2\u0006\u0010\u0014\u001a\u00020\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0007\"\u0004\b\u0010\u0010\u0011¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "sourcePlugin", "getSourcePlugin", "setSourcePlugin", "(Ljava/lang/String;)V", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSafeUrl", "", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExtractorUrl", "id", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class ExtractorApi {
    private String sourcePlugin;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApi$getSafeUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApi", f = "ExtractorApi.kt", i = {0, 0, 0, 0}, l = {1427}, m = "getSafeUrl", n = {ImagesContract.URL, "referer", "subtitleCallback", "callback"}, nl = {1428}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ExtractorApi.this.getSafeUrl(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorApi$getUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorApi", f = "ExtractorApi.kt", i = {0, 0, 0, 0, 0}, l = {1417}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback"}, nl = {1446}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
    static final class C09231 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C09231(Continuation<? super C09231> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ExtractorApi.getUrl$suspendImpl(ExtractorApi.this, null, null, null, null, this);
        }
    }

    public String getExtractorUrl(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return id;
    }

    public abstract String getMainUrl();

    public abstract String getName();

    public abstract boolean getRequiresReferer();

    public Object getUrl(String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        return getUrl$suspendImpl(this, str, str2, continuation);
    }

    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function2, continuation);
    }

    public final String getSourcePlugin() {
        return this.sourcePlugin;
    }

    public final void setSourcePlugin(String str) {
        this.sourcePlugin = str;
    }

    public static /* synthetic */ Object getUrl$default(ExtractorApi extractorApi, String str, String str2, Function1 function1, Function1 function2, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUrl");
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        return extractorApi.getUrl(str, str2, function1, function2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    static /* synthetic */ Object getUrl$suspendImpl(ExtractorApi extractorApi, String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        C09231 c09231;
        if (continuation instanceof C09231) {
            c09231 = (C09231) continuation;
            if ((c09231.label & Integer.MIN_VALUE) != 0) {
                c09231.label -= Integer.MIN_VALUE;
            } else {
                c09231 = extractorApi.new C09231(continuation);
            }
        } else {
            c09231 = extractorApi.new C09231(continuation);
        }
        Object url = c09231.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09231.label;
        if (i == 0) {
            ResultKt.throwOnFailure(url);
            c09231.L$0 = SpillingKt.nullOutSpilledVariable(extractorApi);
            c09231.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c09231.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            c09231.L$3 = SpillingKt.nullOutSpilledVariable(function1);
            c09231.L$4 = function2;
            c09231.label = 1;
            url = extractorApi.getUrl(str, str2, c09231);
            if (url == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function2 = (Function1) c09231.L$4;
            ResultKt.throwOnFailure(url);
        }
        List list = (List) url;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                function2.invoke(it.next());
            }
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object getSafeUrl$default(ExtractorApi extractorApi, String str, String str2, Function1 function1, Function1 function2, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSafeUrl");
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        return extractorApi.getSafeUrl(str, str2, function1, function2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getSafeUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function1);
                anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(function2);
                anonymousClass2.label = 1;
                if (getUrl(str, str2, function1, function2, anonymousClass2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object getUrl$default(ExtractorApi extractorApi, String str, String str2, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUrl");
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        return extractorApi.getUrl(str, str2, continuation);
    }

    static /* synthetic */ Object getUrl$suspendImpl(ExtractorApi extractorApi, String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        return CollectionsKt.emptyList();
    }
}
