package com.lagradost.cloudstream3.ui;

import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ControllerActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/ui/SkipTimeController;", "Lcom/google/android/gms/cast/framework/media/uicontroller/UIController;", "view", "Landroid/widget/ImageView;", "forwards", "", "<init>", "(Landroid/widget/ImageView;Z)V", "getView", "()Landroid/widget/ImageView;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SkipTimeController extends UIController {
    private final ImageView view;

    public SkipTimeController(ImageView view, final boolean z) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
        view.setImageResource(z ? R.drawable.go_forward_30 : R.drawable.go_back_30);
        final int i = 30;
        view.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.SkipTimeController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SkipTimeController._init_$lambda$0(this.f$0, i, z, view2);
            }
        });
    }

    public final ImageView getView() {
        return this.view;
    }

    static final void _init_$lambda$0(SkipTimeController skipTimeController, int i, boolean z, View view) {
        RemoteMediaClient remoteMediaClient = skipTimeController.getRemoteMediaClient();
        if (remoteMediaClient != null) {
            MediaSeekOptions.Builder position = new MediaSeekOptions.Builder().setPosition(remoteMediaClient.getApproximateStreamPosition() + ((long) (i * 1000 * (z ? 1 : -1))));
            Intrinsics.checkNotNullExpressionValue(position, "setPosition(...)");
            remoteMediaClient.seek(position.build());
        }
    }
}
