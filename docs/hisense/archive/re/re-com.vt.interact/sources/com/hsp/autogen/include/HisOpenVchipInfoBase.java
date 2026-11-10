package com.hsp.autogen.include;

import com.hsp.rod.Archive;
import com.hsp.rod.Serializer;

/* JADX INFO: loaded from: classes.dex */
public class HisOpenVchipInfoBase extends Serializer {
    public int dimGrad;
    public int dimIndex;
    public String dimName;
    public int dimNum;
    public String dimRatingAbbrev;
    public int dimRatingIndex;
    public String dimRatingValue;
    public int regionId;
    public String regionName;
    public String regionNum;
    public String versionNum;

    public void Serialize(Archive archive) {
        archive.put(this.versionNum);
        archive.put(this.regionNum);
        archive.put(this.regionId);
        archive.put(this.regionName);
        archive.put(this.dimNum);
        archive.put(this.dimIndex);
        archive.put(this.dimName);
        archive.put(this.dimGrad);
        archive.put(this.dimRatingIndex);
        archive.put(this.dimRatingAbbrev);
        archive.put(this.dimRatingValue);
    }

    public void Deserialize(Archive archive) {
        this.versionNum = archive.getString();
        this.regionNum = archive.getString();
        this.regionId = archive.getInt();
        this.regionName = archive.getString();
        this.dimNum = archive.getInt();
        this.dimIndex = archive.getInt();
        this.dimName = archive.getString();
        this.dimGrad = archive.getInt();
        this.dimRatingIndex = archive.getInt();
        this.dimRatingAbbrev = archive.getString();
        this.dimRatingValue = archive.getString();
    }
}
