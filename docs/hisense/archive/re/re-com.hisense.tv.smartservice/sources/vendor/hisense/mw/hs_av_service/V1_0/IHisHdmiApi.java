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
import com.google.common.primitives.SignedBytes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public interface IHisHdmiApi extends IBase {
    public static final String kInterfaceName = "vendor.hisense.mw.hs_av_service@1.0::IHisHdmiApi";

    public static final class Proxy implements IHisHdmiApi {
        public IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public void getCurrentEdidByport(getCurrentEdidByportCallback getcurrentedidbyportcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcurrentedidbyportcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt8Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public void getItem2Value(int i, getItem2ValueCallback getitem2valuecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(11, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitem2valuecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public void getItemData(int i, getItemDataCallback getitemdatacallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(4, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemdatacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public void getItemString(int i, getItemStringCallback getitemstringcallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(9, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemstringcallback.onValues(hwParcel2.readInt32(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public void getItemValue(int i, getItemValueCallback getitemvaluecallback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
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
        public void getItemValueByArg1(int i, int i2, getItemValueByArg1Callback getitemvaluebyarg1callback) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemvaluebyarg1callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public int setCurrentEdidByport() {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public int setItem2Value(int i, int i2, int i3) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public int setItemData(int i, ArrayList<Integer> arrayList) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeInt32Vector(arrayList);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public int setItemString(int i, String str) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi
        public int setItemValue(int i, int i2) {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IHisHdmiApi.kInterfaceName);
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

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.hisense.mw.hs_av_service@1.0::IHisHdmiApi]@Proxy";
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IHisHdmiApi {
        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{89, -71, 75, -17, 73, -24, -98, -27, -58, -3, SignedBytes.MAX_POWER_OF_TWO, 37, 107, 59, -38, 10, -44, -19, -92, -8, -72, -80, 82, -88, -34, -58, Ascii.ETB, 117, -53, 78, -27, -44}, new byte[]{-20, Ascii.DEL, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, Ascii.CAN, -54, 76}));
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IHisHdmiApi.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IHisHdmiApi.kInterfaceName;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        public void onTransact(int i, HwParcel hwParcel, final HwParcel hwParcel2, int i2) {
            switch (i) {
                case 1:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    getCurrentEdidByport(new getCurrentEdidByportCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.Stub.1
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getCurrentEdidByportCallback
                        public void onValues(int i3, ArrayList<Byte> arrayList) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt8Vector(arrayList);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 2:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    int currentEdidByport = setCurrentEdidByport();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(currentEdidByport);
                    hwParcel2.send();
                    return;
                case 3:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    int itemData = setItemData(hwParcel.readInt32(), hwParcel.readInt32Vector());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemData);
                    hwParcel2.send();
                    return;
                case 4:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    getItemData(hwParcel.readInt32(), new getItemDataCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.Stub.2
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemDataCallback
                        public void onValues(int i3, ArrayList<Integer> arrayList) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32Vector(arrayList);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 5:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    int itemValue = setItemValue(hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemValue);
                    hwParcel2.send();
                    return;
                case 6:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    getItemValue(hwParcel.readInt32(), new getItemValueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.Stub.3
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueCallback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 7:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    getItemValueByArg1(hwParcel.readInt32(), hwParcel.readInt32(), new getItemValueByArg1Callback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.Stub.4
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemValueByArg1Callback
                        public void onValues(int i3, int i4) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeInt32(i4);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 8:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    int itemString = setItemString(hwParcel.readInt32(), hwParcel.readString());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(itemString);
                    hwParcel2.send();
                    return;
                case 9:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    getItemString(hwParcel.readInt32(), new getItemStringCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.Stub.5
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItemStringCallback
                        public void onValues(int i3, String str) {
                            hwParcel2.writeStatus(0);
                            hwParcel2.writeInt32(i3);
                            hwParcel2.writeString(str);
                            hwParcel2.send();
                        }
                    });
                    return;
                case 10:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    int item2Value = setItem2Value(hwParcel.readInt32(), hwParcel.readInt32(), hwParcel.readInt32());
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeInt32(item2Value);
                    hwParcel2.send();
                    return;
                case 11:
                    hwParcel.enforceInterface(IHisHdmiApi.kInterfaceName);
                    getItem2Value(hwParcel.readInt32(), new getItem2ValueCallback() { // from class: vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.Stub.6
                        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi.getItem2ValueCallback
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

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        public IHwInterface queryLocalInterface(String str) {
            if (IHisHdmiApi.kInterfaceName.equals(str)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String str) {
            registerService(str);
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisHdmiApi, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }
    }

    @FunctionalInterface
    public interface getCurrentEdidByportCallback {
        void onValues(int i, ArrayList<Byte> arrayList);
    }

    @FunctionalInterface
    public interface getItem2ValueCallback {
        void onValues(int i, ArrayList<Integer> arrayList);
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
    public interface getItemValueByArg1Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getItemValueCallback {
        void onValues(int i, int i2);
    }

    static IHisHdmiApi asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisHdmiApi)) {
            return (IHisHdmiApi) iHwInterfaceQueryLocalInterface;
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

    static IHisHdmiApi castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static IHisHdmiApi getService(String str, boolean z) {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList);

    void getCurrentEdidByport(getCurrentEdidByportCallback getcurrentedidbyportcallback);

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo();

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain();

    void getItem2Value(int i, getItem2ValueCallback getitem2valuecallback);

    void getItemData(int i, getItemDataCallback getitemdatacallback);

    void getItemString(int i, getItemStringCallback getitemstringcallback);

    void getItemValue(int i, getItemValueCallback getitemvaluecallback);

    void getItemValueByArg1(int i, int i2, getItemValueByArg1Callback getitemvaluebyarg1callback);

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

    int setCurrentEdidByport();

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation();

    int setItem2Value(int i, int i2, int i3);

    int setItemData(int i, ArrayList<Integer> arrayList);

    int setItemString(int i, String str);

    int setItemValue(int i, int i2);

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient);

    static IHisHdmiApi getService(boolean z) {
        return getService("default", z);
    }

    static IHisHdmiApi getService(String str) {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static IHisHdmiApi getService() {
        return getService("default");
    }
}
