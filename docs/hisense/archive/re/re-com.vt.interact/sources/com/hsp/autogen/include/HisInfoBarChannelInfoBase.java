package com.hsp.autogen.include;

import com.hsp.rod.Archive;
import com.hsp.rod.Serializer;

/* JADX INFO: loaded from: classes.dex */
public class HisInfoBarChannelInfoBase extends Serializer {
    public String channelName;
    public String channelNumber;

    public void Serialize(Archive archive) {
        archive.put(this.channelName);
        archive.put(this.channelNumber);
    }

    public void Deserialize(Archive archive) {
        this.channelName = archive.getString();
        this.channelNumber = archive.getString();
    }
}
