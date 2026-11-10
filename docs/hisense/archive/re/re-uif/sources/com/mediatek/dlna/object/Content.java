package com.mediatek.dlna.object;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class Content implements Parcelable, Serializable {
    public static final Parcelable.Creator<Content> CREATOR = new Parcelable.Creator<Content>() { // from class: com.mediatek.dlna.object.Content.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Content createFromParcel(Parcel parcel) {
            MediaServer mediaServerCreateFromParcel = MediaServer.CREATOR.createFromParcel(parcel);
            Bundle bundle = parcel.readBundle();
            bundle.setClassLoader(AnonymousClass1.class.getClassLoader());
            String string = bundle.getString("objectId");
            String string2 = bundle.getString("path");
            String string3 = bundle.getString("title");
            String string4 = bundle.getString("resUri");
            String string5 = bundle.getString("resDuration");
            String string6 = bundle.getString("sampleFrequency");
            String string7 = bundle.getString("bitrate");
            String string8 = bundle.getString("colorDepth");
            String string9 = bundle.getString("resolution");
            String string10 = bundle.getString("nrAudioChannels");
            String string11 = bundle.getString("bitsPerSample");
            String string12 = bundle.getString("parentId");
            String string13 = bundle.getString("mimeType");
            String string14 = bundle.getString("dtcpInfo");
            String string15 = bundle.getString("protocolInfo");
            long j = bundle.getLong("size");
            int i = bundle.getInt("flag");
            int i2 = bundle.getInt("mediaType");
            int i3 = bundle.getInt("drmType");
            int i4 = bundle.getInt("type");
            byte[] byteArray = bundle.getByteArray("sessionId");
            Content content = new Content(mediaServerCreateFromParcel, string, string2, string3, string4, string5, string12, string13, string14, j, i, i2, i3, i4, string6, string7, string8, string9, string10, string11, string15);
            content.setSessionId(byteArray);
            return content;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Content[] newArray(int i) {
            return new Content[i];
        }
    };
    private String bitrate;
    private String bitsPerSample;
    private String colorDepth;
    int drmType;
    String dtcpInfo;
    int flag;
    int mediaType;
    private String mimeType;
    private String nrAudioChannels;
    private String objectId;
    private String parentId;
    private String path;
    private boolean pause;
    private String protocolInfo;
    private String resDuration;
    private String resUri;
    private String resolution;
    private String sampleFrequency;
    private boolean seek;
    private MediaServer server;
    byte[] sessionId;
    long size;
    private int sourceType;
    private String title;
    private ContentType type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Content(MediaServer mediaServer, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, long j, int i, int i2, int i3, int i4, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
        this.server = mediaServer;
        this.objectId = str;
        this.path = str2;
        this.title = str3;
        this.resUri = str4;
        this.size = j;
        this.resDuration = str5;
        this.sampleFrequency = str9;
        this.bitrate = str10;
        this.colorDepth = str11;
        this.resolution = str12;
        this.nrAudioChannels = str13;
        this.bitsPerSample = str14;
        this.parentId = str6;
        this.mimeType = str7;
        this.flag = i;
        this.mediaType = i2;
        this.drmType = i3;
        this.dtcpInfo = str8;
        this.protocolInfo = str15;
        this.sourceType = i4;
        if (i4 == 0) {
            this.type = ContentType.Photo;
        } else if (i4 == 1) {
            this.type = ContentType.Audio;
        } else if (i4 == 2) {
            this.type = ContentType.Video;
        } else if (i4 == 3) {
            this.type = ContentType.Playlist;
        } else if (i4 == 4) {
            this.type = ContentType.Item;
        } else {
            this.type = ContentType.Directory;
        }
        this.sessionId = new byte[0];
        this.pause = true;
        this.seek = ((i & 16) | (i & 8)) != 0;
    }

    public void setSessionId(byte[] bArr) {
        this.sessionId = bArr;
    }

    public long getSize() {
        return this.size;
    }

    public String getResUri() {
        return this.resUri;
    }

    public String getResDuration() {
        return this.resDuration;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public int getDrmType() {
        return this.drmType;
    }

    public int getMediaType() {
        return this.mediaType;
    }

    public int getFlag() {
        return this.flag;
    }

    public String getDtcpInfo() {
        return this.dtcpInfo;
    }

    public byte[] getSessionId() {
        return this.sessionId;
    }

    public String getSampleFrequency() {
        return this.sampleFrequency;
    }

    public String getBitrate() {
        return this.bitrate;
    }

    public String getColorDepth() {
        return this.colorDepth;
    }

    public String getResolution() {
        return this.resolution;
    }

    public String getNrAudioChannels() {
        return this.nrAudioChannels;
    }

    public String getBitsPerSample() {
        return this.bitsPerSample;
    }

    public String getProtocolInfo() {
        return this.protocolInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.server.writeToParcel(parcel, i);
        Bundle bundle = new Bundle();
        bundle.setClassLoader(Content.class.getClassLoader());
        bundle.putString("objectId", this.objectId);
        bundle.putString("path", this.path);
        bundle.putString("title", this.title);
        bundle.putString("resUri", this.resUri);
        bundle.putString("resDuration", this.resDuration);
        bundle.putString("sampleFrequency", this.sampleFrequency);
        bundle.putString("bitrate", this.bitrate);
        bundle.putString("colorDepth", this.colorDepth);
        bundle.putString("resolution", this.resolution);
        bundle.putString("nrAudioChannels", this.nrAudioChannels);
        bundle.putString("bitsPerSample", this.bitsPerSample);
        bundle.putString("parentId", this.parentId);
        bundle.putString("mimeType", this.mimeType);
        bundle.putString("dtcpInfo", this.dtcpInfo);
        bundle.putString("protocolInfo", this.protocolInfo);
        bundle.putLong("size", this.size);
        bundle.putInt("flag", this.flag);
        bundle.putInt("mediaType", this.mediaType);
        bundle.putInt("drmType", this.drmType);
        bundle.putInt("type", this.sourceType);
        bundle.putByteArray("sessionId", this.sessionId);
        parcel.writeBundle(bundle);
    }
}
