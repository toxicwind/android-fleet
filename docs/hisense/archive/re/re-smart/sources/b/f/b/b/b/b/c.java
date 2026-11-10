package b.f.b.b.b.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hisense.tv.smartsense.module.firebase.FirebaseBroadcastDelegate;

/* JADX INFO: loaded from: classes2.dex */
public class c extends BroadcastReceiver {
    public c(FirebaseBroadcastDelegate firebaseBroadcastDelegate) {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        FirebaseBroadcastDelegate.f1963b++;
        if (FirebaseBroadcastDelegate.f1963b > 1 && "android.intent.action.SCREEN_ON".equals(intent.getAction())) {
            b.f.b.b.b.b.a.c.a().e();
        }
    }
}
