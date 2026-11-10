package com.hisense.support.tv;

import android.R;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.PackageManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import b.a.a.a.a;
import com.google.common.base.Ascii;
import com.google.protobuf.MessageSchema;
import com.hisense.avmiddleware.AvMiddleWareManager;
import com.hisense.avmiddleware.HisAudioApi;
import com.hisense.avmiddleware.HisHdmiApi;
import com.hisense.avmiddleware.HisVideoApi;
import com.ju.lib.utils.log.LogUtil;
import com.mediatek.LedFactory.LedFactory;
import com.mediatek.net.MtkNetworkManager;
import com.mediatek.twoworlds.tv.MtkTvChannelListBase;
import com.mediatek.twoworlds.tv.MtkTvConfig;
import com.mediatek.twoworlds.tv.MtkTvInputSource;
import com.mediatek.twoworlds.tv.MtkTvInputSourceBase;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.common.MtkTvChCommonBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import com.mediatek.twoworlds.tv.model.MtkTvATSCChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvAnalogChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvDvbChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvISDBChannelInfo;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes2.dex */
public class HisenseTvAPIBase {
    public static final String ADVERTISEMENT_BOOTANIMATION_FILE = "/data/local/misc/third_party_bootanimation.zip";
    public static final int CFG_PANEL_OFF = 6;
    public static final int CFG_PANEL_ON = 5;
    public static final int CFG_START_JB = 3;
    public static final int CFG_START_OFFRS = 1;
    public static final int CFG_STOP_JB = 4;
    public static final int CFG_STOP_OFFRS = 2;
    public static final int CLEAN_DATA_ERROR = -3;
    public static final boolean DEBUG_HISENSE_TVAPI = false;
    public static final String HISENSE_FFGA_SCREENLESS = "persist.vt.ffga.screenless";
    public static final String PANELPROTECT_CONFIG_NAME = "cfg_panelprotect_status_change";
    public static final String SETTING_TV_AGING_MODE = "com.jamdeo.setting.TV_AGING_MODE";
    public static final String SETTING_TV_FACTORY_MODE = "com.jamdeo.setting.TV_FACTORY_MODE";
    public static final String TAG = "TV_HisenseTvAPI";
    public Context mContext;
    public String[] mLaunchedPackages;
    public static final String[] BLACKLIST_PACKAGES = {"com.android.settings", "com.mediatek.wwtv.tvcenter", "com.android.tv.settings", "com.hisense.assistantmenu", "com.android.providers.settings", "com.android.browser", "com.android.gallery3d", "com.android.music", "com.google.android.tvlauncher", "com.google.android.leanbacklauncher", "com.google.android.leanbacklauncher", "com.jamdeo.tv.mediacenter"};
    public static final String[] WHITELIST_PACKAGES = {"android", "com.jamdeo.tv.service", "com.jamdeo.tv.resources", "com.jamdeo.tv.fte", "com.android.provision"};
    public HisAudioApi mHisAudioApi = null;
    public HisVideoApi mHisVideoApi = null;
    public AvMiddleWareManager mAvMiddleWareManager = null;
    public LedFactory mMicLedController = null;
    public String[] val_language_dtv = {MtkTvConfigTypeBase.S639_CFG_LANG_ENG, MtkTvConfigTypeBase.S639_CFG_LANG_ENG, MtkTvConfigTypeBase.S639_CFG_LANG_FRE, MtkTvConfigTypeBase.S639_CFG_LANG_SPA, MtkTvConfigTypeBase.S639_CFG_LANG_POR, MtkTvConfigTypeBase.S639_CFG_LANG_ARA, MtkTvConfigTypeBase.S639_CFG_LANG_RUS, MtkTvConfigTypeBase.S639_CFG_LANG_CHI, MtkTvConfigTypeBase.S639_CFG_LANG_VIE, MtkTvConfigTypeBase.S639_CFG_LANG_THA, MtkTvConfigTypeBase.S639_CFG_LANG_MMR, MtkTvConfigTypeBase.S639_CFG_LANG_UZB, MtkTvConfigTypeBase.S639_CFG_LANG_HIN, MtkTvConfigTypeBase.S639_CFG_LANG_UKR, "msa", MtkTvConfigTypeBase.S639_CFG_LANG_HBR, MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, MtkTvConfigTypeBase.S639_CFG_LANG_GER, MtkTvConfigTypeBase.S639_CFG_LANG_ITA, MtkTvConfigTypeBase.S639_CFG_LANG_SWE, MtkTvConfigTypeBase.S638_CFG_LANG_DAN, MtkTvConfigTypeBase.S639_CFG_LANG_FIN, MtkTvConfigTypeBase.S639_CFG_LANG_NOR, MtkTvConfigTypeBase.S639_CFG_LANG_CZE, MtkTvConfigTypeBase.S639_CFG_LANG_SLK, MtkTvConfigTypeBase.S639_CFG_LANG_POL, MtkTvConfigTypeBase.S639_CFG_LANG_HUN, MtkTvConfigTypeBase.S639_CFG_LANG_BUL, MtkTvConfigTypeBase.S639_CFG_LANG_TUR, MtkTvConfigTypeBase.S639_CFG_LANG_PER, MtkTvConfigTypeBase.S639_CFG_LANG_IND, MtkTvConfigTypeBase.S639_CFG_LANG_HRV, MtkTvConfigTypeBase.S639_CFG_LANG_SRP, "mac", "alb", MtkTvConfigTypeBase.S639_CFG_LANG_LAV, MtkTvConfigTypeBase.S639_CFG_LANG_EST, MtkTvConfigTypeBase.S639_CFG_LANG_LIT, "kur", MtkTvConfigTypeBase.S639_CFG_LANG_SLV, MtkTvConfigTypeBase.S639_CFG_LANG_DUT, MtkTvConfigTypeBase.S639_CFG_LANG_RUM, MtkTvConfigTypeBase.S639_CFG_LANG_GRE, "arm", "Geo", MtkTvConfigTypeBase.S639_CFG_LANG_URD, MtkTvConfigTypeBase.S639_CFG_LANG_AMH, "kir", MtkTvConfigTypeBase.S639_CFG_LANG_KAZ, MtkTvConfigTypeBase.S639_CFG_LANG_AZE, "bsn", "icl", "byl", MtkTvConfigTypeBase.S639_CFG_LANG_HIN, "mld", "bng", "mog", "cnr"};
    public int MAX_DTV_LANGUAGE = this.val_language_dtv.length;

    public static class ChannelIDCal_ATSC_ISDB {
        public static final int SB_ATSC_NON_PSIP_FLAG = 64;
        public static final int SB_ATSC_ONE_PART_FLAG = 128;
        public static final int _CHATTR_BITS = 2;
        public static final int _CH_INDEX_BITS = 6;
        public static final int _MAJOR_NUM_BITS = 14;
        public static final int _MAJOR_NUM_MASK = 16383;
        public static final int _MINOR_NUM_BITS = 10;
        public static final int _MINOR_NUM_MASK = 1023;
        public static final int _NON_NUM_BITS = 8;
        public static final int _NP_MAJOR_NUM_BITS = 14;
        public static final int _NP_MAJOR_NUM_MASK = 16383;
        public static final int _NP_MINOR_NUM_BITS = 10;
        public static final int _NP_MINOR_NUM_MASK = 16383;
        public static final int _OP_MAJOR_NUM_BITS = 14;
        public static final int _OP_MAJOR_NUM_MASK = 16383;
        public static final int _OP_MINOR_NUM_BITS = 10;
        public static final int _OP_MINOR_NUM_MASK = 1023;
        public static final int _CH_INDEX_MASK = 63;
        public static final int SB_ATSC_MAX_CHANNEL_INDEX = _CH_INDEX_MASK;

        public static int MAKE_BIT_MASK_32(int i) {
            return 1 << i;
        }

        public static int SB_ATSC_GEN_CHANNEL_ID(int i, int i2, int i3) {
            return ((i & _MAJOR_NUM_MASK) << 18) | ((i2 & _MINOR_NUM_MASK) << 8) | (_CH_INDEX_MASK & i3);
        }

        public static int SB_ATSC_GEN_CHANNEL_ID_EX(int i, int i2, int i3) {
            return ((i & _OP_MAJOR_NUM_MASK) << 18) | ((i3 & _OP_MINOR_NUM_MASK) << 8) | (i2 & _CH_INDEX_MASK) | SB_ATSC_ONE_PART_FLAG;
        }

        public static int SB_ATSC_GEN_NON_PSIP_CHANNEL_ID(int i, int i2, int i3) {
            return ((i & _NP_MAJOR_NUM_MASK) << 18) | ((i2 & _NP_MINOR_NUM_MASK) << 8) | (_CH_INDEX_MASK & i3);
        }

        public static int SB_ATSC_GEN_ONE_PART_CHANNEL_ID(int i, int i2) {
            return ((i & _OP_MAJOR_NUM_MASK) << 18) | (i2 & _CH_INDEX_MASK) | SB_ATSC_ONE_PART_FLAG;
        }

        public static int SB_ATSC_GET_CHANNEL_EXTRA_INDEX(int i) {
            return (short) ((i >> 8) & _OP_MINOR_NUM_MASK);
        }

        public static int SB_ATSC_GET_CHANNEL_INDEX(int i) {
            return i & _CH_INDEX_MASK;
        }

        public static int SB_ATSC_GET_MAJOR_CHANNEL_NUM(int i) {
            int i2;
            int i3;
            if (SB_ATSC_IS_ONE_PART_CHANNEL_ID(i)) {
                i2 = i >> 18;
                i3 = _OP_MAJOR_NUM_MASK;
            } else if (SB_ATSC_IS_NON_PSIP_CHANNEL_ID(i)) {
                i2 = i >> 18;
                i3 = _NP_MAJOR_NUM_MASK;
            } else {
                i2 = i >> 18;
                i3 = _MAJOR_NUM_MASK;
            }
            return (short) (i2 & i3);
        }

        public static int SB_ATSC_GET_MINOR_CHANNEL_NUM(int i) {
            int i2;
            int i3;
            if (SB_ATSC_IS_NON_PSIP_CHANNEL_ID(i)) {
                i2 = i >> 8;
                i3 = _NP_MINOR_NUM_MASK;
            } else {
                i2 = i >> 8;
                i3 = _MINOR_NUM_MASK;
            }
            return i2 & i3;
        }

        public static boolean SB_ATSC_IS_NON_PSIP_CHANNEL_ID(int i) {
            return (i & SB_ATSC_NON_PSIP_FLAG) != 0;
        }

        public static boolean SB_ATSC_IS_ONE_PART_CHANNEL_ID(int i) {
            return (i & SB_ATSC_ONE_PART_FLAG) != 0;
        }
    }

    private class CleanAppsThread extends Thread {
        public List<String> mCleanList;
        public volatile int mCleanedCount;
        public Context mContext;
        public IFactoryCleanAppsListener mListener;
        public boolean mNeedUninstallNonSystemPackage;
        public volatile int mUninstalledCount;

        public CleanAppsThread(Context context, boolean z, IFactoryCleanAppsListener iFactoryCleanAppsListener) {
            super("CleanApps");
            this.mUninstalledCount = 0;
            this.mCleanedCount = 0;
            this.mContext = context;
            this.mNeedUninstallNonSystemPackage = z;
            this.mListener = iFactoryCleanAppsListener;
            this.mCleanList = new ArrayList();
        }

        public static /* synthetic */ int access$308(CleanAppsThread cleanAppsThread) {
            int i = cleanAppsThread.mCleanedCount;
            cleanAppsThread.mCleanedCount = i + 1;
            return i;
        }

        public static /* synthetic */ int access$508(CleanAppsThread cleanAppsThread) {
            int i = cleanAppsThread.mUninstalledCount;
            cleanAppsThread.mUninstalledCount = i + 1;
            return i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService("activity");
            PackageManager packageManager = this.mContext.getPackageManager();
            if (activityManager == null || packageManager == null) {
                Log.w(HisenseTvAPIBase.TAG, "stop cleaning due to null android manager");
                return;
            }
            List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(8192);
            if (installedApplications == null) {
                Log.w(HisenseTvAPIBase.TAG, "stop cleaning due to null app list");
                return;
            }
            final int size = installedApplications.size();
            for (String str : HisenseTvAPIBase.BLACKLIST_PACKAGES) {
                try {
                    packageManager.getApplicationInfo(str, 8192);
                    this.mCleanList.add(str);
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.w(HisenseTvAPIBase.TAG, "package:" + str + "not exist");
                }
            }
            Iterator<ApplicationInfo> it = installedApplications.iterator();
            while (it.hasNext()) {
                this.mCleanList.add(it.next().packageName);
            }
            if (HisenseTvAPIBase.this.mLaunchedPackages != null) {
                for (String str2 : HisenseTvAPIBase.this.mLaunchedPackages) {
                    if (!this.mCleanList.contains(str2)) {
                        this.mCleanList.add(str2);
                    }
                }
            }
            for (String str3 : HisenseTvAPIBase.WHITELIST_PACKAGES) {
                if (this.mCleanList.contains(str3)) {
                    this.mCleanList.remove(str3);
                }
            }
            String packageName = this.mContext.getPackageName();
            if (this.mCleanList.contains(packageName)) {
                this.mCleanList.remove(packageName);
            }
            Iterator<String> it2 = this.mCleanList.iterator();
            while (it2.hasNext()) {
                a.c("clean list contains: ", it2.next(), HisenseTvAPIBase.TAG);
            }
            final int size2 = this.mCleanList.size();
            a.b("totalPackagesNumberNeedClean is  ", size2, HisenseTvAPIBase.TAG);
            for (String str4 : this.mCleanList) {
                try {
                    activityManager.clearApplicationUserData(str4, new IPackageDataObserver.Stub() { // from class: com.hisense.support.tv.HisenseTvAPIBase.CleanAppsThread.1
                        public void onRemoveCompleted(String str5, boolean z) {
                            CleanAppsThread.access$308(CleanAppsThread.this);
                            if (CleanAppsThread.this.mListener != null) {
                                if (z) {
                                    int i = ((CleanAppsThread.this.mCleanedCount + CleanAppsThread.this.mUninstalledCount) * 100) / (size2 + size);
                                    if (i < 0) {
                                        i = 0;
                                    }
                                    CleanAppsThread.this.mListener.notifyProgress(i);
                                } else {
                                    CleanAppsThread.this.mListener.notifyError(str5, -3);
                                }
                                if (CleanAppsThread.this.mCleanedCount == size2 && CleanAppsThread.this.mUninstalledCount == size) {
                                    Log.i(HisenseTvAPIBase.TAG, "clean app completed");
                                    CleanAppsThread.this.mListener.notifyCompleted();
                                }
                            }
                            Log.i(HisenseTvAPIBase.TAG, "clean data of package: " + str5 + " return " + z);
                        }
                    });
                } catch (Exception e2) {
                    Log.e(HisenseTvAPIBase.TAG, "Exception occurred when clean app " + str4 + ", e = " + e2);
                }
            }
            a.b(a.a("mCleanedCount is  "), this.mCleanedCount, HisenseTvAPIBase.TAG);
            if (this.mNeedUninstallNonSystemPackage) {
                for (ApplicationInfo applicationInfo : installedApplications) {
                    if ((applicationInfo.flags & 1) == 0) {
                        String str5 = applicationInfo.packageName;
                        try {
                            packageManager.deletePackage(str5, new IPackageDeleteObserver.Stub() { // from class: com.hisense.support.tv.HisenseTvAPIBase.CleanAppsThread.2
                                public void packageDeleted(String str6, int i) {
                                    if (CleanAppsThread.this.mListener != null) {
                                        CleanAppsThread.access$508(CleanAppsThread.this);
                                        if (i == 1) {
                                            int i2 = ((CleanAppsThread.this.mCleanedCount + CleanAppsThread.this.mUninstalledCount) * 100) / (size2 + size);
                                            if (i2 < 0) {
                                                i2 = 0;
                                            }
                                            CleanAppsThread.this.mListener.notifyProgress(i2);
                                        } else {
                                            CleanAppsThread.this.mListener.notifyError(str6, i);
                                        }
                                        if (CleanAppsThread.this.mCleanedCount == size2 && CleanAppsThread.this.mUninstalledCount == size) {
                                            Log.i(HisenseTvAPIBase.TAG, "clean app completed");
                                            CleanAppsThread.this.mListener.notifyCompleted();
                                        }
                                    }
                                    Log.i(HisenseTvAPIBase.TAG, "uninstall non-system package " + str6 + " return " + i);
                                }
                            }, 0);
                        } catch (Exception e3) {
                            Log.e(HisenseTvAPIBase.TAG, "Exception occurred when uninstall app " + str5 + ", e = " + e3);
                        }
                    } else {
                        this.mUninstalledCount++;
                    }
                }
            } else {
                this.mUninstalledCount = size;
            }
            Log.i(HisenseTvAPIBase.TAG, "thread finish clean app ....");
            if (this.mCleanedCount == size2 && this.mUninstalledCount == size) {
                Log.i(HisenseTvAPIBase.TAG, "clean app completed");
                this.mListener.notifyCompleted();
            }
        }
    }

    public static class DvbChannelUtil {
        public static final int SB_DVB_CH_NUM_FLAG = 128;
        public static final int SB_DVB_MAX_CHANNEL_INDEX = 15;

        public static int SB_DVB_GEN_CHANNEL_ID(int i, int i2) {
            return ((i & 16383) << 18) | (i2 & 15) | 128;
        }

        public static int SB_DVB_GEN_CHANNEL_ID_WITH_LCN(int i, int i2, int i3) {
            return ((i & 16383) << 18) | ((i2 & 1023) << 8) | (i3 & 15) | 128 | ((i2 & LogUtil.LOGCAT_MESSAGE_MAX_LENTH) >> 5);
        }

        public static int SB_DVB_GET_CHANNEL_INDEX(int i) {
            return i & 15;
        }

        public static int SB_DVB_GET_CHANNEL_NUM(int i) {
            return (i >> 18) & 16383;
        }

        public static int SB_DVB_GET_LCN(int i) {
            return ((i & 96) << 5) | ((i >> 8) & 1023);
        }
    }

    public static class FactoryCode {
        public static final int CT_ALGERIA = 6;
        public static final int CT_BGH = 20;
        public static final int CT_CZECH = 15;
        public static final int CT_EGYPT = 11;
        public static final int CT_EGYPT_TSB = 18;
        public static final int CT_EMPTY = 0;
        public static final int CT_FRAVEGA = 22;
        public static final int CT_GUI_YANG = 3;
        public static final int CT_HUANG_DAO = 1;
        public static final int CT_INDONESIA = 9;
        public static final int CT_IRAN = 8;
        public static final int CT_JIANG_MEN = 4;
        public static final int CT_MALAYSIA = 7;
        public static final int CT_NIGERIA = 16;
        public static final int CT_NewSan = 19;
        public static final int CT_PAKISTAN = 17;
        public static final int CT_POLAND = 14;
        public static final int CT_RIVIERA = 21;
        public static final int CT_RUSSIA = 10;
        public static final int CT_SHUNDE = 12;
        public static final int CT_SOUTHAFRICA = 5;
        public static final int CT_SYRIA = 13;
        public static final int CT_ZHONG_SHI = 2;
    }

    public static class TvParams {
        public static final int BW_10_MHz = 5;
        public static final int BW_1_712_MHz = 6;
        public static final int BW_5_MHz = 4;
        public static final int BW_6_MHz = 1;
        public static final int BW_7_MHz = 2;
        public static final int BW_8_MHz = 3;
        public static final int BW_UNKNOWN = 0;
        public static final int NW_MASK = ((MtkTvChCommonBase.SB_VNET_ACTIVE | MtkTvChCommonBase.SB_VNET_ALL) | MtkTvChCommonBase.SB_VNET_EPG) | MtkTvChCommonBase.SB_VNET_VISIBLE;
        public static final int SND_SYS_BG = 130;
        public static final int SND_SYS_DK = 130;
        public static final int SND_SYS_I = 130;
        public static final int SND_SYS_L = 130;
        public static final int SND_SYS_L1 = 130;
        public static final int SND_SYS_M = 256;
        public static final int TV_SYS_BG = 66;
        public static final int TV_SYS_DK = 1032;
        public static final int TV_SYS_I = 256;
        public static final int TV_SYS_L = 4096;
        public static final int TV_SYS_L1 = 8192;
        public static final int TV_SYS_M = 16384;
    }

    public HisenseTvAPIBase() {
    }

    private void cleanApps(boolean z, IFactoryCleanAppsListener iFactoryCleanAppsListener) {
        new CleanAppsThread(this.mContext, z, iFactoryCleanAppsListener).start();
    }

    public static boolean getBooleanProperty(String str, boolean z) {
        return SystemProperties.getBoolean(str, z);
    }

    public static int getIntProperty(String str, int i) {
        return SystemProperties.getInt(str, i);
    }

    public static String getStringProperty(String str, String str2) {
        return SystemProperties.get(str, str2);
    }

