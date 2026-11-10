package b.f.b.b.b.a.c;

import android.content.Context;
import android.hardware.hdmi.HdmiControlManager;
import android.hardware.hdmi.HdmiDeviceInfo;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f691a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f692b;

    public a(Context context) {
        this.f692b = context.getApplicationContext();
    }

    public String a() {
        List deviceList;
        try {
            HdmiControlManager hdmiControlManager = (HdmiControlManager) this.f692b.getSystemService(HdmiControlManager.class);
            if (hdmiControlManager == null || (deviceList = hdmiControlManager.getClient(0).getDeviceList()) == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Iterator it = deviceList.iterator();
            while (it.hasNext()) {
                sb.append(((HdmiDeviceInfo) it.next()).getVendorId());
                sb.append("|");
            }
            return sb.toString();
        } catch (Exception e2) {
            Log.e(f691a, e2.toString());
            return "";
        }
    }

    public String b() {
        try {
            StringBuilder sb = new StringBuilder();
            List<StorageVolume> storageVolumes = ((StorageManager) this.f692b.getSystemService("storage")).getStorageVolumes();
            if (storageVolumes == null) {
                return "";
            }
            for (StorageVolume storageVolume : storageVolumes) {
                if (storageVolume.isRemovable()) {
                    sb.append(storageVolume.getDescription(this.f692b));
                    sb.append("|");
                }
            }
            return sb.toString();
        } catch (Exception e2) {
            Log.e(f691a, e2.toString());
            return "";
        }
    }
}
