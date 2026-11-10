package vendor.hisense.mw.hs_av_service.V1_0;

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
public interface IHisHdmiApi extends IHwInterface {

    @FunctionalInterface
    public interface getItemValueCallback {
        void onValues(int i, int i2);
    }

    void getItemValue(int i, getItemValueCallback getitemvaluecallback) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    int setItemValue(int i, int i2) throws RemoteException;

    static IHisHdmiApi asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.hisense.mw.hs_av_service@1.0::IHisHdmiApi");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisHdmiApi)) {
            return (IHisHdmiApi) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.hisense.mw.hs_av_service@1.0::IHisHdmiApi")) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IHisHdmiApi getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService("vendor.hisense.mw.hs_av_service@1.0::IHisHdmiApi", str));
    }

    static IHisHdmiApi getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IHisHdmiApi {
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
                return "[class or subclass of vendor.hisense.mw.hs_av_service@1.0::IHisHdmiApi]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public int setItemValue(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisHdmiApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public void getItemValue(int i, getItemValueCallback getitemvaluecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisHdmiApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemvaluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
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
