package f.a.a.a.a.a;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IBase {

    @FunctionalInterface
    public interface b {
        void onValues(int i, String str);
    }

    @FunctionalInterface
    public interface c {
        void onValues(int i, String str);
    }

    @FunctionalInterface
    public interface d {
        void onValues(int i, String str);
    }

    @FunctionalInterface
    public interface e {
        void onValues(int i, long j);
    }

    @FunctionalInterface
    public interface f {
        void onValues(int i, String str);
    }

    @FunctionalInterface
    public interface g {
        void onValues(ArrayList<Integer> arrayList, int i);
    }

    @FunctionalInterface
    public interface h {
        void onValues(ArrayList<Integer> arrayList, int i);
    }

    static a asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof a)) {
            return (a) iHwInterfaceQueryLocalInterface;
        }
        C0040a c0040a = new C0040a(iHwBinder);
        try {
            Iterator<String> it = c0040a.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig")) {
                    return c0040a;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static a getService(String str, boolean z) {
        return asInterface(HwBinder.getService("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig", str, z));
    }

    static a getService(boolean z) {
        return getService("default", z);
    }

    /* JADX INFO: renamed from: f.a.a.a.a.a.a$a, reason: collision with other inner class name */
    public static final class C0040a implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IHwBinder f2170a;

        public C0040a(IHwBinder iHwBinder) {
            this.f2170a = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        public int a(String str, String str2, boolean z, boolean z2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            hwParcel.writeBool(z);
            hwParcel.writeBool(z2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.f2170a;
        }

        public int b() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int[] c() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(12, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                int[] iArr = new int[3];
                hwParcel2.readBuffer(12L).copyToInt32Array(0L, iArr, 3);
                return iArr;
            } finally {
                hwParcel2.release();
            }
        }

        public String d(String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            hwParcel.writeNativeHandle(nativeHandle);
            hwParcel.writeStringVector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(256131655, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        public String e(String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public int f(String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int g(String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public DebugInfo getDebugInfo() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(257049926, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                DebugInfo debugInfo = new DebugInfo();
                debugInfo.readFromParcel(hwParcel2);
                return debugInfo;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public ArrayList<byte[]> getHashChain() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(256398152, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                ArrayList<byte[]> arrayList = new ArrayList<>();
                HwBlob buffer = hwParcel2.readBuffer(16L);
                int int32 = buffer.getInt32(8L);
                HwBlob embeddedBuffer = hwParcel2.readEmbeddedBuffer(int32 * 32, buffer.handle(), 0L, true);
                arrayList.clear();
                for (int i = 0; i < int32; i++) {
                    byte[] bArr = new byte[32];
                    embeddedBuffer.copyToInt8Array(i * 32, bArr, 32);
                    arrayList.add(bArr);
                }
                hwParcel2.release();
                return arrayList;
            } catch (Throwable th) {
                hwParcel2.release();
                throw th;
            }
        }

        public int h() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(18, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return this.f2170a.hashCode();
        }

        public int i() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(20, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public ArrayList<String> interfaceChain() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public String interfaceDescriptor() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        public int j() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(21, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return this.f2170a.linkToDeath(deathRecipient, j);
        }

        @Override // android.hidl.base.V1_0.IBase
        public void notifySyspropsChanged() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(257120595, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public void ping() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(256921159, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public void setHALInstrumentation() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(256462420, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig]@Proxy";
            }
        }

        @Override // android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return this.f2170a.unlinkToDeath(deathRecipient);
        }

        public int b(String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int d() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(14, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public long e() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(13, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt64();
            } finally {
                hwParcel2.release();
            }
        }

        public int f() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(24, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int g() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(17, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int c(String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(22, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int a(String str, String str2, int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(5, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int b(ArrayList<Integer> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeInt32Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(16, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int a() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(8, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int a(String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public int a(ArrayList<Integer> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeInt32Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(15, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public void a(int i, g gVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(19, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gVar.onValues(hwParcel2.readInt32Vector(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        public int a(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(23, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public void a(h hVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(25, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                hVar.onValues(hwParcel2.readInt32Vector(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        public void a(e eVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(26, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                eVar.onValues(hwParcel2.readInt32(), hwParcel2.readInt64());
            } finally {
                hwParcel2.release();
            }
        }

        public void a(b bVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(27, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                bVar.onValues(hwParcel2.readInt32(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        public void a(d dVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(28, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                dVar.onValues(hwParcel2.readInt32(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        public void a(c cVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(29, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                cVar.onValues(hwParcel2.readInt32(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        public void a(f fVar) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.f2170a.transact(30, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                fVar.onValues(hwParcel2.readInt32(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }
    }
}
