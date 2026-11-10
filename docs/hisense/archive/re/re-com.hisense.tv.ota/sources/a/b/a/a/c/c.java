package a.b.a.a.c;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.bean.upgrade.DevUpgradeReply;
import com.ju.lib.datacommunication.network.http.e;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends b {
    protected e b;

    protected c(e eVar) {
        this.b = eVar;
    }

    public static c f(e eVar) {
        return a.b.a.a.c.d.b.h(eVar);
    }

    public abstract DevUpgradeReply e(String str, boolean z, HashMap map);

    public abstract ReplyInfo g(String str, boolean z, HashMap map);
}
