package com.google.android.exoplayer.text.pgs;

/* JADX INFO: loaded from: classes.dex */
public class ImageObjectFragment {
    private final long imageBufferOfs;
    private final int imagePacketSize;

    public ImageObjectFragment(long j, int i) {
        this.imageBufferOfs = j;
        this.imagePacketSize = i;
    }

    public long getImageBufferOfs() {
        return this.imageBufferOfs;
    }

    public int getImagePacketSize() {
        return this.imagePacketSize;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ImageObjectFragment.class != obj.getClass()) {
            return false;
        }
        ImageObjectFragment imageObjectFragment = (ImageObjectFragment) obj;
        return this.imageBufferOfs == imageObjectFragment.imageBufferOfs && this.imagePacketSize == imageObjectFragment.imagePacketSize;
    }

    public int hashCode() {
        long j = this.imageBufferOfs;
        return (((int) (j ^ (j >>> 32))) * 31) + this.imagePacketSize;
    }

    public String toString() {
        return "ImageObjectFragment [imageBufferOfs=" + this.imageBufferOfs + ", imagePacketSize=" + this.imagePacketSize + "]";
    }
}
