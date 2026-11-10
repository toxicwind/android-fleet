package com.hsp.autogen.include;

import com.hsp.rod.Archive;
import com.hsp.rod.Serializer;

/* JADX INFO: loaded from: classes.dex */
public class IHisTvServiceChannelInfoBase extends Serializer {
    public int attrOperationMask;
    public int attrValue;
    public int broadcastType;
    public int channelAttrEditMask;
    public String channelNum;
    public int frequency;
    public int isChannelInfoAvailable;
    public int modulation;
    public int physicalChannelIndex;
    public int serviceId;
    public String serviceName;
    public int serviceType;
    public String uuid;

    public void Serialize(Archive archive) {
        archive.put(this.isChannelInfoAvailable);
        archive.put(this.uuid);
        archive.put(this.serviceId);
        archive.put(this.broadcastType);
        archive.put(this.serviceType);
        archive.put(this.channelNum);
        archive.put(this.serviceName);
        archive.put(this.frequency);
        archive.put(this.physicalChannelIndex);
        archive.put(this.modulation);
        archive.put(this.attrValue);
        archive.put(this.channelAttrEditMask);
        archive.put(this.attrOperationMask);
    }

    public void Deserialize(Archive archive) {
        this.isChannelInfoAvailable = archive.getInt();
        this.uuid = archive.getString();
        this.serviceId = archive.getInt();
        this.broadcastType = archive.getInt();
        this.serviceType = archive.getInt();
        this.channelNum = archive.getString();
        this.serviceName = archive.getString();
        this.frequency = archive.getInt();
        this.physicalChannelIndex = archive.getInt();
        this.modulation = archive.getInt();
        this.attrValue = archive.getInt();
        this.channelAttrEditMask = archive.getInt();
        this.attrOperationMask = archive.getInt();
    }
}
