package vendor.hisense.dtv.dtvservice.V1_0;

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
public interface IHisChannelListApi extends IHwInterface {
    int addPresetChannel(int i) throws RemoteException;

    int cleanChannelsByTunerMode(int i) throws RemoteException;

    int flushChannelList() throws RemoteException;

    IChannelInfo getChannelInfoByChannelKey(String str) throws RemoteException;

    ArrayList<IChannelInfo> getChannelListByTunerMode(int i, boolean z) throws RemoteException;

    int importChannelFromUSB(String str, boolean z) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    static IHisChannelListApi asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisChannelListApi)) {
            return (IHisChannelListApi) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi")) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IHisChannelListApi getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService("vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi", str));
    }

    static IHisChannelListApi getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IHisChannelListApi {
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
                return "[class or subclass of vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.dtv.dtvservice.V1_0.IHisChannelListApi
        public int addPresetChannel(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.dtv.dtvservice.V1_0.IHisChannelListApi
        public int importChannelFromUSB(String str, boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi");
            hwParcel.writeString(str);
            hwParcel.writeBool(z);
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

        @Override // vendor.hisense.dtv.dtvservice.V1_0.IHisChannelListApi
        public int cleanChannelsByTunerMode(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi");
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.dtv.dtvservice.V1_0.IHisChannelListApi
        public int flushChannelList() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi");
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

        @Override // vendor.hisense.dtv.dtvservice.V1_0.IHisChannelListApi
        public IChannelInfo getChannelInfoByChannelKey(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                IChannelInfo iChannelInfo = new IChannelInfo();
                iChannelInfo.readFromParcel(hwParcel2);
                return iChannelInfo;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.dtv.dtvservice.V1_0.IHisChannelListApi
        public ArrayList<IChannelInfo> getChannelListByTunerMode(int i, boolean z) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.dtv.dtvservice@1.0::IHisChannelListApi");
            hwParcel.writeInt32(i);
            hwParcel.writeBool(z);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return IChannelInfo.readVectorFromParcel(hwParcel2);
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.dtv.dtvservice.V1_0.IHisChannelListApi
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
