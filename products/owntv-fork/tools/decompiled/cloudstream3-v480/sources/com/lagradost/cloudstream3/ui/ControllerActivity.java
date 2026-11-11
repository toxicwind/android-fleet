package com.lagradost.cloudstream3.ui;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.media.widget.ExpandedControllerActivity;
import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ControllerActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/ControllerActivity;", "Lcom/google/android/gms/cast/framework/media/widget/ExpandedControllerActivity;", "<init>", "()V", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ControllerActivity extends ExpandedControllerActivity {
    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.cast_expanded_controller_menu, menu);
        CastButtonFactory.setUpMediaRouteButton(this, menu, R.id.media_route_menu_item);
        return true;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ExpandedControllerActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView buttonImageViewAt = getButtonImageViewAt(0);
        Intrinsics.checkNotNullExpressionValue(buttonImageViewAt, "getButtonImageViewAt(...)");
        ImageView buttonImageViewAt2 = getButtonImageViewAt(1);
        Intrinsics.checkNotNullExpressionValue(buttonImageViewAt2, "getButtonImageViewAt(...)");
        ImageView buttonImageViewAt3 = getButtonImageViewAt(2);
        Intrinsics.checkNotNullExpressionValue(buttonImageViewAt3, "getButtonImageViewAt(...)");
        ImageView buttonImageViewAt4 = getButtonImageViewAt(3);
        Intrinsics.checkNotNullExpressionValue(buttonImageViewAt4, "getButtonImageViewAt(...)");
        getUIMediaController().bindViewToUIController(buttonImageViewAt, new SelectSourceController(buttonImageViewAt, this));
        getUIMediaController().bindViewToUIController(buttonImageViewAt2, new SkipTimeController(buttonImageViewAt2, false));
        getUIMediaController().bindViewToUIController(buttonImageViewAt3, new SkipTimeController(buttonImageViewAt3, true));
        getUIMediaController().bindViewToUIController(buttonImageViewAt4, new SkipNextEpisodeController(buttonImageViewAt4));
    }
}
