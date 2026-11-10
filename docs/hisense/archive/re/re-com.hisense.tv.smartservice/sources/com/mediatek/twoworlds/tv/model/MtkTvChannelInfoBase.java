package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvChannelInfoBase implements Serializable {
    public static final int CUSTOM_DATA_LEN = 8;
    public static final int PRIVATE_DATA_LEN = 20;
    public static final String TAG = "MtkTvChannelInfo";
    public int brdcstMedium;
    public int brdcstType;
    public int channelId;
    public int channelNumber;
    public byte[] customData;
    public int favorites1Index;
    public int favorites2Index;
    public int favorites3Index;
    public int favorites4Index;
    public int frequency;
    public int nwMask;
    public int optionMask;
    public byte[] privateData;
    public String serviceName;
    public int serviceType;
    public int svlId;
    public int svlRecId;

    public MtkTvChannelInfoBase() {
        this.customData = new byte[8];
        this.privateData = new byte[20];
        channelInfoSetDefault();
    }

    private void channelInfoSetDefault() {
        this.nwMask = MtkTvChCommonBase.SB_VNET_ALL | MtkTvChCommonBase.SB_VNET_ACTIVE | MtkTvChCommonBase.SB_VNET_EPG | MtkTvChCommonBase.SB_VNET_VISIBLE;
        this.serviceType = MtkTvChCommonBase.SB_VNET_TV_SERVICE;
        this.frequency = 0;
        this.serviceType = 0;
    }

    public String BrdcstTypeToString(int i) {
        switch (i) {
            case 0:
                return " BRDCST_TYPE_UNKNOWN ";
            case 1:
                return " BRDCST_TYPE_ANALOG  ";
            case 2:
                return " BRDCST_TYPE_DVB     ";
            case 3:
                return " BRDCST_TYPE_ATSC    ";
            case 4:
                return " BRDCST_TYPE_SCTE    ";
            case 5:
                return " BRDCST_TYPE_ISDB    ";
            case 6:
                return " BRDCST_TYPE_FMRDO   ";
            case 7:
                return " BRDCST_TYPE_DTMB    ";
            case 8:
                return " BRDCST_TYPE_MHP     ";
            default:
                return " Undefined_TYPE      ";
        }
    }

    public void _setChannelId(int i) {
        this.channelId = i;
    }

    public void _setChannelNumber(int i) {
        this.channelNumber = i;
    }

    public void _setChannelNumberByChannelID(int i) {
        this.channelNumber = MtkTvChCommonBase.SB_ATSC_GET_MAJOR_CHANNEL_NUM(i);
    }

    public void _setFrequency(int i) {
        this.frequency = i;
    }

    public void _setServiceName(String str) {
        this.serviceName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MtkTvChannelInfoBase mtkTvChannelInfoBase = (MtkTvChannelInfoBase) obj;
        return this.channelId == mtkTvChannelInfoBase.channelId && this.svlId == mtkTvChannelInfoBase.svlId && this.svlRecId == mtkTvChannelInfoBase.svlRecId;
    }

    public int getBrdcstMedium() {
        return this.brdcstMedium;
    }

    public int getBrdcstType() {
        return this.brdcstType;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public int getChannelNumber() {
        return this.channelNumber;
    }

    public byte[] getCustomData() {
        return this.customData;
    }

    public int getFavorites1Index() {
        return this.favorites1Index;
    }

    public int getFavorites2Index() {
        return this.favorites2Index;
    }

    public int getFavorites3Index() {
        return this.favorites3Index;
    }

    public int getFavorites4Index() {
        return this.favorites4Index;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public int getNwMask() {
        return this.nwMask;
    }

    public int getOptionMask() {
        return this.optionMask;
    }

    public byte[] getPrivateData() {
        return this.privateData;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public int getServiceType() {
        return this.serviceType;
    }

    public int getSvlId() {
        return this.svlId;
    }

    public int getSvlRecId() {
        return this.svlRecId;
    }

    public int hashCode() {
        return ((this.channelId + 31) * 31) + this.svlId;
    }

    public boolean isAnalogService() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_ANALOG_SERVICE) > 0;
    }

    public boolean isBlock() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_BLOCKED) > 0;
    }

    public boolean isDigitalFavorites1Service() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_FAVORITE1) > 0;
    }

    public boolean isDigitalFavorites2Service() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_FAVORITE2) > 0;
    }

    public boolean isDigitalFavorites3Service() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_FAVORITE3) > 0;
    }

    public boolean isDigitalFavorites4Service() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_FAVORITE4) > 0;
    }

    public boolean isDigitalFavoritesService() {
        return isDigitalFavorites1Service() || isDigitalFavorites2Service() || isDigitalFavorites3Service() || isDigitalFavorites4Service();
    }

    public boolean isEpgVisible() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_EPG) > 0;
    }

    public boolean isNumberSelectable() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_NUMERIC_SELECTABLE) > 0;
    }

    public boolean isRadioService() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_RADIO_SERVICE) > 0;
    }

    public boolean isScrambled() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_SCRAMBLED) > 0;
    }

    public boolean isSkip() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_VISIBLE) <= 0;
    }

    public boolean isTvService() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_TV_SERVICE) > 0;
    }

    public boolean isUserDelete() {
        return (this.optionMask & MtkTvChCommonBase.SB_VOPT_DELETED_BY_USER) > 0;
    }

    public boolean isUserTmpLock() {
        return (this.optionMask & MtkTvChCommonBase.SB_VOPT_USER_TMP_UNLOCK) > 0;
    }

    public boolean isVisible() {
        return (this.nwMask & MtkTvChCommonBase.SB_VNET_VISIBLE) > 0;
    }

    public void setBlock(boolean z) {
        if (z) {
            this.nwMask |= MtkTvChCommonBase.SB_VNET_BLOCKED;
        } else {
            this.nwMask &= ~MtkTvChCommonBase.SB_VNET_BLOCKED;
        }
    }

    public void setBrdcstMedium(int i) {
        this.brdcstMedium = i;
    }

    public void setBrdcstType(int i) {
        this.brdcstType = i;
    }

    public void setChannelDelChannelId() {
        this.channelId |= 63;
    }

    public void setChannelDeleted(boolean z) {
        if (!z) {
            this.optionMask &= ~MtkTvChCommonBase.SB_VOPT_DELETED_BY_USER;
        } else {
            this.optionMask |= MtkTvChCommonBase.SB_VOPT_DELETED_BY_USER;
            this.nwMask &= ~MtkTvChCommonBase.SB_VNET_BLOCKED;
        }
    }

    public void setChannelId(int i) {
        _setChannelId(i);
        _setChannelNumberByChannelID(i);
    }

    public void setChannelNameEdited(boolean z) {
        if (z) {
            this.nwMask |= MtkTvChCommonBase.SB_VNET_CH_NAME_EDITED;
            this.optionMask |= MtkTvChCommonBase.SB_VOPT_CH_NAME_EDITED;
        } else {
            this.nwMask &= ~MtkTvChCommonBase.SB_VNET_CH_NAME_EDITED;
            this.optionMask &= ~MtkTvChCommonBase.SB_VOPT_CH_NAME_EDITED;
        }
    }

    public void setChannelNumber(int i) {
        _setChannelNumber(i);
        setChannelNumberEdited(true);
    }

    public void setChannelNumberEdited(boolean z) {
        if (z) {
            this.optionMask |= MtkTvChCommonBase.SB_VOPT_CH_NUM_EDITED;
        } else {
            this.optionMask &= ~MtkTvChCommonBase.SB_VOPT_CH_NUM_EDITED;
        }
    }

    public void setCustomData(byte[] bArr) {
        if (bArr == null) {
            Log.d(TAG, "setCustomData fail!\n");
        } else if (bArr.length > 8) {
            Log.d(TAG, "setCustomData fail because data length more than 8!\n");
        } else {
            System.arraycopy(bArr, 0, this.customData, 0, bArr.length);
        }
    }

    public void setDigitalFavoritesMask(boolean z, int i) {
        if (z) {
            if (i == 0) {
                this.nwMask |= MtkTvChCommonBase.SB_VNET_FAVORITE1;
                return;
            }
            if (i == 1) {
                this.nwMask |= MtkTvChCommonBase.SB_VNET_FAVORITE2;
                return;
            }
            if (i == 2) {
                this.nwMask |= MtkTvChCommonBase.SB_VNET_FAVORITE3;
                return;
            } else if (i != 3) {
                a.b("Invalid favoritesIndex(", i, ")!\n", TAG);
                return;
            } else {
                this.nwMask |= MtkTvChCommonBase.SB_VNET_FAVORITE4;
                return;
            }
        }
        if (i == 0) {
            this.nwMask &= ~MtkTvChCommonBase.SB_VNET_FAVORITE1;
            return;
        }
        if (i == 1) {
            this.nwMask &= ~MtkTvChCommonBase.SB_VNET_FAVORITE2;
            return;
        }
        if (i == 2) {
            this.nwMask &= ~MtkTvChCommonBase.SB_VNET_FAVORITE3;
        } else if (i != 3) {
            a.b("Invalid favoritesIndex(", i, ")!\n", TAG);
        } else {
            this.nwMask &= ~MtkTvChCommonBase.SB_VNET_FAVORITE4;
        }
    }

    public void setFavorites1Index(int i) {
        this.favorites1Index = i;
    }

    public void setFavorites2Index(int i) {
        this.favorites2Index = i;
    }

    public void setFavorites3Index(int i) {
        this.favorites3Index = i;
    }

    public void setFavorites4Index(int i) {
        this.favorites4Index = i;
    }

    public void setFrequency(int i) {
        _setFrequency(i);
        setFrequencyEdited(true);
    }

    public void setFrequencyEdited(boolean z) {
        if (z) {
            this.nwMask |= MtkTvChCommonBase.SB_VNET_FREQ_EDITED;
            this.optionMask |= MtkTvChCommonBase.SB_VOPT_FREQ_EDITED;
        } else {
            this.nwMask &= ~MtkTvChCommonBase.SB_VNET_FREQ_EDITED;
            this.optionMask &= ~MtkTvChCommonBase.SB_VOPT_FREQ_EDITED;
        }
    }

    public void setNwMask(int i) {
        this.nwMask = i;
    }

    public void setOptionMask(int i) {
        this.optionMask = i;
    }

    public void setPrivateData(byte[] bArr) {
        if (bArr == null) {
            Log.d(TAG, "setPrivateData fail!\n");
        } else if (bArr.length > 20) {
            Log.d(TAG, "setPrivateData fail because data length more than 20!\n");
        } else {
            System.arraycopy(bArr, 0, this.privateData, 0, bArr.length);
        }
    }

    public void setServiceName(String str) {
        _setServiceName(str);
        setChannelNameEdited(true);
    }

    public void setServiceType(int i) {
        this.serviceType = i;
    }

    public void setSkip(boolean z) {
        if (z) {
            this.nwMask &= ~MtkTvChCommonBase.SB_VNET_VISIBLE;
            this.optionMask |= MtkTvChCommonBase.SB_VOPT_MODIFIED_VISIBLE;
        } else {
            this.nwMask |= MtkTvChCommonBase.SB_VNET_VISIBLE;
            this.optionMask &= ~MtkTvChCommonBase.SB_VOPT_MODIFIED_VISIBLE;
        }
    }

    public void setSvlId(int i) {
        this.svlId = i;
    }

    public void setSvlRecId(int i) {
        this.svlRecId = i;
    }

    public void setUserTmpLock(boolean z) {
        if (z) {
            this.optionMask |= MtkTvChCommonBase.SB_VOPT_USER_TMP_UNLOCK;
        } else {
            this.optionMask &= ~MtkTvChCommonBase.SB_VOPT_USER_TMP_UNLOCK;
        }
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvChannelInfo      [svlId=");
        sbA.append(this.svlId);
        sbA.append(" , svlRecId=");
        sbA.append(this.svlRecId);
        sbA.append(" , channelId=");
        sbA.append(this.channelId);
        sbA.append(" , brdcstType=");
        sbA.append(BrdcstTypeToString(this.brdcstType));
        sbA.append(" , nwMask=");
        sbA.append(this.nwMask);
        sbA.append(" , optionMask=");
        sbA.append(this.optionMask);
        sbA.append(" , serviceType=");
        sbA.append(this.serviceType);
        sbA.append(" , channelNumber=");
        sbA.append(this.channelNumber);
        sbA.append(" , serviceName=");
        sbA.append(this.serviceName);
        sbA.append(" , privateData=");
        sbA.append(Arrays.toString(this.privateData));
        sbA.append(" , customData=");
        sbA.append(Arrays.toString(this.customData));
        sbA.append(" , frequency=");
        sbA.append(this.frequency);
        sbA.append(" , brdcstMedium=");
        return a.a(sbA, this.brdcstMedium, "]\n");
    }

    public MtkTvChannelInfoBase(int i, int i2) {
        this.customData = new byte[8];
        this.privateData = new byte[20];
        channelInfoSetDefault();
        this.svlId = i;
        this.svlRecId = i2;
    }
}
