package com.hsp.autogen.include;

import com.hsp.rod.Archive;
import com.hsp.rod.Serializer;

/* JADX INFO: loaded from: classes.dex */
public class HisOpenVchipRatingSetting extends Serializer {
    public int bBlock;
    public int dimIndex;
    public int dimRatingIndex;
    public int regionId;

    public void Serialize(Archive archive) {
        archive.put(this.regionId);
        archive.put(this.dimIndex);
        archive.put(this.dimRatingIndex);
        archive.put(this.bBlock);
    }

    public void Deserialize(Archive archive) {
        this.regionId = archive.getInt();
        this.dimIndex = archive.getInt();
        this.dimRatingIndex = archive.getInt();
        this.bBlock = archive.getInt();
    }
}
