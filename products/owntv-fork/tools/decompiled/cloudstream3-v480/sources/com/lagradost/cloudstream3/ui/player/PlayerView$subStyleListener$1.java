package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.ui.subtitles.SaveCaptionStyle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerView.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class PlayerView$subStyleListener$1 extends FunctionReferenceImpl implements Function1<SaveCaptionStyle, Unit> {
    PlayerView$subStyleListener$1(Object obj) {
        super(1, obj, PlayerView.class, "onSubStyleChanged", "onSubStyleChanged(Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(SaveCaptionStyle saveCaptionStyle) {
        invoke2(saveCaptionStyle);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(SaveCaptionStyle p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((PlayerView) this.receiver).onSubStyleChanged(p0);
    }
}
