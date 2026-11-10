package b.f.b.b.b.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hisense.tv.smartsense.module.firebase.FirebaseBroadcastDelegate;

/* JADX INFO: loaded from: classes2.dex */
public class d extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FirebaseBroadcastDelegate f723a;

    public d(FirebaseBroadcastDelegate firebaseBroadcastDelegate) {
        this.f723a = firebaseBroadcastDelegate;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.hisense.tv.ACTIVITY_STATE.appCrash".equals(action)) {
            this.f723a.b(context, intent.getExtras());
        } else if ("com.hisense.tv.ACTIVITY_STATE.appAnr".equals(action)) {
            this.f723a.a(context, intent.getExtras());
        }
    }
}
