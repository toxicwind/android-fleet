package vendor.hisense.support.tv.hs_platform_service.V1_0;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface IHisPlatformConfig extends IHwInterface {
    int getEmmcType() throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    int isFileOrDirExist(String str) throws RemoteException;

    String readFactoryLogFile(String str) throws RemoteException;

    String readFile(String str) throws RemoteException;

    int removeFile(String str) throws RemoteException;

    int resetGraphCorrectionUSB() throws RemoteException;

    int setLaserAutoGraphCorrectionUSB(ArrayList<Integer> arrayList) throws RemoteException;

    int writeDrmKey() throws RemoteException;

    int writeFactoryLogFileAppend(String str, String str2, int i) throws RemoteException;

    int writeFile(String str, String str2, boolean z, boolean z2) throws RemoteException;

    static IHisPlatformConfig asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisPlatformConfig)) {
            return (IHisPlatformConfig) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig")) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IHisPlatformConfig getService(String str, boolean z) throws RemoteException {
        return asInterface(HwBinder.getService("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig", str, z));
    }

    static IHisPlatformConfig getService(boolean z) throws RemoteException {
        return getService("default", z);
    }

    public static final class Proxy implements IHisPlatformConfig {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.mRemote = iHwBinder;
        }

        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public String readFile(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public int writeFile(String str, String str2, boolean z, boolean z2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            hwParcel.writeBool(z);
            hwParcel.writeBool(z2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public int removeFile(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public int writeFactoryLogFileAppend(String str, String str2, int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            hwParcel.writeString(str2);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(5, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public String readFactoryLogFile(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public int getEmmcType() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(14, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public int setLaserAutoGraphCorrectionUSB(ArrayList<Integer> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeInt32Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(15, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public int resetGraphCorrectionUSB() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(17, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public int writeDrmKey() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(21, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public int isFileOrDirExist(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.support.tv.hs_platform_service@1.0::IHisPlatformConfig");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(22, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        public String interfaceDescriptor() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }
    }
}
