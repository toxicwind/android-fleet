package com.hisense.uifac.util;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class USBUtils {
    public static List<UsbPathAndLabel> getUsbPath(Context context) {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        if (storageManager != null) {
            try {
                StorageVolume[] storageVolumeArr = (StorageVolume[]) storageManager.getClass().getDeclaredMethod("getVolumeList", null).invoke(storageManager, null);
                ArrayList arrayList2 = new ArrayList();
                Collections.addAll(arrayList2, storageVolumeArr);
                for (int size = arrayList2.size() - 1; size >= 0; size--) {
                    StorageVolume storageVolume = (StorageVolume) arrayList2.get(size);
                    String absolutePath2 = receivePath(storageVolume).getAbsolutePath();
                    if (absolutePath.contains(absolutePath2) || absolutePath2.contains(absolutePath)) {
                        arrayList2.remove(storageVolume);
                    }
                }
                for (int i = 0; i < arrayList2.size(); i++) {
                    UsbPathAndLabel usbPathAndLabel = new UsbPathAndLabel();
                    usbPathAndLabel.setPath(receivePath((StorageVolume) arrayList2.get(i)).getAbsolutePath());
                    usbPathAndLabel.setInternalPath(receiveInternalPath((StorageVolume) arrayList2.get(i)).getAbsolutePath());
                    usbPathAndLabel.setLabel(receiveName((StorageVolume) arrayList2.get(i)));
                    arrayList.add(usbPathAndLabel);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
        return arrayList;
    }

    private static File receivePath(StorageVolume storageVolume) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField = storageVolume.getClass().getDeclaredField("mPath");
        declaredField.setAccessible(true);
        return (File) declaredField.get(storageVolume);
    }

    private static File receiveInternalPath(StorageVolume storageVolume) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField = storageVolume.getClass().getDeclaredField("mInternalPath");
        declaredField.setAccessible(true);
        return (File) declaredField.get(storageVolume);
    }

    private static String receiveName(StorageVolume storageVolume) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField = storageVolume.getClass().getDeclaredField("mDescription");
        declaredField.setAccessible(true);
        return (String) declaredField.get(storageVolume);
    }

    public static class UsbPathAndLabel {
        private String mInternalPath;

        public void setLabel(String str) {
        }

        public void setPath(String str) {
        }

        public String getInternalPath() {
            return this.mInternalPath;
        }

        public void setInternalPath(String str) {
            this.mInternalPath = str;
        }
    }
}
