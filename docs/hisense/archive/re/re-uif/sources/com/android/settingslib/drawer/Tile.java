package com.android.settingslib.drawer;

import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import java.util.ArrayList;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public abstract class Tile implements Parcelable {
    public static final Parcelable.Creator<Tile> CREATOR = new Parcelable.Creator<Tile>() { // from class: com.android.settingslib.drawer.Tile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Tile createFromParcel(Parcel parcel) {
            boolean z = parcel.readBoolean();
            parcel.setDataPosition(0);
            return z ? new ProviderTile(parcel) : new ActivityTile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Tile[] newArray(int i) {
            return new Tile[i];
        }
    };
    private String mCategory;
    protected ComponentInfo mComponentInfo;
    private final String mComponentName;
    private final String mComponentPackage;
    long mLastUpdateTime;
    private Bundle mMetaData;
    public ArrayList<UserHandle> userHandle = new ArrayList<>();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Tile(ComponentInfo componentInfo, String str) {
        this.mComponentInfo = componentInfo;
        this.mComponentPackage = componentInfo.packageName;
        this.mComponentName = componentInfo.name;
        this.mCategory = str;
        new Intent().setClassName(this.mComponentPackage, this.mComponentName);
    }

    Tile(Parcel parcel) {
        parcel.readBoolean();
        this.mComponentPackage = parcel.readString();
        this.mComponentName = parcel.readString();
        new Intent().setClassName(this.mComponentPackage, this.mComponentName);
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            this.userHandle.add((UserHandle) UserHandle.CREATOR.createFromParcel(parcel));
        }
        this.mCategory = parcel.readString();
        this.mMetaData = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this instanceof ProviderTile);
        parcel.writeString(this.mComponentPackage);
        parcel.writeString(this.mComponentName);
        int size = this.userHandle.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.userHandle.get(i2).writeToParcel(parcel, i);
        }
        parcel.writeString(this.mCategory);
        parcel.writeBundle(this.mMetaData);
    }

    public int getOrder() {
        if (hasOrder()) {
            return this.mMetaData.getInt("com.android.settings.order");
        }
        return 0;
    }

    public boolean hasOrder() {
        return this.mMetaData.containsKey("com.android.settings.order") && (this.mMetaData.get("com.android.settings.order") instanceof Integer);
    }

    public void setMetaData(Bundle bundle) {
        this.mMetaData = bundle;
    }

    public Bundle getMetaData() {
        return this.mMetaData;
    }

    static {
        $$Lambda$Tile$5_ETnVHzVG6DF0RKPoy76eRIQM __lambda_tile_5_etnvhzvg6df0rkpoy76eriqm = new Comparator() { // from class: com.android.settingslib.drawer.-$$Lambda$Tile$5_ETnVHzVG6DF0RKPoy76eRI-QM
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tile.lambda$static$0((Tile) obj, (Tile) obj2);
            }
        };
    }

    static /* synthetic */ int lambda$static$0(Tile tile, Tile tile2) {
        return tile2.getOrder() - tile.getOrder();
    }
}
