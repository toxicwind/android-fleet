package com.google.android.exoplayer.demo;

import android.annotation.TargetApi;
import android.os.Parcel;
import com.google.android.exoplayer.drm.ExoMediaDrm;
import com.google.android.exoplayer.drm.MediaDrmCallback;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public class MarlinIntertrustTestMediaDrmCallback implements MediaDrmCallback {
    private String mServerUrl = null;
    private String mContentId = null;

    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws IOException {
        return new byte[0];
    }

    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws IOException {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(keyRequest.getData(), 0, keyRequest.getData().length);
        parcelObtain.setDataPosition(0);
        int i = parcelObtain.readInt();
        if (i != 65536) {
            parcelObtain.recycle();
            throw new IOException("Unexpected plugin version: " + i);
        }
        int i2 = parcelObtain.readInt();
        int i3 = parcelObtain.readInt();
        HashMap map = new HashMap(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            map.put(new String(parcelObtain.createByteArray()), new String(parcelObtain.createByteArray()));
        }
        parcelObtain.recycle();
        String str = (String) map.get("key-type");
        if (str == null) {
            throw new IOException("No key-type in key request!?");
        }
        String str2 = this.mServerUrl;
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain2.writeInt(MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN);
        parcelObtain2.writeInt(i2);
        parcelObtain2.writeInt(2);
        parcelObtain2.writeByteArray("content-id".getBytes());
        parcelObtain2.writeByteArray(this.mContentId.getBytes());
        if (str.equals("streaming")) {
            parcelObtain2.writeByteArray("ms3-url".getBytes());
        } else if (str.equals("offline")) {
            parcelObtain2.writeByteArray("bb-token".getBytes());
        } else {
            parcelObtain2.recycle();
            throw new IOException("Unexpected key type: " + str);
        }
        parcelObtain2.writeByteArray(str2.getBytes());
        byte[] bArrMarshall = parcelObtain2.marshall();
        parcelObtain2.recycle();
        return bArrMarshall;
    }
}
