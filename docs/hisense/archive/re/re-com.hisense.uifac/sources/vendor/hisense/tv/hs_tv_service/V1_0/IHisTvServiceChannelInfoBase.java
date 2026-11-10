package vendor.hisense.tv.hs_tv_service.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class IHisTvServiceChannelInfoBase {
    public int isChannelInfoAvailable = 0;
    public String uuid = new String();
    public int serviceId = 0;
    public int broadcastType = 0;
    public int serviceType = 0;
    public String channelNum = new String();
    public String serviceName = new String();
    public int frequency = 0;
    public int physicalChannelIndex = 0;
    public int modulation = 0;
    public int attrValue = 0;
    public int channelAttrEditMask = 0;
    public int attrOperationMask = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != IHisTvServiceChannelInfoBase.class) {
            return false;
        }
        IHisTvServiceChannelInfoBase iHisTvServiceChannelInfoBase = (IHisTvServiceChannelInfoBase) obj;
        return this.isChannelInfoAvailable == iHisTvServiceChannelInfoBase.isChannelInfoAvailable && HidlSupport.deepEquals(this.uuid, iHisTvServiceChannelInfoBase.uuid) && this.serviceId == iHisTvServiceChannelInfoBase.serviceId && this.broadcastType == iHisTvServiceChannelInfoBase.broadcastType && this.serviceType == iHisTvServiceChannelInfoBase.serviceType && HidlSupport.deepEquals(this.channelNum, iHisTvServiceChannelInfoBase.channelNum) && HidlSupport.deepEquals(this.serviceName, iHisTvServiceChannelInfoBase.serviceName) && this.frequency == iHisTvServiceChannelInfoBase.frequency && this.physicalChannelIndex == iHisTvServiceChannelInfoBase.physicalChannelIndex && this.modulation == iHisTvServiceChannelInfoBase.modulation && this.attrValue == iHisTvServiceChannelInfoBase.attrValue && this.channelAttrEditMask == iHisTvServiceChannelInfoBase.channelAttrEditMask && this.attrOperationMask == iHisTvServiceChannelInfoBase.attrOperationMask;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.isChannelInfoAvailable))), Integer.valueOf(HidlSupport.deepHashCode(this.uuid)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serviceId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.broadcastType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serviceType))), Integer.valueOf(HidlSupport.deepHashCode(this.channelNum)), Integer.valueOf(HidlSupport.deepHashCode(this.serviceName)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.frequency))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.physicalChannelIndex))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.modulation))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.attrValue))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.channelAttrEditMask))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.attrOperationMask))));
    }

    public final String toString() {
        return "{.isChannelInfoAvailable = " + this.isChannelInfoAvailable + ", .uuid = " + this.uuid + ", .serviceId = " + this.serviceId + ", .broadcastType = " + this.broadcastType + ", .serviceType = " + this.serviceType + ", .channelNum = " + this.channelNum + ", .serviceName = " + this.serviceName + ", .frequency = " + this.frequency + ", .physicalChannelIndex = " + this.physicalChannelIndex + ", .modulation = " + this.modulation + ", .attrValue = " + this.attrValue + ", .channelAttrEditMask = " + this.channelAttrEditMask + ", .attrOperationMask = " + this.attrOperationMask + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(96L), 0L);
    }

    public static final ArrayList<IHisTvServiceChannelInfoBase> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<IHisTvServiceChannelInfoBase> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 96, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            IHisTvServiceChannelInfoBase iHisTvServiceChannelInfoBase = new IHisTvServiceChannelInfoBase();
            iHisTvServiceChannelInfoBase.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 96);
            arrayList.add(iHisTvServiceChannelInfoBase);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.isChannelInfoAvailable = hwBlob.getInt32(j + 0);
        long j2 = j + 8;
        String string = hwBlob.getString(j2);
        this.uuid = string;
        hwParcel.readEmbeddedBuffer(string.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        this.serviceId = hwBlob.getInt32(j + 24);
        this.broadcastType = hwBlob.getInt32(j + 28);
        this.serviceType = hwBlob.getInt32(j + 32);
        long j3 = j + 40;
        String string2 = hwBlob.getString(j3);
        this.channelNum = string2;
        hwParcel.readEmbeddedBuffer(string2.getBytes().length + 1, hwBlob.handle(), j3 + 0, false);
        long j4 = j + 56;
        String string3 = hwBlob.getString(j4);
        this.serviceName = string3;
        hwParcel.readEmbeddedBuffer(string3.getBytes().length + 1, hwBlob.handle(), j4 + 0, false);
        this.frequency = hwBlob.getInt32(j + 72);
        this.physicalChannelIndex = hwBlob.getInt32(j + 76);
        this.modulation = hwBlob.getInt32(j + 80);
        this.attrValue = hwBlob.getInt32(j + 84);
        this.channelAttrEditMask = hwBlob.getInt32(j + 88);
        this.attrOperationMask = hwBlob.getInt32(j + 92);
    }
}
