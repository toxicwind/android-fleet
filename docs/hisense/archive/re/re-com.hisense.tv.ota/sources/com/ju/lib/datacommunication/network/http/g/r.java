package com.ju.lib.datacommunication.network.http.g;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class r implements l {
    private static final String i = "r";
    private static r j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n f138a;
    private HandlerThread b;
    private Handler c;
    private s d;
    private int e = 1;
    private Runnable f = new o(this);
    private Runnable g = new p(this);
    private Runnable h = new q(this);

    private r(String str, String str2, String str3, j jVar) {
        this.d = new s(str, str2, str3, jVar);
        HandlerThread handlerThread = new HandlerThread("DnamicAddress");
        this.b = handlerThread;
        handlerThread.start();
        n();
    }

    static /* synthetic */ int m(r rVar) {
        int i2 = rVar.e;
        rVar.e = i2 + 1;
        return i2;
    }

    private void n() {
        this.c = new Handler(this.b.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        for (a aVar : this.f138a.d) {
            List list = aVar.b;
            if (list != null && !list.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                Iterator it = aVar.b.iterator();
                while (it.hasNext()) {
                    arrayList.add(k.a((String) it.next()));
                }
                if (!arrayList.isEmpty()) {
                    aVar.b = arrayList;
                }
            }
        }
        if (com.ju.lib.datacommunication.network.http.i.b.e()) {
            com.ju.lib.datacommunication.network.http.i.b.d(i, "decode end mAddressResponse.payload :", this.f138a.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long p(long j2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        com.ju.lib.datacommunication.network.http.i.b.d(i, "next update time nowTime:" + jCurrentTimeMillis, ",timeStampRet:" + j2);
        if (j2 > 0 && jCurrentTimeMillis >= j2 && jCurrentTimeMillis - j2 < 86400000) {
            return (j2 + 86400000) - jCurrentTimeMillis;
        }
        return 0L;
    }

    public static final r r(String str, String str2, String str3, j jVar) {
        if (j == null) {
            synchronized (r.class) {
                if (j == null) {
                    j = new r(str, str2, str3, jVar);
                }
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(Runnable runnable, long j2) {
        this.c.removeCallbacks(runnable);
        this.c.postDelayed(runnable, j2);
    }

    @Override // com.ju.lib.datacommunication.network.http.g.l
    public void a() {
        s(this.f, 0L);
    }

    @Override // com.ju.lib.datacommunication.network.http.g.l
    public String b(String str) {
        List listQ = q(str);
        return (listQ == null || listQ.isEmpty()) ? str : (String) listQ.get(new Random().nextInt(listQ.size()));
    }

    public List q(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (this.f138a != null && this.f138a.d != null) {
                for (a aVar : this.f138a.d) {
                    if (str.equals(aVar.f125a)) {
                        if (aVar.b == null || aVar.b.isEmpty()) {
                            if (com.ju.lib.datacommunication.network.http.i.b.e()) {
                                com.ju.lib.datacommunication.network.http.i.b.d(i, "address found null:" + str);
                            }
                            arrayList.add(str);
                            return arrayList;
                        }
                        List list = aVar.b;
                        if (com.ju.lib.datacommunication.network.http.i.b.e()) {
                            com.ju.lib.datacommunication.network.http.i.b.d(i, "get address sucess:" + list);
                        }
                        return list;
                    }
                }
            }
            arrayList.add(str);
            return arrayList;
        } catch (Exception e) {
            com.ju.lib.datacommunication.network.http.i.b.d(i, "get address fail:" + e);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(str);
            return arrayList2;
        }
    }
}
