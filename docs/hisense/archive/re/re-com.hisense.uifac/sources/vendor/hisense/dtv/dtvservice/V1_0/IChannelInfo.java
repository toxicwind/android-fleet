package vendor.hisense.dtv.dtvservice.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class IChannelInfo {
    public String channelKey = new String();
    public int tunerMode = 0;
    public int tvSys = 0;
    public int audioSys = 0;
    public int colorSys = 0;
    public int centralFrequency = 0;
    public int transportStreamId = 0;
    public int channelId = 0;
    public int brdcstType = 0;
    public int serviceType = 0;
    public String channelNum = new String();
    public String serviceName = new String();
    public int frequency = 0;
    public int brdcstMedium = 0;
    public int modulationType = 0;
    public int originalNetworkId = 0;
    public int serviceMask = 0;
    public int editMask = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != IChannelInfo.class) {
            return false;
        }
        IChannelInfo iChannelInfo = (IChannelInfo) obj;
        return HidlSupport.deepEquals(this.channelKey, iChannelInfo.channelKey) && this.tunerMode == iChannelInfo.tunerMode && this.tvSys == iChannelInfo.tvSys && this.audioSys == iChannelInfo.audioSys && this.colorSys == iChannelInfo.colorSys && this.centralFrequency == iChannelInfo.centralFrequency && this.transportStreamId == iChannelInfo.transportStreamId && this.channelId == iChannelInfo.channelId && this.brdcstType == iChannelInfo.brdcstType && this.serviceType == iChannelInfo.serviceType && HidlSupport.deepEquals(this.channelNum, iChannelInfo.channelNum) && HidlSupport.deepEquals(this.serviceName, iChannelInfo.serviceName) && this.frequency == iChannelInfo.frequency && this.brdcstMedium == iChannelInfo.brdcstMedium && this.modulationType == iChannelInfo.modulationType && this.originalNetworkId == iChannelInfo.originalNetworkId && this.serviceMask == iChannelInfo.serviceMask && this.editMask == iChannelInfo.editMask;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.channelKey)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.tunerMode))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.tvSys))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.audioSys))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.colorSys))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.centralFrequency))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.transportStreamId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.channelId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.brdcstType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serviceType))), Integer.valueOf(HidlSupport.deepHashCode(this.channelNum)), Integer.valueOf(HidlSupport.deepHashCode(this.serviceName)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.frequency))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.brdcstMedium))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.modulationType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.originalNetworkId))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.serviceMask))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.editMask))));
    }

    public final String toString() {
        return "{.channelKey = " + this.channelKey + ", .tunerMode = " + this.tunerMode + ", .tvSys = " + this.tvSys + ", .audioSys = " + this.audioSys + ", .colorSys = " + this.colorSys + ", .centralFrequency = " + this.centralFrequency + ", .transportStreamId = " + this.transportStreamId + ", .channelId = " + this.channelId + ", .brdcstType = " + this.brdcstType + ", .serviceType = " + this.serviceType + ", .channelNum = " + this.channelNum + ", .serviceName = " + this.serviceName + ", .frequency = " + this.frequency + ", .brdcstMedium = " + this.brdcstMedium + ", .modulationType = " + this.modulationType + ", .originalNetworkId = " + this.originalNetworkId + ", .serviceMask = " + this.serviceMask + ", .editMask = " + this.editMask + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(112L), 0L);
    }

    public static final ArrayList<IChannelInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<IChannelInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 112, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            IChannelInfo iChannelInfo = new IChannelInfo();
            iChannelInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 112);
            arrayList.add(iChannelInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        long j2 = j + 0;
        String string = hwBlob.getString(j2);
        this.channelKey = string;
        hwParcel.readEmbeddedBuffer(string.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        this.tunerMode = hwBlob.getInt32(j + 16);
        this.tvSys = hwBlob.getInt32(j + 20);
        this.audioSys = hwBlob.getInt32(j + 24);
        this.colorSys = hwBlob.getInt32(j + 28);
        this.centralFrequency = hwBlob.getInt32(j + 32);
        this.transportStreamId = hwBlob.getInt32(j + 36);
        this.channelId = hwBlob.getInt32(j + 40);
        this.brdcstType = hwBlob.getInt32(j + 44);
        this.serviceType = hwBlob.getInt32(j + 48);
        long j3 = j + 56;
        String string2 = hwBlob.getString(j3);
        this.channelNum = string2;
        hwParcel.readEmbeddedBuffer(string2.getBytes().length + 1, hwBlob.handle(), j3 + 0, false);
        long j4 = j + 72;
        String string3 = hwBlob.getString(j4);
        this.serviceName = string3;
        hwParcel.readEmbeddedBuffer(string3.getBytes().length + 1, hwBlob.handle(), j4 + 0, false);
        this.frequency = hwBlob.getInt32(j + 88);
        this.brdcstMedium = hwBlob.getInt32(j + 92);
        this.modulationType = hwBlob.getInt32(j + 96);
        this.originalNetworkId = hwBlob.getInt32(j + 100);
        this.serviceMask = hwBlob.getInt32(j + 104);
        this.editMask = hwBlob.getInt32(j + 108);
    }
}
