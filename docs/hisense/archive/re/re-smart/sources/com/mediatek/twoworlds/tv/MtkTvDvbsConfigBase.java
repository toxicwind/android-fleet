package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;
import com.mediatek.twoworlds.tv.model.MtkTvDvbsConfigInfoBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvDvbsConfigBase {
    public static final int DVBS_CFG_SATL_MASK_LNB_SINGCAB = 32768;
    public static final int DVBS_CFG_SATL_MASK_LNB_UNIVER = 16384;
    public static final int DVBS_CFG_SATL_MASK_STATUS_ON = 8192;
    public static final String TAG = "MtkTvDvbsConfigBase";

    public static int createSatlSnapshot(int i) {
        return TVNativeWrapper.createSatlSnapshot_native(i);
    }

    public static int freeSatlSnapshot(int i) {
        return TVNativeWrapper.freeSatlSnapshot_native(i);
    }

    public static int restoreSatlSnapshot(int i) {
        return TVNativeWrapper.restoreSatlSnapshot_native(i);
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x002e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getSatlNumRecs(int r4) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.String r0 = "Enter getSatlRecord("
            java.lang.String r1 = ")\n"
            java.lang.String r2 = "MtkTvDvbsConfigBase"
            b.a.a.a.a.a(r0, r4, r1, r2)
            r0 = 0
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L30
            java.lang.String r1 = "MtkTvDvbsConfigBase"
            java.lang.String r2 = "getSatlNumRecs_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L25
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.getSatlNumRecs_native(r4)     // Catch: java.lang.Throwable -> L25
            if (r4 < 0) goto L1a
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L22
            goto L35
        L1a:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r0 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L22
            java.lang.String r1 = "getSatlNumRecs_native fail"
            r0.<init>(r4, r1)     // Catch: java.lang.Throwable -> L22
            throw r0     // Catch: java.lang.Throwable -> L22
        L22:
            r0 = move-exception
            r1 = r4
            goto L28
        L25:
            r4 = move-exception
            r1 = r0
            r0 = r4
        L28:
            r4 = r3
        L29:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2e
            throw r0     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L2b
        L2b:
            r4 = move-exception
            r0 = r1
            goto L31
        L2e:
            r0 = move-exception
            goto L29
        L30:
            r4 = move-exception
        L31:
            r4.printStackTrace()
            r4 = r0
        L35:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvDvbsConfigBase.getSatlNumRecs(int):int");
    }

    public List<MtkTvDvbsConfigInfoBase> getSatlRecord(int i, int i2) {
        Log.d(TAG, a.a("Enter getSatlRecord(", i, ",", i2, ")\n"));
        ArrayList arrayList = new ArrayList();
        try {
            synchronized (this) {
                Log.d(TAG, "getSatlRecord_native begin");
                int satlRecord_native = TVNativeWrapper.getSatlRecord_native(i, i2, arrayList);
                if (satlRecord_native != 0) {
                    throw new MtkTvExceptionBase(satlRecord_native, "getSatlRecord_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public List<MtkTvDvbsConfigInfoBase> getSatlRecordByRecIdx(int i, int i2) {
        Log.d(TAG, a.a("Enter getSatlRecord(", i, ",", i2, ")\n"));
        ArrayList arrayList = new ArrayList();
        try {
            synchronized (this) {
                Log.d(TAG, "getSatlRecordByRecIdx_native begin");
                int satlRecordByRecIdx_native = TVNativeWrapper.getSatlRecordByRecIdx_native(i, i2, arrayList);
                if (satlRecordByRecIdx_native != 0) {
                    throw new MtkTvExceptionBase(satlRecordByRecIdx_native, "getSatlRecordByRecIdx_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public int satllistCleanDatabase(int i) throws Throwable {
        int iSatllistCleanDatabase_native;
        Log.d(TAG, "+ satllistCleanDatabase.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "satllistCleanDatabase_native begin");
                        iSatllistCleanDatabase_native = TVNativeWrapper.satllistCleanDatabase_native(i);
                        if (iSatllistCleanDatabase_native != 0) {
                            throw new MtkTvExceptionBase(iSatllistCleanDatabase_native, "satllistCleanDatabase_native fail");
                        }
                        Log.d(TAG, "- satllistCleanDatabase.");
                        return iSatllistCleanDatabase_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            iSatllistCleanDatabase_native = i2;
                            Log.d(TAG, "- satllistCleanDatabase.");
                            return iSatllistCleanDatabase_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                iSatllistCleanDatabase_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int satllistLockDatabase(int i) throws Throwable {
        int iSatllistLockDatabase_native;
        Log.d(TAG, "+ satllistLockDatabase.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "satllistLockDatabase_native begin");
                        iSatllistLockDatabase_native = TVNativeWrapper.satllistLockDatabase_native(i);
                        if (iSatllistLockDatabase_native != 0) {
                            throw new MtkTvExceptionBase(iSatllistLockDatabase_native, "satllistLockDatabase_native fail");
                        }
                        Log.d(TAG, "- satllistLockDatabase.");
                        return iSatllistLockDatabase_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            iSatllistLockDatabase_native = i2;
                            Log.d(TAG, "- satllistLockDatabase.");
                            return iSatllistLockDatabase_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                iSatllistLockDatabase_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int satllistReadLockDatabase(int i) throws Throwable {
        int iSatllistReadLockDatabase_native;
        Log.d(TAG, "+ satllistReadLockDatabase.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "satllistReadLockDatabase_native begin");
                        iSatllistReadLockDatabase_native = TVNativeWrapper.satllistReadLockDatabase_native(i);
                        if (iSatllistReadLockDatabase_native != 0) {
                            throw new MtkTvExceptionBase(iSatllistReadLockDatabase_native, "satllistReadLockDatabase_native fail");
                        }
                        Log.d(TAG, "- satllistReadLockDatabase.");
                        return iSatllistReadLockDatabase_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            iSatllistReadLockDatabase_native = i2;
                            Log.d(TAG, "- satllistReadLockDatabase.");
                            return iSatllistReadLockDatabase_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                iSatllistReadLockDatabase_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int satllistReadUnLockDatabase(int i) throws Throwable {
        int iSatllistReadUnLockDatabase_native;
        Log.d(TAG, "+ satllistReadUnLockDatabase.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "satllistReadUnLockDatabase_native begin");
                        iSatllistReadUnLockDatabase_native = TVNativeWrapper.satllistReadUnLockDatabase_native(i);
                        if (iSatllistReadUnLockDatabase_native != 0) {
                            throw new MtkTvExceptionBase(iSatllistReadUnLockDatabase_native, "satllistReadUnLockDatabase_native fail");
                        }
                        Log.d(TAG, "- satllistReadUnLockDatabase.");
                        return iSatllistReadUnLockDatabase_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            iSatllistReadUnLockDatabase_native = i2;
                            Log.d(TAG, "- satllistReadUnLockDatabase.");
                            return iSatllistReadUnLockDatabase_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                iSatllistReadUnLockDatabase_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int satllistUnLockDatabase(int i) throws Throwable {
        int iSatllistUnLockDatabase_native;
        Log.d(TAG, "+ satllistUnLockDatabase.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "satllistUnLockDatabase_native begin");
                        iSatllistUnLockDatabase_native = TVNativeWrapper.satllistUnLockDatabase_native(i);
                        if (iSatllistUnLockDatabase_native != 0) {
                            throw new MtkTvExceptionBase(iSatllistUnLockDatabase_native, "satllistUnLockDatabase_native fail");
                        }
                        Log.d(TAG, "- satllistUnLockDatabase.");
                        return iSatllistUnLockDatabase_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            iSatllistUnLockDatabase_native = i2;
                            Log.d(TAG, "- satllistUnLockDatabase.");
                            return iSatllistUnLockDatabase_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                iSatllistUnLockDatabase_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x003f */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
    
        r5 = th;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int updateSatlRecord(int r4, com.mediatek.twoworlds.tv.model.MtkTvDvbsConfigInfoBase r5, boolean r6) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Enter updateSatlRecord("
            r0.append(r1)
            r0.append(r4)
            java.lang.String r1 = ","
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = ")\n"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "MtkTvDvbsConfigBase"
            android.util.Log.d(r1, r0)
            r0 = 0
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L42
            java.lang.String r1 = "MtkTvDvbsConfigBase"
            java.lang.String r2 = "updateSatlRecord_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L3f
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.updateSatlRecord_native(r4, r5, r6)     // Catch: java.lang.Throwable -> L3f
            if (r4 != 0) goto L34
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L3c
            goto L47
        L34:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r5 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L3c
            java.lang.String r6 = "updateSatlRecord_native fail"
            r5.<init>(r4, r6)     // Catch: java.lang.Throwable -> L3c
            throw r5     // Catch: java.lang.Throwable -> L3c
        L3c:
            r5 = move-exception
            r0 = r4
            goto L40
        L3f:
            r5 = move-exception
        L40:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L3f
            throw r5     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L42
        L42:
            r4 = move-exception
            r4.printStackTrace()
            r4 = r0
        L47:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvDvbsConfigBase.updateSatlRecord(int, com.mediatek.twoworlds.tv.model.MtkTvDvbsConfigInfoBase, boolean):int");
    }
}
