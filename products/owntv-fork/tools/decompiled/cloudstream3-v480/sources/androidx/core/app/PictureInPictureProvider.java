package androidx.core.app;

import kotlin.Metadata;

/* JADX INFO: compiled from: PictureInPictureProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/core/app/PictureInPictureProvider;", "Landroidx/core/app/OnPictureInPictureModeChangedProvider;", "Landroidx/core/app/OnPictureInPictureUiStateChangedProvider;", "Landroidx/core/app/OnUserLeaveHintProvider;", "enterPictureInPictureMode", "", "params", "Landroidx/core/app/PictureInPictureParamsCompat;", "setPictureInPictureParams", "core"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface PictureInPictureProvider extends OnPictureInPictureModeChangedProvider, OnPictureInPictureUiStateChangedProvider, OnUserLeaveHintProvider {
    void enterPictureInPictureMode(PictureInPictureParamsCompat params);

    void setPictureInPictureParams(PictureInPictureParamsCompat params);
}
