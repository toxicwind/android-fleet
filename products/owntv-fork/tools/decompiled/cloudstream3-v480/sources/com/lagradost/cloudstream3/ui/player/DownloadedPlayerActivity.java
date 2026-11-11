package com.lagradost.cloudstream3.ui.player;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.BackPressedCallbackHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadedPlayerActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001a\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0014J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000fH\u0002J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\fH\u0014¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/DownloadedPlayerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "dispatchKeyEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onKeyDown", "keyCode", "", "onUserLeaveHint", "", "onNewIntent", "intent", "Landroid/content/Intent;", "isSameIntent", "newIntent", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "handleIntent", "onResume", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadedPlayerActivity extends AppCompatActivity {
    public static final String TAG = "DownloadedPlayerActivity";

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Boolean boolDispatchKeyEvent = CommonActivity.INSTANCE.dispatchKeyEvent(this, event);
        return boolDispatchKeyEvent != null ? boolDispatchKeyEvent.booleanValue() : super.dispatchKeyEvent(event);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Boolean boolOnKeyDown = CommonActivity.INSTANCE.onKeyDown(this, keyCode, event);
        return boolOnKeyDown != null ? boolOnKeyDown.booleanValue() : super.onKeyDown(keyCode, event);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        CommonActivity.INSTANCE.onUserLeaveHint(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        if (isSameIntent(intent)) {
            return;
        }
        setIntent(intent);
        Log.i(TAG, "onNewIntent");
        handleIntent(intent);
    }

    private final boolean isSameIntent(final Intent newIntent) {
        ClipData.Item itemAt;
        ClipData.Item itemAt2;
        final Intent intent = getIntent();
        if (intent == null) {
            return false;
        }
        Uri data = intent.getData();
        Uri uri = null;
        if (data == null) {
            ClipData clipData = intent.getClipData();
            data = (clipData == null || (itemAt2 = clipData.getItemAt(0)) == null) ? null : itemAt2.getUri();
        }
        Uri data2 = newIntent.getData();
        if (data2 == null) {
            ClipData clipData2 = newIntent.getClipData();
            if (clipData2 != null && (itemAt = clipData2.getItemAt(0)) != null) {
                uri = itemAt.getUri();
            }
        } else {
            uri = data2;
        }
        if (data != null && Intrinsics.areEqual(data, uri)) {
            return true;
        }
        String str = (String) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.DownloadedPlayerActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return intent.getStringExtra("android.intent.extra.TEXT");
            }
        });
        return str != null && Intrinsics.areEqual(str, (String) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.DownloadedPlayerActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return newIntent.getStringExtra("android.intent.extra.TEXT");
            }
        }));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DownloadedPlayerActivity downloadedPlayerActivity = this;
        CommonActivity.INSTANCE.loadThemes(downloadedPlayerActivity);
        CommonActivity.INSTANCE.init(downloadedPlayerActivity);
        UIHelper.INSTANCE.enableEdgeToEdgeCompat(downloadedPlayerActivity);
        setContentView(R.layout.empty_layout);
        Log.i(TAG, "onCreate");
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        handleIntent(intent);
        BackPressedCallbackHelper.INSTANCE.attachBackPressedCallback(this, TAG, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.DownloadedPlayerActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedPlayerActivity.onCreate$lambda$0(this.f$0, (BackPressedCallbackHelper.CallbackHelper) obj);
            }
        });
    }

    static final Unit onCreate$lambda$0(DownloadedPlayerActivity downloadedPlayerActivity, BackPressedCallbackHelper.CallbackHelper attachBackPressedCallback) {
        Intrinsics.checkNotNullParameter(attachBackPressedCallback, "$this$attachBackPressedCallback");
        downloadedPlayerActivity.moveTaskToBack(true);
        return Unit.INSTANCE;
    }

    private final void handleIntent(final Intent intent) {
        CharSequence text;
        Uri data = intent.getData();
        DownloadedPlayerActivity downloadedPlayerActivity = this;
        if (OfflinePlaybackHelper.INSTANCE.playIntent(downloadedPlayerActivity, intent)) {
            return;
        }
        if (!Intrinsics.areEqual(intent.getAction(), "android.intent.action.SEND") && !Intrinsics.areEqual(intent.getAction(), "android.intent.action.OPEN_DOCUMENT") && !Intrinsics.areEqual(intent.getAction(), "android.intent.action.VIEW")) {
            if (Intrinsics.areEqual(data != null ? data.getScheme() : null, "content")) {
                OfflinePlaybackHelper.INSTANCE.playUri(downloadedPlayerActivity, data);
                return;
            } else {
                finishAndRemoveTask();
                return;
            }
        }
        String str = (String) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.DownloadedPlayerActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return intent.getStringExtra("android.intent.extra.TEXT");
            }
        });
        ClipData clipData = intent.getClipData();
        ClipData.Item itemAt = (clipData == null || clipData.getItemCount() <= 0) ? null : clipData.getItemAt(0);
        String string = (itemAt == null || (text = itemAt.getText()) == null) ? null : text.toString();
        if ((itemAt != null ? itemAt.getUri() : null) != null) {
            OfflinePlaybackHelper offlinePlaybackHelper = OfflinePlaybackHelper.INSTANCE;
            Uri uri = itemAt.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            offlinePlaybackHelper.playUri(downloadedPlayerActivity, uri);
            return;
        }
        if (string != null) {
            OfflinePlaybackHelper.INSTANCE.playLink(downloadedPlayerActivity, string);
            return;
        }
        if (data != null) {
            OfflinePlaybackHelper.INSTANCE.playUri(downloadedPlayerActivity, data);
        } else if (str != null) {
            OfflinePlaybackHelper.INSTANCE.playLink(downloadedPlayerActivity, str);
        } else {
            finishAndRemoveTask();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        CommonActivity.INSTANCE.setActivityInstance(this);
    }
}
