package com.hsp.autogen.include;

import com.hsp.rod.Archive;
import com.hsp.rod.Serializer;

/* JADX INFO: loaded from: classes.dex */
public class HisInfoBarTunerInfoBase extends Serializer {
    public int signalLevel;
    public int signalQuality;

    public void Serialize(Archive archive) {
        archive.put(this.signalLevel);
        archive.put(this.signalQuality);
    }

    public void Deserialize(Archive archive) {
        this.signalLevel = archive.getInt();
        this.signalQuality = archive.getInt();
    }
}