    public static boolean isVcomSupport() {
        return getBooleanProperty(HisenseTvApiProperty.HISENSE_PROP_INNERTCON, true);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x005a  */
    /* JADX WARN: Code duplicated, block: B:37:0x007b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:45:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0035: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:13:0x0035 */
    private String searchUsbPath() throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        File file = new File("/proc/mounts");
        ArrayList arrayList = new ArrayList();
        FileInputStream fileInputStream3 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "GBK"));
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.contains("vfat")) {
                            arrayList.add(line);
                        }
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e2) {
                    e = e2;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                    }
                    if (arrayList.isEmpty()) {
                        return null;
                    }
                    String str = (String) arrayList.get(0);
                    String strTrim = str.substring(str.indexOf("/mnt"), str.indexOf("vfat")).trim();
                    a.c("usbpath: ", strTrim, TAG);
                    return strTrim;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                    }
                    if (arrayList.isEmpty()) {
                        return null;
                    }
                    String str2 = (String) arrayList.get(0);
                    String strTrim2 = str2.substring(str2.indexOf("/mnt"), str2.indexOf("vfat")).trim();
                    a.c("usbpath: ", strTrim2, TAG);
                    return strTrim2;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream3 = fileInputStream2;
                if (fileInputStream3 != null) {
                    try {
                        fileInputStream3.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileInputStream = null;
        } catch (IOException e6) {
            e = e6;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream3 != null) {
                fileInputStream3.close();
            }
            throw th;
        }
        try {
            fileInputStream.close();
        } catch (IOException e7) {
            e7.printStackTrace();
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        String str3 = (String) arrayList.get(0);
        String strTrim3 = str3.substring(str3.indexOf("/mnt"), str3.indexOf("vfat")).trim();
        a.c("usbpath: ", strTrim3, TAG);
        return strTrim3;
    }

    private int transformTestPatternIndex(int[] iArr) {
        int i = 2;
        int[] iArr2 = new int[2];
        if (iArr[0] == 0) {
            return 255;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == 255) {
                iArr2[0] = iArr2[0] + 1;
                iArr2[1] = i2;
            }
        }
        StringBuilder sbA = a.a("describe[0] = ");
        sbA.append(iArr2[0]);
        sbA.append(" ; describe[1]");
        a.b(sbA, iArr2[1], TAG);
        if (iArr2[0] == 0) {
            i = 4;
        } else if (iArr2[0] == 3) {
            i = 3;
        } else if (iArr2[1] == 1) {
            i = 0;
        } else if (iArr2[1] == 2) {
            i = 1;
        }
        a.b("transformTestPatternIndex = ", i, TAG);
        return i;
    }

    public MtkTvAnalogChannelInfo AddAnalogChannelInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, String str) {
        Log.d(TAG, "AddAnalogChannelInfo.....\n");
        MtkTvAnalogChannelInfo mtkTvAnalogChannelInfo = new MtkTvAnalogChannelInfo(i, i2);
        mtkTvAnalogChannelInfo.setChannelId(i3);
        mtkTvAnalogChannelInfo.setBrdcstType(i4);
        mtkTvAnalogChannelInfo.setNwMask(i5);
        mtkTvAnalogChannelInfo._setFrequency(i6);
        mtkTvAnalogChannelInfo.setCentralFreq(i7);
        mtkTvAnalogChannelInfo.setTvSys(i8);
        mtkTvAnalogChannelInfo.setColorSys(i9);
        mtkTvAnalogChannelInfo.setAudioSys(i10);
        mtkTvAnalogChannelInfo.setBrdcstMedium(i11);
        mtkTvAnalogChannelInfo.setScanIdx(i12);
        mtkTvAnalogChannelInfo._setServiceName(str);
        return mtkTvAnalogChannelInfo;
    }

    public MtkTvATSCChannelInfo AddAtscChannelInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, String str) {
        Log.d(TAG, "MtkTvATSCChannelInfo.....\n");
        MtkTvATSCChannelInfo mtkTvATSCChannelInfo = new MtkTvATSCChannelInfo(i, i2);
        mtkTvATSCChannelInfo.setBrdcstType(i3);
        mtkTvATSCChannelInfo.setMajorNum(i4);
        mtkTvATSCChannelInfo.setMinorNum(i5);
        mtkTvATSCChannelInfo.setNwMask(i6);
        mtkTvATSCChannelInfo._setFrequency(i7);
        mtkTvATSCChannelInfo.setProgId(i8);
        mtkTvATSCChannelInfo.setServiceType(i9);
        mtkTvATSCChannelInfo.setMod(i10);
        mtkTvATSCChannelInfo.setBrdcstMedium(i11);
        mtkTvATSCChannelInfo.setScanIdx(i12);
        mtkTvATSCChannelInfo.setNwId(i13);
        mtkTvATSCChannelInfo.setOnId(i14);
        mtkTvATSCChannelInfo.setTsId(i15);
        mtkTvATSCChannelInfo._setServiceName(str);
        return mtkTvATSCChannelInfo;
    }

    public MtkTvDvbChannelInfo AddDvbChannelInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, String str, int i11, int i12, int i13, int i14, int i15) {
        MtkTvDvbChannelInfo mtkTvDvbChannelInfo = new MtkTvDvbChannelInfo(i, i2);
        mtkTvDvbChannelInfo._setChannelId(i3);
        mtkTvDvbChannelInfo._setFrequency(i4);
        mtkTvDvbChannelInfo.setNwId(i5);
        mtkTvDvbChannelInfo.setOnId(i6);
        mtkTvDvbChannelInfo.setTsId(i7);
        mtkTvDvbChannelInfo.setProgId(i8);
        mtkTvDvbChannelInfo.setSymRate(i9);
        mtkTvDvbChannelInfo.setMod(i10);
        mtkTvDvbChannelInfo._setServiceName(str);
        mtkTvDvbChannelInfo.setBrdcstMedium(i13);
        mtkTvDvbChannelInfo.setBrdcstType(i12);
        mtkTvDvbChannelInfo.setServiceType(i14);
        mtkTvDvbChannelInfo.setBandWidth(i11);
        mtkTvDvbChannelInfo.setNwMask(i15);
        return mtkTvDvbChannelInfo;
    }

    public MtkTvISDBChannelInfo AddIsdbChannelInfo(int i, int i2, int i3, int i4, String str, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        Log.d(TAG, "MtkTvIsdbChannelInfo.....\n");
        MtkTvISDBChannelInfo mtkTvISDBChannelInfo = new MtkTvISDBChannelInfo(i, i2);
        mtkTvISDBChannelInfo._setFrequency(i6);
        mtkTvISDBChannelInfo.setNwId(i9);
        mtkTvISDBChannelInfo.setOnId(i10);
        mtkTvISDBChannelInfo.setTsId(i11);
        mtkTvISDBChannelInfo.setProgId(i12);
        mtkTvISDBChannelInfo._setServiceName(str);
        mtkTvISDBChannelInfo.setBrdcstType(i3);
        mtkTvISDBChannelInfo.setServiceType(i4);
        mtkTvISDBChannelInfo.setMajorNum(i7);
        mtkTvISDBChannelInfo.setMinorNum(i8);
        mtkTvISDBChannelInfo.setRfIdx(i5);
        return mtkTvISDBChannelInfo;
    }

    public int ChangeModeforBuiltInVideoUpgrade() {
        Log.v(TAG, "[Storemode] ChangeModeforBuiltInVideoUpgrade.\n");
        return HisenseTVNativeWrapper.ChangeModeforBuiltInVideoUpgrade_native();
    }

    public int GetSelinuxEnvironment() {
        if (1 == HisenseTVNativeWrapper.getSelinuxEnvironment_native("selinux")) {
            Log.d(TAG, "Selinux is Permissive");
            return 1;
        }
        Log.d(TAG, "Selinux is Enforce");
        return 0;
    }

    public boolean ResetLaserMovableCoverStep() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_RESET_MOVABLE_COVER_STEP, 0) == 0;
    }

    public boolean SaveLaserMovableCoverStep() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SAVE_MOVABLE_COVER_STEP, 0) == 0;
    }

    public boolean adjust4096GammaPattenr(int[] iArr, int i) {
        return false;
    }

    public int adjustGammaPattenr(int i, int i2, int i3, int i4) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setRGBPattern(i, i2, i3, i4);
    }

    public String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public boolean cleanEepKeyFile() {
        int iMountPerm_native = HisenseTVNativeWrapper.mountPerm_native();
        if (iMountPerm_native == 0) {
            iMountPerm_native = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_CLEAN_EEP_KEY, 0);
            Log.d(TAG, "cleanEepKeyFile, return value = " + iMountPerm_native + "\n");
            HisenseTVNativeWrapper.unmountPerm_native();
        } else {
            a.b("mount perm error, return value = ", iMountPerm_native, "\n", TAG);
        }
        return iMountPerm_native == 0;
    }

    public boolean clearJbTime() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOTAL_JBTIME, 0) == 0;
    }

    public boolean clearOffsTime() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOTAL_OFFSTIME, 0) == 0;
    }

    public int dataSave() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_DATA_SAVE, 0);
    }

    public boolean doFactoryOKReset() {
        if (setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TYPE_FACTORY_OK, 1) != 0) {
            Log.i(TAG, "doFactoryOKReset error 1");
            return false;
        }
        AvMiddleWareManager avMiddleWareManager = this.mAvMiddleWareManager;
        if (AvMiddleWareManager.resetFactoryOKSetting() != 0) {
            Log.i(TAG, "doFactoryOKReset error 2");
            return false;
        }
        HisenseTVNativeWrapper.doresetPub();
        return true;
    }

    public boolean doLocalKeyUserReset() {
        HisenseTVNativeWrapper.setEthernetWolCtl_native(false);
        HisenseTVNativeWrapper.setWifiWolCtl_native(false);
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LOCAL_KEY_USER_RESET, 1) == 0;
    }

    public boolean doMasterClear() {
        HisenseTVNativeWrapper.setEthernetWolCtl_native(false);
        HisenseTVNativeWrapper.setWifiWolCtl_native(false);
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "user_setup_complete", 0, -3);
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "tv_user_setup_complete", 0, -3);
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.setFlags(MessageSchema.REQUIRED_MASK);
        if (this.mContext == null) {
            Log.d(TAG, "doMasterClear  mContext is null");
            return false;
        }
        Log.d(TAG, "doMasterClear  mContext is not null");
        this.mContext.sendBroadcast(intent);
        return true;
    }

    public boolean doProtectedClear() {
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TYPE_PROTECTED_CLEAN, 1);
        Log.i(TAG, "doProtectedClear  relValue = " + configValue);
        if (configValue == 0) {
            AvMiddleWareManager avMiddleWareManager = this.mAvMiddleWareManager;
            if (AvMiddleWareManager.resetFactoryUnprotectedSetting() == 0) {
            }
        }
        return false;
    }

    public boolean doUnprotectedClear() {
        HisenseTVNativeWrapper.doUnprotectedClear();
        return true;
    }

    public boolean doUserReset() {
        HisenseTVNativeWrapper.setEthernetWolCtl_native(false);
        HisenseTVNativeWrapper.setWifiWolCtl_native(false);
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TYPE_USER_RESET, 1) == 0;
    }

    public void endLanB2BWorkThread() {
        Log.v(TAG, "[B2B]endLan B2B Thread.\n");
        HisenseTVNativeWrapper.endLanB2BWorkThread_native();
    }

    public int factoryWriteKeyFinish() {
        return HisenseTVNativeWrapper.factoryWriteKeyFinish_native();
    }

    public boolean generateEepKeyFile() {
        int iMountPerm_native = HisenseTVNativeWrapper.mountPerm_native();
        if (iMountPerm_native == 0) {
            iMountPerm_native = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_EEP_KEY, 0);
            Log.d(TAG, "generateEepKeyFile, return value = " + iMountPerm_native + "\n");
            HisenseTVNativeWrapper.unmountPerm_native();
        } else {
            a.b("mount perm error, return value = ", iMountPerm_native, "\n", TAG);
        }
        return iMountPerm_native == 0;
    }

    public int get2PWBGainBlueDelta() {
        Log.d(TAG, "get2PWBGainBlueDelta");
        return HisVideoApi.get2PWBGainBlueDelta();
    }

    public int get2PWBGainGreenDelta() {
        Log.d(TAG, "get2PWBGainGreenDelta");
        return HisVideoApi.get2PWBGainGreenDelta();
    }

    public int get2PWBGainRedDelta() {
        Log.d(TAG, "get2PWBGainRedDelta");
        return HisVideoApi.get2PWBGainRedDelta();
    }

    public int get2PWBOffsetBlueDelta() {
        Log.d(TAG, "get2PWBOffsetBlueDelta");
        return HisVideoApi.get2PWBOffsetBlueDelta();
    }

    public int get2PWBOffsetGreenDelta() {
        Log.d(TAG, "get2PWBOffsetGreenDelta");
        return HisVideoApi.get2PWBOffsetGreenDelta();
    }

    public int get2PWBOffsetRedDelta() {
        Log.d(TAG, "get2PWBOffsetRedDelta");
        return HisVideoApi.get2PWBOffsetRedDelta();
    }

    public int[] get5GChannel() {
        return getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_5G_CHANNEL_INFO, 100);
    }

    public int getAVCEnableValue(String str) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getAVCFeature(0);
    }

    public int getAVCThreshold() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getAVCFeature(1);
    }

    public int getAbortPowerTimer() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_TIMER_ABORT_TIMER_OFF);
    }

    public int getActiveContrast() {
        return HisVideoApi.getActiveContrast();
    }

    public int[] getAllAudioCurve(String str, int i) {
        int[] iArr = new int[10];
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MIN)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getSoundVolCurveVal(0);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_20)) {
            HisAudioApi hisAudioApi2 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getSoundVolCurveVal(1);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MID)) {
            HisAudioApi hisAudioApi3 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getSoundVolCurveVal(2);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_75)) {
            HisAudioApi hisAudioApi4 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getSoundVolCurveVal(3);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MAX)) {
            HisAudioApi hisAudioApi5 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getSoundVolCurveVal(4);
        }
        return iArr;
    }

    public int[] getAllAudioModeValue(String str, int i) {
        int[] iArr = new int[10];
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOUND_MODE)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getSoundMode();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_1)) {
            HisAudioApi hisAudioApi2 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getGEQ(0);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_2)) {
            HisAudioApi hisAudioApi3 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getGEQ(1);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_3)) {
            HisAudioApi hisAudioApi4 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getGEQ(2);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_4)) {
            HisAudioApi hisAudioApi5 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getGEQ(3);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_5)) {
            HisAudioApi hisAudioApi6 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getGEQ(4);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_6)) {
            HisAudioApi hisAudioApi7 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getGEQ(5);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_7)) {
            HisAudioApi hisAudioApi8 = this.mHisAudioApi;
            iArr[0] = HisAudioApi.getGEQ(6);
        }
        return iArr;
    }

    public int[] getAllBacklighModeValue(String str, int i) {
        int[] iArr = new int[10];
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MIN)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBacklightMin();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_20)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBacklight20();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MID)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBacklightMid();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_80)) {
            HisVideoApi hisVideoApi4 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBacklight80();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MAX)) {
            HisVideoApi hisVideoApi5 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBacklightMax();
        }
        return iArr;
    }

    public int getAspectRatio() {
        return HisVideoApi.getAspectRatio();
    }

    public boolean getAudHpfStatus() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getSoundSocHPFFeature(0) == 1;
    }

    public int getAudPeqEnableStatus() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getSoundSocPEQEnable();
    }

    public int getAudioOutputPort(String str) {
        if (!str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_AMP_OUTPUT_PORT)) {
            return 0;
        }
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getAmpPrescaler();
    }

    public int getAudioPeq(String str) {
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_FREQ)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQFreq(0);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_FREQ)) {
            HisAudioApi hisAudioApi2 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQFreq(1);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_FREQ)) {
            HisAudioApi hisAudioApi3 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQFreq(2);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_FREQ)) {
            HisAudioApi hisAudioApi4 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQFreq(3);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_FREQ)) {
            HisAudioApi hisAudioApi5 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQFreq(4);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_FREQ)) {
            HisAudioApi hisAudioApi6 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQFreq(5);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_FREQ)) {
            HisAudioApi hisAudioApi7 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQFreq(6);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_FREQ)) {
            HisAudioApi hisAudioApi8 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQFreq(7);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_GAIN)) {
            HisAudioApi hisAudioApi9 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQGain(0);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_GAIN)) {
            HisAudioApi hisAudioApi10 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQGain(1);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_GAIN)) {
            HisAudioApi hisAudioApi11 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQGain(2);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_GAIN)) {
            HisAudioApi hisAudioApi12 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQGain(3);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_GAIN)) {
            HisAudioApi hisAudioApi13 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQGain(4);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_GAIN)) {
            HisAudioApi hisAudioApi14 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQGain(5);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_GAIN)) {
            HisAudioApi hisAudioApi15 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQGain(6);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_GAIN)) {
            HisAudioApi hisAudioApi16 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQGain(7);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_Q)) {
            HisAudioApi hisAudioApi17 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQQ(0);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_Q)) {
            HisAudioApi hisAudioApi18 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQQ(1);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_Q)) {
            HisAudioApi hisAudioApi19 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQQ(2);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_Q)) {
            HisAudioApi hisAudioApi20 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQQ(3);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_Q)) {
            HisAudioApi hisAudioApi21 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQQ(4);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_Q)) {
            HisAudioApi hisAudioApi22 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQQ(5);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_Q)) {
            HisAudioApi hisAudioApi23 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQQ(6);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_Q)) {
            HisAudioApi hisAudioApi24 = this.mHisAudioApi;
            return HisAudioApi.getSoundSocPEQQ(7);
        }
        if (!str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_HPF_FREQUENCY)) {
            return 0;
        }
        HisAudioApi hisAudioApi25 = this.mHisAudioApi;
        return HisAudioApi.getSoundSocHPFFeature(1);
    }

    public int[] getAutoGammaColorTemperatureList() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        ArrayList<Integer> autoGammaList = HisVideoApi.getAutoGammaList();
        if (autoGammaList == null || autoGammaList.stream() == null) {
            return null;
        }
        return autoGammaList.stream().mapToInt(new ToIntFunction() { // from class: b.f.a.a.a
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray();
    }

    public String getAutoGammaList() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getAutoGammaListStr();
    }

    public int getAutoGammaResult() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_AUTO_GAMMA_CHECK);
    }

    public String getAutoGammaStatus() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getAutoGammaStatus();
    }

    public int getB2BSwitch() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_B2B_SWITCH);
    }

    public int getBackgroundNoiseCalibrationFactor() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_BACKGROUND_NOISE_CALIBRATION_FACTOR);
        a.c("[laser]getBackgroundNoiseCalibrationFactor, value is ", configValue, "\n", TAG);
        return configValue;
    }

    public int getBackgroundNoiseThresholdValue() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_BACKGROUND_NOISE_THRESHOLD_VALUE);
        a.c("[laser]getBackgroundNoiseThresholdValue, value is ", configValue, "\n", TAG);
        return configValue;
    }

    public int getBackgroundNoiseValue() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_BACKGROUND_NOISE_VALUE);
        a.c("[laser]getBackgroundNoiseValue, value is ", configValue, "\n", TAG);
        return configValue;
    }

    public int[] getBacklight() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[5] = HisVideoApi.getBacklightPercentByFactory();
        return iArr;
    }

    public int[] getBacklight20() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getBacklight20();
        return iArr;
    }

    public int[] getBacklight80() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[3] = HisVideoApi.getBacklight80();
        return iArr;
    }

    public int[] getBacklightMax() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[4] = HisVideoApi.getBacklightMax();
        return iArr;
    }

    public int[] getBacklightMid() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getBacklightMid();
        return iArr;
    }

    public int[] getBacklightMin() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getBacklightMin();
        return iArr;
    }

    public int getBalance() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getBalance();
    }

    public int getBluNums() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_BLU_NUMS);
    }

    public String getBluStatus() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BLU_STATUS);
        Log.i(TAG, "getBluStatus: " + configString);
        return configString;
    }

    public String getBluVersion() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BLU_VERSION);
        a.a("getBluVersion, bluVersion = ", configString, "\n", TAG);
        return configString;
    }

    public int getBlueValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_BLUE_VALUE);
    }

    public int getBootUpLogo(String str) {
        return getConfigValue(str);
    }

    public int[] getBrightness() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getBrightnessByFactory();
        return iArr;
    }

    public int[] getBrightness20() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getBrightness20();
        return iArr;
    }

    public int[] getBrightness80() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[3] = HisVideoApi.getBrightness80();
        return iArr;
    }

    public int[] getBrightnessMax() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[4] = HisVideoApi.getBrightnessMax();
        return iArr;
    }

    public int[] getBrightnessMid() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getBrightnessMid();
        return iArr;
    }

    public int[] getBrightnessMin() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getBrightnessMin();
        return iArr;
    }

    public int getByPassValue(String str) {
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PQ_BYPASS)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            return HisVideoApi.getBypassPqEnable();
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAMMA_BYPASS)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            return HisVideoApi.getBypassGammaEnable();
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SCE_BYPASS)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            return HisVideoApi.getBypassSceEnable();
        }
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_XVYCC_BYPASS)) {
            return 0;
        }
        HisVideoApi hisVideoApi4 = this.mHisVideoApi;
        return HisVideoApi.getBypassXvyccEnable();
    }

    public int getCTBrightness() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getFactoryColorTunerBrightness();
    }

    public int getCTHue() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getFactoryColorTunerHue();
    }

    public int getCTSaturation() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getFactoryColorTunerSaturation();
    }

    public String getChipId() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_SYSTEM_CHIP_ID);
        a.a("getChipId, chipid = ", configString, "\n", TAG);
        return configString;
    }

    public String getChipIdInfo(String str) {
        return getConfigString(str);
    }

    public String getCmdExecResult(String str) {
        return HisenseTVNativeWrapper.getCmdExecResult_native(str);
    }

    public int[] getColor() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[3] = HisVideoApi.getSaturationByFactory();
        return iArr;
    }

    public int getColorTemperature() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getFactoryColorTemperature();
    }

    public int getColorTuner() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getFactoryColorTunerColor();
    }

    public int[] getConfigIntArrayValue(String str, int i) {
        return HisenseTVNativeWrapper.getConfigIntArrayValue_native(-1, i, str);
    }

    public String getConfigString(String str) {
        String configString_native;
        synchronized (HisenseTvAPIBase.class) {
            configString_native = HisenseTVNativeWrapper.getConfigString_native(str);
        }
        return configString_native;
    }

    public int getConfigValue(String str) {
        return getConfigValue(-1, str);
    }

    public int[] getContrast() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getContrastByFactory();
        return iArr;
    }

    public int[] getContrast20() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getContrast20();
        return iArr;
    }

    public int[] getContrast80() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[3] = HisVideoApi.getContrast80();
        return iArr;
    }

    public int[] getContrastMax() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[4] = HisVideoApi.getContrastMax();
        return iArr;
    }

    public int[] getContrastMid() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getContrastMid();
        return iArr;
    }

    public int[] getContrastMin() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getContrastMin();
        return iArr;
    }

    public String getCurrentSourceName() {
        return MtkTvInputSource.getInstance().getCurrentInputSourceName();
    }

    public List<MtkTvInputSourceBase.InputSourceRecord> getCurrentSupportSource() {
        int inputSourceTotalNumber = MtkTvInputSource.getInstance().getInputSourceTotalNumber();
        Log.i(TAG, "getCurrentSupportSource record num = " + inputSourceTotalNumber);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < inputSourceTotalNumber; i++) {
            MtkTvInputSourceBase.InputSourceRecord inputSourceRecord = new MtkTvInputSourceBase.InputSourceRecord();
            MtkTvInputSource.getInstance().getInputSourceRecbyidx(i, inputSourceRecord);
            Log.i(TAG, "getCurrentSupportSource record name = " + inputSourceRecord.getInputType().name());
            Log.i(TAG, "getCurrentSupportSource record name = " + inputSourceRecord.getInternalIdx());
            Log.i(TAG, "getCurrentSupportSource record name = " + inputSourceRecord.getId());
            arrayList.add(inputSourceRecord);
        }
        return arrayList;
    }

    public String getCustomerTypeValue() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_CUSTOMER_TYPE);
    }

    public int getDaylightValue(String str) {
        return getConfigValue(str);
    }

    public int getDdrDetailValue(String str) {
        return 0;
    }

    public boolean getDdrMode() {
        return true;
    }

    public int getDdrSpan() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_STEP);
    }

    public int getDdrSscFreq() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_SPAN);
    }

    public int getDedustMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_DEDUST_MODE);
        a.c("[laser]getDedustMode = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getDedustNotice() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_DEDUST_NOTICE);
        a.c("[laser]getDedustNotice = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getDefaultAudioVolume() {
        return 15;
    }

    public int getDemuraFlagValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA);
    }

    public int getDemuraSpiStatus() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_SPI_STATUS);
    }

    public int getDemuraSwitchStatus() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_SWITCH_STATUS);
    }

    public int getDistenceSensorStrength() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_DISTENCE_SENSOR_STRENGTH, 1);
        if (configIntArrayValue == null || configIntArrayValue.length != 1) {
            Log.v(TAG, "[Laser] getDistenceSensorStrength return data error!!");
            return -1;
        }
        int i = configIntArrayValue[0] & 65535;
        a.c("[laser]getDistenceSensorStrength, value is ", i, "\n", TAG);
        return i;
    }

    public int getDolbyIQEnable() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getDolbyIQEnable();
    }

    public int getDolbyIQUserBinEnable() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getDolbyIQUserBinEnable();
    }

    public String getDolbyIQUserBinInfo() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getDolbyIQUserBinInfo();
    }

    public int getDrcValue(String str) {
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_DRC_L)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            return HisAudioApi.getAmpDrcL();
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_DRC_H)) {
            HisAudioApi hisAudioApi2 = this.mHisAudioApi;
            return HisAudioApi.getAmpDrcH();
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_DRC_M)) {
            HisAudioApi hisAudioApi3 = this.mHisAudioApi;
            return HisAudioApi.getAmpDrcS();
        }
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_DRC_P)) {
            return 0;
        }
        HisAudioApi hisAudioApi4 = this.mHisAudioApi;
        return HisAudioApi.getAmpDrcP();
    }

    public int getEARCStatus() {
        return HisHdmiApi.getEarcStatus();
    }

    public String getEmmcCid() {
        return HisensePlatformConfig.getEmmcCid();
    }

    public String getEmmcFwrev() {
        return HisensePlatformConfig.getEmmcFwrev();
    }

    public String getEmmcHwrev() {
        return HisensePlatformConfig.getEmmcHwrev();
    }

    public int[] getEmmcLifeTime() {
        return HisensePlatformConfig.getEmmcLifeTime();
    }

    public ArrayList<Integer> getEmmcSmartReport() {
        return HisensePlatformConfig.getEmmcSmartReport();
    }

    public int getEmmcType() {
        return HisensePlatformConfig.getEmmcType();
    }

    public long getEmmcWrittenSize() {
        return HisensePlatformConfig.getEmmcWrittenSize();
    }

    public long getEmmcWrittenSizeByDriver() {
        return HisensePlatformConfig.getEmmcWrittenSizeByDriver();
    }

    public int getErrDetCount() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_ERR_DETCOUNT);
    }

    public String getExtenedVersion(String str) {
        return SystemProperties.get("ro.hisense.data.change.date.utc");
    }

    public int getFHDVcomValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE_2);
    }

    public String getFacMacAddress() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_FAC_MAC_ADDRESS);
        a.a("getFacMacAddress, macAddr = ", configString, "\n", TAG);
        if (configString == null || configString.isEmpty() || "00:0C:E7:06:00:00".equalsIgnoreCase(configString)) {
            return null;
        }
        return configString;
    }

    public int getFactoryCheckStatus() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_CHECK_SATUS);
    }

    public String getFactoryFunctionNumber(String str) {
        return getConfigString(str);
    }

    public int getFactoryMICTestDBValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TEST_MIC_DB);
    }

    public int getFactoryOperatorStatus() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OPERATOR_SATUS);
    }

    public int getFactoryTconStatus() {
        return 1;
    }

    public int[] getFactoryWhiteBalanceGainBlue() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getFactoryWhiteBalanceGainBlue();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceGainGreen() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getFactoryWhiteBalanceGainGreen();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceGainRed() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getFactoryWhiteBalanceGainRed();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceOffsetBlue() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getFactoryWhiteBalanceOffsetBlue();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceOffsetGreen() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getFactoryWhiteBalanceOffsetGreen();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceOffsetRed() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getFactoryWhiteBalanceOffsetRed();
        return iArr;
    }

    public byte[] getFanSpeed() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_FAN_SPEED, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.v(TAG, "[Laser] getFanSpeed return data error!!");
            return null;
        }
        byte[] bArr = {(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255)};
        StringBuilder sbA = a.a("[laser] getFanSpeed:status[0] = ");
        sbA.append(configIntArrayValue[0]);
        sbA.append(", status[1] = ");
        sbA.append(configIntArrayValue[1]);
        sbA.append("\n");
        Log.v(TAG, sbA.toString());
        Log.v(TAG, "[laser] getFanSpeed:bytes[0] = " + ((int) bArr[0]) + ", bytes[1] = " + ((int) bArr[1]) + ", bytes[2] = " + ((int) bArr[2]) + ", bytes[3] = " + ((int) bArr[3]) + ", bytes[4] = " + ((int) bArr[4]) + ", bytes[5] = " + ((int) bArr[5]) + "\n");
        return bArr;
    }

    public int getFirstBootState() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FIRSTBOOT_STATE);
    }

    public int getFirstExperienValue(String str) {
        return 0;
    }

    public long getFirstPowerOnTime() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_FIRST_POWER_ON_TIME, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.e(TAG, "[laser]getFirstPowerOnTime, error \n");
            return -1L;
        }
        long j = ((long) configIntArrayValue[0]) + (((long) configIntArrayValue[1]) << 32);
        StringBuilder sbA = a.a("dataHex:");
        sbA.append(Long.toHexString(j));
        sbA.append(";highHex:");
        sbA.append(Integer.toHexString(configIntArrayValue[1]));
        sbA.append(";lowHex:");
        sbA.append(Integer.toHexString(configIntArrayValue[0]));
        Log.e(TAG, sbA.toString());
        return j;
    }

    public int getFirstTimePowerOn(String str) {
        return getConfigValue(str);
    }

    public int getForeignAmbientLight() {
        Log.v(TAG, "[laser]getForeignAmbientLight");
        return getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_GET_FOREIGN_AMBIENT_LIGHT);
    }

    public int getFrcIdx() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FRC_IDX);
    }

    public int getFrcOtaFlag() {
        Log.d(TAG, "getFrcOtaFlag...");
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FRC_OTA_FLAG);
        a.b("getFrcOtaFlag, ret is", configValue, TAG);
        return configValue;
    }

    public String getFrcVersion() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_FRC_VERSION);
        a.a("isNeedToShowFrc, frc ver = ", configString, "\n", TAG);
        return configString;
    }

    public String getFunctionNumber(String str) {
        return getConfigString(str);
    }

    public byte[] getGCFpgaVersion() {
        byte[] bArr = new byte[4];
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_GC_FPGA_VERSION);
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) ((configValue >> i) & 255);
            i += 8;
        }
        StringBuilder sbA = a.a("[laser]getGCFpgaVersion = ", configValue, "bytes[0] = ");
        sbA.append((int) bArr[0]);
        sbA.append("bytes[1] = ");
        sbA.append((int) bArr[1]);
        sbA.append("bytes[2] = ");
        sbA.append((int) bArr[2]);
        sbA.append("bytes[3] = ");
        sbA.append((int) bArr[3]);
        sbA.append("\n");
        Log.v(TAG, sbA.toString());
        return bArr;
    }

    public byte[] getGCMcuVersion() {
        byte[] bArr = new byte[4];
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_GC_MCU_VERSION);
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) ((configValue >> i) & 255);
            i += 8;
        }
        StringBuilder sbA = a.a("[laser]getGCMcuVersion = ", configValue, "bytes[0] = ");
        sbA.append((int) bArr[0]);
        sbA.append("bytes[1] = ");
        sbA.append((int) bArr[1]);
        sbA.append("bytes[2] = ");
        sbA.append((int) bArr[2]);
        sbA.append("bytes[3] = ");
        sbA.append((int) bArr[3]);
        sbA.append("\n");
        Log.v(TAG, sbA.toString());
        return bArr;
    }

    public int[] getGCResult() {
        int i;
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_GET_GC_CORRECT_RESULT, 7);
        if (configIntArrayValue == null || configIntArrayValue.length < 7) {
            Log.v(TAG, "[Laser] getGCResult return data error!!");
            return null;
        }
        int[] iArr = new int[15];
        int i2 = 0;
        for (int i3 = 0; i3 < 7; i3++) {
            if (i3 < 4) {
                int i4 = i2 + 1;
                iArr[i2] = configIntArrayValue[i3] & 65535;
                i2 = i4 + 1;
                iArr[i4] = (configIntArrayValue[i3] >> 16) & 65535;
            } else if (i3 >= 4 && i3 < 6) {
                if (((configIntArrayValue[i3] >> 15) & 1) == 1) {
                    i = i2 + 1;
                    iArr[i2] = 0 - (configIntArrayValue[i3] & 32767);
                } else {
                    i = i2 + 1;
                    iArr[i2] = configIntArrayValue[i3] & 65535;
                }
                if (((configIntArrayValue[i3] >> 31) & 1) == 1) {
                    i2 = i + 1;
                    iArr[i] = 0 - ((configIntArrayValue[i3] >> 16) & 32767);
                } else {
                    i2 = i + 1;
                    iArr[i] = (configIntArrayValue[i3] >> 16) & 65535;
                }
            } else if (i3 == 6) {
                int i5 = i2 + 1;
                iArr[i2] = configIntArrayValue[i3] & 255;
                int i6 = i5 + 1;
                iArr[i5] = (configIntArrayValue[i3] >> 8) & 255;
                iArr[i6] = (configIntArrayValue[i3] >> 16) & 255;
                i2 = i6 + 1;
            }
        }
        Log.v(TAG, "[laser] getGCResult:length: = " + i2 + "\n");
        Log.v(TAG, "[laser] getGCResult:gcresult[0] = " + iArr[0] + ", gcresult[1] = " + iArr[1] + ", gcresult[2] = " + iArr[2] + ", gcresult[13] = " + iArr[13] + ", gcresult[14] = " + iArr[14] + "\n");
        return iArr;
    }

    public int getGammaOperation(String str) {
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_FACTORY_GAMMA_SELECT)) {
            return 0;
        }
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getAutoGammaEnable();
    }

    public int getGlobalLuminanceGain() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_GLOBAL_LUM_GAIN);
    }

    public int getGreenValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_GREEN_VALUE);
    }

    public int getHotProtectFlag() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_HOT_PROTECT_FLAG);
        a.c("[laser]getHotProtectFlag = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getHotelModeValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_HOTEL_MODE);
    }

    public int getHpfFrequency(String str) {
        return getConfigValue(str);
    }

    public int[] getHue20() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getHue20();
        return iArr;
    }

    public int[] getHue80() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[3] = HisVideoApi.getHue80();
        return iArr;
    }

    public int[] getHueMax() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[4] = HisVideoApi.getHueMax();
        return iArr;
    }

    public int[] getHueMid() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getHueMid();
        return iArr;
    }

    public int[] getHueMin() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getHueMin();
        return iArr;
    }

    public int getIranCalendarValue(String str) {
        return getConfigValue(str);
    }

    public int getIsSubwooferSupport() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getIsSubwooferSupport();
    }

    public int getJBExecuteCount() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_JB_EXE_COUNT, 1);
        if (configIntArrayValue == null || configIntArrayValue.length < 1) {
            return -1;
        }
        int i = configIntArrayValue[0] & 65535;
        a.a("[laser]getJBExecuteCount, value is ", i, "\n", TAG);
        return i;
    }

    public byte[] getJBExecuteTime() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_JB_EXE_TIME, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            return null;
        }
        return new byte[]{(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255)};
    }

    public int getLVDSEnable() {
        return 1 == getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_ONOFF) ? 0 : 1;
    }

    public int getLaserAgingMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_AGING_MODE);
        a.c("[laser]getLaserAgingMode mode = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserAlertLedOnOff() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_ALERT_LED_ON_OFF);
        a.c("[Laser] getLaserAlertLedOnOff = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserCW1() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CW1);
        a.c("[laser]getLaserCW1 = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserCW2() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CW2);
        a.c("[laser]getLaserCW2 = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserCloMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLO_MODE);
        a.c("[laser]getLaserCloMode, mode = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserCloOverlapSelect() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLO_OVERLAP_SELECT);
        a.c("[laser]getLaserCloOverlapSelect, mode = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserDlpVersion() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_DLP_VERSION);
        a.c("[Laser] getLaserDlpVersion = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserEyeProtectSwitch() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_EYE_PROTECT_SWITCH);
        a.c("[Laser] getLaserEyeProtectSwitch = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserForeignBodyDetectSwitch() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_FOREIGN_BODY_DETECT_SWITCH);
        a.c("[Laser] getLaserForeignBodyDetectSwitch = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserFpgaVersion() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_FPGA_VERSION);
        a.c("[Laser] getLaserFpgaVersion = ", configValue, "\n", TAG);
        return configValue;
    }

    public String getLaserLedVersion() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_LED_VERSION, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.v(TAG, "[Laser] getLaserLedVersion return data error!!");
            return null;
        }
        byte[] bArr = {(byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255)};
        StringBuilder sbA = a.a("[laser] getFanSpeed:status[0] = ");
        sbA.append(configIntArrayValue[0]);
        sbA.append("status[1] = ");
        sbA.append(configIntArrayValue[1]);
        sbA.append("\n");
        Log.v(TAG, sbA.toString());
        Log.v(TAG, "[laser] getFanSpeed:bytes[0] = " + ((int) bArr[0]) + "bytes[1] = " + ((int) bArr[1]) + "bytes[2] = " + ((int) bArr[2]) + "bytes[3] = " + ((int) bArr[3]) + "\n");
        return bytesToHexString(bArr);
    }

    public int getLaserMcuVersion() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MCU_VERSION);
        a.c("[Laser] getLaserMcuVersion = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserMirrorGainValue() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MIRROR_GAIN_VALUE);
        a.c("[Laser] getLaserMirrorGainValue = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserModeSelect() {
        Log.v(TAG, "[laser]getLaserModeSelect");
        return getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SET_LASER_MODE);
    }

    public int getLaserMovableCoverStep() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MOVABLE_COVER_STEP);
    }

    public int getLaserOverlapMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_OVERLAP_MODE);
        a.c("[laser]getLaserOverlapMode, mode = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserProjectionMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_PROJECTION_MODE);
        a.c("[laser]getLaserProjectionMode = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserRuningTime() {
        byte[] runingTime = getRuningTime();
        if (runingTime == null || runingTime.length < 2) {
            return 0;
        }
        int[] iArr = {runingTime[0] & 255, runingTime[1] & 255};
        int i = (iArr[0] << 8) | iArr[1];
        a.c("[Laser] getLaserRuningTime = ", i, "\n", TAG);
        return i;
    }

    public int getLaserScreenBrightFlag() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SCREEN_BRIGHT_FLAG);
        a.c("[laser]getLaserScreenBrightFlag = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserScreenType() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SCREENTYPE);
        a.c("[Laser] getLaserScreenType = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserSoftwareUpgradeMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SOFTWARE_UPGRADE_MODE);
        a.c("[Laser] getLaserSoftwareUpgradeMode = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLaserSoundMcuVersion() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SOUND_MCU_VERSION);
        a.c("[Laser] getLaserSoundMcuVersion = ", configValue, "\n", TAG);
        return configValue;
    }

    public byte[] getLaserTemperature() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_TEMPERATURE, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.v(TAG, "[Laser] getLaserTemperature return data error!!");
            return null;
        }
        byte[] bArr = {(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255)};
        StringBuilder sbA = a.a("[laser] temperature: temperature[0] = ");
        sbA.append(configIntArrayValue[0]);
        sbA.append(", temperature[1] = ");
        sbA.append(configIntArrayValue[1]);
        sbA.append("\n");
        Log.v(TAG, sbA.toString());
        Log.v(TAG, "[laser] getLaserTemperature: bytes[0] = " + ((int) bArr[0]) + ", bytes[1] = " + ((int) bArr[1]) + ", bytes[2] = " + ((int) bArr[2]) + ", bytes[3] = " + ((int) bArr[3]) + ", bytes[4] = " + ((int) bArr[4]) + ", bytes[5] = " + ((int) bArr[5]) + ", bytes[6] = " + ((int) bArr[6]) + ", bytes[7] = " + ((int) bArr[7]) + "\n");
        return bArr;
    }

    public int getLaserTemperatureProtectSwitch() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_TEMPERATURE_PROTECT_SWITCH);
        a.c("[Laser] getLaserTemperatureProtectSwitch = ", configValue, "\n", TAG);
        return configValue;
    }

    public int getLightSensorCurve(int i, int i2) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getLightSensorCurve(i, i2);
    }

    public int getLocalDimmingState() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getLocalDimming();
    }

    public int getLocaldimmingEnable() {
        return 1;
    }

    public int getLogoHotelFromTv2Udisk(String str) {
        return HisensePlatformConfig.getLogoHotelFromTv2Udisk(str);
    }

    public int getLuxValue() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getDolbyIQLux();
    }

    public int getLvdsDetailValue(String str) {
        return 0;
    }

    public boolean getLvdsMode() {
        return true;
    }

    public String getMIC1Db() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_ONEDB_MIC);
    }

    public String getMIC2Db() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_TWODB_MIC);
    }

    public int getMICTestValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_MIC_TEST);
    }

    public String getMacAddress(String str) {
        return getConfigString(str);
    }

    public String getMachineName(String str) {
        return SystemProperties.get("ro.vt.product.model");
    }

    public String getMachineNumber(String str) {
        return getConfigString(str);
    }

    public int[] getManualGraphCorrectionResult(int i, int i2) {
        if (setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_GEOMETRIC_CORRECTION_INFO, ((i & 255) << 0) | ((i2 & 255) << 8)) != 0) {
            Log.v(TAG, "[Laser] getManualGraphCorrectionResult return data error!!");
            return null;
        }
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MANUAL_GRAPH_CORRECTION, 1);
        if (configIntArrayValue != null && configIntArrayValue.length == 1) {
            return new int[]{(configIntArrayValue[0] >> 0) & 255, (configIntArrayValue[0] >> 8) & 255, (configIntArrayValue[0] >> 16) & 255, (configIntArrayValue[0] >> 24) & 255};
        }
        Log.v(TAG, "[Laser] getManualGraphCorrectionResult return data error!!");
        return null;
    }

    public String getMarketRegion() {
        return SystemProperties.get("ro.vendor.mtk.system.marketregion", "us");
    }

    public int getMicDbDiffSelect() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_MIC_DB_DIFF_SELECT);
    }

    public int getMicGpioStatus() {
        Log.d(TAG, "getMicGpioStatus");
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_MIC_GPIO_STATUS);
    }

    public int getMicSwitchStatus() {
        String str;
        if (this.mMicLedController == null || (str = SystemProperties.get("ro.product.board")) == null || str.equalsIgnoreCase("")) {
            return 0;
        }
        return str.equalsIgnoreCase("MT9900") ? this.mMicLedController.GetMicMuteStatus(HisenseCountryLanInfo.FAC_COUNTRY_LAO_INDEX) : this.mMicLedController.GetMicMuteStatus(HisenseCountryLanInfo.FAC_COUNTRY_LAO_INDEX);
    }

    public int getMultiBalance() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getMultiBalance();
    }

    public int getMultiDigitalAMPValueByDevId(int i, int i2) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getMultiDigitalAMPValueByDevId(i, i2);
    }

    public int getOffsExecuteCount() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_OFFRS_EXE_COUNT, 1);
        if (configIntArrayValue == null || configIntArrayValue.length < 1) {
            return -1;
        }
        int i = configIntArrayValue[0] & 65535;
        a.a("[laser]getOffsExecuteCount, value is ", i, "\n", TAG);
        return i;
    }

    public byte[] getOffsExecuteTime() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_OFFRS_EXE_TIME, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            return null;
        }
        return new byte[]{(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255)};
    }

    public int getOledCpcValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_CPC_ONOFF);
    }

    public int getOledLbc2Value() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_LBC2_ONOFF);
    }

    public int getOledMaintenanceStatus() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_OLED_MAINTENANCE_STATUS);
    }

    public int getOledPixelShiftShapeValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_SHAPE);
    }

    public int getOledPixelShiftTimerValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_TIMER);
    }

    public int getOledPixelShiftTypeValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_TYPE);
    }

    public int getOledPowerOffDoneStatus() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_POWEROFF_DONE_STATUS);
    }

    public String getOledTconVersion() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_OLED_TCON_VERSION);
        a.a("getOledTconVersion, version = ", configString, "\n", TAG);
        return configString;
    }

    public int getOledTpcValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_TPC_ONOFF);
    }

    public String getOledtcontempLutVersion() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_OLED_TCON_TEMP_LUTVERSION);
    }

    public byte[] getOledtcontempLutVersionByte() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_TCON_TEMP_LUTVERSION, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            return null;
        }
        return new byte[]{(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255)};
    }

    public int getOledtempLutVersion() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_TEMP_LUTVERSION);
    }

    public int[] getOverScanBottom() {
        int[] iArr = new int[4];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getOverscanBottom();
        return iArr;
    }

    public int[] getOverScanLeft() {
        int[] iArr = new int[4];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getOverscanLeft();
        return iArr;
    }

    public int[] getOverScanRight() {
        int[] iArr = new int[4];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[3] = HisVideoApi.getOverscanRight();
        return iArr;
    }

    public int[] getOverScanTop() {
        int[] iArr = new int[4];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getOverscanTop();
        return iArr;
    }

    public int[] getOverscanValue(String str, int i) {
        return getConfigIntArrayValue(str, i);
    }

    public int getPagainValue(String str) {
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_PAGAIN)) {
            return 0;
        }
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getAmpGain();
    }

    public int[] getPictureMode() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getPictureModeByFactory();
        return iArr;
    }

    public int[] getPictureModeValue(String str, int i) {
        int[] iArr = new int[10];
        if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_BRIGHTNESS)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBrightnessByFactory();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_CONTRAST)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getContrastByFactory();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_SAT)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSaturationByFactory();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_SHP)) {
            HisVideoApi hisVideoApi4 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSharpnessByFactory();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_DISP_DISP_DISP_BACK_LIGHT)) {
            HisVideoApi hisVideoApi5 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBacklightPercentByFactory();
        }
        return iArr;
    }

    public int getPowerControlMode(String str) {
        return getConfigValue(str);
    }

    public int getPowerIndicatorEffect(String str) {
        return 0;
    }

    public int getPowerIndicatorType(String str) {
        return 0;
    }

    public int getPowerLedValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_POWER_LED);
    }

    public int getPowerOnMode() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_POWER_CTRL);
    }

    public int getPrescaleValue(String str) {
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOC_PRESCALE)) {
            return 0;
        }
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getSoundSocPrescale();
    }

    public int getProjectId() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_PROJECT_ID);
    }

    public int getPwmFrequency(String str) {
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PWM_FREQ)) {
            return 0;
        }
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getBacklightPWMFreq();
    }

    public int getRedValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_RED_VALUE);
    }

    public int getRegionValue(String str) {
        return getConfigValue(str);
    }

    public int getResetKeypadStatus() {
        Log.d(TAG, "getResetKeypadStatus");
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_RESET_KEYPAD_STATUS);
    }

    public byte[] getRuningTime() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_STATUS, 3);
        if (configIntArrayValue == null || configIntArrayValue.length < 3) {
            return null;
        }
        byte[] bArr = {(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255), (byte) ((configIntArrayValue[2] >> 0) & 255), (byte) ((configIntArrayValue[2] >> 8) & 255)};
        StringBuilder sbA = a.a("[Laser] status[0] :  bytes[0] = ");
        sbA.append((int) bArr[0]);
        sbA.append(", bytes[1] = ");
        sbA.append((int) bArr[1]);
        sbA.append(", bytes[2] = ");
        sbA.append((int) bArr[2]);
        sbA.append(", bytes[3] = ");
        sbA.append((int) bArr[3]);
        sbA.append("\n");
        Log.v(TAG, sbA.toString());
        Log.v(TAG, "[Laser] status[1] :  bytes[4] = " + ((int) bArr[4]) + ", bytes[5] = " + ((int) bArr[5]) + ", bytes[6] = " + ((int) bArr[6]) + ", bytes[7] = " + ((int) bArr[7]) + "\n");
        StringBuilder sb = new StringBuilder();
        sb.append("[Laser] status[2] :  bytes[8] = ");
        sb.append((int) bArr[8]);
        sb.append(", bytes[9] = ");
        sb.append((int) bArr[9]);
        sb.append("\n");
        Log.v(TAG, sb.toString());
        return bArr;
    }

    public int getSACustomerType() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_SA_CUSTOMER_TYPE);
    }

    public int getSSCVByOneRange() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_STEP);
    }

    public int getSSCVByOneSpan() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_SPAN);
    }

    public int[] getSaturation20() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getSaturation20();
        return iArr;
    }

    public int[] getSaturation80() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[3] = HisVideoApi.getSaturation80();
        return iArr;
    }

    public int[] getSaturationMax() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[4] = HisVideoApi.getSaturationMax();
        return iArr;
    }

    public int[] getSaturationMid() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getSaturationMid();
        return iArr;
    }

    public int[] getSaturationMin() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getSaturationMin();
        return iArr;
    }

    public int getScreenLessMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_SCREEN_LESS_MODE);
        String str = SystemProperties.get(HISENSE_FFGA_SCREENLESS, "false");
        if (str.equals("true") != (configValue == 1)) {
            Log.d(TAG, "get ffga screenless " + str + " relValue " + configValue);
            if (1 == configValue) {
                SystemProperties.set(HISENSE_FFGA_SCREENLESS, "true");
                setWoWWoLWoC(1);
            } else {
                SystemProperties.set(HISENSE_FFGA_SCREENLESS, "false");
                setWoWWoLWoC(0);
            }
        }
        return configValue;
    }

    public boolean getSensorEnableStatus() {
        return false;
    }

    public int getSensorValue() {
        return HisVideoApi.getLightSensorValue();
    }

    public String getSerialNewNumber(String str) {
        return getConfigString(str);
    }

    public int getSerialNewNumberLength(String str) {
        return getConfigValue(str);
    }

    public String getServiceTag() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SERVICE_TAG);
    }

    public int[] getSharpness() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[4] = HisVideoApi.getSharpnessByFactory();
        return iArr;
    }

    public int[] getSharpness20() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[1] = HisVideoApi.getSharpness20();
        return iArr;
    }

    public int[] getSharpness80() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[3] = HisVideoApi.getSharpness80();
        return iArr;
    }

    public int[] getSharpnessMax() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[4] = HisVideoApi.getSharpnessMax();
        return iArr;
    }

    public int[] getSharpnessMid() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[2] = HisVideoApi.getSharpnessMid();
        return iArr;
    }

    public int[] getSharpnessMin() {
        int[] iArr = new int[10];
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        iArr[0] = HisVideoApi.getSharpnessMin();
        return iArr;
    }

    public int[] getSignalVideoFormat() {
        int[] iArr = new int[4];
        return HisVideoApi.getSignalVideoFormat();
    }

    public int getSilentPowerOn() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_SILENT_POWER_ON);
    }

    public String getSlaveBluStatus() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SLAVE_BLU_STATUS);
        Log.i(TAG, "getSlaveBluStatus: " + configString);
        return configString;
    }

    public String getSlaveBluVersion() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SLAVE_BLU_VERSION);
        a.a("getSlaveBluVersion, SlaveBluVersion = ", configString, "\n", TAG);
        return configString;
    }

    public int getSocDrc() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getSoundSocDRCFeature(1);
    }

    public boolean getSocDrcEnable() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getSoundSocDRCFeature(0) == 1;
    }

    public String getSoftwareVersion() {
        return SystemProperties.get("ro.vt.build.softversion");
    }

    public int getSoundVolCurveMax() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        HisAudioApi.getSoundVolCurveMax();
        return 255;
    }

    public int getSoundVolCurveMin() {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        HisAudioApi.getSoundVolCurveMin();
        return 0;
    }

    public long getStartingOnCount() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_STARTING_ON_COUNT, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.e(TAG, "[laser]getStartingOnCount, error \n");
            return -1L;
        }
        long j = ((long) configIntArrayValue[0]) + (((long) configIntArrayValue[1]) << 32);
        StringBuilder sbA = a.a("dataHex:");
        sbA.append(Long.toHexString(j));
        sbA.append(";highHex:");
        sbA.append(Integer.toHexString(configIntArrayValue[1]));
        sbA.append(";lowHex:");
        sbA.append(Integer.toHexString(configIntArrayValue[0]));
        Log.e(TAG, sbA.toString());
        return j;
    }

    public int getStoreMode() {
        Log.d(TAG, "get Store Mode.\n");
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_USER_MODE_SELECT);
    }

    public int getStrPowerOffCount() {
        return HisensePlatformConfig.getStrPowerOffCount();
    }

    public long getStrStartOnCount() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_STR_STARTING_ON_COUNT, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.e(TAG, "[laser]getStrStartOnCount, error \n");
            return -1L;
        }
        long j = ((long) configIntArrayValue[0]) + (((long) configIntArrayValue[1]) << 32);
        StringBuilder sbA = a.a("dataHex:");
        sbA.append(Long.toHexString(j));
        sbA.append(";highHex:");
        sbA.append(Integer.toHexString(configIntArrayValue[1]));
        sbA.append(";lowHex:");
        sbA.append(Integer.toHexString(configIntArrayValue[0]));
        Log.e(TAG, sbA.toString());
        return j;
    }

    public int getTconType() {
        Log.d(TAG, "getTconType");
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TCON_TYPE);
    }

    public int getTconUpgradeFlag() {
        Log.d(TAG, "getTconUpgradeFlag...");
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TCON_UPGRADE_FLAG);
        a.b("getTconUpgradeFlag, ret is", configValue, TAG);
        return configValue;
    }

    public String getTconVersion() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_TCON_VERSION);
        a.a("getTconVersion, tconVersion = ", configString, "\n", TAG);
        return configString;
    }

    public int[] getTestPatternValue(String str, int i) {
        return getConfigIntArrayValue(str, i);
    }

    public int getTimeSyncFromTsMode() {
        return 0;
    }

    public int getToFacMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOFAC_MODE);
        if (configValue == 255) {
            return 1;
        }
        return configValue;
    }

    public int getTotalJbTime() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOTAL_JBTIME);
    }

    public int getTotalOffsTime() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOTAL_OFFSTIME);
    }

    public int getTunnerGpioStatus() {
        Log.d(TAG, "getTunnerGpioStatus");
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_TUNNER_GPIO_STATUS);
    }

    public String getTvBrand() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BRAND);
    }

    public int getTvCountry(String str) {
        return getConfigValue(str);
    }

    public String getTvKeyName(String str) {
        return "";
    }

    public int getTvLanguage(String str) {
        return getConfigValue(str);
    }

    public int getTvRunTime(String str) {
        return getConfigValue(str);
    }

    public int getTvsRcuType() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_INPUT_SRC_INPUT_SRC_TVS_RCU_TYPE);
    }

    public String getTvsTconVersion() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_TVS_TCON_VERSION);
        a.a("getTvsTconVersion, version = ", configString, "\n", TAG);
        return configString;
    }

    public int getUHDVcomValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE_1);
    }

    public int getUartCtrlState() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_UART_CTRL);
        if (configValue == 1 || configValue == 255) {
            return 1;
        }
        return configValue;
    }

    public int getUpgradeFlag() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_UPGRADE_FLAG);
    }

    public int getUserIrCodeIndex() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_IR_CODE_INDEX);
    }

    public int getVcomValue(String str) {
        return getConfigValue(str);
    }

    public int getVcomVideoParameter(String str) {
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_ENTER_VCOM) && !str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_EXIT_VCOM)) {
            return 0;
        }
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getVcomTestEnable();
    }

    public byte[] getVcomsValueByte() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_ALL_VCOM_VALUE, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            return null;
        }
        return new byte[]{(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255)};
    }

    public int[] getVideoCurveValue(String str, int i) {
        int[] iArr = new int[10];
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MIN)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBrightnessMin();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_20)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBrightness20();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MID)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBrightnessMid();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_80)) {
            HisVideoApi hisVideoApi4 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBrightness80();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MAX)) {
            HisVideoApi hisVideoApi5 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getBrightnessMax();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MIN)) {
            HisVideoApi hisVideoApi6 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getContrastMin();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_20)) {
            HisVideoApi hisVideoApi7 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getContrast20();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MID)) {
            HisVideoApi hisVideoApi8 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getContrastMid();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_80)) {
            HisVideoApi hisVideoApi9 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getContrast80();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MAX)) {
            HisVideoApi hisVideoApi10 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getContrastMax();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MIN)) {
            HisVideoApi hisVideoApi11 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSaturationMin();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_20)) {
            HisVideoApi hisVideoApi12 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSaturation20();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MID)) {
            HisVideoApi hisVideoApi13 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSaturationMid();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_80)) {
            HisVideoApi hisVideoApi14 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSaturation80();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MAX)) {
            HisVideoApi hisVideoApi15 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSaturationMax();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MIN)) {
            HisVideoApi hisVideoApi16 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getHueMin();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_20)) {
            HisVideoApi hisVideoApi17 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getHue20();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MID)) {
            HisVideoApi hisVideoApi18 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getHueMid();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_80)) {
            HisVideoApi hisVideoApi19 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getHue80();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MAX)) {
            HisVideoApi hisVideoApi20 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getHueMax();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MIN)) {
            HisVideoApi hisVideoApi21 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSharpnessMin();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_20)) {
            HisVideoApi hisVideoApi22 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSharpness20();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MID)) {
            HisVideoApi hisVideoApi23 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSharpnessMid();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_80)) {
            HisVideoApi hisVideoApi24 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSharpness80();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MAX)) {
            HisVideoApi hisVideoApi25 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getSharpnessMax();
        }
        return iArr;
    }

    public int[] getVideoFormatInfo(String str, int i) {
        return getConfigIntArrayValue(str, i);
    }

    public int getVolumeCurve(int i) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.getSoundVolCurveVal(i);
    }

    public int[] getVolumeCurve0() {
        int[] iArr = new int[10];
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        iArr[0] = HisAudioApi.getSoundVolCurveVal(0);
        return iArr;
    }

    public int[] getVolumeCurve100() {
        int[] iArr = new int[10];
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        iArr[4] = HisAudioApi.getSoundVolCurveVal(4);
        return iArr;
    }

    public int[] getVolumeCurve25() {
        int[] iArr = new int[10];
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        iArr[1] = HisAudioApi.getSoundVolCurveVal(1);
        return iArr;
    }

    public int[] getVolumeCurve50() {
        int[] iArr = new int[10];
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        iArr[2] = HisAudioApi.getSoundVolCurveVal(2);
        return iArr;
    }

    public int[] getVolumeCurve75() {
        int[] iArr = new int[10];
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        iArr[3] = HisAudioApi.getSoundVolCurveVal(3);
        return iArr;
    }

    public int getWB20PointBlue() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getFactoryMultiPointWhiteBalanceBlue();
    }

    public int getWB20PointGreen() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getFactoryMultiPointWhiteBalanceGreen();
    }

    public int getWB20PointLevel() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getFactoryMultiPointWhiteBalanceLevel();
    }

    public int getWB20PointRed() {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getFactoryMultiPointWhiteBalanceRed();
    }

    public int getWhiteBalancePanel(String str) {
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PANEL_TYPE)) {
            return 0;
        }
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getPanel();
    }

    public int[] getWhiteBalanceValue(String str, int i) {
        int[] iArr = new int[10];
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_R)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceGainRed();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_G)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceGainGreen();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_B)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceGainBlue();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_R)) {
            HisVideoApi hisVideoApi4 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceOffsetRed();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_G)) {
            HisVideoApi hisVideoApi5 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceOffsetGreen();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_B)) {
            HisVideoApi hisVideoApi6 = this.mHisVideoApi;
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceOffsetBlue();
        }
        return iArr;
    }

    public int getWhiteValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_WHITE_VALUE);
    }

    public int getWifiCheckConfigValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_WIFI_CONFIG_CHECK);
    }

    public int getZDMICValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_ZD_MIC);
    }

    public String getZdMIC1Db() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_1MIC_DB_ZD);
    }

    public String getZdMIC2Db() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_2MIC_DB_ZD);
    }

    public void initMicLed() {
        if (SystemProperties.getBoolean("ro.vt.ffga", true)) {
            this.mMicLedController = new LedFactory();
            this.mMicLedController.initLedFactory();
        }
    }

    public boolean isAgingMode() {
        boolean z = 1 == getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_AGING_MODE);
        Log.d(TAG, "isAgingMode value is " + z + "\n");
        return z;
    }

    public boolean isBluStatusSupport() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BLU_STATUS);
        return (configString == null || configString.isEmpty() || configString.contains("FF") || configString.contains("ff") || configString.equals("BLANK")) ? false : true;
    }

    public boolean isBluSupport() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BLU_VERSION);
        return (configString == null || configString.isEmpty() || configString.contains("FF") || configString.contains("ff") || configString.equals("BLANK")) ? false : true;
    }

    public boolean isDdrSscEnable() {
        return 1 != getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_ONOFF);
    }

    public boolean isFactoryMode() {
        return 1 == getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FACTORY_MODE) || 255 == getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FACTORY_MODE);
    }

    public int isFileExit(String str) {
        return HisensePlatformConfig.isFileOrDirExist(str);
    }

    public boolean isFrcSupport() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_FRC_VERSION);
        return (configString == null || configString.isEmpty() || configString.contains("FF") || configString.contains("ff") || configString.equals("BLANK")) ? false : true;
    }

    public boolean isHaveFte() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "user_setup_complete", -3) == 0 || Settings.Secure.getInt(this.mContext.getContentResolver(), "tv_user_setup_complete", -3) == 0;
    }

    public boolean isNeedToShowBlu() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BLU_VERSION);
        a.a("isNeedToShowBlu, version = ", configString, "\n", TAG);
        return (configString == null || configString.isEmpty() || configString.equals("BLANK")) ? false : true;
    }

    public boolean isNeedToShowFrc() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_FRC_VERSION);
        a.a("isNeedToShowFrc, tconVersion = ", configString, "\n", TAG);
        return (configString == null || configString.isEmpty() || configString.equals("BLANK")) ? false : true;
    }

    public boolean isNeedToShowTcon() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_TCON_VERSION);
        a.a("isNeedToShowTcon, tconVersion = ", configString, "\n", TAG);
        return (configString == null || configString.isEmpty() || configString.equals("BLANK")) ? false : true;
    }

    public boolean isSlaveBluStatusSupport() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SLAVE_BLU_STATUS);
        return (configString == null || configString.isEmpty() || configString.contains("FF") || configString.contains("ff") || configString.equals("BLANK")) ? false : true;
    }

    public boolean isSlaveBluSupport() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SLAVE_BLU_VERSION);
        return (configString == null || configString.isEmpty() || configString.contains("FF") || configString.contains("ff") || configString.equals("BLANK")) ? false : true;
    }

    public boolean isSupportSecondVcom() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_SUPPORT_2VCOM) == 1;
    }

    public int isSupportVcom() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_SUPPORT_VCOM);
    }

    public boolean isTconSupport() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_TCON_VERSION);
        return (configString == null || configString.isEmpty() || configString.contains("FF") || configString.contains("ff") || configString.equals("BLANK")) ? false : true;
    }

    public int isfDataExport() throws Throwable {
        int iIsfDataExport_native;
        String strSearchUsbPath = searchUsbPath();
        if (strSearchUsbPath == null) {
            Log.d(TAG, "no usb found!\n");
            return -1;
        }
        String str = SystemProperties.get("ro.product.hisense.model");
        if (str == null) {
            Log.d(TAG, "modelName get failed\n");
            return -2;
        }
        synchronized (HisenseTvAPIBase.class) {
            iIsfDataExport_native = HisenseTVNativeWrapper.isfDataExport_native(str, strSearchUsbPath);
        }
        a.a("Leave isfDataExport, return value = ", iIsfDataExport_native, "\n", TAG);
        return iIsfDataExport_native;
    }

    public int isfDataImport(String str) {
        int iIsfDataImport_native;
        synchronized (HisenseTvAPIBase.class) {
            iIsfDataImport_native = HisenseTVNativeWrapper.isfDataImport_native(str);
        }
        a.a("Leave isfDataImport, return value = ", iIsfDataImport_native, "\n", TAG);
        return iIsfDataImport_native;
    }

    public String isfDataSearch() throws Throwable {
        String strIsfDataSearch_native;
        String strSearchUsbPath = searchUsbPath();
        if (strSearchUsbPath == null) {
            Log.d(TAG, "no usb found!\n");
            return "";
        }
        synchronized (HisenseTvAPIBase.class) {
            strIsfDataSearch_native = HisenseTVNativeWrapper.isfDataSearch_native(strSearchUsbPath);
        }
        a.a("Leave isfDataSearch, return String = ", strIsfDataSearch_native, "\n", TAG);
        return strIsfDataSearch_native;
    }

    public int manualGainEnable(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_MANUAL_GAIN_ENABLE, i);
    }

    public boolean presetChannelList(int i) {
        boolean z;
        HisenseTvAPIBase hisenseTvAPIBase;
        boolean z2;
        HisenseTvAPIBase hisenseTvAPIBase2;
        Log.d(TAG, "presetChannelList(1), cityCode value = " + i + "\n");
        MtkTvChannelListBase mtkTvChannelListBase = new MtkTvChannelListBase();
        ArrayList arrayList = new ArrayList();
        Log.d(TAG, "cleanChannelList(1), return value = " + MtkTvChannelListBase.cleanChannelList(1, true) + "\n");
        Log.d(TAG, "cleanChannelList(2), return value = " + MtkTvChannelListBase.cleanChannelList(2, true) + "\n");
        if (getMarketRegion().equals("eu") || getMarketRegion().equals("em")) {
            Log.d(TAG, "cleanChannelList(3), return value = " + MtkTvChannelListBase.cleanChannelList(3, true) + "\n");
            Log.d(TAG, "cleanChannelList(4), return value = " + MtkTvChannelListBase.cleanChannelList(4, true) + "\n");
            Log.d(TAG, "cleanChannelList(5), return value = " + MtkTvChannelListBase.cleanChannelList(5, true) + "\n");
            Log.d(TAG, "cleanChannelList(6), return value = " + MtkTvChannelListBase.cleanChannelList(6, true) + "\n");
            a.a("cleanChannelList(7), return value = ", MtkTvChannelListBase.cleanChannelList(7, true), "\n", TAG);
        }
        setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
        if (getMarketRegion().equals("us")) {
            arrayList.add(AddAnalogChannelInfo(1, 0, 3670144, 1, 15, 471250000, 471250000, 0, -1, 0, 4, 14, null));
            arrayList.add(AddAnalogChannelInfo(1, 0, 1572992, 1, 15, 83250000, 83250000, 0, -1, 0, 4, 6, null));
            arrayList.add(AddAnalogChannelInfo(1, 0, 4456576, 1, 15, 489250000, 489250000, 0, -1, 0, 4, 17, null));
            arrayList.add(AddAnalogChannelInfo(1, 0, 13369472, 1, 15, 693250000, 693250000, 0, -1, 0, 4, 51, null));
            arrayList.add(AddAnalogChannelInfo(1, 0, 16777344, 1, 15, 771250000, 771250000, 0, -1, 0, 4, 64, null));
            arrayList.add(AddAnalogChannelInfo(1, 0, R.string.PERSOSUBSTATE_RUIM_NETWORK2_PUK_SUCCESS, 1, 15, 777250000, 777250000, 0, -1, 0, 4, 65, null));
            int channelList = mtkTvChannelListBase.setChannelList(0, arrayList);
            a.a("setChannelList(1), return value = ", channelList, "\n", TAG);
            if (channelList != 0) {
                return false;
            }
            arrayList.clear();
            arrayList.add(AddAnalogChannelInfo(2, 0, 10223744, 1, 15, 313250000, 313250000, 0, -1, 0, 5, 39, null));
            arrayList.add(AddAnalogChannelInfo(2, 0, 524416, 1, 15, 55250000, 55250000, 0, -1, 0, 5, 2, null));
            arrayList.add(AddAnalogChannelInfo(2, 0, 1048704, 1, 15, 67250000, 67250000, 0, -1, 0, 5, 4, null));
            arrayList.add(AddAnalogChannelInfo(2, 0, 1572992, 1, 15, 83250000, 83250000, 0, -1, 0, 5, 6, null));
            arrayList.add(AddAnalogChannelInfo(2, 0, 1835136, 1, 15, 175250000, 175250000, 0, -1, 0, 5, 7, null));
            arrayList.add(AddAnalogChannelInfo(2, 0, 2621568, 1, 15, 193250000, 193250000, 0, -1, 0, 5, 10, null));
            arrayList.add(AddAnalogChannelInfo(2, 0, 3145856, 1, 15, 205250000, 205250000, 0, -1, 0, 5, 12, null));
            int channelList2 = mtkTvChannelListBase.setChannelList(0, arrayList);
            a.a("setChannelList(2)...analog_cable, return value = ", channelList2, "\n", TAG);
            if (channelList2 != 0) {
                return false;
            }
            arrayList.clear();
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 5, 1, 15, 79000000, 1, 1, 2, 1, 5, 0, 5, 1, "AT05-1"));
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 5, 2, 15, 79000000, 2, 1, 2, 1, 5, 0, 5, 1, "AT05-2"));
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 5, 3, 15, 79000000, 3, 1, 2, 1, 5, 0, 5, 1, "AT05-3"));
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 5, 4, 15, 79000000, 4, 1, 2, 1, 5, 0, 5, 1, "AT05-4"));
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 22, 1, 15, 521000000, 1, 1, 2, 1, 22, 0, 22, 535, "SBC1-D1"));
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 2, 1, 15, 743000000, 2, 1, 2, 1, 59, 0, 59, 1357, "SBC2-D1"));
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 2, 2, 15, 743000000, 1, 1, 2, 1, 59, 0, 59, 1357, "SBC2-D2"));
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 2, 3, 15, 743000000, 3, 1, 2, 1, 59, 0, 59, 1357, "RRT5-DL"));
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 69, 1, 15, 803000000, 1, 1, 2, 1, 69, 0, 69, 1, "AT69-1"));
            arrayList.add(AddAtscChannelInfo(1, 0, 3, 69, 2, 15, 803000000, 2, 1, 2, 1, 69, 0, 69, 1, "AT69-2"));
            int channelList3 = mtkTvChannelListBase.setChannelList(0, arrayList);
            a.a("setChannelList.....atsc air, return value = ", channelList3, "\n", TAG);
            if (channelList3 != 0) {
                return false;
            }
            arrayList.clear();
            arrayList.add(AddAtscChannelInfo(2, 0, 3, 14, 1, 15, 141000000, 1, 1, 6, 2, 17, 0, 17, 1, "ATSC-1"));
            arrayList.add(AddAtscChannelInfo(2, 0, 3, 14, 2, 15, 141000000, 2, 1, 6, 2, 17, 0, 17, 1, "ATSC-2"));
            arrayList.add(AddAtscChannelInfo(2, 0, 3, 14, 3, 15, 141000000, 3, 1, 6, 2, 17, 0, 17, 1, "ATSC-3"));
            arrayList.add(AddAtscChannelInfo(2, 0, 3, 23, 1, 15, 435000000, 1, 1, 14, 2, 59, 0, 59, 1, "AC23-1"));
            arrayList.add(AddAtscChannelInfo(2, 0, 3, 23, 2, 15, 435000000, 2, 1, 14, 2, 59, 0, 59, 1, "AC23-2"));
            arrayList.add(AddAtscChannelInfo(2, 0, 3, 23, 3, 15, 435000000, 3, 1, 14, 2, 59, 0, 59, 1, "AC23-3"));
            arrayList.add(AddAtscChannelInfo(2, 0, 3, 23, 4, 15, 435000000, 4, 1, 14, 2, 59, 0, 59, 1, "AC23-4"));
            arrayList.add(AddAtscChannelInfo(2, 0, 3, 23, 5, 15, 435000000, 5, 1, 14, 2, 59, 0, 59, 1, "AC23-5"));
            arrayList.add(AddAtscChannelInfo(2, 0, 3, 133, 1, 15, 849000000, 1, 1, 14, 2, 133, 0, 133, 1503, "C256HD"));
            int channelList4 = mtkTvChannelListBase.setChannelList(0, arrayList);
            a.a("setChannelList.....atsc cabale, return value = ", channelList4, "\n", TAG);
            if (channelList4 != 0) {
                return false;
            }
            setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
            return true;
        }
        if (!getMarketRegion().equals("eu") && !getMarketRegion().equals("em")) {
            if (!getMarketRegion().equals("sa")) {
                Log.d(TAG, "presetChannelLis  function region not supported \n");
                return false;
            }
            setConfigValue(MtkTvConfigTypeBase.CFG_MISC_DVBT_DISABLE_MONITOR, 1);
            if (i == 1) {
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(88, 0), 1, TvParams.NW_MASK, 607250000, 607250000, 66, 1, 130, 4, 88, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(134, 0), 1, TvParams.NW_MASK, 855250000, 855250000, TvParams.TV_SYS_DK, 1, 130, 4, 134, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(6, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 4, 6, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(12, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 66, 0, 130, 4, 12, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(17, 0), 1, TvParams.NW_MASK, 489250000, 489250000, 66, 0, 130, 4, 17, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(65, 0), 1, TvParams.NW_MASK, 777250000, 777250000, 256, 1, 130, 4, 65, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(39, 0), 1, TvParams.NW_MASK, 313250000, 313250000, 256, 0, 130, 4, 39, null));
                int channelList5 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(HUANG_DAO of SA), return value = ", channelList5, "\n", TAG);
                if (channelList5 != 0) {
                    a.a("setChannelList(1)air atv for HUANG_DAO of SA failed! return value = ", channelList5, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "TV GloboHD", 19, 503143000, 5, 1, 32720, 32720, 32720, 2048));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "TV GloboSD", 19, 503143000, 5, 2, 32720, 32720, 32720, 2049));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "NHK650i1þBg:e", 20, 509143000, 2, 11, 32721, 32721, 32721, 2056));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "NHK650i2þBg:e", 20, 509143000, 2, 12, 32721, 32721, 32721, 2057));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "NHK650i3þBg:e", 20, 509143000, 2, 13, 32721, 32721, 32721, 2058));
                int channelList6 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1) dtv(HUANG_DAO of SA), return value = ", channelList6, "\n", TAG);
                if (channelList6 != 0) {
                    a.a("setChannelList(1) dtv(HUANG_DAO of SA) failed! return value = ", channelList6, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(88, 0), 1, TvParams.NW_MASK, 607250000, 607250000, 66, 1, 130, 5, 88, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(134, 0), 1, TvParams.NW_MASK, 855250000, 855250000, TvParams.TV_SYS_DK, 1, 130, 5, 134, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(6, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 5, 6, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(12, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 66, 0, 130, 5, 12, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(17, 0), 1, TvParams.NW_MASK, 489250000, 489250000, 66, 0, 130, 5, 17, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(65, 0), 1, TvParams.NW_MASK, 777250000, 777250000, 256, 1, 130, 5, 65, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(39, 0), 1, TvParams.NW_MASK, 313250000, 313250000, 256, 0, 130, 5, 39, null));
                int channelList7 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)cable atv(HUANG_DAO of SA), return value = ", channelList7, "\n", TAG);
                if (channelList7 == 0) {
                    setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                    return true;
                }
                a.a("setChannelList(1)cable atv for HUANG_DAO of SA failed! return value = ", channelList7, "\n", TAG);
                return false;
            }
            if (i == 2) {
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(83, 0), 1, TvParams.NW_MASK, 607250000, 607250000, 66, 1, 130, 4, 88, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(134, 0), 1, TvParams.NW_MASK, 855250000, 855250000, TvParams.TV_SYS_DK, 1, 130, 4, 134, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 4, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(12, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 66, 0, 130, 4, 12, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(17, 0), 1, TvParams.NW_MASK, 489250000, 489250000, 66, 0, 130, 4, 17, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(65, 0), 1, TvParams.NW_MASK, 777250000, 777250000, 256, 4, 130, 4, 65, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(39, 0), 1, TvParams.NW_MASK, 313250000, 313250000, 256, 0, 130, 4, 39, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(29, 0), 1, TvParams.NW_MASK, 561250000, 561250000, 256, 5, 130, 4, 29, null));
                int channelList8 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_ZHONG_SHI), return value = ", channelList8, "\n", TAG);
                if (channelList8 != 0) {
                    a.a("setChannelList(1)air atv for CT_SA_ZHONG_SHI failed! return value = ", channelList8, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "TV GloboHD", 19, 503143000, 5, 1, 32720, 32720, 32720, 2048));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "TV GloboSD", 19, 503143000, 5, 2, 32720, 32720, 32720, 2049));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "NHK650i1þBg:e", 20, 509143000, 2, 11, 32721, 32721, 32721, 2056));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "NHK650i2þBg:e", 20, 509143000, 2, 12, 32721, 32721, 32721, 2057));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "NHK650i3þBg:e", 20, 509143000, 2, 13, 32721, 32721, 32721, 2058));
                int channelList9 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1) dtv(CT_ZHONG_SHI), return value = ", channelList9, "\n", TAG);
                if (channelList9 != 0) {
                    a.a("setChannelList(1) dtv(CT_ZHONG_SHI) failed! return value = ", channelList9, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(83, 0), 1, TvParams.NW_MASK, 607250000, 607250000, 66, 1, 130, 5, 88, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(134, 0), 1, TvParams.NW_MASK, 855250000, 855250000, TvParams.TV_SYS_DK, 1, 130, 5, 134, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 5, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(12, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 66, 0, 130, 5, 12, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(17, 0), 1, TvParams.NW_MASK, 489250000, 489250000, 66, 0, 130, 5, 17, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(65, 0), 1, TvParams.NW_MASK, 777250000, 777250000, 256, 4, 130, 5, 65, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(39, 0), 1, TvParams.NW_MASK, 313250000, 313250000, 256, 0, 130, 5, 39, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(29, 0), 1, TvParams.NW_MASK, 561250000, 561250000, 256, 5, 130, 5, 29, null));
                int channelList10 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_ZHONG_SHI), return value = ", channelList10, "\n", TAG);
                if (channelList10 == 0) {
                    setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                    return true;
                }
                a.a("setChannelList(1)air atv for CT_SA_ZHONG_SHI failed! return value = ", channelList10, "\n", TAG);
                return false;
            }
            if (i == 4) {
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(6, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 4, 6, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(12, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 16384, 0, 256, 4, 12, null));
                int channelList11 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_JIANG_MEN), return value = ", channelList11, "\n", TAG);
                if (channelList11 != 0) {
                    a.a("setChannelList(1)air atv for CT_JIANG_MEN failed! return value = ", channelList11, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "TV GloboHD", 19, 503143000, 5, 1, 32720, 32720, 32720, 2048));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "TV GloboSD", 19, 503143000, 5, 2, 32720, 32720, 32720, 2049));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "NHK650i1þBg:e", 20, 509143000, 2, 11, 32721, 32721, 32721, 2056));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "NHK650i2þBg:e", 20, 509143000, 2, 12, 32721, 32721, 32721, 2057));
                arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "NHK650i3þBg:e", 20, 509143000, 2, 13, 32721, 32721, 32721, 2058));
                int channelList12 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1) dtv(CT_JIANG_MEN), return value = ", channelList12, "\n", TAG);
                if (channelList12 != 0) {
                    a.a("setChannelList(1) dtv(CT_JIANG_MEN) failed! return value = ", channelList12, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(6, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 5, 6, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(12, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 16384, 0, 256, 5, 12, null));
                int channelList13 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_JIANG_MEN), return value = ", channelList13, "\n", TAG);
                if (channelList13 == 0) {
                    setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                    return true;
                }
                a.a("setChannelList(1)air atv for CT_JIANG_MEN failed! return value = ", channelList13, "\n", TAG);
                return false;
            }
            switch (i) {
                case 19:
                    arrayList.clear();
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(3, 0), 1, TvParams.NW_MASK, 61250000, 61250000, 66, 1, 130, 4, 3, null));
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(5, 0), 1, TvParams.NW_MASK, 77250000, 77250000, TvParams.TV_SYS_DK, 1, 130, 4, 5, null));
                    int channelList14 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1)air atv(CT_NewSan), return value = ", channelList14, "\n", TAG);
                    if (channelList14 != 0) {
                        a.a("setChannelList(1)air atv for CT_NewSan failed! return value = ", channelList14, "\n", TAG);
                        return false;
                    }
                    arrayList.clear();
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "MOV1080I", 26, 545143000, 26, 1, 1553, 1553, 1553, 49696));
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "COL576I", 26, 545143000, 26, 2, 1553, 1553, 1553, 49697));
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "16ST576I", 26, 545143000, 26, 3, 1553, 1553, 1553, 49698));
                    int channelList15 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1) dtv(CT_NewSan), return value = ", channelList15, "\n", TAG);
                    if (channelList15 != 0) {
                        a.a("setChannelList(1) dtv(CT_NewSan) failed! return value = ", channelList15, "\n", TAG);
                        return false;
                    }
                    arrayList.clear();
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(3, 0), 1, TvParams.NW_MASK, 61250000, 61250000, 66, 1, 130, 5, 3, null));
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(5, 0), 1, TvParams.NW_MASK, 77250000, 77250000, TvParams.TV_SYS_DK, 1, 130, 5, 5, null));
                    int channelList16 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1)air atv(CT_NewSan), return value = ", channelList16, "\n", TAG);
                    if (channelList16 == 0) {
                        setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                        return true;
                    }
                    a.a("setChannelList(1)air atv for CT_NewSan failed! return value = ", channelList16, "\n", TAG);
                    return false;
                case 20:
                    arrayList.clear();
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(7, 0), 1, TvParams.NW_MASK, 175250000, 175250000, 66, 1, 130, 4, 7, null));
                    int channelList17 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1)air atv(CT_BGH), return value = ", channelList17, "\n", TAG);
                    if (channelList17 != 0) {
                        a.a("setChannelList(1)air atv for CT_BGH failed! return value = ", channelList17, "\n", TAG);
                        return false;
                    }
                    arrayList.clear();
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "ENCUENTRO", 20, 509143000, 22, 1, 32720, 32720, 32720, 59168));
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "PAKA PAKA", 20, 509143000, 22, 2, 32720, 32720, 32720, 59169));
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "TATETI", 20, 509143000, 22, 3, 32721, 32721, 32721, 59170));
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "INCAA TV", 20, 509143000, 22, 4, 32721, 32721, 32721, 59171));
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "TECNOPOLIS", 20, 509143000, 22, 5, 32721, 32721, 32721, 59172));
                    int channelList18 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1) dtv(CT_BGH), return value = ", channelList18, "\n", TAG);
                    if (channelList18 != 0) {
                        a.a("setChannelList(1) dtv(CT_BGH) failed! return value = ", channelList18, "\n", TAG);
                        return false;
                    }
                    arrayList.clear();
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(7, 0), 1, TvParams.NW_MASK, 175250000, 175250000, 66, 1, 130, 5, 7, null));
                    int channelList19 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1)air atv(CT_BGH), return value = ", channelList19, "\n", TAG);
                    if (channelList19 == 0) {
                        setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                        return true;
                    }
                    a.a("setChannelList(1)air atv for CT_BGH failed! return value = ", channelList19, "\n", TAG);
                    return false;
                case 21:
                    arrayList.clear();
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(89, 0), 1, TvParams.NW_MASK, 615250000, 615250000, 66, 0, 130, 4, 89, null));
                    int channelList20 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1)air atv(CT_SA_RIVIERA), return value = ", channelList20, "\n", TAG);
                    if (channelList20 != 0) {
                        a.a("setChannelList(1)air atv for CT_SA_RIVIERA failed! return value = ", channelList20, "\n", TAG);
                        return false;
                    }
                    arrayList.clear();
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "Ecuavisa HD", 23, 527143000, 2, 1, 1, 1, 1, 22));
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "Ecuavisa SD", 23, 527143000, 2, 2, 1, 1, 1, 23));
                    int channelList21 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1) dtv(CT_SA_RIVIERA), return value = ", channelList21, "\n", TAG);
                    if (channelList21 != 0) {
                        a.a("setChannelList(1) dtv(CT_SA_RIVIERA) failed! return value = ", channelList21, "\n", TAG);
                        return false;
                    }
                    arrayList.clear();
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(89, 0), 1, TvParams.NW_MASK, 615250000, 615250000, 66, 0, 130, 5, 89, null));
                    int channelList22 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1)air atv(CT_SA_RIVIERA), return value = ", channelList22, "\n", TAG);
                    if (channelList22 == 0) {
                        setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                        return true;
                    }
                    a.a("setChannelList(1)air atv for CT_SA_RIVIERA failed! return value = ", channelList22, "\n", TAG);
                    return false;
                case 22:
                    arrayList.clear();
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(2, 0), 1, TvParams.NW_MASK, 55250000, 55250000, 66, 4, 130, 4, 2, null));
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(4, 0), 1, TvParams.NW_MASK, 67250000, 67250000, TvParams.TV_SYS_DK, 5, 130, 4, 4, null));
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(6, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 4, 6, null));
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(8, 0), 1, TvParams.NW_MASK, 181250000, 181250000, 66, 5, 130, 4, 8, null));
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(10, 0), 1, TvParams.NW_MASK, 193250000, 193250000, 66, 5, 130, 4, 10, null));
                    arrayList.add(AddAnalogChannelInfo(1, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(12, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 256, 0, 130, 4, 12, null));
                    int channelList23 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1)air atv(CT_FRAVEGA), return value = ", channelList23, "\n", TAG);
                    if (channelList23 != 0) {
                        a.a("setChannelList(1)air atv for CT_FRAVEGA failed! return value = ", channelList23, "\n", TAG);
                        return false;
                    }
                    arrayList.clear();
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "AVC SD 4:3/HE-AAC stereo", 14, 473143000, 9, 1, 3512, 3512, 3512, 46848));
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "AVC SD 16:9/HE-AAC stereo", 14, 473143000, 9, 2, 3512, 3512, 3512, 46849));
                    arrayList.add(AddIsdbChannelInfo(1, 0, 5, 1, "AVC HD/HE-AAC test", 14, 473143000, 9, 3, 3512, 3512, 3512, 46850));
                    int channelList24 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1) dtv(CT_FRAVEGA), return value = ", channelList24, "\n", TAG);
                    if (channelList24 != 0) {
                        a.a("setChannelList(1) dtv(CT_FRAVEGA) failed! return value = ", channelList24, "\n", TAG);
                        return false;
                    }
                    arrayList.clear();
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(2, 0), 1, TvParams.NW_MASK, 55250000, 55250000, 66, 4, 130, 5, 2, null));
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(4, 0), 1, TvParams.NW_MASK, 67250000, 67250000, TvParams.TV_SYS_DK, 5, 130, 5, 4, null));
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(6, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 5, 6, null));
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(8, 0), 1, TvParams.NW_MASK, 181250000, 181250000, 66, 5, 130, 5, 8, null));
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(10, 0), 1, TvParams.NW_MASK, 193250000, 193250000, 66, 5, 130, 5, 10, null));
                    arrayList.add(AddAnalogChannelInfo(2, 0, ChannelIDCal_ATSC_ISDB.SB_ATSC_GEN_ONE_PART_CHANNEL_ID(12, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 256, 0, 130, 5, 12, null));
                    int channelList25 = mtkTvChannelListBase.setChannelList(0, arrayList);
                    a.a("setChannelList(1)air atv(CT_FRAVEGA), return value = ", channelList25, "\n", TAG);
                    if (channelList25 == 0) {
                        setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                        return true;
                    }
                    a.a("setChannelList(1)air atv for CT_FRAVEGA failed! return value = ", channelList25, "\n", TAG);
                    return false;
                default:
                    Log.d(TAG, "presetChannelList function factory not supported for SA \n");
                    return false;
            }
        }
        SystemProperties.getInt("ro.hisense.market.extend_model", 0);
        setConfigValue(MtkTvConfigTypeBase.CFG_MISC_DVBT_DISABLE_MONITOR, 1);
        switch (i) {
            case 1:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 49750000, 49750000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 112250000, 112250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 184250000, 184250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 196250000, 196250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 215250000, 215250000, TvParams.TV_SYS_DK, 2, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 224250000, 224250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 313250000, 313250000, 16384, 0, 256, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 440250000, 440250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(909, 0), 1, TvParams.NW_MASK, 607250000, 607250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(910, 0), 1, TvParams.NW_MASK, 695250000, 695250000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(911, 0), 1, TvParams.NW_MASK, 855250000, 855250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                Log.d(TAG, "REGION5 is supported\n");
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(912, 0), 1, TvParams.NW_MASK, 193250000, 193250000, 16384, 0, 256, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(913, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 16384, 0, 256, 4, 0, null));
                int channelList26 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv, return value = ", channelList26, "\n", TAG);
                if (channelList26 != 0) {
                    a.b("setChannelList(1)air atv failed! return value = ", channelList26, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 538000000, 36924, 36924, 36924, 36988, 0, 0, "DT29 1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 538000000, 36924, 36924, 36924, 37052, 0, 0, "DT29 576i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 538000000, 36924, 36924, 36924, 37116, 0, 0, "DT29 576p", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 826000000, 12308, 9019, 41020, 41084, 0, 0, "DT65 576p", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(5, 1), 826000000, 12308, 9019, 41020, 41148, 0, 0, "DT65 720p", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(6, 1), 826000000, 12308, 9019, 41020, 41212, 0, 0, "DT65 576i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(7, 1), 650000000, 1043, 1043, 1043, 20, 0, 0, "DT2-43_1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(8, 1), 650000000, 1043, 1043, 1043, 21, 0, 0, "DT2-43_576i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(9, 1), 674000000, 1043, 1043, 1043, 20, 0, 0, "DT2-46_1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(10, 1), 627500000, 12307, 9018, 36924, 36988, 0, 0, "DT42_7M 1080i", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(11, 1), 627500000, 12307, 9018, 36924, 37052, 0, 0, "DT42_7M 576i", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(12, 1), 627500000, 12307, 9018, 36924, 37116, 0, 0, "DT42_7M 576p", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(13, 1), 593000000, 13096, 8350, 28, 300, 0, 0, "民視綜合台[民視]", 1, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(14, 1), 593000000, 13096, 8350, 28, 301, 0, 0, "民視交通台[民視]", 1, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(15, 1), 593000000, 13096, 8350, 28, 302, 0, 0, "民視新聞台[民視]", 1, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(16, 1), 593000000, 13096, 8350, 28, 304, 0, 0, "民視HD台[民視]", 1, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(17, 1), 818000000, 1, 1, 1, 1, 0, 0, "IRIB-TV1", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(18, 1), 818000000, 1, 1, 1, 2, 0, 0, "IRIB-TV2", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(19, 1), 818000000, 1, 1, 1, 3, 0, 0, "IRIB-TV3", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(20, 1), 818000000, 1, 1, 1, 4, 0, 0, "IRIB-TV4", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(21, 1), 818000000, 1, 1, 1, 5, 0, 0, "IRIB-TV5", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(22, 1), 818000000, 1, 1, 1, 6, 0, 0, "IRINN", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(23, 1), 818000000, 1, 1, 1, 7, 0, 0, "RADIO AMOUZESH", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(24, 1), 818000000, 1, 1, 1, 8, 0, 0, "RADIO QURAN-TV", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(25, 1), 818000000, 1, 1, 1, 17, 0, 0, "RADIO IRAN", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(26, 1), 818000000, 1, 1, 1, 18, 0, 0, "RADIO PAYAM", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(27, 1), 818000000, 1, 1, 1, 19, 0, 0, "RADIO JAVAN", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(28, 1), 818000000, 1, 1, 1, 20, 0, 0, "RADIO MAAREF", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(29, 1), 818000000, 1, 1, 1, 21, 0, 0, "RADIO QURAN", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(30, 1), 818000000, 1, 1, 1, 22, 0, 0, "RADIO FARHANG", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(31, 1), 818000000, 1, 1, 1, 23, 0, 0, "RADIO SALAMAT", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(32, 1), 818000000, 1, 1, 1, 24, 0, 0, "RADIO VARZESH", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(33, 1), 818000000, 1, 1, 1, 25, 0, 0, "RADIO TEJARAT", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(34, 1), 818000000, 1, 1, 1, 26, 0, 0, "RADIO TEHRAN", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(35, 1), 683000000, 1043, 1043, 1043, 20, 0, 0, "DT2-48-6M_1080i", 1, 2, 1, 1, TvParams.NW_MASK));
                int channelList27 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air dtv, return value = ", channelList27, "\n", TAG);
                if (channelList27 != 0) {
                    a.b("setChannelList(1)air dtv failed! return value = ", channelList27, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 129000000, 12309, 9020, 45116, 45180, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 1080i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 129000000, 12309, 9020, 45116, 45244, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 480p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 129000000, 12309, 9020, 45116, 45308, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 576i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 129000000, 12309, 9020, 45116, 45372, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 576p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(5, 1), 129000000, 12309, 9020, 45116, 47164, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(6, 1), 458000000, 12310, 9021, 49212, 49276, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(7, 1), 458000000, 12310, 9021, 49212, 51260, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(8, 1), 458000000, 12310, 9021, 49212, 49404, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 480p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(9, 1), 458000000, 12310, 9021, 49212, 49468, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 576i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(10, 1), 458000000, 12310, 9021, 49212, 49340, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 720p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(11, 1), 842000000, 1054, 1054, 1054, 17, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC54_1080i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(12, 1), 842000000, 1054, 1054, 1054, 16, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC54_3D_Pattern", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(13, 1), 842000000, 1054, 1054, 1054, 18, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC54_576i_Hfclrbar", 3, 2, 2, 1, TvParams.NW_MASK));
                int channelList28 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2) cable dtv, return value = ", channelList28, "\n", TAG);
                if (channelList28 != 0) {
                    a.b("setChannelList(2) cable dtv failed! return value = ", channelList28, "\n", TAG);
                    return false;
                }
                z = true;
                hisenseTvAPIBase = this;
                hisenseTvAPIBase.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                return z;
            case 2:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 57750000, 57750000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 85250000, 85250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 184250000, 184250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 215250000, 215250000, TvParams.TV_SYS_DK, 2, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 313250000, 313250000, 16384, 0, 256, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 440250000, 440250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 695250000, 695250000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 791250000, 791250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(909, 0), 1, TvParams.NW_MASK, 855250000, 855250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                int channelList29 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_ZHONG_SHI), return value = ", channelList29, "\n", TAG);
                if (channelList29 != 0) {
                    a.a("setChannelList(1)air atv for CT_ZHONG_SHI failed! return value = ", channelList29, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 538000000, 36924, 36924, 36924, 36988, 0, 0, "DT29 1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 538000000, 36924, 36924, 36924, 37052, 0, 0, "DT29 576i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 538000000, 36924, 36924, 36924, 37116, 0, 0, "DT29 576p", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 826000000, 12308, 9019, 41020, 41084, 0, 0, "DT65 576p", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(5, 1), 826000000, 12308, 9019, 41020, 41148, 0, 0, "DT65 720p", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(6, 1), 826000000, 12308, 9019, 41020, 41212, 0, 0, "DT65 576i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(7, 1), 650000000, 1043, 1043, 1043, 20, 0, 0, "DT2-43_1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(8, 1), 650000000, 1043, 1043, 1043, 21, 0, 0, "DT2-43_576i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(9, 1), 674000000, 1043, 1043, 1043, 20, 0, 0, "DT2-46_1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(10, 1), 627500000, 12307, 9018, 36924, 36988, 0, 0, "DT42_7M 1080i", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(11, 1), 627500000, 12307, 9018, 36924, 37052, 0, 0, "DT42_7M 576i", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(12, 1), 627500000, 12307, 9018, 36924, 37116, 0, 0, "DT42_7M 576p", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(13, 1), 550500000, 0, 550, 1022, 7, 0, 0, "DT31-AU_3D 1080i", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(14, 1), 550500000, 0, 550, 1022, 6, 0, 0, "DT31 576i", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(15, 1), 593000000, 13096, 8350, 28, 300, 0, 0, "民視綜合台[民視]", 1, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(16, 1), 593000000, 13096, 8350, 28, 301, 0, 0, "民視交通台[民視]", 1, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(17, 1), 593000000, 13096, 8350, 28, 302, 0, 0, "民視新聞台[民視]", 1, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(18, 1), 593000000, 13096, 8350, 28, 304, 0, 0, "民視HD台[民視]", 1, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(19, 1), 683000000, 1043, 1043, 1043, 20, 0, 0, "DT2-48-6M_1080i", 1, 2, 1, 1, TvParams.NW_MASK));
                int channelList30 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air dtv(CT_ZHONG_SHI), return value = ", channelList30, "\n", TAG);
                if (channelList30 != 0) {
                    a.b("setChannelList(1)air dtv for CT_ZHONG_SHI failed! return value = ", channelList30, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 57750000, 57750000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 85250000, 85250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 184250000, 184250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 215250000, 215250000, TvParams.TV_SYS_DK, 2, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 313250000, 313250000, 16384, 0, 256, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 440250000, 440250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 695250000, 695250000, 256, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 791250000, 791250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(909, 0), 1, TvParams.NW_MASK, 855250000, 855250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                int channelList31 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable atv(CT_ZHONG_SHI), return value = ", channelList31, "\n", TAG);
                if (channelList31 != 0) {
                    a.a("setChannelList(2)cable atv for CT_ZHONG_SHI failed! return value = ", channelList31, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 129000000, 12309, 9020, 45116, 45180, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 1080i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 129000000, 12309, 9020, 45116, 45244, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 480p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 129000000, 12309, 9020, 45116, 45308, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 576i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 129000000, 12309, 9020, 45116, 45372, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 576p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(5, 1), 129000000, 12309, 9020, 45116, 47164, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(6, 1), 458000000, 12310, 9021, 49212, 49276, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(7, 1), 458000000, 12310, 9021, 49212, 51260, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(8, 1), 458000000, 12310, 9021, 49212, 49404, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 480p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(9, 1), 458000000, 12310, 9021, 49212, 49468, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 576i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(10, 1), 458000000, 12310, 9021, 49212, 49340, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 720p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(11, 1), 842000000, 1054, 1054, 1054, 17, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC54_1080i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(12, 1), 842000000, 1054, 1054, 1054, 16, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC54_3D_Pattern", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(13, 1), 842000000, 1054, 1054, 1054, 18, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC54_576i_Hfclrbar", 3, 2, 2, 1, TvParams.NW_MASK));
                int channelList32 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable dtv(CT_ZHONG_SHI), return value = ", channelList32, "\n", TAG);
                if (channelList32 != 0) {
                    a.b("setChannelList(2)cable dtv for CT_ZHONG_SHI failed! return value = ", channelList32, "\n", TAG);
                    return false;
                }
                z = true;
                hisenseTvAPIBase = this;
                hisenseTvAPIBase.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                return z;
            case 3:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 49750000, 49750000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 85250000, 85250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 160250000, 160250000, 16384, 0, 256, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 184250000, 184250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 200250000, 200250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 216250000, 216250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 288250000, 288250000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 607250000, 607250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(909, 0), 1, TvParams.NW_MASK, 863250000, 863250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                int channelList33 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_GUI_YANG), return value = ", channelList33, "\n", TAG);
                if (channelList33 != 0) {
                    a.b("setChannelList(1)air atv for CT_GUI_YANG failed! return value = ", channelList33, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 49750000, 49750000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 85250000, 85250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 160250000, 160250000, 16384, 0, 256, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 184250000, 184250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 200250000, 200250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 216250000, 216250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 288250000, 288250000, 256, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 607250000, 607250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(909, 0), 1, TvParams.NW_MASK, 863250000, 863250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                int channelList34 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable atv(CT_GUI_YANG), return value = ", channelList34, "\n", TAG);
                if (channelList34 != 0) {
                    a.b("setChannelList(2)cable atv for CT_GUI_YANG failed! return value = ", channelList34, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 129000000, 12309, 9020, 45116, 45180, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 1080i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 129000000, 12309, 9020, 45116, 45244, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 480p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 129000000, 12309, 9020, 45116, 45308, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 576i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 129000000, 12309, 9020, 45116, 45372, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 576p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(5, 1), 129000000, 12309, 9020, 45116, 47164, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                int channelList35 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable dtv(CT_GUI_YANG), return value = ", channelList35, "\n", TAG);
                if (channelList35 != 0) {
                    a.b("setChannelList(2)cable dtv for CT_GUI_YANG failed, return value = ", channelList35, "\n", TAG);
                    return false;
                }
                z = true;
                hisenseTvAPIBase = this;
                hisenseTvAPIBase.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                return z;
            case 4:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 49750000, 49750000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 184250000, 184250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 196255000, 196255000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 205250000, 205250000, 16384, 0, 256, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 215250000, 215250000, 66, 2, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 224250000, 224250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 440250000, 440250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(909, 0), 1, TvParams.NW_MASK, 575250000, 575250000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(910, 0), 1, TvParams.NW_MASK, 711250000, 711250000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(911, 0), 1, TvParams.NW_MASK, 777250000, 777250000, 16384, 0, 256, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(912, 0), 1, TvParams.NW_MASK, 855250000, 855250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                int channelList36 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1) atv(CT_JIANG_MEN), return value = ", channelList36, "\n", TAG);
                if (channelList36 != 0) {
                    a.b("setChannelList(1) atv for CT_JIANG_MEN failed! return value = ", channelList36, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 538000000, 12307, 9018, 36924, 36988, 0, 0, "DT29 1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 538000000, 12307, 9018, 36924, 37052, 0, 0, "DT29 576i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 538000000, 12307, 9018, 36924, 37116, 0, 0, "DT29 576p", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 826000000, 12308, 9019, 41020, 41084, 0, 0, "DT65 576p", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(5, 1), 826000000, 12308, 9019, 41020, 41212, 0, 0, "DT65 576i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(6, 1), 826000000, 12308, 9019, 41020, 41148, 0, 0, "DT65 720p", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(7, 1), 627500000, 36924, 36924, 36924, 36988, 0, 0, "DT42-7M 1080i", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(8, 1), 627500000, 36924, 36924, 36924, 37052, 0, 0, "DT42-7M 576i", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(9, 1), 627500000, 36924, 36924, 36924, 37116, 0, 0, "DT42-7M 576p", 2, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(10, 1), 650000000, 1043, 1043, 1043, 20, 0, 0, "DT2-43_1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(11, 1), 683000000, 1043, 1043, 1043, 20, 0, 0, "DT2-48-6M_1080i", 1, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(12, 1), 674000000, 1043, 1043, 1043, 20, 0, 0, "DT2-46_1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                int channelList37 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1) dtv(CT_JIANG_MEN), return value = ", channelList37, "\n", TAG);
                if (channelList37 != 0) {
                    a.b("setChannelList(1) dtv  for CT_JIANG_MEN failed! return value = ", channelList37, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 842000000, 64512, 3, 100, 6, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "Advertising FTA", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 458000000, 12310, 9021, 49212, 49276, 6875000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 458000000, 12310, 9021, 49212, 51260, 6875000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 458000000, 12310, 9021, 49212, 49404, 6875000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 480p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(5, 1), 458000000, 12310, 9021, 49212, 49468, 6875000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 576i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(6, 1), 458000000, 12310, 9021, 49212, 49340, 6875000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC40 720p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(7, 1), 842000000, 64512, 3, 100, 4, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "Moviz SC", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(8, 1), 842000000, 64512, 3, 100, 2, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "Natura SC", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(9, 1), 842000000, 64512, 3, 100, 1, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "Neotion FTA 1", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(10, 1), 842000000, 64512, 3, 100, 3, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "Neotion FTA 2", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(11, 1), 842000000, 64512, 3, 100, 5, 6900000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "Neotion FTA 3 HD", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(12, 1), 129000000, 12309, 9020, 45116, 45180, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 1080i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(13, 1), 129000000, 12309, 9020, 45116, 45244, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 480p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(14, 1), 129000000, 12309, 9020, 45116, 45308, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 576i", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(15, 1), 129000000, 12309, 9020, 45116, 45372, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 576p", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(16, 1), 129000000, 12309, 9020, 45116, 47164, 6901000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "DC04 480i", 3, 2, 2, 1, TvParams.NW_MASK));
                int channelList38 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2) dtv(CT_JIANG_MEN), return value = ", channelList38, "\n", TAG);
                if (channelList38 != 0) {
                    a.b("setChannelList(2) dtv for CT_JIANG_MEN failed! return value = ", channelList38, "\n", TAG);
                    return false;
                }
                z = true;
                hisenseTvAPIBase = this;
                hisenseTvAPIBase.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                return z;
            case 5:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 55250000, 55250000, 66, 2, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 175250000, 175250000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 223250000, 223250000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 487250000, 487250000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 519250000, 519250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 583250000, 583250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 705250000, 705250000, 16384, 0, 256, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 751250000, 751250000, 256, 1, 130, 4, 0, null));
                int channelList39 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_SOUTHAFRICA), return value = ", channelList39, "\n", TAG);
                if (channelList39 != 0) {
                    a.b("setChannelList(1)air atv(CT_SOUTHAFRICA) failed, return value = ", channelList39, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 474000000, 1043, 1043, 1043, 20, 0, 0, "DT2-43_1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 474000000, 1043, 1043, 1043, 21, 0, 0, "DT2-43_576i", 3, 2, 1, 1, TvParams.NW_MASK));
                int channelList40 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air dtv(CT_SOUTHAFRICA-DVBT), return value = ", channelList40, "\n", TAG);
                if (channelList40 != 0) {
                    a.a("setChannelList(1)air dtv(CT_SOUTHAFRICA-DVBT) failed! return value = ", channelList40, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 55250000, 55250000, 66, 2, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 175250000, 175250000, 256, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 223250000, 223250000, 256, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 487250000, 487250000, 256, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 519250000, 519250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 583250000, 583250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 705250000, 705250000, 16384, 0, 256, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 751250000, 751250000, 256, 1, 130, 5, 0, null));
                int channelList41 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable atv(CT_SOUTHAFRICA), return value = ", channelList41, "\n", TAG);
                if (channelList41 != 0) {
                    a.b("setChannelList(2)cable atv(CT_SOUTHAFRICA) failed, return value = ", channelList41, "\n", TAG);
                    return false;
                }
                z2 = true;
                hisenseTvAPIBase2 = this;
                hisenseTvAPIBase2.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                return z2;
            case 6:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 48250000, 48250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 182250000, 182250000, 66, 1, 130, 4, 0, null));
                int channelList42 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_ALGERIA), return value = ", channelList42, "\n", TAG);
                if (channelList42 != 0) {
                    a.b("setChannelList(1)air atv(CT_ALGERIA) failed, return value = ", channelList42, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 586000000, 2048, 2048, 53, 5305, 0, 0, "beIN SPORTS NEWS HD", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 546000000, 2048, 2048, 20, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET_SB_KEY_TYPE_SAVE_ORBIT_INFO, 0, 0, "Sky News Arabia HD", 3, 2, 1, 1, TvParams.NW_MASK));
                int channelList43 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1) dtv(CT_JIANG_MEN), return value = ", channelList43, "\n", TAG);
                if (channelList43 != 0) {
                    a.b("setChannelList(1) dtv  for CT_JIANG_MEN failed! return value = ", channelList43, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 48250000, 48250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 182250000, 182250000, 66, 1, 130, 5, 0, null));
                int channelList44 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable atv(CT_ALGERIA), return value = ", channelList44, "\n", TAG);
                if (channelList44 != 0) {
                    a.b("setChannelList(2)cable atv(CT_ALGERIA) failed, return value = ", channelList44, "\n", TAG);
                    return false;
                }
                z = true;
                setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                hisenseTvAPIBase = this;
                return z;
            case 7:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 61250000, 61250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 175250000, 175250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 189250000, 189250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 575250000, 575250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 631250000, 631250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 203250000, 203250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 216200000, 216200000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 639200000, 639200000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(909, 0), 1, TvParams.NW_MASK, 687200000, 687200000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(910, 0), 1, TvParams.NW_MASK, 727200000, 727200000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                int channelList45 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_MALAYSIA), return value = ", channelList45, "\n", TAG);
                if (channelList45 != 0) {
                    a.b("setChannelList(1)air atv(CT_MALAYSIA) failed, return value = ", channelList45, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 61250000, 61250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 175250000, 175250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 189250000, 189250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 575250000, 575250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 631250000, 631250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 203250000, 203250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 216200000, 216200000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 639200000, 639200000, 256, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(909, 0), 1, TvParams.NW_MASK, 687200000, 687200000, 256, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(910, 0), 1, TvParams.NW_MASK, 727200000, 727200000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                int channelList46 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable atv(CT_MALAYSIA), return value = ", channelList46, "\n", TAG);
                if (channelList46 != 0) {
                    a.b("setChannelList(2)cable atv(CT_MALAYSIA) failed, return value = ", channelList46, "\n", TAG);
                    return false;
                }
                z = true;
                hisenseTvAPIBase = this;
                hisenseTvAPIBase.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                return z;
            case 8:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 62250000, 62250000, 66, 3, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 189250000, 189250000, 66, 0, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 217250000, 217250000, 66, 2, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 487250000, 487250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 495250000, 495250000, 16384, 0, 256, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 519250000, 519250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 543250000, 543250000, 256, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 559250000, 559250000, 66, 1, 130, 4, 0, null));
                int channelList47 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_IRAN), return value = ", channelList47, "\n", TAG);
                if (channelList47 != 0) {
                    a.b("setChannelList(1)air atv(CT_IRAN) failed, return value = ", channelList47, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 62250000, 62250000, 66, 3, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 189250000, 189250000, 66, 0, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 217250000, 217250000, 66, 2, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 487250000, 487250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 495250000, 495250000, 16384, 0, 256, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 519250000, 519250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 543250000, 543250000, 256, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 559250000, 559250000, 66, 1, 130, 5, 0, null));
                int channelList48 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable atv(CT_IRAN), return value = ", channelList48, "\n", TAG);
                if (channelList48 != 0) {
                    a.b("setChannelList(2)cable atv(CT_IRAN) failed, return value = ", channelList48, "\n", TAG);
                    return false;
                }
                z2 = true;
                setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                hisenseTvAPIBase2 = this;
                return z2;
            case 9:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 44250000, 44250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 62250000, 62250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 182250000, 182250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 224250000, 224250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 494250000, 494250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 527250000, 527250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 624250000, 624250000, TvParams.TV_SYS_DK, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 745250000, 745250000, 16384, 0, 256, 4, 0, null));
                int channelList49 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_INDONESIA), return value = ", channelList49, "\n", TAG);
                if (channelList49 != 0) {
                    a.a("setChannelList(1)air atv for CT_INDONESIA failed! return value = ", channelList49, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 530000000, 0, 0, 1, 9, 0, 0, "INSPIRATV", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 530000000, 0, 0, 1, 11, 0, 0, "NET. HD", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 530000000, 0, 0, 1, 8, 0, 0, "Nusantara TV", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 530000000, 0, 0, 1, 12, 0, 0, "TVKU Digital", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(5, 1), 530000000, 0, 0, 1, 3, 0, 0, "TVRI 3 BUDAYA", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(6, 1), 530000000, 0, 0, 1, 4, 0, 0, "TVRI 4 OLAHRAGA", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(7, 1), 530000000, 0, 0, 1, 2, 0, 0, "TVRI Jateng", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(8, 1), 530000000, 0, 0, 1, 1, 0, 0, "TVRI Nasional", 3, 2, 1, 1, TvParams.NW_MASK));
                int channelList50 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air dtv(CT_INDONESIA), return value = ", channelList50, "\n", TAG);
                if (channelList50 != 0) {
                    a.b("setChannelList(1)air dtv for CT_INDONESIA failed! return value = ", channelList50, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 44250000, 44250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 62250000, 62250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 182250000, 182250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 224250000, 224250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 494250000, 494250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 527250000, 527250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 624250000, 624250000, TvParams.TV_SYS_DK, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(908, 0), 1, TvParams.NW_MASK, 745250000, 745250000, 16384, 0, 256, 5, 0, null));
                int channelList51 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable atv(CT_INDONESIA), return value = ", channelList51, "\n", TAG);
                if (channelList51 != 0) {
                    a.a("setChannelList(2)cable atv for CT_INDONESIA failed! return value = ", channelList51, "\n", TAG);
                    return false;
                }
                z2 = true;
                hisenseTvAPIBase2 = this;
                hisenseTvAPIBase2.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                return z2;
            case 10:
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 119250000, 119250000, 66, 1, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 360250000, 360250000, TvParams.TV_SYS_DK, 2, 130, 4, 0, null));
                arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 615250000, 615250000, 256, 1, 130, 4, 0, null));
                int channelList52 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air atv(CT_RUSSIA), return value = ", channelList52, "\n", TAG);
                if (channelList52 != 0) {
                    a.a("setChannelList(1)air atv for CT_RUSSIA failed! return value = ", channelList52, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 658000000, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, 1999, 1001, 0, 0, "Codec 16:9", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 658000000, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, 1999, 1, 0, 0, "Flower", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 658000000, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, 1999, 2, 0, 0, "Horse", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 594000000, 12293, 9018, 16516, 17472, 0, 0, "BBC HD", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(5, 1), 658000000, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, MtkTvScanDvbsBase.EXCHANGE_TYPE_SB_GET, 1999, 3, 0, 0, "Tandberg HD", 3, 2, 1, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(6, 1), 594000000, 12293, 9018, 16516, 17604, 0, 0, "ITV1 HD", 3, 2, 1, 1, TvParams.NW_MASK));
                int channelList53 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(1)air dtv(CT_RUSSIA), return value = ", channelList53, "\n", TAG);
                if (channelList53 != 0) {
                    a.b("setChannelList(1)air dtv for CT_RUSSIA failed! return value = ", channelList53, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 119250000, 119250000, 66, 1, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 360250000, 360250000, TvParams.TV_SYS_DK, 2, 130, 5, 0, null));
                arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 615250000, 615250000, 256, 1, 130, 5, 0, null));
                int channelList54 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable atv(CT_RUSSIA), return value = ", channelList54, "\n", TAG);
                if (channelList54 != 0) {
                    a.a("setChannelList(2)cable atv for CT_RUSSIA failed! return value = ", channelList54, "\n", TAG);
                    return false;
                }
                arrayList.clear();
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 262273, 274006000, 1000, 1000, 1, 3618000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "AVC tes", 3, 2, 2, 1, TvParams.NW_MASK));
                arrayList.add(AddDvbChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 524417, 274006000, 1000, 1000, 2, 3618000, MtkTvChCommonBase.TunerModulation.MOD_QAM_64.ordinal(), "Dummy", 3, 2, 2, 1, TvParams.NW_MASK));
                int channelList55 = mtkTvChannelListBase.setChannelList(0, arrayList);
                a.a("setChannelList(2)cable dtv(CT_RUSSIA), return value = ", channelList55, "\n", TAG);
                if (channelList55 != 0) {
                    a.b("setChannelList(2)cable dtv for CT_RUSSIA failed! return value = ", channelList55, "\n", TAG);
                    return false;
                }
                z = true;
                hisenseTvAPIBase = this;
                hisenseTvAPIBase.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                return z;
            default:
                switch (i) {
                    case 16:
                        arrayList.clear();
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 45250000, 45250000, 66, 1, 130, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 175250000, 175250000, 66, 1, 130, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 575250000, 575250000, 66, 1, 130, 4, 0, null));
                        int channelList56 = mtkTvChannelListBase.setChannelList(0, arrayList);
                        a.a("setChannelList(1)air atv(CT_NIGERIA), return value = ", channelList56, "\n", TAG);
                        if (channelList56 != 0) {
                            a.b("setChannelList(1)air atv(CT_NIGERIA) failed, return value = ", channelList56, "\n", TAG);
                            return false;
                        }
                        arrayList.clear();
                        arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(30, 1), 650000000, 1043, 1043, 1043, 20, 0, 0, "DT2-43_1080i", 3, 2, 1, 1, TvParams.NW_MASK));
                        arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(31, 1), 650000000, 1043, 1043, 1043, 21, 0, 0, "DT2-43_576i", 3, 2, 1, 1, TvParams.NW_MASK));
                        int channelList57 = mtkTvChannelListBase.setChannelList(0, arrayList);
                        a.a("setChannelList(1) dtv(CT_NIGERIA-DVBT), return value = ", channelList57, "\n", TAG);
                        if (channelList57 != 0) {
                            a.a("setChannelList(1) dtv(CT_NIGERIA-DVBT) failed! return value = ", channelList57, "\n", TAG);
                            return false;
                        }
                        arrayList.clear();
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 45250000, 45250000, 66, 1, 130, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 175250000, 175250000, 66, 1, 130, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 575250000, 575250000, 66, 1, 130, 5, 0, null));
                        int channelList58 = mtkTvChannelListBase.setChannelList(0, arrayList);
                        a.a("setChannelList(2)cable atv(CT_NIGERIA), return value = ", channelList58, "\n", TAG);
                        if (channelList58 == 0) {
                            setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                            return true;
                        }
                        a.a("setChannelList(2)cable atv for CT_NIGERIA failed! return value = ", channelList58, "\n", TAG);
                        return false;
                    case 17:
                        arrayList.clear();
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 62250000, 62250000, 66, 1, 130, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 182680000, 182680000, 66, 1, 130, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 719250000, 719250000, 66, 1, 130, 4, 0, null));
                        int channelList59 = mtkTvChannelListBase.setChannelList(0, arrayList);
                        a.a("setChannelList(1)air atv(CT_PAKISTAN), return value = ", channelList59, "\n", TAG);
                        if (channelList59 != 0) {
                            a.a("setChannelList(1)air atv for CT_PAKISTAN failed! return value = ", channelList59, "\n", TAG);
                            return false;
                        }
                        arrayList.clear();
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 62250000, 62250000, 66, 1, 130, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 182680000, 182680000, 66, 1, 130, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 719250000, 719250000, 66, 1, 130, 5, 0, null));
                        int channelList60 = mtkTvChannelListBase.setChannelList(0, arrayList);
                        a.a("setChannelList(2)cable atv(CT_PAKISTAN), return value = ", channelList60, "\n", TAG);
                        if (channelList60 != 0) {
                            a.a("setChannelList(2)cable atv for CT_PAKISTAN failed! return value = ", channelList60, "\n", TAG);
                            return false;
                        }
                        z = true;
                        hisenseTvAPIBase = this;
                        hisenseTvAPIBase.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                        return z;
                    case 18:
                        arrayList.clear();
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 48250000, 48250000, 66, 1, 130, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 59250000, 59250000, TvParams.TV_SYS_DK, 2, 130, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 189250000, 189250000, 16384, 0, 256, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 294250000, 294250000, 66, 1, 130, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 655250000, 655250000, 256, 1, 130, 4, 0, null));
                        arrayList.add(AddAnalogChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 847250000, 847250000, 256, 1, 130, 4, 0, null));
                        int channelList61 = mtkTvChannelListBase.setChannelList(0, arrayList);
                        a.a("setChannelList(1)air atv(CT_EGYPT_TSB), return value = ", channelList61, "\n", TAG);
                        if (channelList61 != 0) {
                            a.a("setChannelList(1)air atv for CT_EGYPT_TSB failed! return value = ", channelList61, "\n", TAG);
                            return false;
                        }
                        arrayList.clear();
                        arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(1, 1), 810000000, 12307, 9018, 16384, 16960, 0, 0, "Color Bar", 3, 2, 1, 1, TvParams.NW_MASK));
                        arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(2, 1), 602000000, 12289, 9018, 768, 12544, 0, 0, "SDTV1", 3, 2, 1, 1, TvParams.NW_MASK));
                        arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(3, 1), 810000000, 12307, 9018, 16384, 16832, 0, 0, "Monoscope", 3, 2, 1, 1, TvParams.NW_MASK));
                        arrayList.add(AddDvbChannelInfo(1, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(4, 1), 810000000, 12307, 9018, 16384, 16768, 0, 0, "Moving Picture", 3, 2, 1, 1, TvParams.NW_MASK));
                        int channelList62 = mtkTvChannelListBase.setChannelList(0, arrayList);
                        a.a("setChannelList(1) dtv(CT_EGYPT_TSB-DVBT), return value = ", channelList62, "\n", TAG);
                        if (channelList62 != 0) {
                            a.a("setChannelList(1) dtv(CT_EGYPT_TSB-DVBT) failed! return value = ", channelList62, "\n", TAG);
                            return false;
                        }
                        arrayList.clear();
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(901, 0), 1, TvParams.NW_MASK, 48250000, 48250000, 66, 1, 130, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(902, 0), 1, TvParams.NW_MASK, 59250000, 59250000, TvParams.TV_SYS_DK, 2, 130, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(903, 0), 1, TvParams.NW_MASK, 83250000, 83250000, 16384, 0, 256, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(904, 0), 1, TvParams.NW_MASK, 189250000, 189250000, 66, 0, 130, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(905, 0), 1, TvParams.NW_MASK, 294250000, 294250000, 66, 1, 130, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(906, 0), 1, TvParams.NW_MASK, 655250000, 655250000, 256, 1, 130, 5, 0, null));
                        arrayList.add(AddAnalogChannelInfo(2, 0, DvbChannelUtil.SB_DVB_GEN_CHANNEL_ID(907, 0), 1, TvParams.NW_MASK, 847250000, 847250000, 256, 1, 130, 5, 0, null));
                        int channelList63 = mtkTvChannelListBase.setChannelList(0, arrayList);
                        a.a("setChannelList(2)cable atv(CT_EGYPT_TSB), return value = ", channelList63, "\n", TAG);
                        if (channelList63 != 0) {
                            a.a("setChannelList(2)cable atv for CT_EGYPT_TSB failed! return value = ", channelList63, "\n", TAG);
                            return false;
                        }
                        z2 = true;
                        hisenseTvAPIBase2 = this;
                        hisenseTvAPIBase2.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE_PRESET, 1);
                        return z2;
                    default:
                        Log.d(TAG, "presetChannelList function factory not supported for EM \n");
                        return false;
                }
        }
    }

    public int removeFile(String str) {
        return HisensePlatformConfig.removeFile(str);
    }

    public int resetConfigValues(int i) {
        int iResetConfigValues_native;
        synchronized (HisenseTvAPIBase.class) {
            iResetConfigValues_native = HisenseTVNativeWrapper.resetConfigValues_native(i);
        }
        return iResetConfigValues_native;
    }

    public boolean resetFactoryUnprotectedSetting() {
        AvMiddleWareManager avMiddleWareManager = this.mAvMiddleWareManager;
        return AvMiddleWareManager.resetFactoryUnprotectedSetting() == 0;
    }

    public boolean resetGraphCorrection() {
        a.c("[laser]resetGraphCorrection = ", 61680, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_RESET_GRAPH_CORRECTION, 61680) == 0;
    }

    public int resetWhteBalance() {
        return HisVideoApi.resetAllFac2PWhiteBalance();
    }

    public boolean saveOffsTime(int i) {
        return i >= 0 && setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOTAL_OFFSTIME, i) == 0;
    }

    public boolean saveTotalJbTime(int i) {
        return i >= 0 && setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOTAL_JBTIME, i) == 0;
    }

    public int set2PWBGainBlueDelta(int i) {
        Log.d(TAG, "set2PWBGainBlueDelta val:" + i);
        return HisVideoApi.set2PWBGainBlueDelta(i) == 0 ? 0 : -1;
    }

    public int set2PWBGainGreenDelta(int i) {
        Log.d(TAG, "set2PWBGainGreenDelta val:" + i);
        return HisVideoApi.set2PWBGainGreenDelta(i) == 0 ? 0 : -1;
    }

    public int set2PWBGainRedDelta(int i) {
        Log.d(TAG, "set2PWBGainRedDelta val:" + i);
        return HisVideoApi.set2PWBGainRedDelta(i) == 0 ? 0 : -1;
    }

    public int set2PWBOffsetBlueDelta(int i) {
        Log.d(TAG, "set2PWBOffsetBlueDelta val:" + i);
        return HisVideoApi.set2PWBOffsetBlueDelta(i) == 0 ? 0 : -1;
    }

    public int set2PWBOffsetGreenDelta(int i) {
        Log.d(TAG, "set2PWBOffsetGreenDelta val:" + i);
        return HisVideoApi.set2PWBOffsetGreenDelta(i) == 0 ? 0 : -1;
    }

    public int set2PWBOffsetRedDelta(int i) {
        Log.d(TAG, "set2PWBOffsetRedDelta val:" + i);
        return HisVideoApi.set2PWBOffsetRedDelta(i) == 0 ? 0 : -1;
    }

    public boolean setAVCEnableValue(String str, int i) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setAVCFeature(0, i) == 0;
    }

    public boolean setAVCThreshold(int i) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setAVCFeature(1, i) == 0;
    }

    public boolean setAbortPowerTimer(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_TIMER_ABORT_TIMER_OFF, i) == 0;
    }

    public int setActiveContrast(int i) {
        return HisVideoApi.setActiveContrast(i);
    }

    public boolean setAgingMode(boolean z) {
        a.a("setAgingMode relValue is ", setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_AGING_MODE, z ? 1 : 0), "\n", TAG);
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        int agingModeEnable = HisVideoApi.setAgingModeEnable(z ? 1 : 0);
        a.a("setAgingModeEnable relValue is ", agingModeEnable, "\n", TAG);
        if (agingModeEnable == 0) {
            Settings.System.putInt(this.mContext.getContentResolver(), SETTING_TV_AGING_MODE, z ? 1 : 0);
        }
        return agingModeEnable == 0;
    }

    public int setAspectRatio(int i) {
        return HisVideoApi.setAspectRatio(i);
    }

    public boolean setAudHpfStatus(boolean z) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setSoundSocHPFFeature(0, z ? 1 : 0) == 0;
    }

    public boolean setAudPeqEnableStatus(boolean z) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setSoundSocPEQEnable(z ? 1 : 0) == 0;
    }

    public boolean setAudioCurveDetailValue(String str, int i) {
        int soundVolCurveVal;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MIN)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            soundVolCurveVal = HisAudioApi.setSoundVolCurveVal(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_20)) {
            HisAudioApi hisAudioApi2 = this.mHisAudioApi;
            soundVolCurveVal = HisAudioApi.setSoundVolCurveVal(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MID)) {
            HisAudioApi hisAudioApi3 = this.mHisAudioApi;
            soundVolCurveVal = HisAudioApi.setSoundVolCurveVal(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_75)) {
            HisAudioApi hisAudioApi4 = this.mHisAudioApi;
            soundVolCurveVal = HisAudioApi.setSoundVolCurveVal(3, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MAX)) {
            HisAudioApi hisAudioApi5 = this.mHisAudioApi;
            soundVolCurveVal = HisAudioApi.setSoundVolCurveVal(4, i);
        } else {
            soundVolCurveVal = 0;
        }
        return soundVolCurveVal == 0;
    }

    public boolean setAudioCurveDetailValueNew(int i, int i2) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setSoundVolCurveVal(i, i2) == 0;
    }

    public boolean setAudioMode(String str, int i) {
        int soundMode;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOUND_MODE)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            soundMode = HisAudioApi.setSoundMode(i);
        } else {
            soundMode = 0;
        }
        return soundMode == 0;
    }

    public boolean setAudioModeDetailValue(String str, int i) {
        int geq;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_1)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            geq = HisAudioApi.setGEQ(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_2)) {
            HisAudioApi hisAudioApi2 = this.mHisAudioApi;
            geq = HisAudioApi.setGEQ(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_3)) {
            HisAudioApi hisAudioApi3 = this.mHisAudioApi;
            geq = HisAudioApi.setGEQ(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_4)) {
            HisAudioApi hisAudioApi4 = this.mHisAudioApi;
            geq = HisAudioApi.setGEQ(3, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_5)) {
            HisAudioApi hisAudioApi5 = this.mHisAudioApi;
            geq = HisAudioApi.setGEQ(4, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_6)) {
            HisAudioApi hisAudioApi6 = this.mHisAudioApi;
            geq = HisAudioApi.setGEQ(5, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_7)) {
            HisAudioApi hisAudioApi7 = this.mHisAudioApi;
            geq = HisAudioApi.setGEQ(6, i);
        } else {
            geq = 0;
        }
        return geq == 0;
    }

    public boolean setAudioOutputPort(String str, int i) {
        int ampPrescaler;
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_AMP_OUTPUT_PORT)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            ampPrescaler = HisAudioApi.setAmpPrescaler(i);
        } else {
            ampPrescaler = 0;
        }
        return ampPrescaler == 0;
    }

    public boolean setAudioPeq(String str, int i) {
        int soundSocHPFFeature;
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_FREQ)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_FREQ)) {
            HisAudioApi hisAudioApi2 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_FREQ)) {
            HisAudioApi hisAudioApi3 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_FREQ)) {
            HisAudioApi hisAudioApi4 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(3, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_FREQ)) {
            HisAudioApi hisAudioApi5 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(4, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_FREQ)) {
            HisAudioApi hisAudioApi6 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(5, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_FREQ)) {
            HisAudioApi hisAudioApi7 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(6, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_FREQ)) {
            HisAudioApi hisAudioApi8 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(7, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_GAIN)) {
            HisAudioApi hisAudioApi9 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_GAIN)) {
            HisAudioApi hisAudioApi10 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_GAIN)) {
            HisAudioApi hisAudioApi11 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_GAIN)) {
            HisAudioApi hisAudioApi12 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(3, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_GAIN)) {
            HisAudioApi hisAudioApi13 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(4, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_GAIN)) {
            HisAudioApi hisAudioApi14 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(5, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_GAIN)) {
            HisAudioApi hisAudioApi15 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(6, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_GAIN)) {
            HisAudioApi hisAudioApi16 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(7, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_Q)) {
            HisAudioApi hisAudioApi17 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_Q)) {
            HisAudioApi hisAudioApi18 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_Q)) {
            HisAudioApi hisAudioApi19 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_Q)) {
            HisAudioApi hisAudioApi20 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(3, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_Q)) {
            HisAudioApi hisAudioApi21 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(4, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_Q)) {
            HisAudioApi hisAudioApi22 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(5, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_Q)) {
            HisAudioApi hisAudioApi23 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(6, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_Q)) {
            HisAudioApi hisAudioApi24 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(7, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_HPF_FREQUENCY)) {
            HisAudioApi hisAudioApi25 = this.mHisAudioApi;
            soundSocHPFFeature = HisAudioApi.setSoundSocHPFFeature(1, i);
        } else {
            soundSocHPFFeature = 0;
        }
        return soundSocHPFFeature == 0;
    }

    public int setAutoGammaEnable(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setAutoGammaEnable(i);
    }

    public void setAutoGammaStartTunning(int i) {
        HisVideoApi.setAutoGammaTuning(i);
    }

    public boolean setB2BSwitch(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_B2B_SWITCH, i) == 0;
    }

    public int setBackgroundNoiseValue(int i) {
        a.c("[laser]setBackgroundNoiseValue, value is ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_BACKGROUND_NOISE_VALUE, i);
    }

    public boolean setBacklightModeDetailValue(String str, int i) {
        int backlightMax;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MIN)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            backlightMax = HisVideoApi.setBacklightMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_20)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            backlightMax = HisVideoApi.setBacklight20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MID)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            backlightMax = HisVideoApi.setBacklightMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_80)) {
            HisVideoApi hisVideoApi4 = this.mHisVideoApi;
            backlightMax = HisVideoApi.setBacklight80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MAX)) {
            HisVideoApi hisVideoApi5 = this.mHisVideoApi;
            backlightMax = HisVideoApi.setBacklightMax(i);
        } else {
            backlightMax = 0;
        }
        return backlightMax == 0;
    }

    public boolean setBalance(int i) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setBalance(i) == 0;
    }

    public int setBlueValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_BLUE_VALUE, i);
    }

    public boolean setBootUpLogo(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public boolean setByPassValue(String str, int i) {
        int bypassXvyccEnable;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PQ_BYPASS)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            bypassXvyccEnable = HisVideoApi.setBypassPqEnable(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAMMA_BYPASS)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            bypassXvyccEnable = HisVideoApi.setBypassGammaEnable(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SCE_BYPASS)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            bypassXvyccEnable = HisVideoApi.setBypassSceEnable(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_XVYCC_BYPASS)) {
            HisVideoApi hisVideoApi4 = this.mHisVideoApi;
            bypassXvyccEnable = HisVideoApi.setBypassXvyccEnable(i);
        } else {
            bypassXvyccEnable = 0;
        }
        return bypassXvyccEnable == 0;
    }

    public boolean setCTBrightness(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setFactoryColorTunerBrightness(i) == 0;
    }

    public boolean setCTHue(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setFactoryColorTunerHue(i) == 0;
    }

    public boolean setCTSaturation(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setFactoryColorTunerSaturation(i) == 0;
    }

    public int setCalibrationOfBackgroundNoise() {
        Log.v(TAG, "[laser]setCalibrationOfBackgroundNoise");
        return -1;
    }

    public int setCleanStartUpTime(int i) {
        a.c("[Laser] setCleanStartUpTime = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLEAN_STARTUP_TIME, i);
    }

    public boolean setCoaxialSwitchStatus(String str, int i) {
        a.c("[laser]setCoaxialSwitchStatus = ", i, "\n", TAG);
        return setConfigValue(str, i) == 0;
    }

    public boolean setColorTemperature(String str, int i) {
        int factoryColorTemperature;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_COLOR_TEMP)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            factoryColorTemperature = HisVideoApi.setFactoryColorTemperature(i);
        } else {
            factoryColorTemperature = 0;
        }
        return factoryColorTemperature == 0;
    }

    public boolean setColorTuner(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setFactoryColorTunerColor(i) == 0;
    }

    public int setConfigIntArrayValue(String str, int[] iArr, int i) {
        int configIntArrayValue_native;
        synchronized (HisenseTvAPIBase.class) {
            configIntArrayValue_native = HisenseTVNativeWrapper.setConfigIntArrayValue_native(str, iArr, i);
        }
        return configIntArrayValue_native;
    }

    public int setConfigString(String str, String str2) {
        int configString_native;
        synchronized (HisenseTvAPIBase.class) {
            configString_native = HisenseTVNativeWrapper.setConfigString_native(str, str2);
        }
        return configString_native;
    }

    public int setConfigValue(String str, int i) {
        return setConfigValue(-1, str, i, 3);
    }

    public int setCurrentSource(int i) {
        AvMiddleWareManager avMiddleWareManager = this.mAvMiddleWareManager;
        return AvMiddleWareManager.setCurrentSource(i);
    }

    public int setCurrentVirtualSource(int i) {
        AvMiddleWareManager avMiddleWareManager = this.mAvMiddleWareManager;
        return AvMiddleWareManager.setCurrentVirtualSource(i);
    }

    public boolean setCustomerTypeValue(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_CUSTOMER_TYPE, str) == 0;
    }

    public void setDBX(int i) {
        setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_DBX_FOR_FAC, i);
    }

    public boolean setDTSSwitch(int i) {
        Log.d(TAG, "set DTSSwitch : value = " + i);
        return setConfigValue(MtkTvConfigTypeBase.CFG_FAC_AUD_FACTORY_MODE, i) == 0;
    }

    public boolean setDaylightValue(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public boolean setDdrDetailValue(String str, int i) {
        return true;
    }

    public boolean setDdrMode(boolean z) {
        return true;
    }

    public int setDdrSpan(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_STEP, i) == 0 ? 0 : -1;
    }

    public int setDdrSscEnable(boolean z) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_ONOFF, z ? 1 : 0) == 0 ? 0 : -1;
    }

    public boolean setDedustMode(int i) {
        a.c("[laser]setDedustMode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_DEDUST_MODE, i) == 0;
    }

    public boolean setDemuraFlagValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA, i) == 0;
    }

    public boolean setDemuraFlashErase() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_FLASH_ERASE, 0) == 0;
    }

    public boolean setDemuraFlashUpgrade(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_FLASH_UPGRADE, str) == 0;
    }

    public int setDemuraResetTcon(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_RESET_TCON, i);
    }

    public int setDemuraSpiStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_SPI_STATUS, i);
    }

    public int setDemuraSwitchStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_SWITCH_STATUS, i);
    }

    public int setDemuraTconStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_TCON_STATUS, i);
    }

    public int setDolbyIQUserBinEnable(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setDolbyIQUserBinEnable(i);
    }

    public int setDolbyIQUserBinUpdate(String str) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setDolbyIQUserBinUpdate(str);
    }

    public int setDoubleCellMode(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_DUAL_CELL_MODE, i);
    }

    public boolean setDrcValue(String str, int i) {
        int ampDrcP;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_DRC_L)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            ampDrcP = HisAudioApi.setAmpDrcL(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_DRC_H)) {
            HisAudioApi hisAudioApi2 = this.mHisAudioApi;
            ampDrcP = HisAudioApi.setAmpDrcH(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_DRC_M)) {
            HisAudioApi hisAudioApi3 = this.mHisAudioApi;
            ampDrcP = HisAudioApi.setAmpDrcS(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_DRC_P)) {
            HisAudioApi hisAudioApi4 = this.mHisAudioApi;
            ampDrcP = HisAudioApi.setAmpDrcP(i);
        } else {
            ampDrcP = 0;
        }
        return ampDrcP == 0;
    }

    public int setEARCStatus(int i) {
        return HisHdmiApi.setEarcEnable(i);
    }

    public boolean setEnterVcomVideoParameter(int i) {
        setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_ENTER_VCOM, 1);
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setVcomTestEnable(1) == 0;
    }

    public int setEthMacAddress(String str) {
        return 0;
    }

    public boolean setExitVcomVideoParameter(int i) {
        setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_ENTER_VCOM, 0);
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setVcomTestEnable(0) == 0;
    }

    public boolean setFactoryCheckStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_CHECK_SATUS, i) == 0;
    }

    public boolean setFactoryFunctionNumber(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public boolean setFactoryMICTestDBValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TEST_MIC_DB, i) == 0;
    }

    public boolean setFactoryMode(boolean z) {
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FACTORY_MODE, z ? 1 : 0);
        if (configValue == 0 && z) {
            SystemProperties.set("persist.vt.factory.mode", "1");
            Settings.System.putInt(this.mContext.getContentResolver(), SETTING_TV_FACTORY_MODE, 1);
        } else {
            SystemProperties.set("persist.vt.factory.mode", "0");
            SystemProperties.set("sys.vt.factory.switch.touser", "1");
            if (SystemProperties.getInt("sys.vt.user.switch.tofactory", 0) == 1) {
                SystemProperties.set("sys.vt.user.switch.tofactory", "0");
            }
            Settings.System.putInt(this.mContext.getContentResolver(), SETTING_TV_FACTORY_MODE, 0);
        }
        return configValue == 0;
    }

    public boolean setFactoryModeToAudioPicture(int i) {
        AvMiddleWareManager avMiddleWareManager = this.mAvMiddleWareManager;
        return AvMiddleWareManager.setFactoryMode(i) == 0;
    }

    public boolean setFactoryOperatorStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OPERATOR_SATUS, i) == 0;
    }

    public boolean setFirstBootState(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FIRSTBOOT_STATE, i) == 0;
    }

    public boolean setFirstExperienValue(String str, int i) {
        return true;
    }

    public boolean setFirstPowerOnTime(long j) {
        int[] iArr = {(int) ((-1) & j), (int) (j >> 32)};
        StringBuilder sbA = a.a("dataHex:");
        sbA.append(Long.toHexString(j));
        sbA.append(";highHex:");
        sbA.append(Integer.toHexString(iArr[1]));
        sbA.append(";lowHex:");
        sbA.append(Integer.toHexString(iArr[0]));
        Log.e(TAG, sbA.toString());
        return setConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_FIRST_POWER_ON_TIME, iArr, 2) == 0;
    }

    public boolean setFirstTimePowerOn(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public boolean setFunctionNumber(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public boolean setGammaOperation(String str, int[] iArr, int i) {
        int testPattern;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_FACTORY_GAMMA_SELECT)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            testPattern = HisVideoApi.setAutoGammaEnable(iArr[0]);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAMMA_ADJUST_PATTERN)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            testPattern = HisVideoApi.setTestPattern(transformTestPatternIndex(iArr));
        } else {
            testPattern = 0;
        }
        return testPattern == 0;
    }

    public boolean setGammaSelect(int[] iArr, int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setAutoGammaEnable(iArr[0]) == 0;
    }

    public int setGlobalLuminanceGain(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_GLOBAL_LUM_GAIN, i);
    }

    public int setGreenValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_GREEN_VALUE, i);
    }

    public boolean setHotProtectFlag(int i) {
        a.c("[laser]setHotProtectFlag = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_HOT_PROTECT_FLAG, i) == 0;
    }

    public boolean setHotelModeValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_HOTEL_MODE, i) == 0;
    }

    public boolean setHpfFrequency(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public boolean setIranCalendarValue(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public int setLVSDSscStatusByOnce(int i) {
        return 1;
    }

    public int setLaserAgingMode(int i) {
        a.c("[laser]setLaserAgingMode mode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_AGING_MODE, i);
    }

    public int setLaserAlertLedOnOff(int i) {
        a.c("[Laser] setLaserAlertLedOnOff = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_ALERT_LED_ON_OFF, i);
    }

    public boolean setLaserAutoGraphCorrection(int[] iArr, int i) {
        return setConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_AUTO_GRAPH_CORRECTION, iArr, i) == 0;
    }

    public boolean setLaserBackLightMode(int i) {
        a.c("[laser]setLaserBackLightMode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_BACKLIGHT_MODE, i) == 0;
    }

    public boolean setLaserCW1(int i) {
        a.c("[laser]setLaserCW1 = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CW1, i) == 0;
    }

    public boolean setLaserCW2(int i) {
        a.c("[laser]setLaserCW2 = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CW2, i) == 0;
    }

    public int setLaserCloMode(int i) {
        a.c("[laser]setLaserCloMode, mode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLO_MODE, i);
    }

    public int setLaserCloOverlapSelect(int i) {
        a.c("[laser]setLaserCloOverlapSelect, mode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLO_OVERLAP_SELECT, i);
    }

    public int setLaserEcoMode(int i) {
        a.c("[laser]setLaserEcoMode mode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_ECO_MODE, i);
    }

    public int setLaserEyeProtectSwitch(int i) {
        a.c("[Laser] setLaserEyeProtectSwitch = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_EYE_PROTECT_SWITCH, i);
    }

    public boolean setLaserFocus(int i) {
        a.c("[laser]setLaserFocus = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_FOCUS, i) == 0;
    }

    public int setLaserForeignBodyDetectSwitch(int i) {
        a.c("[Laser] setLaserForeignBodyDetectSwitch = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_FOREIGN_BODY_DETECT_SWITCH, i);
    }

    public boolean setLaserGraphCorrectionResult(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_GEOMETRIC_CORRECTION_RESULT, i) == 0;
    }

    public boolean setLaserL5LedCtrl(int i) {
        a.c("[laser]setLaserL5LedCtrl, val = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_L5_LED_CTRL, i) == 0;
    }

    public int setLaserLedFixed(int i) {
        a.c("[laser]setLaserLedFixed, mode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_LED_FIXED, i);
    }

    public int setLaserLedRandom(byte[] bArr) {
        if (bArr == null || bArr.length != 45) {
            Log.v(TAG, "[Laser] setLaserLedRandom return data error!!");
            return -1;
        }
        int[] iArr = new int[15];
        for (int i = 0; i < 15; i++) {
            int i2 = i * 3;
            iArr[i] = (bArr[i2 + 2] << Ascii.DLE) | bArr[i2] | (bArr[i2 + 1] << 8);
        }
        return setConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_LED_RANDOM, iArr, 15);
    }

    public int setLaserMirrorAdjustSwitch(int i) {
        a.c("[Laser] setLaserMirrorAdjustSwitch = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MIRROR_ADJUST_SWITCH, i);
    }

    public int setLaserMirrorAdjustValue(int i) {
        a.c("[Laser] setLaserMirrorAdjustValue = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MIRROR_ADJUST_VALUE, i);
    }

    public int setLaserMirrorSwitch(int i) {
        a.c("[Laser] setLaserMirrorSwitch = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MIRROR_SWITCH, i);
    }

    public int setLaserModeSelect(int i) {
        a.c("[laser]setLaserModeSelect mode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SET_LASER_MODE, i);
    }

    public boolean setLaserMovableCoverStep(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MOVABLE_COVER_STEP, i) == 0;
    }

    public int setLaserOverlapMode(int i) {
        a.c("[laser]setLaserOverlapMode, mode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_OVERLAP_MODE, i);
    }

    public boolean setLaserProjectionMode(int i) {
        a.c("[laser]setLaserProjectionMode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_PROJECTION_MODE, i) == 0;
    }

    public int setLaserScreenType(int i) {
        a.c("[Laser] setLaserScreenType = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SCREENTYPE, i);
    }

    public boolean setLaserSlidingCoverMode(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SLIDING_COVER_MODE, i) == 0;
    }

    public int setLaserSoftwareUpgradeMode(int i) {
        a.c("[Laser] setLaserSoftwareUpgradeMode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SOFTWARE_UPGRADE_MODE, i);
    }

    public int setLaserTemperatureProtectSwitch(int i) {
        a.c("[Laser] setLaserTemperatureProtectSwitch = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_TEMPERATURE_PROTECT_SWITCH, i);
    }

    public int setLightSensorCurve(int i, int i2, int i3) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setLightSensorCurve(i, i2, i3);
    }

    public int setLocalDimmingCheck(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setLocalDimmingCheck(i);
    }

    public int setLocalDimmingState(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setLocalDimming(i);
    }

    public int setLocalDimmingSwitch(boolean z) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setLocalDimming(z ? 1 : 0);
    }

    public int setLogoHotelFromUdisk2Tv(String str) {
        return HisensePlatformConfig.setLogoHotelFromUdisk2Tv(str);
    }

    public boolean setLvdsDetailValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_SPAN, i) == 0;
    }

    public boolean setLvdsMode(boolean z) {
        return true;
    }

    public boolean setMIC1Db(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_ONEDB_MIC, str) == 0;
    }

    public boolean setMIC2Db(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_TWODB_MIC, str) == 0;
    }

    public boolean setMICTestValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_MIC_TEST, i) == 0;
    }

    public boolean setMacAddress(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public int setMacAddressForFac(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_FAC_MAC_ADDRESS, str);
    }

    public boolean setMachineName(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public boolean setMachineNumber(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public int setManualGraphCorrection(int[] iArr) {
        if (iArr[2] < 0) {
            iArr[2] = Math.abs(iArr[2]) | 32768;
        }
        int i = ((iArr[2] & 255) << 24) | iArr[0] | (iArr[1] << 8) | ((iArr[2] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) << 8);
        a.c("[laser]setManualGraphCorrection = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MANUAL_GRAPH_CORRECTION, i);
    }

    public int setMicDbDiffSelect(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_MIC_DB_DIFF_SELECT, i);
    }

    public int setMicLed(int i) {
        LedFactory ledFactory = this.mMicLedController;
        if (ledFactory == null) {
            return 1;
        }
        ledFactory.setMicLed(i);
        return 1;
    }

    public int setMiscInputInfo(int i) {
        a.c("setMiscInputInfo, value = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_MISC_INPUT_INFO_UPDATE, i);
    }

    public int setMultiBalance(int i) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setMultiBalance(i);
    }

    public int setMultiDigitalAMPValueByDevId(int i, int i2, int i3) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setMultiDigitalAMPValueByDevId(i, i2, i3);
    }

    public int setMuteColor(int i) {
        Log.d(TAG, "setMuteColor" + i);
        int blueMuteEnable = HisVideoApi.setBlueMuteEnable(i);
        a.b("setMuteColor ret = ", blueMuteEnable, TAG);
        return blueMuteEnable;
    }

    public boolean setOledCpcValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_CPC_ONOFF, i) == 0;
    }

    public boolean setOledLbc2Value(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_LBC2_ONOFF, i) == 0;
    }

    public int setOledMaintenanceStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_OLED_MAINTENANCE_STATUS, i);
    }

    public int setOledPanelCtrl(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_OLED_PANEL_CTRL, i);
    }

    public boolean setOledPixelShiftShapeValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_SHAPE, i) == 0;
    }

    public boolean setOledPixelShiftTimerValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_TIMER, i) == 0;
    }

    public boolean setOledPixelShiftTypeValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_TYPE, i) == 0;
    }

    public boolean setOledPowerOffEnable(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_POWEROFF_ENABLE, i) == 0;
    }

    public boolean setOledStopEvddDetDetectFlag(boolean z) {
        int i = true == z ? 2 : 0;
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_STOPDET_FLAG, i);
        Log.i(TAG, "setOledStopEvddDetDetectFlag, flag is " + z + ", setFlag is " + i + ", relValue is " + configValue);
        return configValue == 0;
    }

    public boolean setOledTpcValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_TPC_ONOFF, i) == 0;
    }

    public boolean setOverScanBottom(int[] iArr) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setOverscanBottom(iArr[1]) == 0;
    }

    public boolean setOverScanLeft(int[] iArr) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setOverscanLeft(iArr[2]) == 0;
    }

    public boolean setOverScanRight(int[] iArr) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setOverscanRight(iArr[3]) == 0;
    }

    public boolean setOverScanTop(int[] iArr) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setOverscanTop(iArr[0]) == 0;
    }

    public boolean setOverscanValue(String str, int[] iArr, int i) {
        return setConfigIntArrayValue(str, iArr, i) == 0;
    }

    public boolean setPagainValue(String str, int i) {
        int ampGain;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_AMP_PAGAIN)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            ampGain = HisAudioApi.setAmpGain(i);
        } else {
            ampGain = 0;
        }
        return ampGain == 0;
    }

    public boolean setPictureMode(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setPictureModeByFactory(i) == 0;
    }

    public boolean setPictureModeDetailValue(String str, int i) {
        int backlightPercentByFactory;
        if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_BRIGHTNESS)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            backlightPercentByFactory = HisVideoApi.setBrightnessByFactory(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_CONTRAST)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            backlightPercentByFactory = HisVideoApi.setContrastByFactory(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_SAT)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            backlightPercentByFactory = HisVideoApi.setSaturationByFactory(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_SHP)) {
            HisVideoApi hisVideoApi4 = this.mHisVideoApi;
            backlightPercentByFactory = HisVideoApi.setSharpnessByFactory(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_DISP_DISP_DISP_BACK_LIGHT)) {
            HisVideoApi hisVideoApi5 = this.mHisVideoApi;
            backlightPercentByFactory = HisVideoApi.setBacklightPercentByFactory(i);
        } else {
            backlightPercentByFactory = 0;
        }
        return backlightPercentByFactory == 0;
    }

    public boolean setPowerControlMode(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public boolean setPowerIndicatorEffect(String str, int i) {
        return true;
    }

    public boolean setPowerLedValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_POWER_LED, i) == 0;
    }

    public int setPowerOnMode(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_POWER_CTRL, i);
    }

    public int setPowerSwquence(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_POWER_SEQUENCE, i);
    }

    public boolean setPrescaleValue(String str, int i) {
        int soundSocPrescale;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOC_PRESCALE)) {
            HisAudioApi hisAudioApi = this.mHisAudioApi;
            soundSocPrescale = HisAudioApi.setSoundSocPrescale(i);
        } else {
            soundSocPrescale = 0;
        }
        return soundSocPrescale == 0;
    }

    public int setProjectId(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_PROJECT_ID, i);
    }

    public boolean setPwmFrequency(String str, int i) {
        int backlightPWMFreq;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PWM_FREQ)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            backlightPWMFreq = HisVideoApi.setBacklightPWMFreq(i);
        } else {
            backlightPWMFreq = 0;
        }
        return backlightPWMFreq == 0;
    }

    public int setRedValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_RED_VALUE, i);
    }

    public boolean setRegionValue(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public int setResetKeypadStatus(int i) {
        Log.d(TAG, "setResetKeypadStatus");
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_RESET_KEYPAD_STATUS, i);
    }

    public boolean setSACustomerType(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_SA_CUSTOMER_TYPE, i) == 0;
    }

    public int setSSCDDRModu(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_SPAN, i) == 0 ? 0 : -1;
    }

    public boolean setSSCVByOneEnable(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_ONOFF, i == 1 ? 0 : 1) == 0;
    }

    public boolean setSSCVByOneRange(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_STEP, i) == 0;
    }

    public boolean setScreenLessMode(int i) {
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_SCREEN_LESS_MODE, i);
        if (1 == i) {
            SystemProperties.set(HISENSE_FFGA_SCREENLESS, "true");
            setWoWWoLWoC(1);
        } else {
            SystemProperties.set(HISENSE_FFGA_SCREENLESS, "false");
            setWoWWoLWoC(0);
        }
        return configValue == 0;
    }

    public int setSensorEnable(boolean z) {
        return 0;
    }

    public boolean setSerialNewNumber(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public boolean setSerialNewNumberLength(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public boolean setServiceTag(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SERVICE_TAG, str) == 0;
    }

    public int setSilentPowerOn(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_SILENT_POWER_ON, i);
    }

    public boolean setSocDrc(int i) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setSoundSocDRCFeature(1, i) == 0;
    }

    public boolean setSocDrcEnable(boolean z) {
        HisAudioApi hisAudioApi = this.mHisAudioApi;
        return HisAudioApi.setSoundSocDRCFeature(0, z ? 1 : 0) == 1;
    }

    public boolean setSoftwareVersion(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public boolean setStartingOnCount(long j) {
        int[] iArr = {(int) ((-1) & j), (int) (j >> 32)};
        StringBuilder sbA = a.a("dataHex:");
        sbA.append(Long.toHexString(j));
        sbA.append(";highHex:");
        sbA.append(Integer.toHexString(iArr[1]));
        sbA.append(";lowHex:");
        sbA.append(Integer.toHexString(iArr[0]));
        Log.e(TAG, sbA.toString());
        return setConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_STARTING_ON_COUNT, iArr, 2) == 0;
    }

    public boolean setStoreMode(int i) {
        a.a("set Store Mode = ", i, "\n", TAG);
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_USER_MODE_SELECT, i) == 0;
    }

    public boolean setStrStartOnCount(long j) {
        int[] iArr = {(int) ((-1) & j), (int) (j >> 32)};
        StringBuilder sbA = a.a("dataHex:");
        sbA.append(Long.toHexString(j));
        sbA.append(";highHex:");
        sbA.append(Integer.toHexString(iArr[1]));
        sbA.append(";lowHex:");
        sbA.append(Integer.toHexString(iArr[0]));
        Log.e(TAG, sbA.toString());
        return setConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_STR_STARTING_ON_COUNT, iArr, 2) == 0;
    }

    public boolean setTconUpgradeFlag(int i) {
        Log.d(TAG, "setTconUpgradeFlag, flag is" + i);
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TCON_UPGRADE_FLAG, i);
        a.b("setTconUpgradeFlag, ret is", configValue, TAG);
        return configValue == 0;
    }

    public boolean setTestPatternValue(String str, int[] iArr, int i) {
        int insidePattern;
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_WB_TEST_PATTERN)) {
            insidePattern = 0;
        } else if (iArr[0] == 0) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            insidePattern = HisVideoApi.setInsidePattern(255);
        } else {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            insidePattern = HisVideoApi.setInsidePattern(iArr[1]);
        }
        return insidePattern == 0;
    }

    public void setTimeSyncFromTsMode(int i) {
    }

    public boolean setToFacMode(int i) {
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOFAC_MODE, i);
        if (configValue == 0) {
            if (i == 0) {
                try {
                    SystemProperties.set("persist.vt.tofac_mode", MtkTvRatingConvert2Goo.RATING_STR_U);
                } catch (Exception e2) {
                    Log.d(TAG, e2.toString());
                }
            } else if (i == 1) {
                try {
                    SystemProperties.set("persist.vt.tofac_mode", MtkTvRatingConvert2Goo.RATING_STR_M);
                } catch (Exception e3) {
                    Log.d(TAG, e3.toString());
                }
            }
        }
        return configValue == 0;
    }

    public boolean setTvBrand(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BRAND, str) == 0;
    }

    public boolean setTvCountry(String str, int i) {
        int configValue = setConfigValue(str, i);
        HisenseTVNativeWrapper.factoryWriteFile_native("/perm/country.cfg", i + "");
        Log.v(TAG, "[wifiCountry path  /perm/country.cfg");
        return configValue == 0;
    }

    public boolean setTvLanguage(String str, int i) {
        int configValue = setConfigValue(str, i);
        if (str.equals(MtkTvConfigTypeBase.CFG_SYSTEM_LANGUAGE) && i > 0 && i < this.MAX_DTV_LANGUAGE) {
            String str2 = this.val_language_dtv[i];
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_SUBTITLE_SUBTITLE_LANG, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_SUBTITLE_SUBTITLE_LANG_2ND, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_GUI_AUD_LANG_AUD_LANGUAGE, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_GUI_AUD_LANG_AUD_2ND_LANGUAGE, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_TTX_LANG_TTX_DIGTL_ES_SELECT, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_GUI_LANG_GUI_LANGUAGE, str2);
        }
        return configValue == 0;
    }

    public boolean setTvRunTime(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public int setTvsRcuType(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_INPUT_SRC_INPUT_SRC_TVS_RCU_TYPE, i);
    }

    public boolean setUartCtrlState(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_UART_CTRL, i) == 0;
    }

    public boolean setUpgradeFlag(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_UPGRADE_FLAG, i) == 0;
    }

    public boolean setUsbUpgradeFlag(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public boolean setUserIrCodeIndex(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_IR_CODE_INDEX, i) == 0;
    }

    public boolean setVcomArrayValue(int[] iArr, int i) {
        return setConfigValue(iArr[0] == 2 ? MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE_2 : MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE_1, iArr[1]) == 0;
    }

    public boolean setVcomBmpInfo(String str, String str2, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("#");
        sb.append(i);
        return setConfigString(str, sb.toString()) == 0;
    }

    public boolean setVcomPanelType(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_PANEL_TYPE, i) == 0;
    }

    public boolean setVcomValue(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public boolean setVideoCurveDetailValue(String str, int i) {
        int sharpnessMax;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MIN)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setBrightnessMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_20)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setBrightness20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MID)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setBrightnessMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_80)) {
            HisVideoApi hisVideoApi4 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setBrightness80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MAX)) {
            HisVideoApi hisVideoApi5 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setBrightnessMax(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MIN)) {
            HisVideoApi hisVideoApi6 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setContrastMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_20)) {
            HisVideoApi hisVideoApi7 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setContrast20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MID)) {
            HisVideoApi hisVideoApi8 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setContrastMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_80)) {
            HisVideoApi hisVideoApi9 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setContrast80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MAX)) {
            HisVideoApi hisVideoApi10 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setContrastMax(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MIN)) {
            HisVideoApi hisVideoApi11 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSaturationMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_20)) {
            HisVideoApi hisVideoApi12 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSaturation20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MID)) {
            HisVideoApi hisVideoApi13 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSaturationMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_80)) {
            HisVideoApi hisVideoApi14 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSaturation80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MAX)) {
            HisVideoApi hisVideoApi15 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSaturationMax(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MIN)) {
            HisVideoApi hisVideoApi16 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setHueMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_20)) {
            HisVideoApi hisVideoApi17 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setHue20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MID)) {
            HisVideoApi hisVideoApi18 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setHueMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_80)) {
            HisVideoApi hisVideoApi19 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setHue80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MAX)) {
            HisVideoApi hisVideoApi20 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setHueMax(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MIN)) {
            HisVideoApi hisVideoApi21 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSharpnessMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_20)) {
            HisVideoApi hisVideoApi22 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSharpness20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MID)) {
            HisVideoApi hisVideoApi23 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSharpnessMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_80)) {
            HisVideoApi hisVideoApi24 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSharpness80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MAX)) {
            HisVideoApi hisVideoApi25 = this.mHisVideoApi;
            sharpnessMax = HisVideoApi.setSharpnessMax(i);
        } else {
            sharpnessMax = 0;
        }
        return sharpnessMax == 0;
    }

    public boolean setVoiceLedStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VOICE_LED_STATUS, i) == 0;
    }

    public boolean setWB20PointBlue(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setFactoryMultiPointWhiteBalanceBlue(i) == 0;
    }

    public boolean setWB20PointGreen(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setFactoryMultiPointWhiteBalanceGreen(i) == 0;
    }

    public boolean setWB20PointLevel(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setFactoryMultiPointWhiteBalanceLevel(i) == 0;
    }

    public boolean setWB20PointRed(int i) {
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.setFactoryMultiPointWhiteBalanceRed(i) == 0;
    }

    public boolean setWhiteBalancePanel(String str, int i) {
        int panel;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PANEL_TYPE)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            panel = HisVideoApi.setPanel(i);
        } else {
            panel = 0;
        }
        return panel == 0;
    }

    public boolean setWhiteBalanceValue(String str, int i) {
        int factoryWhiteBalanceOffsetBlue;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_R)) {
            HisVideoApi hisVideoApi = this.mHisVideoApi;
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceGainRed(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_G)) {
            HisVideoApi hisVideoApi2 = this.mHisVideoApi;
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceGainGreen(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_B)) {
            HisVideoApi hisVideoApi3 = this.mHisVideoApi;
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceGainBlue(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_R)) {
            HisVideoApi hisVideoApi4 = this.mHisVideoApi;
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceOffsetRed(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_G)) {
            HisVideoApi hisVideoApi5 = this.mHisVideoApi;
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceOffsetGreen(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_B)) {
            HisVideoApi hisVideoApi6 = this.mHisVideoApi;
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceOffsetBlue(i);
        } else {
            factoryWhiteBalanceOffsetBlue = 0;
        }
        return factoryWhiteBalanceOffsetBlue == 0;
    }

    public int setWhiteValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_WHITE_VALUE, i);
    }

    public boolean setWifiCheckConfigValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_WIFI_CONFIG_CHECK, i) == 0;
    }

    public boolean setWifiTriggerReset() {
        Log.i(TAG, "rena setWifiTriggerReset");
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_NETWORK_WLAN_TRIGGER_RESET, 2);
        Log.i(TAG, "rena setWifiTriggerReset relValue " + configValue);
        return configValue == 0;
    }

    public void setWoWWoLWoC(int i) {
        boolean z = i == 1;
        if (MtkTvRatingConvert2Goo.STR_MPAA_NA.equals(SystemProperties.get("ro.vt.market")) || "KO".equals(SystemProperties.get("ro.vt.market"))) {
            MtkNetworkManager.getInstance().setEnableWoPacket(z);
            Log.d(TAG, "set WOW,WOC,WOL,Screenless " + z);
            return;
        }
        if (!HisenseCountryLanInfo.LOCALE_JPN_COUNTRY.equalsIgnoreCase(SystemProperties.get("ro.vt.market", "0"))) {
            a.a("set Screenless ", z, TAG);
            return;
        }
        MtkNetworkManager.getInstance().setEnableWoPacket(z);
        Log.d(TAG, "set WOC for TVS,Screenless " + z);
    }

    public boolean setZDMICValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_ZD_MIC, i) == 0;
    }

    public boolean setZdMIC1Db(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_1MIC_DB_ZD, str) == 0;
    }

    public boolean setZdMIC2Db(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_2MIC_DB_ZD, str) == 0;
    }

    public boolean showSnowAsNoSignal(String str, int i) {
        return true;
    }

    public boolean startJb() {
        Log.d(TAG, "startJb");
        return setConfigValue(PANELPROTECT_CONFIG_NAME, 3) == 0;
    }

    public void startLanB2BWorkThread() {
        Log.v(TAG, "[B2B]startLan B2B Thread.\n");
        HisenseTVNativeWrapper.startLanB2BWorkThread_native();
    }

    public boolean startOffrs() {
        Log.d(TAG, "startOffrs");
        return setConfigValue(PANELPROTECT_CONFIG_NAME, 1) == 0;
    }

    public int startRepair() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_START_REPAIR, 0);
    }

    public void startSerialWorkThread() {
        Log.v(TAG, "startSerialWorkThread.\n");
        HisenseTVNativeWrapper.startSerialWorkThread_native();
    }

    public void startTestB2BHWPortWorkThread() {
        Log.v(TAG, "[B2B]sstartTestB2BHWPortWorkThread Thread.\n");
        HisenseTVNativeWrapper.startTestB2BHWPortWorkThread_native();
    }

    public boolean stopJb() {
        Log.d(TAG, "stopJb");
        return setConfigValue(PANELPROTECT_CONFIG_NAME, 4) == 0;
    }

    public boolean stopOffrs() {
        Log.d(TAG, "stopOffrs");
        return setConfigValue(PANELPROTECT_CONFIG_NAME, 2) == 0;
    }

    public boolean stopTv() {
        return false;
    }

    public boolean updateVcomArrayValue(int[] iArr, int i) {
        return setConfigValue(iArr[0] == 2 ? MtkTvConfigTypeBase.CFG_FACTORY_VCOM_SAVE_2 : MtkTvConfigTypeBase.CFG_FACTORY_VCOM_SAVE_1, iArr[1]) == 0;
    }

    public int writeFile(String str, String str2, boolean z, boolean z2) {
        return HisensePlatformConfig.writeFile(str, str2, z, z2);
    }

    public boolean writeToExternalControl(int i, byte[] bArr, int i2, int i3) {
        Log.d(TAG, "Calling writeToExternalControl interface in handler.");
        if (HisenseTVNativeWrapper.writeToExternalControl_native(i, bArr, i2, i3) == 0) {
            return true;
        }
        Log.e(TAG, "Write to serial port jni error");
        return false;
    }

    public int getBootUpLogo() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FAC_BOOTUP_LOGO);
    }

    public int getConfigValue(int i, String str) {
        int configValue_native;
        synchronized (HisenseTvAPIBase.class) {
            configValue_native = HisenseTVNativeWrapper.getConfigValue_native(i, str);
        }
        return configValue_native;
    }

    public boolean setBootUpLogo(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FAC_BOOTUP_LOGO, i) == 0;
    }

    public int setConfigValue(String str, int i, int i2) {
        return setConfigValue(-1, str, i, i2);
    }

    public int getPictureMode(String str) {
        if (!str.equals(MtkTvConfigTypeBase.CFG_VIDEO_PIC_MODE)) {
            return 0;
        }
        HisVideoApi hisVideoApi = this.mHisVideoApi;
        return HisVideoApi.getPictureModeByFactory();
    }

    public int setColorTemperature(int i) {
        return HisVideoApi.setColorTemperature(i);
    }

    public int setConfigValue(int i, String str, int i2, int i3) {
        int configValue_native;
        synchronized (HisenseTvAPIBase.class) {
            configValue_native = HisenseTVNativeWrapper.setConfigValue_native(i, str, i2, i3);
        }
        return configValue_native;
    }

    public boolean doFactoryOKReset(boolean z, IFactoryCleanAppsListener iFactoryCleanAppsListener) {
        Log.i(TAG, "doFactoryOKReset.......,");
        String[] strArr = this.mLaunchedPackages;
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "user_setup_complete", 0, -3);
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "tv_user_setup_complete", 0, -3);
        boolean zDoFactoryOKReset = doFactoryOKReset();
        Log.e(TAG, " setFactoryConfiguration..result.. " + zDoFactoryOKReset);
        if (getMarketRegion().equalsIgnoreCase(HisenseCountryLanInfo.LOCALE_JPN_COUNTRY)) {
            setConfigValue(MtkTvConfigTypeBase.CFG_INPUT_SRC_INPUT_SRC_TVS_RCU_TYPE, 0);
        }
        HisenseTVNativeWrapper.setEthernetWolCtl_native(false);
        HisenseTVNativeWrapper.setWifiWolCtl_native(false);
        try {
            SystemProperties.set("ctl.reset", "/data/misc/wifi/WifiConfigStore.xml");
        } catch (Exception e2) {
            Log.d(TAG, e2.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/wifi/softap.conf");
        } catch (Exception e3) {
            Log.d(TAG, e3.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/wifi/ipconfig.txt");
        } catch (Exception e4) {
            Log.d(TAG, e4.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/ethernet/ipconfig.txt");
        } catch (Exception e5) {
            Log.d(TAG, e5.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/ppp/pppoe.data");
        } catch (Exception e6) {
            Log.d(TAG, e6.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/bluedroid/bt_config.conf");
        } catch (Exception e7) {
            Log.d(TAG, e7.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/bluedroid/bt_config.bak");
        } catch (Exception e8) {
            Log.d(TAG, e8.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/property/persistent_properties");
        } catch (Exception e9) {
            Log.d(TAG, e9.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/system/users/0/settings_global.xml");
        } catch (Exception e10) {
            Log.d(TAG, e10.toString());
        }
        Log.e(TAG, " finish start thread cleanApps ");
        Log.e(TAG, "at the end of the do factoryOK");
        File file = new File(ADVERTISEMENT_BOOTANIMATION_FILE);
        if (file.exists()) {
            Log.i(TAG, "advertisement animation exist, delete return: " + file.delete());
        }
        if (!zDoFactoryOKReset) {
            return true;
        }
        try {
            SystemProperties.set("persist.vt.tofac_mode", MtkTvRatingConvert2Goo.RATING_STR_U);
            return true;
        } catch (Exception e11) {
            Log.d(TAG, e11.toString());
            return true;
        }
    }

    public HisenseTvAPIBase(Context context) {
        this.mContext = context;
        SystemProperties.get("ro.hisense.market");
    }
}
