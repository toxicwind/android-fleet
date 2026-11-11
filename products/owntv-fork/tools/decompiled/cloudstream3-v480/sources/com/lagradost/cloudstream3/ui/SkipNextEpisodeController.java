package com.lagradost.cloudstream3.ui;

import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.media.MediaQueue;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: ControllerActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/ui/SkipNextEpisodeController;", "Lcom/google/android/gms/cast/framework/media/uicontroller/UIController;", "view", "Landroid/widget/ImageView;", "<init>", "(Landroid/widget/ImageView;)V", "getView", "()Landroid/widget/ImageView;", "onMediaStatusUpdated", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SkipNextEpisodeController extends UIController {
    private final ImageView view;

    public SkipNextEpisodeController(ImageView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
        view.setImageResource(R.drawable.ic_baseline_skip_next_24);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.SkipNextEpisodeController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SkipNextEpisodeController._init_$lambda$0(this.f$0, view2);
            }
        });
    }

    public final ImageView getView() {
        return this.view;
    }

    static final void _init_$lambda$0(SkipNextEpisodeController skipNextEpisodeController, View view) {
        RemoteMediaClient remoteMediaClient = skipNextEpisodeController.getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.queueNext(new JSONObject());
            skipNextEpisodeController.view.setVisibility(8);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public void onMediaStatusUpdated() {
        Integer itemIndex;
        MediaQueue mediaQueue;
        RemoteMediaClient remoteMediaClient;
        super.onMediaStatusUpdated();
        this.view.setVisibility(8);
        RemoteMediaClient remoteMediaClient2 = getRemoteMediaClient();
        if (remoteMediaClient2 == null || (itemIndex = ControllerActivityKt.getItemIndex(remoteMediaClient2)) == null) {
            return;
        }
        int iIntValue = itemIndex.intValue();
        RemoteMediaClient remoteMediaClient3 = getRemoteMediaClient();
        if (remoteMediaClient3 == null || (mediaQueue = remoteMediaClient3.getMediaQueue()) == null || mediaQueue.getItemCount() - iIntValue <= 1 || (remoteMediaClient = getRemoteMediaClient()) == null || remoteMediaClient.isLoadingNextItem()) {
            return;
        }
        this.view.setVisibility(0);
    }
}
