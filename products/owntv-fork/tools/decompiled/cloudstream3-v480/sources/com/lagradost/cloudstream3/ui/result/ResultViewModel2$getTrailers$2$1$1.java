package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.TrailerData;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001aH\u0012,\u0012*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00010\u0002j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001`\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00060\u0002j\b\u0012\u0004\u0012\u00020\u0006`\u0005\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\bH\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Ljava/util/ArrayList;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "", "Lkotlin/collections/ArrayList;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "trailerData", "Lcom/lagradost/cloudstream3/TrailerData;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$getTrailers$2$1$1", f = "ResultViewModel2.kt", i = {0, 0, 0, 1, 1, 1}, l = {2458, 2473}, m = "invokeSuspend", n = {"trailerData", CloudStreamPackage.LINKS_EXTRA, CloudStreamPackage.SUBTITLE_EXTRA, "trailerData", CloudStreamPackage.LINKS_EXTRA, CloudStreamPackage.SUBTITLE_EXTRA}, nl = {2469, 2482}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"}, v = 2)
final class ResultViewModel2$getTrailers$2$1$1 extends SuspendLambda implements Function2<TrailerData, Continuation<? super Pair<? extends ArrayList<Pair<? extends ExtractorLink, ? extends String>>, ? extends ArrayList<SubtitleFile>>>, Object> {
    int I$0;
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    ResultViewModel2$getTrailers$2$1$1(Continuation<? super ResultViewModel2$getTrailers$2$1$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ResultViewModel2$getTrailers$2$1$1 resultViewModel2$getTrailers$2$1$1 = new ResultViewModel2$getTrailers$2$1$1(continuation);
        resultViewModel2$getTrailers$2$1$1.L$0 = obj;
        return resultViewModel2$getTrailers$2$1$1;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(TrailerData trailerData, Continuation<? super Pair<? extends ArrayList<Pair<ExtractorLink, String>>, ? extends ArrayList<SubtitleFile>>> continuation) {
        return ((ResultViewModel2$getTrailers$2$1$1) create(trailerData, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(TrailerData trailerData, Continuation<? super Pair<? extends ArrayList<Pair<? extends ExtractorLink, ? extends String>>, ? extends ArrayList<SubtitleFile>>> continuation) {
        return invoke2(trailerData, (Continuation<? super Pair<? extends ArrayList<Pair<ExtractorLink, String>>, ? extends ArrayList<SubtitleFile>>>) continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final ArrayList arrayList;
        final ArrayList arrayList2;
        Pair[] pairArr;
        int i;
        Pair[] pairArr2;
        final TrailerData trailerData = (TrailerData) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                arrayList = new ArrayList();
                arrayList2 = new ArrayList();
                this.L$0 = trailerData;
                this.L$1 = arrayList;
                this.L$2 = arrayList2;
                this.label = 1;
                obj = ExtractorApiKt.loadExtractor(trailerData.getExtractorUrl(), trailerData.getReferer(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getTrailers$2$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ResultViewModel2$getTrailers$2$1$1.invokeSuspend$lambda$0(arrayList2, (SubtitleFile) obj2);
                    }
                }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getTrailers$2$1$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ResultViewModel2$getTrailers$2$1$1.invokeSuspend$lambda$1(arrayList, trailerData, (ExtractorLink) obj2);
                    }
                }, this);
                if (obj == coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i2 == 1) {
                arrayList2 = (ArrayList) this.L$2;
                arrayList = (ArrayList) this.L$1;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                pairArr2 = (Pair[]) this.L$4;
                pairArr = (Pair[]) this.L$3;
                ResultKt.throwOnFailure(obj);
            }
            pairArr2[i] = new Pair(obj, trailerData.getExtractorUrl());
            return TuplesKt.to(CollectionsKt.arrayListOf(pairArr), new ArrayList());
            if (!((Boolean) obj).booleanValue() && trailerData.getRaw()) {
                Pair[] pairArr3 = new Pair[1];
                this.L$0 = trailerData;
                this.L$1 = SpillingKt.nullOutSpilledVariable(arrayList);
                this.L$2 = SpillingKt.nullOutSpilledVariable(arrayList2);
                this.L$3 = pairArr3;
                this.L$4 = pairArr3;
                this.I$0 = 0;
                this.label = 2;
                Object objNewExtractorLink = ExtractorApiKt.newExtractorLink("", "Trailer", trailerData.getExtractorUrl(), ExtractorApiKt.getINFER_TYPE(), new AnonymousClass3(trailerData, null), this);
                if (objNewExtractorLink != coroutine_suspended) {
                    pairArr = pairArr3;
                    i = 0;
                    obj = objNewExtractorLink;
                    pairArr2 = pairArr;
                    pairArr2[i] = new Pair(obj, trailerData.getExtractorUrl());
                    return TuplesKt.to(CollectionsKt.arrayListOf(pairArr), new ArrayList());
                }
                return coroutine_suspended;
            }
            return TuplesKt.to(arrayList, arrayList2);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return null;
        }
    }

    static final Unit invokeSuspend$lambda$0(ArrayList arrayList, SubtitleFile subtitleFile) {
        arrayList.add(subtitleFile);
        return Unit.INSTANCE;
    }

    static final Unit invokeSuspend$lambda$1(ArrayList arrayList, TrailerData trailerData, ExtractorLink extractorLink) {
        arrayList.add(new Pair(extractorLink, trailerData.getExtractorUrl()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getTrailers$2$1$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$getTrailers$2$1$1$3", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ TrailerData $trailerData;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(TrailerData trailerData, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$trailerData = trailerData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$trailerData, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ExtractorLink extractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String referer = this.$trailerData.getReferer();
            if (referer == null) {
                referer = "";
            }
            extractorLink.setReferer(referer);
            extractorLink.setQuality(Qualities.Unknown.getValue());
            extractorLink.setHeaders(this.$trailerData.getHeaders());
            return Unit.INSTANCE;
        }
    }
}
