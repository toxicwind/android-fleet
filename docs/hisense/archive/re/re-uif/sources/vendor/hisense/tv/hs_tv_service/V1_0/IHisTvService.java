package vendor.hisense.tv.hs_tv_service.V1_0;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface IHisTvService extends IHwInterface {
    void changeToOtherSource() throws RemoteException;

    void changeToTvSource() throws RemoteException;

    int cleanChannelListByTunerMode(int i) throws RemoteException;

    IHisTvServiceChannelInfoBase getChannelInfoByUUID(String str) throws RemoteException;

    ArrayList<IHisTvServiceChannelInfoBase> getChannelListByTunerMode(int i) throws RemoteException;

    String getCurrentPlayChannel() throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    String loadSetting(int i) throws RemoteException;

    int presetChannel(int i) throws RemoteException;

    void saveSetting(int i, String str) throws RemoteException;

    int startPlay(String str, int i, int i2) throws RemoteException;

    void startService() throws RemoteException;

    void stopService() throws RemoteException;

    static IHisTvService asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisTvService)) {
            return (IHisTvService) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService")) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IHisTvService getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService", str));
    }

    static IHisTvService getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IHisTvService {
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
                return "[class or subclass of vendor.hisense.tv.hs_tv_service@1.0::IHisTvService]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public int startPlay(String str, int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            hwParcel.writeString(str);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(8, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public String getCurrentPlayChannel() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public IHisTvServiceChannelInfoBase getChannelInfoByUUID(String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                IHisTvServiceChannelInfoBase iHisTvServiceChannelInfoBase = new IHisTvServiceChannelInfoBase();
                iHisTvServiceChannelInfoBase.readFromParcel(hwParcel2);
                return iHisTvServiceChannelInfoBase;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public ArrayList<IHisTvServiceChannelInfoBase> getChannelListByTunerMode(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(13, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return IHisTvServiceChannelInfoBase.readVectorFromParcel(hwParcel2);
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public int cleanChannelListByTunerMode(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public void saveSetting(int i, String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(95, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public String loadSetting(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(96, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public void changeToTvSource() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(99, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public void changeToOtherSource() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(100, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public int presetChannel(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(104, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public void startService() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VS_BIT_RATE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
        public void stopService() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.tv.hs_tv_service@1.0::IHisTvService");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_REPLAY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService
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
