package com.hisense.tvmanager.atsc;

/* JADX INFO: loaded from: classes.dex */
public class ChannelInfoBase {
    public static int CHANNEL_MASK_ANALOG_SERVICE = 256;
    public static int CHANNEL_MASK_BLOCKED = 2048;
    public static int CHANNEL_MASK_FAV1 = 4;
    public static int CHANNEL_MASK_FAV2 = 8;
    public static int CHANNEL_MASK_FAV3 = 16;
    public static int CHANNEL_MASK_FAV4 = 32;
    public static int CHANNEL_MASK_HIDDEN = 4096;
    public static int CHANNEL_MASK_RADIO_SERVICE = 128;
    public static int CHANNEL_MASK_SKIPPED = 512;
    public static int CHANNEL_MASK_TV_SERVICE = 64;
    private int attrValue;
    private int broadcastType;
    private String channelNumber;
    private int frequency;
    private int modulation;
    private int physicalChannelIndex;
    private int serviceId;
    private String serviceName;
    private String uuid;
    private int channelAttrEditMask = 0;
    private int SERVICE_ATTRIBUTES_HIDDEN = 2;
    private int SERVICE_ATTRIBUTES_IS_SKIPPED = 32;
    private int SERVICE_ATTRIBUTES_IS_LOCKED = 64;
    private int SERVICE_ATTRIBUTES_IS_FAVORITE1 = 128;
    private int SERVICE_ATTRIBUTES_IS_FAVORITE2 = 256;
    private int SERVICE_ATTRIBUTES_IS_FAVORITE3 = 512;
    private int SERVICE_ATTRIBUTES_IS_FAVORITE4 = 1024;
    private int SERVICE_ATTRIBUTES_IS_ANALOG = 2048;
    private int SERVICE_ATTRIBUTES_AUDIO_ONLY = 4096;

    public void setChannelInfoAvailable(boolean z) {
    }

    public void setServiceType(int i) {
    }

    public String getServiceUUID() {
        return this.uuid;
    }

    public void setServiceUUID(String str) {
        this.uuid = str;
    }

    public void setServiceId(int i) {
        this.serviceId = i;
    }

    public void setBroadcastType(int i) {
        this.broadcastType = i;
    }

    public String getChannelNumber() {
        return this.channelNumber;
    }

