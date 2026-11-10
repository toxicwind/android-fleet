package com.google.android.exoplayer.text.pgs;

/* JADX INFO: loaded from: classes.dex */
public class PaletteInfo {
    private final int paletteOffset;
    private final int paletteSize;

    public PaletteInfo(int i, int i2) {
        this.paletteOffset = i;
        this.paletteSize = i2;
    }

    public int getPaletteOffset() {
        return this.paletteOffset;
    }

    public int getPaletteSize() {
        return this.paletteSize;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PaletteInfo.class != obj.getClass()) {
            return false;
        }
        PaletteInfo paletteInfo = (PaletteInfo) obj;
        return this.paletteOffset == paletteInfo.paletteOffset && this.paletteSize == paletteInfo.paletteSize;
    }

    public int hashCode() {
        return (this.paletteOffset * 31) + this.paletteSize;
    }

    public String toString() {
        return "PaletteInfo [paletteOffset=" + this.paletteOffset + ", paletteSize=" + this.paletteSize + "]";
    }
}
