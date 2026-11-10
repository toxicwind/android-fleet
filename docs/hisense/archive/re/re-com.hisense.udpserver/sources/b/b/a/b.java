package b.b.a;

import com.hisense.udpserver.AIDLUdpScanService;

/* JADX INFO: loaded from: classes.dex */
public class b implements Runnable {
    public b(AIDLUdpScanService.a aVar) {
    }

    @Override // java.lang.Runnable
    public void run() {
        StringBuilder sbC = b.a.a.a.a.c("stopServer() threadID:");
        sbC.append(Thread.currentThread().getId());
        e.a("AIDLUdpScanService", sbC.toString());
        f fVar = f.c;
        synchronized (fVar) {
            e.a("UdpServer", "stop()    threadID:" + Thread.currentThread().getId() + "  socket:" + fVar.f9a);
            fVar.f10b = false;
            if (fVar.f9a != null) {
                fVar.f9a.close();
                fVar.f9a = null;
            }
        }
    }
}
