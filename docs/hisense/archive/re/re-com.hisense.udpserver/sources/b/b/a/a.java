package b.b.a;

import com.hisense.udpserver.AIDLUdpScanService;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class a implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c f7a;

    public a(AIDLUdpScanService.a aVar, c cVar) {
        this.f7a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        f fVar = f.c;
        try {
            e.a("AIDLUdpScanService", "udpServer.start(receiverListener) before   threadID:" + Thread.currentThread().getId());
            fVar.a(this.f7a);
            e.a("AIDLUdpScanService", "udpServer.start(receiverListener) after   threadID:" + Thread.currentThread().getId());
        } catch (IOException e) {
            e.printStackTrace();
            e.b("AIDLUdpScanService  socket create exception:", e);
        }
    }
}
