package com.lagradost.cloudstream3.extractors;

import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.schabi.newpipe.extractor.stream.AudioStream;
import org.schabi.newpipe.extractor.stream.VideoStream;

/* JADX INFO: compiled from: YoutubeExtractor.jvmCommon.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.YoutubeExtractor$processVideo$2$1", f = "YoutubeExtractor.jvmCommon.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {66}, m = "invokeSuspend", n = {"$this$newExtractorLink", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-YoutubeExtractor$processVideo$2$1$1"}, nl = {109}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
final class YoutubeExtractor$processVideo$2$1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<AudioStream> $audioStreams;
    final /* synthetic */ VideoStream $video;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    YoutubeExtractor$processVideo$2$1(VideoStream videoStream, List<AudioStream> list, Continuation<? super YoutubeExtractor$processVideo$2$1> continuation) {
        super(2, continuation);
        this.$video = videoStream;
        this.$audioStreams = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        YoutubeExtractor$processVideo$2$1 youtubeExtractor$processVideo$2$1 = new YoutubeExtractor$processVideo$2$1(this.$video, this.$audioStreams, continuation);
        youtubeExtractor$processVideo$2$1.L$0 = obj;
        return youtubeExtractor$processVideo$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
        return ((YoutubeExtractor$processVideo$2$1) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x006b  */
    /* JADX WARN: Code duplicated, block: B:13:0x00b1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:14:0x00b2  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x00b2 -> B:15:0x00b3). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = r14.L$0
            com.lagradost.cloudstream3.utils.ExtractorLink r0 = (com.lagradost.cloudstream3.utils.ExtractorLink) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r14.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            int r2 = r14.I$1
            int r5 = r14.I$0
            java.lang.Object r6 = r14.L$8
            java.util.Collection r6 = (java.util.Collection) r6
            java.lang.Object r7 = r14.L$7
            com.lagradost.cloudstream3.utils.ExtractorLink r7 = (com.lagradost.cloudstream3.utils.ExtractorLink) r7
            java.lang.Object r8 = r14.L$6
            org.schabi.newpipe.extractor.stream.AudioStream r8 = (org.schabi.newpipe.extractor.stream.AudioStream) r8
            java.lang.Object r8 = r14.L$4
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r14.L$3
            java.util.Collection r9 = (java.util.Collection) r9
            java.lang.Object r10 = r14.L$2
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.lang.Object r11 = r14.L$1
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            kotlin.ResultKt.throwOnFailure(r15)
            goto Lb3
        L35:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L3d:
            kotlin.ResultKt.throwOnFailure(r15)
            org.schabi.newpipe.extractor.stream.VideoStream r15 = r14.$video
            int r15 = r15.getHeight()
            r0.setQuality(r15)
            java.util.List<org.schabi.newpipe.extractor.stream.AudioStream> r15 = r14.$audioStreams
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.ArrayList r2 = new java.util.ArrayList
            r5 = 10
            int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r15, r5)
            r2.<init>(r5)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r5 = r15.iterator()
            r10 = r15
            r11 = r10
            r7 = r0
            r6 = r2
            r2 = r4
            r8 = r5
            r5 = r2
        L65:
            boolean r15 = r8.hasNext()
            if (r15 == 0) goto Lba
            java.lang.Object r15 = r8.next()
            r9 = r15
            org.schabi.newpipe.extractor.stream.AudioStream r9 = (org.schabi.newpipe.extractor.stream.AudioStream) r9
            java.lang.String r12 = r9.getContent()
            java.lang.String r13 = "getContent(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r13)
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r14.L$0 = r13
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r14.L$1 = r13
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r14.L$2 = r13
            r14.L$3 = r6
            r14.L$4 = r8
            java.lang.Object r15 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)
            r14.L$5 = r15
            java.lang.Object r15 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r14.L$6 = r15
            r14.L$7 = r7
            r14.L$8 = r6
            r14.I$0 = r5
            r14.I$1 = r2
            r14.I$2 = r4
            r14.label = r3
            r15 = 0
            r9 = 2
            java.lang.Object r15 = com.lagradost.cloudstream3.MainAPIKt.newAudioFile$default(r12, r15, r14, r9, r15)
            if (r15 != r1) goto Lb2
            return r1
        Lb2:
            r9 = r6
        Lb3:
            com.lagradost.cloudstream3.AudioFile r15 = (com.lagradost.cloudstream3.AudioFile) r15
            r6.add(r15)
            r6 = r9
            goto L65
        Lba:
            java.util.List r6 = (java.util.List) r6
            r7.setAudioTracks(r6)
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.YoutubeExtractor$processVideo$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
