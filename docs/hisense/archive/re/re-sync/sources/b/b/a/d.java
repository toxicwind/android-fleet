package b.b.a;

import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.hisense.udpserver.AIDLUdpScanService;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends Binder implements IInterface {
    public d() {
        attachInterface(this, "com.hisense.udpserver.IUDPServer");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("com.hisense.udpserver.IUDPServer");
            c cVarC = c.a.c(parcel.readStrongBinder());
            AIDLUdpScanService.a aVar = (AIDLUdpScanService.a) this;
            AIDLUdpScanService aIDLUdpScanService = AIDLUdpScanService.this;
            if (aIDLUdpScanService.f12b == null) {
                aIDLUdpScanService.f12b = new HandlerThread("AIDLUdpScanService");
                AIDLUdpScanService.this.f12b.start();
            }
            AIDLUdpScanService aIDLUdpScanService2 = AIDLUdpScanService.this;
            if (aIDLUdpScanService2.f11a == null) {
                aIDLUdpScanService2.f11a = new Handler(AIDLUdpScanService.this.f12b.getLooper());
            }
            AIDLUdpScanService.this.f11a.post(new a(aVar, cVarC));
            parcel2.writeNoException();
            return true;
        }
        if (i != 2) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.hisense.udpserver.IUDPServer");
            return true;
        }
        parcel.enforceInterface("com.hisense.udpserver.IUDPServer");
        AIDLUdpScanService.a aVar2 = (AIDLUdpScanService.a) this;
        AIDLUdpScanService aIDLUdpScanService3 = AIDLUdpScanService.this;
        if (aIDLUdpScanService3.f12b == null) {
            aIDLUdpScanService3.f12b = new HandlerThread("AIDLUdpScanService");
            AIDLUdpScanService.this.f12b.start();
        }
        AIDLUdpScanService aIDLUdpScanService4 = AIDLUdpScanService.this;
        if (aIDLUdpScanService4.f11a == null) {
            aIDLUdpScanService4.f11a = new Handler(AIDLUdpScanService.this.f12b.getLooper());
        }
        AIDLUdpScanService.this.f11a.post(new b(aVar2));
        HandlerThread handlerThread = AIDLUdpScanService.this.f12b;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            AIDLUdpScanService.this.f12b = null;
        }
        StringBuilder sbC = b.a.a.a.a.c(" mHandlerThread.quitSafely() threadID:");
        sbC.append(Thread.currentThread().getId());
        sbC.append("  mHandlerThread:");
        sbC.append(AIDLUdpScanService.this.f12b);
        e.a("AIDLUdpScanService", sbC.toString());
        parcel2.writeNoException();
        return true;
    }
}
