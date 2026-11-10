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
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public interface IHisAudioApi extends IBase {
    public static final String kInterfaceName = "vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi";

    public static final class Proxy implements IHisAudioApi {
        public IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int ResetEQ() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int RestoreSound() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int SetHpDrc(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int SetHpDrcPostGain(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(100, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int SetHpMaxVol(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int SetHpPrescale(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(98, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int UpdateAmpInit(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int backupAQSetting() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(102, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getAGC(getAGCCallback getagccallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(25, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getagccallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getAVCFeature(int i, getAVCFeatureCallback getavcfeaturecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getAmpDrcH(getAmpDrcHCallback getampdrchcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(85, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getampdrchcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getAmpDrcL(getAmpDrcLCallback getampdrclcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(83, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getampdrclcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getAmpDrcP(getAmpDrcPCallback getampdrcpcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(89, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getampdrcpcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getAmpDrcS(getAmpDrcSCallback getampdrcscallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(87, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getampdrcscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getAmpGain(getAmpGainCallback getampgaincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(91, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getampgaincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getAmpPrescaler(getAmpPrescalerCallback getampprescalercallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(93, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getampprescalercallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getApplySoundMode(getApplySoundModeCallback getapplysoundmodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(32, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getapplysoundmodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getBalance(getBalanceCallback getbalancecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getBassBoost(getBassBoostCallback getbassboostcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(36, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbassboostcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getCodecType(getCodecTypeCallback getcodectypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(94, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcodectypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getCurrAMPType(getCurrAMPTypeCallback getcurramptypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(79, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcurramptypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getCurrSoundEffect(getCurrSoundEffectCallback getcurrsoundeffectcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(78, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcurrsoundeffectcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getDBXFeature(int i, getDBXFeatureCallback getdbxfeaturecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(38, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdbxfeaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getDTSFeature(int i, getDTSFeatureCallback getdtsfeaturecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(40, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdtsfeaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getDolbyAudioFeature(int i, getDolbyAudioFeatureCallback getdolbyaudiofeaturecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(42, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdolbyaudiofeaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getGEQ(int i, getGEQCallback getgeqcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getHeadphoneMode(getHeadphoneModeCallback getheadphonemodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(17, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getheadphonemodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getHeadphoneVol(getHeadphoneVolCallback getheadphonevolcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(19, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getheadphonevolcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getIsBTSupport(getIsBTSupportCallback getisbtsupportcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(73, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getisbtsupportcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getIsHPAMPSupport(getIsHPAMPSupportCallback getishpampsupportcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(76, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getishpampsupportcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getIsHPSupport(getIsHPSupportCallback getishpsupportcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(75, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getishpsupportcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getIsSubwooferSupport(getIsSubwooferSupportCallback getissubwoofersupportcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getLineOut(getLineOutCallback getlineoutcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(21, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlineoutcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getLipSync(getLipSyncCallback getlipsynccallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlipsynccallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getMultiBalance(getMultiBalanceCallback getmultibalancecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(107, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultibalancecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getMultiDigitalAMPValueByDevId(int i, int i2, getMultiDigitalAMPValueByDevIdCallback getmultidigitalampvaluebydevidcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(105, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getmultidigitalampvaluebydevidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSPDefaultVol(getSPDefaultVolCallback getspdefaultvolcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(77, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getspdefaultvolcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundCommonInf(int i, int i2, int i3, getSoundCommonInfCallback getsoundcommoninfcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSoundEffectFeature1(int i, getSoundEffectFeature1Callback getsoundeffectfeature1callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(44, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundeffectfeature1callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundEffectFeature2(int i, getSoundEffectFeature2Callback getsoundeffectfeature2callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(46, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundeffectfeature2callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundEffectFeature3(int i, getSoundEffectFeature3Callback getsoundeffectfeature3callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(48, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundeffectfeature3callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundMainAMPValue(int i, getSoundMainAMPValueCallback getsoundmainampvaluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(54, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundmainampvaluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundMode(getSoundModeCallback getsoundmodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSoundSocDRCFeature(int i, getSoundSocDRCFeatureCallback getsoundsocdrcfeaturecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSoundSocHPFFeature(int i, getSoundSocHPFFeatureCallback getsoundsochpffeaturecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSoundSocPEQEnable(getSoundSocPEQEnableCallback getsoundsocpeqenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSoundSocPEQFreq(int i, getSoundSocPEQFreqCallback getsoundsocpeqfreqcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSoundSocPEQGain(int i, getSoundSocPEQGainCallback getsoundsocpeqgaincallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSoundSocPEQQ(int i, getSoundSocPEQQCallback getsoundsocpeqqcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSoundSocPrescale(getSoundSocPrescaleCallback getsoundsocprescalecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSoundSubAMPValue(int i, getSoundSubAMPValueCallback getsoundsubampvaluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(56, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsoundsubampvaluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSoundVolCurveVal(int i, getSoundVolCurveValCallback getsoundvolcurvevalcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public void getSpdifDelay(getSpdifDelayCallback getspdifdelaycallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(15, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getspdifdelaycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSpdifType(getSpdifTypeCallback getspdiftypecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(13, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getspdiftypecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSpeakerDevices(getSpeakerDevicesCallback getspeakerdevicescallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getspeakerdevicescallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSpeakerEnable(getSpeakerEnableCallback getspeakerenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(96, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getspeakerenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getSubwoofer(getSubwooferCallback getsubwoofercallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(34, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsubwoofercallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getVolumeShift(getVolumeShiftCallback getvolumeshiftcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(30, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getvolumeshiftcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public void getWallMode(getWallModeCallback getwallmodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getwallmodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int restoreAQSetting() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setACRSwitch(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(70, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setAGC(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setAVCFeature(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setAmpDrcH(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setAmpDrcL(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setAmpDrcP(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setAmpDrcS(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setAmpGain(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setAmpPrescaler(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setApplySoundMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(31, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setBalance(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setBassBoost(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(35, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setDBXFeature(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(37, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setDTSFeature(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(39, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setDolbyAudioFeature(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setGEQ(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setHeadphoneMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setHeadphoneVol(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setIsFacMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setLineOut(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setLipSync(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setMultiBalance(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(106, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setMultiDigitalAMPValueByDevId(int i, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSoundCommonInf(int i, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundEffectFeature1(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundEffectFeature2(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundEffectFeature3(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundMainAMPValue(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSoundSocDRCFeature(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSoundSocHPFFeature(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSoundSocPEQEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSoundSocPEQFreq(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSoundSocPEQGain(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSoundSocPEQQ(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSoundSocPrescale(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSoundSubAMPValue(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSoundVolCurveVal(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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
        public int setSpdifDelay(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSpdifType(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSpeakerDevices(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSpeakerEnable(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setStrResume(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setStrSusspend(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(72, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setSubwoofer(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(33, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setVolumeShift(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(29, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi
        public int setWallMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisAudioApi.kInterfaceName);
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

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.hisense.mw.hs_av_service@1.0::IHisAudioApi]@Proxy";
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IHisAudioApi {
        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{-23, 122, -91, -75, Ascii.RS, 113, -110, Ascii.DEL, Ascii.FS, Ascii.DC2, -22, -37, 61, 102, -120, 43, -96, -4, -125, 55, 10, 56, -86, 89, -113, 85, 118, -106, -41, 89, -31, -30}, new byte[]{-20, Ascii.DEL, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, Ascii.CAN, -54, 76}));
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IHisAudioApi.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IHisAudioApi.kInterfaceName;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public void onTransact(int i, HwParcel hwParcel, final HwParcel hwParcel2, int i2) {
            switch (i) {
                case 1:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundMode = setSoundMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundMode);
                    hwParcel2.send();
                    return;
                case 2:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundMode(new getSoundModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.1
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 3:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int geq = setGEQ(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(geq);
                    hwParcel2.send();
                    return;
                case 4:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getGEQ(hwParcel.readInt32(), new getGEQCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.2
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getGEQCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 5:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int iResetEQ = ResetEQ();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetEQ);
                    hwParcel2.send();
                    return;
                case 6:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int wallMode = setWallMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(wallMode);
                    hwParcel2.send();
                    return;
                case 7:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getWallMode(new getWallModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.3
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getWallModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 8:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int speakerDevices = setSpeakerDevices(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(speakerDevices);
                    hwParcel2.send();
                    return;
                case 9:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSpeakerDevices(new getSpeakerDevicesCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.4
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSpeakerDevicesCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 10:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int lipSync = setLipSync(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lipSync);
                    hwParcel2.send();
                    return;
                case 11:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getLipSync(new getLipSyncCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.5
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getLipSyncCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 12:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int spdifType = setSpdifType(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(spdifType);
                    hwParcel2.send();
                    return;
                case 13:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSpdifType(new getSpdifTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.6
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSpdifTypeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 14:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int spdifDelay = setSpdifDelay(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(spdifDelay);
                    hwParcel2.send();
                    return;
                case 15:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSpdifDelay(new getSpdifDelayCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.7
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSpdifDelayCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 16:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int headphoneMode = setHeadphoneMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(headphoneMode);
                    hwParcel2.send();
                    return;
                case 17:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getHeadphoneMode(new getHeadphoneModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.8
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getHeadphoneModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 18:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int headphoneVol = setHeadphoneVol(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(headphoneVol);
                    hwParcel2.send();
                    return;
                case 19:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getHeadphoneVol(new getHeadphoneVolCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.9
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getHeadphoneVolCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 20:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int lineOut = setLineOut(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(lineOut);
                    hwParcel2.send();
                    return;
                case 21:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getLineOut(new getLineOutCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.10
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getLineOutCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 22:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int aVCFeature = setAVCFeature(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(aVCFeature);
                    hwParcel2.send();
                    return;
                case 23:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getAVCFeature(hwParcel.readInt32(), new getAVCFeatureCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.11
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAVCFeatureCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 24:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int agc = setAGC(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(agc);
                    hwParcel2.send();
                    return;
                case 25:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getAGC(new getAGCCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.12
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAGCCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 26:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int balance = setBalance(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(balance);
                    hwParcel2.send();
                    return;
                case 27:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getBalance(new getBalanceCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.13
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getBalanceCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 28:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int iRestoreSound = RestoreSound();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iRestoreSound);
                    hwParcel2.send();
                    return;
                case 29:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int volumeShift = setVolumeShift(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(volumeShift);
                    hwParcel2.send();
                    return;
                case 30:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getVolumeShift(new getVolumeShiftCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.14
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getVolumeShiftCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 31:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int applySoundMode = setApplySoundMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(applySoundMode);
                    hwParcel2.send();
                    return;
                case 32:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getApplySoundMode(new getApplySoundModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.15
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getApplySoundModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 33:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int subwoofer = setSubwoofer(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(subwoofer);
                    hwParcel2.send();
                    return;
                case 34:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSubwoofer(new getSubwooferCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.16
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSubwooferCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 35:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int bassBoost = setBassBoost(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(bassBoost);
                    hwParcel2.send();
                    return;
                case 36:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getBassBoost(new getBassBoostCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.17
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getBassBoostCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 37:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int dBXFeature = setDBXFeature(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dBXFeature);
                    hwParcel2.send();
                    return;
                case 38:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getDBXFeature(hwParcel.readInt32(), new getDBXFeatureCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.18
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getDBXFeatureCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 39:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int dTSFeature = setDTSFeature(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dTSFeature);
                    hwParcel2.send();
                    return;
                case 40:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getDTSFeature(hwParcel.readInt32(), new getDTSFeatureCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.19
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getDTSFeatureCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 41:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int dolbyAudioFeature = setDolbyAudioFeature(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(dolbyAudioFeature);
                    hwParcel2.send();
                    return;
                case 42:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getDolbyAudioFeature(hwParcel.readInt32(), new getDolbyAudioFeatureCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.20
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getDolbyAudioFeatureCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 43:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundEffectFeature1 = setSoundEffectFeature1(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundEffectFeature1);
                    hwParcel2.send();
                    return;
                case 44:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundEffectFeature1(hwParcel.readInt32(), new getSoundEffectFeature1Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.21
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundEffectFeature1Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 45:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundEffectFeature2 = setSoundEffectFeature2(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundEffectFeature2);
                    hwParcel2.send();
                    return;
                case 46:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundEffectFeature2(hwParcel.readInt32(), new getSoundEffectFeature2Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.22
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundEffectFeature2Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 47:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundEffectFeature3 = setSoundEffectFeature3(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundEffectFeature3);
                    hwParcel2.send();
                    return;
                case 48:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundEffectFeature3(hwParcel.readInt32(), new getSoundEffectFeature3Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.23
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundEffectFeature3Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 49:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundVolCurveVal = setSoundVolCurveVal(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundVolCurveVal);
                    hwParcel2.send();
                    return;
                case 50:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundVolCurveVal(hwParcel.readInt32(), new getSoundVolCurveValCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.24
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundVolCurveValCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 51:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundSocPrescale = setSoundSocPrescale(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundSocPrescale);
                    hwParcel2.send();
                    return;
                case 52:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundSocPrescale(new getSoundSocPrescaleCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.25
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPrescaleCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 53:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundMainAMPValue = setSoundMainAMPValue(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundMainAMPValue);
                    hwParcel2.send();
                    return;
                case 54:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundMainAMPValue(hwParcel.readInt32(), new getSoundMainAMPValueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.26
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundMainAMPValueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 55:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundSubAMPValue = setSoundSubAMPValue(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundSubAMPValue);
                    hwParcel2.send();
                    return;
                case 56:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundSubAMPValue(hwParcel.readInt32(), new getSoundSubAMPValueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.27
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSubAMPValueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 57:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundSocDRCFeature = setSoundSocDRCFeature(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundSocDRCFeature);
                    hwParcel2.send();
                    return;
                case 58:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundSocDRCFeature(hwParcel.readInt32(), new getSoundSocDRCFeatureCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.28
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocDRCFeatureCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 59:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundSocHPFFeature = setSoundSocHPFFeature(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundSocHPFFeature);
                    hwParcel2.send();
                    return;
                case 60:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundSocHPFFeature(hwParcel.readInt32(), new getSoundSocHPFFeatureCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.29
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocHPFFeatureCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 61:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundSocPEQFreq = setSoundSocPEQFreq(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundSocPEQFreq);
                    hwParcel2.send();
                    return;
                case 62:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundSocPEQFreq(hwParcel.readInt32(), new getSoundSocPEQFreqCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.30
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQFreqCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 63:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundSocPEQGain = setSoundSocPEQGain(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundSocPEQGain);
                    hwParcel2.send();
                    return;
                case 64:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundSocPEQGain(hwParcel.readInt32(), new getSoundSocPEQGainCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.31
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQGainCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 65:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundSocPEQQ = setSoundSocPEQQ(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundSocPEQQ);
                    hwParcel2.send();
                    return;
                case 66:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundSocPEQQ(hwParcel.readInt32(), new getSoundSocPEQQCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.32
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQQCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 67:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundSocPEQEnable = setSoundSocPEQEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundSocPEQEnable);
                    hwParcel2.send();
                    return;
                case 68:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundSocPEQEnable(new getSoundSocPEQEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.33
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundSocPEQEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 69:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int isFacMode = setIsFacMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(isFacMode);
                    hwParcel2.send();
                    return;
                case 70:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int aCRSwitch = setACRSwitch(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(aCRSwitch);
                    hwParcel2.send();
                    return;
                case 71:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int strResume = setStrResume(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(strResume);
                    hwParcel2.send();
                    return;
                case 72:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int strSusspend = setStrSusspend(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(strSusspend);
                    hwParcel2.send();
                    return;
                case 73:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getIsBTSupport(new getIsBTSupportCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.34
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getIsBTSupportCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 74:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getIsSubwooferSupport(new getIsSubwooferSupportCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.35
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getIsSubwooferSupportCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 75:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getIsHPSupport(new getIsHPSupportCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.36
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getIsHPSupportCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 76:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getIsHPAMPSupport(new getIsHPAMPSupportCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.37
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getIsHPAMPSupportCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 77:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSPDefaultVol(new getSPDefaultVolCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.38
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSPDefaultVolCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 78:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getCurrSoundEffect(new getCurrSoundEffectCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.39
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getCurrSoundEffectCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 79:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getCurrAMPType(new getCurrAMPTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.40
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getCurrAMPTypeCallback
                        public void onValues(int i3, int i4, int i5) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.writeInt32(i5);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 80:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int soundCommonInf = setSoundCommonInf(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(soundCommonInf);
                    hwParcel2.send();
                    return;
                case 81:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSoundCommonInf(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32(), new getSoundCommonInfCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.41
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSoundCommonInfCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 82:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int ampDrcL = setAmpDrcL(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(ampDrcL);
                    hwParcel2.send();
                    return;
                case 83:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getAmpDrcL(new getAmpDrcLCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.42
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpDrcLCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 84:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int ampDrcH = setAmpDrcH(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(ampDrcH);
                    hwParcel2.send();
                    return;
                case 85:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getAmpDrcH(new getAmpDrcHCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.43
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpDrcHCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 86:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int ampDrcS = setAmpDrcS(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(ampDrcS);
                    hwParcel2.send();
                    return;
                case 87:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getAmpDrcS(new getAmpDrcSCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.44
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpDrcSCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 88:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int ampDrcP = setAmpDrcP(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(ampDrcP);
                    hwParcel2.send();
                    return;
                case 89:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getAmpDrcP(new getAmpDrcPCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.45
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpDrcPCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 90:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int ampGain = setAmpGain(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(ampGain);
                    hwParcel2.send();
                    return;
                case 91:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getAmpGain(new getAmpGainCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.46
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpGainCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 92:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int ampPrescaler = setAmpPrescaler(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(ampPrescaler);
                    hwParcel2.send();
                    return;
                case 93:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getAmpPrescaler(new getAmpPrescalerCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.47
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getAmpPrescalerCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 94:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getCodecType(new getCodecTypeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.48
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getCodecTypeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 95:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int speakerEnable = setSpeakerEnable(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(speakerEnable);
                    hwParcel2.send();
                    return;
                case 96:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getSpeakerEnable(new getSpeakerEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.49
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getSpeakerEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 97:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int iSetHpMaxVol = SetHpMaxVol(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iSetHpMaxVol);
                    hwParcel2.send();
                    return;
                case 98:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int iSetHpPrescale = SetHpPrescale(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iSetHpPrescale);
                    hwParcel2.send();
                    return;
                case 99:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int iSetHpDrc = SetHpDrc(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iSetHpDrc);
                    hwParcel2.send();
                    return;
                case 100:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int iSetHpDrcPostGain = SetHpDrcPostGain(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iSetHpDrcPostGain);
                    hwParcel2.send();
                    return;
                case 101:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int iUpdateAmpInit = UpdateAmpInit(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iUpdateAmpInit);
                    hwParcel2.send();
                    return;
                case 102:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int iBackupAQSetting = backupAQSetting();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iBackupAQSetting);
                    hwParcel2.send();
                    return;
                case 103:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int iRestoreAQSetting = restoreAQSetting();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iRestoreAQSetting);
                    hwParcel2.send();
                    return;
                case 104:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int multiDigitalAMPValueByDevId = setMultiDigitalAMPValueByDevId(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(multiDigitalAMPValueByDevId);
                    hwParcel2.send();
                    return;
                case 105:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getMultiDigitalAMPValueByDevId(hwParcel.readInt32(), hwParcel.readInt32(), new getMultiDigitalAMPValueByDevIdCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.50
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getMultiDigitalAMPValueByDevIdCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 106:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    int multiBalance = setMultiBalance(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(multiBalance);
                    hwParcel2.send();
                    return;
                case 107:
                    hwParcel.enforceInterface(IHisAudioApi.kInterfaceName);
                    getMultiBalance(new getMultiBalanceCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.Stub.51
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi.getMultiBalanceCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
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
                            for (int i3 = 0; i3 < size; i3++) {
                                long j = i3 * 32;
                                byte[] bArr = hashChain.get(i3);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        public IHwInterface queryLocalInterface(String str) {
            if (IHisAudioApi.kInterfaceName.equals(str)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String str) {
            registerService(str);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisAudioApi, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }
    }

    @FunctionalInterface
    public interface getAGCCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAVCFeatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAmpDrcHCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAmpDrcLCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAmpDrcPCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAmpDrcSCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAmpGainCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAmpPrescalerCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getApplySoundModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBalanceCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBassBoostCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getCodecTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getCurrAMPTypeCallback {
        void onValues(int i, int i2, int i3);
    }

    @FunctionalInterface
    public interface getCurrSoundEffectCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDBXFeatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDTSFeatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDolbyAudioFeatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGEQCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHeadphoneModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHeadphoneVolCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getIsBTSupportCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getIsHPAMPSupportCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getIsHPSupportCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getIsSubwooferSupportCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLineOutCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLipSyncCallback {
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
    public interface getSPDefaultVolCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundCommonInfCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundEffectFeature1Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundEffectFeature2Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundEffectFeature3Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundMainAMPValueCallback {
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
    public interface getSoundSubAMPValueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSoundVolCurveValCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSpdifDelayCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSpdifTypeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSpeakerDevicesCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSpeakerEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSubwooferCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getVolumeShiftCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getWallModeCallback {
        void onValues(int i, int i2);
    }

    static IHisAudioApi asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisAudioApi)) {
            return (IHisAudioApi) iHwInterfaceQueryLocalInterface;
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

    static IHisAudioApi castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static IHisAudioApi getService(String str, boolean z) {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    int ResetEQ();

    int RestoreSound();

    int SetHpDrc(int i);

    int SetHpDrcPostGain(int i);

    int SetHpMaxVol(int i);

    int SetHpPrescale(int i);

    int UpdateAmpInit(int i);

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    int backupAQSetting();

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList);

    void getAGC(getAGCCallback getagccallback);

    void getAVCFeature(int i, getAVCFeatureCallback getavcfeaturecallback);

    void getAmpDrcH(getAmpDrcHCallback getampdrchcallback);

    void getAmpDrcL(getAmpDrcLCallback getampdrclcallback);

    void getAmpDrcP(getAmpDrcPCallback getampdrcpcallback);

    void getAmpDrcS(getAmpDrcSCallback getampdrcscallback);

    void getAmpGain(getAmpGainCallback getampgaincallback);

    void getAmpPrescaler(getAmpPrescalerCallback getampprescalercallback);

    void getApplySoundMode(getApplySoundModeCallback getapplysoundmodecallback);

    void getBalance(getBalanceCallback getbalancecallback);

    void getBassBoost(getBassBoostCallback getbassboostcallback);

    void getCodecType(getCodecTypeCallback getcodectypecallback);

    void getCurrAMPType(getCurrAMPTypeCallback getcurramptypecallback);

    void getCurrSoundEffect(getCurrSoundEffectCallback getcurrsoundeffectcallback);

    void getDBXFeature(int i, getDBXFeatureCallback getdbxfeaturecallback);

    void getDTSFeature(int i, getDTSFeatureCallback getdtsfeaturecallback);

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo();

    void getDolbyAudioFeature(int i, getDolbyAudioFeatureCallback getdolbyaudiofeaturecallback);

    void getGEQ(int i, getGEQCallback getgeqcallback);

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain();

    void getHeadphoneMode(getHeadphoneModeCallback getheadphonemodecallback);

    void getHeadphoneVol(getHeadphoneVolCallback getheadphonevolcallback);

    void getIsBTSupport(getIsBTSupportCallback getisbtsupportcallback);

    void getIsHPAMPSupport(getIsHPAMPSupportCallback getishpampsupportcallback);

    void getIsHPSupport(getIsHPSupportCallback getishpsupportcallback);

    void getIsSubwooferSupport(getIsSubwooferSupportCallback getissubwoofersupportcallback);

    void getLineOut(getLineOutCallback getlineoutcallback);

    void getLipSync(getLipSyncCallback getlipsynccallback);

    void getMultiBalance(getMultiBalanceCallback getmultibalancecallback);

    void getMultiDigitalAMPValueByDevId(int i, int i2, getMultiDigitalAMPValueByDevIdCallback getmultidigitalampvaluebydevidcallback);

    void getSPDefaultVol(getSPDefaultVolCallback getspdefaultvolcallback);

    void getSoundCommonInf(int i, int i2, int i3, getSoundCommonInfCallback getsoundcommoninfcallback);

    void getSoundEffectFeature1(int i, getSoundEffectFeature1Callback getsoundeffectfeature1callback);

    void getSoundEffectFeature2(int i, getSoundEffectFeature2Callback getsoundeffectfeature2callback);

    void getSoundEffectFeature3(int i, getSoundEffectFeature3Callback getsoundeffectfeature3callback);

    void getSoundMainAMPValue(int i, getSoundMainAMPValueCallback getsoundmainampvaluecallback);

    void getSoundMode(getSoundModeCallback getsoundmodecallback);

    void getSoundSocDRCFeature(int i, getSoundSocDRCFeatureCallback getsoundsocdrcfeaturecallback);

    void getSoundSocHPFFeature(int i, getSoundSocHPFFeatureCallback getsoundsochpffeaturecallback);

    void getSoundSocPEQEnable(getSoundSocPEQEnableCallback getsoundsocpeqenablecallback);

    void getSoundSocPEQFreq(int i, getSoundSocPEQFreqCallback getsoundsocpeqfreqcallback);

    void getSoundSocPEQGain(int i, getSoundSocPEQGainCallback getsoundsocpeqgaincallback);

    void getSoundSocPEQQ(int i, getSoundSocPEQQCallback getsoundsocpeqqcallback);

    void getSoundSocPrescale(getSoundSocPrescaleCallback getsoundsocprescalecallback);

    void getSoundSubAMPValue(int i, getSoundSubAMPValueCallback getsoundsubampvaluecallback);

    void getSoundVolCurveVal(int i, getSoundVolCurveValCallback getsoundvolcurvevalcallback);

    void getSpdifDelay(getSpdifDelayCallback getspdifdelaycallback);

    void getSpdifType(getSpdifTypeCallback getspdiftypecallback);

    void getSpeakerDevices(getSpeakerDevicesCallback getspeakerdevicescallback);

    void getSpeakerEnable(getSpeakerEnableCallback getspeakerenablecallback);

    void getSubwoofer(getSubwooferCallback getsubwoofercallback);

    void getVolumeShift(getVolumeShiftCallback getvolumeshiftcallback);

    void getWallMode(getWallModeCallback getwallmodecallback);

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain();

    @Override // android.hidl.base.V1_0.IBase
    String interfaceDescriptor();

    @Override // android.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j);

    @Override // android.hidl.base.V1_0.IBase
    void notifySyspropsChanged();

    @Override // android.hidl.base.V1_0.IBase
    void ping();

    int restoreAQSetting();

    int setACRSwitch(int i);

    int setAGC(int i);

    int setAVCFeature(int i, int i2);

    int setAmpDrcH(int i);

    int setAmpDrcL(int i);

    int setAmpDrcP(int i);

    int setAmpDrcS(int i);

    int setAmpGain(int i);

    int setAmpPrescaler(int i);

    int setApplySoundMode(int i);

    int setBalance(int i);

    int setBassBoost(int i);

    int setDBXFeature(int i, int i2);

    int setDTSFeature(int i, int i2);

    int setDolbyAudioFeature(int i, int i2);

    int setGEQ(int i, int i2);

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation();

    int setHeadphoneMode(int i);

    int setHeadphoneVol(int i);

    int setIsFacMode(int i);

    int setLineOut(int i);

    int setLipSync(int i);

    int setMultiBalance(int i);

    int setMultiDigitalAMPValueByDevId(int i, int i2, int i3);

    int setSoundCommonInf(int i, int i2, int i3);

    int setSoundEffectFeature1(int i, int i2);

    int setSoundEffectFeature2(int i, int i2);

    int setSoundEffectFeature3(int i, int i2);

    int setSoundMainAMPValue(int i, int i2);

    int setSoundMode(int i);

    int setSoundSocDRCFeature(int i, int i2);

    int setSoundSocHPFFeature(int i, int i2);

    int setSoundSocPEQEnable(int i);

    int setSoundSocPEQFreq(int i, int i2);

    int setSoundSocPEQGain(int i, int i2);

    int setSoundSocPEQQ(int i, int i2);

    int setSoundSocPrescale(int i);

    int setSoundSubAMPValue(int i, int i2);

    int setSoundVolCurveVal(int i, int i2);

    int setSpdifDelay(int i);

    int setSpdifType(int i);

    int setSpeakerDevices(int i);

    int setSpeakerEnable(int i);

    int setStrResume(int i);

    int setStrSusspend(int i);

    int setSubwoofer(int i);

    int setVolumeShift(int i);

    int setWallMode(int i);

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient);

    static IHisAudioApi getService(boolean z) {
        return getService("default", z);
    }

    static IHisAudioApi getService(String str) {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static IHisAudioApi getService() {
        return getService("default");
    }
}
