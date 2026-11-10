package com.hsp.autogen.include;

import com.hsp.rod.Archive;
import com.hsp.rod.Serializer;

/* JADX INFO: loaded from: classes.dex */
public class HisInfoBarProgramInfoBase extends Serializer {
    public String currentProgramDetail;
    public int currentProgramDuration;
    public String currentProgramRating;
    public int currentProgramStartTime;
    public String currentProgramTitle;
    public String nextProgramDetail;
    public int nextProgramDuration;
    public String nextProgramRating;
    public int nextProgramStartTime;
    public String nextProgramTitle;

    public void Serialize(Archive archive) {
        archive.put(this.currentProgramTitle);
        archive.put(this.currentProgramStartTime);
        archive.put(this.currentProgramDuration);
        archive.put(this.currentProgramDetail);
        archive.put(this.currentProgramRating);
        archive.put(this.nextProgramTitle);
        archive.put(this.nextProgramStartTime);
        archive.put(this.nextProgramDuration);
        archive.put(this.nextProgramDetail);
        archive.put(this.nextProgramRating);
    }

    public void Deserialize(Archive archive) {
        this.currentProgramTitle = archive.getString();
        this.currentProgramStartTime = archive.getInt();
        this.currentProgramDuration = archive.getInt();
        this.currentProgramDetail = archive.getString();
        this.currentProgramRating = archive.getString();
        this.nextProgramTitle = archive.getString();
        this.nextProgramStartTime = archive.getInt();
        this.nextProgramDuration = archive.getInt();
        this.nextProgramDetail = archive.getString();
        this.nextProgramRating = archive.getString();
    }
}
