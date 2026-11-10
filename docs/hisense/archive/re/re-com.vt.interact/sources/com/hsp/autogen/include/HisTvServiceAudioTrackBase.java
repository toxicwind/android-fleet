package com.hsp.autogen.include;

import com.hsp.rod.Archive;
import com.hsp.rod.Serializer;

/* JADX INFO: loaded from: classes.dex */
public class HisTvServiceAudioTrackBase extends Serializer {
    public int audioId;
    public String audioLanguage;
    public String audioTextLabel;
    public int audioType;
    public int isADAudioTrack;
    public int isSpokenSubtitleAudioTrack;

    public void Serialize(Archive archive) {
        archive.put(this.audioId);
        archive.put(this.audioLanguage);
        archive.put(this.audioTextLabel);
        archive.put(this.audioType);
        archive.put(this.isADAudioTrack);
        archive.put(this.isSpokenSubtitleAudioTrack);
    }

    public void Deserialize(Archive archive) {
        this.audioId = archive.getInt();
        this.audioLanguage = archive.getString();
        this.audioTextLabel = archive.getString();
        this.audioType = archive.getInt();
        this.isADAudioTrack = archive.getInt();
        this.isSpokenSubtitleAudioTrack = archive.getInt();
    }
}
