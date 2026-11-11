package com.lagradost.cloudstream3.ui.player;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.Qualities;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJl\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\"\u0010\u0012\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0014\u0012\u0004\u0012\u00020\u00170\u00132\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170\u00132\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0006H\u0096@¢\u0006\u0002\u0010\u001cR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/LinkGenerator;", "Lcom/lagradost/cloudstream3/ui/player/NoVideoGenerator;", CloudStreamPackage.LINKS_EXTRA, "", "Lcom/lagradost/cloudstream3/ui/player/BasicLink;", "extract", "", "refererUrl", "", "id", "", "<init>", "(Ljava/util/List;ZLjava/lang/String;Ljava/lang/Integer;)V", "generateLinks", "clearCache", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "callback", "Lkotlin/Function1;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "", "subtitleCallback", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", TypedValues.CycleType.S_WAVE_OFFSET, "isCasting", "(ZLjava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LinkGenerator extends NoVideoGenerator {
    private final boolean extract;
    private final List<BasicLink> links;
    private final String refererUrl;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.LinkGenerator$generateLinks$1, reason: invalid class name */
    /* JADX INFO: compiled from: LinkGenerator.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.LinkGenerator", f = "LinkGenerator.kt", i = {0, 0, 0, 0, 0, 0}, l = {53}, m = "generateLinks", n = {"sourceTypes", "callback", "subtitleCallback", "clearCache", TypedValues.CycleType.S_WAVE_OFFSET, "isCasting"}, nl = {75}, s = {"L$0", "L$1", "L$2", "Z$0", "I$0", "Z$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
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
            return LinkGenerator.this.generateLinks(false, null, null, null, 0, false, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LinkGenerator(List<BasicLink> links, boolean z, String str, Integer num) {
        super(num);
        Intrinsics.checkNotNullParameter(links, "links");
        this.links = links;
        this.extract = z;
        this.refererUrl = str;
    }

    public /* synthetic */ LinkGenerator(List list, boolean z, String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? true : z, (i & 4) != 0 ? null : str, num);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public Object generateLinks(boolean z, Set<? extends ExtractorLinkType> set, Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1, Function1<? super SubtitleData, Unit> function2, int i, boolean z2, Continuation<? super Boolean> continuation) {
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            List<BasicLink> list = this.links;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(function1, function2, null);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(set);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(function2);
            anonymousClass1.Z$0 = z;
            anonymousClass1.I$0 = i;
            anonymousClass1.Z$1 = z2;
            anonymousClass1.label = 1;
            if (ParCollectionsKt.amap(list, anonymousClass2, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean z3 = anonymousClass1.Z$1;
            int i3 = anonymousClass1.I$0;
            boolean z4 = anonymousClass1.Z$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxBoolean(true);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.LinkGenerator$generateLinks$2, reason: invalid class name */
    /* JADX INFO: compiled from: LinkGenerator.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "link", "Lcom/lagradost/cloudstream3/ui/player/BasicLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.LinkGenerator$generateLinks$2", f = "LinkGenerator.kt", i = {0, 1, 2}, l = {54, 65, 62}, m = "invokeSuspend", n = {"link", "link", "link"}, nl = {61, 66, 70}, s = {"L$0", "L$0", "L$0"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<BasicLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Pair<? extends ExtractorLink, ExtractorUri>, Unit> $callback;
        final /* synthetic */ Function1<SubtitleData, Unit> $subtitleCallback;
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1, Function1<? super SubtitleData, Unit> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$callback = function1;
            this.$subtitleCallback = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = LinkGenerator.this.new AnonymousClass2(this.$callback, this.$subtitleCallback, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(BasicLink basicLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(basicLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:30:0x00cd  */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x006d, code lost:
        
            if (r15 == r1) goto L29;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0076, code lost:
        
            if (((java.lang.Boolean) r15).booleanValue() == false) goto L20;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 218
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.player.LinkGenerator.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        static final Unit invokeSuspend$lambda$0(Function1 function1, SubtitleFile subtitleFile) {
            function1.invoke(PlayerSubtitleHelper.INSTANCE.getSubtitleData(subtitleFile));
            return Unit.INSTANCE;
        }

        static final Unit invokeSuspend$lambda$1(Function1 function1, ExtractorLink extractorLink) {
            function1.invoke(TuplesKt.to(extractorLink, null));
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.LinkGenerator$generateLinks$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: LinkGenerator.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.LinkGenerator$generateLinks$2$3", f = "LinkGenerator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass3 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ LinkGenerator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(LinkGenerator linkGenerator, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.this$0 = linkGenerator;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, continuation);
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
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    String str = this.this$0.refererUrl;
                    if (str == null) {
                        str = "";
                    }
                    extractorLink.setReferer(str);
                    extractorLink.setQuality(Qualities.Unknown.getValue());
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
