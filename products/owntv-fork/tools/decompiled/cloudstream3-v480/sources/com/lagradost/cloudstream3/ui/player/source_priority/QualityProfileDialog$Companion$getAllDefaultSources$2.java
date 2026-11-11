package com.lagradost.cloudstream3.ui.player.source_priority;

import com.lagradost.cloudstream3.mvvm.DebugException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: QualityProfileDialog.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/ui/player/source_priority/LinkSource;", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityProfileDialog$Companion;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$Companion$getAllDefaultSources$2", f = "QualityProfileDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class QualityProfileDialog$Companion$getAllDefaultSources$2 extends SuspendLambda implements Function3<CoroutineScope, QualityProfileDialog.Companion, Continuation<? super List<? extends LinkSource>>, Object> {
    int label;

    QualityProfileDialog$Companion$getAllDefaultSources$2(Continuation<? super QualityProfileDialog$Companion$getAllDefaultSources$2> continuation) {
        super(3, continuation);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, QualityProfileDialog.Companion companion, Continuation<? super List<? extends LinkSource>> continuation) {
        return invoke2(coroutineScope, companion, (Continuation<? super List<LinkSource>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, QualityProfileDialog.Companion companion, Continuation<? super List<LinkSource>> continuation) {
        return new QualityProfileDialog$Companion$getAllDefaultSources$2(continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws DebugException {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<QualityDataHelper.QualityProfile> profiles = QualityDataHelper.INSTANCE.getProfiles();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = profiles.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, QualityDataHelper.INSTANCE.getAllSourcePriorityNames(((QualityDataHelper.QualityProfile) it.next()).getId()));
        }
        List listDistinct = CollectionsKt.distinct(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDistinct, 10));
        Iterator it2 = listDistinct.iterator();
        while (it2.hasNext()) {
            arrayList2.add(new LinkSource((String) it2.next()));
        }
        return arrayList2;
    }
}
