package com.lagradost.cloudstream3.ui.player;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJl\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\"\u0010\u0011\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0013\u0012\u0004\u0012\u00020\u00160\u00122\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00160\u00122\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u001bR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/MinimalLinkGenerator;", "Lcom/lagradost/cloudstream3/ui/player/NoVideoGenerator;", CloudStreamPackage.LINKS_EXTRA, "", "Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage$MinimalVideoLink;", CloudStreamPackage.SUBTITLE_EXTRA, "Lcom/lagradost/cloudstream3/actions/temp/CloudStreamPackage$MinimalSubtitleLink;", "id", "", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;)V", "generateLinks", "", "clearCache", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "callback", "Lkotlin/Function1;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "", "subtitleCallback", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", TypedValues.CycleType.S_WAVE_OFFSET, "isCasting", "(ZLjava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MinimalLinkGenerator extends NoVideoGenerator {
    private final List<CloudStreamPackage.MinimalVideoLink> links;
    private final List<CloudStreamPackage.MinimalSubtitleLink> subs;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.MinimalLinkGenerator$generateLinks$1, reason: invalid class name */
    /* JADX INFO: compiled from: LinkGenerator.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.MinimalLinkGenerator", f = "LinkGenerator.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {93}, m = "generateLinks", n = {"sourceTypes", "callback", "subtitleCallback", "link", "clearCache", TypedValues.CycleType.S_WAVE_OFFSET, "isCasting"}, nl = {95}, s = {"L$0", "L$1", "L$2", "L$4", "Z$0", "I$0", "Z$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MinimalLinkGenerator.this.generateLinks(false, null, null, null, 0, false, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MinimalLinkGenerator(List<CloudStreamPackage.MinimalVideoLink> links, List<CloudStreamPackage.MinimalSubtitleLink> subs, Integer num) {
        super(num);
        Intrinsics.checkNotNullParameter(links, "links");
        Intrinsics.checkNotNullParameter(subs, "subs");
        this.links = links;
        this.subs = subs;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x006b  */
    /* JADX WARN: Code duplicated, block: B:19:0x0093 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x0094  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0094 -> B:21:0x0099). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public java.lang.Object generateLinks(boolean r7, java.util.Set<? extends com.lagradost.cloudstream3.utils.ExtractorLinkType> r8, kotlin.jvm.functions.Function1<? super kotlin.Pair<? extends com.lagradost.cloudstream3.utils.ExtractorLink, com.lagradost.cloudstream3.ui.player.ExtractorUri>, kotlin.Unit> r9, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.ui.player.SubtitleData, kotlin.Unit> r10, int r11, boolean r12, kotlin.coroutines.Continuation<? super java.lang.Boolean> r13) {
        /*
            r6 = this;
            boolean r0 = r13 instanceof com.lagradost.cloudstream3.ui.player.MinimalLinkGenerator.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r13
            com.lagradost.cloudstream3.ui.player.MinimalLinkGenerator$generateLinks$1 r0 = (com.lagradost.cloudstream3.ui.player.MinimalLinkGenerator.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            com.lagradost.cloudstream3.ui.player.MinimalLinkGenerator$generateLinks$1 r0 = new com.lagradost.cloudstream3.ui.player.MinimalLinkGenerator$generateLinks$1
            r0.<init>(r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L58
            if (r2 != r3) goto L50
            boolean r7 = r0.Z$1
            int r8 = r0.I$0
            boolean r9 = r0.Z$0
            java.lang.Object r10 = r0.L$5
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            java.lang.Object r11 = r0.L$4
            com.lagradost.cloudstream3.actions.temp.CloudStreamPackage$MinimalVideoLink r11 = (com.lagradost.cloudstream3.actions.temp.CloudStreamPackage.MinimalVideoLink) r11
            java.lang.Object r11 = r0.L$3
            java.util.Iterator r11 = (java.util.Iterator) r11
            java.lang.Object r12 = r0.L$2
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r2 = r0.L$1
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r4 = r0.L$0
            java.util.Set r4 = (java.util.Set) r4
            kotlin.ResultKt.throwOnFailure(r13)
            r5 = r0
            r0 = r7
            r7 = r9
            r9 = r2
            r2 = r13
            r13 = r8
            r8 = r4
            r4 = r5
            goto L99
        L50:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L58:
            kotlin.ResultKt.throwOnFailure(r13)
            java.util.List<com.lagradost.cloudstream3.actions.temp.CloudStreamPackage$MinimalVideoLink> r13 = r6.links
            java.util.Iterator r13 = r13.iterator()
            r5 = r12
            r12 = r11
            r11 = r13
            r13 = r5
        L65:
            boolean r2 = r11.hasNext()
            if (r2 == 0) goto La1
            java.lang.Object r2 = r11.next()
            com.lagradost.cloudstream3.actions.temp.CloudStreamPackage$MinimalVideoLink r2 = (com.lagradost.cloudstream3.actions.temp.CloudStreamPackage.MinimalVideoLink) r2
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$0 = r4
            r0.L$1 = r9
            r0.L$2 = r10
            r0.L$3 = r11
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$4 = r4
            r0.L$5 = r9
            r0.Z$0 = r7
            r0.I$0 = r12
            r0.Z$1 = r13
            r0.label = r3
            java.lang.Object r2 = r2.toExtractorLink(r0)
            if (r2 != r1) goto L94
            return r1
        L94:
            r4 = r0
            r0 = r13
            r13 = r12
            r12 = r10
            r10 = r9
        L99:
            r10.invoke(r2)
            r10 = r12
            r12 = r13
            r13 = r0
            r0 = r4
            goto L65
        La1:
            java.util.List<com.lagradost.cloudstream3.actions.temp.CloudStreamPackage$MinimalSubtitleLink> r6 = r6.subs
            java.util.Iterator r6 = r6.iterator()
        La7:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto Lbb
            java.lang.Object r7 = r6.next()
            com.lagradost.cloudstream3.actions.temp.CloudStreamPackage$MinimalSubtitleLink r7 = (com.lagradost.cloudstream3.actions.temp.CloudStreamPackage.MinimalSubtitleLink) r7
            com.lagradost.cloudstream3.ui.player.SubtitleData r7 = r7.toSubtitleData()
            r10.invoke(r7)
            goto La7
        Lbb:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.player.MinimalLinkGenerator.generateLinks(boolean, java.util.Set, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, int, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
