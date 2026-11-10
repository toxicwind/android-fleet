package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;
import com.mediatek.twoworlds.tv.model.MtkTvCecActiveSourceBase;
import com.mediatek.twoworlds.tv.model.MtkTvCecDevDiscoveryInfoBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCecBase {
    public static int CEC_DEV_VNDR_ID_SIZE = 3;
    public static int CEC_USER_CTRL_OPERAND_SIZE = 4;
    public static int CEC_VNDR_CMD_WITH_ID_SIZE = 11;
    public static final String TAG = "MtkTvCec";

    public class CecDevInfo {
        public boolean ampconnected;
        public String devName;
        public byte[] devVndrId = new byte[MtkTvCecBase.CEC_DEV_VNDR_ID_SIZE];
        public CecDevType devtype;
        public String hdmiPort;
        public CecLogAddr logaddr;
        public String osdName;
        public int phyaddr;

        public CecDevInfo() {
        }

        public boolean getAmpConnected() {
            return this.ampconnected;
        }

        public String getDevName() {
            return this.devName;
        }

        public CecDevType getDevType() {
            return this.devtype;
        }

        public String getHdmiPort() {
            return this.hdmiPort;
        }

        public CecLogAddr getLogAddr() {
            return this.logaddr;
        }

        public String getOsdName() {
            return this.osdName;
        }

        public int getPhyAddr() {
            return this.phyaddr;
        }

        public byte[] getVndrId() {
            return this.devVndrId;
        }

        public void setAmpConnected(boolean z) {
            this.ampconnected = z;
        }

        public void setDevName(String str) {
            this.devName = str;
        }

        public void setDevType(CecDevType cecDevType) {
            this.devtype = cecDevType;
        }

        public void setHdmiPort(String str) {
            this.hdmiPort = str;
        }

        public void setLogAddr(CecLogAddr cecLogAddr) {
            this.logaddr = cecLogAddr;
        }

        public void setOsdName(String str) {
            this.osdName = str;
        }

        public void setPhyAddr(int i) {
            this.phyaddr = i;
        }

        public void setVndrId(byte[] bArr) {
            this.devVndrId = bArr;
        }

        public String toString() {
            StringBuilder sbA = a.a("devType= ");
            sbA.append(this.devtype);
            sbA.append("\ndevName= ");
            sbA.append(this.devName);
            sbA.append("\nhdmiPort= ");
            sbA.append(this.hdmiPort);
            sbA.append("\nosdName= ");
            sbA.append(this.osdName);
            sbA.append("\nampconnected= ");
            sbA.append(this.ampconnected);
            sbA.append("\nlogaddr= ");
            sbA.append(this.logaddr);
            sbA.append("\nphyaddr= 0x");
            sbA.append(Integer.toHexString(this.phyaddr));
            sbA.append("\ndevVndrId= ");
            sbA.append(Arrays.toString(this.devVndrId));
            return sbA.toString();
        }
    }

    public enum CecDevType {
        CEC_DEV_TYPE_TV,
        CEC_DEV_TYPE_REC_DEV,
        CEC_DEV_TYPE_RESERVED,
        CEC_DEV_TYPE_TUNER,
        CEC_DEV_TYPE_PLAYBACK_DEV,
        CEC_DEV_TYPE_AUD_SYS,
        CEC_DEV_TYPE_NONE
    }

    public enum CecLogAddr {
        CEC_LOG_ADDR_TV,
        CEC_LOG_ADDR_REC_DEV_1,
        CEC_LOG_ADDR_REC_DEV_2,
        CEC_LOG_ADDR_TUNER_1,
        CEC_LOG_ADDR_PLAYBACK_DEV_1,
        CEC_LOG_ADDR_AUD_SYS,
        CEC_LOG_ADDR_TUNER_2,
        CEC_LOG_ADDR_TUNER_3,
        CEC_LOG_ADDR_PLAYBACK_DEV_2,
        CEC_LOG_ADDR_REC_DEV_3,
        CEC_LOG_ADDR_TUNER_4,
        CEC_LOG_ADDR_PLAYBACK_DEV_3,
        CEC_LOG_ADDR_RESERVED_1,
        CEC_LOG_ADDR_RESERVED_2,
        CEC_LOG_ADDR_FREE_USE,
        CEC_LOG_ADDR_UNREGED_BRDCST,
        CEC_LOG_ADDR_MAX
    }

    public enum CecUIMsgType {
        CEC_UI_MSG_CEC_OTP,
        CEC_UI_MSG_SAC_AUD_STS,
        CEC_UI_MSG_CEC_SAC_STS
    }

    public class CecUserCtrlInfo {
        public int ilogAddr;
        public CecLogAddr logAddr;
        public int userCtrlCode;
        public int[] userCtrlOperand = new int[MtkTvCecBase.CEC_USER_CTRL_OPERAND_SIZE];
        public int userCtrlOperandSize;

        public CecUserCtrlInfo() {
        }

        public CecLogAddr getLogAddr() {
            return this.logAddr;
        }

        public int getUserCtrlCode() {
            return this.userCtrlCode;
        }

        public int[] getUserCtrlOperand() {
            return this.userCtrlOperand;
        }

        public int getUserCtrlOperandSize() {
            return this.userCtrlOperandSize;
        }

        public int getiLogAddr() {
            return this.ilogAddr;
        }

        public void setLogAddr(CecLogAddr cecLogAddr) {
            this.ilogAddr = cecLogAddr.ordinal();
            a.b(a.a("ilogAddr "), this.ilogAddr, MtkTvCecBase.TAG);
            this.logAddr = cecLogAddr;
        }

        public void setUserCtrlCode(int i) {
            this.userCtrlCode = i;
        }

        public void setUserCtrlOperand(int[] iArr) {
            this.userCtrlOperand = iArr;
            StringBuilder sbA = a.a("userCtrlOperand ");
            sbA.append(Arrays.toString(this.userCtrlOperand));
            Log.d(MtkTvCecBase.TAG, sbA.toString());
        }

        public void setUserCtrlOperandSize(int i) {
            this.userCtrlOperandSize = i;
        }
    }

    public class CecVndrCmdWithIdInfo {
        public CecLogAddr destLogAddr;
        public CecLogAddr initLogAddr;
        public int vndrCmdWithIdSize;
        public int[] vndrId = new int[MtkTvCecBase.CEC_DEV_VNDR_ID_SIZE];
        public int[] vndrCmdWithId = new int[MtkTvCecBase.CEC_VNDR_CMD_WITH_ID_SIZE];

        public CecVndrCmdWithIdInfo() {
        }

        public CecLogAddr getDestLogAddr() {
            return this.destLogAddr;
        }

        public CecLogAddr getInitLogAddr() {
            return this.initLogAddr;
        }

        public int[] getVndrCmdWithId() {
            return this.vndrCmdWithId;
        }

        public int[] getVndrId() {
            return this.vndrId;
        }

        public int getiDestLogAddr() {
            return this.destLogAddr.ordinal();
        }

        public int getiInitLogAddr() {
            return this.initLogAddr.ordinal();
        }

        public int getvndrCmdWithIdSize() {
            return this.vndrCmdWithIdSize;
        }

        public void setDestLogAddr(CecLogAddr cecLogAddr) {
            this.destLogAddr = cecLogAddr;
        }

        public void setInitLogAddr(CecLogAddr cecLogAddr) {
            this.initLogAddr = cecLogAddr;
        }

        public void setVndrCmdWithId(int[] iArr) {
            this.vndrCmdWithId = iArr;
            StringBuilder sbA = a.a("vndrCmdWithId ");
            sbA.append(Arrays.toString(this.vndrCmdWithId));
            Log.d(MtkTvCecBase.TAG, sbA.toString());
        }

        public void setVndrId(int[] iArr) {
            this.vndrId = iArr;
            StringBuilder sbA = a.a("vndrId ");
            sbA.append(Arrays.toString(this.vndrId));
            Log.d(MtkTvCecBase.TAG, sbA.toString());
        }

        public void setvndrCmdWithIdSize(int i) {
            this.vndrCmdWithIdSize = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v6, types: [int] */
    public int discoveryDevice(MtkTvCecDevDiscoveryInfoBase mtkTvCecDevDiscoveryInfoBase) throws Throwable {
        ?? r5;
        Log.d(TAG, "+ discoveryDevice.");
        MtkTvCecDevDiscoveryInfoBase mtkTvCecDevDiscoveryInfoBase2 = null;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "discoveryDevice_native begin");
                        int iDiscoveryDevice_native = TVNativeWrapper.discoveryDevice_native(mtkTvCecDevDiscoveryInfoBase);
                        if (iDiscoveryDevice_native != 0) {
                            throw new MtkTvExceptionBase(iDiscoveryDevice_native, "discoveryDevice_native fail");
                        }
                        r5 = iDiscoveryDevice_native;
                        Log.d(TAG, "- discoveryDevice.");
                        return r5;
                    } catch (Throwable th) {
                        th = th;
                        mtkTvCecDevDiscoveryInfoBase = null;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            mtkTvCecDevDiscoveryInfoBase2 = mtkTvCecDevDiscoveryInfoBase;
                            e = e2;
                            e.printStackTrace();
                            r5 = mtkTvCecDevDiscoveryInfoBase2;
                            Log.d(TAG, "- discoveryDevice.");
                            return r5;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                r5 = mtkTvCecDevDiscoveryInfoBase2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public MtkTvCecActiveSourceBase getActiveSourceInfo() {
        Log.d(TAG, "+ getActiveSourceInfo.");
        MtkTvCecActiveSourceBase mtkTvCecActiveSourceBase = new MtkTvCecActiveSourceBase();
        try {
            synchronized (this) {
                Log.d(TAG, "getActiveSourceInfo_native begin");
                int activeSourceInfo_native = TVNativeWrapper.getActiveSourceInfo_native(mtkTvCecActiveSourceBase);
                if (activeSourceInfo_native != 0) {
                    throw new MtkTvExceptionBase(activeSourceInfo_native, "TVNativeWrapper getActiveSourceInfo_native fail");
                }
                Log.d(TAG, "The device:\n" + mtkTvCecActiveSourceBase.toString() + "\n");
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- getActiveSourceInfo.");
        return mtkTvCecActiveSourceBase;
    }

    public CecDevInfo getCecDevInfo(int i) {
        Log.d(TAG, "+ getCecDevInfo.");
        CecDevInfo cecDevInfo = new CecDevInfo();
        try {
            synchronized (this) {
                Log.d(TAG, "getCecDevInfo_native begin");
                int cecDevInfo_native = TVNativeWrapper.getCecDevInfo_native(i, cecDevInfo);
                if (cecDevInfo_native != 0) {
                    throw new MtkTvExceptionBase(cecDevInfo_native, "TVNativeWrapper getCecDevInfo_native fail");
                }
                Log.d(TAG, "The device:\n" + cecDevInfo.toString() + "\n");
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- getCecDevInfo.");
        return cecDevInfo;
    }

    public List<CecDevInfo> getCecDevListInfo() {
        Log.d(TAG, "Enter getCecDevListInfo.");
        ArrayList arrayList = new ArrayList();
        try {
            synchronized (this) {
                Log.d(TAG, "getCecDevListInfo_native begin");
                int cecDevListInfo_native = TVNativeWrapper.getCecDevListInfo_native(arrayList);
                if (cecDevListInfo_native != 0) {
                    throw new MtkTvExceptionBase(cecDevListInfo_native, "TVNativeWrapper getCecDevListInfo_native fail");
                }
                Log.d(TAG, "cecDevList TotalNumber = " + arrayList.size() + "\n");
                for (int i = 0; i < arrayList.size(); i++) {
                    Log.d(TAG, "The " + i + " device:\n" + arrayList.get(i) + "\n");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "Leave getCecDevListInfo.");
        return arrayList;
    }

    public int isDeviceExist(int i) throws Throwable {
        int iIsDeviceExist_native;
        Log.d(TAG, "+ isDeviceExist.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "isDeviceExist_native begin");
                        iIsDeviceExist_native = TVNativeWrapper.isDeviceExist_native(i);
                        if (iIsDeviceExist_native != 0 && iIsDeviceExist_native != 1) {
                            throw new MtkTvExceptionBase(iIsDeviceExist_native, "TVNativeWrapper isDeviceExist_native fail");
                        }
                        Log.d(TAG, "- isDeviceExist.");
                        return iIsDeviceExist_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            iIsDeviceExist_native = i2;
                            Log.d(TAG, "- isDeviceExist.");
                            return iIsDeviceExist_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                iIsDeviceExist_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0023 */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0024, code lost:
    
        r5 = th;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int notifyCecCompInfo(java.lang.String r4, int r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.String r0 = "MtkTvCec"
            java.lang.String r1 = "+ notifyCecCompInfo."
            android.util.Log.d(r0, r1)
            r0 = 0
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L26
            java.lang.String r1 = "MtkTvCec"
            java.lang.String r2 = "notifyCecCompInfo_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L23
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.notifyCecCompInfo_native(r4, r5, r6)     // Catch: java.lang.Throwable -> L23
            if (r4 != 0) goto L18
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
            goto L2b
        L18:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r5 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L20
            java.lang.String r6 = "notifyCecCompInfo_native fail"
            r5.<init>(r4, r6)     // Catch: java.lang.Throwable -> L20
            throw r5     // Catch: java.lang.Throwable -> L20
        L20:
            r5 = move-exception
            r0 = r4
            goto L24
        L23:
            r5 = move-exception
        L24:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L23
            throw r5     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L26
        L26:
            r4 = move-exception
            r4.printStackTrace()
            r4 = r0
        L2b:
            java.lang.String r5 = "MtkTvCec"
            java.lang.String r6 = "- notifyCecCompInfo."
            android.util.Log.d(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvCecBase.notifyCecCompInfo(java.lang.String, int, java.lang.String):int");
    }

    public int powerOnDeviceByLogicAddr(int i) throws Throwable {
        int iPowerOnDeviceByLogicAddr_native;
        Log.d(TAG, "+ powerOnDeviceByLogicAddr.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "powerOnDeviceByLogicAddr_native begin");
                        iPowerOnDeviceByLogicAddr_native = TVNativeWrapper.powerOnDeviceByLogicAddr_native(i);
                        if (iPowerOnDeviceByLogicAddr_native != 0) {
                            throw new MtkTvExceptionBase(iPowerOnDeviceByLogicAddr_native, "powerOnDeviceByLogicAddr_native fail");
                        }
                        Log.d(TAG, "- powerOnDeviceByLogicAddr.");
                        return iPowerOnDeviceByLogicAddr_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            iPowerOnDeviceByLogicAddr_native = i2;
                            Log.d(TAG, "- powerOnDeviceByLogicAddr.");
                            return iPowerOnDeviceByLogicAddr_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                iPowerOnDeviceByLogicAddr_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int setRecordOff(int i) throws Throwable {
        int recordOff_native;
        Log.d(TAG, "+ setRecordOff.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "setRecordOff_native begin");
                        recordOff_native = TVNativeWrapper.setRecordOff_native(i);
                        if (recordOff_native != 0) {
                            throw new MtkTvExceptionBase(recordOff_native, "TVNativeWrapper setRecordOff_native fail");
                        }
                        Log.d(TAG, "- setRecordOff.");
                        return recordOff_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            recordOff_native = i2;
                            Log.d(TAG, "- setRecordOff.");
                            return recordOff_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                recordOff_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0023 */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0024, code lost:
    
        r5 = th;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int setRecordOn(int r4, com.mediatek.twoworlds.tv.model.MtkTvCecRecordSouceInfoBase r5) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.String r0 = "MtkTvCec"
            java.lang.String r1 = "+ setRecordOn."
            android.util.Log.d(r0, r1)
            r0 = 0
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L26
            java.lang.String r1 = "MtkTvCec"
            java.lang.String r2 = "setRecordOn_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L23
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.setRecordOn_native(r4, r5)     // Catch: java.lang.Throwable -> L23
            if (r4 != 0) goto L18
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
            goto L2b
        L18:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r5 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L20
            java.lang.String r0 = "TVNativeWrapper setRecordOn_native fail"
            r5.<init>(r4, r0)     // Catch: java.lang.Throwable -> L20
            throw r5     // Catch: java.lang.Throwable -> L20
        L20:
            r5 = move-exception
            r0 = r4
            goto L24
        L23:
            r5 = move-exception
        L24:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L23
            throw r5     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L26
        L26:
            r4 = move-exception
            r4.printStackTrace()
            r4 = r0
        L2b:
            java.lang.String r5 = "MtkTvCec"
            java.lang.String r0 = "- setRecordOn."
            android.util.Log.d(r5, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvCecBase.setRecordOn(int, com.mediatek.twoworlds.tv.model.MtkTvCecRecordSouceInfoBase):int");
    }

    public int setStandby(int i) throws Throwable {
        int standby_native;
        Log.d(TAG, "+ setStandby.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "setStandby_native begin");
                        standby_native = TVNativeWrapper.setStandby_native(i);
                        if (standby_native != 0) {
                            throw new MtkTvExceptionBase(standby_native, "TVNativeWrapper setStandby_native fail");
                        }
                        Log.d(TAG, "- setStandby.");
                        return standby_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            standby_native = i2;
                            Log.d(TAG, "- setStandby.");
                            return standby_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                standby_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v6, types: [int] */
    public int setStandbyToAll(boolean z) throws Throwable {
        ?? r5;
        Log.d(TAG, "+ setStandbyToAll.");
        boolean z2 = false;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "setStandbyToAll_native begin");
                        int standbyToAll_native = TVNativeWrapper.setStandbyToAll_native(z);
                        if (standbyToAll_native != 0) {
                            throw new MtkTvExceptionBase(standbyToAll_native, "TVNativeWrapper setStandbyToAll_native fail");
                        }
                        r5 = standbyToAll_native;
                        Log.d(TAG, "- setStandbyToAll.");
                        return r5;
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            z2 = z;
                            e = e2;
                            e.printStackTrace();
                            r5 = z2;
                            Log.d(TAG, "- setStandbyToAll.");
                            return r5;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                r5 = z2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int setSystemAudioModeRequest(int i) throws Throwable {
        int systemAudioModeRequest_native;
        Log.d(TAG, "+ setSystemAudioModeRequest.");
        int i2 = 0;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "setSystemAudioModeRequest_native begin");
                        systemAudioModeRequest_native = TVNativeWrapper.setSystemAudioModeRequest_native(i);
                        if (systemAudioModeRequest_native != 0) {
                            throw new MtkTvExceptionBase(systemAudioModeRequest_native, "TVNativeWrapper setSystemAudioModeRequest_native fail");
                        }
                        Log.d(TAG, "- setSystemAudioModeRequest.");
                        return systemAudioModeRequest_native;
                    } catch (Throwable th) {
                        th = th;
                        i = 0;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            i2 = i;
                            e = e2;
                            e.printStackTrace();
                            systemAudioModeRequest_native = i2;
                            Log.d(TAG, "- setSystemAudioModeRequest.");
                            return systemAudioModeRequest_native;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                systemAudioModeRequest_native = i2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0023 */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0024, code lost:
    
        r5 = th;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int setTimer(int r4, com.mediatek.twoworlds.tv.model.MtkTvCecTimeInfoBase r5) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.String r0 = "MtkTvCec"
            java.lang.String r1 = "+ setTimer."
            android.util.Log.d(r0, r1)
            r0 = 0
            monitor-enter(r3)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L26
            java.lang.String r1 = "MtkTvCec"
            java.lang.String r2 = "setTimer_native begin"
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L23
            int r4 = com.mediatek.twoworlds.tv.TVNativeWrapper.setTimer_native(r4, r5)     // Catch: java.lang.Throwable -> L23
            if (r4 != 0) goto L18
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
            goto L2b
        L18:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r5 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L20
            java.lang.String r0 = "TVNativeWrapper setTimer_native fail"
            r5.<init>(r4, r0)     // Catch: java.lang.Throwable -> L20
            throw r5     // Catch: java.lang.Throwable -> L20
        L20:
            r5 = move-exception
            r0 = r4
            goto L24
        L23:
            r5 = move-exception
        L24:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L23
            throw r5     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L26
        L26:
            r4 = move-exception
            r4.printStackTrace()
            r4 = r0
        L2b:
            java.lang.String r5 = "MtkTvCec"
            java.lang.String r0 = "- setTimer."
            android.util.Log.d(r5, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvCecBase.setTimer(int, com.mediatek.twoworlds.tv.model.MtkTvCecTimeInfoBase):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v6, types: [int] */
    public int setUserCtrlPressed(CecUserCtrlInfo cecUserCtrlInfo) throws Throwable {
        ?? r5;
        Log.d(TAG, "Enter setUserCtrlPressed.");
        CecUserCtrlInfo cecUserCtrlInfo2 = null;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "setUserCtrlPressed_native begin");
                        int userCtrlPressed_native = TVNativeWrapper.setUserCtrlPressed_native(cecUserCtrlInfo);
                        if (userCtrlPressed_native != 0) {
                            throw new MtkTvExceptionBase(userCtrlPressed_native, "TVNativeWrapper setUserCtrlPressed_native fail");
                        }
                        r5 = userCtrlPressed_native;
                        Log.d(TAG, "Leave setUserCtrlPressed.");
                        return r5;
                    } catch (Throwable th) {
                        th = th;
                        cecUserCtrlInfo = null;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            cecUserCtrlInfo2 = cecUserCtrlInfo;
                            e = e2;
                            e.printStackTrace();
                            r5 = cecUserCtrlInfo2;
                            Log.d(TAG, "Leave setUserCtrlPressed.");
                            return r5;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                r5 = cecUserCtrlInfo2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v6, types: [int] */
    public int setUserCtrlReleased(CecUserCtrlInfo cecUserCtrlInfo) throws Throwable {
        ?? r5;
        Log.d(TAG, "+ setUserCtrlReleased.");
        CecUserCtrlInfo cecUserCtrlInfo2 = null;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "setUserCtrlReleased_native begin");
                        int userCtrlReleased_native = TVNativeWrapper.setUserCtrlReleased_native(cecUserCtrlInfo);
                        if (userCtrlReleased_native != 0) {
                            throw new MtkTvExceptionBase(userCtrlReleased_native, "TVNativeWrapper setUserCtrlReleased_native fail");
                        }
                        r5 = userCtrlReleased_native;
                        Log.d(TAG, "- setUserCtrlReleased.");
                        return r5;
                    } catch (Throwable th) {
                        th = th;
                        cecUserCtrlInfo = null;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            cecUserCtrlInfo2 = cecUserCtrlInfo;
                            e = e2;
                            e.printStackTrace();
                            r5 = cecUserCtrlInfo2;
                            Log.d(TAG, "- setUserCtrlReleased.");
                            return r5;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                r5 = cecUserCtrlInfo2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v6, types: [int] */
    public int setVendorCmdWithId(CecVndrCmdWithIdInfo cecVndrCmdWithIdInfo) throws Throwable {
        ?? r5;
        Log.d(TAG, "+ setVendorCmdWithId.");
        CecVndrCmdWithIdInfo cecVndrCmdWithIdInfo2 = null;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "setVendorCmdWithId_native begin");
                        int vendorCmdWithId_native = TVNativeWrapper.setVendorCmdWithId_native(cecVndrCmdWithIdInfo);
                        if (vendorCmdWithId_native != 0) {
                            throw new MtkTvExceptionBase(vendorCmdWithId_native, "TVNativeWrapper setVendorCmdWithId_native fail");
                        }
                        r5 = vendorCmdWithId_native;
                        Log.d(TAG, "- setVendorCmdWithId.");
                        return r5;
                    } catch (Throwable th) {
                        th = th;
                        cecVndrCmdWithIdInfo = null;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            cecVndrCmdWithIdInfo2 = cecVndrCmdWithIdInfo;
                            e = e2;
                            e.printStackTrace();
                            r5 = cecVndrCmdWithIdInfo2;
                            Log.d(TAG, "- setVendorCmdWithId.");
                            return r5;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                r5 = cecVndrCmdWithIdInfo2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
