package vendor.hisense.mw.hs_av_service.V1_0;

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
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.location.GpsStatusWrapper;
import androidx.core.widget.AutoScrollHelper;
import androidx.fragment.app.FragmentManagerImpl;
import com.google.common.base.Ascii;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.storage.StorageTask;
import com.hisense.avmiddleware.HisHdmiApi;
import com.hisense.support.tv.HisenseCountryLanInfo;
import com.mediatek.twoworlds.tv.MtkTvScanDvbtBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: loaded from: classes2.dex */
public interface IHisVideoApi extends IBase {
    public static final String kInterfaceName = "vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi";

    @FunctionalInterface
    public interface CalMAN_ULMPICallback {
        void onValues(int i, String str);
    }

    @FunctionalInterface
    public interface CalMAN_ULMPI_BYTESCallback {
        void onValues(int i, ArrayList<Byte> arrayList);
    }

    @FunctionalInterface
    public interface GetAutoGammaEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface ItemInOutCallback {
        void onValues(int i, ArrayList<Integer> arrayList);
    }

    public static final class Proxy implements IHisVideoApi {
        public IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void CalMAN_ULMPI(String str, CalMAN_ULMPICallback calMAN_ULMPICallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(506, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                calMAN_ULMPICallback.onValues(hwParcel2.readInt32(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void CalMAN_ULMPI_BYTES(ArrayList<Byte> arrayList, CalMAN_ULMPI_BYTESCallback calMAN_ULMPI_BYTESCallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt8Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(538, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                calMAN_ULMPI_BYTESCallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt8Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void GetAutoGammaEnable(GetAutoGammaEnableCallback getAutoGammaEnableCallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(255, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getAutoGammaEnableCallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void ItemInOut(int i, ArrayList<Integer> arrayList, ItemInOutCallback itemInOutCallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(428, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                itemInOutCallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int PictureMenuQuit(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(381, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int ReloadISFData(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(344, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int ResetAllFacSettings(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(123, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int ResetAllUserSettings(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int ResetAllVideoSettings(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int ResetColorTuner(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(79, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int ResetGammaCalibration(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(118, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int ResetMultiPointWhiteBalance(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(107, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int ResetWhiteBalance(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(94, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int ResetWhiteBalanceValue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(321, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int SetAutoGammaEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(254, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int backupPQSetting() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(417, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int cleanCalMANData(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(509, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            hwParcel.writeNativeHandle(nativeHandle);
            hwParcel.writeStringVector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256131655, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBGainBlueDelta(get2PWBGainBlueDeltaCallback get2pwbgainbluedeltacallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(404, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwbgainbluedeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBGainGreenDelta(get2PWBGainGreenDeltaCallback get2pwbgaingreendeltacallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(402, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwbgaingreendeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBGainRedDelta(get2PWBGainRedDeltaCallback get2pwbgainreddeltacallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(400, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwbgainreddeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBOffsetBlueDelta(get2PWBOffsetBlueDeltaCallback get2pwboffsetbluedeltacallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(398, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwboffsetbluedeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBOffsetGreenDelta(get2PWBOffsetGreenDeltaCallback get2pwboffsetgreendeltacallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(396, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwboffsetgreendeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBOffsetRedDelta(get2PWBOffsetRedDeltaCallback get2pwboffsetreddeltacallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(394, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwboffsetreddeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAIPQDetectSwitch(getAIPQDetectSwitchCallback getaipqdetectswitchcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(423, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getaipqdetectswitchcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAIPQDetectSwitchOptionState(getAIPQDetectSwitchOptionStateCallback getaipqdetectswitchoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(424, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getaipqdetectswitchoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAPOContentCurve(int i, int i2, getAPOContentCurveCallback getapocontentcurvecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(457, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getapocontentcurvecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAPOEnable(getAPOEnableCallback getapoenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(461, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getapoenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAPOWBCurve(int i, int i2, getAPOWBCurveCallback getapowbcurvecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(459, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getapowbcurvecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getActiveContrast(getActiveContrastCallback getactivecontrastcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(52, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getactivecontrastcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getActiveContrastOptionState(getActiveContrastOptionStateCallback getactivecontrastoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(510, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getactivecontrastoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAgingModeEnable(getAgingModeEnableCallback getagingmodeenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(245, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getagingmodeenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAllFeaturesFlags(getAllFeaturesFlagsCallback getallfeaturesflagscallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(427, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getallfeaturesflagscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAllPictureSettingsForRemoteNow(getAllPictureSettingsForRemoteNowCallback getallpicturesettingsforremotenowcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(335, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getallpicturesettingsforremotenowcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAspectRatio(getAspectRatioCallback getaspectratiocallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getaspectratiocallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAspectRatioDirectOptionState(getAspectRatioDirectOptionStateCallback getaspectratiodirectoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(300, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getaspectratiodirectoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAspectRatioDotByDotOptionState(getAspectRatioDotByDotOptionStateCallback getaspectratiodotbydotoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(299, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getaspectratiodotbydotoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAspectRatioOptionState(getAspectRatioOptionStateCallback getaspectratiooptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(297, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getaspectratiooptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAspectRatioPanoramicOptionState(getAspectRatioPanoramicOptionStateCallback getaspectratiopanoramicoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(298, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getaspectratiopanoramicoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAudioOnlyEnable(getAudioOnlyEnableCallback getaudioonlyenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(13, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getaudioonlyenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAutoFilmmakerModeStatus(getAutoFilmmakerModeStatusCallback getautofilmmakermodestatuscallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(274, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getautofilmmakermodestatuscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAutoScanMode(getAutoScanModeCallback getautoscanmodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(407, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getautoscanmodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklight20(getBacklight20Callback getbacklight20callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(195, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklight20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklight80(getBacklight80Callback getbacklight80callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(197, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklight80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightLevelOptionState(getBacklightLevelOptionStateCallback getbacklightleveloptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(469, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightleveloptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightMax(getBacklightMaxCallback getbacklightmaxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(198, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightMid(getBacklightMidCallback getbacklightmidcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(196, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightMin(getBacklightMinCallback getbacklightmincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(194, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightPWMFreq(getBacklightPWMFreqCallback getbacklightpwmfreqcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(199, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightpwmfreqcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightPercent(getBacklightPercentCallback getbacklightpercentcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightpercentcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightPercentByFactory(getBacklightPercentByFactoryCallback getbacklightpercentbyfactorycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(127, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightpercentbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBenqITFReserved1(getBenqITFReserved1Callback getbenqitfreserved1callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(376, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbenqitfreserved1callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBenqITFReserved2(getBenqITFReserved2Callback getbenqitfreserved2callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(378, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbenqitfreserved2callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBenqITFReserved3(getBenqITFReserved3Callback getbenqitfreserved3callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(380, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbenqitfreserved3callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBenqLowBlueLight(getBenqLowBlueLightCallback getbenqlowbluelightcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(19, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbenqlowbluelightcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBlackLevel(getBlackLevelCallback getblacklevelcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(498, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getblacklevelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBlackLevelOptionState(getBlackLevelOptionStateCallback getblackleveloptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(500, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getblackleveloptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBlueStretchEnable(getBlueStretchEnableCallback getbluestretchenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(21, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbluestretchenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBlurReductionOptionState(getBlurReductionOptionStateCallback getblurreductionoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(305, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getblurreductionoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBootLogoState(getBootLogoStateCallback getbootlogostatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(282, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbootlogostatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightness(getBrightnessCallback getbrightnesscallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(44, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnesscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightness20(getBrightness20Callback getbrightness20callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(144, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightness20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightness80(getBrightness80Callback getbrightness80callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STOPPED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightness80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightnessByFactory(getBrightnessByFactoryCallback getbrightnessbyfactorycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnessbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightnessMax(getBrightnessMaxCallback getbrightnessmaxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(147, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnessmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightnessMid(getBrightnessMidCallback getbrightnessmidcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STARTED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnessmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightnessMin(getBrightnessMinCallback getbrightnessmincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(143, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnessmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightnessOptionState(getBrightnessOptionStateCallback getbrightnessoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(489, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnessoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBypassPqEnable(getBypassPqEnableCallback getbypasspqenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(389, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbypasspqenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getCalMANCalibrated(int i, getCalMANCalibratedCallback getcalmancalibratedcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(508, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcalmancalibratedcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getCalMANEnable(getCalMANEnableCallback getcalmanenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(505, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcalmanenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getClrTempCompenCurve(int i, int i2, getClrTempCompenCurveCallback getclrtempcompencurvecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(487, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getclrtempcompencurvecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getClrTempCompenEnable(getClrTempCompenEnableCallback getclrtempcompenenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(485, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getclrtempcompenenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getColorSpace(getColorSpaceCallback getcolorspacecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(109, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcolorspacecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getColorSpaceOptionState(getColorSpaceOptionStateCallback getcolorspaceoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(310, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcolorspaceoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getColorTemperature(getColorTemperatureCallback getcolortemperaturecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(68, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcolortemperaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getColorTunerApplyToAll(getColorTunerApplyToAllCallback getcolortunerapplytoallcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(78, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcolortunerapplytoallcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getColorTunerBrightness(getColorTunerBrightnessCallback getcolortunerbrightnesscallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(72, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcolortunerbrightnesscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getColorTunerColor(getColorTunerColorCallback getcolortunercolorcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(70, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcolortunercolorcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getColorTunerHue(getColorTunerHueCallback getcolortunerhuecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(76, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcolortunerhuecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getColorTunerOptionState(getColorTunerOptionStateCallback getcolortuneroptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(426, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcolortuneroptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getColorTunerSaturation(getColorTunerSaturationCallback getcolortunersaturationcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(74, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcolortunersaturationcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrast(getContrastCallback getcontrastcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(42, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrast20(getContrast20Callback getcontrast20callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(154, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrast20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrast80(getContrast80Callback getcontrast80callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(156, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrast80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrastByFactory(getContrastByFactoryCallback getcontrastbyfactorycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(129, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrastMax(getContrastMaxCallback getcontrastmaxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BLOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrastMid(getContrastMidCallback getcontrastmidcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrastMin(getContrastMinCallback getcontrastmincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrastOptionState(getContrastOptionStateCallback getcontrastoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(490, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDMPPlayPhotoMode(getDMPPlayPhotoModeCallback getdmpplayphotomodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(280, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdmpplayphotomodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public DebugInfo getDebugInfo() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(257049926, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                DebugInfo debugInfo = new DebugInfo();
                debugInfo.readFromParcel(hwParcel2);
                return debugInfo;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDigitalNoiseReductionOptionState(getDigitalNoiseReductionOptionStateCallback getdigitalnoisereductionoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(StatusLine.HTTP_PERM_REDIRECT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdigitalnoisereductionoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDolbyIQAPOContentType(getDolbyIQAPOContentTypeCallback getdolbyiqapocontenttypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(449, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdolbyiqapocontenttypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDolbyIQAPOOptionState(getDolbyIQAPOOptionStateCallback getdolbyiqapooptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(468, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdolbyiqapooptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDolbyIQAPOWhiteBalance(getDolbyIQAPOWhiteBalanceCallback getdolbyiqapowhitebalancecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(450, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdolbyiqapowhitebalancecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDolbyIQEnableFlag(getDolbyIQEnableFlagCallback getdolbyiqenableflagcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(441, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdolbyiqenableflagcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDolbyIQUserBinEnable(getDolbyIQUserBinEnableCallback getdolbyiquserbinenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(454, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdolbyiquserbinenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDynamicBacklight(getDynamicBacklightCallback getdynamicbacklightcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdynamicbacklightcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDynamicBacklightOptionState(getDynamicBacklightOptionStateCallback getdynamicbacklightoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(ConfigFetchHandler.HTTP_TOO_MANY_REQUESTS, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdynamicbacklightoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDynamicContrast(getDynamicContrastCallback getdynamiccontrastcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(481, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdynamiccontrastcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDynamicToneMappingEnable(getDynamicToneMappingEnableCallback getdynamictonemappingenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(502, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdynamictonemappingenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDynamicToneMappingOptionState(getDynamicToneMappingOptionStateCallback getdynamictonemappingoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(503, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdynamictonemappingoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getEnergyLoopLogMask(getEnergyLoopLogMaskCallback getenergylooplogmaskcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(317, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getenergylooplogmaskcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getEnergySavingEnable(getEnergySavingEnableCallback getenergysavingenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(15, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getenergysavingenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getEnergySavingOptionState(getEnergySavingOptionStateCallback getenergysavingoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(432, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getenergysavingoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getEnhancedViewingAngleEnable(getEnhancedViewingAngleEnableCallback getenhancedviewingangleenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(62, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getenhancedviewingangleenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getEnhancedViewingAngleOptionState(getEnhancedViewingAngleOptionStateCallback getenhancedviewingangleoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(309, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getenhancedviewingangleoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFRCVersion(getFRCVersionCallback getfrcversioncallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(540, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfrcversioncallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFTEState(getFTEStateCallback getftestatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(284, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getftestatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTemperature(getFactoryColorTemperatureCallback getfactorycolortemperaturecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(211, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortemperaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTunerBrightness(getFactoryColorTunerBrightnessCallback getfactorycolortunerbrightnesscallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(237, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortunerbrightnesscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTunerColor(getFactoryColorTunerColorCallback getfactorycolortunercolorcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(235, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortunercolorcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTunerHue(getFactoryColorTunerHueCallback getfactorycolortunerhuecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(241, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortunerhuecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTunerSaturation(getFactoryColorTunerSaturationCallback getfactorycolortunersaturationcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(239, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortunersaturationcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryMultiPointWhiteBalanceBlue(getFactoryMultiPointWhiteBalanceBlueCallback getfactorymultipointwhitebalancebluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(231, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorymultipointwhitebalancebluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryMultiPointWhiteBalanceGreen(getFactoryMultiPointWhiteBalanceGreenCallback getfactorymultipointwhitebalancegreencallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(229, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorymultipointwhitebalancegreencallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryMultiPointWhiteBalanceLevel(getFactoryMultiPointWhiteBalanceLevelCallback getfactorymultipointwhitebalancelevelcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(225, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorymultipointwhitebalancelevelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryMultiPointWhiteBalanceRed(getFactoryMultiPointWhiteBalanceRedCallback getfactorymultipointwhitebalanceredcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(227, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorymultipointwhitebalanceredcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceGainBlue(getFactoryWhiteBalanceGainBlueCallback getfactorywhitebalancegainbluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(223, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalancegainbluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceGainGreen(getFactoryWhiteBalanceGainGreenCallback getfactorywhitebalancegaingreencallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(221, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalancegaingreencallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceGainRed(getFactoryWhiteBalanceGainRedCallback getfactorywhitebalancegainredcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(219, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalancegainredcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceOffsetBlue(getFactoryWhiteBalanceOffsetBlueCallback getfactorywhitebalanceoffsetbluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(217, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalanceoffsetbluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceOffsetGreen(getFactoryWhiteBalanceOffsetGreenCallback getfactorywhitebalanceoffsetgreencallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(215, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalanceoffsetgreencallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceOffsetRed(getFactoryWhiteBalanceOffsetRedCallback getfactorywhitebalanceoffsetredcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(213, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalanceoffsetredcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFilmmakerDetectionEnable(getFilmmakerDetectionEnableCallback getfilmmakerdetectionenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(269, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfilmmakerdetectionenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFilmmakerModeSelected(getFilmmakerModeSelectedCallback getfilmmakermodeselectedcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(272, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfilmmakermodeselectedcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFilmmakerOptionState(getFilmmakerOptionStateCallback getfilmmakeroptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(AutoScrollHelper.DEFAULT_MINIMUM_VELOCITY_DIPS, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfilmmakeroptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFreeSyncOptionState(getFreeSyncOptionStateCallback getfreesyncoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(438, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfreesyncoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFreeSyncSwitch(getFreeSyncSwitchCallback getfreesyncswitchcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(437, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfreesyncswitchcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getGameDetectionEnable(getGameDetectionEnableCallback getgamedetectionenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(276, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getgamedetectionenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getGammaAdjustOptionState(getGammaAdjustOptionStateCallback getgammaadjustoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(312, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getgammaadjustoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getGammaAdjustment(getGammaAdjustmentCallback getgammaadjustmentcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(113, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getgammaadjustmentcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getGammaCalibrationGain(getGammaCalibrationGainCallback getgammacalibrationgaincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(117, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getgammacalibrationgaincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getGammaCalibrationLevel(getGammaCalibrationLevelCallback getgammacalibrationlevelcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(115, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getgammacalibrationlevelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getGammaCalibrationOptionState(getGammaCalibrationOptionStateCallback getgammacalibrationoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(440, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getgammacalibrationoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getGammaEnable(getGammaEnableCallback getgammaenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(249, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getgammaenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHDMI20Format(getHDMI20FormatCallback gethdmi20formatcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(122, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethdmi20formatcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHDMIDynamicRangeOptionState(getHDMIDynamicRangeOptionStateCallback gethdmidynamicrangeoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(311, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethdmidynamicrangeoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public ArrayList<byte[]> getHashChain() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256398152, hwParcel, hwParcel2, 0);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHdmiDynamicRange(getHdmiDynamicRangeCallback gethdmidynamicrangecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(111, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethdmidynamicrangecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHue20(getHue20Callback gethue20callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethue20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHue80(getHue80Callback gethue80callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethue80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHueMax(getHueMaxCallback gethuemaxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_STREAM_OPENED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethuemaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHueMid(getHueMidCallback gethuemidcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethuemidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHueMin(getHueMinCallback gethuemincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_EOF, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethuemincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHueOptionState(getHueOptionStateCallback gethueoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(492, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethueoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getIMAXOptionState(getIMAXOptionStateCallback getimaxoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(455, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getimaxoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getISFOptionState(getISFOptionStateCallback getisfoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(341, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getisfoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getISFSwitch(getISFSwitchCallback getisfswitchcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(340, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getisfswitchcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getISFType(getISFTypeCallback getisftypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(343, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getisftypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getInstantGameResponseOptionState(getInstantGameResponseOptionStateCallback getinstantgameresponseoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(435, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getinstantgameresponseoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getInstantGameResponseSwitch(getInstantGameResponseSwitchCallback getinstantgameresponseswitchcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(434, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getinstantgameresponseswitchcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getIsTechnicolorHDR(getIsTechnicolorHDRCallback getistechnicolorhdrcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(518, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getistechnicolorhdrcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getItemData(int i, getItemDataCallback getitemdatacallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(409, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemdatacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getItemOptionState(int i, getItemOptionStateCallback getitemoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(533, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getItemString(int i, getItemStringCallback getitemstringcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(414, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemstringcallback.onValues(hwParcel2.readInt32(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getItemTable(int i, int i2, int i3, getItemTableCallback getitemtablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(494, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemtablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getItemValue(int i, getItemValueCallback getitemvaluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(416, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemvaluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getJudderReductionOptionState(getJudderReductionOptionStateCallback getjudderreductionoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(304, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getjudderreductionoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLaserScreenSize(getLaserScreenSizeCallback getlaserscreensizecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(513, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlaserscreensizecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLaserScreenSizeOptionState(getLaserScreenSizeOptionStateCallback getlaserscreensizeoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(HisHdmiApi.HD_EARC_AUDIO_STATUS, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlaserscreensizeoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLaserScreenType(getLaserScreenTypeCallback getlaserscreentypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(516, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlaserscreentypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLaserScreenTypeOptionState(getLaserScreenTypeOptionStateCallback getlaserscreentypeoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(517, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlaserscreentypeoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorALS10(getLightSensorALS10Callback getlightsensorals10callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(348, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorals10callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorALS300(getLightSensorALS300Callback getlightsensorals300callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(352, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorals300callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorALS80(getLightSensorALS80Callback getlightsensorals80callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(350, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorals80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorALSMax(getLightSensorALSMaxCallback getlightsensoralsmaxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(354, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensoralsmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorALSMin(getLightSensorALSMinCallback getlightsensoralsmincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(346, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensoralsmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorCurve(int i, int i2, getLightSensorCurveCallback getlightsensorcurvecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(453, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorcurvecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorEnable(getLightSensorEnableCallback getlightsensorenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(23, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorLS10(getLightSensorLS10Callback getlightsensorls10callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(358, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorls10callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorLS300(getLightSensorLS300Callback getlightsensorls300callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(362, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorls300callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorLS80(getLightSensorLS80Callback getlightsensorls80callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(360, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorls80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorLSMax(getLightSensorLSMaxCallback getlightsensorlsmaxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(364, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorlsmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorLSMin(getLightSensorLSMinCallback getlightsensorlsmincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(356, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorlsmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorLux(getLightSensorLuxCallback getlightsensorluxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(451, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorluxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorMax(getLightSensorMaxCallback getlightsensormaxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(27, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensormaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorMin(getLightSensorMinCallback getlightsensormincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(25, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensormincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorOptionState(getLightSensorOptionStateCallback getlightsensoroptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(302, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensoroptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorValue(getLightSensorValueCallback getlightsensorvaluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(319, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorvaluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLinkSoundEnable(getLinkSoundEnableCallback getlinksoundenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(337, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlinksoundenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLinkSoundOptionState(getLinkSoundOptionStateCallback getlinksoundoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(338, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlinksoundoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLocalDimming(getLocalDimmingCallback getlocaldimmingcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlocaldimmingcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLocalDimmingOptionState(getLocalDimmingOptionStateCallback getlocaldimmingoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(301, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlocaldimmingoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLowBlueLight(getLowBlueLightCallback getlowbluelightcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(17, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlowbluelightcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLowBlueLightOptionState(getLowBlueLightOptionStateCallback getlowbluelightoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(314, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlowbluelightoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMaximumBacklightOptionState(getMaximumBacklightOptionStateCallback getmaximumbacklightoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(431, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmaximumbacklightoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMemc(getMemcCallback getmemccallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(54, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmemccallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMemcBlurReduction(getMemcBlurReductionCallback getmemcblurreductioncallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(58, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmemcblurreductioncallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMemcJudderReduction(getMemcJudderReductionCallback getmemcjudderreductioncallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(56, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmemcjudderreductioncallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMemcOptionState(getMemcOptionStateCallback getmemcoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(303, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmemcoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMinimumBacklightOptionState(getMinimumBacklightOptionStateCallback getminimumbacklightoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(430, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getminimumbacklightoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMotionClearnessEnable(getMotionClearnessEnableCallback getmotionclearnessenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(60, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmotionclearnessenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMotionClearnessOptionState(getMotionClearnessOptionStateCallback getmotionclearnessoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(306, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmotionclearnessoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMpegNoiseReduction(getMpegNoiseReductionCallback getmpegnoisereductioncallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(66, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmpegnoisereductioncallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMultiPointWhiteBalanceApplyToAll(getMultiPointWhiteBalanceApplyToAllCallback getmultipointwhitebalanceapplytoallcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(106, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultipointwhitebalanceapplytoallcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMultiPointWhiteBalanceBlue(getMultiPointWhiteBalanceBlueCallback getmultipointwhitebalancebluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(104, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultipointwhitebalancebluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMultiPointWhiteBalanceEnable(getMultiPointWhiteBalanceEnableCallback getmultipointwhitebalanceenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(96, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultipointwhitebalanceenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMultiPointWhiteBalanceGreen(getMultiPointWhiteBalanceGreenCallback getmultipointwhitebalancegreencallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(102, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultipointwhitebalancegreencallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMultiPointWhiteBalanceLevel(getMultiPointWhiteBalanceLevelCallback getmultipointwhitebalancelevelcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(98, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultipointwhitebalancelevelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMultiPointWhiteBalanceOptionState(getMultiPointWhiteBalanceOptionStateCallback getmultipointwhitebalanceoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(467, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultipointwhitebalanceoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getMultiPointWhiteBalanceRed(getMultiPointWhiteBalanceRedCallback getmultipointwhitebalanceredcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(100, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultipointwhitebalanceredcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getNetVideoBeautySwitch(getNetVideoBeautySwitchCallback getnetvideobeautyswitchcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(476, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getnetvideobeautyswitchcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getNoiseReduction(getNoiseReductionCallback getnoisereductioncallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(64, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getnoisereductioncallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getNoiseReductionOptionState(getNoiseReductionOptionStateCallback getnoisereductionoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(StatusLine.HTTP_TEMP_REDIRECT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getnoisereductionoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDHDREnable(getOLEDHDREnableCallback getoledhdrenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(536, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledhdrenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDLEAEnable(getOLEDLEAEnableCallback getoledleaenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(532, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledleaenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDLocalFrozenChkEnable(getOLEDLocalFrozenChkEnableCallback getoledlocalfrozenchkenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(528, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledlocalfrozenchkenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDLocalLumaChkEnable(getOLEDLocalLumaChkEnableCallback getoledlocallumachkenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(530, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledlocallumachkenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDPLCCurve(getOLEDPLCCurveCallback getoledplccurvecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(526, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledplccurvecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDPLCCurveOptionState(getOLEDPLCCurveOptionStateCallback getoledplccurveoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(537, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledplccurveoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDTPCEnable(getOLEDTPCEnableCallback getoledtpcenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(29, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledtpcenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOptionStateColorTemperature(getOptionStateColorTemperatureCallback getoptionstatecolortemperaturecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(478, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoptionstatecolortemperaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOutMode(getOutModeCallback getoutmodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(295, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoutmodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanBottom(getOverscanBottomCallback getoverscanbottomcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(205, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscanbottomcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanEnable(getOverscanEnableCallback getoverscanenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(5, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscanenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanLeft(getOverscanLeftCallback getoverscanleftcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(HisenseCountryLanInfo.FAC_COUNTRY_KOR_INDEX, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscanleftcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanOptionState(getOverscanOptionStateCallback getoverscanoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(296, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscanoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanRight(getOverscanRightCallback getoverscanrightcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(HisenseCountryLanInfo.FAC_COUNTRY_LAO_INDEX, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscanrightcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanTop(getOverscanTopCallback getoverscantopcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(204, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscantopcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPanel(getPanelCallback getpanelcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(209, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpanelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPeaking(getPeakingCallback getpeakingcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(391, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpeakingcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPeakingOptionState(getPeakingOptionStateCallback getpeakingoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(392, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpeakingoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPictureMode(getPictureModeCallback getpicturemodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(37, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpicturemodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPictureModeByFactory(getPictureModeByFactoryCallback getpicturemodebyfactorycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(125, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpicturemodebyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPictureModeGameModeOptionState(getPictureModeGameModeOptionStateCallback getpicturemodegamemodeoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(313, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpicturemodegamemodeoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPictureSettingsToAllSource(getPictureSettingsToAllSourceCallback getpicturesettingstoallsourcecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(39, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpicturesettingstoallsourcecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPixelShiftEnable(getPixelShiftEnableCallback getpixelshiftenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(520, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpixelshiftenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPixelShiftShape(getPixelShiftShapeCallback getpixelshiftshapecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(35, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpixelshiftshapecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPixelShiftTimer(getPixelShiftTimerCallback getpixelshifttimercallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(33, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpixelshifttimercallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPixelShiftType(getPixelShiftTypeCallback getpixelshifttypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(31, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpixelshifttypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPixelShiftX(getPixelShiftXCallback getpixelshiftxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(522, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpixelshiftxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPixelShiftY(getPixelShiftYCallback getpixelshiftycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(524, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpixelshiftycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPqEnable(getPqEnableCallback getpqenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(247, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpqenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getQDCtrlPQUpdateEnable(getQDCtrlPQUpdateEnableCallback getqdctrlpqupdateenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(472, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getqdctrlpqupdateenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getRGBOnlyOptionState(getRGBOnlyOptionStateCallback getrgbonlyoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(425, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getrgbonlyoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getRgbOnly(getRgbOnlyCallback getrgbonlycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(120, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getrgbonlycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturation(getSaturationCallback getsaturationcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(46, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturation20(getSaturation20Callback getsaturation20callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_CHANNEL_LOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturation20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturation80(getSaturation80Callback getsaturation80callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_18_PLUS_LOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturation80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturationByFactory(getSaturationByFactoryCallback getsaturationbyfactorycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(133, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturationMax(getSaturationMaxCallback getsaturationmaxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CHAPTER_CHANGE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturationMid(getSaturationMidCallback getsaturationmidcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PIN_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturationMin(getSaturationMinCallback getsaturationmincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BAD_TICK, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturationOptionState(getSaturationOptionStateCallback getsaturationoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(491, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSceEnable(getSceEnableCallback getsceenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvScanDvbtBase.EXCHANGE_MAX_LEN, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsceenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getScreenOutputDispRect(getScreenOutputDispRectCallback getscreenoutputdisprectcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(383, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getscreenoutputdisprectcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpness(getSharpnessCallback getsharpnesscallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(48, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnesscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpness20(getSharpness20Callback getsharpness20callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(184, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpness20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpness80(getSharpness80Callback getsharpness80callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(186, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpness80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpnessByFactory(getSharpnessByFactoryCallback getsharpnessbyfactorycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(135, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnessbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpnessMax(getSharpnessMaxCallback getsharpnessmaxcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(HisenseCountryLanInfo.FAC_COUNTRY_GMB_INDEX, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnessmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpnessMid(getSharpnessMidCallback getsharpnessmidcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(185, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnessmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpnessMin(getSharpnessMinCallback getsharpnessmincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(183, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnessmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpnessOptionState(getSharpnessOptionStateCallback getsharpnessoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(439, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnessoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalAPL(getSignalAPLCallback getsignalaplcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(318, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalaplcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalCodecType(getSignalCodecTypeCallback getsignalcodectypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(262, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalcodectypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalColorDepth(getSignalColorDepthCallback getsignalcolordepthcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(261, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalcolordepthcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalColorFormat(getSignalColorFormatCallback getsignalcolorformatcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(HisHdmiApi.HD_IDX_UI_VRR_STATUS_4PORT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalcolorformatcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalColorSpace(getSignalColorSpaceCallback getsignalcolorspacecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(263, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalcolorspacecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalColorSystem(getSignalColorSystemCallback getsignalcolorsystemcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(264, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalcolorsystemcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalDRType(getSignalDRTypeCallback getsignaldrtypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(257, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignaldrtypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalFilmmakerType(getSignalFilmmakerTypeCallback getsignalfilmmakertypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(270, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalfilmmakertypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalFrameDelay(getSignalFrameDelayCallback getsignalframedelaycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(267, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalframedelaycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalIsDolby(getSignalIsDolbyCallback getsignalisdolbycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(266, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalisdolbycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalIsUHD(getSignalIsUHDCallback getsignalisuhdcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(265, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalisuhdcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalRatio(getSignalRatioCallback getsignalratiocallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(HisHdmiApi.HD_IDX_UI_VRR_STATUS_2PORT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalratiocallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalVideoFormat(getSignalVideoFormatCallback getsignalvideoformatcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(HisHdmiApi.HD_IDX_UI_VRR_STATUS_3PORT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalvideoformatcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getTVSModel(getTVSModelCallback gettvsmodelcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(474, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gettvsmodelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getTint(getTintCallback gettintcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(50, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gettintcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getTintByFactory(getTintByFactoryCallback gettintbyfactorycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(137, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gettintbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getUsageMode(getUsageModeCallback getusagemodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(278, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getusagemodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getVRRPlusOptionState(getVRRPlusOptionStateCallback getvrrplusoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(FrameMetricsAggregator.EVERY_DURATION, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getvrrplusoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getVcomTestEnable(getVcomTestEnableCallback getvcomtestenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(243, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getvcomtestenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getVideoBeautySwitchOptionState(getVideoBeautySwitchOptionStateCallback getvideobeautyswitchoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(483, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getvideobeautyswitchoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getVideoMuteState(getVideoMuteStateCallback getvideomutestatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(293, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getvideomutestatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteBalanceGainBlue(getWhiteBalanceGainBlueCallback getwhitebalancegainbluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(91, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhitebalancegainbluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteBalanceGainGreen(getWhiteBalanceGainGreenCallback getwhitebalancegaingreencallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(89, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhitebalancegaingreencallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteBalanceGainRed(getWhiteBalanceGainRedCallback getwhitebalancegainredcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(87, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhitebalancegainredcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteBalanceOffsetBlue(getWhiteBalanceOffsetBlueCallback getwhitebalanceoffsetbluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(85, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhitebalanceoffsetbluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteBalanceOffsetGreen(getWhiteBalanceOffsetGreenCallback getwhitebalanceoffsetgreencallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(83, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhitebalanceoffsetgreencallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteBalanceOffsetRed(getWhiteBalanceOffsetRedCallback getwhitebalanceoffsetredcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(81, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhitebalanceoffsetredcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteBalanceOptionState(getWhiteBalanceOptionStateCallback getwhitebalanceoptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(466, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhitebalanceoptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteBalanceiApplyToAll(getWhiteBalanceiApplyToAllCallback getwhitebalanceiapplytoallcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(93, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhitebalanceiapplytoallcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteLevel(getWhiteLevelCallback getwhitelevelcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(496, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhitelevelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWhiteLevelOptionState(getWhiteLevelOptionStateCallback getwhiteleveloptionstatecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(499, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwhiteleveloptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getWindowHSLHist(getWindowHSLHistCallback getwindowhslhistcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(471, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwindowhslhistcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector(), hwParcel2.readInt32Vector(), hwParcel2.readInt32Vector(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getXvyccEnable(getXvyccEnableCallback getxvyccenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(253, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getxvyccenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int initLightSensor(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(320, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public ArrayList<String> interfaceChain() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public String interfaceDescriptor() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isDolby(isDolbyCallback isdolbycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(285, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                isdolbycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isDolbyBrightness(isDolbyBrightnessCallback isdolbybrightnesscallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(287, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                isdolbybrightnesscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isDolbyCustom(isDolbyCustomCallback isdolbycustomcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(288, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                isdolbycustomcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isDolbyDark(isDolbyDarkCallback isdolbydarkcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(286, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                isdolbydarkcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isDoubleDispalyMemc(isDoubleDispalyMemcCallback isdoubledispalymemccallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(479, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                isdoubledispalymemccallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isGame(isGameCallback isgamecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(465, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                isgamecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isHDR(isHDRCallback ishdrcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(289, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                ishdrcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isHDR10Plus(isHDR10PlusCallback ishdr10pluscallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(290, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                ishdr10pluscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isOLEDModel(isOLEDModelCallback isoledmodelcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(475, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                isoledmodelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isSignalPlaying(isSignalPlayingCallback issignalplayingcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(291, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                issignalplayingcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void isTVSModel(isTVSModelCallback istvsmodelcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(473, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                istvsmodelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public void notifySyspropsChanged() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(257120595, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public void ping() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256921159, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int resetAllFac2PWhiteBalance(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(410, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int resetPictureSettings(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(40, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int resetStoreExpertData(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(420, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int restorePQSetting() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(418, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int saveStoreExpertData(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(419, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int screenCapture(String str, int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeString(str);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(405, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBGainBlueDelta(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(403, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBGainGreenDelta(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(401, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBGainRedDelta(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(399, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBOffsetBlueDelta(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(397, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBOffsetGreenDelta(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(395, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBOffsetRedDelta(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(393, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAIPQDetectSwitch(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(422, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAPOContentCurve(int i, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(456, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAPOEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(460, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAPOWBCurve(int i, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(458, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setActiveContrast(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAgingModeEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(244, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAspectRatio(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAudioOnlyEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(12, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAutoFilmmakerModeStatus(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(273, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAutoScanMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(406, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklight20(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(189, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklight80(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(191, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightMax(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(192, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightMid(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(190, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightMin(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(188, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightPWMFreq(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(GpsStatusWrapper.QZSS_SVID_MIN, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightPercent(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(10, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightPercentByFactory(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(126, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBenqITFReserved1(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(375, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBenqITFReserved2(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(377, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBenqITFReserved3(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(379, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBenqLowBlueLight(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(18, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBlackLevel(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(497, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBlueStretchEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(20, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBootLogoState(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(281, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightness(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(43, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightness20(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(139, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightness80(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(141, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightnessByFactory(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(130, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightnessMax(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(142, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightnessMid(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(140, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightnessMin(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(138, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBypassPqEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(388, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setCalMANEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(504, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setCalMANTuningEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(507, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setClrTempCompenCurve(int i, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(486, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setClrTempCompenEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(484, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setColorSpace(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(108, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setColorTemperature(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setColorTunerApplyToAll(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(77, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setColorTunerBrightness(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(71, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setColorTunerColor(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(69, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setColorTunerHue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(75, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setColorTunerSaturation(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(73, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrast(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(41, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrast20(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_ES_LIST, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrast80(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(151, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrastByFactory(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(128, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrastMax(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(152, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrastMid(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(150, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrastMin(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_SOURCE_READY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDMPPlayPhotoMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(279, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDolbyIQSensorFrontLux(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(443, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDolbyIQSensorTearLum(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(444, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDolbyIQSensorWeight(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(442, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDolbyIQSensorWhiteX(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(445, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDolbyIQSensorWhiteY(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(446, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDolbyIQUserBinEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(447, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDolbyIQUserBinUpdate(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(StorageTask.STATES_COMPLETE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDynamicBacklight(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDynamicContrast(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(480, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDynamicToneMappingEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(501, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setEPOSEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(464, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setEnergyLoopLogMask(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(316, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setEnergySavingEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setEnhancedViewingAngleEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFTEState(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(283, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTemperature(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(210, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTunerBrightness(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(236, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTunerColor(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(234, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTunerHue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(240, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTunerSaturation(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(238, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryMultiPointWhiteBalanceBlue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(230, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryMultiPointWhiteBalanceGreen(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(228, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryMultiPointWhiteBalanceLevel(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(224, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryMultiPointWhiteBalanceRed(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(226, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceGainBlue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(222, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceGainGreen(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(FragmentManagerImpl.ANIM_DUR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceGainRed(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(218, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceOffsetBlue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(216, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceOffsetGreen(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(214, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceOffsetRed(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(212, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFilmmakerDetectionEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(268, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFilmmakerModeSelected(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(271, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFreeSyncSwitch(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(436, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setGameDetectionEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(275, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setGammaAdjustment(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(112, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setGammaCalibrationGain(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(116, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setGammaCalibrationLevel(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(114, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setGammaEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(248, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public void setHALInstrumentation() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256462420, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHDMI20Format(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(121, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHdmiDynamicRange(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHue20(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(169, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHue80(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_TIME_UPDATE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHueMax(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UNDERFLOW, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHueMid(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(170, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHueMin(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setISFSwitch(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(339, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setISFType(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(342, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setInsidePattern(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(233, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setInstantGameResponseSwitch(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(433, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setItemData(int i, ArrayList<Integer> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(408, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setItemString(int i, String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(413, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setItemTable(int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(493, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setItemValue(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(415, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void setItemValueAsync(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(534, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLDTestEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(539, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLaserScreenSize(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(512, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLaserScreenType(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(515, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorALS10(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(347, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorALS300(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(351, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorALS80(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(349, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorALSMax(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(353, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorALSMin(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(345, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorCurve(int i, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(452, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorLS10(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(357, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorLS300(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(361, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorLS80(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(359, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorLSMax(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(363, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorLSMin(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(355, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorMax(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorMin(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(24, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLinkSoundEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(336, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLocalDimming(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLowBlueLight(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(16, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMemc(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(53, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMemcBlurReduction(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMemcJudderReduction(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(55, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMotionClearnessEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMpegNoiseReduction(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMultiPointWhiteBalanceApplyToAll(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(105, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMultiPointWhiteBalanceBlue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(103, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMultiPointWhiteBalanceEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(95, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMultiPointWhiteBalanceGreen(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(101, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMultiPointWhiteBalanceLevel(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(97, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setMultiPointWhiteBalanceRed(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(99, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setNetVideoBeautySwitch(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(477, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setNoiseReduction(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDHDREnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(535, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDLEAEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(531, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDLocalFrozenChkEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(527, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDLocalLumaChkEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(529, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDPLCCurve(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(525, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDTPCEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(28, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOutMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(294, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOverscanBottom(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(201, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOverscanEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOverscanLeft(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(202, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOverscanRight(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(203, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOverscanTop(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(200, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPanel(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(208, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPeaking(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(390, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPictureMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(36, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPictureModeByFactory(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(124, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPictureSettingsToAllSource(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(38, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPixelShiftEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(519, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPixelShiftShape(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(34, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPixelShiftTimer(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(32, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPixelShiftType(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(30, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPixelShiftX(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(521, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPixelShiftY(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(523, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPqEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(246, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setRGBPattern(int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(421, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setRgbOnly(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_NO_CONTENT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturation(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(45, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturation20(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(159, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturation80(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturationByFactory(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(132, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturationMax(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INTERNAL_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturationMid(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(160, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturationMin(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_FULL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSceEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(250, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setScreenOutputDispRect(int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(382, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpness(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(47, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpness20(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_FULL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpness80(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(181, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpnessByFactory(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(134, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpnessMax(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(182, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpnessMid(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(180, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpnessMin(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_NUM_READY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setStandyScreenStatus(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(488, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setTestPattern(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(232, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setTint(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setTintByFactory(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(136, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setTvsCtrlQDPQUpdateEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(482, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setUsageMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(277, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setVcomTestEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(242, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setVideoMuteState(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(292, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setWhiteBalanceGainBlue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(90, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setWhiteBalanceGainGreen(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(88, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setWhiteBalanceGainRed(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(86, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setWhiteBalanceOffsetBlue(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(84, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setWhiteBalanceOffsetGreen(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(82, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setWhiteBalanceOffsetRed(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(80, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setWhiteBalanceiApplyToAll(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(92, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setWhiteLevel(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(495, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setWindowHSLRange(int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(470, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setXvyccEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(252, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetBacklightLinearEnable(testGetBacklightLinearEnableCallback testgetbacklightlinearenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(370, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetbacklightlinearenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetColorTempCompenEnable(testGetColorTempCompenEnableCallback testgetcolortempcompenenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(366, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetcolortempcompenenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetColorTempCompenGain(testGetColorTempCompenGainCallback testgetcolortempcompengaincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(368, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetcolortempcompengaincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetDCDimmingEnable(testGetDCDimmingEnableCallback testgetdcdimmingenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(374, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetdcdimmingenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetGammaIndex(testGetGammaIndexCallback testgetgammaindexcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(387, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetgammaindexcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetGammaType(testGetGammaTypeCallback testgetgammatypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(412, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetgammatypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetOutputRegion(testGetOutputRegionCallback testgetoutputregioncallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(332, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetoutputregioncallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetOverscanIndex(testGetOverscanIndexCallback testgetoverscanindexcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(325, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetoverscanindexcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetOverscanRatio(testGetOverscanRatioCallback testgetoverscanratiocallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(334, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetoverscanratiocallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetSCEIndex(testGetSCEIndexCallback testgetsceindexcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(385, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetsceindexcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetSignalDRType(testGetSignalDRTypeCallback testgetsignaldrtypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(322, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetsignaldrtypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetSrouceRegion(testGetSrouceRegionCallback testgetsrouceregioncallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(330, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetsrouceregioncallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetUseRawDataEnable(testGetUseRawDataEnableCallback testgetuserawdataenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(328, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetuserawdataenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void testGetbacklightLinearGain(testGetbacklightLinearGainCallback testgetbacklightlineargaincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(372, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                testgetbacklightlineargaincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetAPOContentType(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(462, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetAPOWBType(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(463, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetBacklightLinearEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(369, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetBacklightLinearGain(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(371, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetColorTempCompenEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(365, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetColorTempCompenGain(int i, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(367, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetDCDimmingEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(373, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetGammaIndex(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(386, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetGammaType(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(411, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetOutputRegion(int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(331, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetOverscanIndex(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(324, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetOverscanRatio(int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(333, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetSCEIndex(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(384, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetSignalDRType(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(323, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetSignalFilmmakerType(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(326, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetSourceRegion(int i, int i2, int i3, int i4) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(329, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int testSetUseRawDataEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisVideoApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(327, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi]@Proxy";
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IHisVideoApi {
        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{5, -40, 62, 59, -22, 96, -21, -123, -9, 108, 5, -106, 115, -106, -71, 63, -102, 85, 103, -49, -122, -33, -22, -33, 76, 0, 93, 99, -34, Ascii.NAK, -78, 47}, new byte[]{-20, Ascii.DEL, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, Ascii.CAN, -54, 76}));
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IHisVideoApi.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IHisVideoApi.kInterfaceName;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public void onTransact(int i, HwParcel hwParcel, final HwParcel hwParcel2, int i2) {
            switch (i) {
                case 1:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetAllUserSettings = ResetAllUserSettings(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetAllUserSettings);
                    hwParcel2.send();
                    return;
                case 2:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int aspectRatio = setAspectRatio(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(aspectRatio);
                    hwParcel2.send();
                    return;
                case 3:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAspectRatio(new getAspectRatioCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.1
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 4:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int overscanEnable = setOverscanEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(overscanEnable);
                    hwParcel2.send();
                    return;
                case 5:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOverscanEnable(new getOverscanEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.2
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 6:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int localDimming = setLocalDimming(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(localDimming);
                    hwParcel2.send();
                    return;
                case 7:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLocalDimming(new getLocalDimmingCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.3
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLocalDimmingCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 8:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dynamicBacklight = setDynamicBacklight(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dynamicBacklight);
                    hwParcel2.send();
                    return;
                case 9:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDynamicBacklight(new getDynamicBacklightCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.4
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicBacklightCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 10:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int backlightPercent = setBacklightPercent(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(backlightPercent);
                    hwParcel2.send();
                    return;
                case 11:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBacklightPercent(new getBacklightPercentCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.5
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightPercentCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 12:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int audioOnlyEnable = setAudioOnlyEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(audioOnlyEnable);
                    hwParcel2.send();
                    return;
                case 13:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAudioOnlyEnable(new getAudioOnlyEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.6
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAudioOnlyEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 14:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int energySavingEnable = setEnergySavingEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(energySavingEnable);
                    hwParcel2.send();
                    return;
                case 15:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getEnergySavingEnable(new getEnergySavingEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.7
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getEnergySavingEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 16:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lowBlueLight = setLowBlueLight(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lowBlueLight);
                    hwParcel2.send();
                    return;
                case 17:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLowBlueLight(new getLowBlueLightCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.8
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLowBlueLightCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 18:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int benqLowBlueLight = setBenqLowBlueLight(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(benqLowBlueLight);
                    hwParcel2.send();
                    return;
                case 19:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBenqLowBlueLight(new getBenqLowBlueLightCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.9
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBenqLowBlueLightCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 20:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int blueStretchEnable = setBlueStretchEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(blueStretchEnable);
                    hwParcel2.send();
                    return;
                case 21:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBlueStretchEnable(new getBlueStretchEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.10
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBlueStretchEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 22:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorEnable = setLightSensorEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorEnable);
                    hwParcel2.send();
                    return;
                case 23:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorEnable(new getLightSensorEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.11
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 24:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorMin = setLightSensorMin(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorMin);
                    hwParcel2.send();
                    return;
                case 25:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorMin(new getLightSensorMinCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.12
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorMinCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 26:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorMax = setLightSensorMax(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorMax);
                    hwParcel2.send();
                    return;
                case 27:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorMax(new getLightSensorMaxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.13
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorMaxCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 28:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int oLEDTPCEnable = setOLEDTPCEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(oLEDTPCEnable);
                    hwParcel2.send();
                    return;
                case 29:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOLEDTPCEnable(new getOLEDTPCEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.14
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDTPCEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 30:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pixelShiftType = setPixelShiftType(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pixelShiftType);
                    hwParcel2.send();
                    return;
                case 31:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPixelShiftType(new getPixelShiftTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.15
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftTypeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 32:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pixelShiftTimer = setPixelShiftTimer(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pixelShiftTimer);
                    hwParcel2.send();
                    return;
                case 33:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPixelShiftTimer(new getPixelShiftTimerCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.16
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftTimerCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 34:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pixelShiftShape = setPixelShiftShape(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pixelShiftShape);
                    hwParcel2.send();
                    return;
                case 35:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPixelShiftShape(new getPixelShiftShapeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.17
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftShapeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 36:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pictureMode = setPictureMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pictureMode);
                    hwParcel2.send();
                    return;
                case 37:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPictureMode(new getPictureModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.18
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPictureModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 38:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pictureSettingsToAllSource = setPictureSettingsToAllSource(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pictureSettingsToAllSource);
                    hwParcel2.send();
                    return;
                case 39:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPictureSettingsToAllSource(new getPictureSettingsToAllSourceCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.19
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPictureSettingsToAllSourceCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 40:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetPictureSettings = resetPictureSettings(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetPictureSettings);
                    hwParcel2.send();
                    return;
                case 41:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int contrast = setContrast(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(contrast);
                    hwParcel2.send();
                    return;
                case 42:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getContrast(new getContrastCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.20
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 43:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int brightness = setBrightness(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(brightness);
                    hwParcel2.send();
                    return;
                case 44:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBrightness(new getBrightnessCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.21
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 45:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int saturation = setSaturation(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(saturation);
                    hwParcel2.send();
                    return;
                case 46:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSaturation(new getSaturationCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.22
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 47:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int sharpness = setSharpness(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(sharpness);
                    hwParcel2.send();
                    return;
                case 48:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSharpness(new getSharpnessCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.23
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 49:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int tint = setTint(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(tint);
                    hwParcel2.send();
                    return;
                case 50:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getTint(new getTintCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.24
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getTintCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 51:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int activeContrast = setActiveContrast(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(activeContrast);
                    hwParcel2.send();
                    return;
                case 52:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getActiveContrast(new getActiveContrastCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.25
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getActiveContrastCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 53:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int memc = setMemc(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(memc);
                    hwParcel2.send();
                    return;
                case 54:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMemc(new getMemcCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.26
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMemcCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 55:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int memcJudderReduction = setMemcJudderReduction(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(memcJudderReduction);
                    hwParcel2.send();
                    return;
                case 56:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMemcJudderReduction(new getMemcJudderReductionCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.27
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMemcJudderReductionCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 57:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int memcBlurReduction = setMemcBlurReduction(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(memcBlurReduction);
                    hwParcel2.send();
                    return;
                case 58:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMemcBlurReduction(new getMemcBlurReductionCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.28
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMemcBlurReductionCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 59:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int motionClearnessEnable = setMotionClearnessEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(motionClearnessEnable);
                    hwParcel2.send();
                    return;
                case 60:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMotionClearnessEnable(new getMotionClearnessEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.29
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMotionClearnessEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 61:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int enhancedViewingAngleEnable = setEnhancedViewingAngleEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(enhancedViewingAngleEnable);
                    hwParcel2.send();
                    return;
                case 62:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getEnhancedViewingAngleEnable(new getEnhancedViewingAngleEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.30
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getEnhancedViewingAngleEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 63:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int noiseReduction = setNoiseReduction(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(noiseReduction);
                    hwParcel2.send();
                    return;
                case 64:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getNoiseReduction(new getNoiseReductionCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.31
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getNoiseReductionCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 65:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int mpegNoiseReduction = setMpegNoiseReduction(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(mpegNoiseReduction);
                    hwParcel2.send();
                    return;
                case 66:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMpegNoiseReduction(new getMpegNoiseReductionCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.32
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMpegNoiseReductionCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 67:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int colorTemperature = setColorTemperature(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(colorTemperature);
                    hwParcel2.send();
                    return;
                case 68:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getColorTemperature(new getColorTemperatureCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.33
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTemperatureCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 69:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int colorTunerColor = setColorTunerColor(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(colorTunerColor);
                    hwParcel2.send();
                    return;
                case 70:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getColorTunerColor(new getColorTunerColorCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.34
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerColorCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 71:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int colorTunerBrightness = setColorTunerBrightness(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(colorTunerBrightness);
                    hwParcel2.send();
                    return;
                case 72:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getColorTunerBrightness(new getColorTunerBrightnessCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.35
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerBrightnessCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 73:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int colorTunerSaturation = setColorTunerSaturation(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(colorTunerSaturation);
                    hwParcel2.send();
                    return;
                case 74:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getColorTunerSaturation(new getColorTunerSaturationCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.36
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerSaturationCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 75:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int colorTunerHue = setColorTunerHue(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(colorTunerHue);
                    hwParcel2.send();
                    return;
                case 76:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getColorTunerHue(new getColorTunerHueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.37
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerHueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 77:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int colorTunerApplyToAll = setColorTunerApplyToAll(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(colorTunerApplyToAll);
                    hwParcel2.send();
                    return;
                case 78:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getColorTunerApplyToAll(new getColorTunerApplyToAllCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.38
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerApplyToAllCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 79:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetColorTuner = ResetColorTuner(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetColorTuner);
                    hwParcel2.send();
                    return;
                case 80:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int whiteBalanceOffsetRed = setWhiteBalanceOffsetRed(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(whiteBalanceOffsetRed);
                    hwParcel2.send();
                    return;
                case 81:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteBalanceOffsetRed(new getWhiteBalanceOffsetRedCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.39
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceOffsetRedCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 82:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int whiteBalanceOffsetGreen = setWhiteBalanceOffsetGreen(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(whiteBalanceOffsetGreen);
                    hwParcel2.send();
                    return;
                case 83:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteBalanceOffsetGreen(new getWhiteBalanceOffsetGreenCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.40
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceOffsetGreenCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 84:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int whiteBalanceOffsetBlue = setWhiteBalanceOffsetBlue(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(whiteBalanceOffsetBlue);
                    hwParcel2.send();
                    return;
                case 85:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteBalanceOffsetBlue(new getWhiteBalanceOffsetBlueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.41
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceOffsetBlueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 86:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int whiteBalanceGainRed = setWhiteBalanceGainRed(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(whiteBalanceGainRed);
                    hwParcel2.send();
                    return;
                case 87:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteBalanceGainRed(new getWhiteBalanceGainRedCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.42
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceGainRedCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 88:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int whiteBalanceGainGreen = setWhiteBalanceGainGreen(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(whiteBalanceGainGreen);
                    hwParcel2.send();
                    return;
                case 89:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteBalanceGainGreen(new getWhiteBalanceGainGreenCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.43
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceGainGreenCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 90:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int whiteBalanceGainBlue = setWhiteBalanceGainBlue(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(whiteBalanceGainBlue);
                    hwParcel2.send();
                    return;
                case 91:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteBalanceGainBlue(new getWhiteBalanceGainBlueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.44
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceGainBlueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 92:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int whiteBalanceiApplyToAll = setWhiteBalanceiApplyToAll(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(whiteBalanceiApplyToAll);
                    hwParcel2.send();
                    return;
                case 93:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteBalanceiApplyToAll(new getWhiteBalanceiApplyToAllCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.45
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceiApplyToAllCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 94:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetWhiteBalance = ResetWhiteBalance(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetWhiteBalance);
                    hwParcel2.send();
                    return;
                case 95:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int multiPointWhiteBalanceEnable = setMultiPointWhiteBalanceEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(multiPointWhiteBalanceEnable);
                    hwParcel2.send();
                    return;
                case 96:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMultiPointWhiteBalanceEnable(new getMultiPointWhiteBalanceEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.46
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 97:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int multiPointWhiteBalanceLevel = setMultiPointWhiteBalanceLevel(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(multiPointWhiteBalanceLevel);
                    hwParcel2.send();
                    return;
                case 98:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMultiPointWhiteBalanceLevel(new getMultiPointWhiteBalanceLevelCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.47
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceLevelCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 99:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int multiPointWhiteBalanceRed = setMultiPointWhiteBalanceRed(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(multiPointWhiteBalanceRed);
                    hwParcel2.send();
                    return;
                case 100:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMultiPointWhiteBalanceRed(new getMultiPointWhiteBalanceRedCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.48
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceRedCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 101:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int multiPointWhiteBalanceGreen = setMultiPointWhiteBalanceGreen(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(multiPointWhiteBalanceGreen);
                    hwParcel2.send();
                    return;
                case 102:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMultiPointWhiteBalanceGreen(new getMultiPointWhiteBalanceGreenCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.49
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceGreenCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 103:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int multiPointWhiteBalanceBlue = setMultiPointWhiteBalanceBlue(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(multiPointWhiteBalanceBlue);
                    hwParcel2.send();
                    return;
                case 104:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMultiPointWhiteBalanceBlue(new getMultiPointWhiteBalanceBlueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.50
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceBlueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 105:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int multiPointWhiteBalanceApplyToAll = setMultiPointWhiteBalanceApplyToAll(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(multiPointWhiteBalanceApplyToAll);
                    hwParcel2.send();
                    return;
                case 106:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMultiPointWhiteBalanceApplyToAll(new getMultiPointWhiteBalanceApplyToAllCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.51
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceApplyToAllCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 107:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetMultiPointWhiteBalance = ResetMultiPointWhiteBalance(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetMultiPointWhiteBalance);
                    hwParcel2.send();
                    return;
                case 108:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int colorSpace = setColorSpace(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(colorSpace);
                    hwParcel2.send();
                    return;
                case 109:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getColorSpace(new getColorSpaceCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.52
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorSpaceCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END /* 110 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int hdmiDynamicRange = setHdmiDynamicRange(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(hdmiDynamicRange);
                    hwParcel2.send();
                    return;
                case 111:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getHdmiDynamicRange(new getHdmiDynamicRangeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.53
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHdmiDynamicRangeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 112:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int gammaAdjustment = setGammaAdjustment(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(gammaAdjustment);
                    hwParcel2.send();
                    return;
                case 113:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getGammaAdjustment(new getGammaAdjustmentCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.54
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaAdjustmentCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 114:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int gammaCalibrationLevel = setGammaCalibrationLevel(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(gammaCalibrationLevel);
                    hwParcel2.send();
                    return;
                case 115:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getGammaCalibrationLevel(new getGammaCalibrationLevelCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.55
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaCalibrationLevelCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 116:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int gammaCalibrationGain = setGammaCalibrationGain(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(gammaCalibrationGain);
                    hwParcel2.send();
                    return;
                case 117:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getGammaCalibrationGain(new getGammaCalibrationGainCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.56
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaCalibrationGainCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 118:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetGammaCalibration = ResetGammaCalibration(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetGammaCalibration);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_NO_CONTENT /* 119 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int rgbOnly = setRgbOnly(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(rgbOnly);
                    hwParcel2.send();
                    return;
                case 120:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getRgbOnly(new getRgbOnlyCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.57
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getRgbOnlyCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 121:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int hDMI20Format = setHDMI20Format(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(hDMI20Format);
                    hwParcel2.send();
                    return;
                case 122:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getHDMI20Format(new getHDMI20FormatCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.58
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHDMI20FormatCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 123:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetAllFacSettings = ResetAllFacSettings(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetAllFacSettings);
                    hwParcel2.send();
                    return;
                case 124:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pictureModeByFactory = setPictureModeByFactory(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pictureModeByFactory);
                    hwParcel2.send();
                    return;
                case 125:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPictureModeByFactory(new getPictureModeByFactoryCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.59
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPictureModeByFactoryCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 126:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int backlightPercentByFactory = setBacklightPercentByFactory(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(backlightPercentByFactory);
                    hwParcel2.send();
                    return;
                case 127:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBacklightPercentByFactory(new getBacklightPercentByFactoryCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.60
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightPercentByFactoryCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 128:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int contrastByFactory = setContrastByFactory(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(contrastByFactory);
                    hwParcel2.send();
                    return;
                case 129:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getContrastByFactory(new getContrastByFactoryCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.61
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastByFactoryCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 130:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int brightnessByFactory = setBrightnessByFactory(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(brightnessByFactory);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB /* 131 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBrightnessByFactory(new getBrightnessByFactoryCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.62
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessByFactoryCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 132:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int saturationByFactory = setSaturationByFactory(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(saturationByFactory);
                    hwParcel2.send();
                    return;
                case 133:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSaturationByFactory(new getSaturationByFactoryCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.63
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationByFactoryCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 134:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int sharpnessByFactory = setSharpnessByFactory(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(sharpnessByFactory);
                    hwParcel2.send();
                    return;
                case 135:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSharpnessByFactory(new getSharpnessByFactoryCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.64
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessByFactoryCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 136:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int tintByFactory = setTintByFactory(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(tintByFactory);
                    hwParcel2.send();
                    return;
                case 137:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getTintByFactory(new getTintByFactoryCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.65
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getTintByFactoryCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 138:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int brightnessMin = setBrightnessMin(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(brightnessMin);
                    hwParcel2.send();
                    return;
                case 139:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int brightness20 = setBrightness20(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(brightness20);
                    hwParcel2.send();
                    return;
                case 140:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int brightnessMid = setBrightnessMid(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(brightnessMid);
                    hwParcel2.send();
                    return;
                case 141:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int brightness80 = setBrightness80(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(brightness80);
                    hwParcel2.send();
                    return;
                case 142:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int brightnessMax = setBrightnessMax(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(brightnessMax);
                    hwParcel2.send();
                    return;
                case 143:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBrightnessMin(new getBrightnessMinCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.66
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessMinCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 144:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBrightness20(new getBrightness20Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.67
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightness20Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STARTED /* 145 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBrightnessMid(new getBrightnessMidCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.68
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessMidCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STOPPED /* 146 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBrightness80(new getBrightness80Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.69
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightness80Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 147:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBrightnessMax(new getBrightnessMaxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.70
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessMaxCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_SOURCE_READY /* 148 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int contrastMin = setContrastMin(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(contrastMin);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_ES_LIST /* 149 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int contrast20 = setContrast20(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(contrast20);
                    hwParcel2.send();
                    return;
                case 150:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int contrastMid = setContrastMid(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(contrastMid);
                    hwParcel2.send();
                    return;
                case 151:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int contrast80 = setContrast80(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(contrast80);
                    hwParcel2.send();
                    return;
                case 152:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int contrastMax = setContrastMax(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(contrastMax);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND /* 153 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getContrastMin(new getContrastMinCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.71
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastMinCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 154:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getContrast20(new getContrast20Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.72
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrast20Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL /* 155 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getContrastMid(new getContrastMidCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.73
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastMidCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 156:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getContrast80(new getContrast80Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.74
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrast80Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BLOCKED /* 157 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getContrastMax(new getContrastMaxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.75
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastMaxCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_FULL /* 158 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int saturationMin = setSaturationMin(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(saturationMin);
                    hwParcel2.send();
                    return;
                case 159:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int saturation20 = setSaturation20(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(saturation20);
                    hwParcel2.send();
                    return;
                case 160:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int saturationMid = setSaturationMid(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(saturationMid);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_ERROR /* 161 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int saturation80 = setSaturation80(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(saturation80);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INTERNAL_ERROR /* 162 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int saturationMax = setSaturationMax(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(saturationMax);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BAD_TICK /* 163 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSaturationMin(new getSaturationMinCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.76
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationMinCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_CHANNEL_LOCKED /* 164 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSaturation20(new getSaturation20Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.77
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturation20Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PIN_ERROR /* 165 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSaturationMid(new getSaturationMidCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.78
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationMidCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_18_PLUS_LOCKED /* 166 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSaturation80(new getSaturation80Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.79
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturation80Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CHAPTER_CHANGE /* 167 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSaturationMax(new getSaturationMaxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.80
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationMaxCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ /* 168 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int hueMin = setHueMin(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(hueMin);
                    hwParcel2.send();
                    return;
                case 169:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int hue20 = setHue20(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(hue20);
                    hwParcel2.send();
                    return;
                case 170:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int hueMid = setHueMid(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(hueMid);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_TIME_UPDATE /* 171 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int hue80 = setHue80(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(hue80);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UNDERFLOW /* 172 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int hueMax = setHueMax(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(hueMax);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_EOF /* 173 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getHueMin(new getHueMinCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.81
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueMinCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF /* 174 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getHue20(new getHue20Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.82
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHue20Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE /* 175 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getHueMid(new getHueMidCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.83
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueMidCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME /* 176 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getHue80(new getHue80Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.84
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHue80Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_STREAM_OPENED /* 177 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getHueMax(new getHueMaxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.85
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueMaxCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_NUM_READY /* 178 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int sharpnessMin = setSharpnessMin(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(sharpnessMin);
                    hwParcel2.send();
                    return;
                case MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_FULL /* 179 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int sharpness20 = setSharpness20(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(sharpness20);
                    hwParcel2.send();
                    return;
                case 180:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int sharpnessMid = setSharpnessMid(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(sharpnessMid);
                    hwParcel2.send();
                    return;
                case 181:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int sharpness80 = setSharpness80(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(sharpness80);
                    hwParcel2.send();
                    return;
                case 182:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int sharpnessMax = setSharpnessMax(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(sharpnessMax);
                    hwParcel2.send();
                    return;
                case 183:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSharpnessMin(new getSharpnessMinCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.86
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessMinCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 184:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSharpness20(new getSharpness20Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.87
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpness20Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 185:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSharpnessMid(new getSharpnessMidCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.88
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessMidCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 186:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSharpness80(new getSharpness80Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.89
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpness80Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case HisenseCountryLanInfo.FAC_COUNTRY_GMB_INDEX /* 187 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSharpnessMax(new getSharpnessMaxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.90
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessMaxCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 188:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int backlightMin = setBacklightMin(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(backlightMin);
                    hwParcel2.send();
                    return;
                case 189:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int backlight20 = setBacklight20(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(backlight20);
                    hwParcel2.send();
                    return;
                case 190:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int backlightMid = setBacklightMid(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(backlightMid);
                    hwParcel2.send();
                    return;
                case 191:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int backlight80 = setBacklight80(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(backlight80);
                    hwParcel2.send();
                    return;
                case 192:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int backlightMax = setBacklightMax(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(backlightMax);
                    hwParcel2.send();
                    return;
                case GpsStatusWrapper.QZSS_SVID_MIN /* 193 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int backlightPWMFreq = setBacklightPWMFreq(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(backlightPWMFreq);
                    hwParcel2.send();
                    return;
                case 194:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBacklightMin(new getBacklightMinCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.91
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightMinCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 195:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBacklight20(new getBacklight20Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.92
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklight20Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 196:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBacklightMid(new getBacklightMidCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.93
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightMidCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 197:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBacklight80(new getBacklight80Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.94
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklight80Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 198:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBacklightMax(new getBacklightMaxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.95
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightMaxCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 199:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBacklightPWMFreq(new getBacklightPWMFreqCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.96
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightPWMFreqCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 200:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int overscanTop = setOverscanTop(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(overscanTop);
                    hwParcel2.send();
                    return;
                case 201:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int overscanBottom = setOverscanBottom(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(overscanBottom);
                    hwParcel2.send();
                    return;
                case 202:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int overscanLeft = setOverscanLeft(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(overscanLeft);
                    hwParcel2.send();
                    return;
                case 203:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int overscanRight = setOverscanRight(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(overscanRight);
                    hwParcel2.send();
                    return;
                case 204:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOverscanTop(new getOverscanTopCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.97
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanTopCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 205:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOverscanBottom(new getOverscanBottomCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.98
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanBottomCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case HisenseCountryLanInfo.FAC_COUNTRY_KOR_INDEX /* 206 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOverscanLeft(new getOverscanLeftCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.99
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanLeftCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case HisenseCountryLanInfo.FAC_COUNTRY_LAO_INDEX /* 207 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOverscanRight(new getOverscanRightCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.100
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanRightCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 208:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int panel = setPanel(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(panel);
                    hwParcel2.send();
                    return;
                case 209:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPanel(new getPanelCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.101
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPanelCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 210:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryColorTemperature = setFactoryColorTemperature(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryColorTemperature);
                    hwParcel2.send();
                    return;
                case 211:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryColorTemperature(new getFactoryColorTemperatureCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.102
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTemperatureCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 212:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryWhiteBalanceOffsetRed = setFactoryWhiteBalanceOffsetRed(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryWhiteBalanceOffsetRed);
                    hwParcel2.send();
                    return;
                case 213:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryWhiteBalanceOffsetRed(new getFactoryWhiteBalanceOffsetRedCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.103
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceOffsetRedCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 214:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryWhiteBalanceOffsetGreen = setFactoryWhiteBalanceOffsetGreen(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryWhiteBalanceOffsetGreen);
                    hwParcel2.send();
                    return;
                case 215:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryWhiteBalanceOffsetGreen(new getFactoryWhiteBalanceOffsetGreenCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.104
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceOffsetGreenCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 216:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryWhiteBalanceOffsetBlue = setFactoryWhiteBalanceOffsetBlue(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryWhiteBalanceOffsetBlue);
                    hwParcel2.send();
                    return;
                case 217:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryWhiteBalanceOffsetBlue(new getFactoryWhiteBalanceOffsetBlueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.105
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceOffsetBlueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 218:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryWhiteBalanceGainRed = setFactoryWhiteBalanceGainRed(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryWhiteBalanceGainRed);
                    hwParcel2.send();
                    return;
                case 219:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryWhiteBalanceGainRed(new getFactoryWhiteBalanceGainRedCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.106
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceGainRedCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case FragmentManagerImpl.ANIM_DUR /* 220 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryWhiteBalanceGainGreen = setFactoryWhiteBalanceGainGreen(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryWhiteBalanceGainGreen);
                    hwParcel2.send();
                    return;
                case 221:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryWhiteBalanceGainGreen(new getFactoryWhiteBalanceGainGreenCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.107
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceGainGreenCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 222:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryWhiteBalanceGainBlue = setFactoryWhiteBalanceGainBlue(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryWhiteBalanceGainBlue);
                    hwParcel2.send();
                    return;
                case 223:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryWhiteBalanceGainBlue(new getFactoryWhiteBalanceGainBlueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.108
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryWhiteBalanceGainBlueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 224:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryMultiPointWhiteBalanceLevel = setFactoryMultiPointWhiteBalanceLevel(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryMultiPointWhiteBalanceLevel);
                    hwParcel2.send();
                    return;
                case 225:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryMultiPointWhiteBalanceLevel(new getFactoryMultiPointWhiteBalanceLevelCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.109
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceLevelCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 226:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryMultiPointWhiteBalanceRed = setFactoryMultiPointWhiteBalanceRed(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryMultiPointWhiteBalanceRed);
                    hwParcel2.send();
                    return;
                case 227:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryMultiPointWhiteBalanceRed(new getFactoryMultiPointWhiteBalanceRedCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.110
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceRedCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 228:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryMultiPointWhiteBalanceGreen = setFactoryMultiPointWhiteBalanceGreen(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryMultiPointWhiteBalanceGreen);
                    hwParcel2.send();
                    return;
                case 229:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryMultiPointWhiteBalanceGreen(new getFactoryMultiPointWhiteBalanceGreenCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.111
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceGreenCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 230:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryMultiPointWhiteBalanceBlue = setFactoryMultiPointWhiteBalanceBlue(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryMultiPointWhiteBalanceBlue);
                    hwParcel2.send();
                    return;
                case 231:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryMultiPointWhiteBalanceBlue(new getFactoryMultiPointWhiteBalanceBlueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.112
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryMultiPointWhiteBalanceBlueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 232:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int testPattern = setTestPattern(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(testPattern);
                    hwParcel2.send();
                    return;
                case 233:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int insidePattern = setInsidePattern(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(insidePattern);
                    hwParcel2.send();
                    return;
                case 234:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryColorTunerColor = setFactoryColorTunerColor(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryColorTunerColor);
                    hwParcel2.send();
                    return;
                case 235:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryColorTunerColor(new getFactoryColorTunerColorCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.113
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerColorCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 236:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryColorTunerBrightness = setFactoryColorTunerBrightness(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryColorTunerBrightness);
                    hwParcel2.send();
                    return;
                case 237:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryColorTunerBrightness(new getFactoryColorTunerBrightnessCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.114
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerBrightnessCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 238:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryColorTunerSaturation = setFactoryColorTunerSaturation(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryColorTunerSaturation);
                    hwParcel2.send();
                    return;
                case 239:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryColorTunerSaturation(new getFactoryColorTunerSaturationCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.115
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerSaturationCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 240:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int factoryColorTunerHue = setFactoryColorTunerHue(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryColorTunerHue);
                    hwParcel2.send();
                    return;
                case 241:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFactoryColorTunerHue(new getFactoryColorTunerHueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.116
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFactoryColorTunerHueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 242:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int vcomTestEnable = setVcomTestEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(vcomTestEnable);
                    hwParcel2.send();
                    return;
                case 243:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getVcomTestEnable(new getVcomTestEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.117
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getVcomTestEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 244:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int agingModeEnable = setAgingModeEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(agingModeEnable);
                    hwParcel2.send();
                    return;
                case 245:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAgingModeEnable(new getAgingModeEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.118
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAgingModeEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 246:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pqEnable = setPqEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pqEnable);
                    hwParcel2.send();
                    return;
                case 247:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPqEnable(new getPqEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.119
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPqEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 248:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int gammaEnable = setGammaEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(gammaEnable);
                    hwParcel2.send();
                    return;
                case 249:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getGammaEnable(new getGammaEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.120
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 250:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int sceEnable = setSceEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(sceEnable);
                    hwParcel2.send();
                    return;
                case MtkTvScanDvbtBase.EXCHANGE_MAX_LEN /* 251 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSceEnable(new getSceEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.121
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSceEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 252:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int xvyccEnable = setXvyccEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(xvyccEnable);
                    hwParcel2.send();
                    return;
                case 253:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getXvyccEnable(new getXvyccEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.122
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getXvyccEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 254:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iSetAutoGammaEnable = SetAutoGammaEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iSetAutoGammaEnable);
                    hwParcel2.send();
                    return;
                case 255:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    GetAutoGammaEnable(new GetAutoGammaEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.123
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.GetAutoGammaEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 256:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetAllVideoSettings = ResetAllVideoSettings(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetAllVideoSettings);
                    hwParcel2.send();
                    return;
                case 257:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalDRType(new getSignalDRTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.124
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalDRTypeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case HisHdmiApi.HD_IDX_UI_VRR_STATUS_2PORT /* 258 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalRatio(new getSignalRatioCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.125
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalRatioCallback
                        public void onValues(int i3, int i4, int i5) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.send();
                        }
                    });
                    return;
                case HisHdmiApi.HD_IDX_UI_VRR_STATUS_3PORT /* 259 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalVideoFormat(new getSignalVideoFormatCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.126
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalVideoFormatCallback
                        public void onValues(int i3, int i4, int i5, int i6, int i7) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.writeInt32(i6);
                            hwParcel2.writeInt32(i7);
                            hwParcel2.send();
                        }
                    });
                    return;
                case HisHdmiApi.HD_IDX_UI_VRR_STATUS_4PORT /* 260 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalColorFormat(new getSignalColorFormatCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.127
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalColorFormatCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 261:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalColorDepth(new getSignalColorDepthCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.128
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalColorDepthCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 262:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalCodecType(new getSignalCodecTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.129
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalCodecTypeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 263:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalColorSpace(new getSignalColorSpaceCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.130
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalColorSpaceCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 264:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalColorSystem(new getSignalColorSystemCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.131
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalColorSystemCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 265:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalIsUHD(new getSignalIsUHDCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.132
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalIsUHDCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 266:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalIsDolby(new getSignalIsDolbyCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.133
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalIsDolbyCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 267:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalFrameDelay(new getSignalFrameDelayCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.134
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalFrameDelayCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 268:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int filmmakerDetectionEnable = setFilmmakerDetectionEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(filmmakerDetectionEnable);
                    hwParcel2.send();
                    return;
                case 269:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFilmmakerDetectionEnable(new getFilmmakerDetectionEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.135
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFilmmakerDetectionEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 270:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalFilmmakerType(new getSignalFilmmakerTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.136
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalFilmmakerTypeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 271:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int filmmakerModeSelected = setFilmmakerModeSelected(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(filmmakerModeSelected);
                    hwParcel2.send();
                    return;
                case 272:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFilmmakerModeSelected(new getFilmmakerModeSelectedCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.137
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFilmmakerModeSelectedCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 273:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int autoFilmmakerModeStatus = setAutoFilmmakerModeStatus(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(autoFilmmakerModeStatus);
                    hwParcel2.send();
                    return;
                case 274:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAutoFilmmakerModeStatus(new getAutoFilmmakerModeStatusCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.138
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAutoFilmmakerModeStatusCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 275:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int gameDetectionEnable = setGameDetectionEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(gameDetectionEnable);
                    hwParcel2.send();
                    return;
                case 276:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getGameDetectionEnable(new getGameDetectionEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.139
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGameDetectionEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 277:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int usageMode = setUsageMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(usageMode);
                    hwParcel2.send();
                    return;
                case 278:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getUsageMode(new getUsageModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.140
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getUsageModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 279:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dMPPlayPhotoMode = setDMPPlayPhotoMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dMPPlayPhotoMode);
                    hwParcel2.send();
                    return;
                case 280:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDMPPlayPhotoMode(new getDMPPlayPhotoModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.141
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDMPPlayPhotoModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 281:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int bootLogoState = setBootLogoState(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(bootLogoState);
                    hwParcel2.send();
                    return;
                case 282:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBootLogoState(new getBootLogoStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.142
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBootLogoStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 283:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int fTEState = setFTEState(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(fTEState);
                    hwParcel2.send();
                    return;
                case 284:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFTEState(new getFTEStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.143
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFTEStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 285:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isDolby(new isDolbyCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.144
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDolbyCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 286:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isDolbyDark(new isDolbyDarkCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.145
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDolbyDarkCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 287:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isDolbyBrightness(new isDolbyBrightnessCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.146
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDolbyBrightnessCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 288:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isDolbyCustom(new isDolbyCustomCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.147
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDolbyCustomCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 289:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isHDR(new isHDRCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.148
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isHDRCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 290:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isHDR10Plus(new isHDR10PlusCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.149
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isHDR10PlusCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 291:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isSignalPlaying(new isSignalPlayingCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.150
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isSignalPlayingCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 292:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int videoMuteState = setVideoMuteState(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(videoMuteState);
                    hwParcel2.send();
                    return;
                case 293:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getVideoMuteState(new getVideoMuteStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.151
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getVideoMuteStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 294:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int outMode = setOutMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(outMode);
                    hwParcel2.send();
                    return;
                case 295:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOutMode(new getOutModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.152
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOutModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 296:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOverscanOptionState(new getOverscanOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.153
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOverscanOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 297:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAspectRatioOptionState(new getAspectRatioOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.154
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 298:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAspectRatioPanoramicOptionState(new getAspectRatioPanoramicOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.155
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioPanoramicOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 299:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAspectRatioDotByDotOptionState(new getAspectRatioDotByDotOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.156
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioDotByDotOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 300:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAspectRatioDirectOptionState(new getAspectRatioDirectOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.157
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAspectRatioDirectOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 301:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLocalDimmingOptionState(new getLocalDimmingOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.158
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLocalDimmingOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 302:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorOptionState(new getLightSensorOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.159
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 303:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMemcOptionState(new getMemcOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.160
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMemcOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 304:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getJudderReductionOptionState(new getJudderReductionOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.161
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getJudderReductionOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 305:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBlurReductionOptionState(new getBlurReductionOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.162
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBlurReductionOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 306:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMotionClearnessOptionState(new getMotionClearnessOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.163
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMotionClearnessOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case StatusLine.HTTP_TEMP_REDIRECT /* 307 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getNoiseReductionOptionState(new getNoiseReductionOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.164
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getNoiseReductionOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case StatusLine.HTTP_PERM_REDIRECT /* 308 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDigitalNoiseReductionOptionState(new getDigitalNoiseReductionOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.165
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDigitalNoiseReductionOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 309:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getEnhancedViewingAngleOptionState(new getEnhancedViewingAngleOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.166
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getEnhancedViewingAngleOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 310:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getColorSpaceOptionState(new getColorSpaceOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.167
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorSpaceOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 311:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getHDMIDynamicRangeOptionState(new getHDMIDynamicRangeOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.168
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHDMIDynamicRangeOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 312:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getGammaAdjustOptionState(new getGammaAdjustOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.169
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaAdjustOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 313:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPictureModeGameModeOptionState(new getPictureModeGameModeOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.170
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPictureModeGameModeOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 314:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLowBlueLightOptionState(new getLowBlueLightOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.171
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLowBlueLightOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case AutoScrollHelper.DEFAULT_MINIMUM_VELOCITY_DIPS /* 315 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFilmmakerOptionState(new getFilmmakerOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.172
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFilmmakerOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 316:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int energyLoopLogMask = setEnergyLoopLogMask(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(energyLoopLogMask);
                    hwParcel2.send();
                    return;
                case 317:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getEnergyLoopLogMask(new getEnergyLoopLogMaskCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.173
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getEnergyLoopLogMaskCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 318:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSignalAPL(new getSignalAPLCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.174
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSignalAPLCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 319:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorValue(new getLightSensorValueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.175
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorValueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 320:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iInitLightSensor = initLightSensor(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iInitLightSensor);
                    hwParcel2.send();
                    return;
                case 321:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetWhiteBalanceValue = ResetWhiteBalanceValue(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetWhiteBalanceValue);
                    hwParcel2.send();
                    return;
                case 322:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetSignalDRType(new testGetSignalDRTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.176
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetSignalDRTypeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 323:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetSignalDRType = testSetSignalDRType(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetSignalDRType);
                    hwParcel2.send();
                    return;
                case 324:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetOverscanIndex = testSetOverscanIndex(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetOverscanIndex);
                    hwParcel2.send();
                    return;
                case 325:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetOverscanIndex(new testGetOverscanIndexCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.177
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetOverscanIndexCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 326:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetSignalFilmmakerType = testSetSignalFilmmakerType(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetSignalFilmmakerType);
                    hwParcel2.send();
                    return;
                case 327:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetUseRawDataEnable = testSetUseRawDataEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetUseRawDataEnable);
                    hwParcel2.send();
                    return;
                case 328:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetUseRawDataEnable(new testGetUseRawDataEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.178
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetUseRawDataEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 329:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetSourceRegion = testSetSourceRegion(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetSourceRegion);
                    hwParcel2.send();
                    return;
                case 330:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetSrouceRegion(new testGetSrouceRegionCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.179
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetSrouceRegionCallback
                        public void onValues(int i3, int i4, int i5, int i6, int i7) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.writeInt32(i6);
                            hwParcel2.writeInt32(i7);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 331:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetOutputRegion = testSetOutputRegion(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetOutputRegion);
                    hwParcel2.send();
                    return;
                case 332:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetOutputRegion(new testGetOutputRegionCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.180
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetOutputRegionCallback
                        public void onValues(int i3, int i4, int i5, int i6, int i7) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.writeInt32(i6);
                            hwParcel2.writeInt32(i7);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 333:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetOverscanRatio = testSetOverscanRatio(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetOverscanRatio);
                    hwParcel2.send();
                    return;
                case 334:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetOverscanRatio(new testGetOverscanRatioCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.181
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetOverscanRatioCallback
                        public void onValues(int i3, int i4, int i5, int i6, int i7) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.writeInt32(i6);
                            hwParcel2.writeInt32(i7);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 335:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAllPictureSettingsForRemoteNow(new getAllPictureSettingsForRemoteNowCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.182
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAllPictureSettingsForRemoteNowCallback
                        public void onValues(int i3, ArrayList<Integer> arrayList) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32Vector(arrayList);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 336:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int linkSoundEnable = setLinkSoundEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(linkSoundEnable);
                    hwParcel2.send();
                    return;
                case 337:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLinkSoundEnable(new getLinkSoundEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.183
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLinkSoundEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 338:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLinkSoundOptionState(new getLinkSoundOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.184
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLinkSoundOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 339:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iSFSwitch = setISFSwitch(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iSFSwitch);
                    hwParcel2.send();
                    return;
                case 340:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getISFSwitch(new getISFSwitchCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.185
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getISFSwitchCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 341:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getISFOptionState(new getISFOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.186
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getISFOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 342:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iSFType = setISFType(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iSFType);
                    hwParcel2.send();
                    return;
                case 343:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getISFType(new getISFTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.187
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getISFTypeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 344:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iReloadISFData = ReloadISFData(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iReloadISFData);
                    hwParcel2.send();
                    return;
                case 345:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorALSMin = setLightSensorALSMin(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorALSMin);
                    hwParcel2.send();
                    return;
                case 346:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorALSMin(new getLightSensorALSMinCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.188
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALSMinCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 347:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorALS10 = setLightSensorALS10(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorALS10);
                    hwParcel2.send();
                    return;
                case 348:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorALS10(new getLightSensorALS10Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.189
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALS10Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 349:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorALS80 = setLightSensorALS80(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorALS80);
                    hwParcel2.send();
                    return;
                case 350:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorALS80(new getLightSensorALS80Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.190
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALS80Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 351:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorALS300 = setLightSensorALS300(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorALS300);
                    hwParcel2.send();
                    return;
                case 352:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorALS300(new getLightSensorALS300Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.191
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALS300Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 353:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorALSMax = setLightSensorALSMax(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorALSMax);
                    hwParcel2.send();
                    return;
                case 354:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorALSMax(new getLightSensorALSMaxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.192
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorALSMaxCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 355:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorLSMin = setLightSensorLSMin(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorLSMin);
                    hwParcel2.send();
                    return;
                case 356:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorLSMin(new getLightSensorLSMinCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.193
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLSMinCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 357:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorLS10 = setLightSensorLS10(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorLS10);
                    hwParcel2.send();
                    return;
                case 358:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorLS10(new getLightSensorLS10Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.194
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLS10Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 359:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorLS80 = setLightSensorLS80(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorLS80);
                    hwParcel2.send();
                    return;
                case 360:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorLS80(new getLightSensorLS80Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.195
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLS80Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 361:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorLS300 = setLightSensorLS300(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorLS300);
                    hwParcel2.send();
                    return;
                case 362:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorLS300(new getLightSensorLS300Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.196
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLS300Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 363:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorLSMax = setLightSensorLSMax(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorLSMax);
                    hwParcel2.send();
                    return;
                case 364:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorLSMax(new getLightSensorLSMaxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.197
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLSMaxCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 365:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetColorTempCompenEnable = testSetColorTempCompenEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetColorTempCompenEnable);
                    hwParcel2.send();
                    return;
                case 366:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetColorTempCompenEnable(new testGetColorTempCompenEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.198
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetColorTempCompenEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 367:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetColorTempCompenGain = testSetColorTempCompenGain(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetColorTempCompenGain);
                    hwParcel2.send();
                    return;
                case 368:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetColorTempCompenGain(new testGetColorTempCompenGainCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.199
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetColorTempCompenGainCallback
                        public void onValues(int i3, int i4, int i5, int i6) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.writeInt32(i6);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 369:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetBacklightLinearEnable = testSetBacklightLinearEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetBacklightLinearEnable);
                    hwParcel2.send();
                    return;
                case 370:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetBacklightLinearEnable(new testGetBacklightLinearEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.200
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetBacklightLinearEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 371:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetBacklightLinearGain = testSetBacklightLinearGain(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetBacklightLinearGain);
                    hwParcel2.send();
                    return;
                case 372:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetbacklightLinearGain(new testGetbacklightLinearGainCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.201
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetbacklightLinearGainCallback
                        public void onValues(int i3, int i4, int i5) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 373:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetDCDimmingEnable = testSetDCDimmingEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetDCDimmingEnable);
                    hwParcel2.send();
                    return;
                case 374:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetDCDimmingEnable(new testGetDCDimmingEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.202
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetDCDimmingEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 375:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int benqITFReserved1 = setBenqITFReserved1(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(benqITFReserved1);
                    hwParcel2.send();
                    return;
                case 376:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBenqITFReserved1(new getBenqITFReserved1Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.203
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBenqITFReserved1Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 377:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int benqITFReserved2 = setBenqITFReserved2(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(benqITFReserved2);
                    hwParcel2.send();
                    return;
                case 378:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBenqITFReserved2(new getBenqITFReserved2Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.204
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBenqITFReserved2Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 379:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int benqITFReserved3 = setBenqITFReserved3(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(benqITFReserved3);
                    hwParcel2.send();
                    return;
                case 380:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBenqITFReserved3(new getBenqITFReserved3Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.205
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBenqITFReserved3Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 381:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iPictureMenuQuit = PictureMenuQuit(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iPictureMenuQuit);
                    hwParcel2.send();
                    return;
                case 382:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int screenOutputDispRect = setScreenOutputDispRect(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(screenOutputDispRect);
                    hwParcel2.send();
                    return;
                case 383:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getScreenOutputDispRect(new getScreenOutputDispRectCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.206
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getScreenOutputDispRectCallback
                        public void onValues(int i3, int i4, int i5, int i6, int i7) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.writeInt32(i6);
                            hwParcel2.writeInt32(i7);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 384:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetSCEIndex = testSetSCEIndex(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetSCEIndex);
                    hwParcel2.send();
                    return;
                case 385:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetSCEIndex(new testGetSCEIndexCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.207
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetSCEIndexCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 386:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetGammaIndex = testSetGammaIndex(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetGammaIndex);
                    hwParcel2.send();
                    return;
                case 387:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetGammaIndex(new testGetGammaIndexCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.208
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetGammaIndexCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 388:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int bypassPqEnable = setBypassPqEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(bypassPqEnable);
                    hwParcel2.send();
                    return;
                case 389:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBypassPqEnable(new getBypassPqEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.209
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBypassPqEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 390:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int peaking = setPeaking(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(peaking);
                    hwParcel2.send();
                    return;
                case 391:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPeaking(new getPeakingCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.210
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPeakingCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 392:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPeakingOptionState(new getPeakingOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.211
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPeakingOptionStateCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 393:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int i3 = set2PWBOffsetRedDelta(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(i3);
                    hwParcel2.send();
                    return;
                case 394:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    get2PWBOffsetRedDelta(new get2PWBOffsetRedDeltaCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.212
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBOffsetRedDeltaCallback
                        public void onValues(int i4, int i5) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 395:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int i4 = set2PWBOffsetGreenDelta(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(i4);
                    hwParcel2.send();
                    return;
                case 396:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    get2PWBOffsetGreenDelta(new get2PWBOffsetGreenDeltaCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.213
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBOffsetGreenDeltaCallback
                        public void onValues(int i5, int i6) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.writeInt32(i6);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 397:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int i5 = set2PWBOffsetBlueDelta(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(i5);
                    hwParcel2.send();
                    return;
                case 398:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    get2PWBOffsetBlueDelta(new get2PWBOffsetBlueDeltaCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.214
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBOffsetBlueDeltaCallback
                        public void onValues(int i6, int i7) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i6);
                            hwParcel2.writeInt32(i7);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 399:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int i6 = set2PWBGainRedDelta(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(i6);
                    hwParcel2.send();
                    return;
                case 400:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    get2PWBGainRedDelta(new get2PWBGainRedDeltaCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.215
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBGainRedDeltaCallback
                        public void onValues(int i7, int i8) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i7);
                            hwParcel2.writeInt32(i8);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 401:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int i7 = set2PWBGainGreenDelta(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(i7);
                    hwParcel2.send();
                    return;
                case 402:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    get2PWBGainGreenDelta(new get2PWBGainGreenDeltaCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.216
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBGainGreenDeltaCallback
                        public void onValues(int i8, int i9) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i8);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 403:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int i8 = set2PWBGainBlueDelta(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(i8);
                    hwParcel2.send();
                    return;
                case 404:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    get2PWBGainBlueDelta(new get2PWBGainBlueDeltaCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.217
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.get2PWBGainBlueDeltaCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 405:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iScreenCapture = screenCapture(hwParcel.readString(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iScreenCapture);
                    hwParcel2.send();
                    return;
                case 406:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int autoScanMode = setAutoScanMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(autoScanMode);
                    hwParcel2.send();
                    return;
                case 407:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAutoScanMode(new getAutoScanModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.218
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAutoScanModeCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 408:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int itemData = setItemData(hwParcel.readInt32(), hwParcel.readInt32Vector());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemData);
                    hwParcel2.send();
                    return;
                case 409:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getItemData(hwParcel.readInt32(), new getItemDataCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.219
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemDataCallback
                        public void onValues(int i9, ArrayList<Integer> arrayList) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32Vector(arrayList);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 410:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetAllFac2PWhiteBalance = resetAllFac2PWhiteBalance(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetAllFac2PWhiteBalance);
                    hwParcel2.send();
                    return;
                case 411:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetGammaType = testSetGammaType(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetGammaType);
                    hwParcel2.send();
                    return;
                case 412:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    testGetGammaType(new testGetGammaTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.220
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.testGetGammaTypeCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 413:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int itemString = setItemString(hwParcel.readInt32(), hwParcel.readString());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemString);
                    hwParcel2.send();
                    return;
                case 414:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getItemString(hwParcel.readInt32(), new getItemStringCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.221
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemStringCallback
                        public void onValues(int i9, String str) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeString(str);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 415:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int itemValue = setItemValue(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemValue);
                    hwParcel2.send();
                    return;
                case 416:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getItemValue(hwParcel.readInt32(), new getItemValueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.222
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemValueCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 417:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iBackupPQSetting = backupPQSetting();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iBackupPQSetting);
                    hwParcel2.send();
                    return;
                case 418:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iRestorePQSetting = restorePQSetting();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iRestorePQSetting);
                    hwParcel2.send();
                    return;
                case 419:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iSaveStoreExpertData = saveStoreExpertData(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iSaveStoreExpertData);
                    hwParcel2.send();
                    return;
                case 420:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iResetStoreExpertData = resetStoreExpertData(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetStoreExpertData);
                    hwParcel2.send();
                    return;
                case 421:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int rGBPattern = setRGBPattern(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(rGBPattern);
                    hwParcel2.send();
                    return;
                case 422:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int aIPQDetectSwitch = setAIPQDetectSwitch(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(aIPQDetectSwitch);
                    hwParcel2.send();
                    return;
                case 423:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAIPQDetectSwitch(new getAIPQDetectSwitchCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.223
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAIPQDetectSwitchCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 424:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAIPQDetectSwitchOptionState(new getAIPQDetectSwitchOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.224
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAIPQDetectSwitchOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 425:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getRGBOnlyOptionState(new getRGBOnlyOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.225
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getRGBOnlyOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 426:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getColorTunerOptionState(new getColorTunerOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.226
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getColorTunerOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 427:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAllFeaturesFlags(new getAllFeaturesFlagsCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.227
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAllFeaturesFlagsCallback
                        public void onValues(int i9, ArrayList<Integer> arrayList) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32Vector(arrayList);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 428:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    ItemInOut(hwParcel.readInt32(), hwParcel.readInt32Vector(), new ItemInOutCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.228
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.ItemInOutCallback
                        public void onValues(int i9, ArrayList<Integer> arrayList) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32Vector(arrayList);
                            hwParcel2.send();
                        }
                    });
                    return;
                case ConfigFetchHandler.HTTP_TOO_MANY_REQUESTS /* 429 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDynamicBacklightOptionState(new getDynamicBacklightOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.229
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicBacklightOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 430:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMinimumBacklightOptionState(new getMinimumBacklightOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.230
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMinimumBacklightOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 431:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMaximumBacklightOptionState(new getMaximumBacklightOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.231
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMaximumBacklightOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 432:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getEnergySavingOptionState(new getEnergySavingOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.232
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getEnergySavingOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 433:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int instantGameResponseSwitch = setInstantGameResponseSwitch(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(instantGameResponseSwitch);
                    hwParcel2.send();
                    return;
                case 434:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getInstantGameResponseSwitch(new getInstantGameResponseSwitchCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.233
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getInstantGameResponseSwitchCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 435:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getInstantGameResponseOptionState(new getInstantGameResponseOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.234
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getInstantGameResponseOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 436:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int freeSyncSwitch = setFreeSyncSwitch(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(freeSyncSwitch);
                    hwParcel2.send();
                    return;
                case 437:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFreeSyncSwitch(new getFreeSyncSwitchCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.235
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFreeSyncSwitchCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 438:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFreeSyncOptionState(new getFreeSyncOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.236
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFreeSyncOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 439:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSharpnessOptionState(new getSharpnessOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.237
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSharpnessOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 440:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getGammaCalibrationOptionState(new getGammaCalibrationOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.238
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getGammaCalibrationOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 441:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDolbyIQEnableFlag(new getDolbyIQEnableFlagCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.239
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDolbyIQEnableFlagCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 442:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dolbyIQSensorWeight = setDolbyIQSensorWeight(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dolbyIQSensorWeight);
                    hwParcel2.send();
                    return;
                case 443:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dolbyIQSensorFrontLux = setDolbyIQSensorFrontLux(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dolbyIQSensorFrontLux);
                    hwParcel2.send();
                    return;
                case 444:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dolbyIQSensorTearLum = setDolbyIQSensorTearLum(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dolbyIQSensorTearLum);
                    hwParcel2.send();
                    return;
                case 445:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dolbyIQSensorWhiteX = setDolbyIQSensorWhiteX(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dolbyIQSensorWhiteX);
                    hwParcel2.send();
                    return;
                case 446:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dolbyIQSensorWhiteY = setDolbyIQSensorWhiteY(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dolbyIQSensorWhiteY);
                    hwParcel2.send();
                    return;
                case 447:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dolbyIQUserBinEnable = setDolbyIQUserBinEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dolbyIQUserBinEnable);
                    hwParcel2.send();
                    return;
                case StorageTask.STATES_COMPLETE /* 448 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dolbyIQUserBinUpdate = setDolbyIQUserBinUpdate(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dolbyIQUserBinUpdate);
                    hwParcel2.send();
                    return;
                case 449:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDolbyIQAPOContentType(new getDolbyIQAPOContentTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.240
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDolbyIQAPOContentTypeCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 450:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDolbyIQAPOWhiteBalance(new getDolbyIQAPOWhiteBalanceCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.241
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDolbyIQAPOWhiteBalanceCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 451:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorLux(new getLightSensorLuxCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.242
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorLuxCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 452:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lightSensorCurve = setLightSensorCurve(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lightSensorCurve);
                    hwParcel2.send();
                    return;
                case 453:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLightSensorCurve(hwParcel.readInt32(), hwParcel.readInt32(), new getLightSensorCurveCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.243
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLightSensorCurveCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 454:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDolbyIQUserBinEnable(new getDolbyIQUserBinEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.244
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDolbyIQUserBinEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 455:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getIMAXOptionState(new getIMAXOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.245
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getIMAXOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 456:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int aPOContentCurve = setAPOContentCurve(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(aPOContentCurve);
                    hwParcel2.send();
                    return;
                case 457:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAPOContentCurve(hwParcel.readInt32(), hwParcel.readInt32(), new getAPOContentCurveCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.246
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAPOContentCurveCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 458:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int aPOWBCurve = setAPOWBCurve(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(aPOWBCurve);
                    hwParcel2.send();
                    return;
                case 459:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAPOWBCurve(hwParcel.readInt32(), hwParcel.readInt32(), new getAPOWBCurveCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.247
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAPOWBCurveCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 460:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int aPOEnable = setAPOEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(aPOEnable);
                    hwParcel2.send();
                    return;
                case 461:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getAPOEnable(new getAPOEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.248
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getAPOEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 462:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetAPOContentType = testSetAPOContentType(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetAPOContentType);
                    hwParcel2.send();
                    return;
                case 463:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iTestSetAPOWBType = testSetAPOWBType(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iTestSetAPOWBType);
                    hwParcel2.send();
                    return;
                case 464:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int ePOSEnable = setEPOSEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(ePOSEnable);
                    hwParcel2.send();
                    return;
                case 465:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isGame(new isGameCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.249
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isGameCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 466:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteBalanceOptionState(new getWhiteBalanceOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.250
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteBalanceOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 467:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getMultiPointWhiteBalanceOptionState(new getMultiPointWhiteBalanceOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.251
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getMultiPointWhiteBalanceOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 468:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDolbyIQAPOOptionState(new getDolbyIQAPOOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.252
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDolbyIQAPOOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 469:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBacklightLevelOptionState(new getBacklightLevelOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.253
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBacklightLevelOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 470:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int windowHSLRange = setWindowHSLRange(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(windowHSLRange);
                    hwParcel2.send();
                    return;
                case 471:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWindowHSLHist(new getWindowHSLHistCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.254
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWindowHSLHistCallback
                        public void onValues(int i9, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32Vector(arrayList);
                            hwParcel2.writeInt32Vector(arrayList2);
                            hwParcel2.writeInt32Vector(arrayList3);
                            hwParcel2.writeInt32Vector(arrayList4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 472:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getQDCtrlPQUpdateEnable(new getQDCtrlPQUpdateEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.255
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getQDCtrlPQUpdateEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 473:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isTVSModel(new isTVSModelCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.256
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isTVSModelCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 474:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getTVSModel(new getTVSModelCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.257
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getTVSModelCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 475:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isOLEDModel(new isOLEDModelCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.258
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isOLEDModelCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 476:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getNetVideoBeautySwitch(new getNetVideoBeautySwitchCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.259
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getNetVideoBeautySwitchCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 477:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int netVideoBeautySwitch = setNetVideoBeautySwitch(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(netVideoBeautySwitch);
                    hwParcel2.send();
                    return;
                case 478:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOptionStateColorTemperature(new getOptionStateColorTemperatureCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.260
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOptionStateColorTemperatureCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 479:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    isDoubleDispalyMemc(new isDoubleDispalyMemcCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.261
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.isDoubleDispalyMemcCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 480:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dynamicContrast = setDynamicContrast(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dynamicContrast);
                    hwParcel2.send();
                    return;
                case 481:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDynamicContrast(new getDynamicContrastCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.262
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicContrastCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 482:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int tvsCtrlQDPQUpdateEnable = setTvsCtrlQDPQUpdateEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(tvsCtrlQDPQUpdateEnable);
                    hwParcel2.send();
                    return;
                case 483:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getVideoBeautySwitchOptionState(new getVideoBeautySwitchOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.263
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getVideoBeautySwitchOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 484:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int clrTempCompenEnable = setClrTempCompenEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(clrTempCompenEnable);
                    hwParcel2.send();
                    return;
                case 485:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getClrTempCompenEnable(new getClrTempCompenEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.264
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getClrTempCompenEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 486:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int clrTempCompenCurve = setClrTempCompenCurve(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(clrTempCompenCurve);
                    hwParcel2.send();
                    return;
                case 487:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getClrTempCompenCurve(hwParcel.readInt32(), hwParcel.readInt32(), new getClrTempCompenCurveCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.265
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getClrTempCompenCurveCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 488:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int standyScreenStatus = setStandyScreenStatus(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(standyScreenStatus);
                    hwParcel2.send();
                    return;
                case 489:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBrightnessOptionState(new getBrightnessOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.266
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBrightnessOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 490:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getContrastOptionState(new getContrastOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.267
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getContrastOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 491:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getSaturationOptionState(new getSaturationOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.268
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getSaturationOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 492:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getHueOptionState(new getHueOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.269
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getHueOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 493:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int itemTable = setItemTable(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemTable);
                    hwParcel2.send();
                    return;
                case 494:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getItemTable(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), new getItemTableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.270
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemTableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 495:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int whiteLevel = setWhiteLevel(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(whiteLevel);
                    hwParcel2.send();
                    return;
                case 496:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteLevel(new getWhiteLevelCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.271
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteLevelCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 497:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int blackLevel = setBlackLevel(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(blackLevel);
                    hwParcel2.send();
                    return;
                case 498:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBlackLevel(new getBlackLevelCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.272
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBlackLevelCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 499:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getWhiteLevelOptionState(new getWhiteLevelOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.273
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getWhiteLevelOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 500:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getBlackLevelOptionState(new getBlackLevelOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.274
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getBlackLevelOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 501:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int dynamicToneMappingEnable = setDynamicToneMappingEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dynamicToneMappingEnable);
                    hwParcel2.send();
                    return;
                case 502:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDynamicToneMappingEnable(new getDynamicToneMappingEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.275
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicToneMappingEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 503:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getDynamicToneMappingOptionState(new getDynamicToneMappingOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.276
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getDynamicToneMappingOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 504:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int calMANEnable = setCalMANEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(calMANEnable);
                    hwParcel2.send();
                    return;
                case 505:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getCalMANEnable(new getCalMANEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.277
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getCalMANEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 506:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    CalMAN_ULMPI(hwParcel.readString(), new CalMAN_ULMPICallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.278
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.CalMAN_ULMPICallback
                        public void onValues(int i9, String str) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeString(str);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 507:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int calMANTuningEnable = setCalMANTuningEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(calMANTuningEnable);
                    hwParcel2.send();
                    return;
                case 508:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getCalMANCalibrated(hwParcel.readInt32(), new getCalMANCalibratedCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.279
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getCalMANCalibratedCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 509:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int iCleanCalMANData = cleanCalMANData(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iCleanCalMANData);
                    hwParcel2.send();
                    return;
                case 510:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getActiveContrastOptionState(new getActiveContrastOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.280
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getActiveContrastOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case FrameMetricsAggregator.EVERY_DURATION /* 511 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getVRRPlusOptionState(new getVRRPlusOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.281
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getVRRPlusOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 512:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int laserScreenSize = setLaserScreenSize(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(laserScreenSize);
                    hwParcel2.send();
                    return;
                case 513:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLaserScreenSize(new getLaserScreenSizeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.282
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLaserScreenSizeCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case HisHdmiApi.HD_EARC_AUDIO_STATUS /* 514 */:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLaserScreenSizeOptionState(new getLaserScreenSizeOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.283
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLaserScreenSizeOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 515:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int laserScreenType = setLaserScreenType(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(laserScreenType);
                    hwParcel2.send();
                    return;
                case 516:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLaserScreenType(new getLaserScreenTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.284
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLaserScreenTypeCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 517:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getLaserScreenTypeOptionState(new getLaserScreenTypeOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.285
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getLaserScreenTypeOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 518:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getIsTechnicolorHDR(new getIsTechnicolorHDRCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.286
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getIsTechnicolorHDRCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 519:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pixelShiftEnable = setPixelShiftEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pixelShiftEnable);
                    hwParcel2.send();
                    return;
                case 520:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPixelShiftEnable(new getPixelShiftEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.287
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 521:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pixelShiftX = setPixelShiftX(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pixelShiftX);
                    hwParcel2.send();
                    return;
                case 522:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPixelShiftX(new getPixelShiftXCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.288
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftXCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 523:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int pixelShiftY = setPixelShiftY(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(pixelShiftY);
                    hwParcel2.send();
                    return;
                case 524:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getPixelShiftY(new getPixelShiftYCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.289
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getPixelShiftYCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 525:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int oLEDPLCCurve = setOLEDPLCCurve(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(oLEDPLCCurve);
                    hwParcel2.send();
                    return;
                case 526:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOLEDPLCCurve(new getOLEDPLCCurveCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.290
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDPLCCurveCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 527:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int oLEDLocalFrozenChkEnable = setOLEDLocalFrozenChkEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(oLEDLocalFrozenChkEnable);
                    hwParcel2.send();
                    return;
                case 528:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOLEDLocalFrozenChkEnable(new getOLEDLocalFrozenChkEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.291
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDLocalFrozenChkEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 529:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int oLEDLocalLumaChkEnable = setOLEDLocalLumaChkEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(oLEDLocalLumaChkEnable);
                    hwParcel2.send();
                    return;
                case 530:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOLEDLocalLumaChkEnable(new getOLEDLocalLumaChkEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.292
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDLocalLumaChkEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 531:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int oLEDLEAEnable = setOLEDLEAEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(oLEDLEAEnable);
                    hwParcel2.send();
                    return;
                case 532:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOLEDLEAEnable(new getOLEDLEAEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.293
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDLEAEnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 533:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getItemOptionState(hwParcel.readInt32(), new getItemOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.294
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getItemOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 534:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    setItemValueAsync(hwParcel.readInt32(), hwParcel.readInt32());
                    return;
                case 535:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int oLEDHDREnable = setOLEDHDREnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(oLEDHDREnable);
                    hwParcel2.send();
                    return;
                case 536:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOLEDHDREnable(new getOLEDHDREnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.295
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDHDREnableCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 537:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getOLEDPLCCurveOptionState(new getOLEDPLCCurveOptionStateCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.296
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getOLEDPLCCurveOptionStateCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 538:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    CalMAN_ULMPI_BYTES(hwParcel.readInt8Vector(), new CalMAN_ULMPI_BYTESCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.297
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.CalMAN_ULMPI_BYTESCallback
                        public void onValues(int i9, ArrayList<Byte> arrayList) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt8Vector(arrayList);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 539:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    int lDTestEnable = setLDTestEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lDTestEnable);
                    hwParcel2.send();
                    return;
                case 540:
                    hwParcel.enforceInterface(IHisVideoApi.kInterfaceName);
                    getFRCVersion(new getFRCVersionCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.Stub.298
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi.getFRCVersionCallback
                        public void onValues(int i9, int i10) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i9);
                            hwParcel2.writeInt32(i10);
                            hwParcel2.send();
                        }
                    });
                    return;
                default:
                    switch (i) {
                        case 256067662:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            ArrayList<String> arrayListInterfaceChain = interfaceChain();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeStringVector(arrayListInterfaceChain);
                            hwParcel2.send();
                            return;
                        case 256131655:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            debug(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 256136003:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            String strInterfaceDescriptor = interfaceDescriptor();
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeString(strInterfaceDescriptor);
                            hwParcel2.send();
                            return;
                        case 256398152:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            ArrayList<byte[]> hashChain = getHashChain();
                            hwParcel2.writeStatus(0);
                            HwBlob hwBlob = new HwBlob(16);
                            int size = hashChain.size();
                            hwBlob.putInt32(8L, size);
                            hwBlob.putBool(12L, false);
                            HwBlob hwBlob2 = new HwBlob(size * 32);
                            for (int i9 = 0; i9 < size; i9++) {
                                long j = i9 * 32;
                                byte[] bArr = hashChain.get(i9);
                                if (bArr == null || bArr.length != 32) {
                                    throw new IllegalArgumentException("Array element is not of the expected length");
                                }
                                hwBlob2.putInt8Array(j, bArr);
                            }
                            hwBlob.putBlob(0L, hwBlob2);
                            hwParcel2.writeBuffer(hwBlob);
                            hwParcel2.send();
                            return;
                        case 256462420:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            setHALInstrumentation();
                            return;
                        case 256660548:
                        default:
                            return;
                        case 256921159:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            ping();
                            hwParcel2.writeStatus(0);
                            hwParcel2.send();
                            return;
                        case 257049926:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            DebugInfo debugInfo = getDebugInfo();
                            hwParcel2.writeStatus(0);
                            debugInfo.writeToParcel(hwParcel2);
                            hwParcel2.send();
                            return;
                        case 257120595:
                            hwParcel.enforceInterface(IBase.kInterfaceName);
                            notifySyspropsChanged();
                            return;
                    }
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        public IHwInterface queryLocalInterface(String str) {
            if (IHisVideoApi.kInterfaceName.equals(str)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String str) {
            registerService(str);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }
    }

    @FunctionalInterface
    public interface get2PWBGainBlueDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBGainGreenDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBGainRedDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBOffsetBlueDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBOffsetGreenDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBOffsetRedDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAIPQDetectSwitchCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAIPQDetectSwitchOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAPOContentCurveCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAPOEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAPOWBCurveCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getActiveContrastCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getActiveContrastOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAgingModeEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAllFeaturesFlagsCallback {
        void onValues(int i, ArrayList<Integer> arrayList);
    }

    @FunctionalInterface
    public interface getAllPictureSettingsForRemoteNowCallback {
        void onValues(int i, ArrayList<Integer> arrayList);
    }

    @FunctionalInterface
    public interface getAspectRatioCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAspectRatioDirectOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAspectRatioDotByDotOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAspectRatioOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAspectRatioPanoramicOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAudioOnlyEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAutoFilmmakerModeStatusCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAutoScanModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklight20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklight80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightLevelOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightPWMFreqCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightPercentByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightPercentCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBenqITFReserved1Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBenqITFReserved2Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBenqITFReserved3Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBenqLowBlueLightCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBlackLevelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBlackLevelOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBlueStretchEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBlurReductionOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBootLogoStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightness20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightness80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBypassPqEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getCalMANCalibratedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getCalMANEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getClrTempCompenCurveCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getClrTempCompenEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getColorSpaceCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getColorSpaceOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getColorTemperatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getColorTunerApplyToAllCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getColorTunerBrightnessCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getColorTunerColorCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getColorTunerHueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getColorTunerOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getColorTunerSaturationCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrast20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrast80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDMPPlayPhotoModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDigitalNoiseReductionOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDolbyIQAPOContentTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDolbyIQAPOOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDolbyIQAPOWhiteBalanceCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDolbyIQEnableFlagCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDolbyIQUserBinEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDynamicBacklightCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDynamicBacklightOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDynamicContrastCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDynamicToneMappingEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDynamicToneMappingOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getEnergyLoopLogMaskCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getEnergySavingEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getEnergySavingOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getEnhancedViewingAngleEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getEnhancedViewingAngleOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFRCVersionCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFTEStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTemperatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTunerBrightnessCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTunerColorCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTunerHueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTunerSaturationCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryMultiPointWhiteBalanceBlueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryMultiPointWhiteBalanceGreenCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryMultiPointWhiteBalanceLevelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryMultiPointWhiteBalanceRedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceGainBlueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceGainGreenCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceGainRedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceOffsetBlueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceOffsetGreenCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceOffsetRedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFilmmakerDetectionEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFilmmakerModeSelectedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFilmmakerOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFreeSyncOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFreeSyncSwitchCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGameDetectionEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGammaAdjustOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGammaAdjustmentCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGammaCalibrationGainCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGammaCalibrationLevelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGammaCalibrationOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGammaEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHDMI20FormatCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHDMIDynamicRangeOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHdmiDynamicRangeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHue20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHue80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHueMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHueMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHueMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHueOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getIMAXOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getISFOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getISFSwitchCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getISFTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getInstantGameResponseOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getInstantGameResponseSwitchCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getIsTechnicolorHDRCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getItemDataCallback {
        void onValues(int i, ArrayList<Integer> arrayList);
    }

    @FunctionalInterface
    public interface getItemOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getItemStringCallback {
        void onValues(int i, String str);
    }

    @FunctionalInterface
    public interface getItemTableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getItemValueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getJudderReductionOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLaserScreenSizeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLaserScreenSizeOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLaserScreenTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLaserScreenTypeOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorALS10Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorALS300Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorALS80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorALSMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorALSMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorCurveCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorLS10Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorLS300Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorLS80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorLSMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorLSMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorLuxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorValueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLinkSoundEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLinkSoundOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLocalDimmingCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLocalDimmingOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLowBlueLightCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLowBlueLightOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMaximumBacklightOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMemcBlurReductionCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMemcCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMemcJudderReductionCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMemcOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMinimumBacklightOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMotionClearnessEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMotionClearnessOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMpegNoiseReductionCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMultiPointWhiteBalanceApplyToAllCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMultiPointWhiteBalanceBlueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMultiPointWhiteBalanceEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMultiPointWhiteBalanceGreenCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMultiPointWhiteBalanceLevelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMultiPointWhiteBalanceOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getMultiPointWhiteBalanceRedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getNetVideoBeautySwitchCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getNoiseReductionCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getNoiseReductionOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDHDREnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDLEAEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDLocalFrozenChkEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDLocalLumaChkEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDPLCCurveCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDPLCCurveOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDTPCEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOptionStateColorTemperatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOutModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanBottomCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanLeftCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanRightCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanTopCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPanelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPeakingCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPeakingOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPictureModeByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPictureModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPictureModeGameModeOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPictureSettingsToAllSourceCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPixelShiftEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPixelShiftShapeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPixelShiftTimerCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPixelShiftTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPixelShiftXCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPixelShiftYCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPqEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getQDCtrlPQUpdateEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getRGBOnlyOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getRgbOnlyCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturation20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturation80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSceEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getScreenOutputDispRectCallback {
        void onValues(int i, int i2, int i3, int i4, int i5);
    }

    @FunctionalInterface
    public interface getSharpness20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpness80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalAPLCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalCodecTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalColorDepthCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalColorFormatCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalColorSpaceCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalColorSystemCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalDRTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalFilmmakerTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalFrameDelayCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalIsDolbyCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalIsUHDCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalRatioCallback {
        void onValues(int i, int i2, int i3);
    }

    @FunctionalInterface
    public interface getSignalVideoFormatCallback {
        void onValues(int i, int i2, int i3, int i4, int i5);
    }

    @FunctionalInterface
    public interface getTVSModelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getTintByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getTintCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getUsageModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getVRRPlusOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getVcomTestEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getVideoBeautySwitchOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getVideoMuteStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteBalanceGainBlueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteBalanceGainGreenCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteBalanceGainRedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteBalanceOffsetBlueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteBalanceOffsetGreenCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteBalanceOffsetRedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteBalanceOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteBalanceiApplyToAllCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteLevelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWhiteLevelOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWindowHSLHistCallback {
        void onValues(int i, ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2, ArrayList<Integer> arrayList3, ArrayList<Integer> arrayList4);
    }

    @FunctionalInterface
    public interface getXvyccEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isDolbyBrightnessCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isDolbyCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isDolbyCustomCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isDolbyDarkCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isDoubleDispalyMemcCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isGameCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isHDR10PlusCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isHDRCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isOLEDModelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isSignalPlayingCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface isTVSModelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetBacklightLinearEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetColorTempCompenEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetColorTempCompenGainCallback {
        void onValues(int i, int i2, int i3, int i4);
    }

    @FunctionalInterface
    public interface testGetDCDimmingEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetGammaIndexCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetGammaTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetOutputRegionCallback {
        void onValues(int i, int i2, int i3, int i4, int i5);
    }

    @FunctionalInterface
    public interface testGetOverscanIndexCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetOverscanRatioCallback {
        void onValues(int i, int i2, int i3, int i4, int i5);
    }

    @FunctionalInterface
    public interface testGetSCEIndexCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetSignalDRTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetSrouceRegionCallback {
        void onValues(int i, int i2, int i3, int i4, int i5);
    }

    @FunctionalInterface
    public interface testGetUseRawDataEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface testGetbacklightLinearGainCallback {
        void onValues(int i, int i2, int i3);
    }

    static IHisVideoApi asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisVideoApi)) {
            return (IHisVideoApi) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IHisVideoApi castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static IHisVideoApi getService(String str, boolean z) {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    void CalMAN_ULMPI(String str, CalMAN_ULMPICallback calMAN_ULMPICallback);

    void CalMAN_ULMPI_BYTES(ArrayList<Byte> arrayList, CalMAN_ULMPI_BYTESCallback calMAN_ULMPI_BYTESCallback);

    void GetAutoGammaEnable(GetAutoGammaEnableCallback getAutoGammaEnableCallback);

    void ItemInOut(int i, ArrayList<Integer> arrayList, ItemInOutCallback itemInOutCallback);

    int PictureMenuQuit(int i);

    int ReloadISFData(int i);

    int ResetAllFacSettings(int i);

    int ResetAllUserSettings(int i);

    int ResetAllVideoSettings(int i);

    int ResetColorTuner(int i);

    int ResetGammaCalibration(int i);

    int ResetMultiPointWhiteBalance(int i);

    int ResetWhiteBalance(int i);

    int ResetWhiteBalanceValue(int i);

    int SetAutoGammaEnable(int i);

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    int backupPQSetting();

    int cleanCalMANData(int i);

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList);

    void get2PWBGainBlueDelta(get2PWBGainBlueDeltaCallback get2pwbgainbluedeltacallback);

    void get2PWBGainGreenDelta(get2PWBGainGreenDeltaCallback get2pwbgaingreendeltacallback);

    void get2PWBGainRedDelta(get2PWBGainRedDeltaCallback get2pwbgainreddeltacallback);

    void get2PWBOffsetBlueDelta(get2PWBOffsetBlueDeltaCallback get2pwboffsetbluedeltacallback);

    void get2PWBOffsetGreenDelta(get2PWBOffsetGreenDeltaCallback get2pwboffsetgreendeltacallback);

    void get2PWBOffsetRedDelta(get2PWBOffsetRedDeltaCallback get2pwboffsetreddeltacallback);

    void getAIPQDetectSwitch(getAIPQDetectSwitchCallback getaipqdetectswitchcallback);

    void getAIPQDetectSwitchOptionState(getAIPQDetectSwitchOptionStateCallback getaipqdetectswitchoptionstatecallback);

    void getAPOContentCurve(int i, int i2, getAPOContentCurveCallback getapocontentcurvecallback);

    void getAPOEnable(getAPOEnableCallback getapoenablecallback);

    void getAPOWBCurve(int i, int i2, getAPOWBCurveCallback getapowbcurvecallback);

    void getActiveContrast(getActiveContrastCallback getactivecontrastcallback);

    void getActiveContrastOptionState(getActiveContrastOptionStateCallback getactivecontrastoptionstatecallback);

    void getAgingModeEnable(getAgingModeEnableCallback getagingmodeenablecallback);

    void getAllFeaturesFlags(getAllFeaturesFlagsCallback getallfeaturesflagscallback);

    void getAllPictureSettingsForRemoteNow(getAllPictureSettingsForRemoteNowCallback getallpicturesettingsforremotenowcallback);

    void getAspectRatio(getAspectRatioCallback getaspectratiocallback);

    void getAspectRatioDirectOptionState(getAspectRatioDirectOptionStateCallback getaspectratiodirectoptionstatecallback);

    void getAspectRatioDotByDotOptionState(getAspectRatioDotByDotOptionStateCallback getaspectratiodotbydotoptionstatecallback);

    void getAspectRatioOptionState(getAspectRatioOptionStateCallback getaspectratiooptionstatecallback);

    void getAspectRatioPanoramicOptionState(getAspectRatioPanoramicOptionStateCallback getaspectratiopanoramicoptionstatecallback);

    void getAudioOnlyEnable(getAudioOnlyEnableCallback getaudioonlyenablecallback);

    void getAutoFilmmakerModeStatus(getAutoFilmmakerModeStatusCallback getautofilmmakermodestatuscallback);

    void getAutoScanMode(getAutoScanModeCallback getautoscanmodecallback);

    void getBacklight20(getBacklight20Callback getbacklight20callback);

    void getBacklight80(getBacklight80Callback getbacklight80callback);

    void getBacklightLevelOptionState(getBacklightLevelOptionStateCallback getbacklightleveloptionstatecallback);

    void getBacklightMax(getBacklightMaxCallback getbacklightmaxcallback);

    void getBacklightMid(getBacklightMidCallback getbacklightmidcallback);

    void getBacklightMin(getBacklightMinCallback getbacklightmincallback);

    void getBacklightPWMFreq(getBacklightPWMFreqCallback getbacklightpwmfreqcallback);

    void getBacklightPercent(getBacklightPercentCallback getbacklightpercentcallback);

    void getBacklightPercentByFactory(getBacklightPercentByFactoryCallback getbacklightpercentbyfactorycallback);

    void getBenqITFReserved1(getBenqITFReserved1Callback getbenqitfreserved1callback);

    void getBenqITFReserved2(getBenqITFReserved2Callback getbenqitfreserved2callback);

    void getBenqITFReserved3(getBenqITFReserved3Callback getbenqitfreserved3callback);

    void getBenqLowBlueLight(getBenqLowBlueLightCallback getbenqlowbluelightcallback);

    void getBlackLevel(getBlackLevelCallback getblacklevelcallback);

    void getBlackLevelOptionState(getBlackLevelOptionStateCallback getblackleveloptionstatecallback);

    void getBlueStretchEnable(getBlueStretchEnableCallback getbluestretchenablecallback);

    void getBlurReductionOptionState(getBlurReductionOptionStateCallback getblurreductionoptionstatecallback);

    void getBootLogoState(getBootLogoStateCallback getbootlogostatecallback);

    void getBrightness(getBrightnessCallback getbrightnesscallback);

    void getBrightness20(getBrightness20Callback getbrightness20callback);

    void getBrightness80(getBrightness80Callback getbrightness80callback);

    void getBrightnessByFactory(getBrightnessByFactoryCallback getbrightnessbyfactorycallback);

    void getBrightnessMax(getBrightnessMaxCallback getbrightnessmaxcallback);

    void getBrightnessMid(getBrightnessMidCallback getbrightnessmidcallback);

    void getBrightnessMin(getBrightnessMinCallback getbrightnessmincallback);

    void getBrightnessOptionState(getBrightnessOptionStateCallback getbrightnessoptionstatecallback);

    void getBypassPqEnable(getBypassPqEnableCallback getbypasspqenablecallback);

    void getCalMANCalibrated(int i, getCalMANCalibratedCallback getcalmancalibratedcallback);

    void getCalMANEnable(getCalMANEnableCallback getcalmanenablecallback);

    void getClrTempCompenCurve(int i, int i2, getClrTempCompenCurveCallback getclrtempcompencurvecallback);

    void getClrTempCompenEnable(getClrTempCompenEnableCallback getclrtempcompenenablecallback);

    void getColorSpace(getColorSpaceCallback getcolorspacecallback);

    void getColorSpaceOptionState(getColorSpaceOptionStateCallback getcolorspaceoptionstatecallback);

    void getColorTemperature(getColorTemperatureCallback getcolortemperaturecallback);

    void getColorTunerApplyToAll(getColorTunerApplyToAllCallback getcolortunerapplytoallcallback);

    void getColorTunerBrightness(getColorTunerBrightnessCallback getcolortunerbrightnesscallback);

    void getColorTunerColor(getColorTunerColorCallback getcolortunercolorcallback);

    void getColorTunerHue(getColorTunerHueCallback getcolortunerhuecallback);

    void getColorTunerOptionState(getColorTunerOptionStateCallback getcolortuneroptionstatecallback);

    void getColorTunerSaturation(getColorTunerSaturationCallback getcolortunersaturationcallback);

    void getContrast(getContrastCallback getcontrastcallback);

    void getContrast20(getContrast20Callback getcontrast20callback);

    void getContrast80(getContrast80Callback getcontrast80callback);

    void getContrastByFactory(getContrastByFactoryCallback getcontrastbyfactorycallback);

    void getContrastMax(getContrastMaxCallback getcontrastmaxcallback);

    void getContrastMid(getContrastMidCallback getcontrastmidcallback);

    void getContrastMin(getContrastMinCallback getcontrastmincallback);

    void getContrastOptionState(getContrastOptionStateCallback getcontrastoptionstatecallback);

    void getDMPPlayPhotoMode(getDMPPlayPhotoModeCallback getdmpplayphotomodecallback);

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo();

    void getDigitalNoiseReductionOptionState(getDigitalNoiseReductionOptionStateCallback getdigitalnoisereductionoptionstatecallback);

    void getDolbyIQAPOContentType(getDolbyIQAPOContentTypeCallback getdolbyiqapocontenttypecallback);

    void getDolbyIQAPOOptionState(getDolbyIQAPOOptionStateCallback getdolbyiqapooptionstatecallback);

    void getDolbyIQAPOWhiteBalance(getDolbyIQAPOWhiteBalanceCallback getdolbyiqapowhitebalancecallback);

    void getDolbyIQEnableFlag(getDolbyIQEnableFlagCallback getdolbyiqenableflagcallback);

    void getDolbyIQUserBinEnable(getDolbyIQUserBinEnableCallback getdolbyiquserbinenablecallback);

    void getDynamicBacklight(getDynamicBacklightCallback getdynamicbacklightcallback);

    void getDynamicBacklightOptionState(getDynamicBacklightOptionStateCallback getdynamicbacklightoptionstatecallback);

    void getDynamicContrast(getDynamicContrastCallback getdynamiccontrastcallback);

    void getDynamicToneMappingEnable(getDynamicToneMappingEnableCallback getdynamictonemappingenablecallback);

    void getDynamicToneMappingOptionState(getDynamicToneMappingOptionStateCallback getdynamictonemappingoptionstatecallback);

    void getEnergyLoopLogMask(getEnergyLoopLogMaskCallback getenergylooplogmaskcallback);

    void getEnergySavingEnable(getEnergySavingEnableCallback getenergysavingenablecallback);

    void getEnergySavingOptionState(getEnergySavingOptionStateCallback getenergysavingoptionstatecallback);

    void getEnhancedViewingAngleEnable(getEnhancedViewingAngleEnableCallback getenhancedviewingangleenablecallback);

    void getEnhancedViewingAngleOptionState(getEnhancedViewingAngleOptionStateCallback getenhancedviewingangleoptionstatecallback);

    void getFRCVersion(getFRCVersionCallback getfrcversioncallback);

    void getFTEState(getFTEStateCallback getftestatecallback);

    void getFactoryColorTemperature(getFactoryColorTemperatureCallback getfactorycolortemperaturecallback);

    void getFactoryColorTunerBrightness(getFactoryColorTunerBrightnessCallback getfactorycolortunerbrightnesscallback);

    void getFactoryColorTunerColor(getFactoryColorTunerColorCallback getfactorycolortunercolorcallback);

    void getFactoryColorTunerHue(getFactoryColorTunerHueCallback getfactorycolortunerhuecallback);

    void getFactoryColorTunerSaturation(getFactoryColorTunerSaturationCallback getfactorycolortunersaturationcallback);

    void getFactoryMultiPointWhiteBalanceBlue(getFactoryMultiPointWhiteBalanceBlueCallback getfactorymultipointwhitebalancebluecallback);

    void getFactoryMultiPointWhiteBalanceGreen(getFactoryMultiPointWhiteBalanceGreenCallback getfactorymultipointwhitebalancegreencallback);

    void getFactoryMultiPointWhiteBalanceLevel(getFactoryMultiPointWhiteBalanceLevelCallback getfactorymultipointwhitebalancelevelcallback);

    void getFactoryMultiPointWhiteBalanceRed(getFactoryMultiPointWhiteBalanceRedCallback getfactorymultipointwhitebalanceredcallback);

    void getFactoryWhiteBalanceGainBlue(getFactoryWhiteBalanceGainBlueCallback getfactorywhitebalancegainbluecallback);

    void getFactoryWhiteBalanceGainGreen(getFactoryWhiteBalanceGainGreenCallback getfactorywhitebalancegaingreencallback);

    void getFactoryWhiteBalanceGainRed(getFactoryWhiteBalanceGainRedCallback getfactorywhitebalancegainredcallback);

    void getFactoryWhiteBalanceOffsetBlue(getFactoryWhiteBalanceOffsetBlueCallback getfactorywhitebalanceoffsetbluecallback);

    void getFactoryWhiteBalanceOffsetGreen(getFactoryWhiteBalanceOffsetGreenCallback getfactorywhitebalanceoffsetgreencallback);

    void getFactoryWhiteBalanceOffsetRed(getFactoryWhiteBalanceOffsetRedCallback getfactorywhitebalanceoffsetredcallback);

    void getFilmmakerDetectionEnable(getFilmmakerDetectionEnableCallback getfilmmakerdetectionenablecallback);

    void getFilmmakerModeSelected(getFilmmakerModeSelectedCallback getfilmmakermodeselectedcallback);

    void getFilmmakerOptionState(getFilmmakerOptionStateCallback getfilmmakeroptionstatecallback);

    void getFreeSyncOptionState(getFreeSyncOptionStateCallback getfreesyncoptionstatecallback);

    void getFreeSyncSwitch(getFreeSyncSwitchCallback getfreesyncswitchcallback);

    void getGameDetectionEnable(getGameDetectionEnableCallback getgamedetectionenablecallback);

    void getGammaAdjustOptionState(getGammaAdjustOptionStateCallback getgammaadjustoptionstatecallback);

    void getGammaAdjustment(getGammaAdjustmentCallback getgammaadjustmentcallback);

    void getGammaCalibrationGain(getGammaCalibrationGainCallback getgammacalibrationgaincallback);

    void getGammaCalibrationLevel(getGammaCalibrationLevelCallback getgammacalibrationlevelcallback);

    void getGammaCalibrationOptionState(getGammaCalibrationOptionStateCallback getgammacalibrationoptionstatecallback);

    void getGammaEnable(getGammaEnableCallback getgammaenablecallback);

    void getHDMI20Format(getHDMI20FormatCallback gethdmi20formatcallback);

    void getHDMIDynamicRangeOptionState(getHDMIDynamicRangeOptionStateCallback gethdmidynamicrangeoptionstatecallback);

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain();

    void getHdmiDynamicRange(getHdmiDynamicRangeCallback gethdmidynamicrangecallback);

    void getHue20(getHue20Callback gethue20callback);

    void getHue80(getHue80Callback gethue80callback);

    void getHueMax(getHueMaxCallback gethuemaxcallback);

    void getHueMid(getHueMidCallback gethuemidcallback);

    void getHueMin(getHueMinCallback gethuemincallback);

    void getHueOptionState(getHueOptionStateCallback gethueoptionstatecallback);

    void getIMAXOptionState(getIMAXOptionStateCallback getimaxoptionstatecallback);

    void getISFOptionState(getISFOptionStateCallback getisfoptionstatecallback);

    void getISFSwitch(getISFSwitchCallback getisfswitchcallback);

    void getISFType(getISFTypeCallback getisftypecallback);

    void getInstantGameResponseOptionState(getInstantGameResponseOptionStateCallback getinstantgameresponseoptionstatecallback);

    void getInstantGameResponseSwitch(getInstantGameResponseSwitchCallback getinstantgameresponseswitchcallback);

    void getIsTechnicolorHDR(getIsTechnicolorHDRCallback getistechnicolorhdrcallback);

    void getItemData(int i, getItemDataCallback getitemdatacallback);

    void getItemOptionState(int i, getItemOptionStateCallback getitemoptionstatecallback);

    void getItemString(int i, getItemStringCallback getitemstringcallback);

    void getItemTable(int i, int i2, int i3, getItemTableCallback getitemtablecallback);

    void getItemValue(int i, getItemValueCallback getitemvaluecallback);

    void getJudderReductionOptionState(getJudderReductionOptionStateCallback getjudderreductionoptionstatecallback);

    void getLaserScreenSize(getLaserScreenSizeCallback getlaserscreensizecallback);

    void getLaserScreenSizeOptionState(getLaserScreenSizeOptionStateCallback getlaserscreensizeoptionstatecallback);

    void getLaserScreenType(getLaserScreenTypeCallback getlaserscreentypecallback);

    void getLaserScreenTypeOptionState(getLaserScreenTypeOptionStateCallback getlaserscreentypeoptionstatecallback);

    void getLightSensorALS10(getLightSensorALS10Callback getlightsensorals10callback);

    void getLightSensorALS300(getLightSensorALS300Callback getlightsensorals300callback);

    void getLightSensorALS80(getLightSensorALS80Callback getlightsensorals80callback);

    void getLightSensorALSMax(getLightSensorALSMaxCallback getlightsensoralsmaxcallback);

    void getLightSensorALSMin(getLightSensorALSMinCallback getlightsensoralsmincallback);

    void getLightSensorCurve(int i, int i2, getLightSensorCurveCallback getlightsensorcurvecallback);

    void getLightSensorEnable(getLightSensorEnableCallback getlightsensorenablecallback);

    void getLightSensorLS10(getLightSensorLS10Callback getlightsensorls10callback);

    void getLightSensorLS300(getLightSensorLS300Callback getlightsensorls300callback);

    void getLightSensorLS80(getLightSensorLS80Callback getlightsensorls80callback);

    void getLightSensorLSMax(getLightSensorLSMaxCallback getlightsensorlsmaxcallback);

    void getLightSensorLSMin(getLightSensorLSMinCallback getlightsensorlsmincallback);

    void getLightSensorLux(getLightSensorLuxCallback getlightsensorluxcallback);

    void getLightSensorMax(getLightSensorMaxCallback getlightsensormaxcallback);

    void getLightSensorMin(getLightSensorMinCallback getlightsensormincallback);

    void getLightSensorOptionState(getLightSensorOptionStateCallback getlightsensoroptionstatecallback);

    void getLightSensorValue(getLightSensorValueCallback getlightsensorvaluecallback);

    void getLinkSoundEnable(getLinkSoundEnableCallback getlinksoundenablecallback);

    void getLinkSoundOptionState(getLinkSoundOptionStateCallback getlinksoundoptionstatecallback);

    void getLocalDimming(getLocalDimmingCallback getlocaldimmingcallback);

    void getLocalDimmingOptionState(getLocalDimmingOptionStateCallback getlocaldimmingoptionstatecallback);

    void getLowBlueLight(getLowBlueLightCallback getlowbluelightcallback);

    void getLowBlueLightOptionState(getLowBlueLightOptionStateCallback getlowbluelightoptionstatecallback);

    void getMaximumBacklightOptionState(getMaximumBacklightOptionStateCallback getmaximumbacklightoptionstatecallback);

    void getMemc(getMemcCallback getmemccallback);

    void getMemcBlurReduction(getMemcBlurReductionCallback getmemcblurreductioncallback);

    void getMemcJudderReduction(getMemcJudderReductionCallback getmemcjudderreductioncallback);

    void getMemcOptionState(getMemcOptionStateCallback getmemcoptionstatecallback);

    void getMinimumBacklightOptionState(getMinimumBacklightOptionStateCallback getminimumbacklightoptionstatecallback);

    void getMotionClearnessEnable(getMotionClearnessEnableCallback getmotionclearnessenablecallback);

    void getMotionClearnessOptionState(getMotionClearnessOptionStateCallback getmotionclearnessoptionstatecallback);

    void getMpegNoiseReduction(getMpegNoiseReductionCallback getmpegnoisereductioncallback);

    void getMultiPointWhiteBalanceApplyToAll(getMultiPointWhiteBalanceApplyToAllCallback getmultipointwhitebalanceapplytoallcallback);

    void getMultiPointWhiteBalanceBlue(getMultiPointWhiteBalanceBlueCallback getmultipointwhitebalancebluecallback);

    void getMultiPointWhiteBalanceEnable(getMultiPointWhiteBalanceEnableCallback getmultipointwhitebalanceenablecallback);

    void getMultiPointWhiteBalanceGreen(getMultiPointWhiteBalanceGreenCallback getmultipointwhitebalancegreencallback);

    void getMultiPointWhiteBalanceLevel(getMultiPointWhiteBalanceLevelCallback getmultipointwhitebalancelevelcallback);

    void getMultiPointWhiteBalanceOptionState(getMultiPointWhiteBalanceOptionStateCallback getmultipointwhitebalanceoptionstatecallback);

    void getMultiPointWhiteBalanceRed(getMultiPointWhiteBalanceRedCallback getmultipointwhitebalanceredcallback);

    void getNetVideoBeautySwitch(getNetVideoBeautySwitchCallback getnetvideobeautyswitchcallback);

    void getNoiseReduction(getNoiseReductionCallback getnoisereductioncallback);

    void getNoiseReductionOptionState(getNoiseReductionOptionStateCallback getnoisereductionoptionstatecallback);

    void getOLEDHDREnable(getOLEDHDREnableCallback getoledhdrenablecallback);

    void getOLEDLEAEnable(getOLEDLEAEnableCallback getoledleaenablecallback);

    void getOLEDLocalFrozenChkEnable(getOLEDLocalFrozenChkEnableCallback getoledlocalfrozenchkenablecallback);

    void getOLEDLocalLumaChkEnable(getOLEDLocalLumaChkEnableCallback getoledlocallumachkenablecallback);

    void getOLEDPLCCurve(getOLEDPLCCurveCallback getoledplccurvecallback);

    void getOLEDPLCCurveOptionState(getOLEDPLCCurveOptionStateCallback getoledplccurveoptionstatecallback);

    void getOLEDTPCEnable(getOLEDTPCEnableCallback getoledtpcenablecallback);

    void getOptionStateColorTemperature(getOptionStateColorTemperatureCallback getoptionstatecolortemperaturecallback);

    void getOutMode(getOutModeCallback getoutmodecallback);

    void getOverscanBottom(getOverscanBottomCallback getoverscanbottomcallback);

    void getOverscanEnable(getOverscanEnableCallback getoverscanenablecallback);

    void getOverscanLeft(getOverscanLeftCallback getoverscanleftcallback);

    void getOverscanOptionState(getOverscanOptionStateCallback getoverscanoptionstatecallback);

    void getOverscanRight(getOverscanRightCallback getoverscanrightcallback);

    void getOverscanTop(getOverscanTopCallback getoverscantopcallback);

    void getPanel(getPanelCallback getpanelcallback);

    void getPeaking(getPeakingCallback getpeakingcallback);

    void getPeakingOptionState(getPeakingOptionStateCallback getpeakingoptionstatecallback);

    void getPictureMode(getPictureModeCallback getpicturemodecallback);

    void getPictureModeByFactory(getPictureModeByFactoryCallback getpicturemodebyfactorycallback);

    void getPictureModeGameModeOptionState(getPictureModeGameModeOptionStateCallback getpicturemodegamemodeoptionstatecallback);

    void getPictureSettingsToAllSource(getPictureSettingsToAllSourceCallback getpicturesettingstoallsourcecallback);

    void getPixelShiftEnable(getPixelShiftEnableCallback getpixelshiftenablecallback);

    void getPixelShiftShape(getPixelShiftShapeCallback getpixelshiftshapecallback);

    void getPixelShiftTimer(getPixelShiftTimerCallback getpixelshifttimercallback);

    void getPixelShiftType(getPixelShiftTypeCallback getpixelshifttypecallback);

    void getPixelShiftX(getPixelShiftXCallback getpixelshiftxcallback);

    void getPixelShiftY(getPixelShiftYCallback getpixelshiftycallback);

    void getPqEnable(getPqEnableCallback getpqenablecallback);

    void getQDCtrlPQUpdateEnable(getQDCtrlPQUpdateEnableCallback getqdctrlpqupdateenablecallback);

    void getRGBOnlyOptionState(getRGBOnlyOptionStateCallback getrgbonlyoptionstatecallback);

    void getRgbOnly(getRgbOnlyCallback getrgbonlycallback);

    void getSaturation(getSaturationCallback getsaturationcallback);

    void getSaturation20(getSaturation20Callback getsaturation20callback);

    void getSaturation80(getSaturation80Callback getsaturation80callback);

    void getSaturationByFactory(getSaturationByFactoryCallback getsaturationbyfactorycallback);

    void getSaturationMax(getSaturationMaxCallback getsaturationmaxcallback);

    void getSaturationMid(getSaturationMidCallback getsaturationmidcallback);

    void getSaturationMin(getSaturationMinCallback getsaturationmincallback);

    void getSaturationOptionState(getSaturationOptionStateCallback getsaturationoptionstatecallback);

    void getSceEnable(getSceEnableCallback getsceenablecallback);

    void getScreenOutputDispRect(getScreenOutputDispRectCallback getscreenoutputdisprectcallback);

    void getSharpness(getSharpnessCallback getsharpnesscallback);

    void getSharpness20(getSharpness20Callback getsharpness20callback);

    void getSharpness80(getSharpness80Callback getsharpness80callback);

    void getSharpnessByFactory(getSharpnessByFactoryCallback getsharpnessbyfactorycallback);

    void getSharpnessMax(getSharpnessMaxCallback getsharpnessmaxcallback);

    void getSharpnessMid(getSharpnessMidCallback getsharpnessmidcallback);

    void getSharpnessMin(getSharpnessMinCallback getsharpnessmincallback);

    void getSharpnessOptionState(getSharpnessOptionStateCallback getsharpnessoptionstatecallback);

    void getSignalAPL(getSignalAPLCallback getsignalaplcallback);

    void getSignalCodecType(getSignalCodecTypeCallback getsignalcodectypecallback);

    void getSignalColorDepth(getSignalColorDepthCallback getsignalcolordepthcallback);

    void getSignalColorFormat(getSignalColorFormatCallback getsignalcolorformatcallback);

    void getSignalColorSpace(getSignalColorSpaceCallback getsignalcolorspacecallback);

    void getSignalColorSystem(getSignalColorSystemCallback getsignalcolorsystemcallback);

    void getSignalDRType(getSignalDRTypeCallback getsignaldrtypecallback);

    void getSignalFilmmakerType(getSignalFilmmakerTypeCallback getsignalfilmmakertypecallback);

    void getSignalFrameDelay(getSignalFrameDelayCallback getsignalframedelaycallback);

    void getSignalIsDolby(getSignalIsDolbyCallback getsignalisdolbycallback);

    void getSignalIsUHD(getSignalIsUHDCallback getsignalisuhdcallback);

    void getSignalRatio(getSignalRatioCallback getsignalratiocallback);

    void getSignalVideoFormat(getSignalVideoFormatCallback getsignalvideoformatcallback);

    void getTVSModel(getTVSModelCallback gettvsmodelcallback);

    void getTint(getTintCallback gettintcallback);

    void getTintByFactory(getTintByFactoryCallback gettintbyfactorycallback);

    void getUsageMode(getUsageModeCallback getusagemodecallback);

    void getVRRPlusOptionState(getVRRPlusOptionStateCallback getvrrplusoptionstatecallback);

    void getVcomTestEnable(getVcomTestEnableCallback getvcomtestenablecallback);

    void getVideoBeautySwitchOptionState(getVideoBeautySwitchOptionStateCallback getvideobeautyswitchoptionstatecallback);

    void getVideoMuteState(getVideoMuteStateCallback getvideomutestatecallback);

    void getWhiteBalanceGainBlue(getWhiteBalanceGainBlueCallback getwhitebalancegainbluecallback);

    void getWhiteBalanceGainGreen(getWhiteBalanceGainGreenCallback getwhitebalancegaingreencallback);

    void getWhiteBalanceGainRed(getWhiteBalanceGainRedCallback getwhitebalancegainredcallback);

    void getWhiteBalanceOffsetBlue(getWhiteBalanceOffsetBlueCallback getwhitebalanceoffsetbluecallback);

    void getWhiteBalanceOffsetGreen(getWhiteBalanceOffsetGreenCallback getwhitebalanceoffsetgreencallback);

    void getWhiteBalanceOffsetRed(getWhiteBalanceOffsetRedCallback getwhitebalanceoffsetredcallback);

    void getWhiteBalanceOptionState(getWhiteBalanceOptionStateCallback getwhitebalanceoptionstatecallback);

    void getWhiteBalanceiApplyToAll(getWhiteBalanceiApplyToAllCallback getwhitebalanceiapplytoallcallback);

    void getWhiteLevel(getWhiteLevelCallback getwhitelevelcallback);

    void getWhiteLevelOptionState(getWhiteLevelOptionStateCallback getwhiteleveloptionstatecallback);

    void getWindowHSLHist(getWindowHSLHistCallback getwindowhslhistcallback);

    void getXvyccEnable(getXvyccEnableCallback getxvyccenablecallback);

    int initLightSensor(int i);

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain();

    @Override // android.hidl.base.V1_0.IBase
    String interfaceDescriptor();

    void isDolby(isDolbyCallback isdolbycallback);

    void isDolbyBrightness(isDolbyBrightnessCallback isdolbybrightnesscallback);

    void isDolbyCustom(isDolbyCustomCallback isdolbycustomcallback);

    void isDolbyDark(isDolbyDarkCallback isdolbydarkcallback);

    void isDoubleDispalyMemc(isDoubleDispalyMemcCallback isdoubledispalymemccallback);

    void isGame(isGameCallback isgamecallback);

    void isHDR(isHDRCallback ishdrcallback);

    void isHDR10Plus(isHDR10PlusCallback ishdr10pluscallback);

    void isOLEDModel(isOLEDModelCallback isoledmodelcallback);

    void isSignalPlaying(isSignalPlayingCallback issignalplayingcallback);

    void isTVSModel(isTVSModelCallback istvsmodelcallback);

    @Override // android.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j);

    @Override // android.hidl.base.V1_0.IBase
    void notifySyspropsChanged();

    @Override // android.hidl.base.V1_0.IBase
    void ping();

    int resetAllFac2PWhiteBalance(int i);

    int resetPictureSettings(int i);

    int resetStoreExpertData(int i);

    int restorePQSetting();

    int saveStoreExpertData(int i);

    int screenCapture(String str, int i);

    int set2PWBGainBlueDelta(int i);

    int set2PWBGainGreenDelta(int i);

    int set2PWBGainRedDelta(int i);

    int set2PWBOffsetBlueDelta(int i);

    int set2PWBOffsetGreenDelta(int i);

    int set2PWBOffsetRedDelta(int i);

    int setAIPQDetectSwitch(int i);

    int setAPOContentCurve(int i, int i2, int i3);

    int setAPOEnable(int i);

    int setAPOWBCurve(int i, int i2, int i3);

    int setActiveContrast(int i);

    int setAgingModeEnable(int i);

    int setAspectRatio(int i);

    int setAudioOnlyEnable(int i);

    int setAutoFilmmakerModeStatus(int i);

    int setAutoScanMode(int i);

    int setBacklight20(int i);

    int setBacklight80(int i);

    int setBacklightMax(int i);

    int setBacklightMid(int i);

    int setBacklightMin(int i);

    int setBacklightPWMFreq(int i);

    int setBacklightPercent(int i);

    int setBacklightPercentByFactory(int i);

    int setBenqITFReserved1(int i);

    int setBenqITFReserved2(int i);

    int setBenqITFReserved3(int i);

    int setBenqLowBlueLight(int i);

    int setBlackLevel(int i);

    int setBlueStretchEnable(int i);

    int setBootLogoState(int i);

    int setBrightness(int i);

    int setBrightness20(int i);

    int setBrightness80(int i);

    int setBrightnessByFactory(int i);

    int setBrightnessMax(int i);

    int setBrightnessMid(int i);

    int setBrightnessMin(int i);

    int setBypassPqEnable(int i);

    int setCalMANEnable(int i);

    int setCalMANTuningEnable(int i);

    int setClrTempCompenCurve(int i, int i2, int i3);

    int setClrTempCompenEnable(int i);

    int setColorSpace(int i);

    int setColorTemperature(int i);

    int setColorTunerApplyToAll(int i);

    int setColorTunerBrightness(int i);

    int setColorTunerColor(int i);

    int setColorTunerHue(int i);

    int setColorTunerSaturation(int i);

    int setContrast(int i);

    int setContrast20(int i);

    int setContrast80(int i);

    int setContrastByFactory(int i);

    int setContrastMax(int i);

    int setContrastMid(int i);

    int setContrastMin(int i);

    int setDMPPlayPhotoMode(int i);

    int setDolbyIQSensorFrontLux(int i);

    int setDolbyIQSensorTearLum(int i);

    int setDolbyIQSensorWeight(int i);

    int setDolbyIQSensorWhiteX(int i);

    int setDolbyIQSensorWhiteY(int i);

    int setDolbyIQUserBinEnable(int i);

    int setDolbyIQUserBinUpdate(int i);

    int setDynamicBacklight(int i);

    int setDynamicContrast(int i);

    int setDynamicToneMappingEnable(int i);

    int setEPOSEnable(int i);

    int setEnergyLoopLogMask(int i);

    int setEnergySavingEnable(int i);

    int setEnhancedViewingAngleEnable(int i);

    int setFTEState(int i);

    int setFactoryColorTemperature(int i);

    int setFactoryColorTunerBrightness(int i);

    int setFactoryColorTunerColor(int i);

    int setFactoryColorTunerHue(int i);

    int setFactoryColorTunerSaturation(int i);

    int setFactoryMultiPointWhiteBalanceBlue(int i);

    int setFactoryMultiPointWhiteBalanceGreen(int i);

    int setFactoryMultiPointWhiteBalanceLevel(int i);

    int setFactoryMultiPointWhiteBalanceRed(int i);

    int setFactoryWhiteBalanceGainBlue(int i);

    int setFactoryWhiteBalanceGainGreen(int i);

    int setFactoryWhiteBalanceGainRed(int i);

    int setFactoryWhiteBalanceOffsetBlue(int i);

    int setFactoryWhiteBalanceOffsetGreen(int i);

    int setFactoryWhiteBalanceOffsetRed(int i);

    int setFilmmakerDetectionEnable(int i);

    int setFilmmakerModeSelected(int i);

    int setFreeSyncSwitch(int i);

    int setGameDetectionEnable(int i);

    int setGammaAdjustment(int i);

    int setGammaCalibrationGain(int i);

    int setGammaCalibrationLevel(int i);

    int setGammaEnable(int i);

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation();

    int setHDMI20Format(int i);

    int setHdmiDynamicRange(int i);

    int setHue20(int i);

    int setHue80(int i);

    int setHueMax(int i);

    int setHueMid(int i);

    int setHueMin(int i);

    int setISFSwitch(int i);

    int setISFType(int i);

    int setInsidePattern(int i);

    int setInstantGameResponseSwitch(int i);

    int setItemData(int i, ArrayList<Integer> arrayList);

    int setItemString(int i, String str);

    int setItemTable(int i, int i2, int i3, int i4);

    int setItemValue(int i, int i2);

    void setItemValueAsync(int i, int i2);

    int setLDTestEnable(int i);

    int setLaserScreenSize(int i);

    int setLaserScreenType(int i);

    int setLightSensorALS10(int i);

    int setLightSensorALS300(int i);

    int setLightSensorALS80(int i);

    int setLightSensorALSMax(int i);

    int setLightSensorALSMin(int i);

    int setLightSensorCurve(int i, int i2, int i3);

    int setLightSensorEnable(int i);

    int setLightSensorLS10(int i);

    int setLightSensorLS300(int i);

    int setLightSensorLS80(int i);

    int setLightSensorLSMax(int i);

    int setLightSensorLSMin(int i);

    int setLightSensorMax(int i);

    int setLightSensorMin(int i);

    int setLinkSoundEnable(int i);

    int setLocalDimming(int i);

    int setLowBlueLight(int i);

    int setMemc(int i);

    int setMemcBlurReduction(int i);

    int setMemcJudderReduction(int i);

    int setMotionClearnessEnable(int i);

    int setMpegNoiseReduction(int i);

    int setMultiPointWhiteBalanceApplyToAll(int i);

    int setMultiPointWhiteBalanceBlue(int i);

    int setMultiPointWhiteBalanceEnable(int i);

    int setMultiPointWhiteBalanceGreen(int i);

    int setMultiPointWhiteBalanceLevel(int i);

    int setMultiPointWhiteBalanceRed(int i);

    int setNetVideoBeautySwitch(int i);

    int setNoiseReduction(int i);

    int setOLEDHDREnable(int i);

    int setOLEDLEAEnable(int i);

    int setOLEDLocalFrozenChkEnable(int i);

    int setOLEDLocalLumaChkEnable(int i);

    int setOLEDPLCCurve(int i);

    int setOLEDTPCEnable(int i);

    int setOutMode(int i);

    int setOverscanBottom(int i);

    int setOverscanEnable(int i);

    int setOverscanLeft(int i);

    int setOverscanRight(int i);

    int setOverscanTop(int i);

    int setPanel(int i);

    int setPeaking(int i);

    int setPictureMode(int i);

    int setPictureModeByFactory(int i);

    int setPictureSettingsToAllSource(int i);

    int setPixelShiftEnable(int i);

    int setPixelShiftShape(int i);

    int setPixelShiftTimer(int i);

    int setPixelShiftType(int i);

    int setPixelShiftX(int i);

    int setPixelShiftY(int i);

    int setPqEnable(int i);

    int setRGBPattern(int i, int i2, int i3, int i4);

    int setRgbOnly(int i);

    int setSaturation(int i);

    int setSaturation20(int i);

    int setSaturation80(int i);

    int setSaturationByFactory(int i);

    int setSaturationMax(int i);

    int setSaturationMid(int i);

    int setSaturationMin(int i);

    int setSceEnable(int i);

    int setScreenOutputDispRect(int i, int i2, int i3, int i4);

    int setSharpness(int i);

    int setSharpness20(int i);

    int setSharpness80(int i);

    int setSharpnessByFactory(int i);

    int setSharpnessMax(int i);

    int setSharpnessMid(int i);

    int setSharpnessMin(int i);

    int setStandyScreenStatus(int i);

    int setTestPattern(int i);

    int setTint(int i);

    int setTintByFactory(int i);

    int setTvsCtrlQDPQUpdateEnable(int i);

    int setUsageMode(int i);

    int setVcomTestEnable(int i);

    int setVideoMuteState(int i);

    int setWhiteBalanceGainBlue(int i);

    int setWhiteBalanceGainGreen(int i);

    int setWhiteBalanceGainRed(int i);

    int setWhiteBalanceOffsetBlue(int i);

    int setWhiteBalanceOffsetGreen(int i);

    int setWhiteBalanceOffsetRed(int i);

    int setWhiteBalanceiApplyToAll(int i);

    int setWhiteLevel(int i);

    int setWindowHSLRange(int i, int i2, int i3, int i4);

    int setXvyccEnable(int i);

    void testGetBacklightLinearEnable(testGetBacklightLinearEnableCallback testgetbacklightlinearenablecallback);

    void testGetColorTempCompenEnable(testGetColorTempCompenEnableCallback testgetcolortempcompenenablecallback);

    void testGetColorTempCompenGain(testGetColorTempCompenGainCallback testgetcolortempcompengaincallback);

    void testGetDCDimmingEnable(testGetDCDimmingEnableCallback testgetdcdimmingenablecallback);

    void testGetGammaIndex(testGetGammaIndexCallback testgetgammaindexcallback);

    void testGetGammaType(testGetGammaTypeCallback testgetgammatypecallback);

    void testGetOutputRegion(testGetOutputRegionCallback testgetoutputregioncallback);

    void testGetOverscanIndex(testGetOverscanIndexCallback testgetoverscanindexcallback);

    void testGetOverscanRatio(testGetOverscanRatioCallback testgetoverscanratiocallback);

    void testGetSCEIndex(testGetSCEIndexCallback testgetsceindexcallback);

    void testGetSignalDRType(testGetSignalDRTypeCallback testgetsignaldrtypecallback);

    void testGetSrouceRegion(testGetSrouceRegionCallback testgetsrouceregioncallback);

    void testGetUseRawDataEnable(testGetUseRawDataEnableCallback testgetuserawdataenablecallback);

    void testGetbacklightLinearGain(testGetbacklightLinearGainCallback testgetbacklightlineargaincallback);

    int testSetAPOContentType(int i);

    int testSetAPOWBType(int i);

    int testSetBacklightLinearEnable(int i);

    int testSetBacklightLinearGain(int i, int i2);

    int testSetColorTempCompenEnable(int i);

    int testSetColorTempCompenGain(int i, int i2, int i3);

    int testSetDCDimmingEnable(int i);

    int testSetGammaIndex(int i);

    int testSetGammaType(int i);

    int testSetOutputRegion(int i, int i2, int i3, int i4);

    int testSetOverscanIndex(int i);

    int testSetOverscanRatio(int i, int i2, int i3, int i4);

    int testSetSCEIndex(int i);

    int testSetSignalDRType(int i);

    int testSetSignalFilmmakerType(int i);

    int testSetSourceRegion(int i, int i2, int i3, int i4);

    int testSetUseRawDataEnable(int i);

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient);

    static IHisVideoApi getService(boolean z) {
        return getService("default", z);
    }

    static IHisVideoApi getService(String str) {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static IHisVideoApi getService() {
        return getService("default");
    }
}