    public void setChannelNumber(String str) {
        this.channelNumber = str;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public void setFrequencyHz(int i) {
        this.frequency = i * 1000;
    }

    public void setPhysicalChannelIndex(int i) {
        this.physicalChannelIndex = i;
    }

    public void setModulation(int i) {
        this.modulation = i;
    }

    public void setServiceFavMask(boolean z, int i) {
        if (i == 0) {
            int i2 = this.channelAttrEditMask;
            int i3 = CHANNEL_MASK_FAV1;
            this.channelAttrEditMask = i2 | i3;
            if (z) {
                this.attrValue |= i3;
                return;
            } else {
                this.attrValue &= ~i3;
                return;
            }
        }
        if (i == 1) {
            int i4 = this.channelAttrEditMask;
            int i5 = CHANNEL_MASK_FAV2;
            this.channelAttrEditMask = i4 | i5;
            if (z) {
                this.attrValue |= i5;
                return;
            } else {
                this.attrValue &= ~i5;
                return;
            }
        }
        if (i == 2) {
            int i6 = this.channelAttrEditMask;
            int i7 = CHANNEL_MASK_FAV3;
            this.channelAttrEditMask = i6 | i7;
            if (z) {
                this.attrValue |= i7;
                return;
            } else {
                this.attrValue &= ~i7;
                return;
            }
        }
        if (i != 3) {
            return;
        }
        int i8 = this.channelAttrEditMask;
        int i9 = CHANNEL_MASK_FAV4;
        this.channelAttrEditMask = i8 | i9;
        if (z) {
            this.attrValue |= i9;
        } else {
            this.attrValue &= ~i9;
        }
    }

    public void setChannelEditMask(int i) {
        this.channelAttrEditMask = i;
    }

    public void setAttrValue(int i) {
        setSkipped((this.SERVICE_ATTRIBUTES_IS_SKIPPED & i) > 0);
        setHidden((this.SERVICE_ATTRIBUTES_HIDDEN & i) > 0);
        setBlocked((this.SERVICE_ATTRIBUTES_IS_LOCKED & i) > 0);
        setAnalogService((this.SERVICE_ATTRIBUTES_IS_ANALOG & i) > 0);
        setRadioService((this.SERVICE_ATTRIBUTES_AUDIO_ONLY & i) > 0);
        setServiceFavMask((this.SERVICE_ATTRIBUTES_IS_FAVORITE1 & i) > 0, 0);
        setServiceFavMask((this.SERVICE_ATTRIBUTES_IS_FAVORITE2 & i) > 0, 1);
        setServiceFavMask((this.SERVICE_ATTRIBUTES_IS_FAVORITE3 & i) > 0, 2);
        setServiceFavMask((i & this.SERVICE_ATTRIBUTES_IS_FAVORITE4) > 0, 3);
    }

    public boolean isAnalogService() {
        return (this.attrValue & CHANNEL_MASK_ANALOG_SERVICE) > 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("channelinfobase debuginfo start{uuid:");
        sb.append(this.uuid);
        sb.append(", serviceId:");
        sb.append(this.serviceId);
        sb.append(", broadcastType:");
        sb.append(this.broadcastType);
        sb.append(", channelNumber:");
        sb.append(this.channelNumber);
        sb.append(", serviceName:");
        sb.append(this.serviceName);
        sb.append(", frequency:");
        sb.append(this.frequency);
        sb.append(", physicalChannelIndex:");
        sb.append(this.physicalChannelIndex);
        sb.append(", modulation:");
        sb.append(this.modulation);
        sb.append(", isTVService:");
        sb.append((this.attrValue & CHANNEL_MASK_TV_SERVICE) != 0);
        sb.append(", isRadioService:");
        sb.append((this.attrValue & CHANNEL_MASK_RADIO_SERVICE) != 0);
        sb.append(", isServiceInFav1:");
        sb.append((this.attrValue & CHANNEL_MASK_FAV1) != 0);
        sb.append(", isServiceInFav2:");
        sb.append((this.attrValue & CHANNEL_MASK_FAV2) != 0);
        sb.append(", isServiceInFav3:");
        sb.append((this.attrValue & CHANNEL_MASK_FAV3) != 0);
        sb.append(", isServiceInFav4:");
        sb.append((this.attrValue & CHANNEL_MASK_FAV4) != 0);
        sb.append(", isSkipped:");
        sb.append((this.attrValue & CHANNEL_MASK_SKIPPED) != 0);
        sb.append(", isBlocked:");
        sb.append((this.attrValue & CHANNEL_MASK_BLOCKED) != 0);
        sb.append(", isHidden:");
        sb.append((this.attrValue & CHANNEL_MASK_HIDDEN) != 0);
        sb.append("}channelinfobase debuginfo end");
        return sb.toString();
    }

    private void setRadioService(boolean z) {
        if (z) {
            this.attrValue |= CHANNEL_MASK_RADIO_SERVICE;
        } else {
            this.attrValue &= ~CHANNEL_MASK_RADIO_SERVICE;
        }
    }

    private void setAnalogService(boolean z) {
        if (z) {
            this.attrValue |= CHANNEL_MASK_ANALOG_SERVICE;
        } else {
            this.attrValue &= ~CHANNEL_MASK_ANALOG_SERVICE;
        }
    }

    public void setSkipped(boolean z) {
        int i = this.channelAttrEditMask;
        int i2 = CHANNEL_MASK_SKIPPED;
        this.channelAttrEditMask = i | i2;
        if (z) {
            this.attrValue |= i2;
        } else {
            this.attrValue &= ~i2;
        }
    }

    public void setHidden(boolean z) {
        if (z) {
            this.attrValue |= CHANNEL_MASK_HIDDEN;
        } else {
            this.attrValue &= ~CHANNEL_MASK_HIDDEN;
        }
    }

    public void setBlocked(boolean z) {
        int i = this.channelAttrEditMask;
        int i2 = CHANNEL_MASK_BLOCKED;
        this.channelAttrEditMask = i | i2;
        if (z) {
            this.attrValue |= i2;
        } else {
            this.attrValue &= ~i2;
        }
    }
}
