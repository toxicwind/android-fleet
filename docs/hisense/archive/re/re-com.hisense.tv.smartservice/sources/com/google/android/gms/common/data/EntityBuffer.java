package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    public boolean zamh;
    public ArrayList<Integer> zami;

    @KeepForSdk
    public EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zamh = false;
    }

    private final void zabz() {
        synchronized (this) {
            if (!this.zamh) {
                int count = this.mDataHolder.getCount();
                this.zami = new ArrayList<>();
                if (count > 0) {
                    this.zami.add(0);
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    for (int i = 1; i < count; i++) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i, windowIndex);
                        if (string2 == null) {
                            StringBuilder sb = new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78);
                            sb.append("Missing value for markerColumn: ");
                            sb.append(primaryDataMarkerColumn);
                            sb.append(", at row: ");
                            sb.append(i);
                            sb.append(", for window: ");
                            sb.append(windowIndex);
                            throw new NullPointerException(sb.toString());
                        }
                        if (!string2.equals(string)) {
                            this.zami.add(Integer.valueOf(i));
                            string = string2;
                        }
                    }
                }
                this.zamh = true;
            }
        }
    }

    private final int zah(int i) {
        if (i >= 0 && i < this.zami.size()) {
            return this.zami.get(i).intValue();
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("Position ");
        sb.append(i);
        sb.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0066  */
    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @KeepForSdk
    public final T get(int i) {
        int iIntValue;
        int iIntValue2;
        zabz();
        int iZah = zah(i);
        int i2 = 0;
        if (i >= 0 && i != this.zami.size()) {
            if (i == this.zami.size() - 1) {
                iIntValue = this.mDataHolder.getCount();
                iIntValue2 = this.zami.get(i).intValue();
            } else {
                iIntValue = this.zami.get(i + 1).intValue();
                iIntValue2 = this.zami.get(i).intValue();
            }
            int i3 = iIntValue - iIntValue2;
            if (i3 == 1) {
                int iZah2 = zah(i);
                int windowIndex = this.mDataHolder.getWindowIndex(iZah2);
                String childDataMarkerColumn = getChildDataMarkerColumn();
                if (childDataMarkerColumn == null || this.mDataHolder.getString(childDataMarkerColumn, iZah2, windowIndex) != null) {
                    i2 = i3;
                }
            } else {
                i2 = i3;
            }
        }
        return getEntry(iZah, i2);
    }

    @KeepForSdk
    public String getChildDataMarkerColumn() {
        return null;
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @KeepForSdk
    public int getCount() {
        zabz();
        return this.zami.size();
    }

    @KeepForSdk
    public abstract T getEntry(int i, int i2);

    @KeepForSdk
    public abstract String getPrimaryDataMarkerColumn();
}
