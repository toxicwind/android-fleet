package com.mediatek.dlna.object;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MediaServer implements DLNADevice {
    public static final Parcelable.Creator<MediaServer> CREATOR = new Parcelable.Creator<MediaServer>() { // from class: com.mediatek.dlna.object.MediaServer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaServer createFromParcel(Parcel parcel) {
            return new MediaServer(parcel.readString(), parcel.readLong());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaServer[] newArray(int i) {
            return new MediaServer[i];
        }
    };
    private long id;
    private ContentEventListener listener;
    private String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeLong(this.id);
    }

    public MediaServer(String str, long j) {
        this.name = str;
        this.id = j;
    }

    public boolean equals(Object obj) {
        MediaServer mediaServer = (MediaServer) obj;
        return mediaServer != null && this.id == mediaServer.id;
    }
}
