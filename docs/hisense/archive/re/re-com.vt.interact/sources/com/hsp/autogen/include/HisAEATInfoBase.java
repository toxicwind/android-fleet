package com.hsp.autogen.include;

import com.hsp.rod.Archive;
import com.hsp.rod.Serializer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class HisAEATInfoBase extends Serializer {
    public String aeaId;
    public int aeaMode;
    public String aeaText;
    public int aeaType;
    public String eventCode;
    public String eventDesc;
    public ArrayList<String> livemeidaBsid = new ArrayList<>();
    public int livemeidaSerid;
    public String livemeidaSername;
    public String mediaConType;
    public String mediaLocalUrl;
    public String mediaRemoteUrl;
    public int mediaType;
    public int priority;

    public void Serialize(Archive archive) {
        archive.put(this.aeaId);
        archive.put(this.aeaType);
        archive.put(this.aeaMode);
        archive.put(this.priority);
        archive.put(this.eventCode);
        archive.put(this.eventDesc);
        archive.put(this.aeaText);
        archive.put(this.livemeidaBsid.size());
        for (int i = 0; i < this.livemeidaBsid.size(); i++) {
            archive.put(this.livemeidaBsid.get(i));
        }
        archive.put(this.livemeidaSerid);
        archive.put(this.livemeidaSername);
        archive.put(this.mediaType);
        archive.put(this.mediaConType);
        archive.put(this.mediaRemoteUrl);
        archive.put(this.mediaLocalUrl);
    }

    public void Deserialize(Archive archive) {
        this.aeaId = archive.getString();
        this.aeaType = archive.getInt();
        this.aeaMode = archive.getInt();
        this.priority = archive.getInt();
        this.eventCode = archive.getString();
        this.eventDesc = archive.getString();
        this.aeaText = archive.getString();
        int i = archive.getInt();
        for (int i2 = 0; i2 < i; i2++) {
            this.livemeidaBsid.add(archive.getString());
        }
        this.livemeidaSerid = archive.getInt();
        this.livemeidaSername = archive.getString();
        this.mediaType = archive.getInt();
        this.mediaConType = archive.getString();
        this.mediaRemoteUrl = archive.getString();
        this.mediaLocalUrl = archive.getString();
    }
}
