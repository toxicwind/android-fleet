package b.b.a;

import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/* JADX INFO: loaded from: classes.dex */
public class f {
    public static f c = new f();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public DatagramSocket f9a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f10b = false;

    public void a(c cVar) {
        StringBuilder sbC = b.a.a.a.a.c("start(listener)    threadID:");
        sbC.append(Thread.currentThread().getId());
        e.a("UdpServer", sbC.toString());
        StringBuilder sbC2 = b.a.a.a.a.c("start:UdpServer is running?:" + this.f10b + "  listener:" + cVar);
        sbC2.append(e.c());
        Log.i("UdpServer", sbC2.toString());
        synchronized (this) {
            this.f10b = true;
            if (this.f9a == null) {
                this.f9a = new DatagramSocket(49691);
            }
        }
        while (this.f10b) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
                this.f9a.receive(datagramPacket);
                String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                if (cVar != null) {
                    cVar.a(str);
                }
            } catch (RemoteException e) {
                e.b("UdpServer", e);
                return;
            } catch (SocketException e2) {
                e.b("UdpServer", e2);
                return;
            } catch (IOException e3) {
                e.b("UdpServer", e3);
                return;
            }
        }
    }
}
