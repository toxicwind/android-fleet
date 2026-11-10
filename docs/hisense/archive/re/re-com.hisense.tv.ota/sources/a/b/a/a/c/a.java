package a.b.a.a.c;

import com.hmct.cloud.sdk.bean.account.SignonReplyInfo;
import com.ju.lib.datacommunication.network.http.e;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends b {
    protected e b;

    protected a(e eVar) {
        this.b = eVar;
    }

    public static a e(e eVar) {
        return a.b.a.a.c.d.a.g(eVar);
    }

    public abstract SignonReplyInfo f(String str, boolean z, HashMap map);
}
