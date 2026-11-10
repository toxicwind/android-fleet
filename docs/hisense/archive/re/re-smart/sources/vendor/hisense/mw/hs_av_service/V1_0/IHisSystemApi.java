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
public interface IHisSystemApi extends IBase {
    public static final String kInterfaceName = "vendor.hisense.mw.hs_av_service@1.0::IHisSystemApi";

    @FunctionalInterface
    public interface ItemInOutCallback {
        void onValues(int i, ArrayList<Integer> arrayList);
    }

    public static final class Proxy implements IHisSystemApi {
        public IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public void ItemInOut(int i, ArrayList<Integer> arrayList, ItemInOutCallback itemInOutCallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32Vector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(26, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                itemInOutCallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int backupStoreSetting() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int backupUserSetting(String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public void getCurrentSource(getCurrentSourceCallback getcurrentsourcecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcurrentsourcecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public void getFactoryMode(getFactoryModeCallback getfactorymodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(8, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorymodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public void getHidlAVMiddlewareEnable(getHidlAVMiddlewareEnableCallback gethidlavmiddlewareenablecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethidlavmiddlewareenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public void getItemData(int i, getItemDataCallback getitemdatacallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(23, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemdatacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public void getItemString(int i, getItemStringCallback getitemstringcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(25, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemstringcallback.onValues(hwParcel2.readInt32(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public void getItemValue(int i, getItemValueCallback getitemvaluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(21, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemvaluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public void getUserMode(getUserModeCallback getusermodecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getusermodecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int registerCallback(IHisAVApiCallback iHisAVApiCallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeStrongBinder(iHisAVApiCallback == null ? null : iHisAVApiCallback.asBinder());
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int registerCallbackWithPid(IHisAVApiCallback iHisAVApiCallback, int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeStrongBinder(iHisAVApiCallback == null ? null : iHisAVApiCallback.asBinder());
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int resetFactoryOKSetting() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int resetFactoryUnprotectedSetting() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(13, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int resetStoreSetting(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int resetUserSetting() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int restoreUserSetting(String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeString(str);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int setCurrentSource(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int setFactoryMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int setItemData(int i, ArrayList<Integer> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32Vector(arrayList);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int setItemString(int i, String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int setItemValue(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public void setScreenOn(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(19, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int setUserMode(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.hisense.mw.hs_av_service@1.0::IHisSystemApi]@Proxy";
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int unRegisterCallback(IHisAVApiCallback iHisAVApiCallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeStrongBinder(iHisAVApiCallback == null ? null : iHisAVApiCallback.asBinder());
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi
        public int unRegisterCallbackWithPid(int i) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisSystemApi.kInterfaceName);
            hwParcel.writeInt32(i);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IHisSystemApi {
        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{-97, -1, -68, 73, Ascii.VT, 88, -88, -51, 93, -70, 60, -118, 38, 8, -4, -92, -29, -104, -12, Ascii.EM, 113, Ascii.SYN, 62, Ascii.SI, 50, 46, -11, 53, 79, -26, 93, 1}, new byte[]{-20, Ascii.DEL, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, Ascii.CAN, -54, 76}));
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IHisSystemApi.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IHisSystemApi.kInterfaceName;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public void onTransact(int i, HwParcel hwParcel, final HwParcel hwParcel2, int i2) {
            switch (i) {
                case 1:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int currentSource = setCurrentSource(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(currentSource);
                    hwParcel2.send();
                    return;
                case 2:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    getCurrentSource(new getCurrentSourceCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.Stub.1
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getCurrentSourceCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 3:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int userMode = setUserMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(userMode);
                    hwParcel2.send();
                    return;
                case 4:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    getUserMode(new getUserModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.Stub.2
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getUserModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 5:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iRegisterCallback = registerCallback(IHisAVApiCallback.asInterface(hwParcel.readStrongBinder()));
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iRegisterCallback);
                    hwParcel2.send();
                    return;
                case 6:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iUnRegisterCallback = unRegisterCallback(IHisAVApiCallback.asInterface(hwParcel.readStrongBinder()));
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iUnRegisterCallback);
                    hwParcel2.send();
                    return;
                case 7:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int factoryMode = setFactoryMode(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(factoryMode);
                    hwParcel2.send();
                    return;
                case 8:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    getFactoryMode(new getFactoryModeCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.Stub.3
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getFactoryModeCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 9:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    getHidlAVMiddlewareEnable(new getHidlAVMiddlewareEnableCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.Stub.4
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getHidlAVMiddlewareEnableCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 10:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iResetUserSetting = resetUserSetting();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetUserSetting);
                    hwParcel2.send();
                    return;
                case 11:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iResetStoreSetting = resetStoreSetting(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetStoreSetting);
                    hwParcel2.send();
                    return;
                case 12:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iResetFactoryOKSetting = resetFactoryOKSetting();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetFactoryOKSetting);
                    hwParcel2.send();
                    return;
                case 13:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iResetFactoryUnprotectedSetting = resetFactoryUnprotectedSetting();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iResetFactoryUnprotectedSetting);
                    hwParcel2.send();
                    return;
                case 14:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iRegisterCallbackWithPid = registerCallbackWithPid(IHisAVApiCallback.asInterface(hwParcel.readStrongBinder()), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iRegisterCallbackWithPid);
                    hwParcel2.send();
                    return;
                case 15:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iUnRegisterCallbackWithPid = unRegisterCallbackWithPid(hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iUnRegisterCallbackWithPid);
                    hwParcel2.send();
                    return;
                case 16:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iBackupStoreSetting = backupStoreSetting();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iBackupStoreSetting);
                    hwParcel2.send();
                    return;
                case 17:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iBackupUserSetting = backupUserSetting(hwParcel.readString());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iBackupUserSetting);
                    hwParcel2.send();
                    return;
                case 18:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int iRestoreUserSetting = restoreUserSetting(hwParcel.readString());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(iRestoreUserSetting);
                    hwParcel2.send();
                    return;
                case 19:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    setScreenOn(hwParcel.readInt32());
                    return;
                case 20:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int itemValue = setItemValue(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemValue);
                    hwParcel2.send();
                    return;
                case 21:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    getItemValue(hwParcel.readInt32(), new getItemValueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.Stub.5
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getItemValueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 22:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int itemData = setItemData(hwParcel.readInt32(), hwParcel.readInt32Vector());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemData);
                    hwParcel2.send();
                    return;
                case 23:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    getItemData(hwParcel.readInt32(), new getItemDataCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.Stub.6
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getItemDataCallback
                        public void onValues(int i3, ArrayList<Integer> arrayList) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32Vector(arrayList);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 24:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    int itemString = setItemString(hwParcel.readInt32(), hwParcel.readString());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemString);
                    hwParcel2.send();
                    return;
                case 25:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    getItemString(hwParcel.readInt32(), new getItemStringCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.Stub.7
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.getItemStringCallback
                        public void onValues(int i3, String str) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeString(str);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 26:
                    hwParcel.enforceInterface(IHisSystemApi.kInterfaceName);
                    ItemInOut(hwParcel.readInt32(), hwParcel.readInt32Vector(), new ItemInOutCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.Stub.8
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi.ItemInOutCallback
                        public void onValues(int i3, ArrayList<Integer> arrayList) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32Vector(arrayList);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        public IHwInterface queryLocalInterface(String str) {
            if (IHisSystemApi.kInterfaceName.equals(str)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String str) {
            registerService(str);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisSystemApi, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }
    }

    @FunctionalInterface
    public interface getCurrentSourceCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryModeCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHidlAVMiddlewareEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getItemDataCallback {
        void onValues(int i, ArrayList<Integer> arrayList);
    }

    @FunctionalInterface
    public interface getItemStringCallback {
        void onValues(int i, String str);
    }

    @FunctionalInterface
    public interface getItemValueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getUserModeCallback {
        void onValues(int i, int i2);
    }

    static IHisSystemApi asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisSystemApi)) {
            return (IHisSystemApi) iHwInterfaceQueryLocalInterface;
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

    static IHisSystemApi castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static IHisSystemApi getService(String str, boolean z) {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    void ItemInOut(int i, ArrayList<Integer> arrayList, ItemInOutCallback itemInOutCallback);

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    int backupStoreSetting();

    int backupUserSetting(String str);

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList);

    void getCurrentSource(getCurrentSourceCallback getcurrentsourcecallback);

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo();

    void getFactoryMode(getFactoryModeCallback getfactorymodecallback);

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain();

    void getHidlAVMiddlewareEnable(getHidlAVMiddlewareEnableCallback gethidlavmiddlewareenablecallback);

    void getItemData(int i, getItemDataCallback getitemdatacallback);

    void getItemString(int i, getItemStringCallback getitemstringcallback);

    void getItemValue(int i, getItemValueCallback getitemvaluecallback);

    void getUserMode(getUserModeCallback getusermodecallback);

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

    int registerCallback(IHisAVApiCallback iHisAVApiCallback);

    int registerCallbackWithPid(IHisAVApiCallback iHisAVApiCallback, int i);

    int resetFactoryOKSetting();

    int resetFactoryUnprotectedSetting();

    int resetStoreSetting(int i);

    int resetUserSetting();

    int restoreUserSetting(String str);

    int setCurrentSource(int i);

    int setFactoryMode(int i);

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation();

    int setItemData(int i, ArrayList<Integer> arrayList);

    int setItemString(int i, String str);

    int setItemValue(int i, int i2);

    void setScreenOn(int i);

    int setUserMode(int i);

    int unRegisterCallback(IHisAVApiCallback iHisAVApiCallback);

    int unRegisterCallbackWithPid(int i);

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient);

    static IHisSystemApi getService(boolean z) {
        return getService("default", z);
    }

    static IHisSystemApi getService(String str) {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static IHisSystemApi getService() {
        return getService("default");
    }
}
