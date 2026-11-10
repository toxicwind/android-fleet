package vendor.hisense.mw.hs_av_service.V1_0;

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
public interface IHisAudioApi extends IHwInterface {

    @FunctionalInterface
    public interface getAVCFeatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBalanceCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGEQCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getIsSubwooferSupportCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMultiBalanceCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMultiDigitalAMPValueByDevIdCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundCommonInfCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundSocDRCFeatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundSocHPFFeatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundSocPEQEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundSocPEQFreqCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundSocPEQGainCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundSocPEQQCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundSocPrescaleCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundVolCurveValCallback {
        void onValues(int i, int i2);
    }

    void getAVCFeature(int i, getAVCFeatureCallback getavcfeaturecallback) throws RemoteException;

    void getBalance(getBalanceCallback getbalancecallback) throws RemoteException;

    void getGEQ(int i, getGEQCallback getgeqcallback) throws RemoteException;

    void getIsSubwooferSupport(getIsSubwooferSupportCallback getissubwoofersupportcallback) throws RemoteException;

    void getMultiBalance(getMultiBalanceCallback getmultibalancecallback) throws RemoteException;

    void getMultiDigitalAMPValueByDevId(int i, int i2, getMultiDigitalAMPValueByDevIdCallback getmultidigitalampvaluebydevidcallback) throws RemoteException;

    void getSoundCommonInf(int i, int i2, int i3, getSoundCommonInfCallback getsoundcommoninfcallback) throws RemoteException;

    void getSoundMode(getSoundModeCallback getsoundmodecallback) throws RemoteException;

    void getSoundSocDRCFeature(int i, getSoundSocDRCFeatureCallback getsoundsocdrcfeaturecallback) throws RemoteException;

    void getSoundSocHPFFeature(int i, getSoundSocHPFFeatureCallback getsoundsochpffeaturecallback) throws RemoteException;

    void getSoundSocPEQEnable(getSoundSocPEQEnableCallback getsoundsocpeqenablecallback) throws RemoteException;

    void getSoundSocPEQFreq(int i, getSoundSocPEQFreqCallback getsoundsocpeqfreqcallback) throws RemoteException;

    void getSoundSocPEQGain(int i, getSoundSocPEQGainCallback getsoundsocpeqgaincallback) throws RemoteException;

    void getSoundSocPEQQ(int i, getSoundSocPEQQCallback getsoundsocpeqqcallback) throws RemoteException;

    void getSoundSocPrescale(getSoundSocPrescaleCallback getsoundsocprescalecallback) throws RemoteException;

    void getSoundVolCurveVal(int i, getSoundVolCurveValCallback getsoundvolcurvevalcallback) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    int setAVCFeature(int i, int i2) throws RemoteException;

    int setBalance(int i) throws RemoteException;

    int setGEQ(int i, int i2) throws RemoteException;

    int setMultiBalance(int i) throws RemoteException;

    int setMultiDigitalAMPValueByDevId(int i, int i2, int i3) throws RemoteException;

    int setSoundMode(int i) throws RemoteException;

    int setSoundSocDRCFeature(int i, int i2) throws RemoteException;

    int setSoundSocHPFFeature(int i, int i2) throws RemoteException;

    int setSoundSocPEQEnable(int i) throws RemoteException;

    int setSoundSocPEQFreq(int i, int i2) throws RemoteException;

    int setSoundSocPEQGain(int i, int i2) throws RemoteException;

    int setSoundSocPEQQ(int i, int i2) throws RemoteException;

    int setSoundSocPrescale(int i) throws RemoteException;

    int setSoundVolCurveVal(int i, int i2) throws RemoteException;

    static IHisAudioApi asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisAudioApi)) {
            return (IHisAudioApi) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi")) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IHisAudioApi getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi", str));
    }

    static IHisAudioApi getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IHisAudioApi {
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
                return "[class or subclass of vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundMode(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundMode(getSoundModeCallback getsoundmodecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundmodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setGEQ(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getGEQ(int i, getGEQCallback getgeqcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getgeqcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setAVCFeature(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getAVCFeature(int i, getAVCFeatureCallback getavcfeaturecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(23, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getavcfeaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setBalance(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(26, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getBalance(getBalanceCallback getbalancecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(27, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbalancecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundVolCurveVal(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(49, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundVolCurveVal(int i, getSoundVolCurveValCallback getsoundvolcurvevalcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(50, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundvolcurvevalcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundSocPrescale(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(51, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundSocPrescale(getSoundSocPrescaleCallback getsoundsocprescalecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(52, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundsocprescalecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundSocDRCFeature(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(57, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundSocDRCFeature(int i, getSoundSocDRCFeatureCallback getsoundsocdrcfeaturecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(58, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundsocdrcfeaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundSocHPFFeature(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(59, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundSocHPFFeature(int i, getSoundSocHPFFeatureCallback getsoundsochpffeaturecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(60, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundsochpffeaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundSocPEQFreq(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(61, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundSocPEQFreq(int i, getSoundSocPEQFreqCallback getsoundsocpeqfreqcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(62, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundsocpeqfreqcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundSocPEQGain(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(63, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundSocPEQGain(int i, getSoundSocPEQGainCallback getsoundsocpeqgaincallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(64, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundsocpeqgaincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundSocPEQQ(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(65, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundSocPEQQ(int i, getSoundSocPEQQCallback getsoundsocpeqqcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(66, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundsocpeqqcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundSocPEQEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(67, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundSocPEQEnable(getSoundSocPEQEnableCallback getsoundsocpeqenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(68, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundsocpeqenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getIsSubwooferSupport(getIsSubwooferSupportCallback getissubwoofersupportcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(74, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getissubwoofersupportcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundCommonInf(int i, int i2, int i3, getSoundCommonInfCallback getsoundcommoninfcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(81, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundcommoninfcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setMultiDigitalAMPValueByDevId(int i, int i2, int i3) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getMultiDigitalAMPValueByDevId(int i, int i2, getMultiDigitalAMPValueByDevIdCallback getmultidigitalampvaluebydevidcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultidigitalampvaluebydevidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setMultiBalance(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SUBTITLE_FMT_UPDATE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getMultiBalance(getMultiBalanceCallback getmultibalancecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_START, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultibalancecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
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
